package com.coco.rzido.attachFile.service;

import java.util.Optional;

import com.coco.rzido.attachFile.entity.AttachFileImage;
import com.coco.rzido.attachFile.repository.AttachFileImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileService
 */
@Service
public class FileService {
    
    @Autowired
    AttachFileImageRepository attachFileImageRepository;
    
    public Optional<AttachFileImage> one(long id){
        return attachFileImageRepository.findById(id);
    }
    
}