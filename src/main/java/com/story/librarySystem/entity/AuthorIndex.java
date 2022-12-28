package com.story.librarySystem.entity;

import lombok.Data;

@Data
public class AuthorIndex {
    private String authorName;
    private Integer headPointer;
    private Integer length;

    public String toString(){
        return authorName+"\t"+headPointer+"\t"+length;
    }
}
