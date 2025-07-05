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

    private boolean learned = false;

    public DictionaryRecordDto toDto() {
        return new DictionaryRecordDto(this.id, this.word, this.level, this.translation, this.usAudioUrl, this.pos, this.learned);
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
