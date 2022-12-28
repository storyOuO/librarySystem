package com.story.librarySystem.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String borrow;
    private Date borrowDate;
    private String role;
    public String toString(){
        return username+"\t"+password+"\t"+borrow+"\t"+borrowDate+"\t"+role;
    }

    public String toString2(){
        return id+"\t"+username+"\t"+borrow+"\t"+borrowDate+"\t"+role;
    }
}
