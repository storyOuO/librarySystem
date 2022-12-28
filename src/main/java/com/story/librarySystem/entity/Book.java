package com.story.librarySystem.entity;

import lombok.Data;

@Data
public class Book {
    private Integer num;
    private Long bookNum;
    private String bookName;
    private Integer pointer1;
    private String author;
    private Integer pointer2;
    private String publish;
    private Integer pointer3;
    private Integer assort;
    private Integer storage;
    private Integer borrow;

    public String toString(){
        return num+"\t"+bookNum+"\t"+bookName+"\t"+pointer1+"\t"+author+"\t"+pointer2+"\t"+publish+"\t"+pointer3+"\t"+assort+"\t"+storage+"\t"+borrow;
    }
}
