package com.srs.studentRegistrationSystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@Column(name = "STUDENT_ID")
	  private Long studentId;
	   
	   @Column(name = "STUDENT_NAME")
		private String studentName;

		@Column(name = "MOBILE_NUMBER")
     	private String mobileNumber;

		@Column(name = "ADDRESS")
		private String address;

		@ManyToMany(fetch = FetchType.LAZY, mappedBy = "students", cascade=CascadeType.ALL)
		private Set<Lesson> lessons=new HashSet<Lesson>();
		
		
		public Long getStudentId() {
			return studentId;
		}


		public void setStudentId(Long studentId) {
			this.studentId = studentId;
		}


		public String getStudentName() {
			return studentName;
		}


		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}


		public String getMobileNumber() {
			return mobileNumber;
		}


		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public Set<Lesson> getLessons() {
			return lessons;
		}


		public void setLessons(Set<Lesson> lessons) {
			this.lessons = lessons;
		}
      

		public Student() {
	
		}


		public Student(String studentName, String mobileNumber, String address, Set<Lesson> lessons) {
			
			this.studentName = studentName;
			this.mobileNumber = mobileNumber;
			this.address = address;
			this.lessons = lessons;
		}


		@Override
		public String toString() {
			return "Student [studentName=" + studentName + ", mobileNumber=" + mobileNumber + ", address=" + address
					+ "]";
		}
		
		
	
}
