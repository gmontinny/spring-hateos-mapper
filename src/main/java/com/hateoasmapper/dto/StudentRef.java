package com.hateoasmapper.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class StudentRef extends RepresentationModel<StudentRef> implements Serializable {
  private String id;
}
