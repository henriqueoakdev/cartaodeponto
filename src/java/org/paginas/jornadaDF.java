/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paginas;

import Classes.cartaoDePonto;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
@WebServlet(name = "jornadaDF", urlPatterns = {"/jornadaDF"})
public class jornadaDF extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            cartaoDePonto cp = (cartaoDePonto) session.getAttribute("cartaoDePonto");
            /* TODO output your page here. You may use following sample code. */
            int count = 3;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet jornadaDF</title>");
            out.println("<link rel=\"stylesheet\" href=\"styles/bootstrap.min.css\" type=\"text/css\"/>\n"
                    + "<link rel=\"stylesheet\" href=\"styles/consulper.css\" type=\"text/css\"/>\n"
                    + "<link rel=\"stylesheet\" href=\"styles/common.css\" type=\"text/css\"/>\n"
                    + "        <script src=\"scripts/jquery.js\"></script>\n"
                    + "        <script src=\"scripts/jquery.maskedinput.js\"></script>\n"
                    + "        <script src=\"scripts/Uteis.js\"></script>\n"
                    + "        <style>\n"
                    + "         .tdData{\n"
                    + "         width:150px;\n"
                    + "          text-align: center;\n"
                    + "         }\n"
                    + "            .celulatopo\n"
                    + "            {\n"
                    + "                background-color: #DCDCDC;\n"
                    + "            }\n"
                    + "            .celularegular\n"
                    + "            {\n"
                    + "                text-align: center;\n"
                    + "            }\n"
                    + "            .tempo\n"
                    + "            {\n"
                    + "                width:40px;\n"
                    + "            }\n"
                    + "            .tempo2\n"
                    + "            {\n"
                    + "                width:100px;\n"
                    + "            }\n"
                    + "            .button{\n"
                    + "            width:160px;\n"
                    + "            }\n"
                    + "            .button2{\n"
                    + "            width:230px;\n"
                    + "            }\n"
                    + "             .button3{\n"
                    + "            width:90px;\n"
                    + "            }\n"
                    + ".tdLista{\n"
                    + "padding:3px;\n"
                    + "text-align:center;"
                    + "}\n"
                    + "        </style>\n"
                    + "<script>"
                    + "            jQuery(function ($)\n"
                    + "            {\n"
                    + "                $(\".tempo\").mask(\"99:99\");\n"
                    + "            });\n"
                    + "            jQuery(function ($)\n"
                    + "            {\n"
                    + "                $(\".tempo2\").mask(\"99/99/9999\");\n"
                    + "            });\n"
                    + "            function ajustaHora(elmnt, content)\n"
                    + "            {\n"
                    + "                if (content.replace(\":\", \"\") > 2359)\n"
                    + "                {\n"
                    + "                    elmnt.value = \"23:59\";\n"
                    + "                } else if (content.replace(\":\", \"\") < 0)\n"
                    + "                {\n"
                    + "                    elmnt.value = \"0\";\n"
                    + "                }\n"
                    + "                if (content.length === elmnt.maxLength) \n"
                    + "                {\n"
                    + "                    next = elmnt.tabIndex; \n"
                    + "                    var teste = document.forms[0].elements[next].disabled;\n"
                    + "                    while ( teste === true) \n"
                    + "                    {\n"
                    + "                        var x = next;\n"
                    + "                        document.forms[0].elements[next].disabled = \"false\";\n"
                    + "                        next = document.forms[0].elements[next].tabIndex;\n"
                    + "                        document.forms[0].elements[x].disabled = \"true\";\n"
                    + "                        teste = document.forms[0].elements[next].disabled;\n"
                    + "                    }\n"
                    + "                    if (next < document.forms[0].elements.length) {\n"
                    + "                        document.forms[0].elements[next].focus();\n"
                    + "                    }\n"
                    + "                }\n"
                    + "            }\n"
                    + "function somaHoras(x,data)\n"
                    + "{\n"
                    + "var E1=document.getElementsByName(\"txbE1\")[x].value;\n"
                    + "var E2=document.getElementsByName(\"txbE2\")[x].value;\n"
                    + "var E3=document.getElementsByName(\"txbE3\")[x].value;\n"
                    + "var E4=document.getElementsByName(\"txbE4\")[x].value;\n"
                    + "var E5=document.getElementsByName(\"txbE5\")[x].value;\n"
                    + "var E6=document.getElementsByName(\"txbE6\")[x].value;\n"
                    + "var E7=document.getElementsByName(\"txbE7\")[x].value;\n"
                    + "var E8=document.getElementsByName(\"txbE8\")[x].value;\n"
                    + "var E9=document.getElementsByName(\"txbE9\")[x].value;\n"
                    + "var E10=document.getElementsByName(\"txbE10\")[x].value;\n"
                    + "var E11=document.getElementsByName(\"txbE11\")[x].value;\n"
                    + "var E12=document.getElementsByName(\"txbE12\")[x].value;\n"
                    + "var E13=document.getElementsByName(\"txbE13\")[x].value;\n"
                    + "var E14=document.getElementsByName(\"txbE14\")[x].value;\n"
                    + "var E15=document.getElementsByName(\"txbE15\")[x].value;\n"
                    + "var S1=document.getElementsByName(\"txbS1\")[x].value;\n"
                    + "var S2=document.getElementsByName(\"txbS2\")[x].value;\n"
                    + "var S3=document.getElementsByName(\"txbS3\")[x].value;\n"
                    + "var S4=document.getElementsByName(\"txbS4\")[x].value;\n"
                    + "var S5=document.getElementsByName(\"txbS5\")[x].value;\n"
                    + "var S6=document.getElementsByName(\"txbS6\")[x].value;\n"
                    + "var S7=document.getElementsByName(\"txbS7\")[x].value;\n"
                    + "var S8=document.getElementsByName(\"txbS8\")[x].value;\n"
                    + "var S9=document.getElementsByName(\"txbS9\")[x].value;\n"
                    + "var S10=document.getElementsByName(\"txbS10\")[x].value;\n"
                    + "var S11=document.getElementsByName(\"txbS11\")[x].value;\n"
                    + "var S12=document.getElementsByName(\"txbS12\")[x].value;\n"
                    + "var S13=document.getElementsByName(\"txbS13\")[x].value;\n"
                    + "var S14=document.getElementsByName(\"txbS14\")[x].value;\n"
                    + "var S15=document.getElementsByName(\"txbS15\")[x].value;\n"
                    //calculo conversao para cada vetor
                    + "totalhoras=converteHoras(E1,S1)+converteHoras(E2,S2)+converteHoras(E3,S3)+converteHoras(E4,S4)+converteHoras(E5,S5);\n"
                    + "if(totalhoras>24)\n"
                    + "{\n"
                    + "window.alert(\"Total de horas em um dia acima de 24!\");\n"
                    + "document.getElementById(data).style=\"background-color:red\";\n"
                    + "}\n"
                    + "document.getElementsByName(\"totalHoras\")[x].value=totalhoras;\n"
                    + "}\n"
                    + "function completaJornada(){\n"
                    + "enviar('jornadaDF');\n"
                    + "window.close();\n"
                    + "window.opener.refresh();\n"
                    + "}\n"
                    + "function habilitaDia(chbDia,x){"
                    + "if(chbDia.checked==false){\n"
                    + "document.getElementsByName(\"txbE1\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE2\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE3\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE4\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE5\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE6\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE7\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE8\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE9\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE10\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE11\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE12\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE13\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE14\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbE15\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS1\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS2\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS3\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS4\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS5\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS6\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS7\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS8\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS9\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS10\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS11\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS12\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS13\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS14\")[x].disabled=true;\n"
                    + "document.getElementsByName(\"txbS15\")[x].disabled=true;\n"
                    + "} else {\n"
                    + "document.getElementsByName(\"txbE1\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE2\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE3\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE4\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE5\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE6\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE7\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE8\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE9\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE10\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE11\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE12\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE13\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE14\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbE15\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS1\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS2\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS3\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS4\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS5\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS6\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS7\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS8\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS9\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS10\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS11\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS12\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS13\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS14\")[x].disabled=false;\n"
                    + "document.getElementsByName(\"txbS15\")[x].disabled=false;\n"
                    + "}\n"
                    + "}\n"
                    + "</script>"
                    + "</head>");
            out.println("<body>"
                    + "<form name=\"form\" action=\"cartaodeponto\" method=\"post\">\n"
                    + "<input type=\"hidden\" name=\"action\" id=\"action\">\n"
                    + "<span style=\"margin-right:5px; margin-left:20px\">Data Inicial</span>"
                    + "<input type=\"text\" name=\"datainicial\" value=\"" + cp.dtinicial + "\" style=\"margin-right:10px\">"
                    + "<span style=\"margin-right:5px\">Data Final</span>"
                    + "<input type=\"text\" name=\"datafinal\" value=\"" + cp.dtfinal + "\">"
                    + "<div class=\"col-md-12\">\n"
                    + "<span style=\"margin-left:30px; margin-right:10px\" class=\"celulatopo\">Fixar</span>"
                    + "<span class=\"celulatopo\">Dia</span>"
                    + "</div>\n"
                    + "<table>");
            for (int i = 1; i <= 31; i++) {
                out.println("                <tr class=\"\" name=\"\" id=\"\">\n"
                        + "                    <td class=\"tdLista tdData\">\n"
                        + "                        <input type=\"checkbox\" onchange=\"habilitaDia(this," + (i - 1) + ")\" name=\"chbDia\" style=\"margin-left:5px; margin-right:22px\" value=\"" + i + "\">" + i + "\n"
                        + "                    </td>");
                out.println("                    <td name=\"E1\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE1\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S1\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS1\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E2\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE2\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S2\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS2\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E3\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE3\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S3\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS3\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E4\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE4\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S4\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS4\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E5\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE5\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S5\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS5\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E6\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE6\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S6\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS6\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E7\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE7\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S7\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS7\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E8\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE8\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S8\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS8\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E9\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE9\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S9\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS9\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E10\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE10\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S10\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS10\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E11\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE11\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S11\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS11\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E12\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE12\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S12\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS12\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E13\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE13\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S13\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS13\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E14\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE14\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S14\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS14\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E15\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbE15\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S15\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" class=\"tempo\" name=\"txbS15\" tabindex=\"" + (count += 4) + "\" onkeyup=\"ajustaHora(this, this.value)\" disabled=\"true\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"total\" class=\"tdLista\">\n"
                        + "                        <input type=\"text\" value=\"\" name=\"totalHoras\" disabled=\"true\" class=\"tempo\">\n");
                out.println("                </tr>\n");
                count++;
            }
            out.println("</table>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary\" name=\"ok\" style=\"margin:5px\" value=\"OK\" onclick=\"completaJornada()\">\n"
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(jornadaDF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(jornadaDF.class.getName()).log(Level.SEVERE, null, ex);
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
