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

/**
 *
 * @author Administrator
 */
public class qCartao {

    public static void alteraExcedentes(String excSem, String excSeg, String excTer, String excQua, String excQui, String excSex, String excSab, String excDom, String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE EXCEDENTES SET SEM = '" + excSem + "', SEG = '" + excSeg + "', TER = '" + excTer + "', QUA = '" + excQua + "', QUI = '" + excQui + "', SEX = '" + excSex + "', SAB = '" + excSab + "', DOM = '" + excDom + "', ID_CARTAO = '" + idCartao + "'\n"
                + "WHERE ID_CARTAO = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idCartao);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void alteraData(String dataInicial, String dataFinal, String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE CARTOES SET DT_INICIO = '" + dataInicial + "', DT_FINAL = '" + dataFinal + "'\n"
                + "WHERE PROTOCOLO = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idCartao);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void alteraExcDiario(String dia, String excDiario, String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE DIGITACAO SET EXC_DIARIO = '" + excDiario + "'\n"
                + "WHERE ID_CARTAO = ? AND DIA = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idCartao);
            ps.setString(2, dia);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void alteraExcSemana(int numSemana, String excSem, String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE DIGITACAO_SEMANA SET EXC_SEM = ?\n"
                + "WHERE ID_CARTAO = ? AND NUM_SEMANA = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, excSem);
            ps.setString(2, idCartao);
            ps.setInt(3, numSemana);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void insereExcedentes(String excSem, String excSeg, String excTer, String excQua, String excQui, String excSex, String excSab, String excDom, String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO EXCEDENTES (SEM, SEG, TER, QUA, QUI, SEX, SAB, DOM, ID_CARTAO)\n"
                + "values('" + excSem + "', '" + excSeg + "', '" + excTer + "', '" + excQua + "', '" + excQui + "', '" + excSex + "', '" + excSab + "', '" + excDom + "', '" + idCartao + "')";

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

    public static void insereSemana(int numSemana, String valorSemana, String idCartao, String excSem) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO DIGITACAO_SEMANA (NUM_SEMANA, VALOR_SEMANA, ID_CARTAO, EXC_SEM)\n"
                + "values('" + numSemana + "', '" + valorSemana + "', '" + idCartao + "', '" + excSem + "')";

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

    public static void insereMes(int numMes, String valorMes, String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO DIGITACAO_MES (NUM_MES, VALOR_MES, ID_CARTAO)\n"
                + "values('" + numMes + "', '" + valorMes + "', '" + idCartao + "')";

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
    
    public static void insereHoraImportacao(String DIA, String E1, String S1, String E2, String S2, String E3, String S3, String E4, String S4, String E5, String S5, String IDCARTAO, String TOTALHORAS) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO DIGITACAO (DIA, E1, S1, E2, S2, E3, S3, E4, S4, E5, S5, ID_CARTAO, TOTAL_DIARIO)\n"
                + "values('" + DIA + "', '" + E1 + "', '" + S1 + "', '" + E2 + "', '" + S2 + "', '" + E3 + "', '" + S3 + "', '" + E4 + "', '" + S4 + "', '" + E5 + "', '" + S5 + "', '" + IDCARTAO + "', '" + TOTALHORAS + "')";

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

    public static void insereDigitacao(String DIA, String E1, String S1, String E2, String S2, String E3, String S3, String E4, String S4, String E5, String S5, String E6, String S6, String E7, String S7, String E8, String S8, 
                                       String E9, String S9, String E10, String S10, String E11, String S11, String E12, String S12, String E13, String S13, String E14, String S14, String E15, String S15, String TOTAL_DIARIO, String FOLGA, String IDCARTAO) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO DIGITACAO (DIA, E1, S1, E2, S2, E3, S3, E4, S4, E5, S5, E6, S6, E7, S7, E8, S8, E9, S9, E10, S10, E11, S11, E12, S12, E13, S13, E14, S14, E15, S15, TOTAL_DIARIO, FOLGAS, ID_CARTAO)\n"
                + "values('" + DIA + "', '" + E1 + "', '" + S1 + "', '" + E2 + "', '" + S2 + "', '" + E3 + "', '" + S3 + "', '" + E4 + "', '" + S4 + "', '" + E5 + "', '" + S5 + "',"
                + "                      '" + E6 + "', '" + S6 + "', '" + E7 + "', '" + S7 + "', '" + E8 + "', '" + S8 + "', '" + E9 + "', '" + S9 + "', '" + E10 + "', '" + S10 + "',"
                + "                      '" + E11 + "', '" + S11 + "', '" + E12 + "', '" + S12 + "', '" + E13 + "', '" + S13 + "', '" + E14 + "', '" + S14 + "', '" + E15 + "', '" + S15 + "', " + TOTAL_DIARIO + ", " + FOLGA + ", " + IDCARTAO + ")";

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

    public static String insereCartao(String dtInclusao, String processo, String junta, String reclamante, String reclamada, String datainicio, 
                                      String datafinal, String feriadosm, String feriadosn, String feriadosnf, int numcolunas, String status) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO CARTOES (DT_INCLUSAO, PROCESSO, JUNTA, RECLAMANTE, RECLAMADA, DT_INICIO, DT_FINAL, FERIADO_MUN, FERIADO_NAC, "
                + "FERIADO_NACF, NUM_COLUNAS, STATUS)\n"
                + "OUTPUT INSERTED.PROTOCOLO\n"
                + "values('" + dtInclusao + "', '" + processo + "', '" + junta + "', '" + reclamante + "', '" + reclamada + "', '" + datainicio + "', "
                + "'" + datafinal + "', '" + feriadosm + "', '" + feriadosn + "', '" + feriadosnf + "', '" + numcolunas + "', '" + status + "')";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("PROTOCOLO");
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

    public static String[] selectCartao(String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = " ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ";

        String query = "SELECT C.DT_INCLUSAO, C.PROCESSO, C.JUNTA, C.RECLAMANTE, C.RECLAMADA, C.DT_INICIO, C.DT_FINAL, C.FERIADO_MUN, C.FERIADO_NAC, C.FERIADO_NACF,\n"
                + "E.SEM, E.SEG, E.TER, E.QUA, E.QUI, E.SEX, E.SAB, E.DOM, C.NUM_COLUNAS, PRESCRICAO, DT_AJUIZAMENTO \n"
                + "FROM CARTOES C \n"
                + "LEFT JOIN EXCEDENTES E\n"
                + "ON E.ID_CARTAO=C.PROTOCOLO\n"
                + "WHERE C.PROTOCOLO = " + idCartao;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado = rs.getString("DT_INCLUSAO") + ";;"
                        + rs.getString("PROCESSO") + ";;"
                        + rs.getString("JUNTA") + ";;"
                        + rs.getString("RECLAMANTE") + ";;"
                        + rs.getString("RECLAMADA") + ";;"
                        + rs.getString("DT_INICIO") + ";;"
                        + rs.getString("DT_FINAL") + ";;"
                        + rs.getString("FERIADO_MUN") + ";;"
                        + rs.getString("FERIADO_NAC") + ";;"
                        + rs.getString("FERIADO_NACF") + ";;"
                        + rs.getString("SEM") + ";;"
                        + rs.getString("SEG") + ";;"
                        + rs.getString("TER") + ";;"
                        + rs.getString("QUA") + ";;"
                        + rs.getString("QUI") + ";;"
                        + rs.getString("SEX") + ";;"
                        + rs.getString("SAB") + ";;"
                        + rs.getString("DOM") + ";;"
                        + rs.getString("NUM_COLUNAS") + ";;"
                        + rs.getString("PRESCRICAO") + ";;"
                        + rs.getString("DT_AJUIZAMENTO");
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
    
    public static String[] selectExcedentes(String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = " ;; ;; ;; ;; ;; ;; ";

        String query = "SELECT SEM, SEG, TER, QUA, QUI, SEX, SAB, DOM \n"
                + "FROM EXCEDENTES WHERE ID_CARTAO = " + idCartao;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado = rs.getString("SEM") + ";;"
                        + rs.getString("SEG") + ";;"
                        + rs.getString("TER") + ";;"
                        + rs.getString("QUA") + ";;"
                        + rs.getString("QUI") + ";;"
                        + rs.getString("SEX") + ";;"
                        + rs.getString("SAB") + ";;"
                        + rs.getString("DOM") + ";;";
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
    
    public static String[] selectExcSem(String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT EXC_SEM FROM DIGITACAO_SEMANA WHERE ID_CARTAO = " + idCartao;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("EXC_SEM") + ";;";
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
    
    public static String[] selectExcMes(String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT EXC_MES FROM DIGITACAO_MES WHERE ID_CARTAO = " + idCartao;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("EXC_MES") + ";;";
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
    
    public static String[] selectIdSem(String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT ID_SEMANA FROM DIGITACAO_SEMANA WHERE ID_CARTAO = " + idCartao;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("ID_SEMANA") + ";;";
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
    
    public static String[] selectIdMes(String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resultado = "";

        String query = "SELECT ID_MES FROM DIGITACAO_MES WHERE ID_CARTAO = " + idCartao;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado += rs.getString("ID_MES") + ";;";
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
    
    public static void alteraCartao(String dtInclusao, String processo, String junta, String reclamante, String reclamada, String datainicio, String datafinal, String feriadosm, String feriadosn, String feriadosnf, String idcartao, String status) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE CARTOES SET DT_INCLUSAO = '" + dtInclusao + "', PROCESSO = '" + processo + "', JUNTA = '" + junta + "', "
                + "RECLAMANTE = '" + reclamante + "', RECLAMADA = '" + reclamada + "', DT_INICIO = '" + datainicio + "', DT_FINAL = '" + datafinal + "', "
                + "FERIADO_MUN = '" + feriadosm + "', FERIADO_NAC = '" + feriadosn + "', FERIADO_NACF = '" + feriadosnf + "', STATUS = '" + status + "'\n"
                + "WHERE PROTOCOLO = ?";
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
    
    public static void alteraSemana(String idSem, int numSem, String valorSem, String idCartao, String excSem) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
            PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE DIGITACAO_SEMANA SET NUM_SEMANA = " + numSem + ", VALOR_SEMANA = " + valorSem + ", EXC_SEM = '" + excSem + "'\n"
                + "WHERE ID_SEMANA = ? AND ID_CARTAO = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idSem);
            ps.setString(2, idCartao);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void alteraMes(String idMes, int numMes, String valorMes, String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE DIGITACAO_MES SET NUM_MES = " + numMes + ", VALOR_MES = " + valorMes + "\n"
                + "WHERE ID_MES = ? AND ID_CARTAO = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idMes);
            ps.setString(2, idCartao);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void alteraStatus(String status, String idCartao) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE CARTOES SET STATUS = ? WHERE PROTOCOLO = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, idCartao);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}