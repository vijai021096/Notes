package com.vj.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.vj.notes.entity.NotesEntity;

@Repository
@CrossOrigin("*")
public interface NotesRepository extends JpaRepository<NotesEntity, Long> {

}
