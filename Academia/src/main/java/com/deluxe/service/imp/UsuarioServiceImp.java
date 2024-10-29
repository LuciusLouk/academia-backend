package com.deluxe.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deluxe.entity.Usuario;
import com.deluxe.repository.IUsuarioRepository;
import com.deluxe.service.IUsuarioService;

import jakarta.transaction.Transactional;
@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> getAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario getByID(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public Usuario setObject(Usuario o) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(o);
	}

	@Override
	public void modObject(Usuario o) {
		// TODO Auto-generated method stub
		usuarioRepository.save(o);
	}

	@Override
	public void delObject(Long id) {
		// TODO Auto-generated method stub
		usuarioRepository.delete(getByID(id));
	}

	@Override
	public Usuario getUserLogin(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println(username);
		System.out.println(password);
		return usuarioRepository.findByUsernameAndPassword(username, password);
	}
	@Override
	@Transactional
	public Usuario getUsuarioValidation(String username) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));
	}
}
