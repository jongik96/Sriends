package com.project.autonomous.picture.repository;

import com.project.autonomous.picture.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, String> {

}