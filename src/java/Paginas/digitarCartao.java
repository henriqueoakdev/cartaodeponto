/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paginas;

import Classes.cartaoDePonto;
import Classes.tabelaDigitacao;
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
@WebServlet(name = "digitarCartao", urlPatterns = {"/digitarCartao"})
public class digitarCartao extends HttpServlet {

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
            String mensagem = (String) session.getAttribute("mensagemCartao");
            if (mensagem == null) {
                mensagem = "";
            }
            String idCartao = (String) session.getAttribute("idCartao");
            String[] cartao = qCartao.selectCartao(idCartao);

            tabelaDigitacao tDigitacao = (tabelaDigitacao) session.getAttribute("tDigitacao");
            tDigitacao.paginate(cp);

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
            String datafinal = cp.getDtfinal();
            String[] semana = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"};
            String[] feriados = qInsercoesbd.selectDiaFeriado();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(datainicio);
            c.setTime(date);
            c.add(Calendar.DATE, ((tDigitacao.getIndex() * 180) - 180));

            int diaSemana = c.get(Calendar.DAY_OF_WEEK) - 1;
            int day = c.get(Calendar.DATE);
            int month = (c.get(Calendar.MONTH) + 1);
            int year = c.get(Calendar.YEAR);
            String data = (day < 10 ? "0" + day : day) + "/" + (month < 10 ? "0" + month : month) + "/" + year;
            int count = 171;
            if (chbnacionais.equals("1")) {
                vetNacionais = qInsercoesbd.selectTipoFeriado("nacional");
            }
            if (chbnacionaisf.equals("1")) {
                vetNacionaisf = qInsercoesbd.selectTipoFeriado("nacionalfixo");
            }
            tDigitacao.setContFDS(0);
            tDigitacao.setContM(0);
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>\n"
                    + "\n"
                    + "        <title>Digitação</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <script src=\"scripts/jquery.js\"></script>\n"
                    + "        <script src=\"scripts/jquery.maskedinput.js\"></script>\n"
                    + "        <script src=\"scripts/Uteis.js\"></script>\n"
                    + "        <script src=\"scripts/Digitacao.js\"></script>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles/bootstrap.min.css\" type=\"text/css\"/>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles/consulper.css\" type=\"text/css\"/>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles/common.css\" type=\"text/css\"/>\n"
                    + "        <script src=\"scripts/scripts.js\"></script>\n"
                    + "        <script src=\"http://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n"
                    + "        <link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css\" />\n");
            out.println("        <style>\n"
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
                    + "                width:40px;\n"
                    + "            }\n"
                    + "            .tempo3\n"
                    + "            {\n"
                    + "                width:200px;\n"
                    + "            }\n"
                    + "            .tempo4\n"
                    + "            {\n"
                    + "                width:50px;\n"
                    + "                margin-bottom:5px;\n"
                    + "            }\n"
                    + "            .tempo5\n"
                    + "            {\n"
                    + "                width:150px;\n"
                    + "                margin-bottom:5px;\n"
                    + "            }\n"
                    + "            .tempo6\n"
                    + "            {\n"
                    + "                width:100px;\n"
                    + "            }\n"
                    + "            .button{\n"
                    + "            width:185px;\n"
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
                    + "    overflow: none; /* Enable scroll if needed */\n"
                    + "    background-color: rgb(0,0,0); /* Fallback color */\n"
                    + "    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */\n"
                    + "}\n"
                    + "/* Modal Content/Box */\n"
                    + ".modal-content {\n"
                    + "    background-color: #fefefe;\n"
                    + "    margin: 15% auto; /* 15% from the top and centered */\n"
                    + "    padding: 20px;\n"
                    + "    overflow: auto; /* Enable scroll if needed */\n"
                    + "    border: 1px solid #888;\n"
                    + "    width: 15%; /* Could be more or less, depending on screen size */\n"
                    + "    background-color:#F0F0F0;\n"
                    + "}\n"
                    + ".tdLista{\n"
                    + "padding:3px;\n"
                    + "text-align:center;"
                    + "}\n"
                    + ".oculto{\n"
                    + "display:none;"
                    + "}\n"
                    + ".dropdown {\n"
                    + "    position: relative;\n"
                    + "    display: inline-block;\n"
                    + "}\n"
                    + "\n"
                    + ".dropdown-content {\n"
                    + "    display: none;\n"
                    + "    position: absolute;\n"
                    + "    background-color: #f9f9f9;\n"
                    + "    min-width: 160px;\n"
                    + "    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\n"
                    + "    padding: 12px 16px;\n"
                    + "    z-index: 1;\n"
                    + "}\n"
                    + "\n"
                    + ".dropdown:hover .dropdown-content {\n"
                    + "    display: block;\n"
                    + "}"
                    + "        </style>\n");
            out.println("        <script>\n"
                    + "var diaFoco = " + datainicio + ";\n"
                    + "function mensagem(){\n"
                    + (mensagem.equals("") ? "" : "window.alert(\"" + mensagem + "\")")
                    + "exibeColunas('" + cp.numColunas + "');\n"
                    + "}\n"
                    + "        </script>\n"
                    + "    </head>\n"
                    + "    <body onload=\"mensagem()\">\n"
                    + "        <form name=\"form\" action=\"cartaodeponto\" method=\"post\">\n"
                    + "         <input type=\"hidden\" name=\"radioAuxiliar1\" id=\"radioAuxiliar1\" value=\"txbE1\" tabindex=\"1\">"
                    + "         <input type=\"hidden\" name=\"radioAuxiliar2\" id=\"radioAuxiliar2\" value=\"txbS1\" tabindex=\"1\">"
                    + "         <input type=\"hidden\" name=\"action\" id=\"action\" value=\"\" tabindex=\"1\">");
            out.println("<header class=\"navbar-fixed-top\">\n"
                    + "<nav class=\"navbar navbar-default\">\n"
                    + "<div>\n"
                    + "<ul class=\"nav navbar-nav\">\n"
                    + "<li>"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px; margin-left:130px\" onclick=\"salvarDigitacao()\" value=\"Salvar Digitação\">\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<div class=\"dropdown\" style=\"margin-right:5px\">\n"
                    + "<span class=\"btn btn-primary button\">Limpar Campos</span>\n"
                    + "<div class=\"dropdown-content\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" onclick=\"limpaTodosCampos()\" value=\"Limpar Campos Zerados\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" onclick=\"abreJanela('modallimparperiodo')\" value=\"Limpar Período\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<div class=\"dropdown\" style=\"margin-right:5px\">\n"
                    + "<span class=\"btn btn-primary button\">Destacar dias</span>\n"
                    + "<div class=\"dropdown-content\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px\" onclick=\"abreJanela('modaldia')\" value=\"Destacar específico\">\n"
                    + "<input type=\"button\"  class=\"btn btn-primary button\" id=\"diaSimNao\" style=\"margin-bottom:5px\" onclick=\"alternaDia()\" value=\"Exibir dia sim dia não\">\n"
                    + "<input type=\"button\"  class=\"btn btn-primary button\" id=\"diaSequencial\" style=\"margin-bottom:5px; display:none\" onclick=\"sequenciaDia()\" value=\"Exibir dia sim dia não\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px\" onclick=\"abreJanela('modaldiasemana')\" value=\"Dias da semana\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<div class=\"dropdown\" style=\"margin-right:5px\">\n"
                    + "<span class=\"btn btn-primary button\">Alterar jornadas</span>"
                    + "<div class=\"dropdown-content\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px\" onclick=\"jornadaSF()\" value=\"Jornada semanal fixa\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px\" onclick=\"jornadaDF()\" value=\"Jornada diária fixa\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" value=\"Adicionar/Reduzir Jornada Diária\" onclick=\"abreJanela('modalAdRedJornada')\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" onclick=\"abreJanela('modalfolgas')\" value=\"Marca folgas\">\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:-5px\" onclick=\"abreJanela('modaltrocacoluna')\" value=\"Troca colunas\">\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px; margin-left:10px\" onclick=\"abreJanela('modalalmocofixo')\" value=\"Almoço fixo\">\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<div class=\"dropdown\" style=\"margin-right:5px; margin-left:130px\">\n"
                    + "<span class=\"btn btn-primary button\">Parâmetros do Cartão</span>"
                    + "<div class=\"dropdown-content\">\n");
            out.println("<div class=\"col-md-12\" style=\"margin-top:70px\">\n"
                    + "<table>\n"
                    + "<tr>\n"
                    + "<td style=\"padding-right:6px\">\n"
                    + "<span>Pares de colunas a serem exibidas: </span>\n"
                    + "<select name=\"selectColunas\" id=\"selectColunas\" onchange=\"exibeColunas(this.value)\">\n");
            for (int i = 1; i < 16; i++) {
                if (i != Integer.parseInt(cp.numColunas)) {
                    out.println("<option value=\"" + i + "\">" + i + "</option>\n");
                } else {
                    out.println("<option value=\"" + i + "\" selected=\"true\">" + i + "</option>\n");
                }
            }
            out.println("</select>"
                    + "</td>\n"
                    + "<span>Alerta de Horas</span>\n"
                    + "<input type=\"text\" class=\"form-control tempo4\" id=\"alertaHoras\" name=\"alertaHoras\" onchange=\"destacaHoras()\">\n"
                    + "<span>Dias Trabalhados: </span>\n"
                    + "<input type=\"text\" class=\"form-control tempo4\" id=\"diasTrabalhados\" name=\"diasTrabalhados\" value=\"" + cp.diasTrabalhados + "\" disabled=\"true\">\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" onclick=\"trocaExcedente()\" value=\"Troca excedente\">\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" value=\"Emissão de Relatório\" onclick=\"salvaRelatorio()\">\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" value=\"Importar Cartão Antigo\" onclick=\"abreJanela('modalImportaCartao')\">\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" value=\"Copiar Digitações\" onclick=\"abreJanela('modalcopia')\">\n"
                    + "</li>\n"
                    + "<li>\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" value=\"Marcação de Ausências\" onclick=\"abreJanela('modalausencias')\">\n"
                    + "</li>\n"
                    //+ "<li>\n"
                    //+ "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" value=\"Leitor AFD\" onclick=\"abreJanela('modalleitorafd')\">\n"
                    //+ "</li>\n"
                    + "<li>\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px\" onclick=\"enviar('voltarCriarCartao')\" value=\"Voltar ao Cartão\">\n"
                    + "</li>\n"
                    + "<ul>\n"
                    + "</div>\n"
                    + "</nav>\n"
                    + "</header>\n");
            //modal Leitor ADF
            out.println("<div id=\"modalleitorafd\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:230px; height:100px\">\n"
                    + "<div class=\"row\">\n"
                    + "<span>PIS: </span>\n"
                    + "<input type = \"text\" name=\"pisadf\" class=\"data\" tabindex=\"1\" id=\"pisadf\">\n"
                    + "<span>Caminho para o arquivo: </span>\n"
                    + "<input type = \"text\" name=\"caminhoArquivoAdf\" class=\"data\" tabindex=\"1\" id=\"caminhoArquivoAdf\" value=\"" + datainicio + "\">\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"button\" onclick=\"enviar('leitorAFD')\" class=\"btn btn-primary\" value=\"Buscar\" style=\"margin-top:5px;\">\n"
                    + "<input type=\"button\" onclick=\"fechaJanela('modalleitorafd')\" class=\"btn btn-primary\" value=\"Fechar\" style=\"margin-top:5px;\">\n"
                    + "</div>\n"
                    + "  </div>\n"
                    + "</div>");
            //modal Pesquisa Dia
            out.println("<div id=\"modaldia\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:260px; height:100px\">\n"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-left:20px\">Dia:</span>\n"
                    + "<input type = \"text\" name=\"pesquisadia\" class=\"data\" tabindex=\"1\" id=\"pesquisadia\" value=\"" + datainicio + "\">\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"button\" onclick=\"pesquisaData()\" class=\"btn btn-primary\" value=\"Destacar\" style=\"margin-top:5px; margin-left:10px\">\n"
                    + "<input type=\"button\" onclick=\"limpaMarcacoes()\" class=\"btn btn-primary\" value=\"Limpar\" style=\"margin-top:5px;\">\n"
                    + "<input type=\"button\" onclick=\"fechaJanela('modaldia')\" class=\"btn btn-primary\" value=\"Fechar\" style=\"margin-top:5px;\">\n"
                    + "</div>\n"
                    + "  </div>\n"
                    + "</div>");
            //modal Importa Cartão
            out.println("<div id=\"modalImportaCartao\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:230px; height:200px\">\n"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-left:53px\">Número do Arquivo:</span>\n"
                    + "<input type=\"text\" style=\"margin-left:35px\" class=\"form-control tempo5\" name=\"nomeArquivo\" tabindex=\"1\" id=\"nomeArquivo\">\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"button\" style=\"margin-left:40px; margin-top:5px\" onclick=\"importaCartao1()\" class=\"btn btn-primary\" value=\"Importar Padrão 1\" style=\"margin-top:5px;\">\n"
                    + "<input type=\"button\" style=\"margin-left:40px; margin-top:5px\" onclick=\"importaCartao2()\" class=\"btn btn-primary\" value=\"Importar Padrão 2\" style=\"margin-top:5px;\">\n"
                    + "<input type=\"button\" onclick=\"fechaJanela('modalImportaCartao')\" class=\"btn btn-primary\" value=\"Fechar\" style=\"margin-top:5px; margin-left:70px\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>");
            //modal Limpa Período
            out.println("<div id=\"modallimparperiodo\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:230px; height:200px\">\n"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-left:75px\">Dia inicial:</span>\n"
                    + "<input type=\"text\" style=\"margin-left:35px\" class=\"form-control tempo5\" name=\"diaInicialLimpaPeriodo\" id=\"diaInicialLimpaPeriodo\">\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-left:80px\">Dia final:</span>\n"
                    + "<input type=\"text\" style=\"margin-left:35px\" class=\"form-control tempo5\" name=\"diaFinalLimpaPeriodo\" id=\"diaFinalLimpaPeriodo\">\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"button\" style=\"margin-top:5px; margin-left:35px\" onclick=\"apagaPeriodo()\" class=\"btn btn-primary\" value=\"Limpar\" style=\"margin-top:5px;\">\n"
                    + "<input type=\"button\" onclick=\"fechaJanela('modallimparperiodo')\" class=\"btn btn-primary\" value=\"Fechar\" style=\"margin-top:5px; margin-left:5px\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>");
            //modal Adicionar/Reduzir Jornada Diária
            out.println("<div id=\"modalAdRedJornada\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:510px; height:230px\">\n"
                    + "<div col-md-12>\n"
                    + "<strong>Período a ser alterado</strong>\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<span>Data Inicial</span>\n"
                    + "<span style=\"margin-left:70px\">Data Final</span>\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<div class=\"col-md-3\">\n"
                    + "<input type=\"text\" class=\"form-control\" name=\"dataInicialAdrJornada\" id=\"dataInicialAdrJornada\" value=\"" + cp.dtinicial + "\" style=\"margin-right:10px; margin-left:15px\">\n"
                    + "</div>"
                    + "<div class=\"col-md-3\">\n"
                    + "<input type=\"text\" class=\"form-control\" name=\"dataFinalAdrJornada\" id=\"dataFinalAdrJornada\" value=\"" + cp.dtfinal + "\" style=\"margin-right:10px; margin-left:10px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-6\">\n"
                    + "<input type=\"radio\" name=\"rdbColuna\" id=\"radEntrada\" checked=\"true\" value=\"radE1\"> Entrada\n"
                    + "<input type=\"radio\" name=\"rdbColuna\" id=\"radSaida\" value=\"radE1\"> Saída\n"
                    + "</div>"
                    + "<div class=\"col-md-12\">\n"
                    + "<input type=\"radio\" style=\"margin-left:250px\" name=\"rdbAdRed\" id=\"radValorAdc\" checked=\"true\" value=\"valorAdc\" onchange=\"habilitaValores(this)\"> Adicionar\n"
                    + "<input type=\"radio\" style=\"margin-left:8px\" name=\"rdbAdRed\" id=\"radValorRed\" value=\"valorRed\" onchange=\"habilitaValores(this)\"> Reduzir\n"
                    + "</div>\n"
                    + "</div\n>"
                    + "<div class=\"col-md-6\">"
                    + "<span>Adicionar</span>"
                    + "</div>"
                    + "<div class=\"col-md-6\">"
                    + "<span>Reduzir</span>"
                    + "</div>"
                    + "<div class=\"col-md-6\">\n"
                    + "<input type=\"text\" class=\"form-control tempo3\" name=\"valorAdc\" id=\"valorAdc\" style=\"margin-top:5px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-6\">\n"
                    + "<input type=\"text\" class=\"form-control tempo3\" name=\"valorRed\" id=\"valorRed\" style=\"margin-top:5px\" disabled=\"true\">\n"
                    + "</div>\n"
                    + "<input type=\"button\" id=\"aplicarAdcRed\" style=\"margin-right:5px; margin-left:45px; margin-top:5px\" onclick=\"adRedJornada()\" class=\"btn btn-primary button\" value=\"Aplicar\">\n"
                    + "<input type=\"button\" id=\"fecharAlmoco\" style=\"margin-top:5px\" onclick=\"fechaJanela('modalAdRedJornada')\" class=\"btn btn-primary button\" value=\"Fechar\">\n"
                    + "</div>\n"
                    + "</div>");
            //modal Almoco Fixo
            out.println("<div id=\"modalalmocofixo\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:600px; height:430px\">\n"
                    + "<div col-md-12>\n"
                    + "<strong>Período a ser alterado</strong>\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<span style=\"margin-left:-15px\">Data Inicial</span>\n"
                    + "<span style=\"margin-left:50px\">Data Final</span>\n"
                    + "<span style=\"margin-left:280px\">Intervalos</span>\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<div class=\"col-md-3\">\n"
                    + "<input type=\"text\" class=\"form-control\" name=\"dataInicialAlmocoFixo\" id=\"dataInicialAlmocoFixo\" value=\"" + cp.dtinicial + "\" style=\"margin-right:10px\">\n"
                    + "</div>"
                    + "<div class=\"col-md-3\">\n"
                    + "<input type=\"text\" class=\"form-control\" name=\"dataFinalAlmocoFixo\" id=\"dataFinalAlmocoFixo\" value=\"" + cp.dtfinal + "\" style=\"margin-left:-25px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-6\">"
                    + "<input type=\"radio\" style=\"margin-left:-50px\" name=\"rdbHorario\" id=\"horarioFixo\" checked=\"true\" value=\"fixo\" onchange=\"habilitaAlmoco(this)\">Horário Fixo\n"
                    + "<input type=\"radio\" name=\"rdbHorario\" id=\"horarioMovel\" value=\"movel\" onchange=\"habilitaAlmoco(this)\">Horário Móvel\n"
                    + "<select name=\"numIntervalos\" id=\"numIntervalos\" style=\"margin-left:45px\">"
                    + "<option value=\"1\">1</option>"
                    + "<option value=\"2\">2</option>"
                    + "<option value=\"3\">3</option>"
                    + "<option value=\"4\">4</option>"
                    + "<option value=\"5\">5</option>"
                    + "</select>"
                    + "</div>\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<div class=\"col-md-6\" style=\"margin-top:15px\">\n"
                    + "<div class=\"panel panel-primary\">\n"
                    + "<div class=\"panel-heading\">\n"
                    + "<strong>Horário Fixo</strong>\n"
                    + "</div>\n"
                    + "<div class=\"panel-body\">\n"
                    + "<div class=\"col-md-12\">\n"
                    + "Saída<input type=\"text\" class=\"form-control tempo3\" name=\"saidaAlmoco\" id=\"saidaAlmoco\" style=\"margin-bottom:5px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "Retorno<input type=\"text\" class=\"form-control tempo3\" name=\"retornoAlmoco\" id=\"retornoAlmoco\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "<div class=\"col-md-6\" style=\"margin-top:15px\">\n"
                    + "<div class=\"panel panel-primary\">\n"
                    + "<div class=\"panel-heading\">\n"
                    + "<strong>Horário Móvel</strong>\n"
                    + "</div>\n"
                    + "<div class=\"panel-body\">\n"
                    + "<div class=\"col-md-12\">"
                    + "Intervalo Acima<input type=\"text\" class=\"form-control tempo3\" value=\"00:30\" disabled=\"true\" name=\"intervaloAcimaAlmoco\" id=\"intervaloAcimaAlmoco\" style=\"margin-bottom:5px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">"
                    + "Intervalo Abaixo<input type=\"text\" class=\"form-control tempo3\" value=\"00:15\" disabled=\"true\" name=\"intervaloAbaixoAlmoco\" id=\"intervaloAbaixoAlmoco\" style=\"margin-bottom:5px\">\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "Horas Trabalhadas<input type=\"text\" class=\"form-control tempo3\" value=\"06:00\" disabled=\"true\" name=\"horasAlmoco\" id=\"horasAlmoco\">\n" + "</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "<div class=\"col-md-12\">\n"
                    + "<input type=\"button\" id=\"aplicarAlmoco\" style=\"margin-right:5px; margin-left:75px\" onclick=\"almocoFixo()\" class=\"btn btn-primary button\" value=\"Aplicar\">\n"
                    + "<input type=\"button\" id=\"fecharAlmoco\" onclick=\"fechaJanela('modalalmocofixo')\" class=\"btn btn-primary button\" value=\"Fechar\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>");
            //modal Troca Coluna
            out.println("<div id=\"modaltrocacoluna\" class=\"modal\">\n"
                    + "<!-- Modal content -->\n"
                    + "<div class=\"modal-content\" style=\"height:740px\">\n"
                    + "<div class=\"col-md-6\">"
                    + "E1<input type=\"radio\" name=\"coluna1\" value=\"txbE1\" onclick=\"updateR1(this)\" checked=\"true\">\n"
                    + "S1<input type=\"radio\" name=\"coluna1\" value=\"txbS1\" onclick=\"updateR1(this)\">\n"
                    + "E2<input type=\"radio\" name=\"coluna1\" value=\"txbE2\" onclick=\"updateR1(this)\">\n"
                    + "S2<input type=\"radio\" name=\"coluna1\" value=\"txbS2\" onclick=\"updateR1(this)\">\n"
                    + "E3<input type=\"radio\" name=\"coluna1\" value=\"txbE3\" onclick=\"updateR1(this)\">\n"
                    + "S3<input type=\"radio\" name=\"coluna1\" value=\"txbS3\" onclick=\"updateR1(this)\">\n"
                    + "E4<input type=\"radio\" name=\"coluna1\" value=\"txbE4\" onclick=\"updateR1(this)\">\n"
                    + "S4<input type=\"radio\" name=\"coluna1\" value=\"txbS4\" onclick=\"updateR1(this)\">\n"
                    + "E5<input type=\"radio\" name=\"coluna1\" value=\"txbE5\" onclick=\"updateR1(this)\">\n"
                    + "S5<input type=\"radio\" name=\"coluna1\" value=\"txbS5\" onclick=\"updateR1(this)\">\n"
                    + "E6<input type=\"radio\" name=\"coluna1\" value=\"txbE6\" onclick=\"updateR1(this)\">\n"
                    + "S6<input type=\"radio\" name=\"coluna1\" value=\"txbS6\" onclick=\"updateR1(this)\">\n"
                    + "E7<input type=\"radio\" name=\"coluna1\" value=\"txbE7\" onclick=\"updateR1(this)\">\n"
                    + "S7<input type=\"radio\" name=\"coluna1\" value=\"txbS7\" onclick=\"updateR1(this)\">\n"
                    + "E8<input type=\"radio\" name=\"coluna1\" value=\"txbE8\" onclick=\"updateR1(this)\">\n"
                    + "S8<input type=\"radio\" name=\"coluna1\" value=\"txbS8\" onclick=\"updateR1(this)\">\n"
                    + "E9<input type=\"radio\" name=\"coluna1\" value=\"txbE9\" onclick=\"updateR1(this)\">\n"
                    + "S9<input type=\"radio\" name=\"coluna1\" value=\"txbS9\" onclick=\"updateR1(this)\">\n"
                    + "E10<input type=\"radio\" name=\"coluna1\" value=\"txbE10\" onclick=\"updateR1(this)\">\n"
                    + "S10<input type=\"radio\" name=\"coluna1\" value=\"txbS10\" onclick=\"updateR1(this)\">\n"
                    + "E11<input type=\"radio\" name=\"coluna1\" value=\"txbE11\" onclick=\"updateR1(this)\">\n"
                    + "S11<input type=\"radio\" name=\"coluna1\" value=\"txbS11\" onclick=\"updateR1(this)\">\n"
                    + "E12<input type=\"radio\" name=\"coluna1\" value=\"txbE12\" onclick=\"updateR1(this)\">\n"
                    + "S12<input type=\"radio\" name=\"coluna1\" value=\"txbS12\" onclick=\"updateR1(this)\">\n"
                    + "E13<input type=\"radio\" name=\"coluna1\" value=\"txbE13\" onclick=\"updateR1(this)\">\n"
                    + "S13<input type=\"radio\" name=\"coluna1\" value=\"txbS13\" onclick=\"updateR1(this)\">\n"
                    + "E14<input type=\"radio\" name=\"coluna1\" value=\"txbE14\" onclick=\"updateR1(this)\">\n"
                    + "S14<input type=\"radio\" name=\"coluna1\" value=\"txbS14\" onclick=\"updateR1(this)\">\n"
                    + "E15<input type=\"radio\" name=\"coluna1\" value=\"txbE15\" onclick=\"updateR1(this)\">\n"
                    + "S15<input type=\"radio\" name=\"coluna1\" value=\"txbS15\" onclick=\"updateR1(this)\">\n"
                    + "</div>"
                    + "<div class=\"col-md-6\">"
                    + "E1<input type=\"radio\" name=\"coluna2\" value=\"txbE1\" onclick=\"updateR2(this)\">\n"
                    + "S1<input type=\"radio\" name=\"coluna2\" value=\"txbS1\" onclick=\"updateR2(this)\" checked=\"true\">\n"
                    + "E2<input type=\"radio\" name=\"coluna2\" value=\"txbE2\" onclick=\"updateR2(this)\">\n"
                    + "S2<input type=\"radio\" name=\"coluna2\" value=\"txbS2\" onclick=\"updateR2(this)\">\n"
                    + "E3<input type=\"radio\" name=\"coluna2\" value=\"txbE3\" onclick=\"updateR2(this)\">\n"
                    + "S3<input type=\"radio\" name=\"coluna2\" value=\"txbS3\" onclick=\"updateR2(this)\">\n"
                    + "E4<input type=\"radio\" name=\"coluna2\" value=\"txbE4\" onclick=\"updateR2(this)\">\n"
                    + "S4<input type=\"radio\" name=\"coluna2\" value=\"txbS4\" onclick=\"updateR2(this)\">\n"
                    + "E5<input type=\"radio\" name=\"coluna2\" value=\"txbE5\" onclick=\"updateR2(this)\">\n"
                    + "S5<input type=\"radio\" name=\"coluna2\" value=\"txbS5\" onclick=\"updateR2(this)\">\n"
                    + "E6<input type=\"radio\" name=\"coluna1\" value=\"txbE6\" onclick=\"updateR2(this)\">\n"
                    + "S6<input type=\"radio\" name=\"coluna2\" value=\"txbS6\" onclick=\"updateR2(this)\">\n"
                    + "E7<input type=\"radio\" name=\"coluna2\" value=\"txbE7\" onclick=\"updateR2(this)\">\n"
                    + "S7<input type=\"radio\" name=\"coluna2\" value=\"txbS7\" onclick=\"updateR2(this)\">\n"
                    + "E8<input type=\"radio\" name=\"coluna2\" value=\"txbE8\" onclick=\"updateR2(this)\">\n"
                    + "S8<input type=\"radio\" name=\"coluna2\" value=\"txbS8\" onclick=\"updateR2(this)\">\n"
                    + "E9<input type=\"radio\" name=\"coluna2\" value=\"txbE9\" onclick=\"updateR2(this)\">\n"
                    + "S9<input type=\"radio\" name=\"coluna2\" value=\"txbS9\" onclick=\"updateR2(this)\">\n"
                    + "E10<input type=\"radio\" name=\"coluna2\" value=\"txbE10\" onclick=\"updateR2(this)\">\n"
                    + "S10<input type=\"radio\" name=\"coluna2\" value=\"txbS10\" onclick=\"updateR2(this)\">\n"
                    + "E11<input type=\"radio\" name=\"coluna2\" value=\"txbE11\" onclick=\"updateR2(this)\">\n"
                    + "S11<input type=\"radio\" name=\"coluna2\" value=\"txbS11\" onclick=\"updateR2(this)\">\n"
                    + "E12<input type=\"radio\" name=\"coluna2\" value=\"txbE12\" onclick=\"updateR2(this)\">\n"
                    + "S12<input type=\"radio\" name=\"coluna2\" value=\"txbS12\" onclick=\"updateR2(this)\">\n"
                    + "E13<input type=\"radio\" name=\"coluna2\" value=\"txbE13\" onclick=\"updateR2(this)\">\n"
                    + "S13<input type=\"radio\" name=\"coluna2\" value=\"txbS13\" onclick=\"updateR2(this)\">\n"
                    + "E14<input type=\"radio\" name=\"coluna2\" value=\"txbE14\" onclick=\"updateR2(this)\">\n"
                    + "S14<input type=\"radio\" name=\"coluna2\" value=\"txbS14\" onclick=\"updateR2(this)\">\n"
                    + "E15<input type=\"radio\" name=\"coluna2\" value=\"txbE15\" onclick=\"updateR2(this)\">\n"
                    + "S15<input type=\"radio\" name=\"coluna2\" value=\"txbS15\" onclick=\"updateR2(this)\">\n"
                    + "</div>"
                    + "<input type=\"button\" style=\"margin-left:-13px\" value=\"Trocar\" name=\"btnTrocar\" class=\"btn btn-primary button\" onclick=\"trocaColunasRadio()\">"
                    + "  </div>\n"
                    + "</div>");
            //modal diaSemana
            out.println("<div id=\"modaldiasemana\" class=\"modal\">\n"
                    + "\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:18%; height:20%\">\n"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-right:5px;margin-left:4px\">Dom</span>\n"
                    + "<span style=\"margin-right:5px\">Seg</span>\n"
                    + "<span style=\"margin-right:5px\">Ter</span>\n"
                    + "<span style=\"margin-right:5px\">Qua</span>\n"
                    + "<span style=\"margin-right:5px\">Qui</span>\n"
                    + "<span style=\"margin-right:5px\">Sex</span>\n"
                    + "<span>Sab</span>\n"
                    + "</div>"
                    + "<div class=\"row\">\n"
                    + "<input type = \"checkbox\" name=\"domingo\" id=\"Dom\" onclick=\"diaSemana(this)\" checked=\"true\" style=\"margin-right:18px;margin-left:11px\">\n"
                    + "<input type = \"checkbox\" name=\"segunda\" id=\"Seg\" onclick=\"diaSemana(this)\" checked=\"true\" style=\"margin-right:17px\">\n"
                    + "<input type = \"checkbox\" name=\"terca\" id=\"Ter\" onclick=\"diaSemana(this)\" checked=\"true\" style=\"margin-right:16px\">\n"
                    + "<input type = \"checkbox\" name=\"quarta\" id=\"Qua\" onclick=\"diaSemana(this)\" checked=\"true\" style=\"margin-right:17px\">\n"
                    + "<input type = \"checkbox\" name=\"quinta\" id=\"Qui\" onclick=\"diaSemana(this)\" checked=\"true\" style=\"margin-right:14px\">\n"
                    + "<input type = \"checkbox\" name=\"sexta\" id=\"Sex\" onclick=\"diaSemana(this)\" checked=\"true\" style=\"margin-right:14px\">\n"
                    + "<input type = \"checkbox\" name=\"sabado\" id=\"Sab\" onclick=\"diaSemana(this)\" checked=\"true\">\n"
                    + "  </div>\n"
                    + "<div class=\"\">\n"
                    + " <input type=\"button\" class=\"btn btn-primary\" onclick=\"fechaJanela('modaldiasemana')\" value=\"Ok\" style=\"margin-left:38%;margin-top:5px;\">\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>");
            //modal Janela de marcação de folgas
            out.println("<div id=\"modalfolgas\" class=\"modal\">\n"
                    + "\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:30%\">\n"
                    + "  <div class=\"row\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button2\" style=\"margin-bottom:3px\" onclick=\"marcaFolgas(7)\" value=\"Domingos e Feriados\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button3 pull-right\" onclick=\"marcaFolgas(0)\" value=\"Domingos\">\n"
                    + "  </div>\n"
                    + "  <div class=\"row\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button2\" style=\"margin-bottom:3px\" onclick=\"marcaFolgas(8)\" value=\"Sábados, Domingos e Feriados\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button3 pull-right\" onclick=\"marcaFolgas(1)\" value=\"Segundas\">\n"
                    + "  </div>\n"
                    + "  <div class=\"row\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button2\" style=\"margin-bottom:3px\" onclick=\"marcaFolgas(9)\" value=\"Só Feriados, Limpa Domingos\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button3 pull-right\" onclick=\"marcaFolgas(2)\" value=\"Terças\">\n"
                    + "  </div>\n"
                    + "  <div class=\"row\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button2\" style=\"margin-bottom:3px\" onclick=\"marcaFolgas(10)\" value=\"Limpa Todas Folgas\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button3 pull-right\" onclick=\"marcaFolgas(3)\" value=\"Quartas\">\n"
                    + "  </div>\n"
                    + "  <div class=\"row\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button3 pull-right\" onclick=\"marcaFolgas(4)\" value=\"Quintas\">\n"
                    + "  </div>\n"
                    + "  <div class=\"row\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button2\" style=\"margin-bottom:3px\" onclick=\"folgasCompensatorias()\" value=\"Folgas Compensatórias\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button3 pull-right\" onclick=\"marcaFolgas(5)\" value=\"Sextas\">\n"
                    + "  </div>\n"
                    + "  <div class=\"row\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button2\" style=\"margin-bottom:3px\" onclick=\"marcaFolgas(13)\" value=\"Marcar Manualmente\">\n"
                    + "  <input type=\"button\" class=\"btn btn-primary button3 pull-right\" onclick=\"marcaFolgas(6)\" value=\"Sábados\">\n"
                    + "  </div>\n"
                    + "  <div class=\"row\" col-md-12>\n"
                    + "  <input type=\"button\" class=\"btn btn-primary\" onclick=\"fechaJanela('modalfolgas')\" value=\"Fechar\">\n"
                    + "  </div>"
                    + "  </div>"
                    + "</div>");
            //modal Copia Excel
            out.println("<div id=\"modalcopiaexcel\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:600px; height:560px; margin-top:7%; margin-left:4px\">\n"
                    + "<div class=\"row\">\n"
                    + "<textarea name=\"textoCopiaExcel\" id=\"textoCopiaExcel\" style=\"min-width:580px; max-width:580px; min-height:490px; max-height:490px\"></textarea>\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"button\" onclick=\"copiaExcel()\" class=\"btn btn-primary\" value=\"Copiar\" style=\"margin-top:5px; margin-left:220px\">\n"
                    + "<input type=\"button\" id=\"fecharCopiaExcel\" onclick=\"fechaJanela('modalcopiaexcel')\" class=\"btn btn-primary button\" value=\"Fechar\">\n"
                    + "</div>\n"
                    + "  </div>\n"
                    + "</div>");
            //modal CP para Excel
            out.println("<div id=\"modalcpexcel\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:600px; height:560px; margin-top:7%; margin-left:4px\">\n"
                    + "<div class=\"row\">\n"
                    + "<textarea name=\"textoCopiaExcel\" id=\"textoCopiaExcel\" style=\"min-width:580px; max-width:580px; min-height:490px; max-height:490px\"></textarea>\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"button\" onclick=\"copiaExcel()\" class=\"btn btn-primary\" value=\"Copiar\" style=\"margin-top:5px; margin-left:220px\">\n"
                    + "<input type=\"button\" id=\"fecharCopiaExcel\" onclick=\"fechaJanela('modalcopiaexcel')\" class=\"btn btn-primary button\" value=\"Fechar\">\n"
                    + "</div>\n"
                    + "  </div>\n"
                    + "</div>");
            //modal Copia Cartao Sada
            out.println("<div id=\"modalcopiacartaosada\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:595px; height:620px; margin-top:7%; margin-left:4px\">\n"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-left:75px\">Dia inicial:</span>\n"
                    + "<input type=\"text\" style=\"margin-left:35px\" class=\"form-control tempo5\" name=\"diaInicialCopiaSada\" id=\"diaInicialCopiaSada\">\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<textarea name=\"textoCopiaSada\" id=\"textoCopiaSada\" style=\"min-width:580px; max-width:580px; min-height:490px; max-height:490px\"></textarea>\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"button\" onclick=\"copiaCartaoSada()\" class=\"btn btn-primary\" value=\"Copiar\" style=\"margin-top:5px; margin-left:220px\">\n"
                    + "<input type=\"button\" id=\"fecharCopiaCartaoSada\" style=\"width:100px\" onclick=\"fechaJanela('modalcopiacartaosada')\" class=\"btn btn-primary button\" value=\"Fechar\">\n"
                    + "</div>\n"
                    + "  </div>\n"
                    + "</div>");
            //modal Copia
            out.println("<div id=\"modalcopia\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:230px; height:155px; margin-top:7%; margin-left:4px\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" value=\"Excel para CP\" onclick=\"mudarModal('modalcopia', 'modalcopiaexcel')\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" value=\"CP para Excel\" onclick=\"mudarModal('modalcopia', 'modalcpexcel')\">\n"
                    + "<input type=\"button\" class=\"btn btn-primary button\" style=\"margin-bottom:5px; margin-right:5px\" value=\"Copiar Cartão Sada\" onclick=\"mudarModal('modalcopia', 'modalcopiacartaosada')\">\n"
                    + "<input type=\"button\" id=\"fecharCopia\" onclick=\"fechaJanela('modalcopia')\" class=\"btn btn-primary button\" value=\"Fechar\">\n"
                    + "  </div>\n"
                    + "</div>");
            //modal Ausencias
            out.println("<div id=\"modalausencias\" class=\"modal\">\n"
                    + "  <!-- Modal content -->\n"
                    + "  <div class=\"modal-content\" style=\"width:340px; height:155px\">\n"
                    + "<div class=\"row\">\n"
                    + "<span style=\"margin-left:60px\">Início:</span>\n"
                    + "<input type = \"text\" name=\"diainicioausencia\" class=\"data\" tabindex=\"1\" id=\"diainicioausencia\" value=\"" + datainicio + "\">\n"
                    + "<span style=\"margin-left:60px\">Final:</span>\n"
                    + "<input type = \"text\" name=\"diafinalausencia\" class=\"data\" tabindex=\"1\" id=\"diafinalausencia\" value=\"" + datafinal + "\">\n"
                    + "</div>\n"
                    + "<div class=\"row\">\n"
                    + "<input type=\"radio\" style=\"margin-left:10px\" name=\"rdbAusencias\" id=\"radAbono\" checked=\"true\" value=\"radAbono\"> Abono\n"
                    + "<input type=\"radio\" name=\"rdbAusencias\" id=\"radCompensacao\" value=\"radCompensacao\"> Compensação\n"
                    + "<input type=\"radio\" name=\"rdbAusencias\" id=\"radFerias\" checked=\"true\" value=\"radFerias\"> Férias\n"
                    + "<input type=\"radio\" name=\"rdbAusencias\" id=\"radAtestado\" checked=\"true\" value=\"radAtestado\"> Atestado\n"
                    + "<input type=\"button\" onclick=\"enviar('marcacaoAusencias')\" class=\"btn btn-primary\" value=\"Marcar\" style=\"margin-top:5px; margin-left:85px\">\n"
                    + "<input type=\"button\" onclick=\"fechaJanela('modalausencias')\" class=\"btn btn-primary\" value=\"Fechar\" style=\"margin-top:5px;\">\n"
                    + "</div>\n"
                    + "  </div>\n"
                    + "</div>");
            //tabela de dias
            out.println("            <table style=\"margin-top:140px\">\n"
                    + "                <tr>\n"
                    + "                    <td class=\"celulatopo tdLista\">\n"
                    + "                        <span>Dia</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E1\">\n"
                    + "                        <span>E-1</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S1\">\n"
                    + "                        <span>S-1</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E2\">\n"
                    + "                        <span>E-2</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S2\">\n"
                    + "                        <span>S-2</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E3\">\n"
                    + "                        <span>E-3</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S3\">\n"
                    + "                        <span>S-3</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E4\">\n"
                    + "                        <span>E-4</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S4\">\n"
                    + "                        <span>S-4</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E5\">\n"
                    + "                        <span>E-5</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S5\">\n"
                    + "                        <span>S-5</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E6\">\n"
                    + "                        <span>E-6</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S6\">\n"
                    + "                        <span>S-6</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E7\">\n"
                    + "                        <span>E-7</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S7\">\n"
                    + "                        <span>S-7</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E8\">\n"
                    + "                        <span>E-8</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S8\">\n"
                    + "                        <span>S-8</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E9\">\n"
                    + "                        <span>E-9</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S9\">\n"
                    + "                        <span>S-9</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E10\">\n"
                    + "                        <span>E-10</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S10\">\n"
                    + "                        <span>S-10</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E11\">\n"
                    + "                        <span>E-11</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S11\">\n"
                    + "                        <span>S-11</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E12\">\n"
                    + "                        <span>E-12</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S12\">\n"
                    + "                        <span>S-12</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E13\">\n"
                    + "                        <span>E-13</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S13\">\n"
                    + "                        <span>S-13</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E14\">\n"
                    + "                        <span>E-14</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S14\">\n"
                    + "                        <span>S-14</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"E15\">\n"
                    + "                        <span>E-15</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"S15\">\n"
                    + "                        <span>S-15</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"total\">\n"
                    + "                        <span>Total</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"folga\">\n"
                    + "                        <span>Folga</span>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"celulatopo tdLista\" name=\"ausencias\">\n"
                    + "                        <span>Ausências</span>\n"
                    + "                    </td>\n"
                    + "                </tr>\n");
            //linhas do intervalo de dias solicitaco
            int j = 0;
            if (tDigitacao.getIndex() == 1) {
                j = (tDigitacao.getIndex() * 180) - 180;
            } else {
                j = ((tDigitacao.getIndex() - 1) * 180) - (180 * (tDigitacao.getIndex() - 1));
            }
            int i = 0;
            while (i < 180 && j < cp.idDigitacao.length) {
                String celulaTopo = (diaSemana == 0 ? " celulatopo" : "");
                out.println("                <tr class=\"" + (diaSemana == 0 ? " celulatopo" : "") + "\" name=\"" + (i % 2 == 0 ? "par" : "impar") + "\" id=\"" + data + "\">\n"
                        + "                    <td class=\"tdLista tdData\">\n"
                        + "                        <input type=\"hidden\" name=\"idDigitacao\" id=\"idDigitacao\" value=\"" + cp.idDigitacao[j] + "\">\n"
                        + "                        <input type=\"text\" class=\"celularegular" + celulaTopo + "\" value=\"" + data + "-" + (verificaFeriado(data, vetMunicipais, vetNacionais, vetNacionaisf) ? "Fer" : semana[diaSemana]) + "\" disabled=\"true\" name=\"datatabela\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E1\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE1\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e1[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S1\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS1\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s1[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E2\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE2\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e2[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S2\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS2\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s2[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E3\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE3\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e3[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S3\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS3\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s3[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E4\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE4\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e4[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S4\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS4\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s4[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E5\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE5\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e5[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S5\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS5\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s5[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E6\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE6\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e6[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S6\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS6\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s6[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E7\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE7\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e7[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S7\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS7\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s7[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E8\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE8\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e8[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S8\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS8\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s8[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E9\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE9\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e9[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S9\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS9\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s9[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E10\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE10\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e10[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S10\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS10\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s10[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E11\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE11\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e11[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S11\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS11\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s11[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E12\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE12\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e12[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S12\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS12\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s12[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E13\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE13\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e13[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S13\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS13\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s13[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E14\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE14\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e14[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S14\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS14\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s14[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"E15\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbE15\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.e15[j] + "\">\n"
                        + "                    </td>");
                out.println("                    <td name=\"S15\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" class=\"tempo" + celulaTopo + "\" name=\"txbS15\" tabindex=\"" + count++ + "\" onkeyup=\"ajustaHora(this, this.value)\" onblur=\"somaHoras('" + i + "'," + tDigitacao.getContFDS() + "," + tDigitacao.getContM() + ")\" value=\"" + cp.s15[j] + "\">\n"
                        + "                    </td>");
                if (diaSemana == 0 || i == (dias - 1)) {
                    count += 2;
                }
                if (day == 1 || i == (dias - 1)) {
                    count += 2;
                }
                count += 4;
                out.println("                    <td name=\"total\" class=\"tdLista " + celulaTopo + "\">\n"
                        + "                        <input type=\"text\" name=\"totalHoras\" disabled=\"true\" class=\"tempo2" + celulaTopo + " fds" + tDigitacao.getContFDS() + " mes" + tDigitacao.getContM() + "\" value=\"" + cp.totaldiario[j] + "\">\n"
                        + "                    <td name=\"folga\" class=\"tdLista\">\n"
                        + "                    <input type=\"text\" name=\"f" + (verificaFeriado(data, vetMunicipais, vetNacionais, vetNacionaisf) ? "8" : diaSemana) + "\" id=\"folga" + i + "\" disabled=\"true\" class=\"tempo2 folga\" value=\"" + cp.folgas[j] + "\">\n"
                        + "                    </td>"
                        + "                    <td>"
                        + "                        <input type=\"text\" name=\"ausencias\" disabled=\"true\" class=\"tempo6" + celulaTopo + " fds" + tDigitacao.getContFDS() + " mes" + tDigitacao.getContM() + "\" value=\"" + cp.ausencia[j] + "\">\n"
                        + "                    </td>"
                        + "                    <td>");
                if (diaSemana == 0 || i == (dias - 1)) {//manter o pulo automatico das linhas quando trocar de semana visto que um input novo é criado nessa condição
                    //count+=2;
                    out.println("<input type=\"text\" class=\"oculto\" name=\"totalSemana\" id=\"totalSemana" + tDigitacao.getContFDS() + "\" value=\"" + cp.totalsemanal[tDigitacao.getContFDS()] + "\">\n"
                            + "<input type=\"hidden\" name=\"idSemana\" value=\"" + cp.idSem[tDigitacao.usaContFDS()] + "\">\n");
                }
                out.println("</td>");

                c.add(Calendar.DATE, 1);
                day = c.get(Calendar.DATE);
                month = (c.get(Calendar.MONTH) + 1);
                year = c.get(Calendar.YEAR);
                data = (day < 10 ? "0" + day : day) + "/" + (month < 10 ? "0" + month : month) + "/" + year;

                out.println("<td>");
                if (day == 1 || i == (dias - 1)) {//manter o pulo automatico das linhas quando trocar de mes visto que um input novo é criado nessa condição
                    //count += 1;
                    out.println("<input type=\"text\" class=\"oculto\" name=\"totalMes\" id=\"totalMes" + tDigitacao.getContM() + "\" value=\"" + cp.totalmensal[tDigitacao.getContM()] + "\" display=\"\">\n"
                            + "<input type=\"hidden\" name=\"idMes\" value=\"" + cp.idMes[tDigitacao.usaContM()] + "\">\n");
                }

                diaSemana++;
                if (diaSemana >= 7) {
                    diaSemana = 0;
                }
                i++;
                j++;
                count++;
                out.println("</td>");
                out.println("</tr>\n");
            }
            out.println("            </table>\n"
                    + "<button name=\"voltaPagina\" onclick=\"anteriorPagina()\"><i class=\"glyphicon glyphicon-chevron-left\"></i></button>\n"
                    + "<input type=\"text\" name=\"voltaPagina\" value=\"" + tDigitacao.getIndex() + "\" disabled=\"true\">\n"
                    + "<button name=\"passaPagina\" onclick=\"proximaPagina()\"><i class=\"glyphicon glyphicon-chevron-right\"></i></button>\n"
                    + "<span>Páginas: " + tDigitacao.getTotalPaginas() + "</span>\n"
                    + "<input type=\"text\" name=\"irPagina\" placeholder=\"ir para pagina:\">\n"
                    + "<input type=\"button\" name=\"btnMudaPagina\" value=\"Ir\" onclick=\"buscaPagina()\">\n"
                    + "</div>\n"
                    + "</form>\n"
                    + "</body>");
            out.println("</html>");
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
            Logger.getLogger(digitarCartao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(digitarCartao.class.getName()).log(Level.SEVERE, null, ex);
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
