package com.coco.rzido.attachFile.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * AttachFileImage
 */
@Entity
@DiscriminatorValue("image")
@Getter @Setter
public class AttachFileImage extends AttachFile{

    private String resourcePath;
    private String thumbnailYN = "N";
    @Transient
    private String savedPath;
    private String smallPath;
    private String mediumPath;
    private String largePath;
    
}