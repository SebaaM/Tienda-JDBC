/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;

/**
 *
 * @author SebaaM <sebaamartinez54@gmail.com>
 */
public class ProductoDAO extends DAO {

    private FabricanteDAO fabDao;

    public ProductoDAO() {
        fabDao = new FabricanteDAO();
    }
    //DML
    public void guardarProducto(Producto p) throws Exception {

        try {
            if (p == null) {
                throw new Exception(" (Vacio) Debe indicar un Producto.");
            }

            String sql = "INSERT INTO producto (nombre,precio,codigo_fabricante)"
                    + "Values ('" + p.getNombre()
                    + "' , " + p.getPrecio() + " , " + p.getFabricante().getCodigo() + " );";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

    public void modificarProducto(Producto p) throws Exception {
        try {
            if (p == null) {
                throw new Exception("(vacio) Se debe indicar un producto ");
            }

            String sql = "UPDATE producto SET"
                    + " nombre = '" + p.getNombre()
                    + "', precio = " + p.getPrecio() 
                    + ", codigo_fabricante = " + p.getFabricante().getCodigo() + " "
                    + "WHERE codigo=" +  p.getCodigo();

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

    public void eliminarProducto(int codigo) throws Exception {

        try {
            String sql = "DELETE FROM producto where codigo = '" + codigo + "'";

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    //DML
    
    public Producto buscarProductoPorId(Integer id) throws Exception {

        try {

            String sql = "SELECT * FROM producto WHERE codigo=" + id + ";";

            consultarBase(sql);

            Producto prod = null;

            while (resultado.next()) {
                prod = new Producto();
                prod.setCodigo(resultado.getInt(1));
                prod.setNombre(resultado.getString(2));
                prod.setPrecio(resultado.getDouble(3));
                int cod_fabricante = resultado.getInt(4);
                Fabricante fab = new Fabricante();
                fab = fabDao.buscarFabricantePorId(cod_fabricante);
                prod.setFabricante(fab);
            }
            desconectarBase();
            return prod;
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public Collection<Producto> listarProductos() throws Exception {

        try {
            String sql = "SELECT codigo,nombre,precio,codigo_fabricante FROM producto ";
            consultarBase(sql);
            Producto prod = null;
            Collection <Producto> productos = new ArrayList();

            while (resultado.next()) {
                prod = new Producto();
                prod.setCodigo(resultado.getInt(1));
                prod.setNombre(resultado.getString(2));
                prod.setPrecio(resultado.getDouble(3));
                int cod_fabricante = resultado.getInt(4);
                Fabricante fab = new Fabricante();
                fab = fabDao.buscarFabricantePorId(cod_fabricante);
                prod.setFabricante(fab);
                productos.add(prod);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            e.printStackTrace();
            throw new Exception("Error al listar producto");
        }
    }

    //Lista los nombres y los precios de todos los productos de la tabla producto.
    public Collection<Producto> listarProductosNombrePrecio() throws Exception {

        try {
            String sql = "SELECT nombre,precio FROM producto ;";
            consultarBase(sql);
            Producto prod = null;
            Collection<Producto> productos = new ArrayList();

            while (resultado.next()) {
                prod = new Producto();
                prod.setNombre(resultado.getString(1));
                prod.setPrecio(resultado.getDouble(2));
                productos.add(prod);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            e.printStackTrace();
            throw new Exception("Error al listar producto");
        }
    }

    //Listar aquellos productos que su precio esté entre 120 y 202.
    public Collection<Producto> listarProductosEntreValores(int minimo, int maximo) throws Exception {

        try {
            String sql = "SELECT nombre,precio FROM producto ;";
            consultarBase(sql);
            Producto prod = null;
            Collection<Producto> productos = new ArrayList();

            while (resultado.next()) {
                Double precio = resultado.getDouble(2);
                if (precio >= minimo && precio <= maximo) {
                    prod = new Producto();
                    prod.setNombre(resultado.getString(1));
                    prod.setPrecio(precio);
                    productos.add(prod);
                }
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            e.printStackTrace();
            throw new Exception("Error al listar producto");
        }
    }
    
    //Buscar y listar todos los Portátiles de la tabla producto.
    public Collection<Producto> listarProductoPortatiles () throws Exception {
        try {
            String sql = "SELECT nombre,precio FROM producto WHERE nombre LIKE '%portatil%';";
            consultarBase(sql);
            Producto prod = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                prod = new Producto();
                prod.setNombre(resultado.getString(1));
                prod.setPrecio(resultado.getDouble(2));
                productos.add(prod);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            e.printStackTrace();
            throw new Exception("Error al listar producto");
        }
    }
    
    //Listar el nombre y el precio del producto más barato.
    public Collection<Producto> listarProductoMasBarato () throws Exception {
        try {
            String sql = "SELECT nombre,precio FROM producto ORDER BY precio asc LIMIT 1;";
            consultarBase(sql);
            Producto prod = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                prod = new Producto();
                prod.setNombre(resultado.getString(1));
                prod.setPrecio(resultado.getDouble(2));
                productos.add(prod);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            e.printStackTrace();
            throw new Exception("Error al listar producto");
        }
    }
}
