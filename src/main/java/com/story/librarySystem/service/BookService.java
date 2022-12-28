package com.story.librarySystem.service;

import com.story.librarySystem.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAll() throws Exception;

    public void getBookIndex() throws Exception;

    public void getAuthorIndex() throws Exception;

    public void getPublishIndex() throws Exception;

    public List<Book> selectBookByBookNum(Integer bookNum);

    public List<Book> selectBookByBookName(String bookName);

    public List<Book> selectBookByAuthor(String author);

    public List<Book> selectBookByPublish(String publish);

    public void addBook(Integer bookNum, String bookName, String author, String publish, Integer assort, Integer storage);

    public boolean borrowBook(String bookNum,String username);

    public boolean returnBook(String bookNum,String username);
}
