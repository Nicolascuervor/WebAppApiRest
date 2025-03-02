package com.cuervo.Preparcial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion_empresa")
    private String descripcion;

    @Column(name = "sector")
    private String sector;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "correo_contacto")
    private String correo_contacto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private LocalDateTime fecha_registro;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<OfertaEmpleo> empleos;


    public Empresa() {
    }

    public Empresa(String nombre, String descripcion, String sector, String ubicacion, String correo_contacto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sector = sector;
        this.ubicacion = ubicacion;
        this.correo_contacto = correo_contacto;
    }

    public Empresa(Long id, String nombre, String descripcion, String sector, String ubicacion, String correo_contacto, LocalDateTime fecha_registro, List<OfertaEmpleo> empleos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sector = sector;
        this.ubicacion = ubicacion;
        this.correo_contacto = correo_contacto;
        this.fecha_registro = fecha_registro;
        this.empleos = empleos;
    }

    public Empresa(Long id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCorreo_contacto() {
        return correo_contacto;
    }

    public void setCorreo_contacto(String correo_contacto) {
        this.correo_contacto = correo_contacto;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public List<OfertaEmpleo> getEmpleos() {
        return empleos;
    }

    public void setEmpleos(List<OfertaEmpleo> empleos) {
        this.empleos = empleos;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", sector='" + sector + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", correo_contacto='" + correo_contacto + '\'' +
                ", fecha_registro=" + fecha_registro +
                '}';
    }
}
