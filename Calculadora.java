package proyectoEquipo;

import java.util.ArrayList;
import java.util.Arrays;
import pilas.Pila;
import pilas.PilaADT;
import static java.lang.Math.exp;
import static java.lang.Math.log;

/**
 *
 * @author Liliana Acosta & Alexander Hellberg & José Daniel & Javier 
 */
public class Calculadora {
    
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
        String res="", error=""; 
        Pila<Character> aux=new Pila<>();
        int i=0, contIz=0, contDer=0; 
        ArrayList<Character> op=new ArrayList<>(Arrays.asList('+', '-', '*', '/','^'));
        
            while(i<cadena.length() && !res.equals("") ){
                switch (cadena.charAt(i)){
                    case '+':
                    case '-':
                        if(i==0 || i==cadena.length()-1 || op.contains(cadena.charAt(i+1))|| op.contains(cadena.charAt(i-1))||  cadena.charAt(i+1)!='(' || cadena.charAt(i+1)!=')')
                            error="ERROR EN SUMA O SUBTRACCION ["+i+"]"; 
                        else 
                            res+=cadena.charAt(i); 
                        break;
                        
                    case '*':
                    case '/':
                    case '^':
                        if(i==0 || i==cadena.length()-1 || op.contains(cadena.charAt(i+1)) || op.contains(cadena.charAt(i-1)) || cadena.charAt(i-1)!='(' || cadena.charAt(i+1)!=')')
                            error="ERROR EN MULTIPLICACION DIVISION O EXPONENCIAL ["+i+"]";
                        else 
                            res+=cadena.charAt(i); 
                        break; 
                        
                    case '~':
                        if(i==cadena.length()-1 || cadena.charAt(i+1)=='(' || cadena.charAt(i+1)==')' || op.contains(cadena.charAt(i+1)) || cadena.charAt(i+1)=='~' || (i!=0 && !op.contains(cadena.charAt(i-1)) && cadena.charAt(i-1)!='('))
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
                    
                    default:
                        if(Character.isDigit(cadena.charAt(i)) || cadena.charAt(i)=='.' ){
                            int cont=0;
                            while(i<cadena.length() && cont<2 && cadena.charAt(i)!='(' && cadena.charAt(i)!=')' && !op.contains(cadena.charAt(i)) && cadena.charAt(i)!='~'){
                                if(cadena.charAt(i)=='.'){
                                    cont++; 
                                }
                                res+=cadena.charAt(i);
                                i++; 
                            }
                            if(cont==2 || cadena.charAt(i-1)=='.')
                                error= "ERROR ["+i+"]"; 
                            else{
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
    
    public String ConvertirPostfijo(String infijo){
        String postfijo = "";
        PilaADT<Character> aux = new Pila<>();
        
        for (int i = 0; i < infijo.length(); i++){
            int negativo = 0;
            char ch = infijo.charAt(i);
            if (ch == '(') {
                aux.push(ch);
                negativo = 0;
            }
            else if (ch == ')'){
                while (aux.peek() != '(' )
                    postfijo += aux.pop();
                aux.pop(); // Quitar paréntesis derecho                
            }
            else if (esUnDigito(ch)){
                postfijo += ch;
                negativo = 1;
            }
            else if ("+-*/^~".indexOf(ch)>=0){ 
                while ((!aux.isEmpty()) && comparaPrioridad(aux.peek(), ch))
                    postfijo += aux.pop();
                aux.push(ch);
            }              
        }
        
        while (!aux.isEmpty())
            postfijo += aux.pop();
        return postfijo;
    }
    
    private int prioridad(char ch){
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }
    
    private boolean comparaPrioridad(char enPila, char nuevo){
        int prioPila = prioridad(enPila);
        int prioNuevo = prioridad(nuevo);
        boolean res = prioPila >= prioNuevo;
        return res;
    }
    
    private boolean esUnDigito(char ch){
	return (ch>='0' && ch<='9') || ch=='.';
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
