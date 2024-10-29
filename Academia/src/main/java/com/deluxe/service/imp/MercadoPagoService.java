package com.deluxe.service.imp;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.stereotype.Service;
@Service
public class MercadoPagoService {
	

	public MercadoPagoService() {
        try {
        	MercadoPagoConfig.setAccessToken("APP_USR-4973661379136227-070802-76dae679c5b74b901b687930b8eaa869-1892538886");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public Preference createPreference(String title, String description, BigDecimal price, int quantity, Long userId, Long courseId) throws Exception {

        // Crear ítem
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .title(title)
                .description(description)
                .quantity(quantity)
                .currencyId("ARS") // Cambia a la moneda que estés usando
                .unitPrice(price)
                .build();

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        PreferenceBackUrlsRequest backUrls =
        		// ...
        		   PreferenceBackUrlsRequest.builder()
        		       .success("localhost:4200/app-checkout/"+courseId)
        		       .pending("https://www.seu-site/pending")
        		       .failure("https://www.seu-site/failure")
        		       .build();

        // Crear la preferencia
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .externalReference("userId:" + userId + ",courseId:" + courseId)  // Añadir userId y courseId al external_reference
                .backUrls(backUrls)
                //.notificationUrl("localhost:8080/api/payment/webhook") // Aquí defines la URL del webhook
                .build();

        // Llamar a la API de Mercado Pago
        PreferenceClient client = new PreferenceClient();
        return client.create(preferenceRequest);
    }

	/* borrar
    public Preference createPreference() {
    	PreferenceItemRequest itemRequest =
    		       PreferenceItemRequest.builder()
    		           .id("1234")
    		           .title("Games")
    		           .description("PS5")
    		           .pictureUrl("http://picture.com/PS5")
    		           .categoryId("games")
    		           .quantity(2)
    		           .currencyId("BRL")
    		           .unitPrice(new BigDecimal("4000"))
    		           .build();
    		   List<PreferenceItemRequest> items = new ArrayList<>();
    		   items.add(itemRequest);
    		PreferenceRequest preferenceRequest = PreferenceRequest.builder()
    		.items(items).build();
    		PreferenceClient client = new PreferenceClient();
    		Preference preference = new Preference();
    		try {
        		 preference = client.create(preferenceRequest);
			} catch (Exception e) {
				// TODO: handle exception
			}
        return preference;
    }
    */
}
