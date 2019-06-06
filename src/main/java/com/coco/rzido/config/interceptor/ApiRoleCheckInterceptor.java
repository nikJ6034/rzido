package com.coco.rzido.config.interceptor;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coco.rzido.api.entity.Api;
import com.coco.rzido.api.entity.ApiRole;
import com.coco.rzido.api.entity.ApiRoleKey;
import com.coco.rzido.api.repository.ApiRepository;
import com.coco.rzido.api.repository.ApiRoleRepository;
import com.coco.rzido.auth.dto.AuthDTO;
import com.coco.rzido.config.oAuth2.AuthService;
import com.coco.rzido.role.entity.Role;
import com.coco.rzido.role.repository.RoleRepository;
import com.coco.rzido.util.MethodAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiRoleCheckInterceptor implements HandlerInterceptor{
	
	@Autowired
	ApiRoleRepository apiRoleRepository;
	@Autowired
	ApiRepository apiRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AuthService authService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//		OAuth2Authentication userPrincipal = (OAuth2Authentication)request.getUserPrincipal();
//		JwtAuthenticationToken userPrincipal = (JwtAuthenticationToken)request.getUserPrincipal();
		AuthDTO auth = authService.getAuth();

//		HttpSession session = request.getSession();
//		Optional<SecurityContext> context = Optional.ofNullable((SecurityContext)session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY));
		
		if(! (handler instanceof HandlerMethod)) {
			request.getRequestDispatcher("/api404Error").forward(request, response);
			return false;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 일단은 RequestMapping만 사용하도록
		// 추후 GetMapping, PostMapping 등등도 구현
		Method hMethod = handlerMethod.getMethod();
		RequestMapping annotation = hMethod.getDeclaringClass().getAnnotation(RequestMapping.class); //클래스단의 어노테이션 감지
		String classRMValue = Optional.ofNullable(annotation).map(an->an.value()[0]).orElse("");
		RequestMapping methodAnnotation = handlerMethod.getMethodAnnotation(RequestMapping.class); //메소드 단 어노테이션 감지
		String methodRMValue = Optional.ofNullable(methodAnnotation).map(an->an.value()[0]).orElse("");
		final String method = request.getMethod();
		final String requestURI = classRMValue+methodRMValue; //일단은 리퀘스트 매핑된 값의 첫번째 값으로만 검증
        
//		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//		System.out.println(pathVariables);
		Optional<Api> apiUrl = apiRepository.findByUrl(requestURI);
		
		//api가 등록돠지 않은 경우
		if(!apiUrl.isPresent()) {
			request.getRequestDispatcher("/notFindApi").forward(request, response);
			return false;
		}
		//인증 전 api자체 권한이 있는 경우 뒤에 인증 절차를 실행하지 않는다.
		if(MethodAuth.access(method, apiUrl)) {
			return true;
		}
		
		//로그인을 했을 경우!
		if(auth.isAdmin()){ //관리자인 경우.
			return true;
		}else if(auth.isAuthenticated()) {
			final Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			apiUrl.ifPresent(api->{
				authorities.forEach(item -> {
					GrantedAuthority cga = item;
					Optional<Role> role = roleRepository.findByRoleName(cga.getAuthority());
					ApiRoleKey apiRoleKey = new ApiRoleKey();
					apiRoleKey.setApi(apiUrl.get());
					apiRoleKey.setRole(role.get());
					Optional<ApiRole> memberApiRole = apiRoleRepository.findById(apiRoleKey);
					memberApiRole.ifPresent(ar->{
						if("Y".equals(ar.getReadRole())) apiUrl.ifPresent(a ->a.setReadRole("Y"));
						if("Y".equals(ar.getWriteRole())) apiUrl.ifPresent(a ->a.setWriteRole("Y"));
						if("Y".equals(ar.getModifyRole())) apiUrl.ifPresent(a ->a.setModifyRole("Y"));
						if("Y".equals(ar.getDeleteRole())) apiUrl.ifPresent(a ->a.setDeleteRole("Y"));
					});
				});
			});
		}
		
		//권한이 없을경우
		if(!MethodAuth.access(method, apiUrl)) {
			request.getRequestDispatcher("/apiAuthError").forward(request, response);
			return false;
		}
		
		return true;
	}
	
	
}
