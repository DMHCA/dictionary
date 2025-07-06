package com.romantrippel.dictionary.controllers;

import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import com.romantrippel.dictionary.services.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @PatchMapping("/{id}/learned")
    public ResponseEntity<Void> updateLearnedStatus(
            @PathVariable Long id,
            @RequestParam boolean learned
    ) {
        boolean updated = dictionaryService.updateLearnedStatus(id, learned);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/page")
    public Page<DictionaryRecordDto> getAllRecords(
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String pos,
            @RequestParam(required = false) Boolean learned,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return dictionaryService.findFiltered(null, level, pos, learned, pageable);
    }

    @GetMapping("/search")
    public Page<DictionaryRecordDto> searchRecordsByWord(
            @RequestParam String word,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String pos,
            @RequestParam(required = false) Boolean learned,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return dictionaryService.findFiltered(word, level, pos, learned, pageable);
    }
}
