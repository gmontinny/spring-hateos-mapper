package com.hateoasmapper.controller;

import com.hateoasmapper.dto.Lecture;
import com.hateoasmapper.dto.LectureRef;
import com.hateoasmapper.service.ILectureService;
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
@RequestMapping(value = "/lecture", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class LectureController {

  private final ILectureService lectureService;

  public LectureController(ILectureService lectureService) {
    this.lectureService = lectureService;
  }

  @GetMapping(produces = {"application/hal+json"})
  ResponseEntity<CollectionModel<LectureRef>> getAllLectureRefList() {
    List<LectureRef> lectureRefList = this.lectureService.getAllLectureRefList();

    CollectionModel<LectureRef> lectureRefCollectionModel = null;
    if (CollectionUtils.isNotEmpty(lectureRefList)) {
      final Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LectureController.class).getAllLectureRefList()).withSelfRel();
      lectureRefCollectionModel = CollectionModel.of(lectureRefList, link);
    }

    return Optional.ofNullable(lectureRefCollectionModel)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping(value = "/{lectureId}", produces = {"application/hal+json"})
  ResponseEntity<Lecture> getStudentById(@PathVariable final String lectureId) {
    final Lecture lecture = this.lectureService.getLectureById(lectureId);

    return Optional.ofNullable(lecture)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) {
    return ResponseEntity.ok(this.lectureService.createLecture(lecture));
  }
}
