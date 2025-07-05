package com.romantrippel.dictionary.services;

import com.romantrippel.dictionary.dto.OxfordWordDto;
import com.romantrippel.dictionary.entity.DictionaryRecord;
import com.romantrippel.dictionary.parsers.OxfordHtmlParser;
import com.romantrippel.dictionary.repositories.DictionaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExternalSourceService {
    private final OxfordHtmlParser oxfordHtmlParser;
    private final DictionaryRepository repo;

    public int parseOxfordList() {
       List<OxfordWordDto> list = oxfordHtmlParser.parse();


        List<DictionaryRecord> entities = list.stream()
                .map(DictionaryRecord::new)
                .toList();

        List<DictionaryRecord> saved = repo.saveAll(entities);
        return saved.size();
    }
}
