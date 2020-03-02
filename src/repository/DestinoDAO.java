/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import negocio.Destino;

/**
 *
 * @author Invitado
 */
public interface DestinoDAO {
    Destino find(int id) throws Exception;
    List<Destino> getAll() throws Exception;
    List<Destino> getAllWith(String referencia) throws Exception;
    void add(Destino destino) throws Exception;
    void update(Destino destino) throws Exception;
    void delete(int id) throws Exception;
}
