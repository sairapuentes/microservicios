package com.sairapuentes.login.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_usuario")
    private int idUsuario;
    @Column(name="nombre_usuario", nullable = false, length=100)
    private String nombreUsuario;
    @Column(name="cedula_usuario")
    private long cedula;
    @Column(name="correo_usuario")
    private String correo;
    @Column(name="password_usuario")
    private String password;
    @ManyToOne
    @JoinColumn(name="ID_rol")
    private Rol rol;
    @Column(name="ID_sede", nullable = false)
    private int idSede;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, long cedula,String correo, String password, Rol rol, int idSede) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.cedula = cedula;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.idSede = idSede;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }
}
