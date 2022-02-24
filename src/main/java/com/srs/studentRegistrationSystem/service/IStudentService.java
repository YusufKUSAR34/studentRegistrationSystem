package com.srs.studentRegistrationSystem.service;

import java.util.List;
import java.util.Set;

import com.srs.studentRegistrationSystem.model.Lesson;
import com.srs.studentRegistrationSystem.model.Student;



public interface IStudentService {

	List<Student> getAllStudents();
	
	Student getStudentById(Long id);
	
	Long addStudent(Student student);
	
	void removeStudent(Long studentId);
	
	Student updateStudent(Student student);
	
	Set<Student> getStudentsByLessonName(String lessonName);
	
	//void registerLessonToStudent(Long studentId, Set<Lesson>lessons);
	//void deleteLessonFromStudent(Long studentId, Set<Lesson>lessons);
}
