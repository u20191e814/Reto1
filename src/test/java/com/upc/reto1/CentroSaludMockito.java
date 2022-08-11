package com.upc.reto1;

import com.upc.reto1.negocio.NegocioCentroDeSalud;
import com.upc.reto1.repositorio.RepositorioCentroDeSalud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class CentroSaludMockito {

    @Autowired
    private NegocioCentroDeSalud negocioCentroDeSalud;
    @MockBean
    private RepositorioCentroDeSalud repositorioCentroDeSalud;

}
