package com.romantrippel.dictionary.repositories;

import com.romantrippel.dictionary.entity.DictionaryRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DictionaryRepository extends JpaRepository<DictionaryRecord, Long> {
    Optional<DictionaryRecord> findByWord(String word);

    @Query("SELECT d FROM DictionaryRecord d WHERE LOWER(d.word) LIKE LOWER(CONCAT('%', :word, '%'))")
    Page<DictionaryRecord> searchByWord(@Param("word") String word, Pageable pageable);
}

