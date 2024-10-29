package com.deluxe.service;

import com.deluxe.entity.Usuario;

public interface IUsuarioService extends IGenericCRUDService<Usuario>{
	public Usuario getUserLogin(String username,String password);
	public Usuario getUsuarioValidation(String username);
}
