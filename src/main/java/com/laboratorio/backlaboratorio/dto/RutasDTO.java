/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author cristiancg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RutasDTO {
    
    private Integer idRuta;
    private UsuariosDTO idUsuario;
    private EmpresaDTO idEmpresa;
    private Date fecha;
    private String estado;
    
}
