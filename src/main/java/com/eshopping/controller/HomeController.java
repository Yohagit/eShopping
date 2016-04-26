package com.eshopping.controller;

/**
 *
 * @author Yoha
 */
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.eshopping.model.Cart;
import com.eshopping.model.Category;
import com.eshopping.model.Product;
import com.eshopping.model.SystemUser;
import com.eshopping.service.CategoryService;
import com.eshopping.service.ProductService;
import com.eshopping.service.SystemUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "listCategories", "searchProduct", "name", "allProducts",
		"shoppingCart", "size", "cartProducts", "total", "user", "status" })

public class HomeController implements Serializable{

	private CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	private SystemUserService systemUserService;

	public Cart shoppingCart = new Cart();
	int size;
	double total;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, ModelMap map, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
		SystemUser user;
		try {
			user = (SystemUser) session.getAttribute("user");
			System.out.println("User role is : " + user.getRole());
			if (user.getRole().equals("vendor")) {

				return "redirect:/admin/vendor/product";
			} else if (user.getRole().equals("admin")) {
				return "redirect:/admin/system";
			} else {

//				return "redirect:/product/search_all";
				ArrayList<Product> products = productService.allProducts();
				ArrayList<Category> categories = new ArrayList<Category>();
//				Cart shoppingCart = new Cart();
				categories = categoryService.listCategories();

				map.addAttribute("status", true);
				map.addAttribute("products", products);
				map.addAttribute("query", "");
				map.addAttribute("categories", categories);

				return "home2";


			}
		} catch (Exception ex) {

		}

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		map.addAttribute("serverTime", formattedDate);
		
		
		ArrayList<Product> products = productService.allProducts();
		ArrayList<Category> categories = new ArrayList<Category>();
		categories = categoryService.listCategories();
		
		map.addAttribute("products", products);
		map.addAttribute("query", "");
		map.addAttribute("categories", categories);
		
		return "home2";

	}

	@RequestMapping("/back")
	public String backToHome() {

		return "home2";
	}


}
