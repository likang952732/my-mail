package com.example.mymail.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台权限节点封装
 * Created by macro on 2018/9/30.
 */
public class UmsPermissionNode extends com.example.mymail.model.UmsPermission {
    @Getter
    @Setter
    private List<UmsPermissionNode> children;
}
