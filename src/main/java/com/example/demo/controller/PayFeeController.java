package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Fee;
import com.example.demo.entity.Student;
import com.example.demo.repository.FeeRepository;
import com.example.demo.repository.StudentRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PayFeeController {

    @Autowired
    private StudentRepository sr;
    @Autowired
    private FeeRepository fr;
    @PostMapping(value = "/nsms/paidfee")
    public String paidFee(@RequestBody JSONObject json){
        String studentId=json.getString("studentId");
        System.out.println(studentId);
        System.out.println(json);
        Student student=sr.findByStudentId(studentId);
        Collection<Fee> fees1=fr.findByInstitute(student.getInstitute());
        Collection<Fee> fees2=fr.findByInstitute(student.getInstitute());
        fees1.removeAll(student.getFees());
        fees2.removeAll(fees1);
//        String s="";
//        for(Fee fee:student.getFees()){
//            s+=JSON.toJSONString(fee);
//        }
//      Map<String,Fee>map=new HashMap<String,Fee>();
//      int i=0;
//        for(Fee fee:student.getFees()){
//           map.put("fee"+i,fee);
//      }

        return JSON.toJSONString(fees2);

    }
    @PostMapping(value = "/nsms/unpaidfee")
    public String unpaidFee(@RequestBody JSONObject json){
        String studentId=json.getString("studentId");
        Student student=sr.findByStudentId(studentId);
        Collection<Fee> fees=fr.findByInstitute(student.getInstitute());
//        Collection<Fee> unpaid=new ArrayList<Fee>();
        fees.removeAll(student.getFees());
        return JSON.toJSONString(fees);
    }

    @PostMapping(value ="nsms/payfee")
    public String payFee(@RequestBody JSONObject json){
        String studentId=json.getString("studentId");
        Student student=sr.findByStudentId(studentId);
        int feeId=Integer.parseInt(json.getString("feeId"));
        System.out.println(studentId);
        System.out.println(feeId);
        Fee fee=fr.findById(feeId);
        student.getFees().add(fee);
        sr.saveAndFlush(student);
        if(student.getFees().size()==fr.findByInstitute(student.getInstitute()).size()){
            student.setArrived(true);
            sr.saveAndFlush(student);
        }
        return "1";
    }
}
