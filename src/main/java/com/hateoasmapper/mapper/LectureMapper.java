package com.hateoasmapper.mapper;

import com.hateoasmapper.dto.Lecture;
import com.hateoasmapper.dto.LectureRef;
import com.hateoasmapper.entity.LectureEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface LectureMapper {

  LectureMapper INSTANCE = Mappers.getMapper(LectureMapper.class);

  @Mappings({
      @Mapping(target = "id", ignore = true)
  })
  LectureEntity toEntity(Lecture lecture);

  Lecture toDto(LectureEntity lectureEntity);

  List<LectureEntity> toEntityList(List<Lecture> lectureList);

  List<Lecture> toDtoList(List<LectureEntity> lectureEntityList);

  @BeanMapping(ignoreUnmappedSourceProperties = {"name", "passGrade"})
  LectureRef toDtoRef(LectureEntity lectureEntity);

  List<LectureRef> toDtoRefList(List<LectureEntity> lectureEntityList);

  @AfterMapping
  default void setEntityId(Lecture lecture, @MappingTarget LectureEntity lectureEntity) {
    if(Objects.nonNull(lecture.getId()))
      lectureEntity.setId(lecture.getId());
  }

}
