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
public interface PilaADT <T>{
    
    public void push(T dato); // Agrega un dato en la pila
    public T pop(); // Quitar un dato de la pila
    public boolean isEmpty(); // Verifica si la pila está vacía
    public T peek(); // Permite consultar el elemento del tope
    public void multiPop(int n);
    
    
    
    
}
