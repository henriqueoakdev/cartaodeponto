/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Querys;

import data.ConnectionPool;
import data.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Classes.cartaoDePonto;
import Classes.tabelaDigitacao;

/**
 *
 * @author Administrator
 */
public class qInsercoesbd {

    public static String selectAjustarConformeFolgas(String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT COUNT(DIA) AS DIAS \n"
                + "FROM DIGITACAO WHERE ID_CARTAO = " + idCartao + "\n"
                + "and DIA like '%Fer'\n";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("DIAS");
            }
            return "0";
        } catch (SQLException e) {
            System.out.println(e);
            return "0";
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectTipoFeriado(String tipo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT nome, dtFeriado, cidade, justificativa, tipo \n"
                + "FROM feriados where tipo = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("dtFeriado") + ";;";
            }
            return resultado.split(";;");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split(";;");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void insereFeriado(String nome, String data, String cidade, String justificativa, String tipo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO FERIADOS (nome, dtFeriado, cidade, justificativa, tipo)\n"
                + "values('" + nome + "', '" + data + "', '" + cidade + "', '" + justificativa + "', '" + tipo + "')";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void insereCidade(String nome) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO CIDADES (nome)\n"
                + "values(?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nome);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void deleteDigitacao(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "DELETE FROM DIGITACAO WHERE ID_CARTAO = ?\n";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void alteraDigitacao(String E1, String S1, String E2, String S2, String E3, String S3, String E4, String S4, String E5, String S5, String E6, String S6, String E7, String S7, String E8, String S8, String E9, String S9, String E10, String S10, String E11, String S11, String E12, String S12, String E13, String S13, String E14, String S14, String E15, String S15, String TOTAL_DIARIO, String FOLGA, String ID_DIGITACAO) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE DIGITACAO SET E1 = '" + E1 + "', S1 = '" + S1 + "', E2 = '" + E2 + "', S2 = '" + S2 + "', E3 = '" + E3 + "', S3 = '" + S3 + "', E4 = '" + E4 + "', S4 = '" + S4 + "',"
                + "                          E5 = '" + E5 + "', S5 = '" + S5 + "', E6 = '" + E6 + "', S6 = '" + S6 + "', E7 = '" + E7 + "', S7 = '" + S7 + "', E8 = '" + E8 + "', S8 = '" + S8 + "',"
                + "                          E9 = '" + E9 + "', S9 = '" + S9 + "', E10 = '" + E10 + "', S10 = '" + S10 + "', E11 = '" + E11 + "', S11 = '" + S11 + "', E12 = '" + E12 + "', S12 = '" + S12 + "',"
                + "                          E13 = '" + E13 + "', S13 = '" + S13 + "', E14 = '" + E14 + "', S14 = '" + S14 + "', E15 = '" + E15 + "', S15 = '" + S15 + "', TOTAL_DIARIO = '" + TOTAL_DIARIO + "', FOLGAS = '" + FOLGA + "'\n"
                + "WHERE ID_DIGITACAO = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ID_DIGITACAO);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void alteraColunas(String NUM_COLUNAS, String PROTOCOLO) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE CARTOES SET NUM_COLUNAS = ?\n"
                + "WHERE PROTOCOLO = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, NUM_COLUNAS);
            ps.setString(2, PROTOCOLO);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void deleteDigitacaoSemana(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "DELETE FROM DIGITACAO_SEMANA WHERE ID_CARTAO = ?\n";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void deleteDigitacaoMes(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "DELETE FROM DIGITACAO_MES WHERE ID_CARTAO = ?\n";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectFeriado() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = " ;; ;; ;; ;; ";

        String query = "SELECT nome, dtFeriado, cidade, justificativa, tipo \n"
                + "FROM feriados";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("nome") + ";;"
                        + rs.getString("dtFeriado") + ";;"
                        + rs.getString("cidade") + ";;"
                        + rs.getString("justificativa") + ";;"
                        + rs.getString("tipo") + "----";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectTodosFeriados() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT nome, dtFeriado\n"
                + "FROM feriados";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("nome") + ";;"
                        + rs.getString("dtFeriado") + "----";
            }
            if (resultado.equals("")) {
                resultado = " ;; ";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectData(String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT DIA FROM DIGITACAO where ID_CARTAO = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idCartao);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("DIA") + ";;";
            }
            return resultado.split(";;");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split(";;");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectIdDigitacao(String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT ID_DIGITACAO FROM DIGITACAO where ID_CARTAO = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idCartao);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("ID_DIGITACAO") + ";;";
            }
            return resultado.split(";;");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split(";;");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectCartao(String parametro, String valor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT PROTOCOLO, DT_INCLUSAO, PROCESSO, JUNTA, RECLAMANTE, RECLAMADA, DT_INICIO, DT_FINAL\n"
                + "FROM CARTOES where " + parametro + " like ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, valor);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("PROTOCOLO") + ";;"
                        + rs.getString("DT_INCLUSAO") + ";;"
                        + rs.getString("PROCESSO") + ";;"
                        + rs.getString("JUNTA") + ";;"
                        + rs.getString("RECLAMANTE") + ";;"
                        + rs.getString("RECLAMADA") + ";;"
                        + rs.getString("DT_INICIO") + ";;"
                        + rs.getString("DT_FINAL") + "----";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectTodosCartoes() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT PROTOCOLO, DT_INCLUSAO, PROCESSO, JUNTA, RECLAMANTE, RECLAMADA, DT_INICIO, DT_FINAL, "
                + "FERIADO_MUN, FERIADO_NAC, FERIADO_NACF, STATUS\n"
                + "FROM CARTOES";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("PROTOCOLO") + ";;"
                        + rs.getString("DT_INCLUSAO") + ";;"
                        + rs.getString("PROCESSO") + ";;"
                        + rs.getString("JUNTA") + ";;"
                        + rs.getString("RECLAMANTE") + ";;"
                        + rs.getString("RECLAMADA") + ";;"
                        + rs.getString("DT_INICIO") + ";;"
                        + rs.getString("DT_FINAL") + ";;"
                        + rs.getString("FERIADO_MUN") + ";;"
                        + rs.getString("FERIADO_NAC") + ";;"
                        + rs.getString("FERIADO_NACF") + ";;"
                        + rs.getString("STATUS") + "----";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectDigitacao(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT ID_DIGITACAO, E1, S1, E2, S2, E3, S3, E4, S4, E5, S5,"
                + "                          E6, S6, E7, S7, E8, S8, E9, S9, E10, S10,"
                + "                          E11, S11, E12, S12, E13, S13, E14, S14, E15, S15,"
                + "                          TOTAL_DIARIO, FOLGAS, EXC_DIARIO\n"
                + "                          FROM DIGITACAO where ID_CARTAO = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("E1") + ";;"
                        + rs.getString("S1") + ";;"
                        + rs.getString("E2") + ";;"
                        + rs.getString("S2") + ";;"
                        + rs.getString("E3") + ";;"
                        + rs.getString("S3") + ";;"
                        + rs.getString("E4") + ";;"
                        + rs.getString("S4") + ";;"
                        + rs.getString("E5") + ";;"
                        + rs.getString("S5") + ";;"
                        + rs.getString("E6") + ";;"
                        + rs.getString("S6") + ";;"
                        + rs.getString("E7") + ";;"
                        + rs.getString("S7") + ";;"
                        + rs.getString("E8") + ";;"
                        + rs.getString("S8") + ";;"
                        + rs.getString("E9") + ";;"
                        + rs.getString("S9") + ";;"
                        + rs.getString("E10") + ";;"
                        + rs.getString("S10") + ";;"
                        + rs.getString("E11") + ";;"
                        + rs.getString("S11") + ";;"
                        + rs.getString("E12") + ";;"
                        + rs.getString("S12") + ";;"
                        + rs.getString("E13") + ";;"
                        + rs.getString("S13") + ";;"
                        + rs.getString("E14") + ";;"
                        + rs.getString("S14") + ";;"
                        + rs.getString("E15") + ";;"
                        + rs.getString("S15") + ";;"
                        + rs.getString("TOTAL_DIARIO") + ";;"
                        + rs.getString("FOLGAS") + ";;"
                        + rs.getString("EXC_DIARIO") + ";;"
                        + rs.getString("ID_DIGITACAO") + "----";
            }
            if (resultado.equals("")) {
                resultado = " ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ----";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            resultado = " ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ----";
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectDigitacaoPaginado(String idcartao, int index, String[] idDigitacao) { //repensar esta query
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idInicio = Integer.parseInt(idDigitacao[0]);
        int idFinal = Integer.parseInt(idDigitacao[idDigitacao.length - 1]);
        String resultado = "";
        String query = "";

        if (index > 1) {
            idInicio += 180 * (index - 1);
            query = "SELECT E1, S1, E2, S2, E3, S3, E4, S4, E5, S5,"
                    + "            E6, S6, E7, S7, E8, S8, E9, S9, E10, S10,"
                    + "            E11, S11, E12, S12, E13, S13, E14, S14, E15, S15,"
                    + "            TOTAL_DIARIO, FOLGAS, EXC_DIARIO, AUSENCIA\n"
                    + "            FROM DIGITACAO where ID_CARTAO = ? "
                    + "            AND ID_DIGITACAO >= '" + idInicio + "' AND ID_DIGITACAO <= '" + idFinal + "'";
        } else {
            query = "SELECT E1, S1, E2, S2, E3, S3, E4, S4, E5, S5,"
                    + "            E6, S6, E7, S7, E8, S8, E9, S9, E10, S10,"
                    + "            E11, S11, E12, S12, E13, S13, E14, S14, E15, S15,"
                    + "            TOTAL_DIARIO, FOLGAS, EXC_DIARIO, AUSENCIA\n"
                    + "            FROM DIGITACAO where ID_CARTAO = ?";
        }

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("E1") + ";;"
                        + rs.getString("S1") + ";;"
                        + rs.getString("E2") + ";;"
                        + rs.getString("S2") + ";;"
                        + rs.getString("E3") + ";;"
                        + rs.getString("S3") + ";;"
                        + rs.getString("E4") + ";;"
                        + rs.getString("S4") + ";;"
                        + rs.getString("E5") + ";;"
                        + rs.getString("S5") + ";;"
                        + rs.getString("E6") + ";;"
                        + rs.getString("S6") + ";;"
                        + rs.getString("E7") + ";;"
                        + rs.getString("S7") + ";;"
                        + rs.getString("E8") + ";;"
                        + rs.getString("S8") + ";;"
                        + rs.getString("E9") + ";;"
                        + rs.getString("S9") + ";;"
                        + rs.getString("E10") + ";;"
                        + rs.getString("S10") + ";;"
                        + rs.getString("E11") + ";;"
                        + rs.getString("S11") + ";;"
                        + rs.getString("E12") + ";;"
                        + rs.getString("S12") + ";;"
                        + rs.getString("E13") + ";;"
                        + rs.getString("S13") + ";;"
                        + rs.getString("E14") + ";;"
                        + rs.getString("S14") + ";;"
                        + rs.getString("E15") + ";;"
                        + rs.getString("S15") + ";;"
                        + rs.getString("TOTAL_DIARIO") + ";;"
                        + rs.getString("FOLGAS") + ";;"
                        + rs.getString("EXC_DIARIO") + ";;"
                        + rs.getString("AUSENCIA") + "----";
            }
            if (resultado.equals("")) {
                resultado = " ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ----";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            resultado = " ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ----";
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void alteraDigitacaoPaginado(int index, String E1, String S1, String E2, String S2, String E3, String S3, String E4, String S4, String E5, String S5, String E6, String S6, String E7, String S7, String E8, String S8, String E9, String S9, String E10, String S10, String E11, String S11, String E12, String S12, String E13, String S13, String E14, String S14, String E15, String S15, String TOTAL_DIARIO, String FOLGA, int ID_DIGITACAO) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (index > 1) {
            ID_DIGITACAO += 180 * (index - 1);
        }

        String query = "UPDATE DIGITACAO SET E1 = '" + E1 + "', S1 = '" + S1 + "', E2 = '" + E2 + "', S2 = '" + S2 + "', E3 = '" + E3 + "', S3 = '" + S3 + "', E4 = '" + E4 + "', S4 = '" + S4 + "',"
                + "                          E5 = '" + E5 + "', S5 = '" + S5 + "', E6 = '" + E6 + "', S6 = '" + S6 + "', E7 = '" + E7 + "', S7 = '" + S7 + "', E8 = '" + E8 + "', S8 = '" + S8 + "',"
                + "                          E9 = '" + E9 + "', S9 = '" + S9 + "', E10 = '" + E10 + "', S10 = '" + S10 + "', E11 = '" + E11 + "', S11 = '" + S11 + "', E12 = '" + E12 + "', S12 = '" + S12 + "',"
                + "                          E13 = '" + E13 + "', S13 = '" + S13 + "', E14 = '" + E14 + "', S14 = '" + S14 + "', E15 = '" + E15 + "', S15 = '" + S15 + "', TOTAL_DIARIO = '" + TOTAL_DIARIO + "', FOLGAS = '" + FOLGA + "'\n"
                + "WHERE ID_DIGITACAO = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ID_DIGITACAO);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectDigitacaoImportacao(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT E1, S1, E2, S2, E3, S3, E4, S4, E5, S5, E6, S6, E7, S7, E8, S8,"
                + "            E9, S9, E10, S10, E11, S11, E12, S12, E13, S13, E14, S14, E15, S15\n"
                + "            FROM DIGITACAO where ID_CARTAO = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("E1") + ";;"
                        + rs.getString("S1") + ";;"
                        + rs.getString("E2") + ";;"
                        + rs.getString("S2") + ";;"
                        + rs.getString("E3") + ";;"
                        + rs.getString("S3") + ";;"
                        + rs.getString("E4") + ";;"
                        + rs.getString("S4") + ";;"
                        + rs.getString("E5") + ";;"
                        + rs.getString("S5") + ";;"
                        + rs.getString("E6") + ";;"
                        + rs.getString("S6") + ";;"
                        + rs.getString("E7") + ";;"
                        + rs.getString("S7") + ";;"
                        + rs.getString("E8") + ";;"
                        + rs.getString("S8") + ";;"
                        + rs.getString("E9") + ";;"
                        + rs.getString("S9") + ";;"
                        + rs.getString("E10") + ";;"
                        + rs.getString("S10") + ";;"
                        + rs.getString("E11") + ";;"
                        + rs.getString("S11") + ";;"
                        + rs.getString("E12") + ";;"
                        + rs.getString("S12") + ";;"
                        + rs.getString("E13") + ";;"
                        + rs.getString("S13") + ";;"
                        + rs.getString("E14") + ";;"
                        + rs.getString("S14") + ";;"
                        + rs.getString("E15") + ";;"
                        + rs.getString("S15") + ";;";
            }
            if (resultado.equals("")) {
                resultado = " ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ----";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            resultado = " ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ----";
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static double contaDiasDigitacao(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT COUNT(ID_DIGITACAO) AS DIAS\n"
                + "FROM DIGITACAO where ID_CARTAO = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("DIAS");
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String selectDtInicialDigitacao(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT DT_INICIO\n"
                + "FROM CARTOES where PROTOCOLO = ?\n";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado = rs.getString("DT_INICIO");
            }
            return resultado;
        } catch (SQLException e) {
            System.out.println(e);
            return resultado;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String selectDtFinalDigitacao(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT DT_FINAL\n"
                + "FROM CARTOES where PROTOCOLO = ?\n";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado = rs.getString("DT_FINAL");
            }
            return resultado;
        } catch (SQLException e) {
            System.out.println(e);
            return resultado;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectDigitacaoSemana(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT NUM_SEMANA, VALOR_SEMANA, ID_SEMANA\n"
                + "FROM DIGITACAO_SEMANA where ID_CARTAO like ? \n"
                + "ORDER BY NUM_SEMANA";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("VALOR_SEMANA") + "----";
            }
            if (resultado.equals("")) {
                resultado = "0";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectDigitacaoMes(String idcartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT NUM_MES, VALOR_MES, ID_MES\n"
                + "FROM DIGITACAO_MES where ID_CARTAO like ? \n"
                + "ORDER BY NUM_MES";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idcartao);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("VALOR_MES") + "----";
            }
            if (resultado.equals("")) {
                resultado = "0";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectPrimUltimoFeriado() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT nome, dtFeriado, cidade, justificativa, tipo \n"
                + "FROM feriados where id = (select top 1 id from feriados order by id)\n"
                + "or id = (select top 1 id from feriados order by id desc)\n";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("nome") + ";;"
                        + rs.getString("dtFeriado") + ";;"
                        + rs.getString("cidade") + ";;"
                        + rs.getString("justificativa") + ";;"
                        + rs.getString("tipo") + "----";
            }
            if (resultado.equals("")) {
                resultado = " ;; ;; ;; ;; ;; ;; ----";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectDiaFeriado() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT dtFeriado\n"
                + "FROM feriados";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("dtFeriado") + ";;";
            }
            return resultado.split(";;");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split(";;");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectCidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT id_cidade,nome\n"
                + "FROM CIDADES";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("id_cidade") + ";;" + rs.getString("nome") + "----";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] selectFeriadosCidade(String cidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT NOME, DTFERIADO FROM FERIADOS WHERE CIDADE = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cidade);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("NOME") + ";;" + rs.getString("DTFERIADO") + "----";
            }
            return resultado.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return resultado.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void alteraAusencias(String AUSENCIA, String ID_CARTAO, String DIAINICIO, String DIAFINAL) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE DIGITACAO SET AUSENCIA = ?\n"
                + "WHERE ID_CARTAO = ? and DIA >= ? and DIA <= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, AUSENCIA);
            ps.setString(2, ID_CARTAO);
            ps.setString(3, DIAINICIO);
            ps.setString(4, DIAFINAL);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void selectDiaAusencias(String ID_CARTAO, String DIAINICIAL, String DIAFINAL) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT ID_DIGITACAO FROM DIGITACAO WHERE ID_CARTAO = ? AND DIA = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ID_CARTAO);
            ps.setString(2, DIAINICIAL);
            rs = ps.executeQuery();
            String resultado = "";

            while (rs.next()) {
                resultado += rs.getString("ID_DIGITACAO") + ";;";
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
