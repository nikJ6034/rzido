package com.coco.rzido.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiErrorController implements ErrorController{
	
	@RequestMapping("/apiError")
	@ResponseBody
	public Map<String, Object> error() {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("error", "Error");
		map.put("msg", "에러");
		
		
		return map;
	}
	
	@RequestMapping("/apiAuthError")
	@ResponseBody
	public Map<String, Object> authError() {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("error", "AuthError");
		map.put("msg", "접근 권한이 없습니다. 관리자에게 문의하세요.");
		
		
		return map;
	}

	@RequestMapping("/notFindApi")
	@ResponseBody
	public Map<String, Object> notFindApi() {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("error", "notFindApi");
		map.put("msg", "등록되지 않은 api입니다.");


		return map;
	}
	
	@RequestMapping("/api404Error")
	@ResponseBody
	public Map<String, Object> api404Error() {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("error", "404에러");
		map.put("msg", "페이지를 찾을 수 없습니다.");
		
		
		return map;
	}


	// 아래 2개 메소드는 싱글페이지 웹에서 새로고침 할 경우 404대신에 vue라우터에 등록되어있는 페이지를 다시 로드하기 위한 코드.
	@RequestMapping("/error")
    public String notFound() {
        return "forward:/";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
