package com.project.autonomous.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Blob;

@Entity
@Getter
@Setter
public class Picture{

    @Id
    @GeneratedValue
    private String id;

    //    Blob data;
    String file_name;
    String file_type;
    String download_uri;




}
