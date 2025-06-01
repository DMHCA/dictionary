package com.romantrippel.dictionary.entity;

import com.romantrippel.dictionary.dto.DictionaryRecordDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
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
    private String translation;

    public DictionaryRecordDto toDto() {
        return new DictionaryRecordDto(this.word, this.translation);
    }
}
