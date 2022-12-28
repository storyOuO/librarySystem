package com.story.librarySystem.service.impl;

import com.story.librarySystem.entity.AuthorIndex;
import com.story.librarySystem.entity.Book;
import com.story.librarySystem.entity.BookIndex;
import com.story.librarySystem.entity.PublishIndex;
import com.story.librarySystem.mapper.BookMapper;
import com.story.librarySystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    public static Map<String,Integer> p1Map = new HashMap<>();
    public static Map<String,Integer> p2Map = new HashMap<>();
    public static Map<String,Integer> p3Map = new HashMap<>();
    public static Integer len;

    @Override
    public List<Book> getAll() throws Exception{
        String unitIndex = "记录号\t书号\t书名\t指针1\t作者\t指针2\t出版社\t指针3\t分类\t藏书量\t借出数\n";
        List<Book> books = bookMapper.selectAll();
        len = books.size();
        for (Book book : books){
            //存入每本书最后记录的书名和记录号
            p1Map.put(book.getBookName(),book.getNum());
            //存入每本书最后记录的作者和记录号
            p2Map.put(book.getAuthor(),book.getNum());
            //存入每本书最后记录的出版社和记录号
            p3Map.put(book.getPublish(),book.getNum());
            unitIndex += book + "\n";
        }
        String dir = "D:\\123\\unit.txt";
        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists())
            file.createNewFile();
        //创建FileWriter对象
        FileWriter writer = new FileWriter(file);
        //向文件中写入内容
        writer.write(unitIndex);
        writer.flush();
        writer.close();
        return bookMapper.selectAll();
    }

    @Override
    public void getBookIndex() throws Exception {
        String bookIndex = "书名\t链头指针\t长度\n";
        List<BookIndex> bookIndices = bookMapper.selectBookIndex();
//        System.out.println(bookIndices);
        for (BookIndex index : bookIndices){
            bookIndex += index + "\n";
        }
        String dir = "D:\\123\\bookIndex.txt";
        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists())
            file.createNewFile();
        //创建FileWriter对象
        FileWriter writer = new FileWriter(file);
        //向文件中写入内容
        writer.write(bookIndex);
        writer.flush();
        writer.close();
//        return bookIndices;
    }

    @Override
    public void getAuthorIndex() throws Exception {
        String authorIndex = "作者\t链头指针\t长度\n";
        List<AuthorIndex> authorIndices= bookMapper.selectAuthorIndex();
//        System.out.println(bookIndices);
        for (AuthorIndex author : authorIndices){
            authorIndex += author + "\n";
        }
        String dir = "D:\\123\\authorIndex.txt";
        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists())
            file.createNewFile();
        //创建FileWriter对象
        FileWriter writer = new FileWriter(file);
        //向文件中写入内容
        writer.write(authorIndex);
        writer.flush();
        writer.close();
//        return bookIndices;
    }

    @Override
    public void getPublishIndex() throws Exception {
        String publishIndex = "出版社\t链头指针\t长度\n";
        List<PublishIndex> publishIndices= bookMapper.selectPublishIndex();
//        System.out.println(bookIndices);
        for (PublishIndex publish : publishIndices){
            publishIndex += publish + "\n";
        }
        String dir = "D:\\123\\publishIndex.txt";
        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists())
            file.createNewFile();
        //创建FileWriter对象
        FileWriter writer = new FileWriter(file);
        //向文件中写入内容
        writer.write(publishIndex);
        writer.flush();
        writer.close();
//        return bookIndices;
    }

    @Override
    public List<Book> selectBookByBookNum(Integer bookNum) {
        List<Book> books = bookMapper.selectByBookNum(bookNum);
        return books;
    }

    @Override
    public List<Book> selectBookByBookName(String bookName) {
        List<Book> books = bookMapper.selectByBookName(bookName);
        return books;
    }

    @Override
    public List<Book> selectBookByAuthor(String author) {
        List<Book> books = bookMapper.selectByAuthor(author);
        return books;
    }

    @Override
    public List<Book> selectBookByPublish(String publish) {
        List<Book> books = bookMapper.selectByPublish(publish);
        return books;
    }

    @Override
    public void addBook(Integer bookNum, String bookName, String author, String publish, Integer assort, Integer storage) {
        Integer pointer1,pointer2,pointer3;
        Integer l1,l2,l3;
        if (p1Map.containsKey(bookName)){
            pointer1 = p1Map.get(bookName);
            l1 = bookMapper.searchLengthByBookName(bookName)+1;
            bookMapper.updateBookIndex(bookName,p1Map.get(bookName),l1);
        }else{
            pointer1 = 0;
            l1 = 1;
            bookMapper.addBookIndex(bookName,len+1,l1);
        }

        if (p2Map.containsKey(author)){
            pointer2 = p1Map.get(author);
            l2 = bookMapper.searchLengthByAuthor(author)+1;
            bookMapper.updateAuthor(author,p2Map.get(author),l2);
        }else{
            pointer2 = 0;
            l2 = 1;
            bookMapper.addAuthors(author,len+1,l2);
        }

        if (p3Map.containsKey(publish)){
            pointer3 = p1Map.get(publish);
            l3 = bookMapper.searchLengthByPublish(publish)+1;
            bookMapper.updateAuthor(publish,p3Map.get(publish),l3);
        }else{
            pointer3 = 0;
            l3 = 1;
            bookMapper.addPublish(publish,len+1,l3);
        }
        len++;
        bookMapper.addBook(bookNum,bookName,pointer1,author,pointer2,publish,pointer3,assort,storage);
    }

    @Override
    public boolean borrowBook(String bookNum,String username) {
        if (bookMapper.getStorageByBookNum(bookNum)>bookMapper.getBorrowByBookNum(bookNum)){
            bookMapper.borrowBook(bookNum);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean returnBook(String bookNum, String username) {
        if (bookMapper.getBorrowByBookNum(bookNum) > 0){
            bookMapper.returnBook(bookNum);
            return true;
        }else{
            return false;
        }
    }


}
