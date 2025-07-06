package com.romantrippel.dictionary.services;

import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import com.romantrippel.dictionary.entity.DictionaryRecord;
import com.romantrippel.dictionary.repositories.DictionaryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@AllArgsConstructor
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    public Page<DictionaryRecordDto> searchRecordsByWord(String word, Pageable pageable) {
        return dictionaryRepository.searchByWord(word, pageable)
                .map(DictionaryRecord::toDto);
    }

    public DictionaryRecordDto findRecordByWord(String word) {
        return dictionaryRepository.findByWord(word)
                .orElseThrow(() -> new EntityNotFoundException("Word not found: " + word))
                .toDto();
    }

    public Page<DictionaryRecordDto> getRecords(Pageable pageable) {
        return dictionaryRepository.findAll(pageable)
                .map(this::toDto);
    }


    public List<DictionaryRecordDto> getRecords() {
        return dictionaryRepository.findAll()
                .stream()
                .map(DictionaryRecord::toDto)
                .toList();
    }

    public DictionaryRecordDto createRecord(DictionaryRecordDto dto) {
        // TODO: add additional validation if the service is called outside the controller
        var entity = toEntity(dto);
        var saved = dictionaryRepository.save(entity);
        return toDto(saved);
    }

    private DictionaryRecordDto toDto(DictionaryRecord entity) {
        return entity.toDto();
    }

    private DictionaryRecord toEntity(DictionaryRecordDto dto) {
        return dto.toEntity();
    }
}
