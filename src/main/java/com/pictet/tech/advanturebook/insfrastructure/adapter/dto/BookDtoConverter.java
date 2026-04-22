package com.pictet.tech.advanturebook.insfrastructure.adapter.dto;

import com.pictet.tech.advanturebook.domain.exception.InvalidStateException;
import com.pictet.tech.advanturebook.domain.model.Book;
import com.pictet.tech.advanturebook.insfrastructure.adapter.BookConverter;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter extends BookConverter<BookDto> {
    public BookDtoConverter() {
        super(BookDtoConverter::convertBook, BookDtoConverter::convertBookDto);
    }

    public static BookDto convertBook(Book book){
        if(book == null) return null;
        return new BookDto(book.getTitle(), book.getAuthor(), book.getDifficulty(), book.getCategories(), book.getSections());
    }
    public static Book convertBookDto(BookDto bookDto){
        if(bookDto == null) return null;
        return Book.createBook(null, bookDto.title(), bookDto.author(), bookDto.difficulty(), bookDto.categories(), bookDto.sections());
    }
}
