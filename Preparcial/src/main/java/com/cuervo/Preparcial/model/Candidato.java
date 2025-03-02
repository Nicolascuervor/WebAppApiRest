package com.cuervo.Preparcial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidato")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "documento_formal")
    private String cv;

    @Column(name = "habilidades")
    private String habilidades;

    @Column(name = "experiencia_laboral")
    private String experienciaLaboral;

    @Column(name = "educacion")
    private String eduacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private LocalDateTime fecha_registro;

    @OneToMany(mappedBy = "candidato")
    @JsonIgnore
    private List<Postulacion> postulaciones;


    public Candidato() {
    }

    public Candidato(String nombre, String apellido, String correo, String telefono, String cv, String habilidades, String experienciaLaboral, String eduacion, List<Postulacion> postulaciones) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.cv = cv;
        this.habilidades = habilidades;
        this.experienciaLaboral = experienciaLaboral;
        this.eduacion = eduacion;
        this.postulaciones = postulaciones;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(String experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getEduacion() {
        return eduacion;
    }

    public void setEduacion(String eduacion) {
        this.eduacion = eduacion;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<Postulacion> postulaciones) {
        this.postulaciones = postulaciones;
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", cv='" + cv + '\'' +
                ", habilidades='" + habilidades + '\'' +
                ", experienciaLaboral='" + experienciaLaboral + '\'' +
                ", eduacion='" + eduacion + '\'' +
                ", fecha_registro=" + fecha_registro +
                '}';
    }
}
