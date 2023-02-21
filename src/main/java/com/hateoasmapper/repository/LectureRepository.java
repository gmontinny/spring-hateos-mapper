package com.hateoasmapper.repository;

import com.hateoasmapper.entity.LectureEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends MongoRepository<LectureEntity, String> {
}
