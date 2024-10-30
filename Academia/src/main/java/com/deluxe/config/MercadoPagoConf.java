package com.deluxe.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mercadopago.MercadoPagoConfig;

//@Configuration
public class MercadoPagoConf {
	private String mercadoPagoToken = "APP_USR-4973661379136227-070802-76dae679c5b74b901b687930b8eaa869-1892538886";
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
