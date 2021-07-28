/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Classes.PDF;
import Classes.Uteis;
import Classes.cartaoDePonto;
import Classes.leitorTXT;
import Classes.tabelaDigitacao;
import Querys.qCartao;
import Querys.qInsercoesbd;
import com.itextpdf.text.DocumentException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
@WebServlet(name = "cPonto", urlPatterns = {"/cPonto"})
public class cPonto extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        cartaoDePonto cp = (cartaoDePonto) session.getAttribute("cartaoDePonto");
        tabelaDigitacao tDigitacao = (tabelaDigitacao) session.getAttribute("tDigitacao");

        session.setAttribute("mensagemCartao", "");
        PDF teste = new PDF();

        if (tDigitacao == null) {
            tDigitacao = new tabelaDigitacao();
        }
        if (cp == null) {
            cp = new cartaoDePonto();
        }

        String url;
        url = "/criarCartao";
        String action;
        action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "novoFeriado":
                //abrir tela de feriado            
                url = "/inserirFeriado";
                break;
            case "inserirFeriado":                                {
                String nome;
                nome = request.getParameter("nome");
                String cidade;
                cidade = request.getParameter("cidades");
                String data;
                data = request.getParameter("data");
                String justificativa;
                justificativa = request.getParameter("justificativa");
                String tipo;
                tipo = request.getParameter("tipo");

                qInsercoesbd.insereFeriado(nome, data, cidade, justificativa, tipo);
            }
            url = "/inserirFeriado";
            break;





            case "criarcartao":
            {
                String[] txbeE1;
                txbeE1 = request.getParameterValues("txbE1");
                String[] txbeE2;
                txbeE2 = request.getParameterValues("txbE2");
                String[] txbeE3;
                txbeE3 = request.getParameterValues("txbE3");
                String[] txbeE4;
                txbeE4 = request.getParameterValues("txbE4");
                String[] txbeE5;
                txbeE5 = request.getParameterValues("txbE5");
                String[] txbeE6;
                txbeE6 = request.getParameterValues("txbE6");
                String[] txbeE7;
                txbeE7 = request.getParameterValues("txbE7");
                String[] txbeE8;
                txbeE8 = request.getParameterValues("txbE8");
                String[] txbeE9;
                txbeE9 = request.getParameterValues("txbE9");
                String[] txbeE10;
                txbeE10 = request.getParameterValues("txbE10");
                String[] txbeE11;
                txbeE11 = request.getParameterValues("txbE11");
                String[] txbeE12;
                txbeE12 = request.getParameterValues("txbE12");
                String[] txbeE13;
                txbeE13 = request.getParameterValues("txbE13");
                String[] txbeE14;
                txbeE14 = request.getParameterValues("txbE14");
                String[] txbeE15;
                txbeE15 = request.getParameterValues("txbE15");
                String[] txbeS1;
                txbeS1 = request.getParameterValues("txbS1");
                String[] txbeS2;
                txbeS2 = request.getParameterValues("txbS2");
                String[] txbeS3;
                txbeS3 = request.getParameterValues("txbS3");
                String[] txbeS4;
                txbeS4 = request.getParameterValues("txbS4");
                String[] txbeS5;
                txbeS5 = request.getParameterValues("txbS5");
                String[] txbeS6;
                txbeS6 = request.getParameterValues("txbS6");
                String[] txbeS7;
                txbeS7 = request.getParameterValues("txbS7");
                String[] txbeS8;
                txbeS8 = request.getParameterValues("txbS8");
                String[] txbeS9;
                txbeS9 = request.getParameterValues("txbS9");
                String[] txbeS10 = request.getParameterValues("txbS10");
                String[] txbeS11 = request.getParameterValues("txbS11");
                String[] txbeS12 = request.getParameterValues("txbS12");
                String[] txbeS13 = request.getParameterValues("txbS13");
                String[] txbeS14 = request.getParameterValues("txbS14");
                String[] txbeS15 = request.getParameterValues("txbS15");

                cp.setE1(txbeE1);
                cp.setS1(txbeS1);
                cp.setE2(txbeE2);
                cp.setS2(txbeS2);
                cp.setE3(txbeE3);
                cp.setS3(txbeS3);
                cp.setE4(txbeE4);
                cp.setS4(txbeS4);
                cp.setE5(txbeE5);
                cp.setS5(txbeS5);
                cp.setE6(txbeE6);
                cp.setS6(txbeS6);
                cp.setE7(txbeE7);
                cp.setS7(txbeS7);
                cp.setE8(txbeE8);
                cp.setS8(txbeS8);
                cp.setE9(txbeE9);
                cp.setS9(txbeS9);
                cp.setE10(txbeE10);
                cp.setS10(txbeS10);
                cp.setE11(txbeE11);
                cp.setS11(txbeS11);
                cp.setE12(txbeE12);
                cp.setS12(txbeS12);
                cp.setE13(txbeE13);
                cp.setS13(txbeS13);
                cp.setE14(txbeE14);
                cp.setS14(txbeS14);
                cp.setE15(txbeE15);
                cp.setS15(txbeS15);
                session.setAttribute("idCartao", "0");
                session.setAttribute("caminho", "novo");
            }
            url = "/criarCartao";
            break;

            case "digitarCartao": {
                String chbmunicipais = request.getParameter("chbmunicipais");
                String chbnacionais = request.getParameter("chbnacionais");
                String chbnacionaisf = request.getParameter("chbnacionaisf");

                if (chbmunicipais != null) {
                    session.setAttribute("municipal", true);
                } else {
                    session.setAttribute("municipal", false);
                }
                if (chbnacionais != null) {
                    session.setAttribute("nacional", true);
                } else {
                    session.setAttribute("nacional", false);
                }
                if (chbnacionaisf != null) {
                    session.setAttribute("nacionalf", true);
                } else {
                    session.setAttribute("nacionalf", false);
                }

                try {
                    salvarCartao(request, cp, session);
                } catch (ParseException ex) {
                    Logger.getLogger(cPonto.class.getName()).log(Level.SEVERE, null, ex);
                }
                tDigitacao = new tabelaDigitacao();
                int index = tDigitacao.getIndex();
                cp.pesquisaDigitacao(cp.id, index);
            }
            url = "/digitarCartao";
            break;

            case "digitarCartaoVoltar": {

            }
            url = "/digitarCartao";
            break;

            case "duplicaCartao": {
                if (cp.id != null) {
                    qCartao.insereCartao(cp.dtinclusao, cp.processo, cp.junta, cp.reclamante, cp.reclamada, cp.dtinicial,
                            cp.dtfinal, cp.feriadosm, cp.feriadosn, cp.feriadosnf, Integer.parseInt(cp.numColunas), cp.status);
                }
            }
            url = "/criarCartao";
            break;

            case "salvarCartao": {
                try {
                    salvarCartao(request, cp, session);
                } catch (ParseException ex) {
                    Logger.getLogger(cPonto.class.getName()).log(Level.SEVERE, null, ex);
                }
                url = "/criarCartao";
            }
            break;

            case "voltarCriarCartao": {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
                String horaAtual = sdf.format(cal.getTime());
                String dataAtual = data.format(cal.getTime());
                int horas = Integer.parseInt(horaAtual.split(":")[0]);
                int minutos = Integer.parseInt(horaAtual.split(":")[1]);
                minutos -= 31;
                if (minutos < 0) {
                    minutos += 60;
                    horas -= 1;
                }
                if (minutos >= 10) {
                    horaAtual = horas + ":" + minutos;
                } else {
                    horaAtual = horas + ":0" + minutos;
                }
                String status = horaAtual + "-" + dataAtual;
                qCartao.alteraStatus(status, cp.id);
            }
            url = "/criarCartao";
            break;

            case "voltarDigitacao": {

            }
            url = "/digitarCartao";
            break;

            case "salvarDigitacao": {
                salvarDigitacao(request, cp, session);
                int index = tDigitacao.getIndex();
                cp.pesquisaDigitacao(cp.id, index);
            }
            url = "/digitarCartao";
            break;

