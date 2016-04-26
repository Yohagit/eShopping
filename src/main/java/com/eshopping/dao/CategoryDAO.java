package com.eshopping.dao;
/** 
 * author: Somayeh
 * 
 **/
import java.util.ArrayList;
import java.util.List;
import com.eshopping.model.Category;
import com.eshopping.model.Product;

public interface CategoryDAO {
	public ArrayList<Category> listCategories();

	public void addCategory(Category category);

	public List<Category> getAllCategories();

	public void deleteCategory(int pid);

	public Category getCategoryById(int pid);

	public void updateCategory(Category category);
}
