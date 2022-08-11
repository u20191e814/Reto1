package com.upc.reto1.entidades;

import javax.persistence.*;

@Entity
@Table(name = "TB_CENTROSALUD")
public class CentroSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nombre ;
    private TipoCentroDeSalud tipo ;
    private int calificacionInfraestructura;
    private int calificacionServicio;
    private boolean ambulanciaPropia ;

    private  transient double calificacion ;

    private transient  String estadoDeCalificacion;
    public double getCalificacion() {
        return calificacion;
    }

    public String getEstadoDeCalificacion() {
        return estadoDeCalificacion;
    }

    public void setEstadoDeCalificacion(String estadoDeCalificacion) {
        this.estadoDeCalificacion = estadoDeCalificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public CentroSalud() {
    }

    public CentroSalud(String nombre, TipoCentroDeSalud tipo, int calificacionInfraestructura, int calificacionServicio, boolean ambulanciaPropia) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.calificacionInfraestructura = calificacionInfraestructura;
        this.calificacionServicio = calificacionServicio;
        this.ambulanciaPropia = ambulanciaPropia;
    }

    public CentroSalud(Long codigo, String nombre, TipoCentroDeSalud tipo, int calificacionInfraestructura, int calificacionServicio, boolean ambulanciaPropia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.calificacionInfraestructura = calificacionInfraestructura;
        this.calificacionServicio = calificacionServicio;
        this.ambulanciaPropia = ambulanciaPropia;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCentroDeSalud getTipo() {
        return tipo;
    }

    public void setTipo(TipoCentroDeSalud tipo) {
        this.tipo = tipo;
    }

    public int getCalificacionInfraestructura() {
        return calificacionInfraestructura;
    }

    public void setCalificacionInfraestructura(int calificacionInfraestructura) {
        this.calificacionInfraestructura = calificacionInfraestructura;
    }

    public int getCalificacionServicio() {
        return calificacionServicio;
    }

    public void setCalificacionServicio(int calificacionServicio) {
        this.calificacionServicio = calificacionServicio;
    }

    public boolean isAmbulanciaPropia() {
        return ambulanciaPropia;
    }

    public void setAmbulanciaPropia(boolean ambulanciaPropia) {
        this.ambulanciaPropia = ambulanciaPropia;
    }

    @Override
    public String toString() {
        return    "Codigo:" + codigo +
                ", Nombre:" + nombre  +
                ", Tipo de centro:" + tipo +
                ", Calificacion de infraestructura:" + calificacionInfraestructura +
                ", Calificacion de servicio:" + calificacionServicio +
                ",Ambulancia propia:" + ambulanciaPropia ;

    }
}
