package com.upc.reto1.repositorio;

import com.upc.reto1.entidades.CentroSalud;
import com.upc.reto1.entidades.TipoCentroDeSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioCentroDeSalud extends JpaRepository<CentroSalud,Long> {

    public List<CentroSalud> findByTipoEquals(TipoCentroDeSalud tipoCentroDeSalud);
    @Modifying
    @Query("update CentroSalud  c set c.nombre =?1 where c.codigo =?2")
    public CentroSalud updateNameById (String nombre, Long codigo);
}
