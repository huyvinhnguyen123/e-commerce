package com.e.commerce.application.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Files")
public class File {
    @Id
    @Column(name = "file_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "file_size", nullable = false)
    private Long fileSize;
    @Column(name = "link_download", nullable = false)
    private String linkDownload;
    @Column(name = "date_post", nullable = false)
    private LocalDate datePost;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Type type;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    public File() {
        this.datePost = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public File(String name, Long size, String linkDownload) {
        this.fileName = name;
        this.fileSize = size;
        this.linkDownload = linkDownload;
        this.datePost = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

}
