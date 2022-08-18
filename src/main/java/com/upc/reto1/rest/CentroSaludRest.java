package com.upc.reto1.rest;

import com.upc.reto1.entidades.CentroSalud;
import com.upc.reto1.entidades.InputUpdate;
import com.upc.reto1.entidades.TipoCentroDeSalud;
import com.upc.reto1.negocio.NegocioCentroDeSalud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PostUpdate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CentroSaludRest {
    @Autowired
    private NegocioCentroDeSalud centroDeSalud;
    @PostMapping("/centroDeSalud")
    public CentroSalud registrar(@RequestBody CentroSalud centroSalud)
    {
        return  centroDeSalud.registrar(centroSalud);
    }

    @GetMapping("/centrosDeSalud")
    public List<CentroSalud> lista()
    {
        return  centroDeSalud.listar();
    }
    @GetMapping("/centrosDeSaludConCalificacion")
    public List<CentroSalud> listaConCalificacion()
    {
        return  centroDeSalud.listarConCalificacion();
    }
    @GetMapping("/centrosDeSaludSegunTipo/{tipoCentroDeSalud}")
    public List<CentroSalud> centrosDeSaludSegunTipo(@PathVariable(value="tipoCentroDeSalud")TipoCentroDeSalud tipoCentroDeSalud)
    {
        return  centroDeSalud.listarSegunTipo(tipoCentroDeSalud);
    }
    @GetMapping("/verificarAprobacionCentroDeSalud/{codigo}")
    public CentroSalud verificarAprobacionCentroDeSalud(@PathVariable(value="codigo")Long codigo)
    {
        try {
            return  centroDeSalud.verificarAprobacionCentroDeSalud(codigo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error ocurrido en la aprobacion de salud", e);
        }
    }


    @PostMapping("/actualizarCentroDeSalud")
    public CentroSalud modificar(@RequestBody InputUpdate inputUpdate)
    {
        try {
            return  centroDeSalud.actualizarNombreSegunCodigo(inputUpdate.getNombre(), inputUpdate.getCodigo());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error ocurrido en modificar del centro de salud", e);

        }
    }



}
