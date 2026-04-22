package com.pictet.tech.advanturebook.application.usecase;

import com.pictet.tech.advanturebook.domain.enums.BookCategory;
import com.pictet.tech.advanturebook.domain.enums.BookDifficulty;
import com.pictet.tech.advanturebook.domain.enums.ConsequenceType;
import com.pictet.tech.advanturebook.domain.enums.SectionType;
import com.pictet.tech.advanturebook.domain.model.Book;
import com.pictet.tech.advanturebook.domain.model.Consequence;
import com.pictet.tech.advanturebook.domain.model.Option;
import com.pictet.tech.advanturebook.domain.model.Section;
import com.pictet.tech.advanturebook.domain.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class SearchBookUseCaseTest {
    private SearchBookUseCase searchBookUseCase;
    @MockitoBean
    private BookRepository bookRepository;

    private List<Option> options = Collections.emptyList();
    private List<Section> sections = Collections.emptyList();
    private List<Book> books = Collections.emptyList();

    @BeforeEach
    void setup(){
        searchBookUseCase = new SearchBookUseCase(bookRepository);
        options = List.of(
                new Option("You try to open the door", 500, null),
                new Option("You look under the bed", 20, null),
                new Option("Try to scan the area with your hands", 30,
                        new Consequence(ConsequenceType.LOST_HEALTH, 6, "lose 6 points"))
        );
        sections = List.of(
                new Section(1, "section 1", SectionType.BEGIN, List.of(options.get(0))),
                new Section(2, "section 2", SectionType.BEGIN, List.of(options.get(1))),
                new Section(3, "Section 3", SectionType.NODE, List.of(options.get(2))),
                new Section(4, "Section 4", SectionType.NODE, List.of(options.get(1))),
                new Section(5, "Section 5", SectionType.END, List.of(options.get(2))),
                new Section(6, "Section 6", SectionType.END, List.of(options.get(1)))
        );


        books = List.of(
                Book.createBook(UUID.randomUUID(), "The Prisoner", "Daniel El Fuego", BookDifficulty.HARD, List.of(BookCategory.ADVENTURE, BookCategory.HORROR),
                        List.of(sections.get(0), sections.get(3), sections.get(4))),
                Book.createBook(UUID.randomUUID(), "Pirates of the Jade Sea", "Marina Blackwood", BookDifficulty.MEDIUM, List.of(BookCategory.FICTION),
                        List.of(sections.get(1), sections.get(2), sections.get(3), sections.get(5)))
        );
    }

    @Test
    void findAll() {
        //Given
        List<Book> mockBooks = new ArrayList<>(books);
        when(bookRepository.findAll()).thenReturn(mockBooks);

        //When
        List<Book> bookList = searchBookUseCase.findAll();

        //Then
        assertEquals(2, bookList.size());
    }

    @Test
    void searchByTitle() {
        //Given
        Book thePrisonerBook = books.getFirst();
        when(bookRepository.searchByTitle("The Prisoner")).thenReturn(List.of(thePrisonerBook));

        //When
        List<Book> bookList = searchBookUseCase.searchByTitle("The Prisoner");

        //Then
        assertNotNull(bookList);
        assertFalse(bookList.isEmpty());
        assertEquals(1, bookList.size());
        assertTrue(bookList.contains(thePrisonerBook));

    }

    @Test
    void searchByAuthor() {
        //Given
        Book thePrisonerBook = books.getFirst();
        when(bookRepository.searchByAuthor("Daniel El Fuego")).thenReturn(List.of(thePrisonerBook));

        //When
        List<Book> bookList = searchBookUseCase.searchByAuthor("Daniel El Fuego");
        //Then
        assertNotNull(bookList);
        assertFalse(bookList.isEmpty());
        assertTrue(bookList.contains(thePrisonerBook));
    }

    @Test
    void searchByCategory() {
        //Given
        Book thePrisonerBook = books.getFirst();
        when(bookRepository.searchByCategory(BookCategory.ADVENTURE)).thenReturn(List.of(thePrisonerBook));

        //When
        List<Book> bookList = searchBookUseCase.searchByCategory(BookCategory.ADVENTURE);
        //Then
        assertNotNull(bookList);
        assertFalse(bookList.isEmpty());
        assertTrue(bookList.contains(thePrisonerBook));
    }

    @Test
    void searchByDifficulty() {
        //Given
        Book piratesBook = books.get(1);

        when(bookRepository.searchByDifficulty(BookDifficulty.MEDIUM)).thenReturn(List.of(piratesBook));

        //When
        List<Book> bookList = searchBookUseCase.searchByDifficulty(BookDifficulty.MEDIUM);
        //Then
        assertNotNull(bookList);
        assertFalse(bookList.isEmpty());
        assertTrue(bookList.contains(piratesBook));
    }
}