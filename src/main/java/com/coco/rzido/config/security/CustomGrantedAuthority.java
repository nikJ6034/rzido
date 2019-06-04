package com.coco.rzido.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

public final class CustomGrantedAuthority implements GrantedAuthority{

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	private final long id;
	private final String role;
	
	public CustomGrantedAuthority(long id, String role) {
		Assert.hasText(role, "A granted authority textual representation is required");
		this.id = id;
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role;
	}

	public long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof CustomGrantedAuthority) {
			return role.equals(((CustomGrantedAuthority) obj).role);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.role.hashCode();
	}

	@Override
	public String toString() {
		return this.role;
	}
	

}
