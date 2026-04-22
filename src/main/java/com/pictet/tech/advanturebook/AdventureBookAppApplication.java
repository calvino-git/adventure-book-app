package com.pictet.tech.advanturebook;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pictet.tech.advanturebook.domain.model.Book;
import com.pictet.tech.advanturebook.domain.repository.BookRepository;
import com.pictet.tech.advanturebook.insfrastructure.adapter.persistence.SpringDataBookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class AdventureBookAppApplication {
    private final BookRepository repository;
    private final ObjectMapper objectMapper;

    public AdventureBookAppApplication(BookRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdventureBookAppApplication.class, args);
    }

    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            try (InputStream is = getClass().getResourceAsStream("/data/the-prisoner.json")) {
                if (is == null) {
                    throw new IllegalArgumentException("Fichier JSON non trouvé dans le classpath !");
                }
                List<Book> books = objectMapper.readValue(is, new TypeReference<List<Book>>() {});
                books.forEach(repository::save);
                System.out.println("JSON imported");
            } catch (IOException e) {
                System.err.println("Error : " + e.getMessage());
            }
        }
    }
}
