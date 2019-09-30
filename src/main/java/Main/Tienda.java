/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Sebastián Sanchez
 */
public class Tienda {
    
    
    public String leerTeclado() {
        Scanner teclado = new Scanner(System.in);
        String entrada;
        try {
            entrada = teclado.next();
            if (entrada =="") {
                leerTeclado();
            }
        } catch (InputMismatchException e) {
            System.out.println("Dato ingresado incorrecto, intentar nuevamente");
            entrada = leerTeclado();
        }
        return entrada;
    }
    
    public static void crearUsuarios(){
        FileWriter writer;
        try {
            writer = new FileWriter("Usuarios.csv");
            writer.append("nombre,rut,direccion\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
