package com.romantrippel.dictionary.dto;

import com.romantrippel.dictionary.entity.DictionaryRecord;
import jakarta.validation.constraints.NotBlank;

public record DictionaryRecordDto(Long id,
                                  @NotBlank(message = "Word must not be blank") String word,
                                  String level,
                                  String translation,
                                  String usAudioUrl,
                                  String pos,
                                  boolean learned
                                  ) {

    public DictionaryRecord toEntity() {
        return new DictionaryRecord(id, level, word, translation, usAudioUrl, pos, learned);
    }
}
