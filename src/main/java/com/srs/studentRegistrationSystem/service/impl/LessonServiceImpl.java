package com.srs.studentRegistrationSystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.srs.studentRegistrationSystem.exception.StudentLessonllegalStateException;
import com.srs.studentRegistrationSystem.model.Lesson;
import com.srs.studentRegistrationSystem.model.Student;
import com.srs.studentRegistrationSystem.repository.ILessonRepository;
import com.srs.studentRegistrationSystem.service.ILessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class LessonServiceImpl implements ILessonService {

	private final static Logger LOG = LoggerFactory.getLogger(LessonServiceImpl.class);
	
     private ILessonRepository lessonRepository;

     @Autowired
	public LessonServiceImpl(ILessonRepository lessonRepository) {
	
		this.lessonRepository = lessonRepository;
	}

	@Override
	public List<Lesson> getAllLessons() {
	
		return lessonRepository.findAll();
	}

	@Override
	public Lesson getLessonById(Long id) {
		
		return lessonRepository.findById(id).get();
	}

	@Override
	public Long addLesson(Lesson lesson) {
		lesson = lessonRepository.save(lesson);
		LOG.info("Lesson: {} has been successfully added. ", lesson.getLessonId());
		return  lesson.getLessonId();
	}

	@Override
	public void removeLesson(Long lessonId) {
		Optional<Lesson> lesson= lessonRepository.findById(lessonId);
		if (!lesson.isPresent()) {
			throw new StudentLessonllegalStateException("Failed to remove Course. Invalid CourseId :: " + lessonId);
		}
		lessonRepository.delete(lesson.get());
		
	}

	@Override
	public Lesson updateLesson(Lesson lesson) {
		
		return lessonRepository.save(lesson);
	}

	@Override
	public void registerStudentToLesson(Long lessonId, Set<Student> students) {
		LOG.info("LessonId :: {} , Student :: {}", lessonId, students);
		Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
		if (!lessonOptional.isPresent()) {
			throw new StudentLessonllegalStateException("Failed to register Student. Invalid LessonId :: " + lessonId);
		}
		Lesson lesson = lessonOptional.get();
		students.addAll(lesson.getStudents());
		lesson.setStudents(students);
		lessonRepository.save(lesson);
		
	}
	@Override
	public void deleteStudentFromLesson(Long lessonId,Set<Student> students) {
		Optional<Lesson> lessonOptional =lessonRepository.findById(lessonId);
		if (!lessonOptional.isPresent()) {
			throw new StudentLessonllegalStateException("Failed to register lesson. Invalid LessonId :: " + lessonId);
		}
		Lesson lesson =lessonOptional.get();
		students.removeAll(lesson.getStudents());
		lesson.setStudents(students);
		lessonRepository.delete(lesson);
		
	}

	@Override
	public Optional<Lesson> getLessonByLessonName(String lessonName) {
		return lessonRepository.findLessonByLessonName(lessonName);
	}
     
     
}
