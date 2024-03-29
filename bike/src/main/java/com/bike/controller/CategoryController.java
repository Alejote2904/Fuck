package com.bike.controller;

import com.bike.model.Category;
import com.bike.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/Category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategory(@PathVariable("id") int id) {
        return categoryService.getCategory(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteCategory(@PathVariable int id){
        return categoryService.deleteCategory(id);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Category updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }
}
