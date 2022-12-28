package com.story.librarySystem.mapper;

import com.story.librarySystem.entity.AuthorIndex;
import com.story.librarySystem.entity.Book;
import com.story.librarySystem.entity.BookIndex;
import com.story.librarySystem.entity.PublishIndex;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BookMapper {

   public List<Book> selectAll();

   public List<BookIndex> selectBookIndex();

   public List<AuthorIndex> selectAuthorIndex();

   public List<PublishIndex> selectPublishIndex();

   public List<Book> selectByBookNum(Integer id);

   public List<Book> selectByBookName(String bookName);

   public List<Book> selectByAuthor(String author);

   public List<Book> selectByPublish(String publish);

   public void addBook(Integer bookNum, String bookName, Integer pointer1, String author, Integer pointer2,
                   String publish, Integer pointer3, Integer assort, Integer storage);

   public Integer searchLengthByBookName(String bookName);

   public Integer searchLengthByAuthor(String author);

   public Integer searchLengthByPublish(String publish);

   public void addBookIndex(String bookName, Integer headPointer, Integer length);

   public void addAuthors(String authorName, Integer headPointer, Integer length);

   public void addPublish(String publishName, Integer headPointer, Integer length);

   public void updateBookIndex(String bookName, Integer headPointer,Integer len);

   public void updateAuthor(String authorName, Integer headPointer,Integer len);

   public void updatePublish(String publishName, Integer headPointer,Integer len);

   public void borrowBook(String bookNum);

   public Integer getStorageByBookNum(String bookNum);

   public Integer getBorrowByBookNum(String bookNum);

   public String getBookNameByBookNum(String bookNum);

   public void returnBook(String bookNum);
}
