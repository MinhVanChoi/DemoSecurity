package vn.iotstar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.iotstar.entity.Product;
import vn.iotstar.services.ProductServices;


@Controller
public class LoginController {

    @Autowired
    private ProductServices service;

    // Success handler for login
    @PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        System.out.println("Logging user login success...");
        // Redirect to the home page or any other page after successful login
        return "index";
    }

    // Failure handler for login
    @PostMapping("/login_failure_handler")
    public String loginFailureHandler() {
        System.out.println("Login failure handler...");
        // Redirect back to the login page if login fails
        return "login";  // This should map to your login page view
    }

    // Home page view
    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index";  // This should map to your home page view
    }
    
    // Show the form to create a new product
    @RequestMapping("/new")
    public String showNewProductForm(Model model, @ModelAttribute("product") Product product) {
        model.addAttribute("product", product);
        return "new_product";  // Return the view name for creating a new product
    }
    

    // Handle saving the new product
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);  // Call the service to save the product
        return "redirect:/";  // Redirect to home page after saving
    }

    // Show the form to edit an existing product
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_product");  // View for editing the product
        Product product = service.get(id);  // Get the product by id
        mav.addObject("product", product);  // Add the product to the model
        return mav;
    }

    // Handle updating an existing product
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateProduct(@PathVariable(name = "id") Long id, @ModelAttribute("product") Product product) {
        product.setId(id);  // Ensure the ID is set to the product (for update)
        service.save(product);  // Call service to update the product
        return "redirect:/";  // Redirect to home page after updating
    }

    // Handle deleting a product
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        service.delete(id);  // Call service to delete the product by id
        return "redirect:/";  // Redirect to home page after deleting
    }
    
}
