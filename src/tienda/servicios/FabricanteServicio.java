/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.servicios;

import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;

/**
 *
 * @author SebaaM <sebaamartinez54@gmail.com>
 */
public class FabricanteServicio {
    
    private FabricanteDAO fabDao;
    
    public FabricanteServicio(){
        fabDao = new FabricanteDAO ();
    }
    
    public void ingresarFabricante (String nombre) throws Exception {
        try {
            fabDao.guardarFabricante(new Fabricante(nombre));
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Fabricante buscarFabricantePorId (int id) throws Exception {
        
        try {
            Fabricante aux =fabDao.buscarFabricantePorId(id);
            return aux;
        } catch (Exception e) {
            throw e;
        }
        
    }
}
