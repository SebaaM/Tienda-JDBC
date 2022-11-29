/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tienda;

import java.util.Scanner;
import tienda.servicios.ProductoServicio;
import tienda.servicios.FabricanteServicio;

/**
 *
 * @author SebaaM <sebaamartinez54@gmail.com>
 */
public class Tienda {

    /**
     * @param args the command line arguments
     */
    public static void menu() {
        System.out.println("--- Elija la accion a realizar ---");
        System.out.println("a) Listar todos los productos. ");
        System.out.println("b) Listar nombre y precio de los productos. ");
        System.out.println("c) Listar producto entre precios (120 a 202) ");
        System.out.println("d) Listar todos los portatiles ");
        System.out.println("e) Listar producto mas barato.");
        System.out.println("f) Ingresar Producto Nuevo");
        System.out.println("g) Ingresar Fabricante Nuevo.");
        System.out.println("h) Modificar Producto Existente.");
        System.out.println("i) Salir.");

    }

    public static void main(String[] args) {
        // TODO code application logic here
        ProductoServicio prodServi = new ProductoServicio();
        FabricanteServicio fabServi = new FabricanteServicio();
        Scanner leer = new Scanner (System.in).useDelimiter("\n");
        char op;
        boolean salida = false;
        String nombre;
        Double precio;
        Integer codigo_fabricante;
        Integer id;
        do {
            try {
                Tienda.menu();
                op = leer.next().charAt(0);
                switch (op) {
                    
                    case 'a':
                        prodServi.listarProductos();
                        break;
                        
                    case 'b':
                        prodServi.listarProductosCorto();
                        break;
                        
                    case 'c':
                        prodServi.listarPorPrecioEntreRagos();
                        break;
                        
                    case 'd':
                        prodServi.listarPortatiles();
                        break;
                        
                    case 'e':
                        prodServi.verProductoPrecioBajo();
                        break;
                        
                    case 'f':
                        System.out.println("Ingresar el nombre del producto");
                        nombre = leer.next();
                        System.out.println("Ingresar el precio del producto");
                        precio = leer.nextDouble();
                        System.out.println("Ingrese el codigo de fabricante");
                        codigo_fabricante = leer.nextInt();
                        prodServi.crearProducto(nombre, precio, fabServi.buscarFabricantePorId(codigo_fabricante));
                        break;
                        
                    case 'g':
                        System.out.println("Ingrese el nombre del fabricante nuevo.");
                        nombre = leer.next();
                        fabServi.ingresarFabricante(nombre);
                        break;
                        
                    case 'h':
                        System.out.println("Ingrese el id del producoto a modificar");
                        id = leer.nextInt();
                        System.out.println("Ingrese el nuevo nombre del producto");
                        nombre = leer.next();
                        System.out.println("Ingrese el nuevo precio del producto");
                        precio = leer.nextDouble();
                        System.out.println("Ingrese el id del nuevo fabricante");
                        codigo_fabricante = leer.nextInt();
                        prodServi.modificarProducto(id, nombre, precio, codigo_fabricante);
                        break;
                        
                    case 'i':
                        salida = true;
                        break;
                        
                    default:
                        System.out.println("Letra ingresada incorrecta.");
                }
            } catch (Exception e) {
                System.err.println("Se preodujo un error");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        } while (salida == false);

    }

}
