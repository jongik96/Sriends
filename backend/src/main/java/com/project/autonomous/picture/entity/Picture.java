package com.project.autonomous.picture.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    private String imageUrl;

//    @Lob
//    private byte[] data;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Picture(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Picture(String name, String type, String imageUrl) {
        this.name = name;
        this.type = type;
        this.imageUrl = imageUrl;
    }
}
