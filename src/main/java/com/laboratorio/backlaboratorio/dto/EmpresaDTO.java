/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author utp
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
    
    private Integer id;
    private String nombre;
    private String nit;
    private String direccion;
    private Integer telefono;
    private boolean estado;
    private String ruta;
    private Double lat;
    private Double longi;
    private UsuariosDTO idUsuario;
    
}
