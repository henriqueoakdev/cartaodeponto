/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class PDF {

    private static int hours71;
    private static int minutes71;
    DecimalFormat df = new DecimalFormat("#.##");

    public void relatorioPdf(String filename, cartaoDePonto cp, int diaMes, int colunas, String art71, String art66, String art384, String calc,
            String excDiario, String excSem, String intervaloAcima, String intervaloAbaixo, String horasTrabalhadas, String faltas,
            String adcNoturno, String horasExtrasDD, String valorExtrasDD, String horasExtrasDS, String valorExtrasDS, String horasExtrasDM,
            String valorExtrasDM, String horasExtrasTD, String valorExtrasTD1, String valorExtrasTD2, String horasExtrasTS, String valorExtrasTS1,
            String valorExtrasTS2, String horasExtrasTM, String valorExtrasTM1, String valorExtrasTM2, String rsr, String prorrogacaoAdcNoturno,
            String minutosResiduais, String jornadaIdealEntrada, String jornadaIdealSaida, String noturno60, String prorrogacao60)
            throws DocumentException, IOException {
        this.hours71 = 0;
        this.minutes71 = 0;
        String valorArt71 = "";
        String calcArt71[] = calcIntervalo(cp, horasTrabalhadas, intervaloAcima, intervaloAbaixo);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        double totalExcDiario = 0, totalFaltas = 0, totalAdcNoturno = 0, adcNoturnoDia = 0;
        int horasExcDiario = 0, minutosExcDiario = 0, horasFaltas = 0, minutosFaltas = 0, horasAdcNoturno = 0, minutosAdcNoturno = 0,
                horasArt71 = 0, minutosArt71 = 0, horasArt66 = 0, minutosArt66 = 0, totalHorasArt66 = 0, totalMinutosArt66 = 0, horasEDD = 0,
                minutosEDD = 0, horasEDS = 0, minutosEDS = 0, horasETD1 = 0, minutosETD1 = 0, horasETD2 = 0, minutosETD2 = 0,
                totalHorasNormaisED = 0, totalMinutosNormaisED = 0, totalHorasDuplasED = 0, totalMinutosDuplasED = 0, horasNormaisDD = 0,
                minutosNormaisDD = 0, horasNormaisDS, minutosNormaisDS, horasNormaisDM = 0, minutosNormaisDM = 0, horasNormaisTD, minutosNormaisTD,
                horasETS1 = 0, minutosETS1, horasETS2 = 0, minutosETS2, horasNormaisTS = 0, minutosNormaisTS = 0, totalHorasNormaisES = 0,
                horasETM1 = 0, minutosETM1, horasETM2 = 0, minutosETM2, horasNormaisTM = 0, minutosNormaisTM = 0, totalMinutosNormaisES = 0,
                totalHorasDuplasES = 0, totalMinutosDuplasES = 0, totalHorasRsr = 0, totalMinutosRsr = 0, totalHorasMR = 0, totalMinutosMR = 0,
                horasEDM = 0, minutosEDM = 0, totalHorasNormaisEM = 0, totalMinutosNormaisEM = 0, totalHorasDuplasEM = 0,
                totalMinutosDuplasEM = 0, totalHorasEDM = 0, totalMinutosEDM = 0, totalHorasNormaisTD = 0, totalMinutosNormaisTD = 0,
                totalHorasETD1 = 0, totalMinutosETD1 = 0, totalHorasETD2 = 0, totalMinutosETD2 = 0, totalHorasNormaisTS = 0,
                totalMinutosNormaisTS = 0, totalHorasETS1 = 0, totalMinutosETS1 = 0, totalHorasETS2 = 0, totalMinutosETS2 = 0,
                totalHorasNormaisTM = 0, totalMinutosNormaisTM = 0, totalHorasETM1 = 0, totalMinutosETM1 = 0, totalHorasETM2 = 0,
                totalMinutosETM2 = 0, totalHorasArt384 = 0, totalMinutosArt384 = 0;
        int day = 0;
        int month = 0;
        int year = 1990;
        int diaSemana = 0;
        int contM = 0;
        boolean acabou = false;
        int diasTrabalhados = 0;
        try {
            date = sdf.parse(cp.getDtinicial());
            c.setTime(date);
            day = c.get(Calendar.DATE);
            month = c.get(Calendar.MONTH) + 1;
            year = c.get(Calendar.YEAR);
            if (month == 1) {
                month = 12;
                year--;
            }
            diaSemana = c.get(Calendar.DAY_OF_WEEK) - 1;
        } catch (ParseException ex) {
            Logger.getLogger(cartaoDePonto.class.getName()).log(Level.SEVERE, null, ex);
        }

        // step 1
        Document document = new Document(PageSize.A4.rotate(), 45, 45, 15, 15);
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Cartão de Ponto"));
        document.add(new Paragraph("Data de Inclusão: " + cp.dtinclusao + "     Processo: " + cp.processo + "     Junta: " + cp.junta));
        document.add(new Paragraph("Reclamante: " + cp.reclamante + "                       Reclamada: " + cp.reclamada));
        document.add(new Paragraph("Data Inicial: " + cp.dtinicial + "              Data Final: " + cp.dtfinal));
        if (month >= 10) {
            document.add(new Paragraph("Mês: " + month + "/" + year));
        } else {
            document.add(new Paragraph("Mês: 0" + month + "/" + year));
        }
        document.add(new Paragraph(" "));

        PdfPCell cell = new PdfPCell();
        int tamanhoFonte = 8;
        if (colunas > 10) {
            tamanhoFonte = 6;
        }
        Font font = FontFactory.getFont(FontFactory.COURIER, tamanhoFonte, BaseColor.BLACK);

        int[] qtColunas = vetores(colunas, art71, art66, art384, calc, excDiario, excSem, faltas, adcNoturno, horasExtrasDD, horasExtrasDS,
                horasExtrasDM, horasExtrasTD, horasExtrasTS, horasExtrasTM, rsr, minutosResiduais);
        PdfPTable table = new PdfPTable(qtColunas.length);
        table.setWidthPercentage(110);
        table.setWidths(qtColunas);
        //cria os titulos das colunas
        table = cabecalhoTabela(table, colunas, font, art71, art66, art384, calc, excDiario, excSem, faltas, adcNoturno, horasExtrasDD, horasExtrasDS,
                horasExtrasDM, horasExtrasTD, horasExtrasTS, horasExtrasTM, rsr, minutosResiduais);

        //linha 2
        int contSem = 0; //contador de semanas
        int horasExcSem = 0;
        int minutosExcSem = 0;
        int totalHorasExcSem = 0;
        int totalMinutosExcSem = 0;
        boolean fds = false;
        for (int i = 0; i < cp.dias; i++) {
            table.addCell(novaCelula(new Phrase("" + cp.data[i], font), fds));
            switch (colunas) {
                case 1: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                }
                break;

                case 2: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                }
                break;

                case 3: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                }
                break;

                case 4: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                }
                break;

                case 5: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                }
                break;

                case 6: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                }
                break;

                case 7: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                    selecionaColunas(table, font, cp.e7[i], cp.s7[i], fds);
                }
                break;

                case 8: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                    selecionaColunas(table, font, cp.e7[i], cp.s7[i], fds);
                    selecionaColunas(table, font, cp.e8[i], cp.s8[i], fds);
                }
                break;

                case 9: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                    selecionaColunas(table, font, cp.e7[i], cp.s7[i], fds);
                    selecionaColunas(table, font, cp.e8[i], cp.s8[i], fds);
                    selecionaColunas(table, font, cp.e9[i], cp.s9[i], fds);
                }
                break;

                case 10: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                    selecionaColunas(table, font, cp.e7[i], cp.s7[i], fds);
                    selecionaColunas(table, font, cp.e8[i], cp.s8[i], fds);
                    selecionaColunas(table, font, cp.e9[i], cp.s9[i], fds);
                    selecionaColunas(table, font, cp.e10[i], cp.s10[i], fds);
                }
                break;

                case 11: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                    selecionaColunas(table, font, cp.e7[i], cp.s7[i], fds);
                    selecionaColunas(table, font, cp.e8[i], cp.s8[i], fds);
                    selecionaColunas(table, font, cp.e9[i], cp.s9[i], fds);
                    selecionaColunas(table, font, cp.e10[i], cp.s10[i], fds);
                    selecionaColunas(table, font, cp.e11[i], cp.s11[i], fds);
                }
                break;

                case 12: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                    selecionaColunas(table, font, cp.e7[i], cp.s7[i], fds);
                    selecionaColunas(table, font, cp.e8[i], cp.s8[i], fds);
                    selecionaColunas(table, font, cp.e9[i], cp.s9[i], fds);
                    selecionaColunas(table, font, cp.e10[i], cp.s10[i], fds);
                    selecionaColunas(table, font, cp.e11[i], cp.s11[i], fds);
                    selecionaColunas(table, font, cp.e12[i], cp.s12[i], fds);
                }
                break;

                case 13: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                    selecionaColunas(table, font, cp.e7[i], cp.s7[i], fds);
                    selecionaColunas(table, font, cp.e8[i], cp.s8[i], fds);
                    selecionaColunas(table, font, cp.e9[i], cp.s9[i], fds);
                    selecionaColunas(table, font, cp.e10[i], cp.s10[i], fds);
                    selecionaColunas(table, font, cp.e11[i], cp.s11[i], fds);
                    selecionaColunas(table, font, cp.e12[i], cp.s12[i], fds);
                    selecionaColunas(table, font, cp.e13[i], cp.s13[i], fds);
                }
                break;

                case 14: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                    selecionaColunas(table, font, cp.e7[i], cp.s7[i], fds);
                    selecionaColunas(table, font, cp.e8[i], cp.s8[i], fds);
                    selecionaColunas(table, font, cp.e9[i], cp.s9[i], fds);
                    selecionaColunas(table, font, cp.e10[i], cp.s10[i], fds);
                    selecionaColunas(table, font, cp.e11[i], cp.s11[i], fds);
                    selecionaColunas(table, font, cp.e12[i], cp.s12[i], fds);
                    selecionaColunas(table, font, cp.e13[i], cp.s13[i], fds);
                    selecionaColunas(table, font, cp.e14[i], cp.s14[i], fds);
                }
                break;

                case 15: {
                    selecionaColunas(table, font, cp.e1[i], cp.s1[i], fds);
                    selecionaColunas(table, font, cp.e2[i], cp.s2[i], fds);
                    selecionaColunas(table, font, cp.e3[i], cp.s3[i], fds);
                    selecionaColunas(table, font, cp.e4[i], cp.s4[i], fds);
                    selecionaColunas(table, font, cp.e5[i], cp.s5[i], fds);
                    selecionaColunas(table, font, cp.e6[i], cp.s6[i], fds);
                    selecionaColunas(table, font, cp.e7[i], cp.s7[i], fds);
                    selecionaColunas(table, font, cp.e8[i], cp.s8[i], fds);
                    selecionaColunas(table, font, cp.e9[i], cp.s9[i], fds);
                    selecionaColunas(table, font, cp.e10[i], cp.s10[i], fds);
                    selecionaColunas(table, font, cp.e11[i], cp.s11[i], fds);
                    selecionaColunas(table, font, cp.e12[i], cp.s12[i], fds);
                    selecionaColunas(table, font, cp.e13[i], cp.s13[i], fds);
                    selecionaColunas(table, font, cp.e14[i], cp.s14[i], fds);
                    selecionaColunas(table, font, cp.e15[i], cp.s15[i], fds);
                }
                break;
            }
            double totalDiario = Double.parseDouble(converteDecimaisEmMinutos(cp.totaldiario[i]));
            if (totalDiario > 0) {
                diasTrabalhados++;
            }
            table.addCell(novaCelula(new Phrase((cp.ausencia[i]), font), fds));
            table.addCell(novaCelula(new Phrase(converteTotal((totalDiario / 60) + ""), font), fds));
            if (art71.equals("1")) {
                if (calc.equals("integral")) {
                    if (Double.parseDouble(cp.totaldiario[i]) <= 0) {
                        valorArt71 = "00:00";
                    } else if (Double.parseDouble(cp.totaldiario[i]) < Double.parseDouble(horasTrabalhadas.split(":")[0])) {
                        valorArt71 = intervaloAbaixo;
                        horasArt71 += Double.parseDouble(valorArt71.split(":")[0]);
                        minutosArt71 += Double.parseDouble(valorArt71.split(":")[1]);
                        if (minutosArt71 >= 60) {
                            minutosArt71 -= 60;
                            horasArt71 += 1;
                        }
                        if (minutosArt71 < 0) {
                            minutosArt71 *= -1;
                        }
                    } else {
                        valorArt71 = intervaloAcima;
                        horasArt71 += Double.parseDouble(valorArt71.split(":")[0]);
                        minutosArt71 += Double.parseDouble(valorArt71.split(":")[1]);
                        if (minutosArt71 >= 60) {
                            minutosArt71 -= 60;
                            horasArt71 += 1;
                        }
                        if (minutosArt71 < 0) {
                            minutosArt71 *= -1;
                        }
                    }
                    table.addCell(novaCelula(new Phrase("" + valorArt71, font), fds));
                } else if (calc.equals("diferenca")) {
                    table.addCell(novaCelula(new Phrase("" + calcArt71[i], font), fds));
                    horasArt71 += Integer.parseInt(calcArt71[i].split(":")[0]);
                    minutosArt71 += Integer.parseInt(calcArt71[i].split(":")[1]);
                }
            }
            if (art384.equals("1")) {
                String horasMinutos = cp.totaldiario[i];
                if (Double.parseDouble(converteDecimaisEmMinutos(horasMinutos)) >= 5) {
                    table.addCell(novaCelula(new Phrase("00:15", font), fds));
                    totalMinutosArt384 += 15;
                    if (totalMinutosArt384 >= 60) {
                        totalMinutosArt384 -= 60;
                        totalHorasArt384++;
                    }
                }
            }
            if (excSem.equals("1")) {
                if (diaSemana == 0 || i == (cp.dias - 1)) {
                    horasExcSem = Integer.parseInt(converteTotal(cp.totalsemanal[contSem]).split(":")[0]) - Integer.parseInt(cp.excSem[contSem].split(":")[0]);
                    minutosExcSem = Integer.parseInt(converteTotal(cp.totalsemanal[contSem]).split(":")[1]) - Integer.parseInt(cp.excSem[contSem].split(":")[1]);
                    if (horasExcSem > 0 && minutosExcSem > 0) {
                        totalHorasExcSem += horasExcSem;
                        totalMinutosExcSem += minutosExcSem;
                        table.addCell(novaCelula(new Phrase(verificaExibicao(totalHorasExcSem, totalMinutosExcSem), font), fds));
                    } else {
                        table.addCell(novaCelula(new Phrase("00:00", font), fds));
                    }
                } else {
                    table.addCell(emptyCell(fds));
                }
            }
            if (excDiario.equals("1")) {
                double excedente = Double.parseDouble(converteDecimaisEmMinutos(cp.totaldiario[i])) - Double.parseDouble(converteHorasEmMinutos(cp.excDiario[i]));
                if (excedente < 0) {
                    excedente = 0;
                }
                totalExcDiario += excedente;
                if (excedente > 0) {
                    table.addCell(novaCelula(new Phrase(converteTotal((excedente / 60) + ""), font), fds));
                } else {
                    table.addCell(novaCelula(new Phrase("00:00", font), fds));
                }
            }
            if (faltas.equals("1")) {
                double total = Double.parseDouble(converteHorasEmMinutos(cp.excDiario[i])) - Double.parseDouble(converteDecimaisEmMinutos(cp.totaldiario[i]));
                totalFaltas += total;
                if (total > 0) {
                    table.addCell(novaCelula(new Phrase(converteTotal((total / 60) + ""), font), fds));
                } else {
                    table.addCell(novaCelula(new Phrase("00:00", font), fds));
                }
            }
            if (adcNoturno.equals("1")) {
                adcNoturnoDia += adicionalNoturno(cp.e1[i], cp.s1[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e2[i], cp.s2[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e3[i], cp.s3[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e4[i], cp.s4[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e5[i], cp.s5[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e6[i], cp.s6[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e7[i], cp.s7[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e8[i], cp.s8[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e9[i], cp.s9[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e10[i], cp.s10[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e11[i], cp.s11[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e13[i], cp.s13[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e14[i], cp.s14[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                adcNoturnoDia += adicionalNoturno(cp.e15[i], cp.s15[i], prorrogacaoAdcNoturno, noturno60, prorrogacao60);
                table.addCell(novaCelula(new Phrase("" + converteTotal("" + adcNoturnoDia), font), fds));
                totalAdcNoturno += adcNoturnoDia;
                adcNoturnoDia = 0;
            }
            if (art66.equals("1")) {
                String ultimaSaida = "00:00";
                int horasUltimaSaida = 0, minutosUltimaSaida = 0;
                if (i - 1 >= 0) {
                    ultimaSaida = testaValor(cp.s1[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s2[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s3[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s4[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s5[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s6[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s7[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s8[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s9[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s10[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s11[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s12[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s13[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s14[i - 1], ultimaSaida);
                    ultimaSaida = testaValor(cp.s15[i - 1], ultimaSaida);
                }
                horasUltimaSaida = Integer.parseInt(ultimaSaida.split(":")[0]);
                minutosUltimaSaida = Integer.parseInt(ultimaSaida.split(":")[1]);
                if (horasUltimaSaida < 24) {
                    if (i - 1 >= 0 && horasUltimaSaida > 0) {
                        if (cp.folgas[i].equals("1")) {
                            horasArt66 = 35 - (Integer.parseInt(cp.e1[i].split(":")[0]));
                        } else {
                            horasArt66 = 11 - ((24 - horasUltimaSaida) + Integer.parseInt(cp.e1[i].split(":")[0]));
                        }
                    } else if (i == 0) {
                        horasArt66 = 0;
                        minutosArt66 = 0;
                    }
                    minutosArt66 -= minutosUltimaSaida;
                }
                if (minutosArt66 < 0) {
                    horasArt66 -= 1;
                    minutosArt66 += 60;
                    if (horasArt66 <= 0) {
                        horasArt66 = 0;
                        minutosArt66 = 0;
                    }
                }
                if (horasArt66 < 0) {
                    horasArt66 = 0;
                }
                totalHorasArt66 += horasArt66;
                totalMinutosArt66 += minutosArt66;
                table.addCell(novaCelula(new Phrase(verificaExibicao(horasArt66, minutosArt66), font), fds));
            }
            if (horasExtrasDD.equals("1")) {
                horasEDD = Integer.parseInt(valorExtrasDD.split(":")[0]);
                minutosEDD = Integer.parseInt(valorExtrasDD.split(":")[1]);
                horasNormaisDD = Integer.parseInt(converteTotal(cp.totaldiario[i]).split(":")[0]) - Integer.parseInt(cp.excDiario[i].split(":")[0]);
                minutosNormaisDD = Integer.parseInt(converteTotal(cp.totaldiario[i]).split(":")[1]) - Integer.parseInt(cp.excDiario[i].split(":")[1]);
                if (horasNormaisDD > 0) {
                    if (horasEDD >= horasNormaisDD) {
                        horasEDD = horasNormaisDD;
                        minutosEDD = minutosNormaisDD;
                    } else {
                        horasNormaisDD -= horasEDD;
                        minutosNormaisDD -= minutosEDD;
                    }
                    totalHorasNormaisED += horasNormaisDD;
                    totalMinutosNormaisED += minutosNormaisDD;
                    totalHorasDuplasED += horasEDD;
                    totalMinutosDuplasED += minutosEDD;
                } else {
                    horasEDD = 0;
                    minutosEDD = 0;
                    horasNormaisDD = 0;
                    minutosNormaisDD = 0;
                }
                table.addCell(novaCelula(new Phrase(ajustaHora(horasEDD, minutosEDD), font), fds));
                table.addCell(novaCelula(new Phrase(ajustaHora(horasNormaisDD, minutosNormaisDD), font), fds));
                horasNormaisDD = 0;
                minutosNormaisDD = 0;
            }
            if (horasExtrasDS.equals("1")) {
                if (diaSemana == 0 || i == (cp.dias - 1)) {
                    horasEDS = Integer.parseInt(valorExtrasDD.split(":")[0]);
                    minutosEDS = Integer.parseInt(valorExtrasDD.split(":")[1]);
                    horasNormaisDS = Integer.parseInt(converteTotal(cp.totalsemanal[contSem]).split(":")[0]) - Integer.parseInt(cp.excSem[contSem].split(":")[0]);
                    minutosNormaisDS = Integer.parseInt(converteTotal(cp.totalsemanal[contSem]).split(":")[1]) - Integer.parseInt(cp.excSem[contSem].split(":")[1]);
                    if (horasEDS >= horasNormaisDS) {
                        horasEDS = horasNormaisDS;
                        minutosEDS = minutosNormaisDS;
                        horasNormaisDS = 0;
                        minutosNormaisDS = 0;
                    } else {
                        horasNormaisDS -= horasEDS;
                        minutosNormaisDS -= minutosEDS;
                    }
                    totalHorasNormaisES += horasNormaisDS;
                    totalMinutosNormaisES += minutosNormaisDS;
                    totalHorasDuplasES += horasEDS;
                    totalMinutosDuplasES += minutosEDS;
                    table.addCell(novaCelula(new Phrase(ajustaHora(horasEDS, minutosEDS), font), fds));
                    table.addCell(novaCelula(new Phrase(ajustaHora(horasNormaisDS, minutosNormaisDS), font), fds));
                } else {
                    table.addCell(novaCelula(new Phrase("", font), fds));
                    table.addCell(novaCelula(new Phrase("", font), fds));
                }
            }
            if (horasExtrasDM.equals("1")) {
                if (diaMes == 1 || i == (cp.dias - 1)) {
                    horasEDM = Integer.parseInt(valorExtrasDM.split(":")[0]);
                    minutosEDM = Integer.parseInt(valorExtrasDM.split(":")[1]);
                    horasNormaisDM = Integer.parseInt(converteTotal(cp.totalmensal[contM]).split(":")[0]) - Integer.parseInt(cp.excMes[contM].split(":")[0]);
                    minutosNormaisDM = Integer.parseInt(converteTotal(cp.totalmensal[contM]).split(":")[1]) - Integer.parseInt(cp.excMes[contM].split(":")[1]);
                    if (horasEDM >= horasNormaisDM) {
                        horasEDM = horasNormaisDM;
                        minutosEDM = minutosNormaisDM;
                        horasNormaisDM = 0;
                        minutosNormaisDM = 0;
                    } else {
                        horasNormaisDM -= horasEDM;
                        minutosNormaisDM -= minutosEDM;
                    }
                    table.addCell(novaCelula(new Phrase(ajustaHora(horasEDM, minutosEDM), font), fds));
                    table.addCell(novaCelula(new Phrase(ajustaHora(horasNormaisDM, minutosNormaisDM), font), fds));
                } else {
                    table.addCell(novaCelula(new Phrase("", font), fds));
                    table.addCell(novaCelula(new Phrase("", font), fds));
                }
                totalHorasNormaisEM += horasNormaisDM;
                totalMinutosNormaisEM += minutosNormaisDM;
                totalHorasEDM += horasEDM;
                totalMinutosEDM += minutosEDM;
            }
            if (horasExtrasTD.equals("1")) {
                horasETD1 = Integer.parseInt(valorExtrasTD1.split(":")[0]);
                minutosETD1 = Integer.parseInt(valorExtrasTD1.split(":")[1]);
                horasETD2 = Integer.parseInt(valorExtrasTD2.split(":")[0]);
                minutosETD2 = Integer.parseInt(valorExtrasTD2.split(":")[1]);
                horasNormaisTD = Integer.parseInt(converteTotal(cp.totaldiario[i]).split(":")[0]) - Integer.parseInt(cp.excDiario[i].split(":")[0]);
                minutosNormaisTD = Integer.parseInt(converteTotal(cp.totaldiario[i]).split(":")[1]) - Integer.parseInt(cp.excDiario[i].split(":")[1]);
                if (horasNormaisTD >= 0) {
                    if (horasNormaisTD < horasETD1) {
                        horasETD1 = horasNormaisTD;
                        minutosETD1 = minutosNormaisTD;
                    } else if (horasNormaisTD > horasETD1 && horasNormaisTD < horasETD2) {
                        horasETD2 = horasNormaisTD - horasETD1;
                        minutosETD2 = minutosNormaisTD - minutosETD1;
                    } else {
                        horasNormaisTD -= horasETD1 + horasETD2;
                        minutosNormaisTD -= minutosETD1 + minutosETD2;
                    }
                } else {
                    horasETD1 = 0;
                    minutosETD1 = 0;
                    horasETD2 = 0;
                    minutosETD2 = 0;
                    horasNormaisTD = 0;
                    minutosNormaisTD = 0;
                }
                table.addCell(novaCelula(new Phrase(ajustaHora(horasETD1, minutosETD1), font), fds));
                table.addCell(novaCelula(new Phrase(ajustaHora(horasETD2, minutosETD2), font), fds));
                table.addCell(novaCelula(new Phrase(ajustaHora(horasNormaisTD, minutosNormaisTD), font), fds));
                horasNormaisTD = 0;
                minutosNormaisTD = 0;
                totalHorasNormaisTD += horasNormaisTD;
                totalMinutosNormaisTD += minutosNormaisTD;
                totalHorasETD1 += horasETD1;
                totalMinutosETD1 += minutosETD1;
                totalHorasETD2 += horasETD2;
                totalMinutosETD2 += minutosETD2;
            }
            if (horasExtrasTS.equals("1")) {
                horasETS1 = Integer.parseInt(valorExtrasTS1.split(":")[0]);
                minutosETS1 = Integer.parseInt(valorExtrasTS1.split(":")[1]);
                horasETS2 = Integer.parseInt(valorExtrasTS2.split(":")[0]);
                minutosETS2 = Integer.parseInt(valorExtrasTS2.split(":")[1]);
                horasNormaisTS = Integer.parseInt(converteTotal(cp.totalsemanal[contSem]).split(":")[0]) - Integer.parseInt(cp.excSem[contSem].split(":")[0]);
                minutosNormaisTS = Integer.parseInt(converteTotal(cp.totalsemanal[contSem]).split(":")[1]) - Integer.parseInt(cp.excSem[contSem].split(":")[1]);
                if (horasNormaisTS >= 0) {
                    if (horasNormaisTS < horasETS1) {
                        horasETS1 = horasNormaisTS;
                        minutosETS1 = minutosNormaisTS;
                    } else if (horasNormaisTS > horasETS1 && horasNormaisTS < horasETS2) {
                        horasETS2 = horasNormaisTS - horasETS1;
                        minutosETS2 = minutosNormaisTS - minutosETS1;
                    } else {
                        horasNormaisTS -= horasETS1 + horasETS2;
                        minutosNormaisTS -= minutosETS1 + minutosETS2;
                    }
                } else {
                    horasETS1 = 0;
                    minutosETS1 = 0;
                    horasETS2 = 0;
                    minutosETS2 = 0;
                    horasNormaisTS = 0;
                    minutosNormaisTS = 0;
                }
                table.addCell(novaCelula(new Phrase(ajustaHora(horasETS1, minutosETS1), font), fds));
                table.addCell(novaCelula(new Phrase(ajustaHora(horasETS2, minutosETS2), font), fds));
                table.addCell(novaCelula(new Phrase(ajustaHora(horasNormaisTS, minutosNormaisTS), font), fds));
                horasNormaisTS = 0;
                minutosNormaisTS = 0;
                horasNormaisTS = 0;
                minutosNormaisTS = 0;
                totalHorasNormaisTS += horasNormaisTS;
                totalMinutosNormaisTD += minutosNormaisTS;
                totalHorasETS1 += horasETS1;
                totalMinutosETS1 += minutosETS1;
                totalHorasETS2 += horasETS2;
                totalMinutosETS2 += minutosETS2;
            }
            if (horasExtrasTM.equals("1")) {
                horasETM1 = Integer.parseInt(valorExtrasTM1.split(":")[0]);
                minutosETM1 = Integer.parseInt(valorExtrasTM1.split(":")[1]);
                horasETM2 = Integer.parseInt(valorExtrasTM2.split(":")[0]);
                minutosETM2 = Integer.parseInt(valorExtrasTM2.split(":")[1]);
                horasNormaisTM = Integer.parseInt(converteTotal(cp.totalmensal[contM]).split(":")[0]) - Integer.parseInt(cp.excMes[contM].split(":")[0]);
                minutosNormaisTM = Integer.parseInt(converteTotal(cp.totalmensal[contM]).split(":")[1]) - Integer.parseInt(cp.excMes[contM].split(":")[1]);
                if (horasNormaisTM >= 0) {
                    if (horasNormaisTM < horasETM1) {
                        horasETM1 = horasNormaisTM;
                        minutosETM1 = minutosNormaisTM;
                    } else if (horasNormaisTM > horasETM1 && horasNormaisTM < horasETM2) {
                        horasETM2 = horasNormaisTM - horasETM1;
                        minutosETM2 = minutosNormaisTM - minutosETM1;
                    } else {
                        horasNormaisTM -= horasETM1 + horasETM2;
                        minutosNormaisTM -= minutosETM1 + minutosETM2;
                    }
                } else {
                    horasETM1 = 0;
                    minutosETM1 = 0;
                    horasETM2 = 0;
                    minutosETM2 = 0;
                    horasNormaisTM = 0;
                    minutosNormaisTM = 0;
                }
                table.addCell(novaCelula(new Phrase(ajustaHora(horasETM1, minutosETM1), font), fds));
                table.addCell(novaCelula(new Phrase(ajustaHora(horasETM2, minutosETM2), font), fds));
                table.addCell(novaCelula(new Phrase(ajustaHora(horasNormaisTS, minutosNormaisTS), font), fds));
                horasNormaisTM = 0;
                minutosNormaisTM = 0;
                totalHorasNormaisTM += horasNormaisTM;
                totalMinutosNormaisTM += minutosNormaisTM;
                totalHorasETM1 += horasETM1;
                totalMinutosETM1 += minutosETM1;
                totalHorasETM2 += horasETM2;
                totalMinutosETM2 += minutosETM2;
            }
            if (rsr.equals("1")) {
                String total = "";
                if (cp.folgas[i].equals("S") && Double.parseDouble(cp.totaldiario[i]) > 0) {
                    total = "" + novaCelula(new Phrase(converteTotal((totalDiario / 60) + ""), font), fds);
                    table.addCell(novaCelula(new Phrase(converteTotal((totalDiario / 60) + ""), font), fds));
                } else {
                    total = "00:00";
                    table.addCell(novaCelula(new Phrase("00:00", font), fds));
                }
                totalHorasRsr += Double.parseDouble(total.split(":")[0]);
                totalMinutosRsr += Double.parseDouble(total.split(":")[1]);
            }
            if (minutosResiduais.equals("1")) {
                String totalMR = converteTotal("" + (minutosResiduais(jornadaIdealEntrada, jornadaIdealSaida, cp.e1[i], ultimaSaida(cp, i))) / 60);
                table.addCell(novaCelula(new Phrase(totalMR, font), fds));
                totalHorasMR += Integer.parseInt(totalMR.split(":")[0]);
                totalMinutosMR += Integer.parseInt(totalMR.split(":")[1]);
            }

            c.add(Calendar.DATE, 1);
            day = c.get(Calendar.DATE);//incrementa o dia do mes
            fds = false;
            if (diaSemana == 0 || i == (cp.dias - 1)) {
                fds = true;
                contSem++;
            }
            diaSemana++;//incrementa o dia da semana até o sabado (6) e reseta para domingo (0)
            if (diaSemana > 6) {
                diaSemana = 0;
            }
            if (day == diaMes) { //Se for o ultimo dia do mês, irá criar outra página
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
                table.addCell(novaCelula(new Phrase("Total do mês em horas:", font), true));
                table.addCell(emptyCell());
                for (int x = 0; x < colunas; x++) {
                    table.addCell(emptyCell(true));
                    table.addCell(emptyCell(true));
                }
                table.addCell(novaCelula(new Phrase(converteTotal(cp.totalmensal[contM]), font), true));
                if (art71.equals("1")) {
                    if (calc.equals("integral")) {
                        table.addCell(novaCelula(new Phrase(verificaExibicao(horasArt71, minutosArt71), font), true));
                    } else if (calc.equals("diferenca")) {
                        table.addCell(novaCelula(new Phrase(verificaExibicao(horasArt71, minutosArt71), font), true));
                    }
                }
                if (excSem.equals("1")) {
                    if (diaSemana == 0 || i == (cp.dias - 1)) {
                        if (totalHorasExcSem > 0 && totalMinutosExcSem > 0) {
                            table.addCell(novaCelula(new Phrase(verificaExibicao(totalHorasExcSem, totalMinutosExcSem), font), true));
                        }
                    } else {
                        table.addCell(novaCelula(new Phrase("00:00", font), true));
                    }
                }
                if (excDiario.equals("1")) {
                    horasExcDiario += Double.parseDouble(converteTotal((totalExcDiario / 60) + "").split(":")[0]);
                    minutosExcDiario += Double.parseDouble(converteTotal((totalExcDiario / 60) + "").split(":")[1]);
                    if (minutosExcDiario >= 60) {
                        minutosExcDiario -= 60;
                        horasExcDiario += 1;
                    }
                    table.addCell(novaCelula(new Phrase("" + verificaExibicao(horasExcDiario, minutosExcDiario), font), true));
                }
                if (faltas.equals("1")) {
                    if (totalFaltas < 0) {
                        totalFaltas = 0;
                    }
                    horasFaltas += Double.parseDouble(converteTotal((totalFaltas / 60) + "").split(":")[0]);
                    minutosFaltas += Double.parseDouble(converteTotal((totalFaltas / 60) + "").split(":")[1]);
                    if (minutosFaltas >= 60) {
                        minutosFaltas -= 60;
                        horasFaltas += 1;
                    }
                    table.addCell(novaCelula(new Phrase(verificaExibicao(horasFaltas, minutosFaltas), font), true));
                }
                if (adcNoturno.equals("1")) {
                    if (totalAdcNoturno < 0) {
                        totalAdcNoturno = 0;
                    }
                    horasAdcNoturno += Double.parseDouble(converteTotal(totalAdcNoturno + "").split(":")[0]);
                    minutosAdcNoturno += Double.parseDouble(converteTotal(totalAdcNoturno + "").split(":")[1]);
                    if (minutosAdcNoturno >= 60) {
                        minutosAdcNoturno -= 60;
                        horasAdcNoturno += 1;
                    }
                    table.addCell(novaCelula(new Phrase(verificaExibicao(horasAdcNoturno, minutosAdcNoturno), font), true));
                }
                if (art66.equals("1")) {
                    totalHorasArt66 += horasArt66;
                    totalMinutosArt66 += minutosArt66;
                    if (totalMinutosArt66 >= 60) {
                        totalMinutosArt66 -= 60;
                        totalHorasArt66 += 1;
                    }
                    table.addCell(novaCelula(new Phrase(verificaExibicao(totalHorasArt66, totalMinutosArt66), font), true));
                }
                if (art384.equals("1")) {
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasArt384, totalMinutosArt384), font), true));
                }
                if (horasExtrasDD.equals("1")) {
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasNormaisED, totalMinutosNormaisED), font), true));
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasDuplasED, totalMinutosDuplasED), font), true));
                }
                if (horasExtrasDS.equals("1")) {
                    if (diaSemana == 6 || i == (cp.dias - 1)) {
                        horasEDS = Integer.parseInt(valorExtrasDD.split(":")[0]);
                        minutosEDS = Integer.parseInt(valorExtrasDD.split(":")[1]);
                        horasNormaisDS = Integer.parseInt(converteTotal(cp.totalsemanal[contSem]).split(":")[0]) - Integer.parseInt(cp.excSem[contSem].split(":")[0]);
                        minutosNormaisDS = Integer.parseInt(converteTotal(cp.totalsemanal[contSem]).split(":")[1]) - Integer.parseInt(cp.excSem[contSem].split(":")[1]);
                        if (horasEDS >= horasNormaisDS) {
                            horasEDS = horasNormaisDS;
                            minutosEDS = minutosNormaisDS;
                            horasNormaisDS = 0;
                            minutosNormaisDS = 0;
                        } else {
                            horasNormaisDS -= horasEDS;
                            minutosNormaisDS -= minutosEDS;
                        }
                        table.addCell(novaCelula(new Phrase(ajustaHora(horasEDS, minutosEDS), font), true));
                        table.addCell(novaCelula(new Phrase(ajustaHora(horasNormaisDS, minutosNormaisDS), font), true));
                    } else {
                        table.addCell(novaCelula(new Phrase("00:00", font), true));
                        table.addCell(novaCelula(new Phrase("00:00", font), true));
                    }
                }
                if (horasExtrasDM.equals("1")) {
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasNormaisEM, totalMinutosNormaisEM), font), true));
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasDuplasEM, totalMinutosDuplasEM), font), true));
                }
                if (horasExtrasTD.equals("1")) {
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasETD1, totalMinutosETD1), font), true));
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasETD2, totalMinutosETD2), font), true));
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasNormaisTD, totalMinutosNormaisTD), font), true));
                }
                if (horasExtrasTS.equals("1")) {
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasETS1, totalMinutosETS1), font), true));
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasETS2, totalMinutosETS2), font), true));
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasNormaisTS, totalMinutosNormaisTS), font), true));
                }
                if (horasExtrasTM.equals("1")) {
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasETM1, totalMinutosETM1), font), true));
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasETM2, totalMinutosETM2), font), true));
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasNormaisTM, totalMinutosNormaisTM), font), true));
                }
                if (rsr.equals("1")) {
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasRsr, totalMinutosRsr), font), true));
                }
                if (minutosResiduais.equals("1")) {
                    table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasMR, totalMinutosMR), font), true));
                }

                table.addCell(novaCelula(new Phrase("Total do mês em decimais", font), false));
                table.addCell(emptyCell());
                for (int x = 0; x < colunas; x++) {
                    table.addCell(emptyCell());
                    table.addCell(emptyCell());
                }
                table.addCell(novaCelula(new Phrase(encurtaDouble(cp.totalmensal[contM]), font), false));
                if (art71.equals("1")) {
                    if (calc.equals("integral")) {
                        table.addCell(novaCelula(new Phrase("" + (((horasArt71 * 60) + minutosArt71) / 60), font), false));
                    } else if (calc.equals("diferenca")) {
                        table.addCell(novaCelula(new Phrase(encurtaDouble(cp.totalmensal[contM]), font), false));
                    }
                }
                if (excSem.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(totalHorasExcSem, totalMinutosExcSem), cp), font), false));
                }
                if (excDiario.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(horasExcDiario, minutosExcDiario), cp), font), false));
                }
                if (faltas.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(horasFaltas, minutosFaltas), cp), font), false));
                }
                if (adcNoturno.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(horasAdcNoturno, minutosAdcNoturno), cp), font), false));
                }
                if (art66.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(totalHorasArt66, totalMinutosArt66), cp), font), false));
                }
                if (art384.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(totalHorasArt384, totalMinutosArt384), cp), font), false));
                }
                if (horasExtrasDD.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasNormaisED, totalMinutosNormaisED), cp), font), false));
                    table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasDuplasED, totalMinutosDuplasED), cp), font), false));
                }
                if (horasExtrasDS.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasNormaisES, totalMinutosNormaisES), cp), font), false));
                    table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasDuplasES, totalMinutosDuplasES), cp), font), false));
                }
                if (horasExtrasDM.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasNormaisEM, totalMinutosNormaisEM), cp), font), false));
                    table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasDuplasEM, totalMinutosDuplasEM), cp), font), false));
                }
                if (rsr.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasRsr, totalMinutosRsr), cp), font), true));
                }
                if (minutosResiduais.equals("1")) {
                    table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasMR, totalMinutosMR), cp), font), true));
                }

                totalHorasNormaisTM = 0;
                totalMinutosNormaisTM = 0;
                totalHorasETM1 = 0;
                totalMinutosETM1 = 0;
                totalHorasETM2 = 0;
                totalMinutosETM2 = 0;
                totalHorasNormaisTS = 0;
                totalMinutosNormaisTD = 0;
                totalHorasETS1 = 0;
                totalMinutosETS1 = 0;
                totalHorasETS2 = 0;
                totalMinutosETS2 = 0;
                totalHorasNormaisTD = 0;
                totalMinutosNormaisTD = 0;
                totalHorasETD1 = 0;
                totalMinutosETD1 = 0;
                totalHorasETD2 = 0;
                totalMinutosETD2 = 0;
                totalHorasNormaisEM = 0;
                totalMinutosNormaisEM = 0;
                totalHorasEDM = 0;
                totalMinutosEDM = 0;
                totalHorasMR = 0;
                totalMinutosMR = 0;
                totalHorasRsr = 0;
                totalMinutosRsr = 0;
                totalHorasNormaisES = 0;
                totalMinutosNormaisES = 0;
                totalHorasDuplasES = 0;
                totalMinutosDuplasES = 0;
                totalHorasNormaisED = 0;
                totalMinutosNormaisED = 0;
                totalHorasDuplasED = 0;
                totalMinutosDuplasED = 0;
                horasArt66 = 0;
                minutosArt66 = 0;
                horasAdcNoturno = 0;
                minutosAdcNoturno = 0;
                totalAdcNoturno = 0;
                horasFaltas = 0;
                minutosFaltas = 0;
                totalFaltas = 0;
                horasExcDiario = 0;
                minutosExcDiario = 0;
                contM++;
                //final da linha
                document.add(table);
                document.add(new Paragraph("Dias trabalhados neste mês: " + diasTrabalhados));
                diasTrabalhados = 0;
                acabou = (i == cp.dias);
                if (!acabou) {//se o ultimo dia do mês for o ultimo dia do documento, não há motivo para criar outra pagina com o cabeçalho de uma nova tabela vazia
                    horasArt71 = 0;
                    minutosArt71 = 0;
                    document.newPage();
                    table = new PdfPTable(qtColunas.length);
                    table.setWidthPercentage(110);
                    table.setWidths(qtColunas);
                    table = cabecalhoTabela(table, colunas, font, art71, art66, art384, calc, excDiario, excSem, faltas, adcNoturno, horasExtrasDD,
                            horasExtrasDS, horasExtrasDM, horasExtrasTD, horasExtrasTS, horasExtrasTM, rsr, minutosResiduais);

                    document.add(new Paragraph("Cartão de Ponto"));
                    document.add(new Paragraph("Data de Inclusão: " + cp.dtinclusao + "     Processo: " + cp.processo + "     Junta: " + cp.junta));
                    document.add(new Paragraph("Reclamante: " + cp.reclamante + "                       Reclamada: " + cp.reclamada));
                    document.add(new Paragraph("Data Inicial: " + cp.dtinicial + "              Data Final: " + cp.dtfinal));
                    if (month >= 10) {
                        document.add(new Paragraph("Mês: " + month + "/" + year));
                    } else {
                        document.add(new Paragraph("Mês: 0" + month + "/" + year));
                    }
                    document.add(new Paragraph(" "));
                }
            }
        }
        //linhas inseridas ao final da ULTIMA pagina
        table.addCell(novaCelula(new Phrase("Total do mês em horas:", font), true));//se o documento acabar no ultimo dia de um mês, os campos abaixo não precisam ser criados de novo
        table.addCell(emptyCell());
        for (int x = 0; x < colunas; x++) {
            table.addCell(emptyCell(true));
            table.addCell(emptyCell(true));
        }
        table.addCell(novaCelula(new Phrase("" + converteTotal(cp.totalmensal[contM]), font), true));
        if (art71.equals("1")) {
            if (calc.equals("integral")) {
                table.addCell(novaCelula(new Phrase(verificaExibicao((int) horasArt71, (int) minutosArt71), font), true));
            } else if (calc.equals("diferenca")) {
                table.addCell(novaCelula(new Phrase(verificaExibicao(hours71, minutes71), font), true));
            }
        }
        if (excSem.equals("1")) {
            if (totalHorasExcSem > 0 && totalMinutosExcSem > 0) {
                table.addCell(novaCelula(new Phrase(verificaExibicao(totalHorasExcSem, totalMinutosExcSem), font), true));
            } else {
                table.addCell(novaCelula(new Phrase("00:00", font), true));
            }
        }
        if (excDiario.equals("1")) {
            if (totalExcDiario < 0) {
                totalExcDiario = 0;
            }
            horasExcDiario += Double.parseDouble(converteTotal((totalExcDiario / 60) + "").split(":")[0]);
            minutosExcDiario += Double.parseDouble(converteTotal((totalExcDiario / 60) + "").split(":")[1]);
            if (minutosExcDiario >= 60) {
                minutosExcDiario -= 60;
                horasExcDiario += 1;
            }
            table.addCell(novaCelula(new Phrase(verificaExibicao(horasExcDiario, minutosExcDiario), font), true));
        }
        if (faltas.equals("1")) {
            if (totalFaltas < 0) {
                totalFaltas = 0;
            }
            horasFaltas += Double.parseDouble(converteTotal((totalFaltas / 60) + "").split(":")[0]);
            minutosFaltas += Double.parseDouble(converteTotal((totalFaltas / 60) + "").split(":")[1]);
            if (minutosFaltas >= 60) {
                minutosFaltas -= 60;
                horasFaltas += 1;
            }
            table.addCell(novaCelula(new Phrase(verificaExibicao(horasFaltas, minutosFaltas), font), true));
        }
        if (adcNoturno.equals("1")) {
            if (totalAdcNoturno < 0) {
                totalAdcNoturno = 0;
            }
            horasAdcNoturno += Double.parseDouble(converteTotal(totalAdcNoturno + "").split(":")[0]);
            minutosAdcNoturno += Double.parseDouble(converteTotal(totalAdcNoturno + "").split(":")[1]);
            if (minutosAdcNoturno >= 60) {
                minutosAdcNoturno -= 60;
                horasAdcNoturno += 1;
            }
            table.addCell(novaCelula(new Phrase(verificaExibicao(horasAdcNoturno, minutosAdcNoturno), font), true));
        }
        if (art66.equals("1")) {
            if (totalMinutosArt66 >= 60) {
                totalMinutosArt66 -= 60;
                totalHorasArt66 += 1;
            }
            table.addCell(novaCelula(new Phrase(verificaExibicao(totalHorasArt66, totalMinutosArt66), font), true));
        }
        if (art384.equals("1")) {
            table.addCell(novaCelula(new Phrase(verificaExibicao(totalHorasArt384, totalMinutosArt384), font), true));
        }
        if (horasExtrasDD.equals("1")) {
            table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasNormaisED, totalMinutosNormaisED), font), true));
            table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasDuplasED, totalMinutosDuplasED), font), true));
        }
        if (horasExtrasDS.equals("1")) {
            table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasNormaisES, totalMinutosNormaisES), cp), font), false));
            table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasDuplasES, totalMinutosDuplasES), cp), font), false));
        }
        if (horasExtrasDM.equals("1")) {
            table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasNormaisEM, totalMinutosNormaisEM), cp), font), false));
            table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasDuplasEM, totalMinutosDuplasEM), cp), font), false));
        }
        if (rsr.equals("1")) {
            table.addCell(novaCelula(new Phrase(ajustaHora(totalHorasRsr, totalMinutosRsr), font), true));
        }
        if (minutosResiduais.equals("1")) {
            table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasMR, totalMinutosMR), cp), font), true));
        }

        if (day != diaMes) {

            table.addCell(novaCelula(new Phrase("Total do mês em decimais:", font), false));
            table.addCell(emptyCell());
            for (int x = 0; x < colunas; x++) {
                table.addCell(emptyCell());
                table.addCell(emptyCell());
            }
            table.addCell(novaCelula(new Phrase(encurtaDouble(cp.totalmensal[contM]), font), false));
            if (art71.equals("1")) {
                if (calc.equals("integral")) {
                    table.addCell(novaCelula(new Phrase("" + (((horasArt71 * 60) + minutosArt71) / 60), font), false));
                } else if (calc.equals("diferenca")) {
                    table.addCell(novaCelula(new Phrase(encurtaDouble(cp.totalmensal[contM]), font), false));
                }
            }
            if (excSem.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(totalHorasExcSem, totalMinutosExcSem), cp), font), false));
            }
            if (excDiario.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(horasExcDiario, minutosExcDiario), cp), font), false));
            }
            if (faltas.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(horasFaltas, minutosFaltas), cp), font), false));
            }
            if (adcNoturno.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(horasAdcNoturno, minutosAdcNoturno), cp), font), false));
            }
            if (art66.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(totalHorasArt66, totalMinutosArt66), cp), font), false));
            }
            if (art384.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(verificaExibicao(totalHorasArt384, totalMinutosArt384), cp), font), false));
            }
            if (horasExtrasDD.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasNormaisED, totalMinutosNormaisED), cp), font), false));
                table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasDuplasED, totalMinutosDuplasED), cp), font), false));
            }
            if (horasExtrasDS.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasNormaisES, totalMinutosNormaisES), cp), font), false));
                table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasDuplasES, totalMinutosDuplasES), cp), font), false));
            }
            if (horasExtrasDM.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasNormaisEM, totalMinutosNormaisEM), cp), font), false));
                table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasDuplasEM, totalMinutosDuplasEM), cp), font), false));
            }
            if (rsr.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasRsr, totalMinutosRsr), cp), font), true));
            }
            if (minutosResiduais.equals("1")) {
                table.addCell(novaCelula(new Phrase(converteHora(ajustaHora(totalHorasMR, totalMinutosMR), cp), font), true));
            }
            document.add(table);
        }
        document.add(new Paragraph("Dias trabalhados neste mês: " + diasTrabalhados));
        // step 5
        document.close();
    }

    public static final String RESULT = "C:/pdfTeste.pdf";

    public static void main(String[] args)
            throws DocumentException, IOException {
    }

    private String[] calcIntervalo(cartaoDePonto cp, String horasTrabalhadas, String intervaloAcima, String intervaloAbaixo) {
        int dias = (int) cp.dias;
        int horas = 0, minutos = 0;
        int maior = 0;
        int[] vetHorasSaida = new int[14];
        int[] vetMinutosSaida = new int[14];
        int[] vetHorasEntrada = new int[14];
        int[] vetMinutosEntrada = new int[14];
        hours71 = 0;
        minutes71 = 0;
        String[] intervalo = new String[dias];

        for (int i = 0; i < cp.dias; i++) {
            horas = 0;
            minutos = 0;
            maior = 0;
            //armazenamento dos valores de hora e minuto de entrada no vetor correspondente
            vetHorasEntrada[0] = Integer.parseInt(cp.e2[i].split(":")[0]);
            vetMinutosEntrada[0] = Integer.parseInt(cp.e2[i].split(":")[1]);
            vetHorasEntrada[1] = Integer.parseInt(cp.e3[i].split(":")[0]);
            vetMinutosEntrada[1] = Integer.parseInt(cp.e3[i].split(":")[1]);
            vetHorasEntrada[2] = Integer.parseInt(cp.e4[i].split(":")[0]);
            vetMinutosEntrada[2] = Integer.parseInt(cp.e4[i].split(":")[1]);
            vetHorasEntrada[3] = Integer.parseInt(cp.e5[i].split(":")[0]);
            vetMinutosEntrada[3] = Integer.parseInt(cp.e5[i].split(":")[1]);
            vetHorasEntrada[4] = Integer.parseInt(cp.e6[i].split(":")[0]);
            vetMinutosEntrada[4] = Integer.parseInt(cp.e6[i].split(":")[1]);
            vetHorasEntrada[5] = Integer.parseInt(cp.e7[i].split(":")[0]);
            vetMinutosEntrada[5] = Integer.parseInt(cp.e7[i].split(":")[1]);
            vetHorasEntrada[6] = Integer.parseInt(cp.e8[i].split(":")[0]);
            vetMinutosEntrada[6] = Integer.parseInt(cp.e8[i].split(":")[1]);
            vetHorasEntrada[7] = Integer.parseInt(cp.e9[i].split(":")[0]);
            vetMinutosEntrada[7] = Integer.parseInt(cp.e9[i].split(":")[1]);
            vetHorasEntrada[8] = Integer.parseInt(cp.e10[i].split(":")[0]);
            vetMinutosEntrada[8] = Integer.parseInt(cp.e10[i].split(":")[1]);
            vetHorasEntrada[9] = Integer.parseInt(cp.e11[i].split(":")[0]);
            vetMinutosEntrada[9] = Integer.parseInt(cp.e11[i].split(":")[1]);
            vetHorasEntrada[10] = Integer.parseInt(cp.e12[i].split(":")[0]);
            vetMinutosEntrada[10] = Integer.parseInt(cp.e12[i].split(":")[1]);
            vetHorasEntrada[11] = Integer.parseInt(cp.e13[i].split(":")[0]);
            vetMinutosEntrada[11] = Integer.parseInt(cp.e13[i].split(":")[1]);
            vetHorasEntrada[12] = Integer.parseInt(cp.e14[i].split(":")[0]);
            vetMinutosEntrada[12] = Integer.parseInt(cp.e14[i].split(":")[1]);
            vetHorasEntrada[13] = Integer.parseInt(cp.e15[i].split(":")[0]);
            vetMinutosEntrada[13] = Integer.parseInt(cp.e15[i].split(":")[1]);

            //armazenamento dos valores de hora e minuto de saida no vetor correspondente
            vetHorasSaida[0] = Integer.parseInt(cp.s1[i].split(":")[0]);
            vetMinutosSaida[0] = Integer.parseInt(cp.s1[i].split(":")[1]);
            vetHorasSaida[1] = Integer.parseInt(cp.s2[i].split(":")[0]);
            vetMinutosSaida[1] = Integer.parseInt(cp.s2[i].split(":")[1]);
            vetHorasSaida[2] = Integer.parseInt(cp.s3[i].split(":")[0]);
            vetMinutosSaida[2] = Integer.parseInt(cp.s3[i].split(":")[1]);
            vetHorasSaida[3] = Integer.parseInt(cp.s4[i].split(":")[0]);
            vetMinutosSaida[3] = Integer.parseInt(cp.s4[i].split(":")[1]);
            vetHorasSaida[4] = Integer.parseInt(cp.s5[i].split(":")[0]);
            vetMinutosSaida[4] = Integer.parseInt(cp.s5[i].split(":")[1]);
            vetHorasSaida[5] = Integer.parseInt(cp.s6[i].split(":")[0]);
            vetMinutosSaida[5] = Integer.parseInt(cp.s6[i].split(":")[1]);
            vetHorasSaida[6] = Integer.parseInt(cp.s7[i].split(":")[0]);
            vetMinutosSaida[6] = Integer.parseInt(cp.s7[i].split(":")[1]);
            vetHorasSaida[7] = Integer.parseInt(cp.s8[i].split(":")[0]);
            vetMinutosSaida[7] = Integer.parseInt(cp.s8[i].split(":")[1]);
            vetHorasSaida[8] = Integer.parseInt(cp.s9[i].split(":")[0]);
            vetMinutosSaida[8] = Integer.parseInt(cp.s9[i].split(":")[1]);
            vetHorasSaida[9] = Integer.parseInt(cp.s10[i].split(":")[0]);
            vetMinutosSaida[9] = Integer.parseInt(cp.s10[i].split(":")[1]);
            vetHorasSaida[10] = Integer.parseInt(cp.s11[i].split(":")[0]);
            vetMinutosSaida[10] = Integer.parseInt(cp.s11[i].split(":")[1]);
            vetHorasSaida[11] = Integer.parseInt(cp.s12[i].split(":")[0]);
            vetMinutosSaida[11] = Integer.parseInt(cp.s12[i].split(":")[1]);
            vetHorasSaida[12] = Integer.parseInt(cp.s13[i].split(":")[0]);
            vetMinutosSaida[12] = Integer.parseInt(cp.s13[i].split(":")[1]);
            vetHorasSaida[13] = Integer.parseInt(cp.s14[i].split(":")[0]);
            vetMinutosSaida[13] = Integer.parseInt(cp.s14[i].split(":")[1]);

            maior = calculaMaior(maior, vetHorasEntrada[0], vetHorasSaida[0], vetMinutosEntrada[0], vetMinutosSaida[0]);
            if (vetHorasEntrada[1] > 0 || vetMinutosEntrada[1] > 0) {
                maior = calculaMaior(maior, vetHorasEntrada[1], vetHorasSaida[1], vetMinutosEntrada[1], vetMinutosSaida[1]);
            }
            if (vetHorasEntrada[2] > 0 || vetMinutosEntrada[1] > 0) {
                maior = calculaMaior(maior, vetHorasEntrada[2], vetHorasSaida[2], vetMinutosEntrada[2], vetMinutosSaida[2]);
            }
            if (vetHorasEntrada[1] > 0 || vetMinutosEntrada[1] > 0) {
                maior = calculaMaior(maior, vetHorasEntrada[3], vetHorasSaida[3], vetMinutosEntrada[3], vetMinutosSaida[3]);
            }

            horas = maior / 60;
            minutos = maior % 60;

            if (Double.parseDouble(converteTotal(cp.totaldiario[i]).split(":")[0]) >= Double.parseDouble(horasTrabalhadas.split(":")[0])) {
                hours71 += Double.parseDouble(intervaloAcima.split(":")[0]);
                minutes71 += Double.parseDouble(intervaloAcima.split(":")[1]);
            } else {
                if (Double.parseDouble(converteTotal(cp.totaldiario[i]).split(":")[0]) < Double.parseDouble(horasTrabalhadas.split(":")[0])) {
                    hours71 += Double.parseDouble(intervaloAbaixo.split(":")[0]);
                    minutes71 += Double.parseDouble(intervaloAbaixo.split(":")[1]);
                }
            }
            if (minutes71 >= 60) {
                minutes71 -= 60;
                hours71 += 1;
                if (minutes71 < 0) {
                    minutes71 *= -1;
                }
            }

            horas -= hours71;
            minutos -= minutes71;
            if (horas < 0) {
                horas = 0;
            }
            if (minutos < 0) {
                minutos = 0;
            }

            intervalo[i] = verificaExibicao(horas, minutos);
        }
        return intervalo;
    }

    private static int calculaMaior(int maior, int horaE, int horaS, int minutoE, int minutoS) {
        int difHora = ((horaE - horaS) * 60);
        if (horaE < horaS) {
            difHora = -60;
        }
        int difMinuto = minutoE - minutoS;
        int totalMinutos = difHora + difMinuto;
        if (totalMinutos > maior) {
            return totalMinutos;
        }
        return maior;
    }

    private String converteHora(String total, cartaoDePonto cp) {
        double minutos = 0, horas = 0;
        horas += Double.parseDouble(total.split(":")[0]);
        minutos += (Double.parseDouble(total.split(":")[1])) / 60;
        total = "" + (horas + minutos);
        return total;
    }

    private String converteDecimaisEmMinutos(String valor) {
        double total = 0;
        total = Double.parseDouble(valor) * 60;

        return "" + total;
    }

    private String converteHorasEmMinutos(String valor) {
        double total = 0;
        total += (Double.parseDouble(valor.split(":")[0]) * 60) + Double.parseDouble(valor.split(":")[1]);

        return "" + total;
    }

    private String converteTotal(String total) {
        double finalBuildTime = Double.parseDouble(total);
        int hours = (int) finalBuildTime;
        int minutes = (int) (finalBuildTime * 60) % 60;
        String retorno = "";
        if (hours < 10) {
            retorno += "0";
        }
        retorno += hours + ":";
        if (minutes < 10) {
            retorno += "0";
        }
        retorno += minutes;

        return retorno;
    }

    private int[] vetores(int colunas, String art71, String art66, String art384, String valorCalc, String excDiario, String excSem, String faltas,
            String adcNoturno, String horasExtrasDD, String horasExtrasDS, String horasExtrasDM, String horasExtrasTD, String horasExtrasTS,
            String horasExtrasTM, String rsr, String minutosResiduais) {
        List<Integer> posicoes = new ArrayList();
        posicoes.add(7);
        switch (colunas) {
            case 15: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 14: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 13: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 12: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 11: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 10: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 9: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 8: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 7: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 6: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 5: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 4: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 3: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 2: {
                posicoes.add(4);
                posicoes.add(4);
            }

            case 1: {
                posicoes.add(4);
                posicoes.add(4);
            }
            break;
        }

        posicoes.add(4);
        posicoes.add(5);

        if (art71.equals("1")) {
            if (valorCalc.equals("integral")) {
                posicoes.add(5);
            } else if (valorCalc.equals("diferenca")) {
                posicoes.add(5);
            }
        }

        if (excDiario.equals("1")) {
            posicoes.add(4);
        }

        if (excSem.equals("1")) {
            posicoes.add(4);
        }

        if (faltas.equals("1")) {
            posicoes.add(4);
        }

        if (adcNoturno.equals("1")) {
            posicoes.add(4);
        }

        if (art66.equals("1")) {
            posicoes.add(4);
        }

        if (art384.equals("1")) {
            posicoes.add(4);
        }

        if (horasExtrasDD.equals("1")) {
            posicoes.add(4);
            posicoes.add(4);
        }

        if (horasExtrasDS.equals("1")) {
            posicoes.add(4);
            posicoes.add(4);
        }

        if (horasExtrasDM.equals("1")) {
            posicoes.add(4);
            posicoes.add(4);
        }

        if (horasExtrasTD.equals("1")) {
            posicoes.add(4);
            posicoes.add(4);
            posicoes.add(4);
        }

        if (horasExtrasTS.equals("1")) {
            posicoes.add(4);
            posicoes.add(4);
            posicoes.add(4);
        }

        if (horasExtrasTM.equals("1")) {
            posicoes.add(4);
            posicoes.add(4);
            posicoes.add(4);
        }

        if (rsr.equals("1")) {
            posicoes.add(4);
        }

        if (minutosResiduais.equals("1")) {
            posicoes.add(4);
        }

        int[] lista = new int[posicoes.size()];

        for (int i = 0; i < lista.length; i++) {
            lista[i] = posicoes.get(i);
        }

        return lista;
    }

    private PdfPTable cabecalhoTabela(PdfPTable table, int colunas, Font font, String art71, String art66, String art384, String valorCalc, String excDiario,
            String excSem, String faltas, String adcNoturno, String horasExtrasDD, String horasExtrasDS, String horasExtrasDM,
            String horasExtrasTD, String horasExtrasTS, String horasExtrasTM, String rsr, String minutosResiduais) {
        table.addCell(novaCelula(new Phrase("Data", font), false));
        for (int j = 1; j < colunas + 1; j++) {
            table.addCell(novaCelula(new Phrase("   E" + j, font), false));
            table.addCell(novaCelula(new Phrase("   S" + j, font), false));
        }
        table.addCell(novaCelula(new Phrase("Ausências", font), false));
        table.addCell(novaCelula(new Phrase("Total Diário", font), false));
        if (art71.equals("1")) {
            table.addCell(novaCelula(new Phrase("Art 71", font), false));
        }
        if (art384.equals("1")) {
            table.addCell(novaCelula(new Phrase("Art 384", font), false));
        }
        if (excSem.equals("1")) {
            table.addCell(novaCelula(new Phrase("Exc. Sem.", font), false));
        }
        if (excDiario.equals("1")) {
            table.addCell(novaCelula(new Phrase("Exc. Diário", font), false));
        }
        if (faltas.equals("1")) {
            table.addCell(novaCelula(new Phrase("Faltas", font), false));
        }
        if (adcNoturno.equals("1")) {
            table.addCell(novaCelula(new Phrase("Adc. Noturno", font), false));
        }
        if (art66.equals("1")) {
            table.addCell(novaCelula(new Phrase("Art 66", font), false));
        }
        if (horasExtrasDD.equals("1")) {
            table.addCell(novaCelula(new Phrase("Horas Duplas Diárias", font), false));
            table.addCell(novaCelula(new Phrase("Horas Normais Diárias", font), false));
        }
        if (horasExtrasDS.equals("1")) {
            table.addCell(novaCelula(new Phrase("Horas Duplas Semanais", font), false));
            table.addCell(novaCelula(new Phrase("Horas Normais Semanais", font), false));
        }
        if (horasExtrasDM.equals("1")) {
            table.addCell(novaCelula(new Phrase("Horas Duplas Mensais", font), false));
            table.addCell(novaCelula(new Phrase("Horas Normais Mensais", font), false));
        }
        if (horasExtrasTD.equals("1")) {
            table.addCell(novaCelula(new Phrase("Horas Triplas Diárias 1", font), false));
            table.addCell(novaCelula(new Phrase("Horas Triplas Diárias 2", font), false));
            table.addCell(novaCelula(new Phrase("Horas Normais Diárias", font), false));
        }
        if (horasExtrasTS.equals("1")) {
            table.addCell(novaCelula(new Phrase("Horas Triplas Sem. 1", font), false));
            table.addCell(novaCelula(new Phrase("Horas Triplas Sem. 2", font), false));
            table.addCell(novaCelula(new Phrase("Horas Normais Sem.", font), false));
        }
        if (horasExtrasTM.equals("1")) {
            table.addCell(novaCelula(new Phrase("Horas Triplas Men. 1", font), false));
            table.addCell(novaCelula(new Phrase("Horas Triplas Men. 2", font), false));
            table.addCell(novaCelula(new Phrase("Horas Normais Men.", font), false));
        }
        if (rsr.equals("1")) {
            table.addCell(novaCelula(new Phrase("RSR", font), false));
        }
        if (minutosResiduais.equals("1")) {
            table.addCell(novaCelula(new Phrase("Art 58", font), false));
        }
        return table;
    }

    private PdfPCell emptyCell() {
        PdfPCell cell;
        cell = new PdfPCell(new Phrase(""));
        cell.setUseVariableBorders(true);
        cell.setBorderColorTop(BaseColor.WHITE);
        cell.setBorderColorBottom(BaseColor.WHITE);
        return cell;
    }

    private PdfPCell emptyCell(boolean fds) {
        PdfPCell cell;
        cell = new PdfPCell(new Phrase(""));
        cell.setUseVariableBorders(true);
        if (!fds) {
            cell.setBorderColorTop(BaseColor.WHITE);
        }
        cell.setBorderColorBottom(BaseColor.WHITE);
        return cell;
    }

    private static String encurtaDouble(String valor) {
        DecimalFormat df = new DecimalFormat("#.##");
        String retorno;
        try {
            retorno = "" + df.format(Double.parseDouble(valor));
        } catch (Exception e) {
            retorno = "0";
        }
        return retorno;
    }

    private static void selecionaColunas(PdfPTable table, Font font, String entrada, String saida, boolean fds) {
        table.addCell(novaCelula(new Phrase(entrada, font), fds));
        table.addCell(novaCelula(new Phrase(saida, font), fds));
    }

    private static PdfPCell novaCelula(Phrase conteudo, boolean fds) {
        PdfPCell cell = new PdfPCell(conteudo);
        cell.setUseVariableBorders(true);
        if (!fds) {
            cell.setBorderColorTop(BaseColor.WHITE);
        }
        cell.setBorderColorBottom(BaseColor.WHITE);
        return cell;
    }

    private static String verificaExibicao(double horas, double minutos) {
        if (horas < 0) {
            horas = 0;
        }
        if (horas <= 0 && minutos < 0) {
            horas = 0;
            minutos = 0;
        }
        if (horas >= 10 & minutos >= 10) {
            return horas + ":" + minutos;
        } else if (horas < 10 & minutos >= 10) {
            return "0" + horas + ":" + minutos;
        } else if (horas >= 10 & minutos < 10) {
            return horas + ":0" + minutos;
        } else if (horas < 10 & minutos < 10) {
            return "0" + horas + ":0" + minutos;
        } else if (horas < 0) {
            return "00:00";
        }

        return "";
    }

    private static double adicionalNoturno(String e, String s, String prorrogacao, String noturno60, String prorrogacao60) {
        double total = 0;
        int entradaHoras = Integer.parseInt(e.split(":")[0]);
        int entradaMinutos = Integer.parseInt(e.split(":")[1]);
        int saidaHoras = Integer.parseInt(s.split(":")[0]);
        int saidaMinutos = Integer.parseInt(s.split(":")[1]);
        double valorNoturno60 = 0, valorProrrogacao60 = 0;

        if (noturno60.equals("1")) {
            valorNoturno60 = 60;
        } else {
            valorNoturno60 = 52.5;
        }
        if (prorrogacao60.equals("1")) {
            valorNoturno60 = 60;
        } else {
            valorNoturno60 = 52.5;
        }

        if (entradaHoras != 0 || saidaHoras != 0) {
            if (entradaHoras < 22) {
                if (saidaHoras > 22) { //19:00 ate 23:00
                    total = ((saidaHoras - 22) * 60);
                    total += saidaMinutos - entradaMinutos;
                    total = total / valorNoturno60;
                } else if (saidaHoras < entradaHoras && saidaHoras > 4) { //19:00 ate 06:00
                    if (prorrogacao.equals("0")) {
                        total = ((5 + (entradaHoras - 22)) * 60);
                    } else {
                        total = ((entradaHoras - 22) * 60);
                    }
                    total -= entradaMinutos;
                    total = total / valorProrrogacao60;
                } else if (saidaHoras < entradaHoras) { //19:00 ate 04:00
                    total = ((saidaHoras + 2) * 60);
                    total += saidaMinutos - entradaMinutos;
                    total = total / valorNoturno60;
                } else if (entradaHoras < saidaHoras && saidaHoras < 5) { //02:00 ate 04:30
                    total = (saidaHoras - entradaHoras) * 60;
                    total += saidaMinutos - entradaMinutos;
                    total = total / valorNoturno60;
                } else if (entradaHoras < saidaHoras && saidaHoras > 4) { //03:00 ate 06:00
                    if (prorrogacao.equals("0")) {
                        total = (5 - entradaHoras) * 60;
                    } else {
                        total = entradaHoras * 60;
                    }
                    total -= entradaMinutos;
                    total = total / valorProrrogacao60;
                } else if (entradaHoras == saidaHoras) { //03:10 ate 03:50
                    total = 0;
                    total += saidaMinutos - entradaMinutos;
                    total = total / valorNoturno60;
                }
            } else {
                total = (entradaHoras - 22) * 60;
                if (saidaHoras < 4) {//23:00 ate 03:00
                    total += saidaHoras * 60;
                    total += saidaMinutos - entradaMinutos;
                    total = total / valorNoturno60;
                } else if (entradaHoras == saidaHoras) {//23:00 ate 23:30
                    total = saidaMinutos - entradaMinutos;
                    total = total / valorNoturno60;
                } else { // 23:00 ate 08:00
                    if (prorrogacao.equals("0")) {
                        total += (saidaHoras - (saidaHoras - 5)) * 60;
                    } else {
                        total += (saidaHoras - saidaHoras) * 60;
                    }
                    total -= entradaMinutos;
                    total = total / valorProrrogacao60;
                }
            }
            if (total < 0) {
                total = 0;
            }
        }
        return total;
    }

    private static String testaValor(String saida, String ultimaSaida) {
        if (Double.parseDouble(saida.split(":")[0]) > Double.parseDouble(ultimaSaida.split(":")[0])
                || (Double.parseDouble(saida.split(":")[0]) == Double.parseDouble(ultimaSaida.split(":")[0])
                && Double.parseDouble(saida.split(":")[1]) > Double.parseDouble(ultimaSaida.split(":")[1]))) {
            ultimaSaida = saida;
        }
        return ultimaSaida;
    }

    private static String ajustaHora(int horas, int minutos) {
        if (minutos < 0) {
            horas -= 1;
            minutos += 60;
            if (horas <= 0) {
                horas = 0;
                minutos = 0;
            }
        }
        return verificaExibicao(horas, minutos);
    }

    private static int minutosResiduais(String jornadaIdealEntrada, String jornadaIdealSaida, String entrada, String saida) {

        int totalEntrada = (Integer.parseInt(entrada.split(":")[0]) * 60) + Integer.parseInt(entrada.split(":")[1]);
        int totalSaida = (Integer.parseInt(saida.split(":")[0]) * 60) + Integer.parseInt(saida.split(":")[1]);
        int totalJIE = (Integer.parseInt(jornadaIdealEntrada.split(":")[0]) * 60) + Integer.parseInt(jornadaIdealEntrada.split(":")[1]);
        int totalJIS = (Integer.parseInt(jornadaIdealSaida.split(":")[0]) * 60) + Integer.parseInt(jornadaIdealSaida.split(":")[1]);
        int minutosResid = 0;

        if ((totalJIE - totalEntrada > 5 || totalJIE - totalEntrada < -5) && entrada != "00:00") {
            minutosResid += totalJIE - totalEntrada;
        }
        if ((totalJIS - totalSaida > 5 || totalJIS - totalSaida < -5) && saida != "00:00") {
            minutosResid += totalJIS - totalSaida;
        }

        return minutosResid;
    }

    private static String ultimaSaida(cartaoDePonto cp, int i) {
        if (cp.s1[i] != "00:00") {
            return cp.s1[i];
        } else if (cp.s2[i] != "00:00") {
            return cp.s2[i];
        } else if (cp.s3[i] != "00:00") {
            return cp.s3[i];
        } else if (cp.s4[i] != "00:00") {
            return cp.s4[i];
        } else if (cp.s5[i] != "00:00") {
            return cp.s5[i];
        } else if (cp.s6[i] != "00:00") {
            return cp.s6[i];
        } else if (cp.s7[i] != "00:00") {
            return cp.s7[i];
        } else if (cp.s8[i] != "00:00") {
            return cp.s8[i];
        } else if (cp.s9[i] != "00:00") {
            return cp.s9[i];
        } else if (cp.s10[i] != "00:00") {
            return cp.s10[i];
        } else if (cp.s11[i] != "00:00") {
            return cp.s11[i];
        } else if (cp.s12[i] != "00:00") {
            return cp.s12[i];
        } else if (cp.s13[i] != "00:00") {
            return cp.s13[i];
        } else if (cp.s14[i] != "00:00") {
            return cp.s14[i];
        } else {
            return cp.s15[i];
        }
    }
}
