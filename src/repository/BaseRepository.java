/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import persistencia.ConnectionFactory;

/**
 *
 * @author Invitado
 */
public abstract class BaseRepository {
    
    private final ConnectionFactory connectionFactory;

    public BaseRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
   
    void add(Object objeto) throws Exception{
        
    }
    void update(Object objeto) throws Exception{
        
    }
    void delete(int id) throws Exception{
        
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
    
    
}
