package com.story.librarySystem.controller;

import com.story.librarySystem.entity.User;
import com.story.librarySystem.mapper.UserMapper;
import com.story.librarySystem.service.BookService;
import com.story.librarySystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/addUser", params = {"username","password"})
    public String addUser(Model model,@RequestParam("username") String username, @RequestParam("password") String password){
        if (userService.getUserName().contains(username)){
            model.addAttribute("msg","用户名已存在");
            return "login";
        }else{
            userService.addUser(username,password);
            model.addAttribute("msg","注册成功!");
            return "login";
        }
    }

    @RequestMapping(value = "/deleteUser{id}")
    public String deleteUser(@RequestParam("id") Integer id){
        userService.deleteUserById(id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/login1", params = {"username","password"})
    public String login(Model model,@RequestParam("username") String username, @RequestParam("password") String password) throws NullPointerException{
//        log.info("username:"+username+"password:"+password);
        if (!userService.getUserName().contains(username) || !userService.getUser(username).getPassword().equals(password)){
//            log.info(userService.getUser(username).toString());
            model.addAttribute("msg","用户不存在或密码错误,请重试!");
            return "login";
        }else{
            if (userService.getUser(username).getRole().equals("admin")) {
                return "redirect:/index";
            }else{
                return "redirect:/user";
            }
        }
    }

    @RequestMapping(value = "/borrow", params = {"bookNum","username"})
    public String borrow(@RequestParam("bookNum") String bookNum,@RequestParam("username") String username) throws Exception{
        boolean borrow1 = bookService.borrowBook(bookNum,username);
        boolean borrow2 = userService.borrowBook(username);
        if (borrow1 && borrow2){
            userService.setBorrow(bookNum,username);
            String dir = "D:\\123\\borrowAndReturn.txt";
            File file = new File(dir);
            //如果文件不存在，创建文件
            if (!file.exists())
                file.createNewFile();
            //创建FileWriter对象
            FileWriter writer = new FileWriter(file,true);
            //向文件中写入内容
            writer.write(username+"借书:"+bookNum+", 日期为:"+ new Date()+ "\n");
            writer.flush();
            writer.close();
            return "borrow_successful";
        }else{
            return "borrow_failed";
        }
    }


    @RequestMapping(value = "/return", params = {"bookNum","username"})
    public String returnBook(@RequestParam("bookNum") String bookNum,@RequestParam("username") String username) throws Exception{
        boolean return1 = bookService.returnBook(bookNum,username);
        boolean return2 = userService.returnBook(username,bookNum);
        if (return1 && return2){
            String dir = "D:\\123\\borrowAndReturn.txt";
            File file = new File(dir);
            //如果文件不存在，创建文件
            if (!file.exists())
                file.createNewFile();
            //创建FileWriter对象
            FileWriter writer = new FileWriter(file,true);
            //向文件中写入内容
            writer.write(username+"还书:"+bookNum+", 日期为:"+ new Date()+ "\n");
            writer.flush();
            writer.close();
            return "return_successful";
        }else{
            return "return_failed";
        }
    }

}
