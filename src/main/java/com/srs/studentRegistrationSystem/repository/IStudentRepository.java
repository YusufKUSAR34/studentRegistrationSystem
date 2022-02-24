package com.srs.studentRegistrationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srs.studentRegistrationSystem.model.Student;

@Repository
public interface IStudentRepository  extends   JpaRepository<Student,Long> {

}
