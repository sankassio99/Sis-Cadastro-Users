/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

//import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.activation.DataSource;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;

/**
 *
 * @author kassi
 */
public class FabricaConexao {

    public Connection pegaConexao() throws SQLException 
    {
//        try {
            Connection con = null;
//            InitialContext ic = new InitialContext();
//            DataSource ds = ( DataSource ) ic.lookup("recursoAula");
//            con = ds.getConnection();
//            return con;
//        } catch (NamingException ex) {
//            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
//        }      
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/teste","root","");
                    
        } catch (ClassNotFoundException |SQLException ex) {
            System.out.println("Erro ao tentar se conectar");
        }
        return con;
        
    }

}
