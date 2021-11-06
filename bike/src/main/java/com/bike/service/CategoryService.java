package com.bike.service;

import com.bike.model.Category;
import com.bike.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> category1 = categoryRepository.getCategory(category.getId());
            if (category1.isEmpty()) {
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }

    public boolean deleteCategory(int id) {

        Optional<Category> categoria = getCategory(id);

        if (categoria.isEmpty()) {
            return false;
        } else {
            categoryRepository.delete(categoria.get());
            return true;
        }
    }

    /*
        {"id":1,"name":"modificada","description":"se ha modificado"}
     */
    public Category updateCategory(Category category) {
        if (category.getId() != null) {
            Optional<Category> g = categoryRepository.getCategory(category.getId());
            if (!g.isEmpty()) {
                if (category.getDescription() != null) {
                    g.get().setDescription(category.getDescription());
                }
                if (category.getName() != null) {
                    g.get().setName(category.getName());
                }
                return categoryRepository.save(g.get());
            }
        }
        return category;
    }
}
