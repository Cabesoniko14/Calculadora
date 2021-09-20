
package pilas;

import java.lang.Math.*;
import static java.lang.Math.exp;
import static java.lang.Math.log;

public class CalculadoraPosPrueba {
    
    /**
     * Metodo que evalua la expresion en posfijo 
     * @param Pila<String>
     * @return double
     * @author José Daniel & Alexander Hellberg
     * <ul>
     * <li>Por definiir</li>
     * 
     * </ul>
     */
    
    public static void main(String[] args) {
        Pila<String> pilita1 = new Pila();
//        pilita1.push("2");
//        pilita1.push("3");
//        pilita1.push("*");
//        pilita1.push("4");
//        pilita1.push("+");
        /////////////////////////
    //    pilita1.push("2");
      //  pilita1.push("3");
      //  pilita1.push("4");
      //  pilita1.push("+");
       // pilita1.push("*");
       // System.out.println(resuelveExpresion(pilita1));
       
            System.out.println(exp(log(2)*1.3));
       
           }
    
    
    private static <T> void VaciaPilaAotra(PilaADT<T> Original, PilaADT<T> aux){
        while (!Original.isEmpty()){
            aux.push(Original.pop());
        }
    }
    
    public static double resuelveExpresion(PilaADT<String> pila){
        double res;
        Pila<String> PilitaAux1 = new Pila();
        Pila<String> PilitaAux2 = new Pila();
        Pila<String> PilitaAux3 = new Pila();
        String simbolo;
        VaciaPilaAotra(pila,PilitaAux1);
        
        while (!PilitaAux1.isEmpty()){
            
            simbolo = PilitaAux1.pop();
            
            switch (simbolo) {
                case "+" :{
                    res = Double.parseDouble(PilitaAux2.pop())+Double.parseDouble(PilitaAux2.pop());
                    PilitaAux2.push(String.valueOf(res));
                }
                case "-" : {
                    PilitaAux3.push(PilitaAux2.pop());
                    res = Double.parseDouble(PilitaAux2.pop())-Double.parseDouble(PilitaAux3.pop());
                    PilitaAux2.push(String.valueOf(res));
                }
                case "*" : {
                    res = Double.parseDouble(PilitaAux2.pop())*Double.parseDouble(PilitaAux2.pop());
                    PilitaAux2.push(String.valueOf(res));
                }
                case "/" : {
                    PilitaAux3.push(PilitaAux2.pop());
                    res = Double.parseDouble(PilitaAux2.pop())/Double.parseDouble(PilitaAux3.pop());
                    PilitaAux2.push(String.valueOf(res));
                }
                  case "^" : {
                      //5^a
                      
                    PilitaAux3.push(PilitaAux2.pop());
                    if(Double.parseDouble(PilitaAux3.peek())%1==Double.parseDouble(PilitaAux3.peek()) && Double.parseDouble(PilitaAux3.peek()) != 0){ // Excepcion 0 talvez inutil
                        res=1;
                        for(int cont =0 ; cont< Double.parseDouble(PilitaAux3.peek()); cont++){
                            res*=Double.parseDouble(PilitaAux2.peek());
                        }
                        PilitaAux2.pop();
                        PilitaAux3.pop();
                        
                    }
                   // a^b = exp(log(a)*b) a^(1/2)
                    else
                         res = exp(log(Double.parseDouble(PilitaAux2.pop()))
                             *(Double.parseDouble(PilitaAux3.pop())));
                    PilitaAux2.push(String.valueOf(res));
                }
                default : PilitaAux2.push(simbolo);
            }
        }

        return Double.parseDouble (PilitaAux2.peek());
    }

    

      
   }
    
    
    



