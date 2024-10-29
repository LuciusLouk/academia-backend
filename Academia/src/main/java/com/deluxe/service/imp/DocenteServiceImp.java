package com.deluxe.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deluxe.entity.Docente;
import com.deluxe.repository.IDocenteRepository;
import com.deluxe.service.IDocenteService;
@Service
public class DocenteServiceImp implements IDocenteService {

	@Autowired
	IDocenteRepository docenteRepository;
	
	@Override
	public List<Docente> getAll() {
		// TODO Auto-generated method stub
		return docenteRepository.findAll();
	}

	@Override
	public Docente getByID(Long id) {
		// TODO Auto-generated method stub
		return docenteRepository.findById(id).orElse(null);
	}

	@Override
	public Docente setObject(Docente o) {
		// TODO Auto-generated method stub
		return docenteRepository.save(o);
	}

	@Override
	public void modObject(Docente o) {
		// TODO Auto-generated method stub
		docenteRepository.save(o);
	}

	@Override
	public void delObject(Long id) {
		// TODO Auto-generated method stub
		docenteRepository.delete(getByID(id));
	}

}
