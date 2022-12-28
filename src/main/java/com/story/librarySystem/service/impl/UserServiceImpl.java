package com.story.librarySystem.service.impl;

import com.story.librarySystem.entity.Book;
import com.story.librarySystem.entity.User;
import com.story.librarySystem.mapper.UserMapper;
import com.story.librarySystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public void addUser(String username, String password) {
        userMapper.addUser(username,password);
    }

    @Override
    public void setBorrow(String bookNum, String username) {
        userMapper.setBorrow(bookNum,new Date(System.currentTimeMillis()),username);
    }

    @Override
    public List<String> getUserName() {
        return userMapper.getAllUserName();
    }

    @Override
    public boolean borrowBook(String username) {
        return userMapper.getBorrowByUserName(username) == null;
    }

    @Override
    public boolean returnBook(String username,String bookNum) {
        if (userMapper.getBookNumByUserName(username).equals(bookNum)) {
            userMapper.returnBook(bookNum, username);
            return true;
        }
        return false;
    }


    @Override
    public List<User> getAll() throws Exception{
        String userIndex = "id\t用户名\t借书\t借书日期\t权限\n";
        List<User> users = userMapper.selectAll();
        for (User user : users){
            userIndex += user.toString2() + "\n";
        }
        String dir = "D:\\123\\users.txt";
        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists())
            file.createNewFile();
        //创建FileWriter对象
        FileWriter writer = new FileWriter(file);
        //向文件中写入内容
        writer.write(userIndex);
        writer.flush();
        writer.close();
        return userMapper.selectAll();
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

}
