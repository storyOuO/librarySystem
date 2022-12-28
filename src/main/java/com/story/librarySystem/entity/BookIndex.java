package com.story.librarySystem.entity;

import lombok.Data;

@Data
public class BookIndex {
    private String bookName;
    private Integer headPointer;
    private Integer length;

    public String toString(){
        return bookName+"\t"+headPointer+"\t"+length;
    }
}
