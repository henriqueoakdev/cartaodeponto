/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paginas;

import Querys.qInsercoesbd;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "exibeCartoes", urlPatterns = {"/exibeCartoes"})
public class exibeCartoes extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String vp = (String) session.getAttribute("valorParametro");
        String pp = (String) session.getAttribute("parametroPesquisa");
        String[] cartao;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = cal.getTime() + "";
        String hora = sdf.format(cal.getTime());
        Date horaAtual = sdf.parse(hora);
        if (pp.equals("nenhum")) {
            cartao = qInsercoesbd.selectTodosCartoes();
        } else {
            cartao = qInsercoesbd.selectCartao(pp, vp);
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"
                    + "<link rel=\"stylesheet\" href=\"styles/bootstrap.min.css\" type=\"text/css\"/>\n"
                    + "<link rel=\"stylesheet\" href=\"styles/consulper.css\" type=\"text/css\"/>\n"
                    + "<link rel=\"stylesheet\" href=\"styles/common.css\" type=\"text/css\"/>\n"
                    + "        <script src=\"scripts/jquery.js\"></script>\n"
                    + "        <script src=\"scripts/jquery.maskedinput.js\"></script>\n"
                    + "        <script src=\"scripts/Uteis.js\"></script>\n"
                    + "<style>"
                    + ".celula{"
                    + "width:11.1%;"
                    + "border:solid;"
                    + "border-width:1px;"
                    + "text-align:center;"
                    + "}"
                    + ".modal {\n"
                    + "    display: none; /* Hidden by default */\n"
                    + "    position: fixed; /* Stay in place */\n"
                    + "    z-index: 1; /* Sit on top */\n"
                    + "    left: 0;\n"
                    + "    top: 0;\n"
                    + "    width: 100%; /* Full width */\n"
                    + "    height: 100%; /* Full height */\n"
                    + "    overflow: auto; /* Enable scroll if needed */\n"
                    + "    background-color: rgb(0,0,0); /* Fallback color */\n"
                    + "    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */\n"
                    + "}\n"
                    + "\n"
                    + "/* Modal Content/Box */\n"
                    + ".modal-content {\n"
                    + "    background-color: #fefefe;\n"
                    + "    margin: 15% auto; /* 15% from the top and centered */\n"
                    + "    padding: 20px;\n"
                    + "    border: 1px solid #888;\n"
                    + "    width: 15%; /* Could be more or less, depending on screen size */\n"
                    + "    background-color:#F0F0F0;\n"
                    + "}\n"
                    + "</style>"
                    + "<script>"
                    + "function abreJanela(campo){\n"
                    + "document.getElementById(campo).style.display = \"block\";\n"
                    + "}\n"
                    + "function fechaJanela(campo){\n"
                    + "document.getElementById(campo).style.display = \"none\";\n"
                    + "}\n"
                    + "function visualizaCartao(protocolo){\n"
                    + "document.getElementById(\"auxiliar\").value=protocolo;\n"
                    + "enviar('buscaDigitacao');\n"
                    + "}\n"
                    + "</script>");
            out.println("<title>Exibição de Cartões</title>");
            out.println("</head>");
            out.println("<body>"
                    + "<form name=\"form\" action=\"cartaodeponto\" method=\"post\">\n"
                    + "<input type=\"hidden\" name=\"action\" id=\"action\" value=\"\">\n"
                    + "<input type=\"hidden\" name=\"auxiliar\" id=\"auxiliar\" value=\"\">");
            //modal Buscar Cartoes
            out.println("<div id=\"modalbuscacartoes\" class=\"modal\">\n"
                    + "\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:20%\">\n"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-left:52px\">Selecione um Parâmetro:</span>\n"
                    + "</div>"
                    + "<select name=\"parametro\" style=\"margin-left:50px\">"
                    + "<option value=\"protocolo\">Protocolo</option>"
                    + "<option value=\"dt_inclusao\">Data de Inclusão</option>"
                    + "<option value=\"processo\">Processo</option>"
                    + "<option value=\"junta\">Junta</option>"
                    + "<option value=\"reclamante\">Reclamante</option>"
                    + "<option value=\"reclamada\">Reclamada</option>"
                    + "<option value=\"dt_inicial\">Data Inicial</option>"
                    + "<option value=\"dt_final\">Data Final</option>"
                    + "</select>"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-left:110px\">Valor:</span>\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"text\" style=\"margin-left:55px\" name=\"valor\">\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px; margin-left:45px\" onclick=\"enviar('buscaCartao')\" name=\"registrar\" value=\"Buscar\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px\" onclick=\"fechaJanela('modalbuscacartoes')\" name=\"fecha\" value=\"Fechar\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>");
            out.println("<h1 style=\"margin-left:20px\">Busca de Cartões</h1>"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px; margin-left:20px\" value=\"Filtrar Busca\" onclick=\"abreJanela('modalbuscacartoes')\">"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px; margin-left:10px\" value=\"Mostrar Todos\" onclick=\"enviar('buscaTodosCartoes')\">"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin-left:10px\" onclick=\"enviar('paginaInicial')\" value=\"Voltar\">\n"
                    + "<table style=\"width:80%; margin-left:20px;\">"
                    + "<tr>\n"
                    + "<td class=\"celula\"><span>Protocolo</span></td>"
                    + "<td class=\"celula\"><span>Data de Inclusão</span></td>"
                    + "<td class=\"celula\"><span>Processo</span></td>"
                    + "<td class=\"celula\"><span>Junta</span></td>"
                    + "<td class=\"celula\"><span>Reclamante</span></td>"
                    + "<td class=\"celula\"><span>Reclamada</span></td>"
                    + "<td class=\"celula\"><span>Data Inicial</span></td>"
                    + "<td class=\"celula\"><span>Data Final</span></td>"
                    + "<td class=\"celula\">Digitação</td>"
                    + "</tr>\n");
            for (String linha : cartao) {
                out.println("<tr>");
                out.println("<td class=\"celula\">" + linha.split(";;")[0] + "</td>");
                out.println("<td class=\"celula\">" + linha.split(";;")[1] + "</td>");
                out.println("<td class=\"celula\">" + linha.split(";;")[2] + "</td>");
                out.println("<td class=\"celula\">" + linha.split(";;")[3] + "</td>");
                out.println("<td class=\"celula\">" + linha.split(";;")[4] + "</td>");
                out.println("<td class=\"celula\">" + linha.split(";;")[5] + "</td>");
                out.println("<td class=\"celula\">" + linha.split(";;")[6] + "</td>");
                out.println("<td class=\"celula\">" + linha.split(";;")[7] + "</td>");
//                Date horaStatus = sdf.parse(linha.split(";;")[11].split("-")[0]);
//                Date diaStatus = data.parse(linha.split(";;")[11].split("-")[1]);
//                String statusAtual = diaStatus + "";
//                double diferenca = (((double) horaAtual.getTime() - (double) horaStatus.getTime() / 1000) / 60) / 60;
//                if (diferenca > 0.5 && statusAtual != dataAtual || diferenca > 0.5 && statusAtual == dataAtual || diferenca < 0.5 && statusAtual != dataAtual) {
                    out.println("<td class=\"celula\"><input type=\"button\" class=\"btn btn-primary\" onclick=\"visualizaCartao('" + linha.split(";;")[0] + "')\" value=\"Visualizar\"></td>");
//                } else {
//                    out.println("<td class=\"celula\"><span>Em Edição</span></td>");
//                }
                out.println("</tr>");
            }
            out.println("</table>\n"
                    + "</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(exibeCartoes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(exibeCartoes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
