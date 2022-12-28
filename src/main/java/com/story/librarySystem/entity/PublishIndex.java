package com.story.librarySystem.entity;

import lombok.Data;

@Data
public class PublishIndex {
    private String publishName;
    private Integer headPointer;
    private Integer length;

    public String toString(){
        return publishName+"\t"+headPointer+"\t"+length;
    }
}
