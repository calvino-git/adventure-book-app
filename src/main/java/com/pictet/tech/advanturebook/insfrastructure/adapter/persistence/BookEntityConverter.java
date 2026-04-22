package com.pictet.tech.advanturebook.insfrastructure.adapter.persistence;

import com.pictet.tech.advanturebook.domain.model.Book;
import com.pictet.tech.advanturebook.insfrastructure.adapter.BookConverter;
import org.springframework.stereotype.Component;

@Component
public class BookEntityConverter extends BookConverter<BookEntity> {

    public BookEntityConverter() {
        super(BookEntityConverter::convertBook, BookEntityConverter::convertBookEntity);
    }

    public static BookEntity convertBook(Book book){
        if(book == null) return null;
        BookEntity bookEntity = new BookEntity();
        bookEntity.author = book.getAuthor();
        bookEntity.title = book.getTitle();
        bookEntity.categories.addAll(book.getCategories());
        bookEntity.difficulty = book.getDifficulty();
        bookEntity.sections.addAll(book.getSections());
        return bookEntity;
    }
    public static Book convertBookEntity(BookEntity bookEntity){
        if(bookEntity == null) return null;
        return Book.createBook(bookEntity.id, bookEntity.title, bookEntity.author, bookEntity.difficulty, bookEntity.categories, bookEntity.sections);
    }
}
