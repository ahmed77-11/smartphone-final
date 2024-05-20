package com.ahmed.smartphonejava.dao;

import java.util.List;

import com.ahmed.smartphonejava.beans.Category;

public interface ICategoryDao <T>{
    public List<T> getAllCategories();
    public T getCategoryById(int id);
	boolean addCategory(Category category);
	boolean deleteCategory(Category category);
	boolean updateCategory(Category category);
}
