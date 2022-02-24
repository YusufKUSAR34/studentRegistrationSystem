package com.srs.studentRegistrationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.srs.studentRegistrationSystem.model.Lesson;
import com.srs.studentRegistrationSystem.model.Student;
import com.srs.studentRegistrationSystem.service.ILessonService;
import com.srs.studentRegistrationSystem.service.IStudentService;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/registration")
public class StudentLessonRegistrationController {

	private final static Logger LOG = LoggerFactory.getLogger(StudentLessonRegistrationController.class);

	private IStudentService studentService;
	private ILessonService lessonService;

	@Autowired
	public StudentLessonRegistrationController( ILessonService lessonService,IStudentService studentService) {
		this.studentService = studentService;
		this.lessonService = lessonService;
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents()
	{
		    return studentService.getAllStudents();
	}
	
	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable Long id)
	{
		    return studentService.getStudentById(id);
	}
	
	@PostMapping("/student")
	public String addStudent(@Valid @RequestBody Student student) {
		LOG.info("Student :: Student Name {}", student.getStudentName());
		studentService.addStudent(student);
		return "Student with Name:" + student.getStudentName() + " has been Added.";
	}
	
	@DeleteMapping("/student/{studentId}")
	public String removeStudent(Long studentId) {
		studentService.removeStudent(studentId);
		return "Student with Id:" + studentId + " has been removed.";
	}
	@PutMapping("/student/{studentId}")
	public String updateStudent(@PathVariable Long studentId,@Valid @RequestBody Student student)
	{
		LOG.info("Student :: Student Name {}", student.getStudentName());
		Student existingStudent=studentService.getStudentById(studentId);
		 existingStudent.setStudentId(studentId);
	    existingStudent.setStudentName(student.getStudentName());
	    existingStudent.setAddress(student.getAddress());
	    existingStudent.setMobileNumber(student.getMobileNumber());
	    existingStudent.setLessons(student.getLessons());
		studentService.updateStudent(existingStudent);
		return "Student with Name:" + existingStudent.getStudentName() + " has been updated.";
	}


	@GetMapping("/lessons")
	public List<Lesson> getAllLessons()
	{
		    return lessonService.getAllLessons();
	}
	@GetMapping("/lessons/{id}")
	public Lesson getLessonById(@PathVariable Long id)
	{
		    return lessonService.getLessonById(id);
	}
	@PostMapping("/lesson")
	public String addLesson(@RequestBody Lesson lesson) {
		LOG.info("Course  ::Course Name {}", lesson.getLessonName());
		lessonService.addLesson(lesson);
		return "Lesson with Name:" + lesson.getLessonName() + " has been Added.";
	}
	@DeleteMapping("/lesson/{lessonId}")
	public String removeLesson(@PathVariable Long lessonId) {
		lessonService.removeLesson(lessonId);
		return "Lesson with Id:" + lessonId + " has been removed.";
	}
	@PutMapping("/registerStudentsToLesson/{lessonId}")
	public String registerStudentsToLesson(@PathVariable Long lessonId,@Valid @RequestBody Set<Student> students) throws MaxUploadSizeExceededException {
		lessonService.registerStudentToLesson(lessonId, students);
		return "Students has been successfully Enrolled to Lesson :: " + lessonId;
	}
	@DeleteMapping("/deleteStudentFromLesson/{lessonId}")
	public String deleteStudentFromLesson(@PathVariable Long lessonId,@RequestBody  Set<Student> students)
	{
		lessonService.deleteStudentFromLesson(lessonId, students);
		return "Students has been successfully deleted from Lesson :: " + lessonId;
	}
	
	@GetMapping("/studentsByLessonName/{lessonName}")
	public Set<Student> getStudentsByLessonName(@PathVariable String lessonName) {
		return studentService.getStudentsByLessonName(lessonName);
	}
	
	
	
	
	
}
