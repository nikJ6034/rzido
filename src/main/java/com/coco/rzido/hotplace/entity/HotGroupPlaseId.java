package com.coco.rzido.hotplace.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * HotGroupPlaseId
 */
@Setter @Getter
public class HotGroupPlaseId implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long hotGroup;
    private Long hotPlace;
    
}