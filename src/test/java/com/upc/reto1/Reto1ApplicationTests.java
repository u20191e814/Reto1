package com.upc.reto1;

import com.upc.reto1.entidades.CentroSalud;
import com.upc.reto1.entidades.TipoCentroDeSalud;
import com.upc.reto1.negocio.NegocioCentroDeSalud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class Reto1ApplicationTests {

	@Autowired
	private NegocioCentroDeSalud negocioCentroDeSalud;
	@Test
	void contextLoads() {
	}

	//1)
	@Test
	void registrarCentrosDeSalud(){

		CentroSalud c1 = new CentroSalud("Arcangel", TipoCentroDeSalud.clinica,60,80,true );
		CentroSalud c2 = new CentroSalud("Nazareno", TipoCentroDeSalud.clinica,50,50,false );
		CentroSalud c3 = new CentroSalud("San Juan", TipoCentroDeSalud.hospital,65,85,true );
		CentroSalud c4 = new CentroSalud("San Miguel", TipoCentroDeSalud.hospital,50,40,false );

		negocioCentroDeSalud.registrar(c1);
		negocioCentroDeSalud.registrar(c2);
		negocioCentroDeSalud.registrar(c3);
		negocioCentroDeSalud.registrar(c4);

	}

	//2)
	@Test
	void listarCentrosDeSaludRegistrados (){

		List<CentroSalud> lista = negocioCentroDeSalud.listar();
		for (CentroSalud c : lista){
			System.out.println(c);
		}
	}

	//3)
	@Test
	void listadoCentrosDeSaludConCalificacion(){
		List<CentroSalud> lista = negocioCentroDeSalud.listarConCalificacion();
		for (CentroSalud c : lista){
			System.out.println("Codigo: "+ c.getCodigo() + ", Nombre: "+ c.getNombre() + ", Tipo de centro: "+ c.getTipo() + ", Calificacion de servicio: "+c.getCalificacionServicio() + ", Calificacion de infraestructura: "+ c.getCalificacionInfraestructura() + ", Ambulancia propia: "+ c.isAmbulanciaPropia()+ ", Calificacion: "+ c.getCalificacion() );
		}
	}

	//4)
	@Test
	void listadoCentroDeSaludSegunTipo(){
		List<CentroSalud> lista = negocioCentroDeSalud.listarSegunTipo(TipoCentroDeSalud.hospital);
		for (CentroSalud c : lista){
			System.out.println(c);
		}
	}
	//5)
	@Test
	void  verificarAprobacionCentroDeSalud(){
		try {

			CentroSalud info =negocioCentroDeSalud.verificarAprobacionCentroDeSalud(3L);
			System.out.println(info + ", ESTADO: "+ info	.getEstadoDeCalificacion());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	//6)
	@Test
	void actualizarNombreSegunCodigo(){

		try {
			CentroSalud info = negocioCentroDeSalud.actualizarNombreSegunCodigo("San juan 2", 3L);
			System.out.println(info);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
