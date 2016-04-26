package com.eshopping.controller;

/**
 *
 * @author Jefferson
 */
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.eshopping.model.Customer;
import com.eshopping.model.Order;
import com.eshopping.model.SystemUser;
import com.eshopping.service.SystemUserService;
import com.eshopping.service.OrderService;

@Controller
@SessionAttributes({"user", "status", "listCategories", "searchProduct", "size", "shoppingCart", "cartProducts", "total"})
public class CustomerController {

    private SystemUserService customerService;

    @Autowired
    OrderService orderService;

    @Autowired
    public void setCustomerService(SystemUserService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/admin/customer")
    public String showCustomerPage(ModelMap map) {

        return "/admin/customer/home";
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public String addCustomeraction(
            @ModelAttribute("customer") @Valid Customer customer,
            BindingResult result) {

        if (result.hasErrors()) {
            return "registerCustomer";
        }

        customer.setRole("customer");
        customerService.addUser(customer);

        return "customerRegSuccess";
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.GET)
    public String addCustomerpage(Model model) {

        model.addAttribute("customer", new Customer());

        return "registerCustomer";
    }

    @RequestMapping(value = "/admin/customer/viewHistory", method = RequestMethod.GET)
    public String viewCustomerHistory(Model model, HttpSession session) {

        SystemUser customer = (SystemUser) session.getAttribute("user");
        int id = customer.getUserId();
        ArrayList<Order> orders = orderService.getOrdersByCustomer(id);
        model.addAttribute("orders", orders);
        System.out.println("orders" + orders.size());
        return "/admin/customer/viewOrderHistory";
    }
}
