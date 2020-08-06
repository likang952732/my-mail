package com.example.mymail.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.mymail.bo.AdminUserDetails;
import com.example.mymail.dao.UmsAdminRoleRelationDao;
import com.example.mymail.dto.UmsAdminParam;
import com.example.mymail.mapper.UmsAdminMapper;
import com.example.mymail.mapper.UmsAdminRoleRelationMapper;
import com.example.mymail.model.*;
import com.example.mymail.service.UmsAdminService;
import com.example.mymail.util.JwtTokenUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/29 14:57   
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Resource
    private UmsAdminRoleRelationMapper roleRelationMapper;

    @Override
    public int updateAdmin(Long id, UmsAdmin admin) {
        UmsAdmin oldAdmin = adminMapper.selectByPrimaryKey(id);
        String password = admin.getPassword();
        if(oldAdmin.getPassword().equals(password)) {
            admin.setPassword(null);
        } else {
            if(!StringUtils.isEmpty(password)) {
                String encodePassword = passwordEncoder.encode(password);
                admin.setPassword(encodePassword);
            }
        }
        admin.setId(id);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //删除关系
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        UmsAdminRoleRelationExample.Criteria criteria = example.createCriteria();
        criteria.andAdminIdEqualTo(adminId);
        roleRelationMapper.deleteByExample(example);
        //添加关系
        if(!CollectionUtils.isEmpty(roleIds)) {
            ArrayList<UmsAdminRoleRelation> list = new ArrayList();
            for (Long roleId: roleIds) {
                UmsAdminRoleRelation relation = new UmsAdminRoleRelation();
                relation.setAdminId(adminId);
                relation.setRoleId(roleId);
                list.add(relation);
            }
            adminRoleRelationDao.insertList(list);
        }
        return count;
    }

    @Override
    public int deleteAdmin(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            //insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        com.example.mymail.model.UmsAdminExample example = new com.example.mymail.model.UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public List<UmsAdmin> getUserPage(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        if(StrUtil.isNotBlank(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return adminRoleRelationDao.getResourceList(adminId);
    }

}
