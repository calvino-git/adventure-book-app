package com.pictet.tech.advanturebook.application.usecase;

import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.enums.BookDifficulty;
import com.pictet.tech.advanturebook.domain.model.Book;
import com.pictet.tech.advanturebook.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchBookUseCase {
    private final BookRepository bookRepository;

    public SearchBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> searchByTitle(String title){
        return bookRepository.searchByTitle(title);
    }

    public List<Book> searchByAuthor(String author){
        return bookRepository.searchByAuthor(author);
    }

    public List<Book> searchByCategory(BookCategory category){
        return bookRepository.searchByCategory(category);
    }

    public List<Book> searchByDifficulty(BookDifficulty difficulty){
        return bookRepository.searchByDifficulty(difficulty);
    }
}
