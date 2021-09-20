
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daniel, Javi
 */
public class Calculadora {
    
    public String ConvertirPostfijo(String infijo){
        String postfijo = "";
        PilaADT<Character> aux = new PilaA<>();
        
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
                postfijo += ' ';
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
    
    public double resuelveExpresion(String postfijo){
        double res;
        PilaADT<Double> PilitaAux = new PilaA();
        String num = "";
        for (int i = 0; i < postfijo.length(); i++){
            char ch = postfijo.charAt(i);
           if (esUnDigito(ch)){
               num += ch;
           } 
           if (i+1 < postfijo.length()&& !esUnDigito(postfijo.charAt(i+1))){
               PilitaAux.push(Double.parseDouble(num));
               num = "";
           }
            switch (ch) {
                case '~' :{
                    double x = (double) PilitaAux.pop();
                    PilitaAux.push(-x);
                }
                case '+' :{
                    double x = (double) PilitaAux.pop();
                    double y = (double) PilitaAux.pop();
                    res = x+y;
                    PilitaAux.push(res);
                    break;
                }
                case '-' : {
                    double x = (double) PilitaAux.pop();
                    double y = (double) PilitaAux.pop();
                    res = x-y;
                    PilitaAux.push(res);
                    break;
                }
                case '*' : {
                    double x = (double) PilitaAux.pop();
                    double y = (double) PilitaAux.pop();
                    res = x*y;
                    PilitaAux.push(res);
                    break;
                }
                case '/' : {
                    double x = (double) PilitaAux.pop();
                    double y = (double) PilitaAux.pop();
                    res = y/x;
                    PilitaAux.push(res);
                    break;
                }
                case '^' : {
                    double x = (double) PilitaAux.pop();
                    double y = (double) PilitaAux.pop();
                    res = Math.pow(x,y);
                    PilitaAux.push(res); 
                    break;
                    }
                }
            }

        return (double) PilitaAux.pop();
    }
    
    private static <T> void VaciaPilaAotra(PilaADT<T> Original, PilaADT<T> aux){
        while (!Original.isEmpty()){
            aux.push(Original.pop());
        }
    }
    
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
    public String revisaSintaxis(String cadena){
        String res="", error=""; 
        PilaADT<Character> aux=new PilaA<>();
        int contIz=0, contDer=0; 
        ArrayList<Character> op=new ArrayList<>(Arrays.asList('+', '-', '*', '/','^'));
        
            for (int i = 0; i < cadena.length(); i++){
                if (Character.isDigit(cadena.charAt(i))||cadena.charAt(i) == ' ' ){
                    res += cadena.charAt(i);
                    System.out.println(cadena.charAt(i));
                }
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
        
        if(error.equals("") && cadena.charAt(cadena.length()-1)==')')
            res+=cadena.charAt(cadena.length()-1);
        
        while(error.equals("") && !aux.isEmpty()){
            res+=')'; 
            aux.pop();
        }
       
        if(error.equals("")){
            return cadena;
        }else{
            System.out.println(error);
            return error;
        }
    }
    
    public boolean esError(String cad){
        String prueba = "";
        for ( int i = 0; i < 5; i++){
           prueba += cad.charAt(i);
        }
        return prueba.equals("ERROR");
    }
}