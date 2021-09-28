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
        String infijo = "2+2";
        Calculadora instance = new Calculadora();
        String expResult = "2 2+";
        String result = instance.ConvertirPostfijo(infijo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of resuelveExpresion method, of class Calculadora.
     */
    @Test
    public void testResuelveExpresion() {
        System.out.println("resuelveExpresion");
        String postfijo = "2 2+";
        Calculadora instance = new Calculadora();
        double expResult = 4.0;
        double result = instance.resuelveExpresion(postfijo);
        assertEquals(expResult, result, 0.0);
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
        Calculadora instance = new Calculadora();
        String expResult = "ERROR EN SUMA [2]";
        String result = instance.revisaSintaxis(cadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of esError method, of class Calculadora.
     */
    @Test
    public void testEsError() {
        System.out.println("esError");
        String cad = "2+2";
        Calculadora instance = new Calculadora();
        boolean expResult = false;
        boolean result = instance.esError(cad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
