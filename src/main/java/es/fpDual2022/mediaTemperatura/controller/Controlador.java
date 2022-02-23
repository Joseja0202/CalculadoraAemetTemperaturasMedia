package es.fpDual2022.mediaTemperatura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.fpDual2022.mediaTemperatura.modelo.Dato;
import es.fpDual2022.mediaTemperatura.servicio.Servicio;

@RestController
@RequestMapping("/media")
public class Controlador {

	@Autowired
	private Servicio service;

	@GetMapping("/{codigos}")
	public String getMedia(@PathVariable String codigos) {

		String[] arrayCodigos = codigos.split(",");
		int totalmin = 0;
		int totalmax = 0;
		Integer totalMediaMax = 0;
		Integer totalMediaMin = 0;

		for (int i = 0; i < arrayCodigos.length; i++) {
			Dato dato = service.llamadaAemet(Long.parseLong(arrayCodigos[i]));

			totalmax = service.suma(totalmax, dato.getTemperaturaMaxima());
			totalMediaMax = service.division(totalmax, arrayCodigos.length);

			totalmin = service.suma(totalmin, dato.getTemperaturaMinima());
			totalMediaMin = service.division(totalmin, arrayCodigos.length);

		}

		return "LA MEDIA DE LA TEMPERATURA MAX ==> " + totalMediaMax + " LA MEDIA DE LA TEMPERATURA MINIMA ==> "
				+ totalMediaMin;

	}

}
