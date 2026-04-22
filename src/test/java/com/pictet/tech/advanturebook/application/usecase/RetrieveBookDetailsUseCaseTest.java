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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class RetrieveBookDetailsUseCaseTest {
    private RetrieveBookDetailsUseCase retrieveBookDetailsUseCase;
    @MockitoBean
    private BookRepository bookRepository;

    private List<Option> options = Collections.emptyList();
    private List<Section> sections = Collections.emptyList();
    private List<Book> books = Collections.emptyList();

    @BeforeEach
    void setup(){
        retrieveBookDetailsUseCase = new RetrieveBookDetailsUseCase(bookRepository);
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


        books = List.of(Book.createBook(UUID.randomUUID(), "The Prisoner", "Daniel El Fuego", BookDifficulty.HARD, new ArrayList<>(List.of(BookCategory.ADVENTURE, BookCategory.HORROR)),
                        List.of(sections.get(0), sections.get(3), sections.get(4))),
                Book.createBook(UUID.randomUUID(), "Pirates of the Jade Sea", "Marina Blackwood", BookDifficulty.MEDIUM, new ArrayList<>(List.of(BookCategory.FICTION)),
                        List.of(sections.get(1), sections.get(2), sections.get(3), sections.get(5)))
        );
    }

    @Test
    void retrieveDetails() {
        //Given
        Book thePrisonerBook = books.getFirst();
        UUID mockId = thePrisonerBook.getId();
        when(bookRepository.retrieveDetails(mockId)).thenReturn(thePrisonerBook);

        //When
        Book retrievedBook = retrieveBookDetailsUseCase.retrieveDetails(mockId);

        //Then
        assertEquals(thePrisonerBook, retrievedBook);
    }

    @Test
    void addCategory() {
        //Given
        Book thePrisonerBook = books.getFirst();
        UUID mockId = thePrisonerBook.getId();
        when(bookRepository.retrieveDetails(mockId)).thenReturn(thePrisonerBook);

        //When
        retrieveBookDetailsUseCase.addCategory(thePrisonerBook, BookCategory.FICTION);
        Book retrievedBook = retrieveBookDetailsUseCase.retrieveDetails(mockId);

        //Then
        assertTrue(thePrisonerBook.getCategories().contains(BookCategory.FICTION));
    }

    @Test
    void addCategories() {
        //Given
        Book thePrisonerBook = books.getFirst();
        UUID mockId = thePrisonerBook.getId();
        when(bookRepository.retrieveDetails(mockId)).thenReturn(thePrisonerBook);

        //When
        retrieveBookDetailsUseCase.addCategories(thePrisonerBook, List.of(BookCategory.FICTION, BookCategory.SCIENCE));
        Book retrievedBook = retrieveBookDetailsUseCase.retrieveDetails(mockId);

        //Then
        assertTrue(thePrisonerBook.getCategories().containsAll(List.of(BookCategory.FICTION, BookCategory.SCIENCE)));
    }

    @Test
    void removeCategory() {
        //Given
        Book thePrisonerBook = books.getFirst();
        UUID mockId = thePrisonerBook.getId();
        when(bookRepository.retrieveDetails(mockId)).thenReturn(thePrisonerBook);

        //When
        retrieveBookDetailsUseCase.removeCategory(thePrisonerBook, BookCategory.HORROR);
        Book retrievedBook = retrieveBookDetailsUseCase.retrieveDetails(mockId);

        //Then
        assertFalse(thePrisonerBook.getCategories().contains(BookCategory.HORROR));
    }

    @Test
    void removeCategories() {
        //Given
        Book thePrisonerBook = books.getFirst();
        UUID mockId = thePrisonerBook.getId();
        when(bookRepository.retrieveDetails(mockId)).thenReturn(thePrisonerBook);

        //When
        retrieveBookDetailsUseCase.removeCategories(thePrisonerBook, List.of(BookCategory.FICTION, BookCategory.SCIENCE));
        Book retrievedBook = retrieveBookDetailsUseCase.retrieveDetails(mockId);

        //Then
        assertFalse(thePrisonerBook.getCategories().containsAll(List.of(BookCategory.FICTION, BookCategory.SCIENCE)));
    }
}