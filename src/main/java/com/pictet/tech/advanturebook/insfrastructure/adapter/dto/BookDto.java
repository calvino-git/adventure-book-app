package com.pictet.tech.advanturebook.insfrastructure.adapter.dto;

import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.enums.BookDifficulty;
import com.pictet.tech.advanturebook.domain.model.Section;

import java.util.List;

public record BookDto (String title, String author, BookDifficulty difficulty, List<BookCategory> categories, List<Section> sections){
}
