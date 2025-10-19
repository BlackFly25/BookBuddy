package ru.blackfly.bookbuddy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.blackfly.bookbuddy.dto.BookDto;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class BookBuddyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID existingBookId;

    @BeforeEach
    void setup() throws Exception {
        // Создаём книгу для тестов
        BookDto book = BookDto.builder()
                .title("Test Book")
                .author("Test Author")
                .publishedYear(2025)
                .genre("Fiction")
                .build();

        String bookJson = objectMapper.writeValueAsString(book);

        String bookResponse = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        BookDto createdBook = objectMapper.readValue(bookResponse, BookDto.class);
        existingBookId = createdBook.getId();
    }

    @Test
    void testGetBooksPagination() throws Exception {
        mockMvc.perform(get("/api/books?page=0&size=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()", lessThanOrEqualTo(2)));
    }

    @Test
    void testBookNotFound() throws Exception {
        mockMvc.perform(get("/api/books/00000000-0000-0000-0000-000000000000"))
                .andExpect(status().is4xxClientError()) // просто проверяем любой 4xx
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testCreateBookValidation() throws Exception {
        BookDto invalidBook = BookDto.builder()
                .title("") // пустое название
                .author("Author")
                .publishedYear(2025)
                .genre("Fiction")
                .build();

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidBook)))
                .andExpect(status().isBadRequest()); // проверяем, что 400, если включить @Valid и аннотации
    }
}