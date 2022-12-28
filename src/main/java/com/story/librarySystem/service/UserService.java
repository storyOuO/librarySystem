package com.story.librarySystem.service;

import com.story.librarySystem.entity.User;

import java.sql.Date;
import java.util.List;

public interface UserService {
    public User getUser(String username);
    public void addUser(String username, String password);
    public void setBorrow(String bookNum,String username);
    public List<String> getUserName();
    public boolean borrowBook(String username);
    public List<User> getAll() throws Exception;
    public void deleteUserById(Integer id);
    public boolean returnBook(String username, String bookNum);
}
