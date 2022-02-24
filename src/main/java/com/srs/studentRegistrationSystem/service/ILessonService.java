package com.srs.studentRegistrationSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.srs.studentRegistrationSystem.model.Lesson;
import com.srs.studentRegistrationSystem.model.Student;



public interface ILessonService {

	List<Lesson> getAllLessons();
	Lesson getLessonById(Long id);
	Long addLesson(Lesson lesson);
	void removeLesson(Long lessonId);
	Lesson updateLesson(Lesson lesson);
	
	void registerStudentToLesson(Long lessonId, Set<Student> students);
	void deleteStudentFromLesson(Long lessonId, Set<Student> students);
	Optional<Lesson> getLessonByLessonName(String lessonName);
}
