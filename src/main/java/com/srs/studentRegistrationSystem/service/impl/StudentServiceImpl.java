package com.srs.studentRegistrationSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srs.studentRegistrationSystem.exception.StudentLessonllegalStateException;
import com.srs.studentRegistrationSystem.model.Lesson;
import com.srs.studentRegistrationSystem.model.Student;
import com.srs.studentRegistrationSystem.repository.IStudentRepository;
import com.srs.studentRegistrationSystem.service.IStudentService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class StudentServiceImpl  implements  IStudentService{

	private final static Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	private IStudentRepository studentRepository;
	private LessonServiceImpl lessonService;
	
	@Autowired
	public StudentServiceImpl(IStudentRepository studentRepository, LessonServiceImpl lessonService) {
	
		this.studentRepository = studentRepository;
		this.lessonService = lessonService;
	}

	@Override
	public List<Student> getAllStudents() {
		
		return  studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).get();
	}

	@Override
	public Long addStudent(Student student) {
     student= studentRepository.save(student);
		LOG.info("Student {} Successfully added", student.getStudentId());
		return student.getStudentId();
	}

	@Override
	public void removeStudent(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if (!student.isPresent()) {
			throw new StudentLessonllegalStateException("Failed to remove Student. Invalid StudentId :: " + studentId);
		}
		studentRepository.delete(student.get());
		
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Set<Student> getStudentsByLessonName(String lessonName) {
		Optional<Lesson> lesson= lessonService.getLessonByLessonName(lessonName);
		if (!lesson.isPresent()) {
			throw new StudentLessonllegalStateException("Failed to get Students. Invalid lessonName :: " + lessonName);
		}
		Comparator<Student> studentByName = (Student student1, Student student2) -> student1.getStudentName()
				.compareTo(student2.getStudentName());
		TreeSet<Student> sortedStudents = new TreeSet<>(studentByName);

		Set<Student> students = lesson.get().getStudents();
		students.forEach(student -> student.setLessons(null));
		sortedStudents.addAll(students);
		LOG.debug("Actual Students :: {} and Sorted Students by Name:: {}", students, sortedStudents);
		return sortedStudents;
	}
/*
	@Override
	public void registerLessonToStudent(Long studentId, Set<Lesson> lessons) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		if (!studentOptional.isPresent()) {
			throw new StudentLessonllegalStateException("Failed to register lesson. Invalid LessonId :: " + studentId);
		}
		Student student = studentOptional.get();
		lessons.addAll(student.getLessons());
		student.setLessons(lessons);
		studentRepository.save(student);
		
	}

	@Override
	public void deleteLessonFromStudent(Long studentId, Set<Lesson> lessons) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		if (!studentOptional.isPresent()) {
			throw new StudentLessonllegalStateException("Failed to register lesson. Invalid LessonId :: " + studentId);
		}
		Student student = studentOptional.get();
		lessons.removeAll(student.getLessons());
		student.setLessons(lessons);
		studentRepository.delete(student);
		
	}
	
	*/

	

}
