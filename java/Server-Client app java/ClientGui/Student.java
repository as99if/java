package com.uchihaitachi;

import java.io.Serializable;

/**
 * Created by Asif Ahmed Shuvo on 23-12-16.
 */
public class Student implements Serializable{

    private String name, id, sem, dept;
    private static final int serialUID = 2199;

    public Student() {
    }

    public Student(String name, String id, String sem, String dept) {
        this.name = name;
        this.id = id;
        this.sem = sem;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
