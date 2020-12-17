/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.dto;

import java.sql.Time;
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
public class FormularioDTO {
    
    private Integer idFormulario;
    private Date fechaVisita;
    private Time horaLlegada;
    private Time horaSalida;
    private EmpresaDTO iEmpresa;
    private String tipoControl;
    private String areasTratadas;
    private String observaciones;
    private String servicio;
    
}
