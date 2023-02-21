package com.hateoasmapper.service;

import com.hateoasmapper.dto.StudentRef;
import com.hateoasmapper.dto.Student;

import java.util.List;

public interface IStudentService {

  /**
   * get all student refs in the database
   *
   * @return List<StudentRef>
   */
  List<StudentRef> getAllStudentRefList();


  /**
   * get a specific student from the database
   *
   * @param studentId id of the queried student
   * @return Student
   */
  Student getStudentById(String studentId);

  /**
   * create a student instance in the database
   *
   * @param student student to be created
   * @return Student
   */
  Student createStudent(Student student);
}
