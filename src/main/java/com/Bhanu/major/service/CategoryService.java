package com.Bhanu.major.service;

import com.Bhanu.major.model.Category;
import com.Bhanu.major.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public void deleteCategory(Integer categoryId){
        categoryRepository.deleteById(categoryId);
    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public Optional<Category> findCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
}
