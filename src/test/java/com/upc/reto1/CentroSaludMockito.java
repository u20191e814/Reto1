package com.upc.reto1;

import com.upc.reto1.entidades.CentroSalud;
import com.upc.reto1.entidades.TipoCentroDeSalud;
import com.upc.reto1.negocio.NegocioCentroDeSalud;
import com.upc.reto1.repositorio.RepositorioCentroDeSalud;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
@SpringBootTest
public class CentroSaludMockito {

    @Autowired
    private NegocioCentroDeSalud negocioCentroDeSalud;
    @MockBean
    private RepositorioCentroDeSalud repositorioCentroDeSalud;
    //1)
    @Test
    void RegistrarCentroDeSalud (){

        CentroSalud c = new CentroSalud(1L, "San Juan Bautista", TipoCentroDeSalud.hospital, 60, 50,true);
        when(repositorioCentroDeSalud.save(c)).thenReturn(c);
        Assertions.assertEquals(c, negocioCentroDeSalud.registrar(c));
    }

    //2)
    @Test
    void ListadoDeCentroDeSalud (){
        when(repositorioCentroDeSalud.findAll()).thenReturn(
                Stream.of(
                        new CentroSalud(2L, "San Jose ",TipoCentroDeSalud.hospital, 70,90,true),
                        new CentroSalud(3L, "Santa Rosa", TipoCentroDeSalud.clinica, 25, 50,false),
                        new CentroSalud(4L, "Gobierno regional de Lima",TipoCentroDeSalud.clinica ,25, 50, false)).collect(Collectors.toList())
        );
        Assertions.assertEquals(3, negocioCentroDeSalud.listar().size());
    }
    //3)
    @Test
    void ListadoCentrosDeSaludConCalificacion(){
        when(repositorioCentroDeSalud.findAll()).thenReturn(
                Stream.of(
                        new CentroSalud(2L, "San Jose ",TipoCentroDeSalud.hospital, 70,90,true),
                        new CentroSalud(3L, "Santa Rosa", TipoCentroDeSalud.clinica, 25, 50,false),
                        new CentroSalud(4L, "Gobierno regional de Lima",TipoCentroDeSalud.clinica ,25, 50, false)).collect(Collectors.toList())
        );

        Assertions.assertEquals(83,negocioCentroDeSalud.listarConCalificacion().get(0).getCalificacion(), 0.01 );

    }

    //4)
    @Test
    void listadoCentroDeSaludSegunTipo(){
        when(repositorioCentroDeSalud.findByTipoEquals(TipoCentroDeSalud.hospital)).thenReturn(
                Stream.of(
                        new CentroSalud(2L, "San Jose ",TipoCentroDeSalud.hospital, 70,90,true),
                        new CentroSalud(3L, "Santa Rosa", TipoCentroDeSalud.hospital, 25, 50,false)
                      ).collect(Collectors.toList())
        );
        Assertions.assertEquals(2, negocioCentroDeSalud.listarSegunTipo(TipoCentroDeSalud.hospital).size());

    }
    //5)
    @Test
    void  verificarAprobacionCentroDeSalud(){
        try {

            when(repositorioCentroDeSalud.findById(2L)).thenReturn(
                    Stream.of(
                            new CentroSalud(2L, "San Jose ",TipoCentroDeSalud.hospital, 70,90,true)

                    ).findFirst()
            );
            Assertions.assertEquals("RECHAZADO", negocioCentroDeSalud.verificarAprobacionCentroDeSalud(2L).getEstadoDeCalificacion());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //6)
    @Test
    void actualizarNombreSegunCodigo(){
        try {
            CentroSalud c = new CentroSalud(1L, "San Juan Bautista", TipoCentroDeSalud.hospital, 60, 50,true);
            when(repositorioCentroDeSalud.save(c)).thenReturn(c);
            when(repositorioCentroDeSalud.findById(1L)).thenReturn(Optional.of(c));

            Assertions.assertEquals("San Juan Bautista 2", negocioCentroDeSalud.actualizarNombreSegunCodigo("San Juan Bautista 2", 1L).getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
