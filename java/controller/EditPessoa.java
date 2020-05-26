/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.PessoaDaoClasse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pessoa;

/**
 *
 * @author kassi
 */
@WebServlet(name = "EditPessoa", urlPatterns = {"/EditPessoa"})
public class EditPessoa extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         
            String nome = request.getParameter("nome");  
            String nomeOriginal = request.getParameter("nomeOriginal");  
            int idade = Integer.parseInt(request.getParameter("idade"));

            
            PessoaDaoClasse pdc = new PessoaDaoClasse();
            
            
            try {
                pdc.editarPessoa(nomeOriginal, nome, idade);
                System.out.println("Nome mudado de :"+nomeOriginal+" para: "+nome);
                System.out.println("Nome mudado para: "+idade); 
                RequestDispatcher despacho = getServletContext().getRequestDispatcher("/ListarTodos");
                despacho.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EditPessoa.class.getName()).log(Level.SEVERE, null, ex);
            }
          


            
            


//            ArrayList< Pessoa > ListaPessoas = (ArrayList< Pessoa >) pdc.ListarUsuarios();
//            
//
//            out.print("<table class='tabela'>") ;;
//                out.print("<tbody  >");
//                out.print("<tr><th>ID</th><th>NOME</th><th>IDADE</th>");
//                    for(Pessoa c: ListaPessoas){
//                        out.println("<tr>");
//                        out.print("<td>"+c.getCod()+"</td><td>"+c.getNome()+"</td><td>"+c.getIdade()+"</td>");
//                        out.println("</tr>");
//                    }
//                out.print("</tbody>");
//            out.println("</table>");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
