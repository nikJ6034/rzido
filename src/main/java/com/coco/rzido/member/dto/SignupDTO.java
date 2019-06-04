package com.coco.rzido.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupDTO {
    private String memberName;
    private String email;
    private String mobile;
    private String kind;
    private String token;
}
