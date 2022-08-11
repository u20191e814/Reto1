package com.upc.reto1.negocio;

import com.upc.reto1.entidades.CentroSalud;
import com.upc.reto1.entidades.TipoCentroDeSalud;
import com.upc.reto1.repositorio.RepositorioCentroDeSalud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NegocioCentroDeSalud {
    @Autowired
    private RepositorioCentroDeSalud repositorioCentroDeSalud;

    public CentroSalud registrar(CentroSalud centroSalud){
        return repositorioCentroDeSalud.save(centroSalud);
    }

    public List<CentroSalud> listar(){
        return  repositorioCentroDeSalud.findAll();
    }
    public List<CentroSalud> listarConCalificacion(){
        List<CentroSalud> lista = listar();
        for (CentroSalud centroSalud: lista){
            centroSalud.setCalificacion(calcularCalificacion(centroSalud));
        }
        return lista;
    }

    public List<CentroSalud> listarSegunTipo (TipoCentroDeSalud tipoCentroDeSalud){
        return  repositorioCentroDeSalud.findByTipoEquals(tipoCentroDeSalud);
    }

    public double calcularCalificacion(CentroSalud centroSalud){
        return centroSalud.getCalificacionInfraestructura() *0.35 + centroSalud.getCalificacionServicio()*0.65;

    }
    public CentroSalud buscar (Long codigo)throws Exception{
        return repositorioCentroDeSalud.findById(codigo).orElseThrow(
                ()-> new Exception("No se encontró una entidad con ese id"));
    }
    public CentroSalud verificarAprobacionCentroDeSalud(Long codigo) throws Exception{

        return calcularAprobacion(buscar(codigo));
    }

    public CentroSalud actualizarNombreSegunCodigo(String nombre, Long codigo) throws Exception {

        CentroSalud s = buscar(codigo);
        s.setNombre(nombre);
        return  repositorioCentroDeSalud.save(s);


    }

    public CentroSalud calcularAprobacion (CentroSalud centroSalud){
        if (calcularCalificacion(centroSalud)>=80){
            centroSalud.setEstadoDeCalificacion("APROBADO");
        }
        centroSalud.setEstadoDeCalificacion("RECHAZADO");
        return  centroSalud;

    }

}
