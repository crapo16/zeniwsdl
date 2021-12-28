package ar.com.zeni.db.wP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniBaseExeption;
import ar.com.zeni.common.exceptions.ZeniDBExeption;

/**
 *
 * @author ogagliano
 *
 */
public class ZeniQueryExcecutorWordPress {

    public interface ResulsetObjectBuilder {

        public void thisIsTheResulset(ResultSet rset) throws SQLException;
    }

    /**
     * Ejecuta el select pasado por parametro e invoca al objeto
     * {@link ResulsetObjectBuilder} para utilizar en el resultado.
     *
     * @param select
     * @param resb
     * @throws ZeniDBExeption
     */
    public static void excecuteSelect(final String select, final ResulsetObjectBuilder resb) throws ZeniDBExeption {
        Connection conn = null;
        try {
            ZeniContextServer.getInstance().printQuery(select);
            conn = ZeniContextServer.getInstance().getConnectionWP().getConnection();
            PreparedStatement slstmt = null;
            try {
                slstmt = conn.prepareStatement(select);
                ResultSet rset = slstmt.executeQuery();
                resb.thisIsTheResulset(rset);
            } catch (final SQLException e) {
                throw new ZeniDBExeption(select, e);
            } finally {
                try {
                    slstmt.close();
                } catch (final Exception e) {
                    ZeniContextServer.getInstance().printErrorLog("Cant close rset: " + select);
                }
            }
        } catch (final SQLException e) {
            throw new ZeniDBExeption(select, e);
        } finally {
            try {
                conn.close();
            } catch (final Exception e) {
                ZeniContextServer.getInstance().printErrorLog("Cant conn rset: " + select);
            }
        }
    }

    /**
     * Ejecuta la dml pasada por marametro.
     *
     * @param query
     * @throws ZeniBaseExeption
     * @throws ZeniDBExeption
     */
    public static int excecuteUpdate(final String query) throws ZeniDBExeption {
        Connection conn = null;
        int result = -1;
        try {
            ZeniContextServer.getInstance().printQuery(query);
            conn = ZeniContextServer.getInstance().getConnectionWP().getConnection();
            PreparedStatement slstmt = null;
            try {
                slstmt = conn.prepareStatement(query);
                result = slstmt.executeUpdate();
            } catch (final SQLException e) {
                throw new ZeniDBExeption(query, e);
            } finally {
                try {
                    slstmt.close();
                } catch (final Exception e) {
                    ZeniContextServer.getInstance().printErrorLog("Cant close rset: " + query);
                }
            }
        } catch (final SQLException e) {
            throw new ZeniDBExeption(query, e);
        } finally {
            try {
                conn.close();
            } catch (final Exception e) {
                ZeniContextServer.getInstance().printErrorLog("Cant conn rset: " + query);
            }
        }
        return result;
    }
}
