package com.e.commerce.application.domain.repositories;

import com.e.commerce.application.domain.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Optional<Image> findByImageName(String imageName);
}
