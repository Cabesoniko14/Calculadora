/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.*;
/* import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Daniel
 */
public class CalculadoraTest {
    
    public CalculadoraTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    

    /**
     * Test of ConvertirPostfijo method, of class Calculadora.
     */
    @Test
    public void testConvertirPostfijo() {
        System.out.println("ConvertirPostfijo");
        String infijo = "1+2*(3^4-5)^(6+7*8)-9";
        String infijo2 = "~30-50+10";
        String infijo3 = "1.5*(3.7+1.4)/2.1";
        Calculadora instance = new Calculadora();
        String expResult = "1 2 3 4 ^5- 6 7 8*+ ^*+9-";
        String result = instance.ConvertirPostfijo(infijo);
        String expResult2 = "~30 50 -10+";
        String expResult3 = "1.5 3.7 1.4+ *2.1/";
        String result2  = instance.ConvertirPostfijo(infijo2);
        String result3  = instance.ConvertirPostfijo(infijo3);
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of resuelveExpresion method, of class Calculadora.
     */
    @Test
    public void testResuelveExpresion() {
        System.out.println("resuelveExpresion");
        String postfijo = "~30 50 -10+";
        String postfijo2 = "5 2 ^2*";
        String postfijo3 = "1.5 3.7 1.4+ *2.1/";
        Calculadora instance = new Calculadora();
        double expResult = -70.0;
        double expResult2 = 50.0;
        double expResult3 = 3.6428571428571423;
        double result = instance.resuelveExpresion(postfijo);
        double result2 = instance.resuelveExpresion(postfijo2);
        double result3 = instance.resuelveExpresion(postfijo3);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult2, result2, 0.0);
        assertEquals(expResult3, result3, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of revisaSintaxis method, of class Calculadora.
     */
    @Test
    public void testRevisaSintaxis() {
        System.out.println("revisaSintaxis");
        String cadena = "2++2";
        String cadena2 = "-30-50+10";
        String cadena3 = "8..7";
        String cadena4 = "^7";
        Calculadora instance = new Calculadora();
        String expResult = "ERROR EN SUMA [2]";
        String expResult2 = "~30-50+10";
        String expResult3 = "ERROR CON PUNTOS [3]";
        String result = instance.revisaSintaxis(cadena);
        String result2 = instance.revisaSintaxis(cadena2);
        String result3 = instance.revisaSintaxis(cadena3);
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of esError method, of class Calculadora.
     */
    @Test
    public void testEsError() {
        System.out.println("esError");
        String cad = "~30 50 -10+";
        String cad2 = "ERROR";
        Calculadora instance = new Calculadora();
        boolean expResult = false;
        boolean expResult2 = true;
        boolean result = instance.esError(cad);
        boolean result2 = instance.esError(cad2);
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
