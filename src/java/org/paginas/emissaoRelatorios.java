/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paginas;

import Classes.cartaoDePonto;
import Querys.qCartao;
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
@WebServlet(name = "emissaoRelatorios", urlPatterns = {"/emissaoRelatorios"})
public class emissaoRelatorios extends HttpServlet {

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
        HttpSession session = request.getSession();
        cartaoDePonto cp = (cartaoDePonto) session.getAttribute("cartaoDePonto");
        String[] cartao;
        cartao = qCartao.selectExcedentes(cp.id);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Emissão de Relatórios</title>"
                    + "        <script src=\"scripts/jquery.js\"></script>\n"
                    + "        <script src=\"scripts/Digitacao.js\"></script>\n"
                    + "        <script src=\"scripts/jquery.maskedinput.js\"></script>\n"
                    + "        <script src=\"scripts/Uteis.js\"></script>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles/bootstrap.min.css\" type=\"text/css\"/>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles/consulper.css\" type=\"text/css\"/>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles/common.css\" type=\"text/css\"/>\n"
                    + "        <script src=\"scripts/scripts.js\"></script>\n"
                    + "        <script src=\"http://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n"
                    + "        <link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css\" />\n"
                    + "<style>"
                    + ".tempo{\n"
                    + "}\n"
                    + ".txt{\n"
                    + "width:50px;\n"
                    + "margin-right:35px;\n"
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
                    + "\n"
                    + "/* Modal Content/Box */\n"
                    + ".modal-content {\n"
                    + "    background-color: #fefefe;\n"
                    + "    margin: 15% auto; /* 15% from the top and centered */\n"
                    + "    padding: 20px;\n"
                    + "    border: 1px solid #888;\n"
                    + "    width: 20%; /* Could be more or less, depending on screen size */\n"
                    + "    background-color:#F0F0F0;\n"
                    + "}\n"
                    + ".tdLista{\n"
                    + "padding:3px;\n"
                    + "text-align:center;"
                    + "}\n"
                    + ".campo{\n"
                    + "width:55px;\n"
                    + "margin-right:35px;\n"
                    + "margin-left:-30px;\n"
                    + "}\n"
                    + "</style>"
                    + "<script>"
                    + "function checkVisualizacao(checkbox,input){\n"
                    + "if(checkbox.checked==true){\n"
                    + "document.getElementById(input).style=\"margin:5px; display:normal\";\n"
                    + "} else {\n"
                    + "document.getElementById(input).style=\"margin:5px; display:none\";\n"
                    + "}\n"
                    + "}\n"
                    + "function checkVisualizacao(checkbox,input1,input2){\n"
                    + "if(checkbox.checked==true){\n"
                    + "document.getElementById(input1).style=\"margin:5px; display:normal\";\n"
                    + "document.getElementById(input2).style=\"margin:5px; display:normal\";\n"
                    + "} else {\n"
                    + "document.getElementById(input1).style=\"margin:5px; display:none\";\n"
                    + "document.getElementById(input2).style=\"margin:5px; display:none\";\n"
                    + "}\n"
                    + "}\n"
                    + "function checkHabilita(checkbox,input){\n"
                    + "if(checkbox.checked==true){\n"
                    + "document.getElementById(input).disabled=false;\n"
                    + "} else {\n"
                    + "document.getElementById(input).disabled=true;\n"
                    + "}\n"
                    + "}\n"
                    + "function checkHabilita2(checkbox,input,input2){\n"
                    + "if(checkbox.checked==true){\n"
                    + "document.getElementById(input).disabled=false;\n"
                    + "document.getElementById(input2).disabled=false;\n"
                    + "} else {\n"
                    + "document.getElementById(input).disabled=true;\n"
                    + "}\n"
                    + "}\n"
                    + "function abreJanela(campo){\n"
                    + "document.getElementById(campo).style.display = \"block\";\n"
                    + "}\n"
                    + "function fechaJanela(campo){\n"
                    + "document.getElementById(campo).style.display = \"none\";\n"
                    + "}\n"
                    + "window.onclick = function(event) {\n"
                    + "    if (event.target == document.getElementById('modaldia')) {\n"
                    + "        document.getElementById('modaldia').style.display = \"none\";\n"
                    + "    }\n"
                    + "    if (event.target == document.getElementById('modaldiasemana')) {\n"
                    + "        document.getElementById('modaldiasemana').style.display = \"none\";\n"
                    + "    }\n"
                    + "}\n"
                    + "</script>");
            out.println("</head>");
            out.println("<body>\n"
                    + "         <form name=\"form\" action=\"cartaodeponto\" method=\"post\">\n"
                    + "         <input type=\"hidden\" name=\"action\" id=\"action\" value=\"\">");
            //modal Artigo 71
            out.println("<div id=\"modalart71\" class=\"modal\">\n"
                    + "\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"height:400px\">\n"
                    + "<div class=\"col-md-12\" style=\"margin-top:5px\">\n"
                    + "<div class=\"panel panel-primary\">\n"
                    + "<div class=\"panel-heading\">\n"
                    + "<strong>Artigo 71</strong>\n"
                    + "</div>\n"
                    + "<div class=\"panel-body\">\n"
                    + "<div class=\"col-md-12\">"
                    + "Intervalo Acima<input type=\"text\" class=\"form-control tempo3\" value=\"00:30\" name=\"intervaloAcima\" id=\"intervaloAcimaAlmoco\" style=\"margin-bottom:5px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">"
                    + "Intervalo Abaixo<input type=\"text\" class=\"form-control tempo3\" value=\"00:15\" name=\"intervaloAbaixo\" id=\"intervaloAbaixoAlmoco\" style=\"margin-bottom:5px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "Horas Trabalhadas<input type=\"text\" class=\"form-control tempo3\" value=\"06:00\" name=\"horasTrabalhadas\" id=\"horasAlmoco\">\n" + "</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<input type = \"radio\" name=\"checkcalc\" id=\"calcintegral\" value=\"integral\" checked=\"true\">\n"
                    + "<span> Calcular integral</span>\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<input type = \"radio\" name=\"checkcalc\" id=\"calcdiferenca\" value=\"diferenca\">\n"
                    + "<span> Calcular diferença</span>\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<input type=\"button\" onclick=\"fechaJanela('modalart71')\" class=\"btn btn-primary\" value=\"Fechar\" style=\"margin-left:30%;margin-top:10px;\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>");
            //modal Minutos Residuais
            out.println("<div id=\"modalminutosresiduais\" class=\"modal\">\n"
                    + "\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"height:200px\">\n"
                    + "<div class=\"col-md-6\" style=\"margin-top:5px\">\n"
                    + "Entrada Jornada Ideal<input type=\"text\" class=\"form-control tempo3\" value=\"00:00\" name=\"jornadaIdealEntrada\" id=\"jornadaIdealEntrada\" style=\"margin-bottom:5px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-6\" style=\"margin-top:5px\">\n"
                    + "Saída Jornada Ideal<input type=\"text\" class=\"form-control tempo3\" value=\"00:00\" name=\"jornadaIdealSaida\" id=\"jornadaIdealSaida\" style=\"margin-bottom:5px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<input type=\"button\" onclick=\"fechaJanela('modalminutosresiduais')\" class=\"btn btn-primary\" value=\"Fechar\" style=\"margin-left:30%;margin-top:10px;\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>");
            out.println("            <div class=\"col-md-12\">\n"
                    + "            <span style=\"margin:10px\">Protocolo: " + cp.id + "</span>\n"
                    + "            <span style=\"margin:10px\">Processo: " + cp.processo + "</span>\n"
                    + "            <span style=\"margin:10px\">Junta: " + cp.junta + "</span>\n"
                    + "            </div>\n"
                    + "            <div class=\"col-md-12\">\n"
                    + "            <span style=\"margin:10px\">Período: </span>\n"
                    + "            <span style=\"margin:10px\">De:</span>\n"
                    + "            <input type=\"text\" name=\"datainicio\" style=\"margin:10px\" class=\"tempo5 data\" value=\"" + cp.dtinicial + "\">\n"
                    + "            <span style=\"margin:10px\">Até:</span>\n"
                    + "            <input type=\"text\" name=\"datafinal\" style=\"margin:10px\" class=\"tempo5 data\" value=\"" + cp.dtfinal + "\">\n"
                    + "            <span style=\"margin:10px\">Dia inicial para emissão:</span>\n"
                    + "            <input type=\"text\" name=\"diainicialemissao data\" style=\"margin:10px\">\n"
                    + "            </div>"
                    + "            <div class=\"col-md-12\">\n"
                    + "            <table>\n"
                    + "                <tr>\n"
                    + "                        <td><span>Pares de colunas a serem exibidas: </span></td>\n"
                    + "                         <td><select name=\"selectColunas\" onchange=\"exibeColunas(this.value)\">\n");
            for (int i = 1; i < 16; i++) {
                if (i != Integer.parseInt(cp.numColunas)) {
                    out.println("<option value=\"" + i + "\">" + i + "</option>\n");
                } else {
                    out.println("<option value=\"" + i + "\" selected=\"true\">" + i + "</option>\n");
                }
            }
            out.println("                      </select></td>\n"
                    + "                </tr>\n"
                    + "            </table>\n"
                    + "<div class=\"col-md-3\" style=\"margin-top:5px\">\n"
                    + "                    <div class=\"panel panel-primary\">\n"
                    + "                    <div class=\"panel-heading\">\n"
                    + "                        <strong>Artigos</strong>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"panel-body\">\n"
                    + "            <table>\n"
                    + "                <tr>\n"
                    + "                <td>\n"
                    + "                        <span style=\"margin-right:10px\" id=\"minutosResiduais\">58</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">66</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">71</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">384</span>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\"name=\"checkMinutosResiduais\" id=\"checkMinutosResiduais\" onchange=\"abreJanela('modalminutosresiduais')\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"Art66\" id=\"Art66\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"Art71\" id=\"Art71\" onchange=\"checkVisualizacao(this, 'botaoAjustaValores')\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"Art384\" id=\"Art384\">\n"
                    + "                    </td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                    <td colspan=\"2\">\n"
                    + "                        <input type=\"button\" id=\"botaoAjustaValores\" class=\"btn btn-primary\" style=\"display:none\" value=\"Alterar Valores do Art 71\" onclick=\"abreJanela('modalart71')\">\n"
                    + "                    </td>"
                    + "                    </tr>\n"
                    + "                    </table>\n"
                    + "                    </div>\n"
                    + "                    </div>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"col-md-4\" style=\"margin-top:5px\">\n"
                    + "                    <div class=\"panel panel-primary\">\n"
                    + "                    <div class=\"panel-heading\">\n"
                    + "                        <strong>Horas Extras</strong>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"panel-body\">\n"
                    + "                    <table>\n"
                    + "                    <tr>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Semanais</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Diárias</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Duplas Diárias</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Triplas Diárias</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Duplas Semanais</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Triplas Semanais</span>\n"
                    + "                    </td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"ExcSem\" id=\"ExcSem\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"ExcDia\" id=\"ExcDiario\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"extrasDD\" id=\"extrasDD\" onchange=\"checkVisualizacao(this, 'valorExtrasDD')\">\n"
                    + "                        <input type=\"text\" class=\"txt tempo\" style=\"display:none\" id=\"valorExtrasDD\" name=\"valorExtrasDD\" value=\"02:00\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"extrasTD\" id=\"extrasTD\" onchange=\"checkVisualizacao(this, 'valorExtrasTD1', 'valorExtrasTD2')\">\n"
                    + "                        <input type=\"text\" class=\"txt tempo\" style=\"display:none\" id=\"valorExtrasTD1\" name=\"valorExtrasTD1\" value=\"02:00\">\n"
                    + "                        <input type=\"text\" class=\"txt tempo\" style=\"display:none\" id=\"valorExtrasTD2\" name=\"valorExtrasTD2\" value=\"04:00\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"extrasDS\" id=\"extrasDS\" onchange=\"checkVisualizacao(this, 'valorExtrasDS')\">\n"
                    + "                        <input type=\"text\" class=\"txt tempo\" style=\" display:none\" id=\"valorExtrasDS\" name=\"valorExtrasDS\" value=\"10:00\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"extrasTS\" id=\"extrasTS\" onchange=\"checkVisualizacao(this, 'valorExtrasTS1', 'valorExtrasTS2')\">\n"
                    + "                        <input type=\"text\" class=\"txt tempo\" style=\" display:none\" id=\"valorExtrasTS1\" name=\"valorExtrasTS1\" value=\"10:00\">\n"
                    + "                        <input type=\"text\" class=\"txt tempo\" style=\" display:none\" id=\"valorExtrasTS2\" name=\"valorExtrasTS2\" value=\"12:00\">\n"
                    + "                    </td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Duplas Mensais</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Triplas Mensais</span>\n"
                    + "                    </td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"extrasDM\" id=\"extrasDM\" onchange=\"checkVisualizacao(this, 'valorExtrasDM')\">\n"
                    + "                        <input type=\"text\" class=\"txt tempo\" style=\" display:none\" id=\"valorExtrasDM\" name=\"valorExtrasDM\" value=\"10:00\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"extrasTM\" id=\"extrasTM\" onchange=\"checkVisualizacao(this, 'valorExtrasTM1', 'valorExtrasTM2')\">\n"
                    + "                        <input type=\"text\" class=\"txt tempo\" style=\" display:none\" id=\"valorExtrasTM1\" name=\"valorExtrasTM1\" value=\"10:00\">\n"
                    + "                        <input type=\"text\" class=\"txt tempo\" style=\" display:none\" id=\"valorExtrasTM2\" name=\"valorExtrasTM2\" value=\"12:00\">\n"
                    + "                    </td>\n"
                    + "                    </tr>\n"
                    + "                    </table>\n"
                    + "                    </div>\n"
                    + "                    </div>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"col-md-4\" style=\"margin-top:5px\">\n"
                    + "                    <div class=\"panel panel-primary\">\n"
                    + "                    <div class=\"panel-heading\">\n"
                    + "                        <strong>Outros</strong>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"panel-body\">\n"
                    + "                    <table>\n"
                    + "                    <tr>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:20px\">Faltas</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:30px\">RSR</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:20px\">Adicional Noturno</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Noturno 60</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\" id=\"prorrogacao\">Prorrogação</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span style=\"margin-right:10px\">Prorrogção 60</span>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                    <tr>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"Faltas\" id=\"Faltas\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"rsr\" id=\"rsr\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"adcNoturno\" id=\"adcNoturno\" onchange=\"checkHabilita2(this, 'prorrogacaoAdcNoturno', 'noturno60')\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"noturno60\" id=\"noturno60\" disabled=\"true\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" disabled=\"true\" name=\"prorrogacaoAdcNoturno\" id=\"prorrogacaoAdcNoturno\" onchange=\"checkHabilita(this, 'prorrogacao60')\">\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"checkbox\" style=\"margin-right:10px\" name=\"prorrogacao60\" id=\"prorrogacao60\" disabled=\"true\">\n"
                    + "                    </td>\n"
                    + "                    </tr>\n"
                    + "                </table>\n"
                    + "                </div>\n"
                    + "                </div>\n"
                    + "                </div>\n"
                    + "                <div class=\"col-md-12\">\n"
                    + "                        <input type=\"text\" name=\"nomePDF\" id=\"nomePDF\" style=\"margin:5px; width:250px\" value=\"Relatório_cartão_" + cp.id + "\">\n"
                    + "                </div>\n"
                    + "                <div class=\"col-md-12\">\n"
                    + "                        <input type=\"button\" class=\"btn btn-primary\" style=\"margin:5px\" value=\"Emitir Relatório\" onclick=\"enviar('testePDF')\">\n"
                    + "                        <input type=\"button\" class=\"btn btn-primary button\" style=\"margin-top:1px\" onclick=\"enviar('digitarCartaoVoltar')\" value=\"Voltar\">\n"
                    + "            </div>\n");
            out.println("</form>\n");
            out.println("</body>\n");
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
