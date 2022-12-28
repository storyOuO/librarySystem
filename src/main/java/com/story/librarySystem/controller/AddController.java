package com.story.librarySystem.controller;

import com.story.librarySystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class AddController {
    @Resource
    private BookService bookService;

    @RequestMapping(value = "/add", params = {"bookNum", "bookName", "author", "publish", "assort","storage"})
    public String addBook(@RequestParam("bookNum") Integer bookNum, @RequestParam("bookName") String bookName,
                          @RequestParam("author") String author, @RequestParam("publish") String publish,
                          @RequestParam("assort") Integer assort, @RequestParam("storage") Integer storage){

        bookService.addBook(bookNum,bookName,author,publish,assort,storage);
        return "redirect:/index";
    }
}
