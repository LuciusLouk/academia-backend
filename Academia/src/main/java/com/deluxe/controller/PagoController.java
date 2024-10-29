package com.deluxe.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deluxe.service.IAlumnoService;
import com.deluxe.service.imp.MercadoPagoService;
import com.mercadopago.resources.preference.Preference;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:4200") // Permitir el frontend local
public class PagoController {
	
	
	 @Autowired
	 private MercadoPagoService mercadoPagoService;
	 
	 @Autowired
	 private IAlumnoService alumnoServiceImp;

	 
	 
    @PostMapping("/create")
    public ResponseEntity<Preference> createPayment(@RequestParam String title,
										    		@RequestParam String description, 
										    		@RequestParam BigDecimal price, 
										    		@RequestParam int quantity,
										    		@RequestParam Long userId, 
										    		@RequestParam Long courseId) 
    {
        try {
            Preference preference = mercadoPagoService.createPreference(title, description, price, quantity,userId,courseId);
            return ResponseEntity.ok(preference);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    

    @PostMapping("/webhook")
    public ResponseEntity<Void> handleWebhook(@RequestBody Map<String, Object> payload) {
        try {
            // Obtener el estado del pago desde el payload
            String paymentStatus = (String) payload.get("status");

            // Extraer el external_reference para obtener los IDs de usuario y curso
            String externalReference = (String) payload.get("external_reference");

            if (externalReference != null && paymentStatus != null) {
                // Procesar los datos de external_reference (userId y courseId)
                String[] parts = externalReference.split(",");
                Long userId = Long.parseLong(parts[0].split(":")[1]);
                Long courseId = Long.parseLong(parts[1].split(":")[1]);

                // Verificar el estado del pago
                if ("approved".equals(paymentStatus)) {
                    // Solo registrar el curso si el pago fue aprobado
                	alumnoServiceImp.registrarAlumnoCurso(userId, courseId);
                } else if ("rejected".equals(paymentStatus)) {
                    // Manejar pagos rechazados (si es necesario)
                } else if ("pending".equals(paymentStatus)) {
                    // Manejar pagos pendientes (si es necesario)
                }

                // Confirmar la recepción de la notificación
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.status(400).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    

}
