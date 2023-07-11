package com.jbk.dao;

import java.util.List;

import com.jbk.entity.Category;


public interface CategoryDao {
		public boolean saveCategory(Category category);
		public Category getCategoryById(String categoryId);
		public List<Category> getAllCategorys();
		public boolean deleteCategoryById(String categoryId);
		public boolean updateCategory(Category category);
		

}
