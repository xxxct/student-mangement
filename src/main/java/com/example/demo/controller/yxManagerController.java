package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import net.sf.json.JSONObject;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class yxManagerController {

    @Autowired
    private StudentRepository sr;
    @PostMapping(value="/nsms/findStudent")
    public String findStudent(@RequestBody JSONObject json){
        String institute=json.getString("institute");
        List<Student> students=sr.findByInstitute(institute);
        return JSON.toJSONString(students);
    }
    @PostMapping(value="/nsms/findPaidStudent")
    public String findPaidStudent(@RequestBody JSONObject json){
        String institute=json.getString("institute");
        List<Student> students;
        students = sr.findByInstituteAndArrived(institute,true);
        return JSON.toJSONString(students);
    }
    @PostMapping(value="/nsms/findUnpaidStudent")
    public String findUnpaidStudent(@RequestBody JSONObject json){
        String institute=json.getString("institute");
        List<Student> students;
        students = sr.findByInstituteAndArrived(institute,false);
        return JSON.toJSONString(students);
    }
}
