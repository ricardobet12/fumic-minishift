/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author utp
 */
@Configuration
public class Configuracion {
    @Bean
    public ModelMapper modelmapper(){
        return new ModelMapper();
    }
    
}
