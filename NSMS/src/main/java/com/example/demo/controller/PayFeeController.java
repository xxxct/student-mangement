package com.example.demo.controller;

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

import java.util.Collection;
import java.util.Map;

@RestController
public class PayFeeController {

    @Autowired
    private StudentRepository sr;
    @Autowired
    private FeeRepository fr;
    @PostMapping(value = "/nsms/paidfee")
    public Collection<Fee> paidFee(@RequestBody JSONObject json){
        String studentId=json.getString("studentId");
        System.out.println(studentId);
        System.out.println(json);
        Student student=sr.findByStudentId(studentId);

        return student.getFees();
    }
    @PostMapping(value = "/nsms/unpaidfee")
    public Collection<Fee> unpaidFee(@RequestBody JSONObject json){
        String studentId=json.getString("studentId");
        Student student=sr.findByStudentId(studentId);
        Collection<Fee> fees=fr.findByInstitute(student.getInstitute());
//        Collection<Fee> unpaid=new ArrayList<Fee>();
        fees.removeAll(student.getFees());
        return fees;
    }

    @PostMapping(value ="nsms/payfee")
    public void payfee(@RequestBody JSONObject json){
        String studentId=json.getString("studentId");
        Student student=sr.findByStudentId(studentId);
        String feeId=json.getString("feeId");
        Fee fee=fr.findById(feeId);
        student.getFees().add(fee);
        sr.save(student);
    }
}
