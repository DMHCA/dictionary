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
        List<DictionaryRecord> records = dictionaryRepository.findAll();
        return records.stream().map(DictionaryRecord::toDto).toList();
    }

    public DictionaryRecordDto createRecord(DictionaryRecordDto dictionaryRecordDto) {
        return dictionaryRepository.save(dictionaryRecordDto.toEntity()).toDto();
    }
}
