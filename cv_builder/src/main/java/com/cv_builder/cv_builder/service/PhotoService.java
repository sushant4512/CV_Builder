package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.Photo;
import com.cv_builder.cv_builder.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    public Optional<Photo> findById(Long id) {
        return photoRepository.findById(id);
    }

    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    public void deleteById(Long id) {
        photoRepository.deleteById(id);
    }
}