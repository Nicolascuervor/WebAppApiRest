package com.cuervo.Preparcial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Oferta_empleo")
public class OfertaEmpleo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "salario")
    private String salario;

    @Column(name = "requisitos")
    private String requisitos;

    @Column(name = "jornada")
    private String jornada;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "ubicacion")
    private String ubicacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_publicacion")
    private LocalDateTime fecha_publicacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_finalizacion")
    private Date fecha_expiracion;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "empresaID")
    private Empresa empresa;

    @OneToMany(mappedBy = "ofertaEmpleo")
    @JsonIgnore
    private List<Postulacion> postulaciones;

    public OfertaEmpleo() {
    }

    public OfertaEmpleo(String titulo, String descripcion, String salario, String requisitos, String jornada, String modalidad, String ubicacion, LocalDateTime fecha_publicacion, Date fecha_expiracion, String estado, Empresa empresa) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.salario = salario;
        this.requisitos = requisitos;
        this.jornada = jornada;
        this.modalidad = modalidad;
        this.ubicacion = ubicacion;
        this.fecha_publicacion = fecha_publicacion;
        this.fecha_expiracion = fecha_expiracion;
        this.estado = estado;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDateTime getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDateTime fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Date getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(Date fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<Postulacion> postulaciones) {
        this.postulaciones = postulaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "OfertaEmpleo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", salario='" + salario + '\'' +
                ", requisitos='" + requisitos + '\'' +
                ", jornada='" + jornada + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", fecha_publicacion=" + fecha_publicacion +
                ", fecha_expiracion=" + fecha_expiracion +
                ", estado='" + estado + '\'' +
                ", empresa=" + empresa +
                '}';
    }
}
