package com.romantrippel.dictionary.controllers;

import com.romantrippel.dictionary.services.ExternalSourceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExternalSourceController {

    private static final Logger logger = LoggerFactory.getLogger(ExternalSourceController.class);
    private final ExternalSourceService externalSourceService;

    @GetMapping("/oxford/words")
    public ResponseEntity<Integer> words() {
        logger.info("Getting words from external source");

        int savedCount = externalSourceService.parseOxfordList();

        return ResponseEntity.ok(savedCount);
    }
}

