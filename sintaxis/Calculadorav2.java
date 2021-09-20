package pilas;

import java.util.ArrayList;
import java.util.Arrays;
import pilas.Pila;

/**
 *
 * @author Liliana Acosta & Alexander Hellberg
 */
public class Calculadorav2 {
    
    /**
     * Metodo que evalua una operacion en infijo y determina su vlaidez
     * @param cadena Cadena con la operacion en infijo
     * @return 
     * <ul>
     * <li>La misma cadena si es correcta</li>
     * <li>Si tiene un elemento x negatico lo convierte a (0-x)</li>
     * <li>Error si la operación no es válida</li>
     * </ul>
     */
    public static String revisaSintaxis(String cadena){
        String res="", error =""; 
        Pila<Character> aux=new Pila<>();
        int i=0, contIz = 0, contDer = 0;
        ArrayList<Character> op=new ArrayList<>(Arrays.asList('+', '-', '*', '/','^'));
        

         
            while(i<cadena.length() && error.equals("") ){
                switch (cadena.charAt(i)){
                    case '+':
                    case '-':      
                    
                        if(i==0 || i==cadena.length()-1 || op.contains(cadena.charAt(i+1))|| op.contains(cadena.charAt(i-1))||  cadena.charAt(i-1)!='(' || cadena.charAt(i+1)!=')')
                            error="ERROR EN SUMA O SUBTRACCION ["+i+"]"; 
                        else 
                            res+=cadena.charAt(i); 
                        break; 
                       
                    case '*':
                    case '/':
                    case '^':    
                        if(i==0 || i==cadena.length()-1 || op.contains(cadena.charAt(i+1))|| op.contains(cadena.charAt(i-1)) || cadena.charAt(i-1)!='(' || cadena.charAt(i+1)!=')')
                            error="ERROR EN MULTIPLICACION DIVISION O EXPONENCIAL ["+i+"]"; 
                        else 
                            res+=cadena.charAt(i); 
                        break; 
                                
                   //Revisar con todos para ver si aceptara ()     
                    case '~':
                        if(i==cadena.length()-1 || cadena.charAt(i+1)=='(' || cadena.charAt(i+1)==')' || op.contains(cadena.charAt(i+1)) || cadena.charAt(i+1)=='~' || (i!=0 && !op.contains(cadena.charAt(i-1)) && cadena.charAt(i-1)!='(')) //no entendi operacion
                            error="ERROR EN ~ ["+i+"]"; 
                        else{
                            int cont=0; 
                            i++; 
                            res+="(0-";
                            while(i<cadena.length() && cont<2 && cadena.charAt(i)!='(' && cadena.charAt(i)!=')' && !op.contains(cadena.charAt(i)) && cadena.charAt(i)!='~'){
                              if(cadena.charAt(i)=='.'){
                                  cont++;
                              }
                              res+=cadena.charAt(i);
                              i++; 
                            }
                            if(cont==2)
                                error="ERROR ["+i+"]"; 
                            else{
                                i--; 
                                res+=')'; 
                            }
                        }  
                        break;
                        
                   // 55+7*(34+5)/3    --> 34 5 + 7**3/55+
                     // (5+7)5   5 7 + 5*   
                    case '(':
                        if( i == 0 || i==cadena.length()-1  || !op.contains(cadena.charAt(i+1)) || cadena.charAt(i+1)!=')' )
                            error="ERROR EN ( ["+i+"]";
                        else{
                            
                            res+=cadena.charAt(i); 
                            aux.push(cadena.charAt(i));
                            contIz++;}
                        
                        break;
                        
                    case ')':
                    
                        if(i==0 || op.contains(cadena.charAt(i-1)) || cadena.charAt(i-1)=='(' || aux.isEmpty())//|| cadena.charAt(i+1)!='+'|| cadena.charAt(i+1)!='-'|| cadena.charAt(i+1)=='('
                            error= "ERROR EN ) ["+i+"]";
                        else{
                            res+=cadena.charAt(i);
                            aux.pop(); 
                            contDer++;
                        }
                        break;
                    
                    default://Casos de numeros o '.'
                        
                        if(Character.isDigit(cadena.charAt(i)) || cadena.charAt(i)=='.' ){
                             int cont=0; 
                        
                             while(i<cadena.length()-1 && cont<2 ){ //POR REVISAR && cadena.charAt(i)!='(' && cadena.charAt(i)!=')' && !op.contains(cadena.charAt(i)) && cadena.charAt(i)!='~'
                               if(cadena.charAt(i)=='.'){
                                  cont++; 
                               }
                              res+=cadena.charAt(i);
                              i++; 
                             }
                             if(cont==2 || cadena.charAt(i-1)=='.')
                                error= "ERROR ["+i+"]"; 
                             else{//VERIFICAR
                                i--; 
                           }
                        }else{
                            error = "Caracter no reconocido ["+i+"]";
                        }
                        
                        break;
                }
                i++;        
            }
            
        if(contIz!=contDer)
            error= "ERROR: No hay misma cantidad de parentesis ";     
        
     
        if(i<cadena.length() && error.equals("") && cadena.charAt(cadena.length()-1)==')')
            res+=cadena.charAt(cadena.length()-1);
        
        while(error.equals("") && !aux.isEmpty()){
            res+=')'; 
            aux.pop();
        }
       
        if(error.equals("")){
            return res;
        }else{
            return error;
        }
    }

    
}


