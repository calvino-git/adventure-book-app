package com.pictet.tech.advanturebook.application.usecase;

import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.model.Book;
import com.pictet.tech.advanturebook.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RetrieveBookDetailsUseCase {
    private final BookRepository bookRepository;

    public RetrieveBookDetailsUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book retrieveDetails(UUID id){
        return bookRepository.retrieveDetails(id);
    }

    public void addCategory(Book book, BookCategory category){
        book.addCategory(category);
    }

    public void addCategories(Book book, List<BookCategory> categories){
        for(BookCategory category : categories){
           book.addCategory(category);
        }
    }
    public void removeCategory(Book book, BookCategory category){
        book.removeCategory(category);
    }

    public void removeCategories(Book book, List<BookCategory> categories){
        for(BookCategory category : categories){
            book.removeCategory(category);
        }
    }

}
