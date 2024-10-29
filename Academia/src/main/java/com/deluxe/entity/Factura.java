package com.deluxe.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Table(name="FACTURAS")
@Data @AllArgsConstructor @NoArgsConstructor
public class Factura {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name="fac_id")
	private Long id;
	
	@Column(name="fac_email")
	private String email;

	@Column(name="fac_pais")
	private String pais;

	@Column(name="fac_apellido")
	private String apellido;

	@Column(name="fac_nombre")
	private String nombre;

	@Column(name="fac_monto")
	private double monto;

	@Column(name="fac_fecha_hora_pago")
	private LocalDate fechaHoraPago;

	@Column(name="fac_nombre_curso")
	private String nombreCurso;
}
