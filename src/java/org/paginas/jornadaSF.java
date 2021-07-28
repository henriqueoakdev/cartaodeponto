package org.paginas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Classes.cartaoDePonto;
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
@WebServlet(name = "jornadaSF", urlPatterns = {"/jornadaSF"})
public class jornadaSF extends HttpServlet {

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
            double dias;
            try {
                dias = (double) session.getAttribute("intervaloSF");
            } catch (Exception e) {
                dias = 0;
            }
            String datainicio = (String) session.getAttribute("datainicioSF");
            if (datainicio == null) {
                datainicio = (String) session.getAttribute("datainicio");
            }
            String dtinicio = (String) session.getAttribute("datainicio");//data inicio do cartao
            String dtfinal = (String) session.getAttribute("datafinal");//data final do cartao
            String[] semana = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Fer"};
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(datainicio);
            c.setTime(date);
            int diaSemana = 0;
            int day = c.get(Calendar.DATE);
            int count = 5;

            boolean chbmunicipais = (boolean) session.getAttribute("municipal");
            boolean chbnacionais = (boolean) session.getAttribute("nacional");
            boolean chbnacionaisf = (boolean) session.getAttribute("nacionalf");
            String[] vetMunicipais = new String[1];
            String[] vetNacionais = new String[1];
            String[] vetNacionaisf = new String[1];
            if (chbmunicipais) {
                vetMunicipais = qInsercoesbd.selectTipoFeriado("municipal");
            }
            if (chbnacionais) {
                vetNacionais = qInsercoesbd.selectTipoFeriado("nacional");
            }
            if (chbnacionaisf) {
                vetNacionaisf = qInsercoesbd.selectTipoFeriado("nacionalfixo");
            }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Jornada Semanal Fixa</title>"
                    + "<link rel=\"stylesheet\" href=\"styles/bootstrap.min.css\" type=\"text/css\"/>\n"
                    + "<link rel=\"stylesheet\" href=\"styles/consulper.css\" type=\"text/css\"/>\n"
                    + "<link rel=\"stylesheet\" href=\"styles/common.css\" type=\"text/css\"/>\n"
                    + "        <script src=\"scripts/jquery.js\"></script>\n"
                    + "        <script src=\"scripts/jquery.maskedinput.js\"></script>\n"
                    + "        <script src=\"scripts/Uteis.js\"></script>\n"
                    + "        <script src=\"scripts/Digitacao.js\"></script>\n"
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
                    + ".tdLista{\n"
                    + "padding:3px;\n"
                    + "text-align:center;"
                    + "}\n"
                    + "        </style>\n"
                    + "<script>"
                    + "function somaHoras(x,data)\n"
                    + "{\n"
                    + "var E1=document.getElementsByName(\"txbE1\")[x].value;\n"
                    + "var E2=document.getElementsByName(\"txbE2\")[x].value;\n"
                    + "var E3=document.getElementsByName(\"txbE3\")[x].value;\n"
                    + "var E4=document.getElementsByName(\"txbE4\")[x].value;\n"
                    + "var E5=document.getElementsByName(\"txbE5\")[x].value;\n"
                    + "var S1=document.getElementsByName(\"txbS1\")[x].value;\n"
                    + "var S2=document.getElementsByName(\"txbS2\")[x].value;\n"
                    + "var S3=document.getElementsByName(\"txbS3\")[x].value;\n"
                    + "var S4=document.getElementsByName(\"txbS4\")[x].value;\n"
                    + "var S5=document.getElementsByName(\"txbS5\")[x].value;\n"
                    //calculo conversao para cada vetor
                    + "totalhoras=converteHoras(E1,S1)+converteHoras(E2,S2)+converteHoras(E3,S3)+converteHoras(E4,S4)+converteHoras(E5,S5);\n"
                    + "if(totalhoras>24)"
                    + "{window.alert(\"Total de horas em um dia acima de 24!\");"
                    + "document.getElementById(data).style=\"background-color:red\";\n"
                    + "}"
                    + "document.getElementsByName(\"totalHoras\")[x].value=totalhoras;\n"
                    + "}\n"
                    //converte hora para decimal
                    + "function converteHoras(ex,sx)\n"
                    + "{\n"
                    + "if(ex.length==5 && sx.length==5){\n"
                    + "var ehm=ex.split(\":\");\n"
                    + "var shm=sx.split(\":\");\n"
                    + "var resultE = parseFloat(ehm[0])+ parseFloat((ehm[1]/60));\n"
                    + "var resultS = parseFloat(shm[0])+ parseFloat((shm[1]/60));\n"
                    + "if(resultE<resultS){\n"
                    + "return resultS-resultE;\n"
                    + "} else{\n"
                    + "return 0;\n"
                    + "}\n"
                    + "}\n"
                    + "else{\n"
                    + "return 0;\n"
                    + "}\n"
                    + "}\n"
                    + "function replicaDiaSemana(campo){\n"
                    + "var classe=campo.class;\n"
                    + "window.alert(\"classe: \"+classe);\n"
                    + "}\n"
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
                    //+ "replicaDiaSemana(elmnt);\n"
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
                    + "function completaJornada(){\n"
                    + "enviar('jornadaSF');\n"
                    + "window.close();\n"
                    + "window.opener.refresh();\n"
                    + "}\n"
                    + "function exibeColunas(num) {\n"
                    + "    var e2 = document.getElementsByName(\"E2\");\n"
                    + "    var s2 = document.getElementsByName(\"S2\");\n"
                    + "    var e3 = document.getElementsByName(\"E3\");\n"
                    + "    var s3 = document.getElementsByName(\"S3\");\n"
                    + "    var e4 = document.getElementsByName(\"E4\");\n"
                    + "    var s4 = document.getElementsByName(\"S4\");\n"
                    + "    var e5 = document.getElementsByName(\"E5\");\n"
                    + "    var s5 = document.getElementsByName(\"S5\");\n"
                    + "    var e6 = document.getElementsByName(\"E6\");\n"
                    + "    var s6 = document.getElementsByName(\"S6\");\n"
                    + "    var e7 = document.getElementsByName(\"E7\");\n"
                    + "    var s7 = document.getElementsByName(\"S7\");\n"
                    + "    var e8 = document.getElementsByName(\"E8\");\n"
                    + "    var s8 = document.getElementsByName(\"S8\");\n"
                    + "    var e9 = document.getElementsByName(\"E9\");\n"
                    + "    var s9 = document.getElementsByName(\"S9\");\n"
                    + "    var e10 = document.getElementsByName(\"E10\");\n"
                    + "    var s10 = document.getElementsByName(\"S10\");\n"
                    + "    var e11 = document.getElementsByName(\"E11\");\n"
                    + "    var s11 = document.getElementsByName(\"S11\");\n"
                    + "    var e12 = document.getElementsByName(\"E12\");\n"
                    + "    var s12 = document.getElementsByName(\"S12\");\n"
                    + "    var e13 = document.getElementsByName(\"E13\");\n"
                    + "    var s13 = document.getElementsByName(\"S13\");\n"
                    + "    var e14 = document.getElementsByName(\"E14\");\n"
                    + "    var s14 = document.getElementsByName(\"S14\");\n"
                    + "    var e15 = document.getElementsByName(\"E15\");\n"
                    + "    var s15 = document.getElementsByName(\"S15\");\n"
                    + "\n"
                    + "    ocultaColunaSelecionada(e2, \"txbE2\");\n"
                    + "    ocultaColunaSelecionada(s2, \"txbS2\");\n"
                    + "    ocultaColunaSelecionada(e3, \"txbE3\");\n"
                    + "    ocultaColunaSelecionada(s3, \"txbS3\");\n"
                    + "    ocultaColunaSelecionada(e4, \"txbE4\");\n"
                    + "    ocultaColunaSelecionada(s4, \"txbS4\");\n"
                    + "    ocultaColunaSelecionada(e5, \"txbE5\");\n"
                    + "    ocultaColunaSelecionada(s5, \"txbS5\");\n"
                    + "    ocultaColunaSelecionada(e6, \"txbE6\");\n"
                    + "    ocultaColunaSelecionada(s6, \"txbS6\");\n"
                    + "    ocultaColunaSelecionada(e7, \"txbE7\");\n"
                    + "    ocultaColunaSelecionada(s7, \"txbS7\");\n"
                    + "    ocultaColunaSelecionada(e8, \"txbE8\");\n"
                    + "    ocultaColunaSelecionada(s8, \"txbS8\");\n"
                    + "    ocultaColunaSelecionada(e9, \"txbE9\");\n"
                    + "    ocultaColunaSelecionada(s9, \"txbS9\");\n"
                    + "    ocultaColunaSelecionada(e10, \"txbE10\");\n"
                    + "    ocultaColunaSelecionada(s10, \"txbS10\");\n"
                    + "    ocultaColunaSelecionada(e11, \"txbE11\");\n"
                    + "    ocultaColunaSelecionada(s11, \"txbS11\");\n"
                    + "    ocultaColunaSelecionada(e12, \"txbE12\");\n"
                    + "    ocultaColunaSelecionada(s12, \"txbS12\");\n"
                    + "    ocultaColunaSelecionada(e13, \"txbE13\");\n"
                    + "    ocultaColunaSelecionada(s13, \"txbS13\");\n"
                    + "    ocultaColunaSelecionada(e14, \"txbE14\");\n"
                    + "    ocultaColunaSelecionada(s14, \"txbS14\");\n"
                    + "    ocultaColunaSelecionada(e15, \"txbE15\");\n"
                    + "    ocultaColunaSelecionada(s15, \"txbS15\");\n"
                    + "\n"
                    + "    switch (num) {\n"
                    + "        case \"15\":\n"
                    + "            exibeColunaSelecionada(e15, \"txbE15\");\n"
                    + "            exibeColunaSelecionada(s15, \"txbS15\");\n"
                    + "        case \"14\":\n"
                    + "            exibeColunaSelecionada(e14, \"txbE14\");\n"
                    + "            exibeColunaSelecionada(s14, \"txbS14\");\n"
                    + "        case \"13\":\n"
                    + "            exibeColunaSelecionada(e13, \"txbE13\");\n"
                    + "            exibeColunaSelecionada(s13, \"txbS13\");\n"
                    + "        case \"12\":\n"
                    + "            exibeColunaSelecionada(e12, \"txbE12\");\n"
                    + "            exibeColunaSelecionada(s12, \"txbS12\");\n"
                    + "        case \"11\":\n"
                    + "            exibeColunaSelecionada(e11, \"txbE11\");\n"
                    + "            exibeColunaSelecionada(s11, \"txbS11\");\n"
                    + "        case \"10\":\n"
                    + "            exibeColunaSelecionada(e10, \"txbE10\");\n"
                    + "            exibeColunaSelecionada(s10, \"txbS10\");\n"
                    + "        case \"9\":\n"
                    + "            exibeColunaSelecionada(e9, \"txbE9\");\n"
                    + "            exibeColunaSelecionada(s9, \"txbS9\");\n"
                    + "        case \"8\":\n"
                    + "            exibeColunaSelecionada(e8, \"txbE8\");\n"
                    + "            exibeColunaSelecionada(s8, \"txbS8\");\n"
                    + "        case \"7\":\n"
                    + "            exibeColunaSelecionada(e7, \"txbE7\");\n"
                    + "            exibeColunaSelecionada(s7, \"txbS7\");\n"
                    + "        case \"6\":\n"
                    + "            exibeColunaSelecionada(e6, \"txbE6\");\n"
                    + "            exibeColunaSelecionada(s6, \"txbS6\");\n"
                    + "        case \"5\":\n"
                    + "            exibeColunaSelecionada(e5, \"txbE5\");\n"
                    + "            exibeColunaSelecionada(s5, \"txbS5\");\n"
                    + "        case \"4\":\n"
                    + "            exibeColunaSelecionada(e4, \"txbE4\");\n"
                    + "            exibeColunaSelecionada(s4, \"txbS4\");\n"
                    + "        case \"3\":\n"
                    + "            exibeColunaSelecionada(e3, \"txbE3\");\n"
                    + "            exibeColunaSelecionada(s3, \"txbS3\");\n"
                    + "        case \"2\":\n"
                    + "            exibeColunaSelecionada(e2, \"txbE2\");\n"
                    + "            exibeColunaSelecionada(s2, \"txbS2\");\n"
                    + "            break;\n"
                    + "    }\n"
                    + "}\n"
                    + "function exibeColunaSelecionada(coluna, campos) {\n"
                    + "    apareceColunas(document.getElementsByName(campos));\n"
                    + "    for (var i = 0; i < coluna.length; i++) {\n"
                    + "        coluna[i].style.display = \"\";\n"
                    + "    }\n"
                    + "}\n"
                    + "\n"
                    + "function ocultaColunaSelecionada(coluna, campos) {\n"
                    + "    apagaColunas(document.getElementsByName(campos));\n"
                    + "    for (var i = 0; i < coluna.length; i++) {\n"
                    + "        coluna[i].style.display = \"none\";\n"
                    + "    }\n"
                    + "}\n"
                    + "\n"
                    + "function apagaColunas(colunas) {\n"
                    + "    for (var i = 0; i < colunas.length; i++) {\n"
                    + "        colunas[i].disabled = true;\n"
                    + "    }\n"
                    + "}\n"
                    + "\n"
                    + "function apareceColunas(colunas) {\n"
                    + "    for (var i = 0; i < colunas.length; i++) {\n"
                    + "        colunas[i].disabled = false;\n"
                    + "    }\n"
                    + "}"
                    + "</script>");
            out.println("</head>\n");
            out.println("<body>"
                    + "<form name=\"form\" action=\"cartaodeponto\" method=\"post\">\n"
                    + "<input type=\"hidden\" name=\"action\" id=\"action\">\n"
                    + "<span style=\"margin-right:5px; margin-left:20px\">Data Inicial</span>"
                    + "<input type=\"text\" name=\"datainicial\" value=\"" + cp.dtinicial + "\" style=\"margin-right:10px\">"
                    + "<span style=\"margin-right:5px\">Data Final</span>"
                    + "<input type=\"text\" name=\"datafinal\" value=\"" + cp.dtfinal + "\">"
                    + "<select name=\"selectColunas\" id=\"selectColunas\" onchange=\"exibeColunas(this.value)\">\n");
            for (int i = 1; i < 16; i++) {
                if (i != Integer.parseInt(cp.numColunas)) {
                    out.println("<option value=\"" + i + "\">" + i + "</option>\n");
                } else {
                    out.println("<option value=\"" + i + "\" selected=\"true\">" + i + "</option>\n");
                }
            }
            out.println("</select>"
                    + "<div class=\"col-md-10\">\n"
                    + "<table>\n");
            for (int i = 0; i < 8; i++) {
                out.println("                <tr class=\"" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"" + (i % 2 == 0 ? "par" : "impar") + "\" id=\"\">\n"
                        + "                    <td class=\"tdLista tdData\">\n"
                        + "                        <input type=\"text\" class=\"celularegular" + (diaSemana == 7 ? " celulatopo" : "") + "\" value=\"" + semana[diaSemana] + "\" disabled=\"true\" name=\"datatabela\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E1\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE1\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S1\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS1\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E2\" class=\"" + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE2\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S2\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS2\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E3\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE3\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S3\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS3\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E4\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE4\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S4\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS4\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E5\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE5\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S5\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS5\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E6\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE6\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S6\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS6\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E7\" class=\"" + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE7\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S7\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS7\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E8\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE8\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S8\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS8\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E9\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE9\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S9\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS9\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E10\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE10\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S10\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS10\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E11\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE11\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S11\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS11\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E12\" class=\"" + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE12\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S12\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS12\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E13\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE13\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S13\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS13\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E14\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE14\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S14\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS14\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E15\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbE15\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S15\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\" name=\"txbS15\" tabindex=\"" + (count += 2) + "\" onkeyup=\"ajustaHora(this, this.value)\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"total\" class=\"tdLista " + (diaSemana == 7 ? " celulatopo" : "") + "\">\n"
                        + "                        <input type=\"text\" value=\"\" name=\"totalHoras\" disabled=\"true\" class=\"tempo" + (diaSemana == 7 ? " celulatopo" : "") + "\">\n");
                out.println("                </tr>\n");
                diaSemana++;
                count++;
            }
            out.println("</table>"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary\" name=\"ok\" style=\"margin:5px\" value=\"OK\" onclick=\"completaJornada()\">\n"
                    + "</div>\n"
                    + "</form>\n");
            out.println("</body>\n");
            out.println("</html>\n");
        }
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
            Logger.getLogger(jornadaSF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(jornadaSF.class.getName()).log(Level.SEVERE, null, ex);
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
