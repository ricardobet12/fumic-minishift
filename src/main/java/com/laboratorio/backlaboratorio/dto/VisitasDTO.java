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
 * @author ricardo.bet
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitasDTO {
        
    private Integer idVisita;
    private EmpresaDTO idEmpresa;
    private Date fecha;
    private FormularioDTO idFormulario;
    private Boolean estado;
}
