package com.eshopping.controller;

/**
 *
 * @author Tunlaya
 */
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.eshopping.model.Category;
import com.eshopping.service.CategoryService;

@Controller
@SessionAttributes({ "user", "status", "listCategories", "searchProduct" ,"size","shoppingCart","cartProducts", "total"})
public class CategoryController {
	/*
	private CategoryService categoryService;
	
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	

	@RequestMapping("/admin/category")
	public String showProducList(Model model){
		model.addAttribute("categories", categoryService.getAllCategories());
		return "/admin_management/category/category";
	}
	@RequestMapping(value="/admin/category/edit", method = RequestMethod.GET)
	public String showCategoryEdit(Model model, @RequestParam("pid") String categoryId, HttpServletRequest request){				
		
		int id = Integer.parseInt(categoryId);
		
		model.addAttribute("category",categoryService.getCategoryById(id));
		return "/admin_management/category/category_edit";
	}
	@RequestMapping("/admin/category/add")
	public String showCategoryAdd(Model model){
		model.addAttribute("category", new Category());
		return "/admin_management/category/category_add";
	}
	@RequestMapping(value="/admin/category/update")
	public String doUpdateCategory(Model model,
			@ModelAttribute("category") Category category,
			@RequestParam("pid") String categoryId,
			HttpServletRequest request){	
		category.setId(Integer.parseInt(categoryId));
		categoryService.updateCategory(category);
		return "redirect:/admin/category";
	}
	@RequestMapping("/admin/category/doAdd")
	public String doAddCategory(@ModelAttribute Category category, BindingResult result, HttpServletRequest request){
		
		
		categoryService.addCategory(category);
		
		return "redirect:/admin/category";
		
	}
	@RequestMapping("/admin/category/delete")
	public String deleteCategory(@RequestParam("pid") String categoryId)
	{
		int id = Integer.parseInt(categoryId);
		categoryService.deleteCategory(id);
		return "redirect:/admin/category";
	}*/
}
