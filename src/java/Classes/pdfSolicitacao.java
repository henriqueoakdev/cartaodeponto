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
import java.io.BufferedReader;
import java.io.FileReader;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class pdfSolicitacao {

    public String date;
    public String name;
    public String path;

    HttpSession session;

    public String gerarPDFHistorico(HttpSession session)
            throws IOException, DocumentException {
        this.name = "";
        this.session = session;
        try {
            this.session = session;
            name = "Solicitacao__HISTORICO.pdf";
            path = "C:\\SISTEMAS\\CONSULPER\\AUDITORIA\\HISTORICO\\" + name;
            createPdfHistorico(path, session);

        } catch (FileNotFoundException f) {
            //File file = new File("C:\\PDF\\PDF_" + date);
            File file = new File("C:\\SISTEMAS\\CONSULPER\\AUDITORIA\\HISTORICO");
            file.mkdirs();
            new pdfSolicitacao().createPdfHistorico(path, session);

        } finally {

        }
        return name;
    }

    public void createPdfHistorico(String filename, HttpSession session)
            throws IOException, DocumentException {


        // step 1
        Document document = new Document(PageSize.A4.rotate(), 30, 30, 75, 30);
        // step 2
        this.session = session;
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        Rectangle rect = new Rectangle(20, 20, 800, 550);
        writer.setBoxSize("art", rect);
        // step 3
        document.open();
        // Criar as tabelas com limite de linhas e quebra de página
        Font font;
        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
        //informações do topo da pagina
        document.add(new Paragraph("Solicitação Numero: "));
        document.add(new Paragraph("Escritorio: "));
        document.add(new Paragraph("Cliente: "));
        document.add(new Paragraph("Criado por: " ));
        document.add(new Paragraph(" "));
        try {
            String historicoNome = "C:\\SISTEMAS\\CONSULPER\\AUDITORIA\\HISTORICO\\Historico.txt";
            FileReader arq = new FileReader(historicoNome);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {

                //criação da tabela
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setWidths(new int[]{1, 3, 1});
                PdfPCell cell;

                // cabeçalho da tabela
                cell = new PdfPCell(new Phrase("Data"));
                cell.setUseVariableBorders(true);
                cell.setBorderColorLeft(BaseColor.WHITE);
                cell.setBorderColorRight(BaseColor.WHITE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Evento"));
                cell.setUseVariableBorders(true);
                cell.setBorderColorLeft(BaseColor.WHITE);
                cell.setBorderColorRight(BaseColor.WHITE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Por:"));
                cell.setUseVariableBorders(true);
                cell.setBorderColorLeft(BaseColor.WHITE);
                cell.setBorderColorRight(BaseColor.WHITE);
                table.addCell(cell);
                //
                int cont = 0;
                while (linha != null && cont < 15) {
                    if (linha.equals("")) {
                        linha = lerArq.readLine();
                    } else {
                        String[] parametrosHistorico = linha.split(";;;;");
                        //data
                        cell = new PdfPCell(new Phrase(parametrosHistorico[0], font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        cell.setBorderColorTop(BaseColor.WHITE);
                        cell.setBorderColorBottom(BaseColor.WHITE);
                        table.addCell(cell);
                        //evento
                        cell = new PdfPCell(new Phrase(parametrosHistorico[1], font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        cell.setBorderColorTop(BaseColor.WHITE);
                        cell.setBorderColorBottom(BaseColor.WHITE);
                        table.addCell(cell);
                        //usuario
                        cell = new PdfPCell(new Phrase(parametrosHistorico[2], font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        cell.setBorderColorTop(BaseColor.WHITE);
                        cell.setBorderColorBottom(BaseColor.WHITE);
                        table.addCell(cell);
                        linha = lerArq.readLine();
                        cont++;
                    }
                }
                document.add(table);
                if (linha != null) {
                    document.newPage();
                }
            }
            arq.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        // step 5
        document.close();
    }

    public void createPdfAlteracoes(String filename, HttpSession session)
            throws IOException, DocumentException {
        
//        String Escritorio = lEmpresa.listaEmpresa(parametros[9])[1];
//        String Cliente = lEmpresa.listaEmpresa(parametros[10])[1];
//        String Criador = lEmpresa.listaEmpresa(parametros[13])[1];

        // step 1
        Document document = new Document(PageSize.A4.rotate(), 30, 30, 75, 30);
        // step 2
        this.session = session;
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        Rectangle rect = new Rectangle(20, 20, 800, 550);
        writer.setBoxSize("art", rect);
        // step 3
        document.open();
        //informações do topo da pagina
        document.add(new Paragraph("Solicitação Numero: "));
        document.add(new Phrase("\n"));
        Font font;
        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        try {
            String historicoNome = "C:\\SISTEMAS\\CONSULPER\\AUDITORIA\\ALTERACOES\\Alteracoes.txt";
            FileReader arq = new FileReader(historicoNome);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {

                BaseColor color = new BaseColor(51, 122, 183);
                BaseColor danger = new BaseColor(180, 55, 55);
                //
                int cont = 0;
                while (linha != null && cont < 2) {
                    if (linha.equals("")) {
                        linha = lerArq.readLine();
                    } else {
                        //criação da tabela
                        PdfPTable table = new PdfPTable(6);
                        table.setWidthPercentage(100);
                        table.setWidths(new int[]{1, 1, 1, 1, 1, 1});
                        PdfPCell cell;
                        // cabeçalho da tabela
                        cell = new PdfPCell(new Phrase("Campo"));
                        cell.setBackgroundColor(color);
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("De:"));
                        cell.setBackgroundColor(color);
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Para:"));
                        cell.setBackgroundColor(color);
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Campo"));
                        cell.setBackgroundColor(color);
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("De:"));
                        cell.setBackgroundColor(color);
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Para:"));
                        cell.setBackgroundColor(color);
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        table.addCell(cell);
                        String[] parametrosHistorico = linha.split(";;;;");
                        //Processo
                        String processoOld = parametrosHistorico[1].split(">>>>")[0];
                        String processoNew = parametrosHistorico[1].split(">>>>")[1];
                        cell = new PdfPCell(new Phrase("Nº Processo", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!processoOld.equals(processoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(processoOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!processoOld.equals(processoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(processoNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!processoOld.equals(processoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        //Vara
                        String varaOld = parametrosHistorico[2].split(">>>>")[0];
                        String varaNew = parametrosHistorico[2].split(">>>>")[1];
                        cell = new PdfPCell(new Phrase("Vara", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!varaOld.equals(varaNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(varaOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!varaOld.equals(varaNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(varaNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!varaOld.equals(varaNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        //Email
                        String emailOld = parametrosHistorico[3].split(">>>>")[0];
                        String emailNew = parametrosHistorico[3].split(">>>>")[1];
                        cell = new PdfPCell(new Phrase("Email", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!emailOld.equals(emailNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(emailOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!emailOld.equals(emailNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(emailNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!emailOld.equals(emailNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        //Contato
                        String contatoOld = parametrosHistorico[4].split(">>>>")[0];
                        String contatoNew = parametrosHistorico[4].split(">>>>")[1];
                        cell = new PdfPCell(new Phrase("Contato", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!contatoOld.equals(contatoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(contatoOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!contatoOld.equals(contatoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(contatoNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!contatoOld.equals(contatoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
//                        //Objetivo
//                        String objetivoOld = nBd.listaTiposDiversosUnicoInativo(parametrosHistorico[5].split(">>>>")[0]).split(";;")[1];
//                        String objetivoNew = nBd.listaTiposDiversosUnicoInativo(parametrosHistorico[5].split(">>>>")[1]).split(";;")[1];
//                        cell = new PdfPCell(new Phrase("Objetivo", font));
//                        cell.setUseVariableBorders(true);
//                        cell.setBorderColorLeft(BaseColor.WHITE);
//                        cell.setBorderColorRight(BaseColor.WHITE);
//                        if (!objetivoOld.equals(objetivoNew)) {
//                            cell.setBackgroundColor(danger);
//                        }
//                        table.addCell(cell);
//                        cell = new PdfPCell(new Phrase(objetivoOld, font));
//                        cell.setUseVariableBorders(true);
//                        cell.setBorderColorLeft(BaseColor.WHITE);
//                        cell.setBorderColorRight(BaseColor.WHITE);
//                        if (!objetivoOld.equals(objetivoNew)) {
//                            cell.setBackgroundColor(danger);
//                        }
//                        table.addCell(cell);
//                        cell = new PdfPCell(new Phrase(objetivoNew, font));
//                        cell.setUseVariableBorders(true);
//                        cell.setBorderColorLeft(BaseColor.WHITE);
//                        cell.setBorderColorRight(BaseColor.WHITE);
//                        if (!objetivoOld.equals(objetivoNew)) {
//                            cell.setBackgroundColor(danger);
//                        }
//                        table.addCell(cell);
                        //Tipos
                        String tipoOld = "";
                        String tipoNew = "";
                        
                        
                        cell = new PdfPCell(new Phrase("Tipos", font));
                        table.addCell(cell);
                        
                        
                        cell = new PdfPCell(new Phrase(tipoOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!tipoOld.equals(tipoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(tipoNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!tipoOld.equals(tipoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);

                        /*
                    //Dados

                    String dadosOld;
                    String dadosNew;
                    try {
                        dadosOld = parametrosHistorico[7].split(">>>>")[0];
                        dadosNew = parametrosHistorico[7].split(">>>>")[1];
                    } catch (Exception e) {
                        dadosOld = "";
                        dadosNew = "";
                    }
                    cell = new PdfPCell(new Phrase("Dados Complementares", font));
                    cell.setUseVariableBorders(true);
                    cell.setBorderColorLeft(BaseColor.WHITE);
                    cell.setBorderColorRight(BaseColor.WHITE);


                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase(dadosOld, font));
                    cell.setUseVariableBorders(true);
                    cell.setBorderColorLeft(BaseColor.WHITE);
                    cell.setBorderColorRight(BaseColor.WHITE);


                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase(dadosNew, font));
                    cell.setUseVariableBorders(true);
                    cell.setBorderColorLeft(BaseColor.WHITE);
                    cell.setBorderColorRight(BaseColor.WHITE);


                    table.addCell(cell);

                         */
                        //Estado
                        String estadoOld = "";
                        String estadoNew = "";
                        cell = new PdfPCell(new Phrase("Estado", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!estadoOld.equals(estadoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(estadoOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!estadoOld.equals(estadoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(estadoNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!estadoOld.equals(estadoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        //Cidade
                        String cidadeOld = "";
                        String cidadeNew ="";
                        cell = new PdfPCell(new Phrase("Cidade", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!cidadeOld.equals(cidadeOld)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(cidadeOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!cidadeOld.equals(cidadeOld)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(cidadeNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!cidadeOld.equals(cidadeOld)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        //Escritorio
                        String escritorioOld = "";
                        String escritorioNew = "";
                        cell = new PdfPCell(new Phrase("Escritorio", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!escritorioOld.equals(escritorioNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(escritorioOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!escritorioOld.equals(escritorioNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(escritorioNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!escritorioOld.equals(escritorioNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        //Cliente
                        String clienteOld ="";
                        String clienteNew = "";
                        cell = new PdfPCell(new Phrase("Cliente", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!clienteOld.equals(clienteNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(clienteOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!clienteOld.equals(clienteNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(clienteNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!clienteOld.equals(clienteNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        //Criador
                        String criadorOld = "";
                        String criadorNew = "";
                        cell = new PdfPCell(new Phrase("Criador", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!criadorOld.equals(criadorNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(criadorOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!criadorOld.equals(criadorNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(criadorNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!criadorOld.equals(criadorNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        //Prazo
                        String prazoOld = parametrosHistorico[11].split(">>>>")[0];
                        String prazoNew = parametrosHistorico[11].split(">>>>")[1];
                        cell = new PdfPCell(new Phrase("Prazo", font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!prazoOld.equals(prazoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(prazoOld, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!prazoOld.equals(prazoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(prazoNew, font));
                        cell.setUseVariableBorders(true);
                        cell.setBorderColorLeft(BaseColor.WHITE);
                        cell.setBorderColorRight(BaseColor.WHITE);
                        if (!prazoOld.equals(prazoNew)) {
                            cell.setBackgroundColor(danger);
                        }
                        table.addCell(cell);

                        linha = lerArq.readLine();
                        cont++;
                        document.add(table);
                        document.add(new Phrase("\n"));
                    }
                }

                if (linha != null) {
                    document.newPage();
                }
            }
            arq.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        // step 5
        document.close();
    }

}
