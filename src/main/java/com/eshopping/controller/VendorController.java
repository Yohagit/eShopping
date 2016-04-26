package com.eshopping.controller;

/**
 *
 * @author Somayeh
 */
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import com.eshopping.model.Vendor;
import com.eshopping.service.SystemUserService;
import com.eshopping.model.SystemUser;
import com.eshopping.service.VendorService;
import com.eshopping.validator.RegistrationUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import com.eshopping.service.encryptionService;
import com.eshopping.validator.RegistrationUser;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes({"user"})
public class VendorController implements Serializable {
private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	@Autowired
	private SystemUserService systemService;
	
	@Autowired
	private VendorService vendorService;

    @Autowired
    private encryptionService encryptor;

	@RequestMapping("/admin/vendor/waiting")
	public String showWaiting(Model model) {
		return "/admin/vendor/waiting";
	}
	
	@RequestMapping("/admin/vendor/profile")
	public String showProducList(Model model, HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		Vendor v = vendorService.getVendorById(user.getUserId());
		if (v.getStatus()!=1) return "/admin/vendor/waiting";
		System.out.println("profile : " + user.getUserId());
		model.addAttribute("vendor", v);
		return "/admin/vendor/profile";
	}

	@RequestMapping("/admin/vendor/profile/edit")
	public String showProfileEdit(Model model, HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		Vendor v = vendorService.getVendorById(user.getUserId());
		if (v.getStatus()!=1) return "/admin/vendor/waiting";
		System.out.println("edit profile : " + v.getVendorName());
		model.addAttribute("vendor", v);
		return "/admin/vendor/profile_edit";
	}

	@RequestMapping(value = "/admin/vendor/profile/picture")
	public void getPic(Model model, HttpSession session,
			HttpServletResponse response) {

		SystemUser user = (SystemUser) session.getAttribute("user");
		Vendor v = vendorService.getVendorById(user.getUserId());

		try {
			byte[] bytes = v.getImage();
			if (bytes != null && bytes.length > 0) {

				response.setContentType("image/jpg");
				response.getOutputStream().write(bytes);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}

		} catch (IOException e1) {
			System.out.print("eeeee msg" + e1);
		}

	}

	@RequestMapping("/admin/vendor/profile/update")
	public String doUpdate(ModelMap map,
			@Valid @ModelAttribute("vendor") Vendor profile,
			BindingResult result, HttpSession session) {
		Vendor user = (Vendor) session.getAttribute("user");
		MultipartFile productImage = profile.getProductImage();

		try {
			profile.setImage(productImage.getBytes());
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		if (result.hasErrors()) {
			map.put(BindingResult.class.getName() + ".vendor", result);
			return "/admin/vendor/profile_edit";

		} else {
			Vendor v = vendorService.getVendorById(user.getUserId());
			v.setEmail(profile.getEmail());
			//v.setPassword(profile.getPassword());
			v.setVendorName(profile.getVendorName());
			v.setAddress(profile.getAddress());
			byte[] ib = profile.getImage();
			if (ib.length>0) v.setImage(profile.getImage());

			vendorService.updateVendor(v);
			session.setAttribute("user", v);
			return "/admin/vendor/profile";
		}
	}
        @RequestMapping(value = "/USRegCO", method = RequestMethod.POST)
    public String validateCard(@Valid @ModelAttribute RegistrationUser reg_user,
            BindingResult bindresult, HttpServletRequest request, ModelMap map, HttpSession session) {

//        double total = (Double) session.getAttribute("total");
//        int size = (Integer) session.getAttribute("size");
        if (bindresult.hasErrors()) {
            return "USRegCO";
}
        
        String CompanyRegNo = reg_user.getRegNo();
        System.out.println("------CompanyRegNo = " + CompanyRegNo);
        
        // compnay registered number ecryption
        try {
            String encrypted = encryptor.encrypt(CompanyRegNo);
            System.out.println("-----------encrypted = " + encrypted); 
            reg_user.setVendorNo(encrypted);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8084/usRegCo/validate?cmpNo="
                + reg_user.getVendorNo();
        System.out.println("US Registered Company Web Service URL : " + url);

        String result = null;
        try {
            result = restTemplate.postForObject(url, null, String.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
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
           
            //SystemUser user = new SystemUser();
//       reg_user = new RegistrationUser();
        map.addAttribute("user", user);
        map.addAttribute("reg_user", reg_user);
        
            
            
            return "isUsRegisteredCo";
        } else {
            return "notUsRegisteredCo";
        }
    }
}
