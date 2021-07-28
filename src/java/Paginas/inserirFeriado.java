
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paginas;

import Querys.qInsercoesbd;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "inserirFeriado", urlPatterns = {"/inserirFeriado"})
public class inserirFeriado extends HttpServlet {

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
            HttpSession session = request.getSession();
            String parametroFeriados = "1";
            String[] vetFeriados = qInsercoesbd.selectPrimUltimoFeriado();
            String[] primFeriado = vetFeriados[0].split(";;");
            String[] ultFeriado = vetFeriados[1].split(";;");
            String[] cidades = qInsercoesbd.selectCidades();
            String[] feriados = qInsercoesbd.selectFeriado();
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
                    + "        <script src=\"scripts/scripts.js\"></script>\n"
                    + "        <script src=\"http://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n"
                    + "        <link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css\" />\n"
                    + "<style>"
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
                    + "<script>\n"
                    + "function abreJanela(campo){\n"
                    + "document.getElementById(campo).style.display = \"block\";\n"
                    + "}\n"
                    + "function fechaJanela(campo){\n"
                    + "document.getElementById(campo).style.display = \"none\";\n"
                    + "}\n"
                    + "function habilitaCidades(radio)\n"
                    + "{if(radio.value==\"municipal\"){\n"
                    + "document.getElementById(\"cidades\").disabled=false\n"
                    + "}\n"
                    + "else{\n"
                    + "document.getElementById(\"cidades\").disabled=true;}\n"
                    + "}\n"
                    + "function enviar(destino){\n"
                    + "document.getElementById(\"action\").value=destino;\n"
                    + "document.form.submit();\n"
                    + "}\n"
                    + "            jQuery(function ($)\n"
                    + "            {\n"
                    + "                $(\".tempo\").mask(\"99/99/9999\");\n"
                    + "            });\n"
                    + "</script>\n");
            out.println("<title>Inserção de Feriados</title>");
            out.println("</head>");
            out.println("<body>"
                    + "<form name=\"form\" action=\"cartaodeponto\" method=\"post\">\n"
                    + "<input type=\"hidden\" name=\"action\" id=\"action\">\n");
            //modal Insere Cidades
            out.println("<div id=\"modalinserecidades\" class=\"modal\">\n"
                    + "\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:20%\">\n"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-left:50px\">Insira o nome da cidade</span>\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"text\" style=\"margin-left:50px\" name=\"nomecidade\" style>\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin:10px; margin-left:40px\" onclick=\"enviar('registrarCidade')\" name=\"registrar\" value=\"Registrar\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px\" onclick=\"fechaJanela('modalinserecidades')\" name=\"fecha\" value=\"Fechar\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>");
            out.println("<h1 style=\"padding-left:15px\">Inserção de Feriados</h1>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<span>Primeiro feriado registrado: " + primFeriado[0] + " - " + primFeriado[1] + "</span>"
                    + "</div>"
                    + "<div class=\"col-md-12\">\n"
                    + "<span>Último feriado registrado: " + ultFeriado[0] + " - " + ultFeriado[1] + "</span>"
                    + "</div>"
                    + "<span>Nome</span>"
                    + "<input type=\"text\" name=\"nome\" style=\"margin:5px\">\n"
                    + "<span>Data</span>"
                    + "<input type=\"text\" name=\"data\" onkeyup=\"validaData(this)\" class=\"tempo data\" style=\"margin:5px\" maxlength=\"10\">\n"
                    + "<span style=\"margin-left:220px\">Feriados registrados</span>"
                    + "</div>"
                    + "<div class=\"col-md-12\">\n"
                    + "<input type=\"radio\" name=\"tipo\" onchange=\"habilitaCidades(this)\" value=\"nacional\"> Feriados nacionais\n"
                    + "<input type=\"radio\" name=\"tipo\" onchange=\"habilitaCidades(this)\" value=\"nacionalfixo\"> Feriados nacionais fixos\n"
                    + "<input type=\"radio\" name=\"tipo\" onchange=\"habilitaCidades(this)\" value=\"municipal\" checked=\"true\"> Feriados municipais\n"
                    + "<select name=\"cidades\" id=\"cidades\" onchange=\"enviar('selecionaFeriados')\">");
            for (String cidade : cidades) {
                String id = cidade.split(";;")[0];
                String nome = cidade.split(";;")[1];
                out.println("<option value=\"" + id + "\">" + nome + "</option>");
            }
            out.println("</div>"
                    + "<div class=\"col-md-12\">\n"
                    + "<span>Justificativa</span>"
                    + "</div>"
                    + "<div class=\"col-md-6\">\n"
                    + "<textarea name =\"justificativa\" rows=\"4\" style=\"min-width:400px;max-width:400px;min-height:200px;max-height:200px; margin:5px\"></textarea>\n"
                    + "</div>\n"
                    + "<div class=\"col-md-6\">\n"
                    + "<div class=\"panel panel-primary\" style=\"min-width:400px;max-width:400px;min-height:250px;max-height:250px;\">\n"
                    + "<div class=\"panel-heading\">Feriados Registrados</div>\n"
                    + "<div class=\"panel-body\" style=\"min-height:200px;max-height:200px;overflow-y:auto\">");
            for (String feriado : feriados) {
                String nome = feriado.split(";;")[0];
                String data = feriado.split(";;")[1];
                out.println("<span>" + data + " - " + nome + "</span><br>");
            }
            out.println("</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "            <input type=\"button\" class=\"btn btn-primary button\" style=\"margin:5px\" onclick=\"enviar('inserirFeriado')\" value=\"Inserir Feriado\">\n"
                    + "            <input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px\" value=\"Inserir Nova Cidade\" onclick=\"abreJanela('modalinserecidades')\">\n"
                    + "            <input type=\"button\" class=\"btn btn-primary button\" style=\"margin:5px\" onclick=\"enviar('paginaInicial')\" value=\"Voltar\">\n"
                    + "</div>\n"
                    + "</form>");
            out.println("</body>");
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
