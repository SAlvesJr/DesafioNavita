package com.SAlvesJr.patrimonio.services.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SAlvesJr.patrimonio.model.entity.Usuario;
import com.SAlvesJr.patrimonio.repositories.UsuarioRepository;
import com.SAlvesJr.patrimonio.security.UserSS;;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private UsuarioRepository usuarioRepository;
	
	public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(user.getId(), user.getEmail(), user.getSenha());
	}

}
