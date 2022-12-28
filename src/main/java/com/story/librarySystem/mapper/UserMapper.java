package com.story.librarySystem.mapper;

import com.story.librarySystem.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    public User getUser(String username);

    public void addUser(String username, String password);

    public void setBorrow(String bookNum, Date date, String username);

    public List<String> getAllUserName();

    public String getBorrowByUserName(String username);

    public List<User> selectAll();

    public void deleteUserById(Integer id);

    public void returnBook(String bookNum, String username);

    public String getBookNumByUserName(String username);
}
