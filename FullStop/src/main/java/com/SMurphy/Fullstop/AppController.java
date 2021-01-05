package com.SMurphy.Fullstop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    @Autowired
	private UserRepository repo;
    
    @Autowired
    private PeriodRepository repo2;
    
    @Autowired
    private PeriodService service;
	
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/index")
    public String viewIndexPage() {
        return "index";
    }
    
//    @GetMapping("/register")
//    public String showSignUpForm(Model model) {
//    	model.addAttribute("user", new User());
//    	
//    	return "signup_form";
//    }
    
//    @PostMapping("/process_register")
//    public String processRegistration(User user) {
//    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//    	String encodedPassword = encoder.encode(user.getPassword());
//    	user.setPassword(encodedPassword);
//    	user.setEnabled(1);
//    	repo.save(user);
//    	
//    	return"register_success";
//    }
    
    @GetMapping("/add_period")
    public String showAddNewDataForm(Model model) {
    	model.addAttribute("period", new Period());
    	
    	return "add_period";
    }
    
    @PostMapping("/process_period")
    public String processPeriod(Period period) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	period.setUserName(username);;	
    	
    	repo2.save(period);
    	return"period_added";
    }
    
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePeriod(@ModelAttribute("period") Period period) {
		service.save(period);
		
		return "period_edited";
	}
    
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPeriodForm(@PathVariable(name="id") Long id) {
    	ModelAndView mav = new ModelAndView("edit_period");
    	Period period = service.get(id);
    	mav.addObject("period", period);
    	return mav;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id")Long id) {
    	service.delete(id);
    	return "period_deleted";
    }
    
    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
    	List<User> listUsers = repo.findAll();
    			model.addAttribute("listUsers", listUsers);
    	return "users";
    }
    @GetMapping("/period_added")
    public String viewPeriodAddedPage() {
        return "period_added";
    }
    
    @GetMapping("/view_period")
    public String viewPeriodList(Model model) {
    	List<Period> listPeriods = repo2.findAll();
    			model.addAttribute("listPeriods", listPeriods);
    	return "view_period";
    }
    @GetMapping("/logout_success")
    public String viewLogoutPage() {
        return "logout_success";
    }
    
    @GetMapping("/error")
    public String viewErrorPage() {
    	return "error";
    }
}
