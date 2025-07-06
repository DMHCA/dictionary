package com.romantrippel.dictionary.repositories;

import com.romantrippel.dictionary.entity.DictionaryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryRepository extends JpaRepository<DictionaryRecord, Long> {
}
