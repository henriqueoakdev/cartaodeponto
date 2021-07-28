/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Querys;

/**
 *
 * @vefificador de dados
 */
import java.sql.*;
import data.*;

public class VerifyDB {

    public static void insereFuncionario(String id, String desc) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO FUNCIONARIO "
                + "VALUES(" + id + ",'" + desc + "')";
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

    public static void insereVerba(String id, String desc) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO VERBA VALUES('" + id + "','" + desc + "')";

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

    public static void inserePagamento(String matricula, String codverba, String data, String valor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO PAGAMENTO VALUES('" + data + "',1," + valor + "," + matricula + "," + codverba + ")";

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

    public static boolean inserePagamento(String matricula, String codverba, String data, String valor, String frequencia) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        frequencia = frequencia.replace(",", ".");
        frequencia = frequencia.replace(" ", "");
        valor = valor.replace(" ", "");
        valor = valor.replace(",", ".");
        String query = "INSERT INTO PAGAMENTO VALUES('" + data + "'," + frequencia + "," + valor + "," + matricula + ",'" + codverba + "')";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            String erro = e.getMessage();
            System.out.println(e);
            if (!erro.equals("The statement did not return a result set.")) {
                insereErro("Erro ao inserir Pagamento: ", query + "----" + erro);
            }
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void insereErro(String arquivo, String mensagem) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO ERROR VALUES('" + arquivo + "','" + mensagem + "')";

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

    public static String pesquisaValoresTxt(String funcionario, String verba) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";

        String query = "SELECT CONVERT(DATE,MES,103) AS MES,SUM(HORA) as HORA,SUM(VALOR) AS VALOR FROM PAGAMENTO"
                + " WHERE FUNCIONARIO = " + funcionario
                + " AND VERBA like ('%" + verba + "%')"
                + " GROUP BY MES,FUNCIONARIO,VERBA"
                + " ORDER BY MES";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("MES") + ";;" + rs.getString("HORA") + ";;" + rs.getString("VALOR") + "----";
            }
            if (result.equals("")) {
                result = "00/0000;;0;;0----";
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            result = "00/0000;;0;;0----";
            return result;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String pesquisaValoresXls(String funcionario, String verba) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";

        String query = "SELECT CONVERT(DATE,MES,103) AS MES,SUM(HORA) as HORA,SUM(VALOR) AS VALOR FROM PAGAMENTO_EXCEL"
                + " WHERE FUNCIONARIO = " + funcionario
                + " AND VERBA like ('%" + verba + "%')"
                + " GROUP BY MES,FUNCIONARIO,VERBA"
                + " ORDER BY MES";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("MES") + ";;" + rs.getString("HORA") + ";;" + rs.getString("VALOR") + "----";
            }
            if (result.equals("")) {
                result = "00/0000;;0;;0----";
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            result = "00/0000;;0;;0----";
            return result;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String pesquisaValoresPdf(String funcionario, String verba) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";

        String query = "SELECT CONVERT(DATE,MES,103) AS MES,SUM(HORA) as HORA,SUM(VALOR) AS VALOR FROM PAGAMENTO_PDF"
                + " WHERE FUNCIONARIO = " + funcionario
                + " AND VERBA like ('%" + verba + "%')"
                + " GROUP BY MES,FUNCIONARIO,VERBA"
                + " ORDER BY MES";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("MES") + ";;" + rs.getString("HORA") + ";;" + rs.getString("VALOR") + "----";
            }
            if (result.equals("")) {
                result = "00/0000;;0;;0----";
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            result = "00/0000;;0;;0----";
            return result;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String pesquisaFuncionarioTxb(String funcionario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "NÃO ENCONTRADO";

        String query = "SELECT NOME FROM FUNCIONARIO"
                + " WHERE MATRICULA = " + funcionario;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("NOME");
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return result;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String pesquisaVerbaTxb(String verba) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "NÃO ENCONTRADO";

        String query = "SELECT DESCRICAO FROM VERBA"
                + " WHERE CODIGO = " + verba;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("DESCRICAO");
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return result;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String pesquisaFuncionarioXls(String funcionario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "NÃO ENCONTRADO";

        String query = "SELECT NOME FROM FUNCIONARIO_EXCEL"
                + " WHERE MATRICULA = " + funcionario;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("NOME");
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return result;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String pesquisaVerbaXls(String verba) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "NÃO ENCONTRADO";

        String query = "SELECT DESCRICAO FROM VERBA_EXCEL"
                + " WHERE CODIGO = " + verba;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("DESCRICAO");
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return result;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String pesquisaFuncionarioPdf(String funcionario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "NÃO ENCONTRADO";

        String query = "SELECT NOME FROM FUNCIONARIO_PDF"
                + " WHERE MATRICULA = " + funcionario;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("NOME");
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return result;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String pesquisaVerbaPdf(String verba) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "NÃO ENCONTRADO";

        String query = "SELECT DESCRICAO FROM VERBA_PDF"
                + " WHERE CODIGO = " + verba;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("DESCRICAO");
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return result;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] consultaFuncionarioTxb(String funcionario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";

        String query = "SELECT NOME,MATRICULA FROM FUNCIONARIO"
                + " WHERE NOME LIKE '%" + funcionario + "%'";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("MATRICULA") + ";;" + rs.getString("NOME") + "----";
            }
            if (result.equals("")) {
                result = "0;;NAO ENCONTRADO----";
            }
            return result.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return result.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] consultaVerbaTxb(String verba) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";

        String query = "SELECT CODIGO,DESCRICAO FROM VERBA"
                + " WHERE DESCRICAO like '%" + verba + "%'";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("CODIGO") + ";;" + rs.getString("DESCRICAO") + "----";
            }
            if (result.equals("")) {
                result = "0;;NAO ENCONTRADO----";
            }
            return result.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return result.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] consultaFuncionarioXls(String funcionario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";

        String query = "SELECT NOME,MATRICULA FROM FUNCIONARIO_EXCEL"
                + " WHERE NOME LIKE '%" + funcionario + "%'";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("MATRICULA") + ";;" + rs.getString("NOME") + "----";
            }
            if (result.equals("")) {
                result = "0;;NAO ENCONTRADO----";
            }
            return result.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return result.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] consultaVerbaXls(String verba) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";

        String query = "SELECT CODIGO,DESCRICAO FROM VERBA_EXCEL"
                + " WHERE DESCRICAO like '%" + verba + "%'";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("CODIGO") + ";;" + rs.getString("DESCRICAO") + "----";
            }
            if (result.equals("")) {
                result = "0;;NAO ENCONTRADO----";
            }
            return result.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return result.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] consultaFuncionarioPdf(String funcionario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";

        String query = "SELECT NOME,MATRICULA FROM FUNCIONARIO_PDF"
                + " WHERE NOME LIKE '%" + funcionario + "%'";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("MATRICULA") + ";;" + rs.getString("NOME") + "----";
            }
            if (result.equals("")) {
                result = "0;;NAO ENCONTRADO----";
            }
            return result.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return result.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String[] consultaVerbaPdf(String verba) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";

        String query = "SELECT CODIGO,DESCRICAO FROM VERBA_PDF"
                + " WHERE DESCRICAO like '%" + verba + "%'";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("CODIGO") + ";;" + rs.getString("DESCRICAO") + "----";
            }
            if (result.equals("")) {
                result = "0;;NAO ENCONTRADO----";
            }
            return result.split("----");
        } catch (SQLException e) {
            System.out.println(e);
            return result.split("----");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
