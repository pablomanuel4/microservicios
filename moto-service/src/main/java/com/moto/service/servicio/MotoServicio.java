package com.moto.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entidades.Moto;
import com.moto.service.repositorio.MotoRepository;

@Service
public class MotoServicio {
	
	@Autowired
	private MotoRepository repositorio;
	
	public List<Moto> getAll(){
		return repositorio.findAll();
	}
	
	public Moto getCocheById(int id){
		return repositorio.findById(id).orElse(null);
	}
	
	public Moto save(Moto moto) {
		return repositorio.save(moto);
	}
	
	public List<Moto> getbyUsuarioId(int usuarioId){
		return repositorio.findByUsuarioId(usuarioId);
	}

}
