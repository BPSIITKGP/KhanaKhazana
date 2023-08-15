package com.Bhanu.major.dto;

import com.Bhanu.major.model.Category;
import lombok.Data;

import javax.persistence.*;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Integer categoryId;
    private double price;
    private double weight;
    private String description;
    private String imageName;
}
