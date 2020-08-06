package com.example.mymail.dto;

import com.example.mymail.model.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 创建和修改商品时使用的参数
 * Created by macro on 2018/4/26.
 */
public class PmsProductParam extends com.example.mymail.model.PmsProduct {
    @ApiModelProperty("商品阶梯价格设置")
    private List<com.example.mymail.model.PmsProductLadder> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<com.example.mymail.model.PmsProductFullReduction> productFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<com.example.mymail.model.PmsMemberPrice> memberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<com.example.mymail.model.PmsSkuStock> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<com.example.mymail.model.PmsProductAttributeValue> productAttributeValueList;
    @ApiModelProperty("专题和商品关系")
    private List<com.example.mymail.model.CmsSubjectProductRelation> subjectProductRelationList;
    @ApiModelProperty("优选专区和商品的关系")
    private List<com.example.mymail.model.CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList;

    public List<com.example.mymail.model.PmsProductLadder> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<com.example.mymail.model.PmsProductLadder> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List<com.example.mymail.model.PmsProductFullReduction> getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List<com.example.mymail.model.PmsProductFullReduction> productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }

    public List<com.example.mymail.model.PmsMemberPrice> getMemberPriceList() {
        return memberPriceList;
    }

    public void setMemberPriceList(List<com.example.mymail.model.PmsMemberPrice> memberPriceList) {
        this.memberPriceList = memberPriceList;
    }

    public List<com.example.mymail.model.PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<com.example.mymail.model.PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<com.example.mymail.model.PmsProductAttributeValue> getProductAttributeValueList() {
        return productAttributeValueList;
    }

    public void setProductAttributeValueList(List<com.example.mymail.model.PmsProductAttributeValue> productAttributeValueList) {
        this.productAttributeValueList = productAttributeValueList;
    }

    public List<com.example.mymail.model.CmsSubjectProductRelation> getSubjectProductRelationList() {
        return subjectProductRelationList;
    }

    public void setSubjectProductRelationList(List<com.example.mymail.model.CmsSubjectProductRelation> subjectProductRelationList) {
        this.subjectProductRelationList = subjectProductRelationList;
    }

    public List<com.example.mymail.model.CmsPrefrenceAreaProductRelation> getPrefrenceAreaProductRelationList() {
        return prefrenceAreaProductRelationList;
    }

    public void setPrefrenceAreaProductRelationList(List<com.example.mymail.model.CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList) {
        this.prefrenceAreaProductRelationList = prefrenceAreaProductRelationList;
    }
}
