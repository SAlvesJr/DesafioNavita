package com.SAlvesJr.patrimonio.services.Impl;

import org.springframework.security.core.context.SecurityContextHolder;

import com.SAlvesJr.patrimonio.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
