package com.romantrippel.dictionary.controllers;

import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import com.romantrippel.dictionary.services.DictionaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class DictionaryController {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    private final DictionaryService dictionaryService;

    @GetMapping("/page")
    public Page<DictionaryRecordDto> getRecordsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return dictionaryService.getRecords(pageable);
    }

    @GetMapping("/{word}")
    public ResponseEntity<DictionaryRecordDto> getRecordByWord(@PathVariable String word) {
        logger.info("Received request to get record by word: {}", word);
        var record = dictionaryService.findRecordByWord(word);
        logger.info("Returning record for word: {}", word);

        return ResponseEntity.status(HttpStatus.OK).body(record);
    }

    @GetMapping
    public List<DictionaryRecordDto> getRecords() {
        logger.info("Received request to get all records");

        return dictionaryService.getRecords();
    }

    @PostMapping
    public ResponseEntity<DictionaryRecordDto> createRecord(@Valid @RequestBody DictionaryRecordDto dictionaryRecordDto) {
        logger.info("Received request to create record: {}", dictionaryRecordDto);
        var savedDto = dictionaryService.createRecord(dictionaryRecordDto);
        logger.info("Created record with id: {}", savedDto.id());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }
}
