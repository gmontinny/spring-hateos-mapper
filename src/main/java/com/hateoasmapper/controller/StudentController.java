package com.hateoasmapper.controller;

import com.hateoasmapper.dto.Student;
import com.hateoasmapper.dto.StudentRef;
import com.hateoasmapper.service.IStudentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class StudentController {

  private final IStudentService studentService;

  public StudentController(IStudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping(produces = {"application/hal+json"})
  ResponseEntity<CollectionModel<StudentRef>> getAllStudentRefList() {
    List<StudentRef> studentRefList = this.studentService.getAllStudentRefList();

    CollectionModel<StudentRef> studentRefCollectionModel = null;
    if (CollectionUtils.isNotEmpty(studentRefList)) {
      final Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).getAllStudentRefList()).withSelfRel();
      studentRefCollectionModel = CollectionModel.of(studentRefList, link);
    }

    return Optional.ofNullable(studentRefCollectionModel)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping(value = "/{studentId}", produces = {"application/hal+json"})
  ResponseEntity<Student> getStudentById(@PathVariable final String studentId) {
    final Student student = this.studentService.getStudentById(studentId);

    return Optional.ofNullable(student)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  ResponseEntity<Student> createStudent(@RequestBody Student student) {
    return ResponseEntity.ok(this.studentService.createStudent(student));
  }
}
