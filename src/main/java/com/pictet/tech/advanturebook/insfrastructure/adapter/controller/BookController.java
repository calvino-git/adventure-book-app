package com.pictet.tech.advanturebook.insfrastructure.adapter.controller;

import com.pictet.tech.advanturebook.application.usecase.RetrieveBookDetailsUseCase;
import com.pictet.tech.advanturebook.application.usecase.SearchBookUseCase;
import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.enums.BookDifficulty;
import com.pictet.tech.advanturebook.domain.model.Book;
import com.pictet.tech.advanturebook.domain.repository.BookRepository;
import com.pictet.tech.advanturebook.insfrastructure.adapter.dto.BookDto;
import com.pictet.tech.advanturebook.insfrastructure.adapter.dto.BookDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {
    private final SearchBookUseCase searchBookUseCase;
    private final RetrieveBookDetailsUseCase retrieveBookDetailsUseCase;
    private final BookDtoConverter bookDtoConverter;

    public BookController(SearchBookUseCase searchBookUseCase, RetrieveBookDetailsUseCase retrieveBookDetailsUseCase, BookDtoConverter bookDtoConverter) {
        this.searchBookUseCase = searchBookUseCase;
        this.retrieveBookDetailsUseCase = retrieveBookDetailsUseCase;
        this.bookDtoConverter = bookDtoConverter;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return searchBookUseCase.findAll().stream()
                .map(bookDtoConverter::convertFromBook)
                .toList();
    }

    @PostMapping("/search/{title}")
    public List<BookDto> searchByTitle(@PathVariable String title) {
        return searchBookUseCase.searchByTitle(title).stream()
                .map(bookDtoConverter::convertFromBook).toList();
    }

    @PostMapping("/search/{author}")
    public List<BookDto> searchByAuthor(@PathVariable String author){
        return searchBookUseCase.searchByAuthor(author).stream()
                .map(bookDtoConverter::convertFromBook).toList();
    }

    @PostMapping("/search/{category}")
    public List<BookDto> searchByCategory(@PathVariable BookCategory category){
        return searchBookUseCase.searchByCategory(category).stream()
                .map(bookDtoConverter::convertFromBook).toList();
    }

    @PostMapping("/search/{difficulty}")
    public List<BookDto> searchByDifficulty(@PathVariable BookDifficulty difficulty){
        return searchBookUseCase.searchByDifficulty(difficulty).stream()
                .map(bookDtoConverter::convertFromBook).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> retrieveBookDetails(@PathVariable UUID id){
        BookDto bookDto = bookDtoConverter.convertFromBook(retrieveBookDetailsUseCase.retrieveDetails(id));
        if(bookDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping("/{id}/add/category")
    public ResponseEntity<Void> addCategory(@PathVariable UUID id, @RequestBody BookCategory category){
        Book book = retrieveBookDetailsUseCase.retrieveDetails(id);
        retrieveBookDetailsUseCase.addCategory(book, category);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}/remove/category")
    public ResponseEntity<Void> removeCategory(@PathVariable UUID id, @RequestBody BookCategory category){
        Book book = retrieveBookDetailsUseCase.retrieveDetails(id);
        retrieveBookDetailsUseCase.removeCategory(book, category);
        return ResponseEntity.ok().build();
    }

}
