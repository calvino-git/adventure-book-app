package com.pictet.tech.advanturebook.domain.repository;

import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.enums.BookDifficulty;
import com.pictet.tech.advanturebook.domain.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository {
    //UC 1
    List<Book> findAll();
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    List<Book> searchByCategory(BookCategory category);
    List<Book> searchByDifficulty(BookDifficulty difficulty);

    //UC 2
    Book retrieveDetails(UUID id);

    void save(Book book);
    long count();


}
