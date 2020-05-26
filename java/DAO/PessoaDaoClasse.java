/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pessoa;

/**
 *
 * @author kassi
 */
public class PessoaDaoClasse {
    Connection con ;
    public PessoaDaoClasse()
    {
        FabricaConexao fabrica=new FabricaConexao();
        con=fabrica.pegaConexao();
    }
    
    public void CadatrarPessoa(Pessoa p ) throws SQLException {
        try{
            PreparedStatement ps = con.prepareStatement("insert into Pessoa(id,nome,idade) values (default,?,?)");
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getIdade());
                    
            ps.executeUpdate();
            ps.close();
          
        }catch(SQLException e){
            e.printStackTrace();
        }
    } 

    public Pessoa pegaPessoa(String nome) throws SQLException {
        PreparedStatement ps=con.prepareStatement("select * from Pessoa where nome=?");
        ps.setString(1, nome);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            Pessoa p=new Pessoa();
            p.setCod(rs.getInt("id"));
            p.setIdade(rs.getInt("idade"));
            p.setNome(rs.getString("nome"));
            return p;
        }
        return null;
    }

    public void deletarPessoa(int codigo) throws SQLException {
        PreparedStatement ps=con.prepareStatement("delete from Pessoa where cod=?");
        ps.setInt(1, codigo);
        ps.executeUpdate();
        ps.close();
    }

    public String deletarPessoa(String nome) {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from Pessoa where nome=?");
            ps.setString(1, nome);
            ps.executeUpdate();
            ps.close();
            return "deletado com sucesso!";
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDaoClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Erro ao deletar!" ;
    }

    public void editarPessoa(String nomeOriginal, String nomeMudado, int idade) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE `teste`.`Pessoa` SET `nome` = ? WHERE (`nome` = ?);");
        ps.setString(1, nomeMudado);
        ps.setString(2, nomeOriginal);
        ps.executeUpdate();
        ps = con.prepareStatement("UPDATE `teste`.`Pessoa` SET `idade` = ? WHERE (`nome` = ?);");
        ps.setInt(1, idade);
        ps.setString(2, nomeMudado);
        ps.executeUpdate();
        ps.close();
    }
    
    public List<Pessoa> ListarUsuarios(){
        ArrayList<Pessoa> grupo = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("select * from Pessoa");
            ResultSet rs = ps.executeQuery();;
            while(rs.next()){
                Pessoa p = new Pessoa();
                p.setCod(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setIdade(rs.getInt("idade"));
                
                grupo.add(p);
            }
            rs.close();
            return grupo ;   
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
}
