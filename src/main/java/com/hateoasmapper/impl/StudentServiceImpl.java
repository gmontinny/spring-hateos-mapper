package com.hateoasmapper.impl;

import com.hateoasmapper.controller.LectureController;
import com.hateoasmapper.controller.StudentController;
import com.hateoasmapper.dto.Student;
import com.hateoasmapper.dto.StudentRef;
import com.hateoasmapper.mapper.StudentMapper;
import com.hateoasmapper.service.IStudentService;
import com.hateoasmapper.entity.StudentEntity;
import com.hateoasmapper.repository.StudentRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;

  public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public List<StudentRef> getAllStudentRefList() {
    List<StudentEntity> studentEntityList = this.studentRepository.findAll();

    if (CollectionUtils.isNotEmpty(studentEntityList)) {
      List<StudentRef> studentRefList = this.studentMapper.toDtoRefList(studentEntityList);

      //adding hateoas links to each object in studentRefList
      studentRefList.forEach(studentRef -> {
        final Link selfLink = WebMvcLinkBuilder.linkTo(StudentController.class).slash(studentRef.getId()).withSelfRel();
        studentRef.add(selfLink);
      });

      return  studentRefList;
    }

    return null;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Student getStudentById(String studentId) {

    Optional<StudentEntity> studentEntity = Optional.of(this.studentRepository.findById(studentId)).orElse(null);

    if (studentEntity.isPresent()) {
      Student student = this.studentMapper.toDto(studentEntity.get());

      //adding hateoas links to student object
      final Link selfLink = WebMvcLinkBuilder.linkTo(StudentController.class).slash(student.getId()).withSelfRel();
      student.add(selfLink);

      student.getLectureList().forEach(lectureRef -> {
        final Link selfLectureLink = WebMvcLinkBuilder.linkTo(LectureController.class).slash(lectureRef.getId()).withSelfRel();
        lectureRef.add(selfLectureLink);
      });

      return student;
    }

    return null;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Student createStudent(Student student) {
    StudentEntity studentEntity = this.studentMapper.toEntity(student);
    studentEntity = this.studentRepository.save(studentEntity);
    return this.studentMapper.toDto(studentEntity);
  }
}
