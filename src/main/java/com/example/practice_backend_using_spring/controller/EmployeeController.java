package com.example.practice_backend_using_spring.controller;

import com.example.practice_backend_using_spring.model.Employee;
import com.example.practice_backend_using_spring.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/user")
public class EmployeeController {

//    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/data")
    public List<Employee> getemployee(){
        return employeeService.getuser();
    }
    @GetMapping("/signup")
    public String showsignuppage(Model model){
//        model.addAttribute("employee",new Employee());
        return "signup";
    }

    @GetMapping("/login")
    public String showsignuppage1(Model model){
//        model.addAttribute("employee",new Employee());
        return "login";
    }

//    @GetMapping("/profile")
//        public String view(@ModelAttribute  Model model, HttpSession session) {
//            Long userid = (Long) session.getAttribute("UserId");
//            if (userid != null) {
//                Employee employee = employeeService.finduser(userid);
//                model.addAttribute("employee", employee);
//                return "profile";
//            }
//            return "login";
//        }

    @GetMapping("/profile")
    public String viewProfile(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("UserId"); // Get logged-in user's ID from session

        if (userId != null) {
            Employee employee = employeeService.findById(userId);
            if (employee != null) {
                model.addAttribute("employee", employee);
                return "profile";
            }
        }
        return "redirect:/login"; // Redirect to login if not logged in
    }

    @GetMapping("/")
    public String homepage(Model model,HttpSession httpSession){
        Long userid = (Long) httpSession.getAttribute("UserId");
        if(userid !=null){
            Employee employee = employeeService.checkuser(userid);
            model.addAttribute("employee",employee);
            return "home";
        }
        return "profile";
    }

    @PostMapping("/signup")
    public String adduser(@ModelAttribute Employee employee,Model model){

        if(employeeService.add(employee)){
//            model.addAttribute("employee",employee);
            return "login";
        }
        return "redirect:/signup?error=true";
    }

    @PostMapping("/login")
    public String loginuser(@ModelAttribute("employee") Employee employee, Model model, HttpSession httpSession){
        Employee loggedinuser = employeeService.check(employee.getEmail(),employee.getPassword());
        if(loggedinuser != null){
            httpSession.setAttribute("UserId",loggedinuser.getId());
            model.addAttribute("employee",loggedinuser);
            model.addAttribute("error","");
            return "home";
        }else {
            model.addAttribute("error","Invalid UserName And Password");
            return "login";
        }
    }
    @GetMapping("/success")
    public String homepage(){
        return "success";
    }

    @GetMapping("/edit")
    public String edituser(){
        return "Edit";
    }

    @PostMapping("/edit")
    public String changeuser(@RequestParam String name,@RequestParam String email,@RequestParam String password,HttpSession httpSession,Model model){
        Long userid = (Long) httpSession.getAttribute("UserId");
        if(userid != null){
            employeeService.setuser(name,email,password,userid);
        }
        return "login";
    }

    @PostMapping("/delete")
    public String deleteuser(HttpSession httpSession){
        Long userid = (Long) httpSession.getAttribute("UserId");
        if(userid != null){
          employeeService.deleteemployee(userid);
          httpSession.invalidate();
        }
        return "redirect:/login";
    }

}
