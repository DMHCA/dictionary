package com.romantrippel.dictionary.dto;

import com.romantrippel.dictionary.entity.DictionaryRecord;
import jakarta.validation.constraints.NotBlank;

public record DictionaryRecordDto(Long id,
                                  @NotBlank(message = "Word must not be blank") String word,
                                  String translation) {

    public DictionaryRecord toEntity() {
        return new DictionaryRecord(id, word, translation);
    }
}
