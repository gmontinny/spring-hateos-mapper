package com.hateoasmapper.impl;

import com.hateoasmapper.controller.LectureController;
import com.hateoasmapper.dto.Lecture;
import com.hateoasmapper.dto.LectureRef;
import com.hateoasmapper.service.ILectureService;
import com.hateoasmapper.entity.LectureEntity;
import com.hateoasmapper.mapper.LectureMapper;
import com.hateoasmapper.repository.LectureRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LectureServiceImpl implements ILectureService {

  private final LectureRepository lectureRepository;
  private final LectureMapper lectureMapper;

  public LectureServiceImpl(LectureRepository lectureRepository, LectureMapper lectureMapper) {
    this.lectureRepository = lectureRepository;
    this.lectureMapper = lectureMapper;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public List<LectureRef> getAllLectureRefList() {
    List<LectureEntity> lectureEntityList = this.lectureRepository.findAll();

    if (CollectionUtils.isNotEmpty(lectureEntityList)) {
      List<LectureRef> lectureRefList = this.lectureMapper.toDtoRefList(lectureEntityList);

      //adding hateoas links to each object in lectureRefList
      lectureRefList.forEach(lectureRef -> {
        final Link selfLink = WebMvcLinkBuilder.linkTo(LectureController.class).slash(lectureRef.getId()).withSelfRel();
        lectureRef.add(selfLink);
      });

      return  lectureRefList;
    }

    return null;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Lecture getLectureById(String lectureId) {

    Optional<LectureEntity> lectureEntity = Optional.of(this.lectureRepository.findById(lectureId)).orElse(null);

    if (lectureEntity.isPresent()) {
      Lecture lecture = this.lectureMapper.toDto(lectureEntity.get());

      //adding hateoas links to student object
      final Link selfLink = WebMvcLinkBuilder.linkTo(LectureController.class).slash(lecture.getId()).withSelfRel();
      lecture.add(selfLink);

      return lecture;
    }

    return null;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Lecture createLecture(Lecture lecture) {
    LectureEntity lectureEntity = this.lectureMapper.toEntity(lecture);
    lectureEntity = this.lectureRepository.save(lectureEntity);
    return this.lectureMapper.toDto(lectureEntity);
  }
}
