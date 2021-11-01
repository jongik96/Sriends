package com.project.autonomous.picture.repository;

import com.project.autonomous.picture.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PictureRepository extends JpaRepository<Picture, String> {

}