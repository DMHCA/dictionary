package com.romantrippel.dictionary.dto;

public record OxfordWordDto(
        String word,
        String level,
        String pos,
        String usAudioUrl
) { }
