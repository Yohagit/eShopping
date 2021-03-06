package com.eshopping.controller;

/**
 *
 * @author Tunlaya
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eshopping.model.Product;
import com.eshopping.model.SystemUser;
import com.eshopping.service.CategoryService;
import com.eshopping.service.ProductService;
import com.eshopping.service.SystemUserService;

@Controller
@SessionAttributes({ "user", "status", "listCategories", "searchProduct" ,"size","shoppingCart","cartProducts", "total","userId"})

public class UserController {

	@Autowired
	SystemUserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	Product searchProduct = new Product();
	int userId;

	// public ModelMap model= new ModelMap();

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(@ModelAttribute("user") SystemUser userLogin,
			BindingResult result, ModelMap map, HttpSession session) {

		SystemUser user = userService.checkLogin(userLogin.getEmail(),
				userLogin.getPassword());
		boolean status;
		boolean error;
		if (user != null) {
			status = true;
			// if (authUser.getRole().equals("customer"))
			// authUser = (Customer) authUser;
			// else
			// authUser = (Vendor) authUser;

			session.setAttribute("user", user);
			map.addAttribute("user", user);
			map.addAttribute("status", true);

			userId = user.getUserId();
			map.addAttribute("userId",userId);
			return "redirect:/allProducts";
		} else {
			status = false;
			map.addAttribute("status", false);
			error = true;
			map.addAttribute("error", true);
			return "login";
		}

	}


	@RequestMapping(value = "/home2")
	public String home() {
		
		return "home2";

	}

	@RequestMapping(value = "/login")
	public String login(ModelMap map) {
		SystemUser user = new SystemUser();
		map.addAttribute("user", user);
		// model.addAttribute("searchProduct", new Product());
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, ModelMap map, HttpSession session) {
		boolean status;
		SystemUser user = new SystemUser();
		// newUser.setRole("default");
		map.put("user", user);
		map.remove("status");
		map.put("status", false);
		List<Product> cartProducts = new ArrayList<Product>();
		cartProducts.clear();
		map.addAttribute("cartProducts", cartProducts);
		
		session.setAttribute("user", null);
		session.setAttribute("status", false);
		session.setAttribute("cartProducts", cartProducts);
		

		status = false;
		return "redirect:/";

	}

	@RequestMapping(value = "/productPerCategory")
	public String listProducts(@ModelAttribute("catId") int categoryId,
			BindingResult result, ModelMap map) {
		ArrayList<Product> listOfProducts = productService
				.listProductsByCriteria(categoryId);
		map.addAttribute("productsByCat", listOfProducts);
		return "listProductsByCategory";

	}

	@RequestMapping(value = "/productByName", method = RequestMethod.POST)
	public String searchProductsByName(
			@ModelAttribute("searchProduct") Product p, BindingResult result,
			ModelMap map) {
		ArrayList<Product> products = productService.getProductsByName(p
				.getName());
		map.addAttribute("products", products);
		if (products.size() == 0)
			return "noItem";
		else
			return "searchProductsByName";
	}

	@RequestMapping(value = "/allProducts", method = RequestMethod.GET)
	public String getAllProducts(Model model) {
		ArrayList<Product> allProducts = (ArrayList<Product>) productService
				.getAvailableProducts();
		model.addAttribute("allProducts", allProducts);
		return "allProducts";
	}
}
