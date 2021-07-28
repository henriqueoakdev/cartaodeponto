/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paginas;

import Classes.PDF;
import Classes.cartaoDePonto;
import Querys.qCartao;
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
@WebServlet(name = "criarCartao", urlPatterns = {"/criarCartao"})
public class criarCartao extends HttpServlet {

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
        cartaoDePonto cp = (cartaoDePonto) session.getAttribute("cartaoDePonto");
        String idCartao = (String) session.getAttribute("idCartao");
        String caminho = (String) session.getAttribute("caminho");
        if (idCartao == null) {
            idCartao = "0";
        }
        String[] cartao = {"", "", "", "", "", "", "", "0", "0", "0", "", "", "", "", "", "", "", "", "", ""};
        if (!idCartao.equals("0")) {
            cartao = qCartao.selectCartao(idCartao);
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>\n"
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
                    + ".row{\n"
                    + "text-align:center;\n"
                    + "}\n"
                    + "td > .form-control{\n"
                    + "width:150px;\n"
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
                    + "    width: 15%; /* Could be more or less, depending on screen size */\n"
                    + "    background-color:#F0F0F0;\n"
                    + "}\n"
                    + ".tdLista{\n"
                    + "padding:3px;\n"
                    + "text-align:center;\n"
                    + "}\n"
                    + ".tempo2{"
                    + "}"
                    + "</style>"
                    + "<script>\n"
                    + "function funcaoTeste(){\n"
                    + "document.getElementById(\"datainclusao\").value=\"28/11/2017\";\n"
                    + "document.getElementById(\"processo\").value=\"88\";\n"
                    + "document.getElementById(\"junta\").value=\"Belo Horizonte\";\n"
                    + "document.getElementById(\"reclamante\").value=\"José\";\n"
                    + "document.getElementById(\"reclamada\").value=\"João LTDA\";\n"
                    + "document.getElementById(\"datainicio\").value=\"28/11/2017\";\n"
                    + "document.getElementById(\"datafinal\").value=\"05/12/2017\";\n"
                    + "document.getElementById(\"excsemanal\").value=\"44:00\";\n"
                    + "document.getElementById(\"excseg\").value=\"8:00\";\n"
                    + "document.getElementById(\"excter\").value=\"8:00\";\n"
                    + "document.getElementById(\"excqua\").value=\"8:00\";\n"
                    + "document.getElementById(\"excqui\").value=\"8:00\";\n"
                    + "document.getElementById(\"excsex\").value=\"8:00\";\n"
                    + "document.getElementById(\"excsab\").value=\"8:00\";\n"
                    + "document.getElementById(\"excdom\").value=\"8:00\";\n"
                    + "}\n"
                    + "function distribuiValores(){\n"
                    + "semanal=document.getElementById(\"excsemanal\");\n"
                    + "seg=document.getElementById(\"excseg\");\n"
                    + "ter=document.getElementById(\"excter\");\n"
                    + "qua=document.getElementById(\"excqua\");\n"
                    + "qui=document.getElementById(\"excqui\");\n"
                    + "sex=document.getElementById(\"excsex\");\n"
                    + "sab=document.getElementById(\"excsab\");\n"
                    + "dom=document.getElementById(\"excdom\");\n"
                    + "if(semanal.value==\"44:00\"){\n"
                    + "seg.value=\"8:00\"\n"
                    + "ter.value=\"8:00\"\n"
                    + "qua.value=\"8:00\"\n"
                    + "qui.value=\"8:00\"\n"
                    + "sex.value=\"8:00\"\n"
                    + "sab.value=\"8:00\"\n"
                    + "dom.value=\"8:00\"\n"
                    + "}\n"
                    + "else if(semanal.value==\"30:00\"){\n"
                    + "seg.value=\"6:00\"\n"
                    + "ter.value=\"6:00\"\n"
                    + "qua.value=\"6:00\"\n"
                    + "qui.value=\"6:00\"\n"
                    + "sex.value=\"6:00\"\n"
                    + "sab.value=\"6:00\"\n"
                    + "dom.value=\"6:00\"\n"
                    + "}\n"
                    + "else if(semanal.value==\"36:00\"){\n"
                    + "seg.value=\"6:00\"\n"
                    + "ter.value=\"6:00\"\n"
                    + "qua.value=\"6:00\"\n"
                    + "qui.value=\"6:00\"\n"
                    + "sex.value=\"6:00\"\n"
                    + "sab.value=\"6:00\"\n"
                    + "dom.value=\"6:00\"\n"
                    + "}\n"
                    + "}\n"
                    + "function habilitaCidades(check)\n"
                    + "{if(check.checked==true){\n"
                    + "document.getElementById(\"cidades\").disabled=false\n"
                    + "}\n"
                    + "else{\n"
                    + "document.getElementById(\"cidades\").disabled=true;}\n"
                    + "}\n"
                    + "function abreJanela(campo){\n"
                    + "document.getElementById(campo).style.display = \"block\";\n"
                    + "}\n"
                    + "function fechaJanela(campo){\n"
                    + "document.getElementById(campo).style.display = \"none\";\n"
                    + "}\n"
                    + "function salvar(destino){\n"
                    + "var dtIni = document.getElementById(\"datainicio\").value;\n"
                    + "var dtFim = document.getElementById(\"datafinal\").value;\n"
                    + "if(dtIni == \"\" && dtFim == \"\"){\n"
                    + "window.alert (\"Data Inicio e Data Final são obrigatórios para esta função!\");\n"
                    + "}\n"
                    + "else{\n"
                    + "document.getElementById(\"action\").value=destino;\n"
                    + "document.form.submit();\n"
                    + "}\n"
                    + "}\n"
                    + "            jQuery(function ($)\n"
                    + "            {\n"
                    + "                $(\".tempo\").mask(\"99/99/9999\");\n"
                    + "            });\n"
                    + "            jQuery(function ($)\n"
                    + "            {\n"
                    + "                $(\".tempo2\").mask(\"99:99\");\n"
                    + "            });\n"
                    + "</script>\n"
                    + "        <title>Cartão de Ponto</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "         <form name=\"form\" action=\"cartaodeponto\" method=\"post\">\n"
                    + "         <input type=\"hidden\" name=\"action\" id=\"action\" value=\"\">\n");
            out.println("          <div class=\"col-md-12\">\n"
                    + "            <div class=\"panel panel-primary\" style=\"margin-top:5px\">\n"
                    + "            <div class=\"panel-heading\">\n"
                    + "            <strong>Cartão de Ponto</strong>\n"
                    + "            </div>\n"
                    + "            <div class=\"panel-body\">\n"
                    + "            <table style=\"width:100%; margin-left:5px\">\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <span>Data de Inclusão</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span>Número de Protocolo</span>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"text\" id=\"datainclusao\" class=\"tempo form-control data\" onblur=\"validaData(this)\" name=\"datainclusao\" value=\"" + cartao[0] + "\" required>\n"
                    + "                    </td> \n"
                    + "                    <td>\n"
                    + "                    <span>" + idCartao + "</span>"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <span>Número do Processo</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span>Vara</span>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"text\" onblur=\"isInt(this)\" name=\"processo\" id=\"processo\" class=\"form-control\" value=\"" + cartao[1] + "\" required>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"text\" name=\"junta\" id=\"junta\" class=\"form-control\" value=\"" + cartao[2] + "\" required>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <span>Reclamante</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span>Reclamada</span>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"text\" name=\"reclamante\" id=\"reclamante\" class=\"form-control\" value=\"" + cartao[3] + "\" required>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"text\" name=\"reclamada\" id=\"reclamada\" class=\"form-control\" value=\"" + cartao[4] + "\" required>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <span>Data de Admissão</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span>Data de Demissão</span>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"text\" class=\"tempo form-control data\" id=\"datainicio\" name=\"datainicio\" value=\"" + cartao[5] + "\" onblur=\"validaData(this)\" required>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"text\" class=\"tempo form-control data\" id=\"datafinal\" name=\"datafinal\" value=\"" + cartao[6] + "\" onblur=\"validaData(this)\" required>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <span>Prescrição</span>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <span>Data de Ajuizamento</span>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"text\" class=\"form-control\" id=\"prescricao\" name=\"prescricao\" value=\"" + cartao[19] + "\" required>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <input type=\"text\" class=\"tempo form-control data\" id=\"dataajuizamento\" name=\"dataajuizamento\" value=\"" + cartao[20] + "\" onblur=\"validaData(this)\" required>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "            </table>\n"
                    + "            </div>"
                    + "            </div>\n"
                    + "            <div class=\"col-md-4\">\n"
                    + "             <div class=\"panel panel-primary\" style=\"margin-left:-15px\">\n"
                    + "             <div class=\"panel-heading\">\n"
                    + "             <strong>Excedentes</strong>\n"
                    + "             </div>\n"
                    + "             <div class=\"panel-body\">\n"
                    + "             <div class=\"col-md-12\">\n"
                    + "             <div class=\"col-md-12\">\n"
                    + "               <div class=\"col-md-4\"></div>\n"
                    + "               <div class=\"col-md-4\">\n"
                    + "               <div class=\"row\"><span>Semanal: </span></div>\n"
                    + "               <input type=\"text\" name=\"excsemanal\" id=\"excsemanal\" onchange=\"distribuiValores()\" value=\"" + cartao[10] + "\" class=\"form-control\" required>\n"
                    + "               </div>\n"
                    + "               <div class=\"col-md-4\"></div>\n"
                    + "             </div>\n"
                    + "             <div class=\"col-md-4\">\n"
                    + "             <div class=\"row\"><span>Seg: </span></div>\n"
                    + "             <input type=\"text\" name=\"excseg\" id=\"excseg\" value=\"" + cartao[11] + "\" class=\"tempo2 form-control\" required>\n"
                    + "             </div>\n"
                    + "             <div class=\"col-md-4\">\n"
                    + "             <div class=\"row\"><span>Ter: </span></div>\n"
                    + "             <input type=\"text\" name=\"excter\" id=\"excter\" value=\"" + cartao[12] + "\" class=\"tempo2 form-control\" required>\n"
                    + "             </div>\n"
                    + "             <div class=\"col-md-4\">\n"
                    + "             <div class=\"row\"><span>Qua: </span></div>\n"
                    + "             <input type=\"text\" name=\"excqua\" id=\"excqua\" value=\"" + cartao[13] + "\" class=\"tempo2 form-control\" required>\n"
                    + "             </div>"
                    + "             <div class=\"col-md-4\">\n"
                    + "             <div class=\"row\"><span>Qui: </span></div>\n"
                    + "             <input type=\"text\" name=\"excqui\" id=\"excqui\" value=\"" + cartao[14] + "\" class=\"tempo2 form-control\" required>\n"
                    + "             </div>\n"
                    + "             <div class=\"col-md-4\">\n"
                    + "             <div class=\"row\"><span>Sex: </span></div>\n"
                    + "             <input type=\"text\" name=\"excsex\" id=\"excsex\" value=\"" + cartao[15] + "\" class=\"tempo2 form-control\" required>\n"
                    + "             </div>\n"
                    + "             <div class=\"col-md-4\">\n"
                    + "             <div class=\"row\"><span>Sab: </span></div>\n"
                    + "             <input type=\"text\" name=\"excsab\" id=\"excsab\" value=\"" + cartao[16] + "\" class=\"tempo2 form-control\" required>\n"
                    + "             </div>\n"
                    + "             <div class=\"col-md-4\">\n"
                    + "             <div class=\"row\"><span>Dom: </span></div>\n"
                    + "             <input type=\"text\" name=\"excdom\" id=\"excdom\" value=\"" + cartao[17] + "\" class=\"tempo2 form-control\" required>\n"
                    + "             </div>\n"
                    + "             </div>\n"
                    + "             </div>\n"
                    + "             </div>\n"
                    + "            </div>\n"
                    + "            <div class=\"col-md-2\" style=\"margin-top:2px\">\n"
                    + "            <div class=\"panel panel-primary\">\n"
                    + "            <div class=\"panel-heading\">\n"
                    + "            <strong>Marcação de feriados</strong>\n"
                    + "            </div>\n"
                    + "            <div class=\"panel-body\">\n"
                    + "            <div class=\"col-md-12\">\n"
                    + "            <span style=\"margin-left:40px\">M</span>\n"
                    + "            <span>N</span>\n"
                    + "            <span>NF</span>\n"
                    + "            </div>\n"
                    + "            <div class=\"col-md-12\" style=\"margin-left:37px\">\n"
                    + "            <input type=\"checkbox\" name=\"chbmunipais\" onchange=\"habilitaCidades(this)\" id=\"m\" " + (cartao[7].equals("1") ? "checked=\"true\"" : "") + ">\n"
                    + "            <input type=\"checkbox\" name=\"chbnacionais\" id=\"n\" " + (cartao[8].equals("1") ? "checked=\"true\"" : "") + ">\n"
                    + "            <input type=\"checkbox\" name=\"chbnacionaisf\" id=\"nf\" " + (cartao[9].equals("1") ? "checked=\"true\"" : "") + ">\n"
                    + "            </div>\n"
                    + "            <div class=\"col-md-12\" style=\"margin-left:-5px\">\n"
                    + "            <select name=\"cidades\" id=\"cidades\" style=\"margin-left:10px\" disabled=\"true\">");
            String[] cidades = qInsercoesbd.selectCidades();
            for (String cidade : cidades) {
                String id = cidade.split(";;")[0];
                String nome = cidade.split(";;")[1];
                out.println("            <option value=\"" + id + "\">" + nome + "</option>");
            }
            out.println("            </select>\n"
                    + "            </div>\n"
                    + "            </div>\n"
                    + "            </div>\n"
                    + "            </div>\n"
                    + "            <div class=\"col-md-12\">"
                    + "            <span>*TODOS os campos são de preenchimento obrigatório</span>"
                    + "            </div>"
                    + "            <div class=\"col-md-6\"></div>\n"
                    + "            <div class=\"col-md-12\">\n"
                    + "            <input type=\"submit\" class=\"btn btn-primary\" name=\"ok\" style=\"margin:5px\" value=\"Salvar Cartão\" onclick=\"salvar('salvarCartao')\">\n"
                    + (idCartao.equals("0") ? "" : "          <button onclick=\"enviar('digitarCartao')\" class=\"btn btn-primary\">Digitacao</button>\n")
//                    + "            <input type=\"button\" class=\"btn btn-primary\" name=\"teste\" style=\"margin:5px\" value=\"Teste\" onclick=\"funcaoTeste()\">\n"
//                    + "            <input type=\"button\" class=\"btn btn-primary\" name=\"duplicaCartao\" style=\"margin:5px\" value=\"Duplicar Este Cartão\" onclick=\"enviar('duplicaCartao')\">\n"
                    + "            <input type=\"button\" class=\"btn btn-primary\" onclick=\"enviar('" + (caminho.equals("novo") ? "paginaInicial" : "buscaTodosCartoes") + "')\" value=\"Voltar\">\n"
                    + "            </div>\n"
                    + "            </div>\n"
                    + "        </form>\n"
                    + "    </body>"
            );
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
            Logger.getLogger(criarCartao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(criarCartao.class.getName()).log(Level.SEVERE, null, ex);
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
