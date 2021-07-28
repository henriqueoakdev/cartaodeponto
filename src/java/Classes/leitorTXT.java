/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Querys.qCartao;
import Querys.qInsercoesbd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Part;

/**
 *
 * @author Administrator
 */
public class leitorTXT {

    public static String Leitor(String nomeArquivo, String idCartao) throws IOException {
        String caminho = "C:\\Users\\Administrator\\Desktop\\WINSCT9\\CA" + nomeArquivo + ".dbf";
        try {
            FileReader arq = new FileReader(caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            int linhas = 0;
            String linha = lerArq.readLine();
            while (linha != null) {
                linha = lerArq.readLine();
                linhas++;
            }
            arq = new FileReader(caminho);
            lerArq = new BufferedReader(arq);
            for (int i = 0; i < linhas; i++) {
                linha = lerArq.readLine();
            }
            boolean loop = true;
            int i = 0;
            int puloInicial = 0;
            float totalHorasValor = 0;
            String totalHoras = "";
            try {
                while (linha.substring(puloInicial, puloInicial + 1).equals("\0") || linha.substring(puloInicial, puloInicial + 1).equals(" ")) {
                    puloInicial++;
                }
            } catch (Exception e) {

            }
            String dataInicial = linha.substring(0 + puloInicial, 8 + puloInicial);
            dataInicial = dataInicial.substring(6, 8) + "/" + dataInicial.substring(4, 6) + "/" + dataInicial.substring(0, 4);
            String dataFinal = dataInicial;
            qInsercoesbd.deleteDigitacao(idCartao);
            while (loop) {
                try {
                    String linhaCorte = linha.substring(((i * 155)) + puloInicial, ((i * 155) + 155 + puloInicial));
                    String data = linhaCorte.substring(0, 8);
                    String e1 = linhaCorte.substring(8, 13);
                    String e2 = linhaCorte.substring(13, 18);
                    String e3 = linhaCorte.substring(18, 23);
                    String e4 = linhaCorte.substring(23, 28);
                    String e5 = linhaCorte.substring(28, 33);
                    String s1 = linhaCorte.substring(58, 63);
                    String s2 = linhaCorte.substring(63, 68);
                    String s3 = linhaCorte.substring(68, 73);
                    String s4 = linhaCorte.substring(73, 78);
                    String s5 = linhaCorte.substring(78, 83);
                    data = data.substring(6, 8) + "/" + data.substring(4, 6) + "/" + data.substring(0, 4);
                    totalHorasValor = (converteHora(s1) - converteHora(e1))
                            + (converteHora(s2) - converteHora(e2)) + (converteHora(s3) - converteHora(e3))
                            + (converteHora(s4) - converteHora(e4)) + (converteHora(s5) - converteHora(e5));
                    totalHoras = "" + totalHorasValor;
                    qCartao.insereHoraImportacao(data, e1, s1, e2, s2, e3, s3, e4, s4, e5, s5, idCartao, totalHoras);
                    dataFinal = data;
                    i++;
                } catch (Exception e) {
                    loop = false;
                    qCartao.alteraData(dataInicial, dataFinal, idCartao);
                   // return "Erro durante importação do cartão!";
                }
            }
        } catch (FileNotFoundException e) {
            return "Cartão não encontrado!";
        }
        return "";
    }

    public static String Leitor2(String nomeArquivo, String idCartao) throws IOException {
        String caminho = "C:\\Users\\Administrator\\Desktop\\WINSCT9\\CA" + nomeArquivo + ".dbf";
        try {
            FileReader arq = new FileReader(caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            String aux = linha;
            while (linha != null) {
                aux = linha;
                linha = lerArq.readLine();
            }
            linha = aux;
            int puloInicial = 0;
            String totalHoras = "";
            float totalHorasValor = 0;
            String[] texto = linha.split("ÿ");
            try {
                while (texto[0].substring(puloInicial, puloInicial + 1).equals("\0") || texto[0].substring(puloInicial, puloInicial + 1).equals(" ")) {
                        puloInicial++;
                    }
                String dataInicial = linha.substring(0 + puloInicial, 8 + puloInicial);
                dataInicial = dataInicial.substring(6, 8) + "/" + dataInicial.substring(4, 6) + "/" + dataInicial.substring(0, 4);
                String dataFinal = dataInicial;
                qInsercoesbd.deleteDigitacao(idCartao);
                for (int i = 0; i < texto.length; i++) {                    
                    if (i != 0) {
                        puloInicial = 4;
                    }
                    if(i!=texto.length-1){
                    String data = texto[i].substring(puloInicial, puloInicial + 8);
                    String e1 = texto[i].substring(puloInicial + 8, puloInicial + 13);
                    String e2 = texto[i].substring(puloInicial + 13, puloInicial + 18);
                    String e3 = texto[i].substring(puloInicial + 18, puloInicial + 23);
                    String e4 = texto[i].substring(puloInicial + 23, puloInicial + 28);
                    String e5 = texto[i].substring(puloInicial + 28, puloInicial + 33);
                    String s1 = texto[i].substring(puloInicial + 58, puloInicial + 63);
                    String s2 = texto[i].substring(puloInicial + 63, puloInicial + 68);
                    String s3 = texto[i].substring(puloInicial + 68, puloInicial + 73);
                    String s4 = texto[i].substring(puloInicial + 73, puloInicial + 78);
                    String s5 = texto[i].substring(puloInicial + 78, puloInicial + 83);
                    data = data.substring(6, 8) + "/" + data.substring(4, 6) + "/" + data.substring(0, 4);
                    totalHorasValor = (converteHora(s1) - converteHora(e1))
                            + (converteHora(s2) - converteHora(e2)) + (converteHora(s3) - converteHora(e3))
                            + (converteHora(s4) - converteHora(e4)) + (converteHora(s5) - converteHora(e5));
                    totalHoras = "" + totalHorasValor;
                    qCartao.insereHoraImportacao(data, e1, s1, e2, s2, e3, s3, e4, s4, e5, s5, idCartao, totalHoras);
                    dataFinal = data;
                    puloInicial = 0;
                    } else{
                        System.out.println("deu erro");
                    }
                }
                qCartao.alteraData(dataInicial, dataFinal, idCartao);
            } catch (Exception e) {
                    return "Erro durante importação do cartão!";
            }
        } catch (FileNotFoundException e) {
            return "Cartão não encontrado!";
        }
        return "";
    }

    private static float converteHora(String hora) {
        if (hora.equals("     ") || hora.equals("")) {
            hora = "00:00";
        }
        String[] valorSplit = hora.split(":");
        float horas = Float.parseFloat(valorSplit[0]) * 60;
        float minutos = Float.parseFloat(valorSplit[1]);
        return (horas + minutos) / 60;
    }

    private static String converteDecimalHora(float decimal) {
        float total = decimal * 60;
        float hora = total / 60;
        float minuto = (total - (hora * 60));
        String textoHora = "" + hora;
        String textoMinuto = "" + minuto;
        if (minuto < 10) {
            textoMinuto = "0" + minuto;
        }
        if (hora < 10) {
            textoHora = "0" + hora;
        }
        return textoHora + ":" + textoMinuto;
    }
}
