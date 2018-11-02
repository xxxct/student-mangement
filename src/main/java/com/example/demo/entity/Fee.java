package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer money;
    private String description;
    private String institute;

    @ManyToMany(mappedBy = "fees")
    private Collection<Student> students=new ArrayList<Student>();

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public Fee() {
    }
}
