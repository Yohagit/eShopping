package com.eshopping.controller;

/**
 *
 * @author Tunlaya
 */
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eshopping.model.Admin;
import com.eshopping.model.Category;
import com.eshopping.model.Customer;
import com.eshopping.model.Order;
import com.eshopping.model.Product;
import com.eshopping.model.SystemUser;
import com.eshopping.model.Vendor;
import com.eshopping.service.CategoryService;
import com.eshopping.service.OrderService;
import com.eshopping.service.ProductService;
import com.eshopping.service.SystemUserService;
import com.eshopping.service.VendorService;

@Controller
@SessionAttributes({ "user"})
public class AdminController {

	@Autowired
	private SystemUserService customerService;

	@Autowired
	private VendorService vendorService;
	
	@Autowired
	OrderService orderService;
        
        @Autowired
	private ProductService productService;
        
        @Autowired
        private CategoryService categoryService;
 
	@RequestMapping("/admin/system")
	public String showAdminPage(ModelMap map) {

		return "redirect:/admin/vendor";
	}

	@RequestMapping("/customers")
	public String allCustomers(Model model) {
		List<Customer> customers = customerService.allCustomerUsers();
		model.addAttribute("customers", customers);
		return "manageCustomers";
	}
        
        public boolean isAdmin(HttpSession session) {
            
            try {
                    Admin admin = (Admin)session.getAttribute("user");
                    if(admin!=null) { 
                            if (admin.getRole().equals("admin")) return true;
                    }
                   return false;
            } 
            catch (Exception e) {		
                    return false;
            }  
        }

	@RequestMapping("/admin/vendor")
	public String allVendors(Model model, HttpServletRequest request, HttpSession session) {
            
       
            if(isAdmin(session)) {
                    List<Vendor> vendors = vendorService.allVendors();
                    model.addAttribute("vendors", vendors);
                    return "admin_management/vendor/vendor";
            }
            
            return "redirect:/"; 
	}
	
	
	@RequestMapping("/admin/vendor/deactivate")
	public String disableVendor(Model model, @ModelAttribute("vendor") Vendor vendor, BindingResult result, @RequestParam("id") String vendorId, HttpServletRequest request) {
		
		int id = Integer.parseInt(vendorId);
		Vendor v = vendorService.getVendorById(id);
		v.setStatus(2);
	
		vendorService.updateVendor(v);
		return "redirect:/admin/vendor";
	}
	
	
	@RequestMapping("/admin/vendor/activate")
	public String activateVendor(Model model, @ModelAttribute("vendor") Vendor vendor, BindingResult result, @RequestParam("id") String vendorId, HttpServletRequest request) {
		
		int id = Integer.parseInt(vendorId);
		Vendor v = vendorService.getVendorById(id);
		v.setStatus(1);
	
		vendorService.updateVendor(v);
		return "redirect:/admin/vendor";
	}
	
	@RequestMapping(value = "/admin/system/viewHistory", method = RequestMethod.GET)
	public String viewCustomerHistory(Model model, HttpSession session) {
		
		SystemUser vendor = (SystemUser) session.getAttribute("user");
		System.out.println("*********"+vendor.getEmail());
		int id= vendor.getUserId();
		ArrayList<Order> orders= orderService.getAllOrders();
		model.addAttribute("orders", orders);
		System.out.println("orders" + orders.size());
//		System.out.println("customer");
		return "/admin/system/viewOrderHistory";
	}
        
        //===============================================admin manage product
        @RequestMapping("/admin/product")
	public String showAllProducts(Model model, HttpServletRequest request, HttpSession session) {
		
                if(isAdmin(session)) {
                        List<Product> products = productService.getAllProducts();
                        model.addAttribute("products", products);
                        return "admin_management/product/product";
                }
                
                return "redirect:/"; 
	}
        
	@RequestMapping("/admin/product/reject")
	public String rejectProduct(Model model, @ModelAttribute("product") Product product, BindingResult result, @RequestParam("id") String productId, HttpServletRequest request) {
		
		int id = Integer.parseInt(productId);
		Product p = productService.getProductById(id);
		p.setStatus(2);
	
		productService.updateProduct(p);	
                return "redirect:/admin/product";
	}
	
	
	@RequestMapping("/admin/product/approve")
	public String approveProduct(Model model, @ModelAttribute("product") Product product, BindingResult result, @RequestParam("id") String productId, HttpServletRequest request) {
		
		int id = Integer.parseInt(productId);
		Product p = productService.getProductById(id);
		p.setStatus(1);
	
		productService.updateProduct(p);
		return "redirect:/admin/product";
	}
        
        
	//===============================================admin manage category
        @Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	

	@RequestMapping("/admin/category")
	public String showProductCategoryList(Model model, HttpServletRequest request,  HttpSession session){
                
                if(isAdmin(session)) {
                    model.addAttribute("categories", categoryService.getAllCategories());
                    return "/admin_management/category/category";
                }
                
                return "redirect:/"; 	
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
	}

}
