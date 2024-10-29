package com.deluxe.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
/**
 * Independientemente si llama al registro o login, AuthResponse me devuelve un token
 * @author Louk
 *
 */
public class AuthResponse {

	String token;
}
