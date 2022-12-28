package com.story.librarySystem.controller;

import com.story.librarySystem.entity.Book;
import com.story.librarySystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SelectController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/select/bookNum{bookNum}")
    public String selectByBookNum(Model model, @RequestParam("bookNum") Integer bookNum) throws Exception{
        List<Book> books = bookService.selectBookByBookNum(bookNum);
//        model.addAttribute("selectValue",book==null?"查无数据,请重试":book);
        model.addAttribute("selectValue",books);
        return "select";
    }

    @RequestMapping("/select/bookName{bookName}")
    public String selectByBookName(Model model, @RequestParam("bookName") String bookName) throws Exception{
        List<Book> books = bookService.selectBookByBookName(bookName);
//        model.addAttribute("selectValue",book==null?"查无数据,请重试":book);
        model.addAttribute("selectValue",books);
        return "select";
    }

    @RequestMapping("/select/author{author}")
    public String selectByAuthor(Model model, @RequestParam("author") String author) throws Exception{
        List<Book> books = bookService.selectBookByAuthor(author);
//        model.addAttribute("selectValue",book==null?"查无数据,请重试":book);
        model.addAttribute("selectValue",books);
        return "select";
    }

    @RequestMapping("/select/publish{publish}")
    public String selectByPublish(Model model, @RequestParam("publish") String publish) throws Exception{
        List<Book> books = bookService.selectBookByPublish(publish);
//        model.addAttribute("selectValue",book==null?"查无数据,请重试":book);
        model.addAttribute("selectValue",books);
        return "select";
    }
}
