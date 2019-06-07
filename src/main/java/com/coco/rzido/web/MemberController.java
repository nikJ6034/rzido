package com.coco.rzido.web;

import java.awt.Menu;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.coco.rzido.auth.dto.AuthDTO;
import com.coco.rzido.config.oAuth2.AuthService;
import com.coco.rzido.member.dto.SignupDTO;
import com.coco.rzido.member.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/web")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	AuthService authService;

	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public Map<String, Object> insertUser(@RequestBody SignupDTO signupDTO) {
		Map<String, Object> map = new HashMap<>();

		try{
			memberService.insertMember(signupDTO);
			map.put("result", "success");
			map.put("msg", "회원가입이 완료되었습니다.");
		}catch (Exception e){
			map.put("result", "fail");
			map.put("msg", "회원가입에 실패하였습니다.");
		}

		return map;

	}
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public Map<String, Object> memberInfo(Principal principal, HttpSession session, HttpServletRequest request, String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		/* MenuRole menuRole = new MenuRole();
		Menu menu = menuService.getMenuByUrl(url); */
		AuthDTO auth = authService.getAuth();
		/* if(auth.isAdmin()){
			menuRole.setDeleteRole("Y");
			menuRole.setModifyRole("Y");
			menuRole.setReadRole("Y");
			menuRole.setWriteRole("Y");
		}else{
			menuRole = menuRoleService.getMenuRole(auth.getAuthorities(), menu);
		} */
		map.put("memberName", auth.getMemberName());
		map.put("name", auth.getName());
		map.put("id", auth.getMemberId());
		map.put("isAdmin", auth.isAdmin());
		// map.put("menuRole", menuRole);
		return map;
	}

	@RequestMapping(value = "/existsMember", method = RequestMethod.GET)
	public Map<String, Object> existsMember(String memberName){
		Map<String, Object> map = new HashMap<>();

		if(memberService.existsMember(memberName) > 0){
			map.put("result","exists");
			map.put("msg","이미 등록된 아이디입니다.");
		}else{
			map.put("result","success");
			map.put("msg","등록 가능한 아이디입니다.");
		}

		return map;
	}
}
