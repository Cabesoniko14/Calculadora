/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
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
    
}
