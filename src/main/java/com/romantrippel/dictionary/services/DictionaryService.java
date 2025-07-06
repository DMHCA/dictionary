package com.romantrippel.dictionary.services;

import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import com.romantrippel.dictionary.entity.DictionaryRecord;
import com.romantrippel.dictionary.repositories.DictionaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepository repository;

    public Page<DictionaryRecordDto> findFiltered(
            String word,
            String level,
            String pos,
            Boolean learned,
            Pageable pageable
    ) {
        List<DictionaryRecordDto> all = repository.findAll()
                .stream()
                .map(DictionaryRecord::toDto)    // вот тут исправление
                .filter(record -> word == null || record.word().toLowerCase().contains(word.toLowerCase()))
                .filter(record -> level == null || level.equalsIgnoreCase(record.level()))
                .filter(record -> pos == null || pos.equalsIgnoreCase(record.pos()))
                .filter(record -> learned == null || learned.equals(record.learned()))
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), all.size());

        List<DictionaryRecordDto> content = (start >= end) ? List.of() : all.subList(start, end);

        return new PageImpl<>(content, pageable, all.size());
    }

    public List<DictionaryRecordDto> getRecords() {
        return repository.findAll()
                .stream()
                .map(DictionaryRecord::toDto)   // тоже исправлено
                .toList();
    }
}
