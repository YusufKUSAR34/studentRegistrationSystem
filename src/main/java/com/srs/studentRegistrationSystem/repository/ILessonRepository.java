package com.srs.studentRegistrationSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srs.studentRegistrationSystem.model.Lesson;

@Repository
public interface ILessonRepository  extends JpaRepository<Lesson, Long>{
	public Optional<Lesson> findLessonByLessonName(String lessonName);
}
