package com.pictet.tech.advanturebook.insfrastructure.adapter.persistence;

import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.enums.BookDifficulty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataBookRepository extends MongoRepository<BookEntity, UUID> {
    List<BookEntity> findAllByTitle(String title);

    @Query("{'title': ?0}")
    List<BookEntity> searchByTitle(String title);

    @Query("{'author': ?0}")
    List<BookEntity> searchByAuthor(String author);

    @Query("{'categories': ?0}")
    List<BookEntity> searchByCategory(BookCategory category);

    @Query("{'difficulty': ?0}")
    List<BookEntity> searchByDifficulty(BookDifficulty difficulty);
}
