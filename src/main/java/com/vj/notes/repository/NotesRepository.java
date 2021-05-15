package com.vj.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vj.notes.entity.NotesEntity;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity, Long> {

}
