package net.hamas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HTMLController {
 
    @Autowired
    private CustomerService service;
    
    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Customer> listCustomers = service.viewCustomers();
        model.addAttribute("listCustomers", listCustomers);
         
        return "index";
    }
    
    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
         
        return "new_customer";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
    	
    	service.addCustomer(customer);   
        return "redirect:/";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCustomer(@ModelAttribute("customer") Customer customer) {
    	
    	service.editCustomer(customer);   
        return "redirect:/";
    }
    
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_customer");
        Customer customer = service.viewCustomer(id);
        mav.addObject("customer", customer);
         
        return mav;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") int id) {
        service.deleteCustomer(id);
        
        return "redirect:/"; 
        
           
    }
    
     

}
