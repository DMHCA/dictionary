package com.romantrippel.dictionary.controllers;

import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import com.romantrippel.dictionary.services.DictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/records/{word}")
    public ResponseEntity<DictionaryRecordDto> getRecordByWord(@PathVariable String word) {
        var record = dictionaryService.findRecordByWord(word);

        return ResponseEntity.status(HttpStatus.OK).body(record);
    }

    @GetMapping("/records")
    public List<DictionaryRecordDto> getRecords() {
        return dictionaryService.getRecords();
    }

    @PostMapping("/records")
    public ResponseEntity<DictionaryRecordDto> createRecord(@RequestBody DictionaryRecordDto dictionaryRecordDto) {
        var savedDto = dictionaryService.createRecord(dictionaryRecordDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }
}
