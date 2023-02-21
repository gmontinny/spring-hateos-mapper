package com.hateoasmapper.service;

import com.hateoasmapper.dto.Lecture;
import com.hateoasmapper.dto.LectureRef;

import java.util.List;

public interface ILectureService {

  /**
   * get all lecture refs in the database
   *
   * @return List<LectureRef>
   */
  List<LectureRef> getAllLectureRefList();


  /**
   * get a specific lecture from the database
   *
   * @param lectureId id of the queried lecture
   * @return Lecture
   */
  Lecture getLectureById(String lectureId);

  /**
   * create a lecture instance in the database
   *
   * @param lecture lecture to be created
   * @return Lecture
   */
  Lecture createLecture(Lecture lecture);
}
