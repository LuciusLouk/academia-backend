package com.deluxe.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deluxe.entity.Factura;
import com.deluxe.repository.IFacturaRepository;
import com.deluxe.service.IFacturaService;
@Service
public class FacturaServiceImp implements IFacturaService {
	
	@Autowired
	IFacturaRepository facturaRepository;
	
	@Override
	public List<Factura> getAll() {
		// TODO Auto-generated method stub
		return facturaRepository.findAll();
	}

	@Override
	public Factura getByID(Long id) {
		// TODO Auto-generated method stub
		return facturaRepository.findById(id).orElse(null);
	}

	@Override
	public Factura setObject(Factura o) {
		// TODO Auto-generated method stub
		return facturaRepository.save(o);
	}

	@Override
	public void modObject(Factura o) {
		// TODO Auto-generated method stub
		facturaRepository.save(o);
	}

	@Override
	public void delObject(Long id) {
		// TODO Auto-generated method stub
		facturaRepository.delete(getByID(id));
	}

}
