/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class cPontoTest {
    
    public cPontoTest() {
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
     * Test of doPost method, of class cPonto.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        cPonto instance = new cPonto();
        instance.doPost(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preencheCampos method, of class cPonto.
     */
    @Test
    public void testPreencheCampos() {
        System.out.println("preencheCampos");
        String campo = "";
        cPonto instance = new cPonto();
        String expResult = "";
        String result = instance.preencheCampos(campo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadArquivoPdf method, of class cPonto.
     */
    @Test
    public void testDownloadArquivoPdf() throws Exception {
        System.out.println("downloadArquivoPdf");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String name = "";
        String path = "";
        cPonto instance = new cPonto();
        instance.downloadArquivoPdf(request, response, name, path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMarcacao method, of class cPonto.
     */
    @Test
    public void testCheckMarcacao() {
        System.out.println("checkMarcacao");
        String checkBox = "";
        String variavel = "";
        cPonto instance = new cPonto();
        String expResult = "";
        String result = instance.checkMarcacao(checkBox, variavel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of diferencaDatas method, of class cPonto.
     */
    @Test
    public void testDiferencaDatas() {
        System.out.println("diferencaDatas");
        String dataInicio = "";
        String dataFinal = "";
        cPonto instance = new cPonto();
        float expResult = 0.0F;
        float result = instance.diferencaDatas(dataInicio, dataFinal);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServletInfo method, of class cPonto.
     */
    @Test
    public void testGetServletInfo() {
        System.out.println("getServletInfo");
        cPonto instance = new cPonto();
        String expResult = "";
        String result = instance.getServletInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doGet method, of class cPonto.
     */
    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        cPonto instance = new cPonto();
        instance.doGet(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
