package com.story.librarySystem.controller;

import com.story.librarySystem.entity.Book;
import com.story.librarySystem.entity.User;
import com.story.librarySystem.service.BookService;
import com.story.librarySystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String getAll(Model model) throws Exception{
        List<Book> books = bookService.getAll();
//        String selectValue = "请输入要查询的值...";
        bookService.getBookIndex();
        bookService.getAuthorIndex();
        bookService.getPublishIndex();
        model.addAttribute("books",books);
        List<User> users = userService.getAll();
        model.addAttribute("users",users);
//        model.addAttribute("selectValue",new Book());
//        model.addAttribute("selectValue",selectValue);
//        model.addAttribute("selectInput",selectInput);
        return "index";
    }

    @RequestMapping("/")
    public String unit(){
        return "login";
    }

    @RequestMapping("/user")
    public String user(Model model) throws Exception{
        List<Book> books = bookService.getAll();
        model.addAttribute("books",books);
        return "user";
    }
}
