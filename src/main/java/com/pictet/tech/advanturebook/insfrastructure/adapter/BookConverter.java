package com.pictet.tech.advanturebook.insfrastructure.adapter;

import com.pictet.tech.advanturebook.domain.model.Book;

import java.util.function.Function;

public class BookConverter<T> {
    Function<Book, T> fromBookConversion;
    Function<T, Book> toBookConverrsion;
    public BookConverter(final Function<Book, T> fromBookConversion, final Function<T, Book> toBookConverrsion) {
        this.fromBookConversion = fromBookConversion;
        this.toBookConverrsion = toBookConverrsion;
    }

    public T convertFromBook(Book book){
        return fromBookConversion.apply(book);
    }

    public Book convertToBook(T t){
        return toBookConverrsion.apply(t);
    }
}
