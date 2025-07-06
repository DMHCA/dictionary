package com.romantrippel.dictionary.dto;

import jakarta.validation.constraints.NotBlank;

public record DictionaryRecordDto(Long id,
                                  @NotBlank(message = "Word must not be blank") String word,
                                  String level,
                                  String translation,
                                  String usAudioUrl,
                                  String pos,
                                  boolean learned
                                  ) {
}
