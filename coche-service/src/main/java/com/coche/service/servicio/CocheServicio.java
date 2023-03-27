package com.coche.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coche.service.entidades.Coche;
import com.coche.service.repositorio.CocheRepository;

@Service
public class CocheServicio {
	
	@Autowired
	private CocheRepository repositorio;
	
	public List<Coche> getAll(){
		return repositorio.findAll();
	}
	
	public Coche getCocheById(int id){
		return repositorio.findById(id).orElse(null);
	}
	
	public Coche save(Coche car) {
		return repositorio.save(car);
	}
	
	public List<Coche> getbyUsuarioId(int usuarioId){
		return repositorio.findByUsuarioId(usuarioId);
	}
}
