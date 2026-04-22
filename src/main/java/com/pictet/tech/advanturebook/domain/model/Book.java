package com.pictet.tech.advanturebook.domain.model;

import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.enums.BookDifficulty;
import com.pictet.tech.advanturebook.domain.enums.SectionType;
import com.pictet.tech.advanturebook.domain.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private String author;
    private BookDifficulty difficulty ;
    private List<BookCategory> categories = new ArrayList<>();
    private List<Section> sections = new ArrayList<>();

    public static Book createBook(UUID id, String title, String author, BookDifficulty difficulty, List<BookCategory> categories, List<Section> sections) throws InvalidStateException {
        Book book = new Book();
        if(sections.stream().filter(s->s.type().equals(SectionType.BEGIN)).count() > 1){
            throw new InvalidStateException("book more than one beginning");
        }else
        if(sections.stream().noneMatch(s -> s.type().equals(SectionType.END))) {
            throw new InvalidStateException("Book has no ending");
        }else {
            book.setId(id);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategories(categories);
            book.setDifficulty(difficulty);
            book.setSections(sections);
        }
        return book;
    }

    public void addCategory(BookCategory category){
        if(!categories.contains(category))
            categories.add(category);
    }

    public void removeCategory(BookCategory category){
        categories.remove(category);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BookDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<BookCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<BookCategory> categories) {
        this.categories = categories;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}
