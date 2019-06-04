package com.coco.rzido.attachFile.service;

import java.util.Optional;

import com.coco.rzido.attachFile.entity.AttachFile;
import com.coco.rzido.attachFile.repository.AttachFileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileService
 */
@Service
public class FileService {
    
    @Autowired
    AttachFileRepository attachFileRepository;
    
    public Optional<AttachFile> one(long id){
        return attachFileRepository.findById(id);
    }
    
}