package com.e.commerce.application.domain.entities;

import com.e.commerce.application.domain.utils.common.RandomAnyString;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Images")
public class Image {
    @Id
    @Column(name = "image_id", updatable = false)
    private String imageId;
    @Column(name = "image_path_id", nullable = false)
    private String imagePathId;
    @Column(name = "image_name", nullable = false)
    private String imageName;
    @Column(name = "image_path", nullable = false)
    private String imagePath;
    @Column(name = "time_upload", nullable = false)
    private LocalDate timeUpload;

    @ManyToOne
    @JoinColumn(name = "typeId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Type type;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    public Image() {
        this.imageId = "image-"+ RandomAnyString.generateUUIDString(10);
        this.timeUpload = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Image(String pathId, String name, String path) {
        this.imageId = "image-"+ RandomAnyString.generateUUIDString(10);
        this.imagePathId = pathId;
        this.imageName = name;
        this.imagePath = path;
        this.timeUpload = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}
