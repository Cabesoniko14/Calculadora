/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculadora;

import static java.lang.System.in;
import java.util.Arrays;



/**
 *
 * @author javi
 */
public class Postfijo {
    
    private String postifjo;

    public Postfijo(String postfijo) {
        this.postifjo = postfijo;   
    }

    public String getPostifjo() {
        return postifjo;
    }

    public void setPostifjo(String postifjo) {
        this.postifjo = postifjo;
    }
    
    
    
    public void ConvertirPostfijo(String infijo){
        
        PilaADT<Character> aux = new PilaA();
        
        for (int i = 0; i < infijo.length(); i++){
            if (infijo.charAt(i) == '(')
                aux.push(infijo.charAt(i));
            else if (infijo.charAt(i) == ')'){
                while (aux.peek() != '(' )
                    postifjo += aux.pop();
                aux.pop(); // Quitar paréntesis derecho                
            }
            else if (Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0').contains(infijo.charAt(i)))
                postifjo += infijo.charAt(i);
            else if (Arrays.asList('/', '*', '+', '-', '^').contains(infijo.charAt(i))){ // usar else para que tome a todos los operadores de una ? o no porque falta incluir los puntos decimales
                while ((!aux.isEmpty()) && (aux.peek()) >= infijo.charAt(i)) //Aquí falta hacer un método que compare jerarquía de operadores
                    postifjo += aux.pop();
                aux.push(infijo.charAt(i));
            }              
        }
        
        while (!aux.isEmpty())
            postifjo += aux.pop();

    }
    
   
}
