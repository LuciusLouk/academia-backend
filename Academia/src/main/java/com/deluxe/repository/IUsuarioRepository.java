package com.deluxe.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deluxe.entity.Usuario;


public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u WHERE u.nombreUsuario= :username AND u.password =:password")
    public Usuario findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);



	/**
	 * Obtiene un usuario en base al nombre de usuario
	 * usado para en el registro de usuario
	 * @param usuario
	 * @return objeto de tipo usuario
	 */
	@Query("SELECT u FROM Usuario u WHERE u.nombreUsuario= :username")
	public Optional<Usuario> findByUsername(@Param("username") String username);
}
