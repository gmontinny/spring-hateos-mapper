package com.hateoasmapper.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Student extends RepresentationModel<Student> implements Serializable {
  private String id;
  private String name;
  private List<Lecture> lectureList;
}
