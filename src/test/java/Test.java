/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Tienda.Tienda;
import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
/**
 *
 * @author Sebastián Sanchez
 */
public class Test {
    
    public Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     *  prueba considernado el caso critico que no se ingrese un rut 
     * 
     */
    @org.junit.Test//no me funciona el @Test
     public void validarRutTest() {
     boolean resultado=Tienda.validarRut("");
     boolean esperado=false;
         assertEquals(esperado,resultado);
     }
     
     /**
     *  prueba que es positiva
     * 
     */
    @org.junit.Test
     public void validarRut2Test() {
     boolean resultado=Tienda.validarRut("20.105.508-3");
     boolean esperado=true;
         assertEquals(esperado,resultado);
     }
     
  
}
