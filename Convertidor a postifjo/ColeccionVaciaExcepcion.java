/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculadora;

/**
 *
 * @author javi
 */
public class ColeccionVaciaExcepcion extends RuntimeException{

    public ColeccionVaciaExcepcion() {
        super();
    }
    
    public ColeccionVaciaExcepcion(String mensaje){
        super(mensaje);
    }
    
    
    
}
