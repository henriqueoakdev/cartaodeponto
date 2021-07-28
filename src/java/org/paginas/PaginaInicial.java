package org.paginas;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */


public class PaginaInicial extends HttpServlet {

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
        response.setContentType("text/html;charset = UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("caminho", "");
        
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Página Inicial</title>"
                    + "        <script src=\"scripts/jquery.js\"></script>\n"
                    + "        <script src=\"scripts/jquery.maskedinput.js\"></script>\n"
                    + "        <script src=\"scripts/Uteis.js\"></script>\n"
                    + "<link rel=\"stylesheet\" href=\"styles/bootstrap.min.css\" type=\"text/css\"/>\n"
                    + "<link rel=\"stylesheet\" href=\"styles/consulper.css\" type=\"text/css\"/>\n"
                    + "<link rel=\"stylesheet\" href=\"styles/common.css\" type=\"text/css\"/>\n"
                    + "<script>"
                    + "             function enviar(destino){\n"
                    + "             document.getElementById(\"action\").value=destino;\n"
                    + "                    var lista = document.getElementsByName(\"totalHoras\");\n"
                    + "                    for (var x = 0; x < lista.length; x++) {\n"
                    + "                        lista[x].disabled=false;\n"
                    + "                    }\n"
                    + "             document.form.submit();\n"
                    + "             }\n"
                    + "</script>\n"
                    + "<style>\n"
                    + ".classDiv{\n"
                    + "height:100%;\n"
                    + "position:absolute;\n"
                    + "width:50%;\n"
                    + "margin-right:50%;\n"
                    + "padding-top:20%;\n"
                    + "cursor:pointer;\n"
                    + "}\n"
                    + ".classDiv:hover{\n"
                    + "background-color:#0174DF;\n"
                    + "}\n"
                    + "body{\n"
                    + "margin-top:0px;\n"
                    + "}\n"
                    + ".fonte{\n"
                    + "font-size:50px;"
                    + "margin-left:20%;"
                    + "}\n"
                    + "</style>");
            out.println("</head>");
            out.println("<body>"
                    + "        <form name=\"form\" action=\"cartaodeponto\" method=\"post\">\n"
                    + "         <input type=\"hidden\" name=\"action\" id=\"action\" value=\"\" tabindex=\"1\">\n");
            out.println("<div class=\"col-md-5\" style=\"margin-top:160px; margin-left:150px\">"
                    + "<h1 style=\"margin-left:70px;\">Inserções no Sistema</h1>"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px; margin-left: 87px\" value=\"Inserir Novo Cartão\" onclick=\"enviar('criarCartao')\">"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px\" value=\"Inserir Novo Feriado\" onclick=\"enviar('novoFeriado')\">\n"
                    + "</div>"
                    + "<div class=\"col-md-5\" style=\"margin-top:160px\">"
                    + "<h1 style=\"margin-left:70px\">Buscas no Sistema</h1>"
                    + "<input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px; margin-left:145px\" value=\"Buscar um Cartão\" onclick=\"enviar('buscaTodosCartoes')\">"
                    + "</div>");
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
