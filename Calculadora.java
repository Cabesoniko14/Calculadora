
import java.util.ArrayList;
import java.util.Arrays;

/*
 *<pre> 
 * Clase Calculadora
 * 
 * Recibe operaciones matematicas y las resuelve.
 *</pre>
 *@author Daniel, Javi, Liliana, Alexander, Jose
 */
public class Calculadora {
    
     /**
     * Metodo que recibe una operacion en infijo y la convierte a postfijo
     * @param infijo Cadena con la operacion en infijo
     * @return 
     * <ul>
     * <li>La cadena convertida a postfijo</li>
     * </ul>
     */
    public String ConvertirPostfijo(String infijo){
        String postfijo = "";
        PilaADT<Character> aux = new PilaA<>();
        int negativo = 0;
        for (int i = 0; i < infijo.length(); i++){

            char ch = infijo.charAt(i);
            if (ch == '(') {
                aux.push(ch);
            }
            else if (ch == ')'){
                while (aux.peek() != '(' )
                    postfijo += aux.pop();
                aux.pop(); // Quitar paréntesis derecho                
            }
            else if ( ch== '~'){ 
                negativo = 1;                 
            }
            else if (esUnDigito(ch)&&negativo == 0 ){
                postfijo += ch;
            }
            else if (esUnDigito(ch)&& negativo == 1){
                postfijo +='~';
                postfijo += ch;
                negativo = 0;
            }
            
            else if ("+-*/^".indexOf(ch)>=0){ 
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
    
    /**
     * Recibe un operador y le asigna una prioridad
     * @param ch Operador
     * @return <ul>
     * <li> 1: si el operador es una suma o una resta </li>
     * <li> 2: si el operador es una multiplicacion o una division </li>
     * <li> 3: si es un caret </li>
     * 
     * </ul> 
     */
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
    /**
     * Compara la prioridad de dos operadores
     * @param enPila Operador que se encuentra en la pila
     * @param nuevo Operdor que se acaba recibir
     * @return <ul>
     * <li> true: cuando la prioridad de enPila es mayor al nuevo </li>
     * <li> false:  cuando la prioridad de en Pila es menor al nuevo </li>
     * 
     * </ul>
     */
    private boolean comparaPrioridad(char enPila, char nuevo){
        int prioPila = prioridad(enPila);
        int prioNuevo = prioridad(nuevo);
        boolean res = prioPila >= prioNuevo;
        return res;
    }
    /**
     *  Comprueba si un caracter es un numero o un punto.
     * @param ch Caracter a comprobar
     * @return<ul>
     * <li> true: cuando es un numero o un punto </li>
     * <li> false:  cuando no es un numero o un punto </li>
     * 
     * </ul>
     * 
     */
    private boolean esUnDigito(char ch){
	return (ch>='0' && ch<='9') || ch=='.';
	}
    
     /**
     * Metodo que recibe una operacion en postfijo y la resuelve
     * @param postfijo Cadena con la operacion en postfijo
     * @return 
     * <ul>
     * <li>El resultado de la operación</li>
     * </ul>
     */
    public double resuelveExpresion(String postfijo){
        postfijo += " ";
        double res;
        PilaADT<Double> PilitaAux = new PilaA();
        String num = "";
        for (int i = 0; i < postfijo.length()-1; i++){
            char ch = postfijo.charAt(i);
           if (esUnDigito(ch)||ch == '~'){
               if (ch == '~')
                   num += '-';
               else
                   num += ch;
               System.out.println(ch);
           } 
           if (!esUnDigito(postfijo.charAt(i+1)) && !num.equals("")){
               PilitaAux.push(Double.parseDouble(num));
               System.out.println(num+"Agregado");
               num = "";
           }
            switch (ch) {
                case '+' :{
                    double x = (double) PilitaAux.pop();
                    double y = (double) PilitaAux.pop();
                    System.out.println(x);
                    System.out.println(y);
                    res = x+y;
                    PilitaAux.push(res);
                    break;
                }
                case '-' : {
                    double x = (double) PilitaAux.pop();
                    System.out.println(x);
                    double y = (double) PilitaAux.pop();
                    System.out.println(y);
                    res = y-x;
                    System.out.println("res"+res);
                    PilitaAux.push(res);
                    break;
                }
                case '*' : {
                    double x = (double) PilitaAux.pop();
                    double y = (double) PilitaAux.pop();
                    System.out.println(x);
                    System.out.println(y);
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
                    System.out.println(x);
                    System.out.println(y);
                    res = Math.pow(y,x);
                    PilitaAux.push(res); 
                    break;
                    }
                }
            }

        return (double) PilitaAux.pop();
    }
    
    
     /**
     * Metodo que evalua una operacion en infijo y determina su vlaidez
     * @param cadena Cadena con la operacion en infijo
     * @return 
     * <ul>
     * <li>La misma cadena si es correcta</li>
     * <li>Si tiene un elemento x negatico convierte el simbolo a ~</li>
     * <li>Error si la operación no es válida</li>
     * </ul>
     */
    public String revisaSintaxis(String cadena){
        String res="", error=""; 
        StringBuilder nuevaCadena = new StringBuilder(cadena);
        PilaADT<Character> aux=new PilaA<>();
        int contIz=0, contDer=0; 
        ArrayList<Character> op=new ArrayList<>(Arrays.asList('+', '-', '*', '/','^','~'));
        
            for (int i = 0; i < cadena.length(); i++){
                
                switch (cadena.charAt(i)){
                    case '+':
                        if(i==0 || i==cadena.length()-1 || op.contains(cadena.charAt(i+1))|| op.contains(cadena.charAt(i-1))|| cadena.charAt(i+1)==')')
                            error="ERROR EN SUMA ["+i+"]"; 
                        break;
                    case '-':
                        if(i==cadena.length()-1 || (i!=0 && op.contains(cadena.charAt(i+1)))|| (i!=0 && op.contains(cadena.charAt(i-1)))|| cadena.charAt(i+1)==')')
                            error="ERROR EN SUBTRACCION ["+i+"]"; 
                        else if (i == 0|| cadena.charAt(i-1)=='('){
                            nuevaCadena.setCharAt(i, '~');
                            cadena = nuevaCadena.toString();
                        }                            
                        break;
                    case '*':
                    case '/':
                    case '^':
                        if(i==0 || i==cadena.length()-1 || op.contains(cadena.charAt(i+1)) || op.contains(cadena.charAt(i-1)) || cadena.charAt(i-1)=='(' || cadena.charAt(i+1)==')')
                            error="ERROR EN MULTIPLICACION DIVISION O EXPONENCIAL ["+i+"]";
                        break; 
                            
                    case '(':
                        if(i==cadena.length()-1  || op.contains(cadena.charAt(i+1)) || cadena.charAt(i+1)==')' )
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
                            aux.pop();
                            contDer++;
                        }
                        break;
                    
                    default:
                        if(esUnDigito(cadena.charAt(i)) || cadena.charAt(i)==' ' ){
                            int cont=0;
                            while(i<cadena.length() && cont<2 && cadena.charAt(i)!='(' && cadena.charAt(i)!=')' && !op.contains(cadena.charAt(i))){
                                if(cadena.charAt(i)=='.'){
                                    cont++; 
                                }
                                i++; 
                            }
                            if(cont==2 || cadena.charAt(i-1)=='.')
                                error= "ERROR CON PUNTOS ["+i+"]"; 
                            else{
                                i--; 
                            }
                        }else{
                            error = "ERROR: Caracter no reconocido ["+i+"]";
                        }
                        break;
                }
            }
            
        if(contIz!=contDer)
            error= "ERROR: No hay misma cantidad de parentesis ";
        if(error.equals("")){
            return cadena;
        }else{
            return error;
        }
    }
    /**
     * Verifica si fue hubo error o no al revisar la sintaxis
     * @param cad cadena revisada o anuncio de error
     * @return 
     * <ul>
     * <li>true: si es un error</li>
     * <li>false: si no es un error</li>
     * </ul>
     */
    public boolean esError(String cad){
        String prueba = "";
        if ( cad.length()<5){
            return false;
        }
        for ( int i = 0; i < 5; i++){
           prueba += cad.charAt(i);
        }
        return prueba.equals("ERROR");
    }
}
