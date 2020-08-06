package com.example.mymail.dto;

import com.example.mymail.model.PmsProductCategory;
import lombok.Data;

import java.util.List;

@Data
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    private List<PmsProductCategory> children;
}
