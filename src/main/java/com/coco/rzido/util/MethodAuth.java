package com.coco.rzido.util;

import java.util.Optional;

import com.coco.rzido.api.entity.Api;

public class MethodAuth {
	public static boolean access(String method, Optional<Api> apiRole) {
		Optional<String> role = null;
		if(apiRole.isPresent()) {
			if("GET".equals(method)) {
				role = apiRole.map(Api::getReadRole);
			}else if("POST".equals(method)) {
				role = apiRole.map(Api::getWriteRole);
			}else if("PUT".equals(method)) {
				role = apiRole.map(Api::getModifyRole);
			}else if("DELETE".equals(method)) {
				role = apiRole.map(Api::getDeleteRole);
			}else {
				return false;
			}
			return role.map(r->"Y".equals(r.toUpperCase())).get();
		}else {
			return false;
		}
		
		
	}
}
