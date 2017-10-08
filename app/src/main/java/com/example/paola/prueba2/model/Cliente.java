package com.example.paola.prueba2.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Paola on 08/10/2017.
 */

@Entity
public class Cliente {

    @Id(autoincrement = true)
    private Long codigo;
    private String nombre;
    private String apellidos;

    @Generated(hash = 826759515)
    public Cliente(Long codigo, String nombre, String apellidos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    @Generated(hash = 1805939709)
    public Cliente() {
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
