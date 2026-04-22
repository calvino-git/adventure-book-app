package com.pictet.tech.advanturebook.insfrastructure.adapter.persistence;

import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.enums.BookDifficulty;
import com.pictet.tech.advanturebook.domain.model.Book;
import com.pictet.tech.advanturebook.domain.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MongoBookAdapter implements BookRepository {
    private final SpringDataBookRepository springDataBookRepository;
    private final BookEntityConverter bookEntityConverter;
    public MongoBookAdapter(SpringDataBookRepository springDataBookRepository, BookEntityConverter bookEntityConverter) {
        this.springDataBookRepository = springDataBookRepository;
        this.bookEntityConverter = bookEntityConverter;
    }

    @Override
    public List<Book> findAll() {
       var  list = springDataBookRepository.findAll();
       return list.stream().map(bookEntityConverter::convertToBook).toList();
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return springDataBookRepository.searchByTitle(title).stream().map(bookEntityConverter::convertToBook).toList();
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return springDataBookRepository.searchByAuthor(author).stream().map(bookEntityConverter::convertToBook).toList();
    }

    @Override
    public List<Book> searchByCategory(BookCategory category) {
        return springDataBookRepository.searchByCategory(category).stream().map(bookEntityConverter::convertToBook).toList();
    }

    @Override
    public List<Book> searchByDifficulty(BookDifficulty difficulty) {
        return springDataBookRepository.searchByDifficulty(difficulty).stream().map(bookEntityConverter::convertToBook).toList();
    }

    @Override
    public Book retrieveDetails(UUID id) {
        return springDataBookRepository.findById(id).map(bookEntityConverter::convertToBook).orElse(null);
    }

    @Override
    public void save(Book book) {
        springDataBookRepository.save(bookEntityConverter.convertFromBook(book));
    }

    @Override
    public long count() {
        return springDataBookRepository.count();
    }
}
