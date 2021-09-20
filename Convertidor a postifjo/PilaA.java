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
public class PilaA <T> implements PilaADT<T>{
    
    private int tope;
    private T[]pila;
    private final int MAX = 20;
    
    public PilaA(){
        pila = (T[]) new Object[MAX];
        tope = -1; // Indica pila vacía
    }
    
    public PilaA(int maximo){
        pila = (T[]) new Object[maximo];
        tope = -1; // Indica pila vacía
    }
    
    public boolean isEmpty(){
        return tope  == -1;
    }
    
    
    public void push (T dato){
        if (tope + 1 == pila.length)
            expandeCapacidad();
        tope++;
        pila[tope] = dato;
    }
    
    private void expandeCapacidad(){
        
        T[] masGrande = (T[])new Object[pila.length*2];
        
        for(int i = 0; i <= tope; i++)
            masGrande[i] = pila[i];
        pila = masGrande;
    
    }
    
    public T pop(){
        if(isEmpty())
            throw new ColeccionVaciaExcepcion("Pila vacía");
        T resultado = pila[tope];
        tope--;
        return resultado;
        
    }
    
    public void multiPop(int n){
        for(int i = 0; i < n ; i++){
            if (isEmpty())
                throw new ColeccionVaciaExcepcion("Pila vacía");
            else
                pop();
        }
    }
    
    
    public T peek(){
        if(isEmpty())
            throw new ColeccionVaciaExcepcion("Pila vacía");
        return pila[tope];
    }
    
    
}



