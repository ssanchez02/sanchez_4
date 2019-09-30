/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tienda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Sebastián Sanchez
 */
public class Tienda {

    /**
     * Método que crea un usuario para la plataforma
     *
     * @return objeto tipo Persona con sus atributos
     */
    public Persona crearUsuario() {
        String nombre = leerNombre();
        String rut = leerRut();
        String direccion = leerDireccion();
        Persona persona = new Persona(nombre, rut, direccion);
        return persona;
    }

    /**
     * Método que lee el nombre del usuario a crear
     *
     * @return nombre completo
     */
    public String leerNombre() {
        System.out.println("Ingrese nombre de la persona");
        String nombre = recibirTexto();
        System.out.println("Ingrese apellido de la persona");
        String apellido = recibirTexto();
        String nombreComp = nombre + " " + apellido;
        if (nombreComp.length() > 20) {
            System.out.println("Su nombre exede los 20 caracteres, escribalo abreviando");
            leerNombre();
        }
        return nombreComp;
    }

    /**
     * Método que recibe las entradas por teclado
     *
     * @return String recibido y validado
     */
    public String recibirTexto() {
        Scanner teclado = new Scanner(System.in);
        String texto = " ";
        try {
            texto = teclado.nextLine();
        } catch (Exception e) {
            System.out.println("Intente nuevamente");
            recibirTexto();
        }
        return texto;
    }

    /**
     * Método que lee el rut
     *
     * @return rut de la persona
     */
    public String leerRut() {
        String rut = " ";
        System.out.println("Ingrese su rut con guión(-)");
        rut = recibirTexto();
        if (validarRut(rut) == false) {
            System.out.println("rut invalido");
            leerRut();
        } else if (validarRut(rut) == true) {
            System.out.println("Rut valido");
        }
        return rut;
    }

    /**
     * Método que valida el rut
     *
     * @param rut que será validado
     * @return rut validado
     */
    public boolean validarRut(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0;
            int s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    /**
     * Método que lee la direccion completa del usuario
     *
     * @return la direccion completa
     */
    public String leerDireccion() {
        String direccion = " ";
        System.out.println("Ingrese su direccion de domicilio (Calle,Número,Ciudad,Región) ");
        System.out.println("Ingrese la calle del domicilio");
        String calle = recibirTexto();
        System.out.println("Ingrese el Número de domicilio");
        int numero = leerNumeroDireccion();
        System.out.println("Ingrese la ciudad de domicilio");
        String ciudad = recibirTexto();
        System.out.println("Ingrese la región de domicilio");
        String region = recibirTexto();
        direccion = calle + "," + numero + "," + ciudad + "," + region;
        return direccion;
    }

    /**
     * Método que recibe el numero de la calle
     *
     * @return numero de la calle validado
     */
    public int leerNumeroDireccion() {
        int numero = 0;
        Scanner sc = new Scanner(System.in);
        try {
            numero = sc.nextInt();
            if (numero < 0) {
                leerNumeroDireccion();
            }
        } catch (InputMismatchException e) {
            System.out.println("Ingrese solo numeros");
            leerNumeroDireccion();
        }
        return numero;
    }

    /**
     * Método que lee el archivo en la ruta especificada
     *
     * @param ruta del archivo a leer
     * @return texto dentro del archivo
     */
    public String leerArchivo(String ruta) {
        String texto = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String temp = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                temp = temp + bfRead;
            }
            texto = temp;
        } catch (IOException e) {
            System.err.println("El Archivo no pudo ser leido");
        }
        return texto;
    }

    /**
     * Método que agrega un texto en la ruta especificada
     *
     * @param ruta del archivo al cual se le agrega el texto
     * @param texto que se agrega en el archivo
     * @return texto agregado
     */
    public String agregarTexto(String ruta, String texto) {
        try {
            FileWriter archivo = new FileWriter(ruta, true);
            archivo.append(texto);
            archivo.close();
            System.out.println("Texto agregado correctamente " + texto);
        } catch (IOException e) {
            System.out.println("Error al agregar el texto");

        }
        return leerArchivo(ruta);
    }

    /**
     * Método que inicia el programa
     */
    public void menu() {
        Persona usuario = crearUsuario();
        agregarTexto("Usuarios.csv", usuario.getNombre() + "," + usuario.getRut() + "," + usuario.getDireccion());
        leerArchivo("Usuarios.csv");
    }
}
