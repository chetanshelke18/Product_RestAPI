package com.jbk.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.jbk.dao.CategoryDao;
import com.jbk.entity.Category;
import com.jbk.service.CategoryService;

public class CategoryServiceIMPL implements CategoryService{

@Autowired	
private CategoryDao dao;

@Override
public boolean saveCategory(Category category) {
	String categoryId = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
	category.setCategoryId(categoryId);
	boolean added = dao.saveCategory(category);
	return added;
}

@Override
public Category getCategoryById(String categoryId) {
	
	return null;
}

@Override
public List<Category> getAllCategorys() {
	
	return null;
}

@Override
public boolean deleteCategoryById(String categoryId) {
	
	return false;
}

@Override
public boolean updateCategory(Category category) {
	
	return false;
}

}
