package com.srs.studentRegistrationSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="Lesson")
public class Lesson {
        
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LESSON_ID")
	private Long lessonId;

	@Column(name = "LESSON_NAME")
	  private String lessonName;
	
	
	 @Max(value = 20, message = "The maximum number of students should be 20")
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = Student.class)
	private Set students=new HashSet();


	   
	public Long getLessonId() {
		return lessonId;
	}


	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}


	public String getLessonName() {
		return lessonName;
	}


	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}


	public Set<Student> getStudents() {
		return students;
	}


	public void setStudents(Set<Student> students) {
		this.students = students;
	}

   
	public Lesson() {
	
	}


	public Lesson(String lessonName, Set<Student> students) {

		this.lessonName = lessonName;
		this.students = students;
	}


	@Override
	public String toString() {
		return "Lesson [lessonName=" + lessonName + "]";
	}

	
}
