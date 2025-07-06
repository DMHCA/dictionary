package com.romantrippel.dictionary.entity;

import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import com.romantrippel.dictionary.dto.OxfordWordDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class DictionaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String word;

    @NonNull
    private String level;

    @NonNull
    private String translation;

    private String usAudioUrl;

    private String pos;

    private boolean learned;

    private static final String OXFORD_BASE_URL = "https://www.oxfordlearnersdictionaries.com";

    public DictionaryRecordDto toDto() {
        String fullAudioUrl = (usAudioUrl != null && usAudioUrl.startsWith("/media/"))
                ? OXFORD_BASE_URL + usAudioUrl
                : usAudioUrl;

        return new DictionaryRecordDto(
                this.id,
                this.word,
                this.level,
                this.translation,
                fullAudioUrl,
                this.pos,
                this.learned
        );
    }

    public DictionaryRecord(OxfordWordDto dto) {
        this.word = dto.word();
        this.level = dto.level();
        this.translation = "";
        this.usAudioUrl = dto.usAudioUrl();
        this.pos = dto.pos();
        this.learned = false;
    }
}
