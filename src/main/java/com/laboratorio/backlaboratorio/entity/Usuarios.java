/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author utp
 */
@Entity
@Table(name= "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    private String nombres;
    private String apellidos;
    private Integer cedula;
    private String direccion;
    private String usuario;
    private String clave;
    private String rol;
    @Column(name = "estado")
    private Boolean estado;
    
}
