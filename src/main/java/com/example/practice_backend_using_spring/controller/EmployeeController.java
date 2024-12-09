package com.example.practice_backend_using_spring.controller;

import com.example.practice_backend_using_spring.model.Employee;
import com.example.practice_backend_using_spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/user")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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

    @GetMapping("/")
    public String showpage(){
        return "Home";
    }

//    @PostMapping("/signup")
//    public String registeruser(@ModelAttribute Employee employee,Model model){
//       employeeService.saveuser(employee);
//       model.addAttribute("employee",new Employee());
//       model.addAttribute("success","success");
//       return "signup";
//    }

//    @PostMapping("/signup")
//    public String adduser(@ModelAttribute Employee employee,Model model){
//        if(employeeService.add(employee)){
//            model.addAttribute("employee",employee);
//            return "success";
//        }
//        return "redirect:/signup?error=true";
//    }

    @PostMapping("/signup")
    public String adduser(@ModelAttribute Employee employee,Model model){

        if(employeeService.add(employee)){
//            model.addAttribute("employee",employee);
            return "login";
        }
        return "redirect:/signup?error=true";
    }

    @PostMapping("/login")
    public String loginuser(@ModelAttribute("loginuservalue") Employee employee,Model model){
        Employee loggedinuser = employeeService.check(employee.getEmail(),employee.getPassword());
        if(loggedinuser != null){
            model.addAttribute("loginuservalue",loggedinuser);
            return "success";
        }
        return "login";
    }
    @GetMapping("/success")
    public String homepage(){
        return "success";
    }
}
