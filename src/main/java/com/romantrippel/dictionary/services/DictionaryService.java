package com.romantrippel.dictionary.services;

import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import com.romantrippel.dictionary.entity.DictionaryRecord;
import com.romantrippel.dictionary.repositories.DictionaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    public DictionaryService(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public DictionaryRecordDto findRecordByWord(String word) {
        return dictionaryRepository.findByWord(word)
                .orElseThrow(() -> new EntityNotFoundException("Word not found: " + word))
                .toDto();
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
