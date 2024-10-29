package com.deluxe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deluxe.entity.Factura;

public interface IFacturaRepository  extends JpaRepository<Factura, Long>{

}
