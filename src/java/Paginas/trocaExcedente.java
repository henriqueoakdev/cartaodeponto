/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paginas;

import Classes.cartaoDePonto;
import Querys.qCartao;
import Querys.qInsercoesbd;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
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
@WebServlet(name = "trocaExcedente", urlPatterns = {"/trocaExcedente"})
public class trocaExcedente extends HttpServlet {

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

            String idCartao = (String) session.getAttribute("idCartao");
            String[] cartao = qCartao.selectCartao(idCartao);

            String chbmunicipais = cartao[7];
            String chbnacionais = cartao[8];
            String chbnacionaisf = cartao[9];

            String[] vetMunicipais = new String[1];
            String[] vetNacionais = new String[1];
            String[] vetNacionaisf = new String[1];
            if (chbmunicipais.equals("1")) {
                vetMunicipais = qInsercoesbd.selectTipoFeriado("municipal");
            }
            double dias = cp.getDias();
            String datainicio = cp.getDtinicial();
            String[] semana = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"};
            String[] feriados = qInsercoesbd.selectDiaFeriado();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(datainicio);
            c.setTime(date);
            int diaSemana = c.get(Calendar.DAY_OF_WEEK) - 1;
            int day = c.get(Calendar.DATE);
            int month = (c.get(Calendar.MONTH) + 1);
            int year = c.get(Calendar.YEAR);
            String data = day + "/" + (month < 10 ? "0" + month : month) + "/" + year;
            int count = 4;
            if (chbnacionais.equals("1")) {
                vetNacionais = qInsercoesbd.selectTipoFeriado("nacional");
            }
            if (chbnacionaisf.equals("1")) {
                vetNacionaisf = qInsercoesbd.selectTipoFeriado("nacionalfixo");
            }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Troca Excedente</title>");
            out.println("        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <script src=\"scripts/jquery.js\"></script>\n"
                    + "        <script src=\"scripts/jquery.maskedinput.js\"></script>\n"
                    + "        <script src=\"scripts/Uteis.js\"></script>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles/bootstrap.min.css\" type=\"text/css\"/>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles/consulper.css\" type=\"text/css\"/>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles/common.css\" type=\"text/css\"/>\n"
                    + "        <script src=\"scripts/Digitacao.js\"></script>\n"
                    + "        <script src=\"scripts/scripts.js\"></script>\n"
                    + "        <script src=\"http://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n"
                    + "        <link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css\" />\n");
            out.println("        <style>\n"
                    + ".tdData{\n"
                    + "width:150px;\n"
                    + "text-align: center;\n"
                    + "}\n"
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
                    + "/* Modal Content/Box */\n"
                    + ".modal-content {\n"
                    + "    background-color: #fefefe;\n"
                    + "    margin: 15% auto; /* 15% from the top and centered */\n"
                    + "    padding: 20px;\n"
                    + "    border: 1px solid #888;\n"
                    + "    width: 15%; /* Could be more or less, depending on screen size */\n"
                    + "    background-color:#F0F0F0;\n"
                    + "}\n"
                    + ".celulatopo\n"
                    + "{\n"
                    + "background-color: #DCDCDC;\n"
                    + "}\n"
                    + ".celularegular\n"
                    + "{\n"
                    + "text-align: center;\n"
                    + "}\n"
                    + ".tempo\n"
                    + "{\n"
                    + "width:50px;\n"
                    + "}\n"
                    + ".tempo2\n"
                    + "{\n"
                    + "width:40px;\n"
                    + "}\n"
                    + "</style>\n"
                    + "<script>"
                    + "function checkHabilita(checkbox,input){\n"
                    + "if(checkbox.checked==true){\n"
                    + "document.getElementById(input).disabled=false;\n"
                    + "} else {\n"
                    + "document.getElementById(input).disabled=true;\n"
                    + "}\n"
                    + "}\n"
                    + "function ajustarConformeFolgas(){\n"
                    + "var feriados = document.getElementsByClassName(\"f8\");\n"
                    + "var fds = 0;\n"
                    + "var verificador = document.getElementById(\"diario\");\n"
                    + "var numSem = document.getElementsByName(\"excSem\").length;\n"
                    + "var valorFixo = document.getElementById(\"valorFixo\");\n"
                    + "for(var i=0;i<feriados.length;i++){\n"
                    + "if(feriados[i].className==\"f8 tempo semExc\" + fds){\n"
                    + "if(verificador.checked==true){\n"
                    + "var horas=document.getElementById(\"excSem\" + fds).value.split(\":\")[0] - feriados[i].value.split(\":\")[0];\n"
                    + "var minutos=document.getElementById(\"excSem\" + fds).value.split(\":\")[1] -  feriados[i].value.split(\":\")[1];\n"
                    + "}else{\n"
                    + "var horas=document.getElementById(\"excSem\" + fds).value.split(\":\")[0] - valorFixo.value.split(\":\")[0];\n"
                    + "var minutos=document.getElementById(\"excSem\" + fds).value.split(\":\")[1] -  valorFixo.value.split(\":\")[1];\n"
                    + "}\n"
                    + "}\n"
                    + "else{\n"
                    + "fds++;\n"
                    + "}\n"
                    + "}\n"
                    + "if(horas < 0){\n"
                    + "horas=0;\n"
                    + "minutos=0;\n"
                    + "}else if(horas > 0 && minutos < 0){\n"
                    + "hora -= 1;"
                    + "minutos += 60;"
                    + "}\n"
                    + "if(typeof horas != \"undefined\"){\n"                    
                    + "if(minutos > 10){\n"
                    + "document.getElementById(\"excSem\" + fds).value =horas + \":\" + minutos ;\n"
                    + "}else{\n"
                    + "document.getElementById(\"excSem\" + fds).value =horas + \":0\" + minutos ;\n"
                    + "}\n"
                    + "}else{\n"
                    + "window.alert(\"Nenhuma categoria de feriados foi incluída neste cartão!\");\n"
                    + "}\n"
                    + "}\n"
                    + "function habilitaColuna(radio){\n"
                    + "var valor = radio.value;\n"
                    + "var totalDiario = document.getElementsByName(\"excDiario\");\n"
                    + "var excSem = document.getElementsByName(\"excSem\");\n"
                    + "if(valor==\"diario\"){\n"
                    + "for(var i=0; i<totalDiario.length; i++){\n"
                    + "totalDiario[i].disabled=false;\n"
                    + "}\n"
                    + "for(var i=0; i<excSem.length; i++){\n"
                    + "excSem[i].disabled=true;\n"
                    + "}\n"
                    + "}\n"
                    + "else{\n"
                    + "for(var i=0; i<totalDiario.length; i++){\n"
                    + "totalDiario[i].disabled=true;\n"
                    + "}\n"
                    + "for(var i=0; i<excSem.length; i++){\n"
                    + "excSem[i].disabled=false;\n"
                    + "}\n"
                    + "}\n"
                    + "}\n"
                    //função dos campos de hora que passa pro proximo campo automaticamente
                    + "            function ajustaHora(elmnt, content)\n"
                    + "            {\n"
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
                    + "function fechaPopUp(){\n"
                    + "var data = document.getElementsByName(\"datatabela\");\n"
                    + "var excDiario = document.getElementsByName(\"excDiario\");\n"
                    + "var excSem = document.getElementsByName(\"excSem\");\n"
                    + "for(var i=0; i<data.length; i++){\n"
                    + "habilitaCamposGenerico(data[i]);\n"
                    + "habilitaCamposGenerico(excDiario[i]);\n"
                    + "}\n"
                    + "for(var i=0; i<excSem.length; i++){\n"
                    + "habilitaCamposGenerico(excSem[i]);\n"
                    + "}\n"
                    + "enviar('trocaExcedente');\n"
                    + "window.opener.refresh();\n"
                    + "window.close();\n"
                    + "}\n"
                    + "            jQuery(function ($)\n"
                    + "            {\n"
                    + "                $(\".tempo\").mask(\"99:99\");\n"
                    + "            });\n"
                    + "</script>");
            out.println("</head>");
            out.println("<body>"
                    + "        <form name=\"form\" action=\"cartaodeponto\" method=\"post\">\n"
                    + "         <input type=\"hidden\" name=\"action\" id=\"action\" value=\"\" tabindex=\"1\">"
                    + "<input type=\"radio\" name=\"radioColuna\" id=\"radioDiario\" value=\"diario\" checked=\"true\" onchange=\"habilitaColuna(this)\" style=\"margin-left:367px\">\n"
                    + "<input type=\"radio\" name=\"radioColuna\" id=\"radioSemanal\" value=\"semanal\" onchange=\"habilitaColuna(this)\" style=\"margin-left:28px\">\n");
            int contFDS = 0;
            for (int i = 0; i < dias; i++) {
                out.println("<table style=\"margin-left:10px\">"
                        + "<tr class=\"" + (diaSemana == 0 ? " celulatopo" : "") + "\" name=\"" + (i % 2 == 0 ? "par" : "impar") + "\" id=\"" + data + "\">\n"
                        + "<td class=\"tdLista tdData\">\n"
                        + "<input type=\"text\" class=\"celularegular" + (diaSemana == 0 ? " celulatopo" : "") + "\" tabindex=\"" + count++ + "\" value=\"" + data + "-" + (verificaFeriado(data, vetMunicipais, vetNacionais, vetNacionaisf) ? "Fer" : semana[diaSemana]) + "\" disabled=\"true\" name=\"datatabela\">\n"
                        + "</td>");
                out.println("<td name=\"folga\" class=\"tdLista\">\n"
                        + "<input type=\"text\" name=\"f" + (verificaFeriado(data, vetMunicipais, vetNacionais, vetNacionaisf) ? "8" : diaSemana) + "\" tabindex=\"" + count++ + "\" id=\"folga" + i + "\" disabled=\"true\" class=\"tempo2\" value=\"" + cp.folgas[i] + "\">\n"
                        + "</td>\n"
                        + "<td>\n"
                        + "<input type=\"text\" name=\"totaldiario\" id=\"totaldiario\" tabindex=\"" + count++ + "\" value=\"" + cp.totaldiario[i] + "\" disabled=\"true\">\n"
                        + "</td>\n"
                        + "<td>"
                        + "<input type=\"text\" name=\"excDiario\" class=\"f" + (verificaFeriado(data, vetMunicipais, vetNacionais, vetNacionaisf) ? "8" : diaSemana) + " tempo semExc" + contFDS + "\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" id=\"excDiario" + i + "\" value=\"" + verificaDia(diaSemana, cp) + "\" maxlength=\"5\">\n"
                        + "</td>"
                        + "<td>");
                if (diaSemana == 0 || i == (dias - 1)) {
                    out.println("<input type=\"text\" name=\"excSem\" class=\"tempo sem" + contFDS + "\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" id=\"excSem" + contFDS + "\" disabled=\"true\" value=\"" + cp.excSem[contFDS++] + "\" maxlength=\"5\">\n");
                    count++;
                }
                out.println("</td>"
                        + "</tr>\n"
                        + "</table>\n");

                diaSemana++;
                c.add(Calendar.DATE, 1);
                day = c.get(Calendar.DATE);
                month = (c.get(Calendar.MONTH) + 1);
                year = c.get(Calendar.YEAR);
                data = day + "/" + (month < 10 ? "0" + month : month) + "/" + year;
                if (diaSemana >= 7) {
                    diaSemana = 0;
                }
            }
            //modal Ajustar Conforme Folgas
            out.println("<div id=\"modalajustarcf\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:230px; height:110px\">\n"
                    + "Diário <input type=\"radio\" name=\"opcaoAjustarCF\" id=\"diario\" checked=\"true\">\n"
                    + "Fixo <input type=\"radio\" name=\"opcaoAjustarCF\" id=\"fixo\" onchange=\"checkHabilita(this, 'valorFixo')\">\n"
                    + "<input type=\"text\" class=\"tempo\" disabled=\"true\" name=\"valorFixo\" id=\"valorFixo\" style=\"width:50px\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-top:10px; margin-left:20px; width:70px\" onclick=\"ajustarConformeFolgas()\" value=\"Ok\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-top:-57px; margin-left:100px\" onclick=\"fechaJanela('modalajustarcf')\" value=\"Fechar\">\n"
                    + "</div>\n"
                    + "</div>");
            out.println("<div class=\"col-md-12\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-top:5px; margin-left:-5px\" onclick=\"fechaPopUp()\" value=\"Ok\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-top:5px\" onclick=\"abreJanela('modalajustarcf')\" value=\"Ajustar Conforme Folgas\">\n"
                    + "</div>\n");
            out.println("</form>"
                    + "</body>");
            out.println("</html>");
        }
    }

    private String verificaDia(int diaSemana, cartaoDePonto cp) {
        String valorExcDiario = "";

        if (diaSemana == 0) {
            valorExcDiario = cp.excDom;
        } else if (diaSemana == 1) {
            valorExcDiario = cp.excSeg;
        } else if (diaSemana == 2) {
            valorExcDiario = cp.excTer;
        } else if (diaSemana == 3) {
            valorExcDiario = cp.excQua;
        } else if (diaSemana == 4) {
            valorExcDiario = cp.excQui;
        } else if (diaSemana == 5) {
            valorExcDiario = cp.excSex;
        } else if (diaSemana == 6) {
            valorExcDiario = cp.excSab;
        }

        return valorExcDiario;
    }

    private boolean verificaFeriado(String data, String[] municipais, String[] nacionais, String[] nacionaisf) {
        for (String feriado : municipais) {
            if (feriado != null && feriado.equals(data)) {
                return true;
            }
        }
        for (String feriado : nacionais) {
            if (feriado != null && feriado.equals(data)) {
                return true;
            }
        }
        for (String feriado : nacionaisf) {
            if (feriado != null && feriado.equals(data)) {
                return true;
            }
        }
        return false;
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
            Logger.getLogger(trocaExcedente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(trocaExcedente.class.getName()).log(Level.SEVERE, null, ex);
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
