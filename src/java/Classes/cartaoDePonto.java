/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Querys.qCartao;
import Querys.qInsercoesbd;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class cartaoDePonto {

    //atributos da tela de criar cartao
    public String dtinclusao;
    public String processo;
    public String junta;
    public String reclamante;
    public String reclamada;
    public String dtinicial;
    public String dtfinal;
    public String feriadosm;
    public String feriadosn;
    public String feriadosnf;
    public String excSeg;
    public String excTer;
    public String excQua;
    public String excQui;
    public String excSex;
    public String excSab;
    public String excDom;
    public String numColunas;
    public String status;
    public String prescricao;
    public String dtajuizamento;
    //atributos da tela de digitação
    public String id;
    public String[] idDigitacao;
    public String[] e1;
    public String[] s1;
    public String[] e2;
    public String[] s2;
    public String[] e3;
    public String[] s3;
    public String[] e4;
    public String[] s4;
    public String[] e5;
    public String[] s5;
    public String[] e6;
    public String[] s6;
    public String[] e7;
    public String[] s7;
    public String[] e8;
    public String[] s8;
    public String[] e9;
    public String[] s9;
    public String[] e10;
    public String[] s10;
    public String[] e11;
    public String[] s11;
    public String[] e12;
    public String[] s12;
    public String[] e13;
    public String[] s13;
    public String[] e14;
    public String[] s14;
    public String[] e15;
    public String[] s15;
    public String[] totaldiario;
    public String[] totalsemanal;
    public String[] totalmensal;
    public String[] folgas;
    public String[] data;
    public String[] excDiario;
    public String[] excSem;
    public String[] excMes;
    public String[] idSem;
    public String[] idMes;
    public String[] ausencia;
    public String[] caracteres;
    public double dias;
    public int diasTrabalhados;

    public cartaoDePonto() {
        numColunas = "4";
    }

    public cartaoDePonto(double dias, String dtinclusao, String idCartao, String processo, String junta, String reclamante, String reclamada,
            String dtinicial, String dtfinal, String feriadosm, String feriadosn, String feriadosnf, String excSeg,
            String excTer, String excQua, String excQui, String excSex, String excSab, String excDom, String numColunas,
            int diasTrabalhados, String status, String prescricao, String dtajuizamento) {
        this.dias = dias;
        this.dtinclusao = dtinclusao;
        this.processo = processo;
        this.junta = junta;
        this.reclamante = reclamante;
        this.reclamada = reclamada;
        this.dtinicial = dtinicial;
        this.dtfinal = dtfinal;
        this.id = idCartao;
        this.feriadosm = feriadosm;
        this.feriadosn = feriadosn;
        this.feriadosnf = feriadosnf;
        this.excSeg = excSeg;
        this.excTer = excTer;
        this.excQua = excQua;
        this.excQui = excQui;
        this.excSex = excSex;
        this.excSab = excSab;
        this.excDom = excDom;
        this.numColunas = numColunas;
        this.diasTrabalhados = diasTrabalhados;
        this.status = status;
        this.prescricao = prescricao;
        this.dtajuizamento = dtajuizamento;

        preencheQuantMesSemana();

        int periodo = (int) dias;
        idDigitacao = new String[periodo];
        data = new String[periodo];
        e1 = new String[periodo];
        s1 = new String[periodo];
        e2 = new String[periodo];
        s2 = new String[periodo];
        e3 = new String[periodo];
        s3 = new String[periodo];
        e4 = new String[periodo];
        s4 = new String[periodo];
        e5 = new String[periodo];
        s5 = new String[periodo];
        e6 = new String[periodo];
        s6 = new String[periodo];
        e7 = new String[periodo];
        s7 = new String[periodo];
        e8 = new String[periodo];
        s8 = new String[periodo];
        e9 = new String[periodo];
        s9 = new String[periodo];
        e10 = new String[periodo];
        s10 = new String[periodo];
        e11 = new String[periodo];
        s11 = new String[periodo];
        e12 = new String[periodo];
        s12 = new String[periodo];
        e13 = new String[periodo];
        s13 = new String[periodo];
        e14 = new String[periodo];
        s14 = new String[periodo];
        e15 = new String[periodo];
        s15 = new String[periodo];
        totaldiario = new String[periodo];
        folgas = new String[periodo];
        excDiario = new String[periodo];
        excSem = new String[periodo];
        excMes = new String[periodo];
        idSem = new String[periodo];
        idMes = new String[periodo];
        ausencia = new String[periodo];
    }

    public String[] getExcMes() {
        return excMes;
    }

    public void setExcMes(String[] excMes) {
        this.excMes = excMes;
    }

    public String[] getExcSem() {
        return excSem;
    }

    public void setExcSem(String[] excSem) {
        this.excSem = excSem;
    }

    public String[] getExcDiario() {
        return excDiario;
    }

    public void setExcDiario(String[] excDiario) {
        this.excDiario = excDiario;
    }

    public String getNumColunas() {
        return numColunas;
    }

    public void setNumColunas(String numColunas) {
        this.numColunas = numColunas;
    }

    public String getDtinclusao() {
        return dtinclusao;
    }

    public String getProcesso() {
        return processo;
    }

    public String getJunta() {
        return junta;
    }

    public String getReclamante() {
        return reclamante;
    }

    public String getReclamada() {
        return reclamada;
    }

    public String getFeriadosm() {
        return feriadosm;
    }

    public String getFeriadosn() {
        return feriadosn;
    }

    public String getFeriadosnf() {
        return feriadosnf;
    }

    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public void setDiasTrabalhados(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getDtajuizamento() {
        return dtajuizamento;
    }

    public void setDtajuizamento(String dtajuizamento) {
        this.dtajuizamento = dtajuizamento;
    }
        
    public String[] getE1() {
        return e1;
    }

    public void setE1(String[] e1) {
        this.e1 = e1;
    }

    public String[] getS1() {
        return s1;
    }

    public void setS1(String[] s1) {
        this.s1 = s1;
    }

    public String[] getE2() {
        return e2;
    }

    public void setE2(String[] e2) {
        this.e2 = e2;
    }

    public String[] getS2() {
        return s2;
    }

    public void setS2(String[] s2) {
        this.s2 = s2;
    }

    public String[] getE3() {
        return e3;
    }

    public void setE3(String[] e3) {
        this.e3 = e3;
    }

    public String[] getS3() {
        return s3;
    }

    public void setS3(String[] s3) {
        this.s3 = s3;
    }

    public String[] getE4() {
        return e4;
    }

    public void setE4(String[] e4) {
        this.e4 = e4;
    }

    public String[] getS4() {
        return s4;
    }

    public void setS4(String[] s4) {
        this.s4 = s4;
    }

    public String[] getE5() {
        return e5;
    }

    public void setE5(String[] e5) {
        this.e5 = e5;
    }

    public String[] getS5() {
        return s5;
    }

    public void setS5(String[] s5) {
        this.s5 = s5;
    }

    public String[] getE6() {
        return e6;
    }

    public void setE6(String[] e6) {
        this.e6 = e6;
    }

    public String[] getS6() {
        return s6;
    }

    public void setS6(String[] s6) {
        this.s6 = s6;
    }

    public String[] getE7() {
        return e7;
    }

    public void setE7(String[] e7) {
        this.e7 = e7;
    }

    public String[] getS7() {
        return s7;
    }

    public void setS7(String[] s7) {
        this.s7 = s7;
    }

    public String[] getE8() {
        return e8;
    }

    public void setE8(String[] e8) {
        this.e8 = e8;
    }

    public String[] getS8() {
        return s8;
    }

    public void setS8(String[] s8) {
        this.s8 = s8;
    }

    public String[] getE9() {
        return e9;
    }

    public void setE9(String[] e9) {
        this.e9 = e9;
    }

    public String[] getS9() {
        return s9;
    }

    public void setS9(String[] s9) {
        this.s9 = s9;
    }

    public String[] getE10() {
        return e10;
    }

    public void setE10(String[] e10) {
        this.e10 = e10;
    }

    public String[] getS10() {
        return s10;
    }

    public void setS10(String[] s10) {
        this.s10 = s10;
    }

    public String[] getE11() {
        return e11;
    }

    public void setE11(String[] e11) {
        this.e11 = e11;
    }

    public String[] getS11() {
        return s11;
    }

    public void setS11(String[] s11) {
        this.s11 = s11;
    }

    public String[] getE12() {
        return e12;
    }

    public void setE12(String[] e12) {
        this.e12 = e12;
    }

    public String[] getS12() {
        return s12;
    }

    public void setS12(String[] s12) {
        this.s12 = s12;
    }

    public String[] getE13() {
        return e13;
    }

    public void setE13(String[] e13) {
        this.e13 = e13;
    }

    public String[] getS13() {
        return s13;
    }

    public void setS13(String[] s13) {
        this.s13 = s13;
    }

    public String[] getE14() {
        return e14;
    }

    public void setE14(String[] e14) {
        this.e14 = e14;
    }

    public String[] getS14() {
        return s14;
    }

    public void setS14(String[] s14) {
        this.s14 = s14;
    }

    public String[] getE15() {
        return e15;
    }

    public void setE15(String[] e15) {
        this.e15 = e15;
    }

    public String[] getS15() {
        return s15;
    }

    public void setS15(String[] s15) {
        this.s15 = s15;
    }

    public String getDtinicial() {
        return dtinicial;
    }

    public String[] getAusencia() {
        return ausencia;
    }

    public void setAusencia(String[] ausencia) {
        this.ausencia = ausencia;
    }

    public void setDtinicial(String dtinicial) {
        this.dtinicial = dtinicial;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dtinicial);
            Uteis u = new Uteis();
            dias = u.getDayCountMonth(dtinicial, dtfinal) + 1;
            calendar.setTime(date);
        } catch (ParseException e) {

        }
    }

    public String getDtfinal() {
        return dtfinal;
    }

    public void setDtfinal(String dtfinal) {
        this.dtfinal = dtfinal;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dtinicial);
            Uteis u = new Uteis();
            dias = u.getDayCountMonth(dtinicial, dtfinal) + 1;
            calendar.setTime(date);
        } catch (ParseException e) {

        }
    }

    public double getDias() {
        return dias;
    }

    public void setDias(double dias) {
        this.dias = dias;
    }

    public String[] vetorNuloHora(String[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == null || vetor[i].equals("") || vetor[i].equals(" ") || vetor[i].equals(":") || vetor[i].equals("null")) {
                vetor[i] = "00:00";
            }
        }
        return vetor;
    }

    public String[] vetorNuloValor(String[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == null || vetor[i].equals("") || vetor[i].equals(" ")) {
                vetor[i] = "0.0";
            }
        }
        return vetor;
    }

    public String[] vetorNuloFolga(String[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == null || vetor[i].equals("") || vetor[i].equals(" ")) {
                vetor[i] = "N";
            }
        }
        return vetor;
    }

    public void pesquisacartao(String id) {
        this.id = id;
        String parametros2[] = qCartao.selectCartao(id);
        //String parametros3[] = qCartao.selectExcedentes(id);
        dtinclusao = parametros2[0];
        processo = parametros2[1];
        junta = parametros2[2];
        reclamante = parametros2[3];
        reclamada = parametros2[4];
        dtinicial = parametros2[5];
        dtfinal = parametros2[6];
        excSeg = parametros2[11];
        excTer = parametros2[12];
        excQua = parametros2[13];
        excQui = parametros2[14];
        excSex = parametros2[15];
        excSab = parametros2[16];
        excDom = parametros2[17];
        numColunas = parametros2[18];
        prescricao = parametros2[19];
        dtajuizamento = parametros2[20];
        excSem = qCartao.selectExcSem(id);
        excMes = qCartao.selectExcMes(id);
        idSem = qCartao.selectIdSem(id);
        idMes = qCartao.selectIdMes(id);

        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dtinicial);
            Uteis u = new Uteis();
            dias = u.getDayCountMonth(dtinicial, dtfinal);
            dias += 1;
            calendar.setTime(date);
        } catch (ParseException e) {

        }
    }

    public void pesquisaDigitacao(String id, int index) {
        idDigitacao = qInsercoesbd.selectIdDigitacao(id);
        String parametros[] = qInsercoesbd.selectDigitacaoPaginado(id, index, idDigitacao);
        int quantDias = (int) dias;
        data = new String[quantDias];
        e1 = new String[quantDias];
        s1 = new String[quantDias];
        e2 = new String[quantDias];
        s2 = new String[quantDias];
        e3 = new String[quantDias];
        s3 = new String[quantDias];
        e4 = new String[quantDias];
        s4 = new String[quantDias];
        e5 = new String[quantDias];
        s5 = new String[quantDias];
        e6 = new String[quantDias];
        s6 = new String[quantDias];
        e7 = new String[quantDias];
        s7 = new String[quantDias];
        e8 = new String[quantDias];
        s8 = new String[quantDias];
        e9 = new String[quantDias];
        s9 = new String[quantDias];
        e10 = new String[quantDias];
        s10 = new String[quantDias];
        e11 = new String[quantDias];
        s11 = new String[quantDias];
        e12 = new String[quantDias];
        s12 = new String[quantDias];
        e13 = new String[quantDias];
        s13 = new String[quantDias];
        e14 = new String[quantDias];
        s14 = new String[quantDias];
        e15 = new String[quantDias];
        s15 = new String[quantDias];
        totaldiario = new String[quantDias];
        folgas = new String[quantDias];
        excDiario = new String[quantDias];
        data = qInsercoesbd.selectData(id);
        ausencia = new String[quantDias];
        diasTrabalhados = 0;

        if (quantDias >= parametros.length) {
            for (int i = 0; i < parametros.length; i++) {
                String[] linhaTabela = parametros[i].split(";;");
                e1[i] = linhaTabela[0];
                s1[i] = linhaTabela[1];
                e2[i] = linhaTabela[2];
                s2[i] = linhaTabela[3];
                e3[i] = linhaTabela[4];
                s3[i] = linhaTabela[5];
                e4[i] = linhaTabela[6];
                s4[i] = linhaTabela[7];
                e5[i] = linhaTabela[8];
                s5[i] = linhaTabela[9];
                e6[i] = linhaTabela[10];
                s6[i] = linhaTabela[11];
                e7[i] = linhaTabela[12];
                s7[i] = linhaTabela[13];
                e8[i] = linhaTabela[14];
                s8[i] = linhaTabela[15];
                e9[i] = linhaTabela[16];
                s9[i] = linhaTabela[17];
                e10[i] = linhaTabela[18];
                s10[i] = linhaTabela[19];
                e11[i] = linhaTabela[20];
                s11[i] = linhaTabela[21];
                e12[i] = linhaTabela[22];
                s12[i] = linhaTabela[23];
                e13[i] = linhaTabela[24];
                s13[i] = linhaTabela[25];
                e14[i] = linhaTabela[26];
                s14[i] = linhaTabela[27];
                e15[i] = linhaTabela[28];
                s15[i] = linhaTabela[29];
                totaldiario[i] = linhaTabela[30];
                if (linhaTabela[31].equals("0") || linhaTabela[31] == null || linhaTabela[31].equals("") || linhaTabela[31].equals(" ")) {
                    folgas[i] = "N";
                } else {
                    folgas[i] = "S";
                }
                excDiario[i] = linhaTabela[32];
                if (linhaTabela[33].equals("NULL") || linhaTabela[33].equals("null")) {
                    if (totaldiario[i].equals("0")) {
                        linhaTabela[33] = "Falta";
                    } else {
                        linhaTabela[33] = "";
                    }
                }
                ausencia[i] = linhaTabela[33];

                if (Double.parseDouble(totaldiario[i]) > 0) {
                    diasTrabalhados++;
                }
            }
        }
        preencheQuantMesSemana();

        vetorNuloHora(e1);
        vetorNuloHora(s1);
        vetorNuloHora(e2);
        vetorNuloHora(s2);
        vetorNuloHora(e3);
        vetorNuloHora(s3);
        vetorNuloHora(e4);
        vetorNuloHora(s4);
        vetorNuloHora(e5);
        vetorNuloHora(s5);
        vetorNuloHora(e6);
        vetorNuloHora(s6);
        vetorNuloHora(e7);
        vetorNuloHora(s7);
        vetorNuloHora(e8);
        vetorNuloHora(s8);
        vetorNuloHora(e9);
        vetorNuloHora(s9);
        vetorNuloHora(e10);
        vetorNuloHora(s10);
        vetorNuloHora(e11);
        vetorNuloHora(s11);
        vetorNuloHora(e12);
        vetorNuloHora(s12);
        vetorNuloHora(e13);
        vetorNuloHora(s13);
        vetorNuloHora(e14);
        vetorNuloHora(s14);
        vetorNuloHora(e15);
        vetorNuloHora(s15);
        vetorNuloHora(excDiario);
        vetorNuloFolga(folgas);
        vetorNuloValor(totaldiario);
    }

    public void pesquisaDigitacao(String id) {
        String parametros[] = qInsercoesbd.selectDigitacao(id);
        int quantDias = (int) dias;
        data = new String[quantDias];
        e1 = new String[quantDias];
        s1 = new String[quantDias];
        e2 = new String[quantDias];
        s2 = new String[quantDias];
        e3 = new String[quantDias];
        s3 = new String[quantDias];
        e4 = new String[quantDias];
        s4 = new String[quantDias];
        e5 = new String[quantDias];
        s5 = new String[quantDias];
        e6 = new String[quantDias];
        s6 = new String[quantDias];
        e7 = new String[quantDias];
        s7 = new String[quantDias];
        e8 = new String[quantDias];
        s8 = new String[quantDias];
        e9 = new String[quantDias];
        s9 = new String[quantDias];
        e10 = new String[quantDias];
        s10 = new String[quantDias];
        e11 = new String[quantDias];
        s11 = new String[quantDias];
        e12 = new String[quantDias];
        s12 = new String[quantDias];
        e13 = new String[quantDias];
        s13 = new String[quantDias];
        e14 = new String[quantDias];
        s14 = new String[quantDias];
        e15 = new String[quantDias];
        s15 = new String[quantDias];
        totaldiario = new String[quantDias];
        folgas = new String[quantDias];
        idDigitacao = new String[quantDias];
        excDiario = new String[quantDias];
        data = qInsercoesbd.selectData(id);

        if (quantDias >= parametros.length) {
            for (int i = 0; i < parametros.length; i++) {
                String[] linhaTabela = parametros[i].split(";;");
                e1[i] = linhaTabela[0];
                s1[i] = linhaTabela[1];
                e2[i] = linhaTabela[2];
                s2[i] = linhaTabela[3];
                e3[i] = linhaTabela[4];
                s3[i] = linhaTabela[5];
                e4[i] = linhaTabela[6];
                s4[i] = linhaTabela[7];
                e5[i] = linhaTabela[8];
                s5[i] = linhaTabela[9];
                e6[i] = linhaTabela[10];
                s6[i] = linhaTabela[11];
                e7[i] = linhaTabela[12];
                s7[i] = linhaTabela[13];
                e8[i] = linhaTabela[14];
                s8[i] = linhaTabela[15];
                e9[i] = linhaTabela[16];
                s9[i] = linhaTabela[17];
                e10[i] = linhaTabela[18];
                s10[i] = linhaTabela[19];
                e11[i] = linhaTabela[20];
                s11[i] = linhaTabela[21];
                e12[i] = linhaTabela[22];
                s12[i] = linhaTabela[23];
                e13[i] = linhaTabela[24];
                s13[i] = linhaTabela[25];
                e14[i] = linhaTabela[26];
                s14[i] = linhaTabela[27];
                e15[i] = linhaTabela[28];
                s15[i] = linhaTabela[29];
                totaldiario[i] = linhaTabela[30];
                if (linhaTabela[31].equals("0") || linhaTabela[31] == null || linhaTabela[31].equals("") || linhaTabela[31].equals(" ")) {
                    folgas[i] = "N";
                } else {
                    folgas[i] = "S";
                }
                excDiario[i] = linhaTabela[32];
                idDigitacao[i] = linhaTabela[33];
            }
        }
        preencheQuantMesSemana();
        excSem = qCartao.selectExcSem(id);
        excMes = qCartao.selectExcMes(id);
        idSem = qCartao.selectIdSem(id);
        idMes = qCartao.selectIdMes(id);
        vetorNuloHora(e1);
        vetorNuloHora(s1);
        vetorNuloHora(e2);
        vetorNuloHora(s2);
        vetorNuloHora(e3);
        vetorNuloHora(s3);
        vetorNuloHora(e4);
        vetorNuloHora(s4);
        vetorNuloHora(e5);
        vetorNuloHora(s5);
        vetorNuloHora(e6);
        vetorNuloHora(s6);
        vetorNuloHora(e7);
        vetorNuloHora(s7);
        vetorNuloHora(e8);
        vetorNuloHora(s8);
        vetorNuloHora(e9);
        vetorNuloHora(s9);
        vetorNuloHora(e10);
        vetorNuloHora(s10);
        vetorNuloHora(e11);
        vetorNuloHora(s11);
        vetorNuloHora(e12);
        vetorNuloHora(s12);
        vetorNuloHora(e13);
        vetorNuloHora(s13);
        vetorNuloHora(e14);
        vetorNuloHora(s14);
        vetorNuloHora(e15);
        vetorNuloHora(s15);
        vetorNuloHora(excDiario);
        vetorNuloFolga(folgas);
        vetorNuloValor(totaldiario);
    }

    private void preencheQuantMesSemana() {
        double semana = 0;
        double mes = 0;

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        int day = 0;
        int diaSemana = 0;

        try {
            date = sdf.parse(dtinicial);
            c.setTime(date);
            diaSemana = c.get(Calendar.DAY_OF_WEEK) - 1;
        } catch (ParseException ex) {
            Logger.getLogger(cartaoDePonto.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < dias; i++) {
            if (diaSemana == 0 || i == (dias - 1)) {
                semana++;
            }
            diaSemana++;
            if (diaSemana > 6) {
                diaSemana = 0;
            }
        }
        //for que conta o numero de meses
        for (int i = 0; i < dias; i++) {
            c.add(Calendar.DATE, 1);
            day = c.get(Calendar.DATE);
            if (day == 1 || i == dias - 1) {
                mes++;
            }
        }

        totalsemanal = new String[(int) semana];
        totalmensal = new String[(int) mes];
        String[] vetorSemanaBanco = qInsercoesbd.selectDigitacaoSemana(id);
        String[] vetorMesBanco = qInsercoesbd.selectDigitacaoMes(id);
        int x = 0;

        for (int i = 0; i < totalsemanal.length; i++) {
            totalsemanal[i] = "0";
        }

        for (int i = 0; i < totalmensal.length; i++) {
            totalmensal[i] = "0";
        }

        while (x < totalsemanal.length && x < vetorSemanaBanco.length) {
            totalsemanal[x] = vetorSemanaBanco[x];
            x++;
        }
        x = 0;
        while (x < totalmensal.length && x < vetorMesBanco.length) {
            totalmensal[x] = vetorMesBanco[x];
            x++;
        }
    }

    public void insereTotalSemana() {
        for (int i = 0; i < idSem.length; i++) {
            qCartao.insereSemana(i, "0", id, excSem[i]);
        }
    }

    public void insereTotalMes() {
        for (int i = 0; i < idMes.length; i++) {
            qCartao.insereMes(i, "0", id);
        }
    }

    public void alteraTotalSemana() {
        for (int i = 0; i < idSem.length; i++) {
            qCartao.alteraSemana(idSem[i], i, totalsemanal[i], id, excSem[i]);
        }
    }

    public void alteraTotalMes() {
        for (int i = 0; i < idMes.length; i++) {
            qCartao.alteraMes(idMes[i], i, totalmensal[i], id);
        }
    }

    public void refazSoma() {
        double novoTotal = 0;
        for (int i = 0; i < totaldiario.length; i++) {
            novoTotal += Double.parseDouble(retornaValorDecimal(s1[i])) - Double.parseDouble(retornaValorDecimal(e1[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s2[i])) - Double.parseDouble(retornaValorDecimal(e2[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s3[i])) - Double.parseDouble(retornaValorDecimal(e3[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s4[i])) - Double.parseDouble(retornaValorDecimal(e4[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s5[i])) - Double.parseDouble(retornaValorDecimal(e5[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s6[i])) - Double.parseDouble(retornaValorDecimal(e6[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s7[i])) - Double.parseDouble(retornaValorDecimal(e7[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s8[i])) - Double.parseDouble(retornaValorDecimal(e8[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s9[i])) - Double.parseDouble(retornaValorDecimal(e9[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s10[i])) - Double.parseDouble(retornaValorDecimal(e10[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s11[i])) - Double.parseDouble(retornaValorDecimal(e11[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s12[i])) - Double.parseDouble(retornaValorDecimal(e12[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s13[i])) - Double.parseDouble(retornaValorDecimal(e13[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s14[i])) - Double.parseDouble(retornaValorDecimal(e14[i]));
            novoTotal += Double.parseDouble(retornaValorDecimal(s15[i])) - Double.parseDouble(retornaValorDecimal(e15[i]));
            totaldiario[i] = "" + novoTotal;
            novoTotal = 0;
        }
    }

    /**
     *
     * @param valor
     * @return
     */
    public String retornaValorDecimal(String valor) {
        double novoTotal = 0, horas = 0, minutos = 0;
        horas += Double.parseDouble(valor.split(":")[0]) * 60;
        minutos += Double.parseDouble(valor.split(":")[1]);
        novoTotal += (horas / 60) + minutos;
        valor = "" + novoTotal;
        return valor;
    }
}
