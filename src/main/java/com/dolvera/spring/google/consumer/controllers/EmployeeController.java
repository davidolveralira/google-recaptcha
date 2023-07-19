package com.dolvera.spring.google.consumer.controllers;

import com.dolvera.spring.google.models.dto.EmployeeDto;
import com.dolvera.spring.google.models.entity.EmployeeEntity;
import com.dolvera.spring.google.services.EmployeeService;
import com.dolvera.spring.google.services.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@RequestMapping("api/v1/google/captcha")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RecaptchaService recaptchaService;


    @GetMapping(path = {"/", "/all"})
    public String showAll(Model model){

        List<EmployeeEntity> employeeEntityList = employeeService.findAll();
        model.addAttribute("employees", employeeEntityList);

        return "index";
    }

    @GetMapping("/create/form")
    public String createForm(Model model){
        model.addAttribute("employee", new EmployeeEntity());
        return "form";
    }

    @PostMapping("/create/process")
    public String createProcess(@ModelAttribute("employee") EmployeeDto employeeDTO, @RequestParam(name="g-recaptcha-response") String captcha, Model model){

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setLastName(employeeDTO.getLastName());
        employeeEntity.setDateOfBirth(employeeDTO.getDateOfBirth());

        boolean isCaptchaValid = recaptchaService.validateCaptcha(captcha);

        if(isCaptchaValid){
            employeeService.createEmployee(employeeEntity);
            return "redirect:/all";
        } else {
            model.addAttribute("message", "Recaptcha Invalido");
            return "Error";
        }
    }

}
