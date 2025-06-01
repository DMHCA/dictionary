package com.romantrippel.dictionary.dto;

import com.romantrippel.dictionary.entity.DictionaryRecord;

public record DictionaryRecordDto(String word, String translation) {

    public DictionaryRecord toEntity() {
        return new DictionaryRecord(word, translation);
    }
}
