package com.cuervo.Preparcial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "postulaciones")
public class Postulacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidatoID")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "ofertaEmpleoID")
    private OfertaEmpleo ofertaEmpleo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_postulacion")
    private LocalDateTime fecha_postulacion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "comentarios")
    private String comentarios;


    public Postulacion() {
    }

    public Postulacion(Candidato candidato, OfertaEmpleo ofertaEmpleo, String estado, String comentarios) {
        this.candidato = candidato;
        this.ofertaEmpleo = ofertaEmpleo;
        this.estado = estado;
        this.comentarios = comentarios;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public OfertaEmpleo getOfertaEmpleo() {
        return ofertaEmpleo;
    }

    public void setOfertaEmpleo(OfertaEmpleo ofertaEmpleo) {
        this.ofertaEmpleo = ofertaEmpleo;
    }

    public LocalDateTime getFecha_postulacion() {
        return fecha_postulacion;
    }

    public void setFecha_postulacion(LocalDateTime fecha_postulacion) {
        this.fecha_postulacion = fecha_postulacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }


    @Override
    public String toString() {
        return "Postulacion{" +
                "id=" + id +
                ", candidato=" + candidato +
                ", ofertaEmpleo=" + ofertaEmpleo +
                ", fecha_postulacion=" + fecha_postulacion +
                ", estado='" + estado + '\'' +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}
