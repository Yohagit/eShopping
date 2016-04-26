package com.eshopping.controller;

/**
 *
 * @author Yoha & Amr
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eshopping.model.Cart;

import com.eshopping.model.Product;
import com.eshopping.model.SystemUser;

import com.eshopping.service.CustomerService;
import com.eshopping.service.MailService;
import com.eshopping.service.ProductService;
import com.eshopping.service.SystemUserService;
import com.eshopping.validator.RegistrationUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes({"user", "status", "listCategories", "searchProduct",
    "size", "shoppingCart", "cartProducts", "total", "loggedIn"})
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private HomeController homeController;

    @Autowired
    SystemUserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    private MailService mailService;
    
    //by Tunlaya
    @Autowired
    private CartController cartController;

    @Autowired
    private SystemUserService systemUserService;

    @RequestMapping("/signin")
    public String showRegistrationPage(ModelMap map) {
        SystemUser user = new SystemUser();
        RegistrationUser reg_user = new RegistrationUser();
        map.addAttribute("user", user);
        map.addAttribute("reg_user", reg_user);

        return "/registration/signin";
    }

    @RequestMapping(value = "/registration/signin", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("user") SystemUser userLogin,
            BindingResult result, ModelMap map, HttpSession session) {

        SystemUser user = userService.checkLogin(userLogin.getEmail(),
                userLogin.getPassword());
        boolean status;
        boolean login_error;
        if (user != null) {
            status = true;
            // if (authUser.getRole().equals("customer"))
            // authUser = (Customer) authUser;
            // else
            // authUser = (Vendor) authUser;

            session.setAttribute("user", user);

            map.addAttribute("user", user);
            map.addAttribute("status", true);

            if (user.getRole().equals("customer")) {
                 
                //by Tunlaya, clear session cart
                cartController.clearCartSession(session);
             
                session.setAttribute("status", true);
                // Customer c =
                // customerService.getCustomerById(user.getUserId());
                // map.addAttribute("loggedIn", c);
                return "redirect:/product/search_all";
            } else if (user.getRole().equals("vendor")) {
                return "redirect:/admin/vendor/product";
            } else {
                return "redirect:/admin/system";
            }
        } else {
            status = false;
            map.addAttribute("status", false);
            login_error = true;
            map.addAttribute("login_error", true);
            map.addAttribute("reg_user", new RegistrationUser());
            return "registration/signin";
        }

    }

    @RequestMapping(value = "/registration/logout", method = RequestMethod.GET)
    public String logout(Locale locale, ModelMap map, HttpSession session) {
        boolean status;
        SystemUser user = new SystemUser();
        // newUser.setRole("default");
        map.put("user", user);
        map.remove("status");
        map.put("status", false);

        Cart shoppingCart = new Cart();
        List<Product> cartProducts = new ArrayList<Product>();
        homeController.shoppingCart = new Cart();
        map.addAttribute("cartProducts", cartProducts);
        session.setAttribute("user", user);
        session.setAttribute("status", false);
        session.removeAttribute("shoppingCart");
        session.setAttribute("shoppingCart", shoppingCart);
        session.setAttribute("total", 0.0);
        session.setAttribute("size", 0);
        map.addAttribute("user", user);
        map.addAttribute("status", false);
        map.addAttribute("shoppingCart", shoppingCart);
        map.addAttribute("total", 0.0);
        map.addAttribute("size", 0);

        status = false;
        
        //by Tunlaya, clear session cart
        cartController.clearCartSession(session);
        
        
        return "redirect:/";

      
    }

}
