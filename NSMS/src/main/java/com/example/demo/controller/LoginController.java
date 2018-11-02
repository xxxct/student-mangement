package com.example.demo.controller;


import com.example.demo.entity.Manager;
import com.example.demo.entity.Student;
import com.example.demo.entity.Fee;
import com.example.demo.repository.FeeRepository;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private StudentRepository sr;
    @Autowired
    private ManagerRepository mr;

    @PostMapping(value = "/nsms/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("type") String type,
                        Map<String, Object> map) {
        if (type.equals("2")) {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                map.put("msg", "用户名和密码不能为空");
                return "login";
            } else {
                Student student = sr.findByStudentId(username);
                if (student == null) {
                    map.put("msg", "用户不存在");
                    return "login";
                }
                if  (student.getPassword().equals(password)) {
                    //model.addAttribute("name",student.getName());
                    //List<Fee> feeList = fr.findByInstitute(student.getInstitute());
                    map.put("student",student);
                    map.put("index","1");
                    //map.put("feeList",feeList);
                    //System.out.println(student.getName());
                    return "student";
                    //return "redirect:/student";
                } else {
                    map.put("msg", "用户名或密码不正确");
                    return "login";
                }
            }
        } else {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                map.put("msg", "用户名和密码不能为空");
                return "login";
            } else {
                Manager manager = mr.findById(username);
                if (manager == null) {
                    map.put("msg", "用户不存在");
                    return "login";
                }
                if (manager.getPassword().equals(password)) {
                    map.put("manager",manager);
                    return "redirect:/manager";
                } else {
                    map.put("msg", "用户名或密码不正确");
                    return "login";

                }
            }
        }
    }
}
