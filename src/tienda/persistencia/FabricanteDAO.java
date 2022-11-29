/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Fabricante;

/**
 *
 * @author SebaaM <sebaamartinez54@gmail.com>
 */
public class FabricanteDAO  extends DAO {
     
    public void guardarFabricante (Fabricante f) throws Exception {
        try {
            if (f == null) {
                throw new Exception("Debe indicar un fabricante");
            }

            String sql = "INSERT INTO fabricante (nombre) "
                    + "VALUES ('" + f.getNombre()+ "' );";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
    
    public void modificarFabricante (Fabricante f) throws Exception {
        try {
            if (f == null) {
                throw new Exception("Debe indicar el fabricante que desea modificar");
            }

            String sql = "UPDATE fabricante SET "
                    + "codigo = " + f.getCodigo()+ " WHERE nombre = '" + f.getNombre()+ "';";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
    
    public void eliminarFabricante (int codigo) throws Exception {
        try {

            String sql = "DELETE FROM fabricante WHERE codigo = " + codigo + ";";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
    
    public Fabricante buscarFabricantePorId(Integer id) throws Exception {
        try {

            String sql = "SELECT codigo, nombre FROM fabricante"
                    + " WHERE codigo = " + id ;

            consultarBase(sql);

            Fabricante fab = null;
            while (resultado.next()) {
                fab = new Fabricante();
                fab.setCodigo(resultado.getInt(1));
                fab.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fab;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public Collection<Fabricante> listarUsuarios() throws Exception {
        try {
            String sql = "SELECT codigo, nombre FROM fabricante ;";

            consultarBase(sql);

            Fabricante fab = null;
            Collection<Fabricante> usuarios = new ArrayList();
            while (resultado.next()) {
                fab = new Fabricante();
                fab.setNombre(resultado.getString(2));
                fab.setCodigo(resultado.getInt(1));
                usuarios.add(fab);
            }
            desconectarBase();
            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    

}
