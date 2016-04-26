package com.eshopping.controller;

/**
 *
 * @author Yoha & Amr & Somayeh
 */
import com.eshopping.model.Category;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eshopping.model.Customer;
import com.eshopping.model.Product;
import com.eshopping.model.SystemUser;
import com.eshopping.model.Vendor;
import com.eshopping.service.CategoryService;
import com.eshopping.service.CustomerService;
import com.eshopping.service.MailService;
import com.eshopping.service.ProductService;
import com.eshopping.service.SystemUserService;
import com.eshopping.validator.RegistrationUser;
import com.eshopping.validator.RegistrationValidator;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;

@Controller
@SessionAttributes({"user", "status", "listCategories", "searchProduct",
    "size", "shoppingCart", "cartProducts", "total", "loggedIn"})
public class RegistrationController {

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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SystemUserService systemUserService;
    //private Object categoryService;

    @RequestMapping("/registration")
    public String showRegistrationPage(ModelMap map) {
        SystemUser user = new SystemUser();
        RegistrationUser reg_user = new RegistrationUser();
        map.addAttribute("user", user);
        map.addAttribute("reg_user", reg_user);

        return "/registration/register";
    }

    @RequestMapping(value = "/registration/register", method = RequestMethod.POST)
    public String doRegister(ModelMap map,
            @Valid @ModelAttribute RegistrationUser reg_user,
            BindingResult result, HttpSession session) {

        String rootDirectory = session.getServletContext().getRealPath("/");
        String message = null;
        FileInputStream fisTargetFile = null;
        try {
            if (reg_user.getRole().equals("customer")) {
                fisTargetFile = new FileInputStream(new File(rootDirectory
                        + "\\resources\\message\\greeting"));
            } else {
                fisTargetFile = new FileInputStream(new File(rootDirectory
                        + "\\resources\\message\\greeting1"));
            }

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }

        try {
            message = IOUtils.toString(fisTargetFile, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        RegistrationValidator userValidator = new RegistrationValidator();
        userValidator.validate(reg_user, result);
        if (result.hasErrors()) {
            if (reg_user.getRole().equals("customer")) {
                System.out.println("----customer validator error---");

                SystemUser user = new SystemUser();
                map.addAttribute("user", user);
//             map.addAttribute("reg_user", reg_user);
                map.put(BindingResult.class.getName() + ".reg_user", result);
                return "/registration/register";
            } else if (reg_user.getRole().equals("vendor")){
                SystemUser user = new SystemUser();
                map.addAttribute("user", user);
//             map.addAttribute("reg_user", reg_user);
                map.put(BindingResult.class.getName() + ".reg_user", result);
                System.out.println("---vendor validatior error---");
                return "isUsRegisteredCo";
            }else{
                System.out.println("-----else error---rol is unclear---");
                return "/admin/error";
            }
        } else {
            //check existing users
            if (userService.UserExisting(reg_user.getEmail())) {
                if (reg_user.getRole().equals("customer")) {
                    System.out.println("----customer validator error---");

                    result.rejectValue("email", "existing.registrationUser.email");
                    SystemUser user = new SystemUser();
                    map.addAttribute("user", user);
                    map.put(BindingResult.class.getName() + ".reg_user", result);
                    return "/registration/register";
                } else {
                    result.rejectValue("email", "existing.registrationUser.email");
                    SystemUser user = new SystemUser();
                    map.addAttribute("user", user);
                    map.put(BindingResult.class.getName() + ".reg_user", result);
                    System.out.println("---vendor validatior error existing email---");
                    return "isUsRegisteredCo";
                }
            } else if (reg_user.getRole().equals("customer")) {
                Customer c = new Customer();
                c.setEmail(reg_user.getEmail());
                c.setPassword(reg_user.getPassword());
                c.setRole("customer");
                systemUserService.addUser(c);
                map.addAttribute("user", c);
                session.setAttribute("user", c);
                session.setAttribute("status", true);

                try {
                    mailService.sendMail(reg_user.getEmail(), "Greeting", message);
                } catch (MailException e) {
                    logger.error(e.getMessage());
                }

                return "/admin/vendor/waiting_1";
            } else {
                Vendor c = new Vendor();
                c.setEmail(reg_user.getEmail());
                c.setPassword(reg_user.getPassword());
                c.setRole("vendor");
                c.setVendorName(reg_user.getEmail());

                systemUserService.addUser(c);
                map.addAttribute("user", c);
                session.setAttribute("user", c);
                try {
                    mailService.sendMail(reg_user.getEmail(), "Greeting", message);
                } catch (MailException e) {
                    logger.error(e.getMessage());
                }
                return "redirect:/admin/vendor/product";
            }
        }
    }
}
