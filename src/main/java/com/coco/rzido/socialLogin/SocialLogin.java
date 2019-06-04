package com.coco.rzido.socialLogin;

import com.coco.rzido.social.dto.TokenDTO;

public class SocialLogin {
	public static ISocialAuth create(TokenDTO tokenDTO) {
		if("naver".equals(tokenDTO.getKind())) {
//			return new Naver(tokenDTO.getToken());
			return null;
		}else if("kakao".equals(tokenDTO.getKind())) {
			return new Kakao(tokenDTO.getToken());
		}else if("facebook".equals(tokenDTO.getKind())) {
//			return new FaceBook(tokenDTO.getToken());
			return null;
		}else if("google".equals(tokenDTO.getKind())) {
//			return  new Google(tokenDTO.getToken());
			return null;
		}else {
			return null;
		}
	}
}
