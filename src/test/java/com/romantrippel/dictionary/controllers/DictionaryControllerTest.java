package com.romantrippel.dictionary.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import com.romantrippel.dictionary.services.DictionaryService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DictionaryController.class)
@Import(DictionaryControllerTest.MockedServiceConfig.class)
class DictionaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockedServiceConfig {
        @Bean
        public DictionaryService dictionaryService() {
            return Mockito.mock(DictionaryService.class);
        }
    }

    @Test
    @DisplayName("GET /records/{word} — returns a record")
    void shouldReturnRecordByWord() throws Exception {
        var dto = new DictionaryRecordDto(4L, "apple", "яблоко");
        Mockito.when(dictionaryService.findRecordByWord("apple")).thenReturn(dto);

        mockMvc.perform(get("/api/records/apple"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.word", is("apple")))
                .andExpect(jsonPath("$.translation", is("яблоко")));
    }

    @Test
    @DisplayName("GET /records/{word} — returns 404 when not found")
    void shouldReturn404WhenWordNotFound() throws Exception {
        Mockito.when(dictionaryService.findRecordByWord("ghost"))
                .thenThrow(new EntityNotFoundException("Record not found"));

        mockMvc.perform(get("/api/records/ghost"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /records — returns all records")
    void shouldReturnAllRecords() throws Exception {
        var list = List.of(
                new DictionaryRecordDto(2L, "apple", "яблоко"),
                new DictionaryRecordDto(3L, "dog", "собака")
        );

        Mockito.when(dictionaryService.getRecords()).thenReturn(list);

        mockMvc.perform(get("/api/records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].word", is("apple")))
                .andExpect(jsonPath("$[1].word", is("dog")));
    }

    @Test
    @DisplayName("POST /records — creates a new record")
    void shouldCreateRecord() throws Exception {
        var inputDto = new DictionaryRecordDto(1L,"cat", "кот");
        var savedDto = new DictionaryRecordDto(1L, "cat", "кот");

        Mockito.when(dictionaryService.createRecord(Mockito.any())).thenReturn(savedDto);

        mockMvc.perform(post("/api/records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.word", is("cat")))
                .andExpect(jsonPath("$.translation", is("кот")));
    }

    @Test
    @DisplayName("POST /records — returns 400 when word is blank")
    void shouldFailValidationWhenWordIsBlank() throws Exception {
        var invalidDto = new DictionaryRecordDto(1L, "  ", "пусто");

        mockMvc.perform(post("/api/records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }
}
