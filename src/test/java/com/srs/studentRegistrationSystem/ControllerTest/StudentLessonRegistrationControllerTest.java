package com.srs.studentRegistrationSystem.ControllerTest;

import org.mockito.InjectMocks;

import java.util.Collections;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.srs.studentRegistrationSystem.controller.StudentLessonRegistrationController;
import com.srs.studentRegistrationSystem.model.Lesson;
import com.srs.studentRegistrationSystem.model.Student;
import com.srs.studentRegistrationSystem.service.ILessonService;
import com.srs.studentRegistrationSystem.service.IStudentService;
import com.srs.studentRegistrationSystem.service.impl.LessonServiceImpl;
import com.srs.studentRegistrationSystem.service.impl.StudentServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class StudentLessonRegistrationControllerTest {

	private final static Logger LOG = LoggerFactory.getLogger(StudentLessonRegistrationController.class);

	@Mock
	private StudentServiceImpl studentServiceMock;
	@Mock
	private LessonServiceImpl lessonServiceMock;

	@InjectMocks
	private StudentLessonRegistrationController studentLessonRegistrationController;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddStudent() {
		Student student = new Student();
		student.setStudentId(1l);
		studentLessonRegistrationController.addStudent(student);
	}

	@Test
	public void testRemoveStudent() {
		Long studentId = 1l;
		studentLessonRegistrationController.removeStudent(studentId);
	}

	@Test
	public void testAddLesson() {
		Lesson lesson = new Lesson();
		lesson.setLessonId(1l);
		LOG.info("Course  ::Course Name {}", lesson.getLessonName());
		studentLessonRegistrationController.addLesson(lesson);
	}

	@Test
	public void testRemoveLesson() {
		Long lessonId = 1l;
		studentLessonRegistrationController.removeLesson(lessonId);
	}

	@Test
	public void testRegisterStudentToCourse() {
		Long lessonId = 1l;
		Set<Student> students = Collections.emptySet();
		studentLessonRegistrationController.registerStudentsToLesson(lessonId, students);
	}

	@Test
	public void testGetStudentsByLessonName() {
		String lessonName = "DevOps";
		studentLessonRegistrationController.getStudentsByLessonName(lessonName);
	}
}
