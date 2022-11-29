/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.servicios;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.persistencia.FabricanteDAO;
import tienda.persistencia.ProductoDAO;

/**
 *
 * @author SebaaM <sebaamartinez54@gmail.com>
 */
public class ProductoServicio {

    private ProductoDAO dao;

    public ProductoServicio() {
        this.dao = new ProductoDAO();
    }

    // DML
    public void crearProducto(String nombre, Double precio, Fabricante fab) throws Exception {

        try {

            if (nombre == null || nombre.trim().isEmpty() || nombre.length() > 200) {
                throw new Exception("Se debe indicar el nombre del producto");
            }
            if (precio == null || precio.isNaN() || precio.isInfinite()) {
                throw new Exception("Se debe indicar el precio del producto");
            }
            if (fab == null) {
                throw new Exception("Fabricante del producto no indicado.");
            }

            Producto prod = new Producto();
            prod.setNombre(nombre);
            prod.setPrecio(precio);
            prod.setFabricante(fab);

            dao.guardarProducto(prod);

        } catch (Exception e) {
            throw e;
        }

    }

    public void modificarProducto(int id, String nombre, Double precio, int idfab) throws Exception {

        try {

            //Validamos
            if (id < 0) {
                throw new Exception("Debe indicar el id");
            }

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }

            if (precio == null || precio.isNaN()) {
                throw new Exception("Debe indicar la precio");
            }

            if (idfab < 0) {
                throw new Exception("Debe indicar el Fabricante");
            }
            //Buscamos
            // Buscar el fabricante en el main, servicio o el Dao??
            Producto prod = dao.buscarProductoPorId(id);
            prod.setNombre(nombre);
            prod.setPrecio(precio);

            //Necesario ¿?
            FabricanteDAO fabDao = new FabricanteDAO();
            prod.setFabricante(fabDao.buscarFabricantePorId(idfab));

            dao.modificarProducto(prod);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarProducto(Integer id) throws Exception {

        try {

            //Validamos 
            if (id == 0 || id == null) {
                throw new Exception("Debe indicar el id a eliminar.");
            }

            dao.eliminarProducto(id);
        } catch (Exception e) {
            throw e;
        }
    }

    //DML
    public void listarPortatiles() throws Exception {
        Collection<Producto> productos = new ArrayList();
        try {
            productos = dao.listarProductoPortatiles();
            mostrarProductosCorto(productos);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarPorPrecioEntreRagos() throws Exception {
//        Scanner leer = new Scanner (System.in);
//        int max = leer.nextInt();
//        int min = leer.nextInt();
        Collection<Producto> productos = new ArrayList();
        try {
            productos = dao.listarProductosEntreValores(120, 200);
            mostrarProductosCorto(productos);
        } catch (Exception e) {
            throw e;
        }

    }

    public void verProductoPrecioBajo() throws Exception {
        Collection<Producto> productos = new ArrayList();
        try {
            productos = dao.listarProductoMasBarato();
            mostrarProductos(productos);

        } catch (Exception e) {
            throw e;
        }

    }

    public void listarProductos() throws Exception {

        Collection<Producto> productos = new ArrayList();
        try {
            productos = dao.listarProductos();
            mostrarProductos(productos);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listarProductosCorto() throws Exception {

        Collection<Producto> productos = new ArrayList();
        try {
            productos = dao.listarProductos();
            mostrarProductosCorto(productos);
        } catch (Exception e) {
            throw e;
        }
    }
    
    

    public void mostrarProductos(Collection<Producto> lista) throws Exception {

        if (lista.isEmpty()) {
            throw new Exception("No hay productos para mostrar.");
        } else {
            for (Producto producto : lista) {
                System.out.println(producto.toString());
            }
        }
    }

    public void mostrarProductosCorto(Collection<Producto> lista) throws Exception {

        if (lista.isEmpty()) {
            throw new Exception("No hay productos para mostrar.");
        } else {
            for (Producto producto : lista) {
                System.out.println(producto.toStringSimple());
            }
        }
    }

}
