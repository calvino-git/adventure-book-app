package com.pictet.tech.advanturebook.insfrastructure.adapter.persistence;

import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.enums.BookDifficulty;
import com.pictet.tech.advanturebook.domain.model.Section;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "books")
public class BookEntity {
    @Id
    public UUID id;
    public String title;
    public String author;
    public BookDifficulty difficulty;
    public List<BookCategory> categories = new ArrayList<>();
    public List<Section> sections = new ArrayList<>();
}
