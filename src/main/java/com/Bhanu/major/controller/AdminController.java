package com.Bhanu.major.controller;

import com.Bhanu.major.dto.ProductDTO;
import com.Bhanu.major.model.Category;
import com.Bhanu.major.model.Product;
import com.Bhanu.major.service.CategoryService;
import com.Bhanu.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    private final CategoryService categoryService;
    @Autowired
    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin")
    public String adminhome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategories(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategories(@PathVariable("id") Integer id,Model model){
        Optional<Category> category = categoryService.findCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category);
            return "categoriesAdd";
        }
        else{
            return "404";
        }
    }
    @GetMapping("admin/categories/delete/{id}")
    public String deleteCategories(@PathVariable("id") Integer id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
    @PostMapping("/admin/categories/add")
    public String addCategories(@ModelAttribute("category")Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }


    // Product section
@Autowired
    ProductService productService;
    @GetMapping("/admin/products")
    public String getProducts(Model model){
        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String addGetProduct(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file, @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.findCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath,file.getBytes());
        }
        else{
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }
    @GetMapping("admin/product/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model){
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }
}