//            case "leitorAFD": {
//                String pis = request.getParameter("pisadf");
//                String caminhoArquivo = request.getParameter("caminhoArquivoAdf");
//                try {
//                    FileInputStream is = new FileInputStream(caminhoArquivo);
//                    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//                    BufferedReader lerArq = new BufferedReader(isr);
//                    String linha = lerArq.readLine(); // lê a primeira linha
//                    // a variável "linha" recebe o valor "null" quando o processo
//                    // de repetição atingir o final do arquivo texto
//                    boolean entradaSaida = false;
//                    
//                    try {
//                        if (linha.substring((linha.length() - 11), linha.length()).equals(pis)) {
//                            
//                            linha = lerArq.readLine();
//                        } else {
//                            linha = lerArq.readLine();
//                        }
//
//                    } catch (Exception e) {
//                        linha = null;
//                    }
//                    isr.close();
//                } catch (IOException e) {
//                    System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
//                }
//            }
//            url = "/digitarCartao";
//            break;
            case "jornadaSF": {
                String[] e1 = request.getParameterValues("txbE1");
                String[] s1 = request.getParameterValues("txbS1");
                String[] e2 = request.getParameterValues("txbE2");
                String[] s2 = request.getParameterValues("txbS2");
                String[] e3 = request.getParameterValues("txbE3");
                String[] s3 = request.getParameterValues("txbS3");
                String[] e4 = request.getParameterValues("txbE4");
                String[] s4 = request.getParameterValues("txbS4");
                String[] e5 = request.getParameterValues("txbE5");
                String[] s5 = request.getParameterValues("txbS5");
                String[] e6 = request.getParameterValues("txbE6");
                String[] s6 = request.getParameterValues("txbS6");
                String[] e7 = request.getParameterValues("txbE7");
                String[] s7 = request.getParameterValues("txbS7");
                String[] e8 = request.getParameterValues("txbE8");
                String[] s8 = request.getParameterValues("txbS8");
                String[] e9 = request.getParameterValues("txbE9");
                String[] s9 = request.getParameterValues("txbS9");
                String[] e10 = request.getParameterValues("txbE10");
                String[] s10 = request.getParameterValues("txbS10");
                String[] e11 = request.getParameterValues("txbE11");
                String[] s11 = request.getParameterValues("txbS11");
                String[] e12 = request.getParameterValues("txbE12");
                String[] s12 = request.getParameterValues("txbS12");
                String[] e13 = request.getParameterValues("txbE13");
                String[] s13 = request.getParameterValues("txbS13");
                String[] e14 = request.getParameterValues("txbE14");
                String[] s14 = request.getParameterValues("txbS14");
                String[] e15 = request.getParameterValues("txbE15");
                String[] s15 = request.getParameterValues("txbS15");
                String dtinicio = request.getParameter("datainicial");
                String dtfinal = request.getParameter("datafinal");
                String[] diainicial = dtinicio.split("/");
                String[] diafinal = dtfinal.split("/");

                String datainicio = cp.getDtinicial();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date;
                try {
                    date = sdf.parse(datainicio);
                    c.setTime(date);
                } catch (ParseException ex) {
                    Logger.getLogger(cPonto.class.getName()).log(Level.SEVERE, null, ex);
                }
                int diaSemana = c.get(Calendar.DAY_OF_WEEK) - 1;

                for (int i = 0; i < cp.getDias(); i++) {
                    cp.e1[i] = preencheCampos(e1[diaSemana]);
                    cp.s1[i] = preencheCampos(s1[diaSemana]);
                    cp.e2[i] = preencheCampos(e2[diaSemana]);
                    cp.s2[i] = preencheCampos(s2[diaSemana]);
                    cp.e3[i] = preencheCampos(e3[diaSemana]);
                    cp.s3[i] = preencheCampos(s3[diaSemana]);
                    cp.e4[i] = preencheCampos(e4[diaSemana]);
                    cp.s4[i] = preencheCampos(s4[diaSemana]);
                    cp.e5[i] = preencheCampos(e5[diaSemana]);
                    cp.s5[i] = preencheCampos(s5[diaSemana]);
                    cp.e6[i] = preencheCampos(e6[diaSemana]);
                    cp.s6[i] = preencheCampos(s6[diaSemana]);
                    cp.e7[i] = preencheCampos(e7[diaSemana]);
                    cp.s7[i] = preencheCampos(s7[diaSemana]);
                    cp.e8[i] = preencheCampos(e8[diaSemana]);
                    cp.s8[i] = preencheCampos(s8[diaSemana]);
                    cp.e9[i] = preencheCampos(e9[diaSemana]);
                    cp.s9[i] = preencheCampos(s9[diaSemana]);
                    cp.e10[i] = preencheCampos(e10[diaSemana]);
                    cp.s10[i] = preencheCampos(s10[diaSemana]);
                    cp.e11[i] = preencheCampos(e11[diaSemana]);
                    cp.s11[i] = preencheCampos(s11[diaSemana]);
                    cp.e12[i] = preencheCampos(e12[diaSemana]);
                    cp.s12[i] = preencheCampos(s12[diaSemana]);
                    cp.e13[i] = preencheCampos(e13[diaSemana]);
                    cp.s13[i] = preencheCampos(s13[diaSemana]);
                    cp.e14[i] = preencheCampos(e14[diaSemana]);
                    cp.s14[i] = preencheCampos(s14[diaSemana]);
                    cp.e15[i] = preencheCampos(e15[diaSemana]);
                    cp.s15[i] = preencheCampos(s15[diaSemana]);
                    diaSemana++;

                    if (diaSemana >= 7) {
                        diaSemana = 0;
                    }
                }
            }
            cp.refazSoma();
            url = "/jornadaSF";
            break;

            case "atualizaDigitarCartao":
                url = "/digitarCartao";
                break;

            case "jornadaDF": {
                String[] chb = request.getParameterValues("chbDia");
                String[] e1 = request.getParameterValues("txbE1");
                String[] s1 = request.getParameterValues("txbS1");
                String[] e2 = request.getParameterValues("txbE2");
                String[] s2 = request.getParameterValues("txbS2");
                String[] e3 = request.getParameterValues("txbE3");
                String[] s3 = request.getParameterValues("txbS3");
                String[] e4 = request.getParameterValues("txbE4");
                String[] s4 = request.getParameterValues("txbS4");
                String[] e5 = request.getParameterValues("txbE5");
                String[] s5 = request.getParameterValues("txbS5");
                String[] e6 = request.getParameterValues("txbE6");
                String[] s6 = request.getParameterValues("txbS6");
                String[] e7 = request.getParameterValues("txbE7");
                String[] s7 = request.getParameterValues("txbS7");
                String[] e8 = request.getParameterValues("txbE8");
                String[] s8 = request.getParameterValues("txbS8");
                String[] e9 = request.getParameterValues("txbE9");
                String[] s9 = request.getParameterValues("txbS9");
                String[] e10 = request.getParameterValues("txbE10");
                String[] s10 = request.getParameterValues("txbS10");
                String[] e11 = request.getParameterValues("txbE11");
                String[] s11 = request.getParameterValues("txbS11");
                String[] e12 = request.getParameterValues("txbE12");
                String[] s12 = request.getParameterValues("txbS12");
                String[] e13 = request.getParameterValues("txbE13");
                String[] s13 = request.getParameterValues("txbS13");
                String[] e14 = request.getParameterValues("txbE14");
                String[] s14 = request.getParameterValues("txbS14");
                String[] e15 = request.getParameterValues("txbE15");
                String[] s15 = request.getParameterValues("txbS15");
                String dtinicio = request.getParameter("datainicial");
                String dtfinal = request.getParameter("datafinal");
                String[] diainicial = dtinicio.split("/");
                String[] diafinal = dtfinal.split("/");
                int x = 0; //auxiliar de iteração do vetor CHB
                int count = 0; //auxiliar para garantir que todas as posicoes de CHB possam ser testadas

                String datainicio = cp.getDtinicial();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date;
                try {
                    date = sdf.parse(datainicio);
                    c.setTime(date);
                } catch (ParseException ex) {
                    Logger.getLogger(cPonto.class.getName()).log(Level.SEVERE, null, ex);
                }
                int dia = c.get(Calendar.DAY_OF_MONTH);
                for (int i = 0; i < cp.getDias(); i++) {
                    while (count < chb.length) {
                        if (chb[x].equals("" + dia)) {
                            cp.e1[i] = e1[x];
                            cp.s1[i] = s1[x];
                            cp.e2[i] = e2[x];
                            cp.s2[i] = s2[x];
                            cp.e3[i] = e3[x];
                            cp.s3[i] = s3[x];
                            cp.e4[i] = e4[x];
                            cp.s4[i] = s4[x];
                            cp.e5[i] = e5[x];
                            cp.s5[i] = s5[x];
                            cp.e6[i] = e6[x];
                            cp.s6[i] = s6[x];
                            cp.e7[i] = e7[x];
                            cp.s7[i] = s7[x];
                            cp.e8[i] = e8[x];
                            cp.s8[i] = s8[x];
                            cp.e9[i] = e9[x];
                            cp.s9[i] = s9[x];
                            cp.e10[i] = e10[x];
                            cp.s10[i] = s10[x];
                            cp.e11[i] = e11[x];
                            cp.s11[i] = s11[x];
                            cp.e12[i] = e12[x];
                            cp.s12[i] = s12[x];
                            cp.e13[i] = e13[x];
                            cp.s13[i] = s13[x];
                            cp.e14[i] = e14[x];
                            cp.s14[i] = s14[x];
                            cp.e15[i] = e15[x];
                            cp.s15[i] = s15[x];
                        }
                        x++;
                        count++;
                        if (x >= chb.length) {
                            x = 0;
                        }
                    }
                    count = 0;
                    c.add(Calendar.DATE, 1);
                    dia = c.get(Calendar.DAY_OF_MONTH);
                }
            }
            cp.refazSoma();
            url = "/jornadaDF";
            break;

            case "registrarCidade": {
                String nome = request.getParameter("nomecidade");
                qInsercoesbd.insereCidade(nome);
            }
            url = "/inserirFeriado";
            break;

            case "emissaoRelatorios": {
                cp.refazSoma();
                salvarDigitacao(request, cp, session);
            }
            url = "/emissaoRelatorios";
            break;

            case "testePDF": {
                String caminho = "C:/pdfTeste.pdf";
                String diaMes = request.getParameter("diainicialemissao");
                int colunas = Integer.parseInt(request.getParameter("selectColunas"));
                String checkArt66 = request.getParameter("Art66");
                String checkArt71 = request.getParameter("Art71");
                String checkArt384 = request.getParameter("Art384");
                String checkExcSem = request.getParameter("ExcSem");
                String checkExcDiario = request.getParameter("ExcDia");
                String checkFaltas = request.getParameter("Faltas");
                String checkAdcNoturno = request.getParameter("adcNoturno");
                String checkNoturno60 = request.getParameter("noturno60");
                String checkRsr = request.getParameter("rsr");
                String checkHorasExtrasDD = request.getParameter("extrasDD");
                String checkHorasExtrasDS = request.getParameter("extrasDS");
                String checkHorasExtrasDM = request.getParameter("extrasDM");
                String checkHorasExtrasTD = request.getParameter("extrasTD");
                String checkHorasExtrasTS = request.getParameter("extrasTS");
                String checkHorasExtrasTM = request.getParameter("extrasTM");
                String checkProrrogacaoAdcNoturno = request.getParameter("prorrogacaoAdcNoturno");
                String checkProrrogacao60 = request.getParameter("prorrogacao60");
                String checkMinutosResiduais = request.getParameter("checkMinutosResiduais");
                String radioCalc = request.getParameter("checkcalc");
                String jornadaIdealEntrada = request.getParameter("jornadaIdealEntrada");
                String jornadaIdealSaida = request.getParameter("jornadaIdealSaida");
                String intervaloAcima = request.getParameter("intervaloAcima");
                String intervaloAbaixo = request.getParameter("intervaloAbaixo");
                String horasTrabalhadas = request.getParameter("horasTrabalhadas");
                String valorExtrasDD = request.getParameter("valorExtrasDD");
                String valorExtrasDS = request.getParameter("valorExtrasDS");
                String valorExtrasDM = request.getParameter("valorExtrasDM");
                String valorExtrasTD1 = request.getParameter("valorExtrasTD1");
                String valorExtrasTD2 = request.getParameter("valorExtrasTD2");
                String valorExtrasTS1 = request.getParameter("valorExtrasTS1");
                String valorExtrasTS2 = request.getParameter("valorExtrasTS2");
                String valorExtrasTM1 = request.getParameter("valorExtrasTM1");
                String valorExtrasTM2 = request.getParameter("valorExtrasTM2");
                String nomePDF = request.getParameter("nomePDF");
                String art66 = "";
                String art71 = "";
                String art384 = "";
                String excSem = "";
                String excDiario = "";
                String faltas = "";
                String adcNoturno = "";
                String noturno60 = "";
                String horasExtrasDD = "";
                String horasExtrasDS = "";
                String horasExtrasDM = "";
                String horasExtrasTD = "";
                String horasExtrasTS = "";
                String horasExtrasTM = "";
                String minutosResiduais = "";
                String rsr = "";
                String prorrogacaoAdcNoturno = "";
                String prorrogacao60 = "";
                int dia;

                art66 = checkMarcacao(checkArt66, art66);
                art71 = checkMarcacao(checkArt71, art71);
                art384 = checkMarcacao(checkArt384, art384);
                excSem = checkMarcacao(checkExcSem, excSem);
                excDiario = checkMarcacao(checkExcDiario, excDiario);
                faltas = checkMarcacao(checkFaltas, faltas);
                adcNoturno = checkMarcacao(checkAdcNoturno, adcNoturno);
                horasExtrasDD = checkMarcacao(checkHorasExtrasDD, horasExtrasDD);
                horasExtrasDS = checkMarcacao(checkHorasExtrasDS, horasExtrasDS);
                horasExtrasDM = checkMarcacao(checkHorasExtrasDM, horasExtrasDM);
                horasExtrasTD = checkMarcacao(checkHorasExtrasTD, horasExtrasTD);
                horasExtrasTS = checkMarcacao(checkHorasExtrasTS, horasExtrasTS);
                horasExtrasTM = checkMarcacao(checkHorasExtrasTM, horasExtrasTM);
                rsr = checkMarcacao(checkRsr, rsr);
                prorrogacaoAdcNoturno = checkMarcacao(checkProrrogacaoAdcNoturno, prorrogacaoAdcNoturno);
                minutosResiduais = checkMarcacao(checkMinutosResiduais, minutosResiduais);
                noturno60 = checkMarcacao(checkNoturno60, noturno60);
                prorrogacao60 = checkMarcacao(checkProrrogacao60, prorrogacao60);

                try {
                    dia = Integer.parseInt(diaMes);
                    if (dia < 1) {
                        dia = 1;
                    }

                    if (dia > 31) {
                        dia = 31;
                    }
                } catch (NumberFormatException e) {
                    dia = 1;
                }

                try {
                    cp.pesquisaDigitacao(cp.id);
                    teste.relatorioPdf(caminho, cp, dia, colunas, art71, art66, art384, radioCalc, excDiario, excSem, intervaloAcima,
                            intervaloAbaixo, horasTrabalhadas, faltas, adcNoturno, horasExtrasDD, valorExtrasDD,
                            horasExtrasDS, valorExtrasDS, horasExtrasDM, valorExtrasDM, horasExtrasTD, valorExtrasTD1, valorExtrasTD2,
                            horasExtrasTS, valorExtrasTS1, valorExtrasTS2, horasExtrasTM, valorExtrasTM1, valorExtrasTM2, rsr,
                            prorrogacaoAdcNoturno, minutosResiduais, jornadaIdealEntrada, jornadaIdealSaida, noturno60, prorrogacao60);
                    downloadArquivoPdf(request, response, nomePDF + ".pdf", "C:/pdfTeste.pdf");
                } catch (DocumentException ex) {
                    Logger.getLogger(cPonto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            url = "/emissaoRelatorios";
            break;

            case "selecionaFeriados": {
                String cidade = request.getParameter("cidades");
                String retorno[] = qInsercoesbd.selectFeriadosCidade(cidade);
                session.setAttribute("parametroFeriados", retorno);
            }
            url = "/inserirFeriado";
            break;

            case "buscaCartao": {
                String parametro = request.getParameter("parametro");
                String valor = request.getParameter("valor");

                session.setAttribute("valorParametro", valor);
                session.setAttribute("parametroPesquisa", parametro);
            }
            url = "/exibeCartoes";
            break;

            case "buscaTodosCartoes": {
                session.setAttribute("parametroPesquisa", "nenhum");
                session.setAttribute("caminho", "busca");
                url = "/exibeCartoes";
            }
            break;

            case "trocaExcedente": {
                String idCartao = cp.id;
                String[] data = request.getParameterValues("datatabela");
                String[] excDiario = request.getParameterValues("excDiario");
                String[] excSem = request.getParameterValues("excSem");

                for (int i = 0; i < data.length; i++) {
                    qCartao.alteraExcDiario(data[i].substring(0, data[i].length() - 4), excDiario[i], idCartao);
                    cp.setExcDiario(excDiario);
                }

                for (int i = 0; i < excSem.length; i++) {
                    qCartao.alteraExcSemana(i, excSem[i], idCartao);
                    cp.setExcSem(excSem);
                }
                cp.refazSoma();
                url = "/trocaExcedente";
            }
            break;

            case "ajustarConformeFolgas": {
                String[] excSem = request.getParameterValues("excSem");
                int cont = 0, valor = 0;
                cont = Integer.parseInt(qInsercoesbd.selectAjustarConformeFolgas(cp.id));
                valor = cont * 8;
                for (int i = 0; i < excSem.length; i++) {
                    cp.excSem[i] = "" + (Double.parseDouble(excSem[i]) - valor);
                }
                url = "/trocaExcedente";
            }
            break;

            case "marcacaoAusencias": {
                String diaInicial = request.getParameter("diainicioausencia");
                String diaFinal = request.getParameter("diafinalausencia");
                String tipoAusencia = request.getParameter("rdbAusencias");
                tipoAusencia = "" + tipoAusencia.split("rad")[1];
                qInsercoesbd.alteraAusencias(tipoAusencia, cp.id, diaInicial, diaFinal);
                url = "/digitarCartao";
            }
            break;

            case "importaCartao": {
                String nomeArquivo = request.getParameter("nomeArquivo");
                String idCartao = (String) session.getAttribute("idCartao");
                String mensagem = leitorTXT.Leitor(nomeArquivo, idCartao);
                session.setAttribute("mensagemCartao", mensagem);
                int index = tDigitacao.getIndex();
                cp.pesquisacartao(idCartao);
                cp.pesquisaDigitacao(idCartao, index);
                tDigitacao = new tabelaDigitacao();
                url = "/digitarCartao";
            }
            break;

            case "importaCartao2": {
                String nomeArquivo = request.getParameter("nomeArquivo");
                String idCartao = (String) session.getAttribute("idCartao");
                String mensagem = leitorTXT.Leitor2(nomeArquivo, idCartao);
                session.setAttribute("mensagemCartao", mensagem);
                int index = tDigitacao.getIndex();
                cp.pesquisacartao(idCartao);
                cp.pesquisaDigitacao(idCartao, index);
                tDigitacao = new tabelaDigitacao();
                url = "/digitarCartao";
            }
            break;

            case "buscaDigitacao": {
                try {
                    cp.pesquisacartao(request.getParameter("auxiliar"));
                    session.setAttribute("idCartao", request.getParameter("auxiliar"));
                } catch (Exception e) {
                    if (cp.excSem == null || cp.excSem.equals("null")) {
                        for (int i = 0; i < cp.excSem.length; i++) {
                            cp.excSem[i] = "44";
                        }
                        cp.excSeg = "8:00";
                        cp.excTer = "8:00";
                        cp.excQua = "8:00";
                        cp.excQui = "8:00";
                        cp.excSex = "8:00";
                        cp.excSab = "8:00";
                        cp.excDom = "8:00";
                    }
                    session.setAttribute("idCartao", request.getParameter("auxiliar"));
                }
            }
            url = "/criarCartao";
            break;

            case "aumentarIndex": {
                salvarDigitacao(request, cp, session);
                tDigitacao.aumentarIndex();
                int index = tDigitacao.getIndex();
                cp.pesquisaDigitacao(cp.id, index);
            }
            url = "/digitarCartao";
            break;

            case "reduzirIndex": {
                salvarDigitacao(request, cp, session);
                tDigitacao.reduzirIndex();
                int index = tDigitacao.getIndex();
                cp.pesquisaDigitacao(cp.id, index);
            }
            url = "/digitarCartao";
            break;

            case "buscarPagina": {
                salvarDigitacao(request, cp, session);
                int indexPagina = 1;
                try {
                    indexPagina = Integer.parseInt(request.getParameter("irPagina"));
                } catch (NumberFormatException e) {

                }
                tDigitacao.setIndex(indexPagina);
                cp.pesquisaDigitacao(cp.id, indexPagina);
            }
            url = "/digitarCartao";
            break;

            default:
                url = "/PaginaInicial";
                break;

        }
        session.setAttribute("tDigitacao", tDigitacao);
        session.setAttribute("cartaoDePonto", cp);

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    public String preencheCampos(String campo) {
        if (campo.equals("")) {
            campo = "00:00";
        }

        return campo;
    }

    public void downloadArquivoPdf(HttpServletRequest request, HttpServletResponse response, String name, String path) throws ServletException, IOException {
        String fileType = "application/pdf";
        response.setContentType(fileType);
        response.setHeader("Content-disposition", "attachment; filename=" + name);

        OutputStream out;
        try (FileInputStream file = new FileInputStream(path)) {
            out = response.getOutputStream();
            int i = file.read();
            while (i != -1) {
                out.write(i);
                i = file.read();
            }
        }
        out.close();
    }

    private void salvarCartao(HttpServletRequest request, cartaoDePonto cp, HttpSession session) throws ParseException {
        String dtInclusao = request.getParameter("datainclusao");
        String processo = request.getParameter("processo");
        String junta = request.getParameter("junta");
        String reclamante = request.getParameter("reclamante");
        String reclamada = request.getParameter("reclamada");
        String datainicio = request.getParameter("datainicio");
        String datafinal = request.getParameter("datafinal");
        String chbmunicipais = request.getParameter("chbmunipais");
        String chbnacionais = request.getParameter("chbnacionais");
        String chbnacionaisf = request.getParameter("chbnacionaisf");
        String idCartao = (String) session.getAttribute("idCartao");
        String excsemanal = request.getParameter("excsemanal");
        String excseg = request.getParameter("excseg");
        String excter = request.getParameter("excter");
        String excqua = request.getParameter("excqua");
        String excqui = request.getParameter("excqui");
        String excsex = request.getParameter("excsex");
        String excsab = request.getParameter("excsab");
        String excdom = request.getParameter("excdom");

        double dias = 0;

        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(datainicio);
            Uteis u = new Uteis();
            dias = u.getDayCountMonth(datainicio, datafinal) + 1;
            calendar.setTime(date);
        } catch (ParseException e) {

        }
        if (chbmunicipais != null) {
            session.setAttribute("municipal", true);
            chbmunicipais = "1";
        } else {
            session.setAttribute("municipal", false);
            chbmunicipais = "0";
        }
        if (chbnacionais != null) {
            session.setAttribute("nacional", true);
            chbnacionais = "1";
        } else {
            session.setAttribute("nacional", false);
            chbnacionais = "0";
        }
        if (chbnacionaisf != null) {
            session.setAttribute("nacionalf", true);
            chbnacionaisf = "1";
        } else {
            session.setAttribute("nacionalf", false);
            chbnacionaisf = "0";
        }

        if (idCartao.equals("0")) {
            idCartao = qCartao.insereCartao(dtInclusao, processo, junta, reclamante, reclamada, datainicio, datafinal, chbmunicipais, chbnacionais,
                    chbnacionaisf, 4, "0");
            qCartao.insereExcedentes(excsemanal, excseg, excter, excqua, excqui, excsex, excsab, excdom, idCartao);
            session.setAttribute("idCartao", idCartao);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(datainicio);
            c.setTime(date);
            int diaSemana = c.get(Calendar.DAY_OF_WEEK) - 1;
            int diaMes = c.get(Calendar.DATE);
            int mes = (c.get(Calendar.MONTH) + 1);
            int ano = c.get(Calendar.YEAR);
            String data = diaMes + "/" + (mes < 10 ? "0" + mes : mes) + "/" + ano;
            int j = 0, k = 0;
            for (float i = 0; i < dias; i++) {
                qCartao.insereDigitacao(data, "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "0.0", "0", idCartao);

                if (diaSemana == 0 || i == (dias - 1)) {
                    qCartao.insereSemana(j, "", idCartao, excsemanal);
                    j++;
                }

                diaSemana++;
                if (diaSemana >= 7) {
                    diaSemana = 0;
                }

                if (diaMes == 1 || i == (dias - 1)) {
                    qCartao.insereMes(k, "", idCartao);
                    k++;
                }
                c.add(Calendar.DATE, 1);
                diaMes = c.get(Calendar.DATE);
                mes = (c.get(Calendar.MONTH) + 1);
                ano = c.get(Calendar.YEAR);
                data = diaMes + "/" + (mes < 10 ? "0" + mes : mes) + "/" + ano;
            }
            cp.pesquisacartao(idCartao);
            cp.insereTotalSemana();
            cp.insereTotalMes();
        } else {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
            String horaAtual = sdf.format(cal.getTime());
            String dataAtual = data.format(cal.getTime());
            cp.status = horaAtual + "-" + dataAtual;
            qCartao.alteraCartao(dtInclusao, processo, junta, reclamante, reclamada, datainicio, datafinal, chbmunicipais, chbnacionais, chbnacionaisf, idCartao, cp.status);
            qCartao.alteraExcedentes(excsemanal, excseg, excter, excqua, excqui, excsex, excsab, excdom, idCartao);
            cp.pesquisacartao(idCartao);
            session.setAttribute("idCartao", idCartao);
        }
        session.setAttribute("datainicio", datainicio);
        session.setAttribute("datafinal", datafinal);
        session.setAttribute("intervalo", dias);
        session.setAttribute("cartaoDePonto", cp);
    }

    private void salvarDigitacao(HttpServletRequest request, cartaoDePonto cp, HttpSession session) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = sdf.parse(cp.getDtinicial());
            c.setTime(date);
        } catch (ParseException ex) {
            Logger.getLogger(cPonto.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabelaDigitacao tDigitacao = (tabelaDigitacao) session.getAttribute("tDigitacao");
        String numColunas = request.getParameter("selectColunas");
        String[] idDigitacao = cp.idDigitacao;
        String[] txbE1 = request.getParameterValues("txbE1");
        String[] txbE2 = request.getParameterValues("txbE2");
        String[] txbE3 = request.getParameterValues("txbE3");
        String[] txbE4 = request.getParameterValues("txbE4");
        String[] txbE5 = request.getParameterValues("txbE5");
        String[] txbE6 = request.getParameterValues("txbE6");
        String[] txbE7 = request.getParameterValues("txbE7");
        String[] txbE8 = request.getParameterValues("txbE8");
        String[] txbE9 = request.getParameterValues("txbE9");
        String[] txbE10 = request.getParameterValues("txbE10");
        String[] txbE11 = request.getParameterValues("txbE11");
        String[] txbE12 = request.getParameterValues("txbE12");
        String[] txbE13 = request.getParameterValues("txbE13");
        String[] txbE14 = request.getParameterValues("txbE14");
        String[] txbE15 = request.getParameterValues("txbE15");
        String[] txbS1 = request.getParameterValues("txbS1");
        String[] txbS2 = request.getParameterValues("txbS2");
        String[] txbS3 = request.getParameterValues("txbS3");
        String[] txbS4 = request.getParameterValues("txbS4");
        String[] txbS5 = request.getParameterValues("txbS5");
        String[] txbS6 = request.getParameterValues("txbS6");
        String[] txbS7 = request.getParameterValues("txbS7");
        String[] txbS8 = request.getParameterValues("txbS8");
        String[] txbS9 = request.getParameterValues("txbS9");
        String[] txbS10 = request.getParameterValues("txbS10");
        String[] txbS11 = request.getParameterValues("txbS11");
        String[] txbS12 = request.getParameterValues("txbS12");
        String[] txbS13 = request.getParameterValues("txbS13");
        String[] txbS14 = request.getParameterValues("txbS14");
        String[] txbS15 = request.getParameterValues("txbS15");
        String[] totalhoras = request.getParameterValues("totalHoras");
        String[] folga = request.getParameterValues("folga");
        String[] totalsemana = request.getParameterValues("totalSemana");
        String[] totalmes = request.getParameterValues("totalMes");

        txbE1 = cp.vetorNuloHora(txbE1);
        txbS1 = cp.vetorNuloHora(txbS1);
        txbE2 = cp.vetorNuloHora(txbE2);
        txbS2 = cp.vetorNuloHora(txbS2);
        txbE3 = cp.vetorNuloHora(txbE3);
        txbS3 = cp.vetorNuloHora(txbS3);
        txbE4 = cp.vetorNuloHora(txbE4);
        txbS4 = cp.vetorNuloHora(txbS4);
        txbE5 = cp.vetorNuloHora(txbE5);
        txbS5 = cp.vetorNuloHora(txbS5);
        txbE6 = cp.vetorNuloHora(txbE6);
        txbS6 = cp.vetorNuloHora(txbS6);
        txbE7 = cp.vetorNuloHora(txbE7);
        txbS7 = cp.vetorNuloHora(txbS7);
        txbE8 = cp.vetorNuloHora(txbE8);
        txbS8 = cp.vetorNuloHora(txbS8);
        txbE9 = cp.vetorNuloHora(txbE9);
        txbS9 = cp.vetorNuloHora(txbS9);
        txbE10 = cp.vetorNuloHora(txbE10);
        txbS10 = cp.vetorNuloHora(txbS10);
        txbE11 = cp.vetorNuloHora(txbE11);
        txbS11 = cp.vetorNuloHora(txbS11);
        txbE12 = cp.vetorNuloHora(txbE12);
        txbS12 = cp.vetorNuloHora(txbS12);
        txbE13 = cp.vetorNuloHora(txbE13);
        txbS13 = cp.vetorNuloHora(txbS13);
        txbE14 = cp.vetorNuloHora(txbE14);
        txbS14 = cp.vetorNuloHora(txbS14);
        txbE15 = cp.vetorNuloHora(txbE15);
        txbS15 = cp.vetorNuloHora(txbS15);
        totalhoras = cp.vetorNuloValor(totalhoras);
        folga = cp.vetorNuloValor(folga);
        totalsemana = cp.vetorNuloValor(totalsemana);
        totalmes = cp.vetorNuloValor(totalmes);

        String idcartao = (String) session.getAttribute("idCartao");
        qInsercoesbd.alteraColunas(numColunas, idcartao);

        for (int i = 0; i < txbE1.length; i++) {
            if (folga[i].equals("S")) {
                folga[i] = "1";
            } else {
                folga[i] = "0";
            }
            qInsercoesbd.alteraDigitacaoPaginado(tDigitacao.getIndex(), txbE1[i], txbS1[i], txbE2[i], txbS2[i], txbE3[i], txbS3[i], txbE4[i], txbS4[i], txbE5[i], txbS5[i], txbE6[i], txbS6[i], txbE7[i], txbS7[i], txbE8[i], txbS8[i],
                    txbE9[i], txbS9[i], txbE10[i], txbS10[i], txbE11[i], txbS11[i], txbE12[i], txbS12[i], txbE13[i], txbS13[i], txbE14[i], txbS14[i], txbE15[i], txbS15[i], totalhoras[i], folga[i], Integer.parseInt(idDigitacao[i]));
        }

        for (int i = 0; i < totalsemana.length; i++) {
            qCartao.alteraSemana(cp.idSem[i], i, totalsemana[i], idcartao, cp.excSem[i]);
        }

        for (int i = 0; i < totalmes.length; i++) {
            qCartao.alteraMes(cp.idMes[i], i, totalmes[i], idcartao);
        }

        cp.pesquisacartao(idcartao);
        session.setAttribute("idCartao", idcartao);
    }

    public String checkMarcacao(String checkBox, String variavel) {
        if (checkBox == null) {
            variavel = "0";
        } else {
            variavel = "1";
        }
        return variavel;
    }

    public float diferencaDatas(String dataInicio, String dataFinal) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1 = myFormat.parse(dataInicio);
            Date date2 = myFormat.parse(dataFinal);
            long diff = date2.getTime() - date1.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
        }
        return 0;
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
    }
}
