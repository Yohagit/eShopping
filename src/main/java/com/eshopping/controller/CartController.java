package com.eshopping.controller;

/**
 *
 * @author Yoha
 */
import com.eshopping.model.Cart;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.eshopping.model.CreditCard;
import com.eshopping.model.Customer;
import com.eshopping.model.Guest;
import com.eshopping.model.Order;
import com.eshopping.model.OrderItem;
import com.eshopping.model.Product;
import com.eshopping.model.SystemUser;
import com.eshopping.service.CustomerService;
import com.eshopping.service.Guestservice;
import com.eshopping.service.MailService;
import com.eshopping.service.OrderItemservice;
import com.eshopping.service.OrderService;
import com.eshopping.service.ProductService;
import com.eshopping.service.SystemUserService;
import com.eshopping.service.encryptionService;
import java.util.ArrayList;
import org.springframework.mail.MailException;

@Controller
@SessionAttributes({"productList", "searchProduct", "shoppingCart", "total",
    "listCategories", "size", "cartProducts", "user", "status", "result"})
public class CartController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private HomeController homeController;

    @Autowired
    private SystemUserService systemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Guestservice guestservice;

    @Autowired
    private MailService mailService;

    @Autowired
    ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemservice orderItemService;

    @Autowired
    private encryptionService encryptor;

    double total;
    int size;

    @RequestMapping(value = "/product/cart")
    public String doShowCart(ModelMap map) {
        return "/product/cart";
    }

    @RequestMapping(value = "/product/addtocart")
    public String addItemToCart(@ModelAttribute("id") int id,
            BindingResult result, ModelMap map, HttpSession session) {
        
        // by Tunlaya 
        if(session.getAttribute("total") != null) 
            total = (Double)session.getAttribute("total");
        if(session.getAttribute("size") != null) 
            size = (Integer)session.getAttribute("size");
        
        
        Product product = productService.getProductById(id);
        map.addAttribute("product", product);
        System.out.println(product.getName());
        List<Product> searchProducts;
        Product searchProduct = new Product();

        map.addAttribute("searchProduct", searchProduct);

        List<Product> cartProducts = homeController.shoppingCart.getProducts();

        int cartQuantity = 0;
        for (Product p : cartProducts) {
            if (p.getId() == product.getId()) {
                //by Tunlaya
                //p.setQuantity(p.getQuantity() - 1); 
                String notice = "";
                if(p.getQuantity() < p.getCartQuantity()+1) {
                    notice = "Sorry!";
                }
                else {
                    p.setCartQuantity(p.getCartQuantity() + 1);
                    productService.updateProduct(p);

                    //by Tunlaya correct price
                    total += p.getPrice() * p.getQuantity();
                    //total = total + p.getPrice();
                     
                    size++;
                }
                
                map.addAttribute("notice", notice);
                map.addAttribute("cartProducts", cartProducts);
                map.addAttribute("total", total);
                map.addAttribute("size", size);
                
                //by Tunlaya
                session.setAttribute("total", total);
                session.setAttribute("size", size);
                
                return "/product/cart";
            }

        }
        //by Tunlaya        
        //product.setQuantity(product.getQuantity() - 1);
        String notice = "";
        if(product.getQuantity() < product.getCartQuantity()+1) {
            notice = "Sorry!";
        }
        else {
            product.setCartQuantity(product.getCartQuantity() + 1);
            productService.updateProduct(product);
            cartProducts.add(product);
            
            //by Tunlaya
            total += product.getPrice();//* product.getQuantity();
            //total = total + product.getPrice();
            
            size++;
        }
                
        map.addAttribute("notice", notice);
        map.addAttribute("cartProducts", cartProducts);
        map.addAttribute("size", size);
        map.addAttribute("total", total);
        
        //by Tunlaya
        session.setAttribute("total", total);
        session.setAttribute("size", size);

        return "/product/cart";
    }

    @RequestMapping(value = "/continue", method = RequestMethod.GET)
    public String continueShopping() {
        return "redirect:/";
    }

    @RequestMapping(value = "/product/removeFromCart")
    public String removeItemFromCart(@ModelAttribute("id") int id,
            BindingResult result, ModelMap map, HttpSession session) {
        
        List<Product> cartProducts = homeController.shoppingCart.getProducts();

        Product product = new Product();
        for (Product p : cartProducts) {
            if (p.getId() == id) {
                product = p;
                break;
            }
        }
        
        total = (Double) session.getAttribute("total");
        size = (Integer) session.getAttribute("size");
        
            
        size -= product.getCartQuantity();
        total -= product.getPrice() * product.getCartQuantity();
        
        //by Tunlaya
        if(size <= 0 ) 
            this.clearCartSession(session);
        
        
        System.out.println("thissize" + this.size);
        System.out.println("thistotal" + this.total);
        
        System.out.println("size" + size);
        System.out.println("total" + total);
        
        cartProducts.remove(product);
        map.addAttribute("cartProducts", cartProducts);
        map.addAttribute("size", size);
        map.addAttribute("total", total);

        //by Tunlaya
        session.setAttribute("total", total);
        session.setAttribute("size", size);

              
        return "/product/cart";
    }

    @RequestMapping(value = "/product/minusOne")
    public String minusOneItem(@ModelAttribute("id") int id,
            BindingResult result, ModelMap map, HttpSession session) {
             
        total = (Double) session.getAttribute("total");
        size = (Integer) session.getAttribute("size");
        List<Product> cartProducts = homeController.shoppingCart.getProducts();

        Product product = new Product();
        for (Product p : cartProducts) {
            if (p.getId() == id) {
                if (p.getCartQuantity() > 0) {
                    //by Tunlaya
                    //p.setQuantity(p.getQuantity() + 1);
     
                    //by Tunlaya, remove instead of decrease
                    if(p.getCartQuantity() - 1 == 0) {
                        return this.removeItemFromCart(p.getId(), result, map, session);
                    }
                    
                    p.setCartQuantity(p.getCartQuantity() - 1);
                    productService.updateProduct(p);
                    total = total - p.getPrice();
                    map.addAttribute("cartProducts", cartProducts);
                    map.addAttribute("total", total);
                    size--;
                    map.addAttribute("size", size);
                    return "/product/cart";
                } else {
                    cartProducts.remove(p);
                }
                // total -= p.getPrice();
                map.addAttribute("cartProducts", cartProducts);
                // size=homeController.size-1;

                map.addAttribute("size", size);
                map.addAttribute("total", total);
                
                //by Tunlaya
                session.setAttribute("total", total);
                session.setAttribute("size", size);
            

                return "/product/cart";
            }
        }
        
        return "/product/cart";

        
    }

    @RequestMapping(value = "/product/plusOne")
    public String plusOneItem(@ModelAttribute("id") int id,
            BindingResult result, ModelMap map, HttpSession session) {

        total = (Double) session.getAttribute("total");
        size = (Integer) session.getAttribute("size");

        List<Product> cartProducts = homeController.shoppingCart.getProducts();
        Product product = new Product();
        for (Product p : cartProducts) {
            if (p.getId() == id) {
                //by Tunlaya
                //p.setQuantity(p.getQuantity() - 1);
                String notice = "";
                if(p.getQuantity() < p.getCartQuantity()+1) {
                    notice = "Sorry!";
                }
                else {
                    p.setCartQuantity(p.getCartQuantity() + 1);
                    productService.updateProduct(p);
                    total = total + p.getPrice();
                    size++;
                }
                map.addAttribute("notice", notice);
                map.addAttribute("cartProducts", cartProducts);
                map.addAttribute("total", total);
                map.addAttribute("size", size);
                
                //by Tunlaya, clear session cart
                session.setAttribute("total", total);
                session.setAttribute("size", size);
        
                return "/product/cart";
            }
        }
        
        return "/product/cart";
    }

    @RequestMapping("product_summary")
    public String showSummary() {
        return "product_summary";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String makePayment(Model model, HttpServletRequest request) {
        model.addAttribute("creditCard", new CreditCard());
        return "payment";
    }
    
    private void createOrder(CreditCard creditCard, Customer customer, Guest guest, 
            double getGrandTotal, double profit, double myprofit, boolean isGuest) {
        Order order = new Order();
        order.setCustomer_address(creditCard.getAddress());
        order.setTotal(getGrandTotal);
        order.setProfit_total(profit);
        order.setProfit_for_mycompany(myprofit);
        
        if(isGuest) {
            order.setGuest(guest);
        } else {
            order.setUser(customer);
        }
        order.setDate(new Date());
        orderService.addOrder(order);
        // creating order items
        List<Product> cartProducts = homeController.shoppingCart.getProducts();
        for (Product prd : cartProducts) {
            OrderItem oitm = new OrderItem();
            oitm.setProduct(prd);
            oitm.setQuantity(prd.getCartQuantity());
            oitm.setPrice(prd.getPrice());
            oitm.setOrder(order);
            orderItemService.addOrderItem(oitm);
        }
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String validateCard(@ModelAttribute("creditCard") @Valid CreditCard creditCard,
            BindingResult bindresult, HttpServletRequest request, ModelMap map, HttpSession session) {

        double total = (Double) session.getAttribute("total");
        int size = (Integer) session.getAttribute("size");

        if (bindresult.hasErrors()) {
            return "payment";
        }

        double getGrandTotal = (Double) request.getSession().getAttribute("total");

        // calculating profits
        double profit = getGrandTotal * 0.2;
        double myprofit = profit * 0.1;

        String plainCardNo = creditCard.getFirst() + creditCard.getSecond()
                + creditCard.getThird() + creditCard.getFourth();

        // credit card number ecryption
        try {
            String encrypted = encryptor.encrypt(plainCardNo);
            creditCard.setCardNo(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

        creditCard.setExpDate();

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8084/payment/validate?ccn="
                + creditCard.getCardNo() + "&amount=" + (int) getGrandTotal;

        System.out.println("Payment web service URL : " + url);

        String result = null;
        try {
            result = restTemplate.postForObject(url, null, String.class);
        } catch (Exception e) {
            return "serviceError";
        }

        if (result.equals("y")) {
            SystemUser user = (SystemUser) request.getSession().getAttribute("user");
            boolean isGuest = true;
            if (user != null) {
                if (user.getUserId() != 0) {
                    if (user.getRole().equalsIgnoreCase("customer")) {
                        isGuest = false;
                    }
                }
            }

            if (!isGuest) {
                Customer customer = customerService.getCustomerById(user.getUserId());
                customer.setAddress(creditCard.getAddress());
                customer.setCreditCard(creditCard);
                customerService.updateCustomer(customer);

                String rootDirectory = request.getSession().getServletContext().getRealPath("/");
                String message = null;

                // read message file
                FileInputStream fisTargetFile = null;
                try {
                    fisTargetFile = new FileInputStream(new File(rootDirectory
                            + "\\resources\\message\\confirmation"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    message = IOUtils.toString(fisTargetFile, "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mailService.sendMail(customer.getEmail(), "Confirmation", message);
                } catch(MailException e) {
                    logger.error(e.getMessage());
                }
                
//                Order order = new Order();
//                order.setCustomer_address(creditCard.getAddress());
//                order.setTotal(getGrandTotal);
//                order.setProfit_total(profit);
//                order.setProfit_for_mycompany(myprofit);
//                order.setUser(customer);
//                order.setDate(new Date());
//                orderService.addOrder(order);

//                List<Product> cartProducts = homeController.shoppingCart.getProducts();
//                for (Product prd : cartProducts) {
//                    OrderItem oitm = new OrderItem();
//                    oitm.setProduct(prd);
//                    oitm.setQuantity(prd.getCartQuantity());
//                    oitm.setPrice(prd.getPrice());
//                    oitm.setOrder(order);
//                    orderItemService.addOrderItem(oitm);
//                }
//                cartProducts.clear();   
//                map.addAttribute("cartProducts", cartProducts);
                createOrder(creditCard, customer, null, getGrandTotal, profit, myprofit, isGuest);
            } 
            
            else {
                Guest guest = new Guest();
                guest.setAddress(creditCard.getAddress());
                guest.setCreditCard(creditCard);
                guestservice.addGuest(guest);

//                Order order = new Order();
//                order.setCustomer_address(creditCard.getAddress());
//                order.setTotal(getGrandTotal);
//                order.setProfit_total(profit);
//                order.setProfit_for_mycompany(myprofit);
//                order.setDate(new Date());
//                order.setGuest(guest);
//                orderService.addOrder(order);

//                List<Product> cartProducts = homeController.shoppingCart.getProducts();
//                for (Product prd : cartProducts) {
//                    OrderItem oitm = new OrderItem();
//                    oitm.setProduct(prd);
//                    oitm.setQuantity(prd.getCartQuantity());
//                    oitm.setPrice(prd.getPrice());
//                    oitm.setOrder(order);
//                    orderItemService.addOrderItem(oitm);
//                }
//                cartProducts.clear();
//                map.addAttribute("cartProducts", cartProducts);
            
                createOrder(creditCard, null, guest, getGrandTotal, profit, myprofit, isGuest);
            }
            
            //by Tunlaya, update product quantity
            List<Product> cartProducts = homeController.shoppingCart.getProducts();
            for (Product prd : cartProducts) {
                    prd.setQuantity(prd.getQuantity() - prd.getCartQuantity());
                    prd.setCartQuantity(0);
                    productService.updateProduct(prd);
            } 
            
            //by Tunlaya, clear session cart
            clearCartSession(session);

            map.addAttribute("cartProducts", new ArrayList<Product>());
            this.total = 0;
            map.addAttribute("total", this.total);
            this.size = 0;
            map.addAttribute("size", this.size);
         
            String strAddress = creditCard.getAddress().getZip();

            String url2 = "http://localhost:8084/finance/archive?ccn="
                    + creditCard.getCardNo() + "&address=" + strAddress
                    + "&profit=" + profit + "&total=" + getGrandTotal
                    + "&myprofit=" + myprofit;
            
            System.out.println("Finance gateway URL : " + url2);
            try {
                restTemplate.postForObject(url2, null, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        
            return "paymentSucces";
        } else {
            return "paymentFailure";
        }
    }
    
    //add fn by Tunlaya
    public void clearCartSession(HttpSession session) {
            homeController.shoppingCart = new Cart();
            homeController.total = 0;
            homeController.size = 0;
            homeController.shoppingCart.setGrandTotal(0);
            homeController.shoppingCart.setProducts(new ArrayList<Product>());
            total = 0;
            size = 0;
            session.setAttribute("total", total);
            session.setAttribute("size", size);
    }
}
