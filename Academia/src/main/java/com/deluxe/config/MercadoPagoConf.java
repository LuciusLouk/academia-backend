package com.deluxe.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mercadopago.MercadoPagoConfig;

//@Configuration
public class MercadoPagoConf {
	@Value("${MP_ACCESS_TOKEN}")
	private String mercadoPagoToken;
	//@Bean
    public void setupMercadoPago() {
        MercadoPagoConfig.setAccessToken(mercadoPagoToken); // Cambia con tu token de acceso
    }
	
	
	/*
	 * 
	 * 
	 * public MercadoPagoService() {
        try {
        	MercadoPagoConfig.setAccessToken("PROD_ACCESS_TOKEN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	 */
}
