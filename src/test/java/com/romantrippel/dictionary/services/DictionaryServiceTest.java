package com.romantrippel.dictionary.services;

import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import com.romantrippel.dictionary.entity.DictionaryRecord;
import com.romantrippel.dictionary.repositories.DictionaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("DictionaryService")
class DictionaryServiceTest {

    private final DictionaryRepository repository = mock(DictionaryRepository.class);
    private final DictionaryService service = new DictionaryService(repository);

    @Test
    @DisplayName("findRecordByWord() returns DTO when word is found")
    void shouldReturnDtoWhenWordIsFound() {
        var record = new DictionaryRecord("hello", "привет");
        when(repository.findByWord("hello")).thenReturn(Optional.of(record));

        var result = service.findRecordByWord("hello");

        assertThat(result.word()).isEqualTo("hello");
        assertThat(result.translation()).isEqualTo("привет");
    }

    @Test
    @DisplayName("findRecordByWord() throws EntityNotFoundException when word is not found")
    void shouldThrowWhenWordNotFound() {
        when(repository.findByWord("missing")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findRecordByWord("missing"))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Word not found: missing");
    }

    @Test
    @DisplayName("getRecords() returns list of DTOs from repository")
    void shouldReturnAllRecords() {
        var list = List.of(
                new DictionaryRecord("cat", "кот"),
                new DictionaryRecord("dog", "пёс")
        );
        when(repository.findAll()).thenReturn(list);

        var result = service.getRecords();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).word()).isEqualTo("cat");
        assertThat(result.get(1).word()).isEqualTo("dog");
    }

    @Test
    @DisplayName("createRecord() saves entity and returns DTO")
    void shouldSaveAndReturnDto() {
        var dto = new DictionaryRecordDto("sun", "солнце");
        var entity = dto.toEntity();
        var saved = new DictionaryRecord("sun", "солнце");

        when(repository.save(entity)).thenReturn(saved);

        var result = service.createRecord(dto);

        assertThat(result.word()).isEqualTo("sun");
        assertThat(result.translation()).isEqualTo("солнце");
    }
}
