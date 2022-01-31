package ar.com.zeni.security;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.db.wP.ConnectionPropertiesWordPress;
import ar.com.zeni.db.wP.ZeniQueryExcecutorWordPress;
import ar.com.zeni.db.wP.ZeniQueryExcecutorWordPress.ResulsetObjectBuilder;
import ar.com.zeni.security.UsuarioAuthenticationUtil.UserData;

/**
 * @author ogagliano
 *
 */
public class UsuarioWordPressUtil {

	/**
	 * Inserta el usuario en WordPress
	 *
	 * @param ud
	 * @throws ZeniDBExeption
	 */
	public void addUSer(UserData ud) throws ZeniDBExeption {
		// Se inserta el usuario en wp_users
		insertWPUsers(ud.getUsername(), ud.getEmail(), ud.getNombre(), ud.getApellido());
		BigDecimal id = BigDecimal.ZERO;
		try {
			// Se obtiene el id del usuario insertado en wp_users
			id = getIdUserWPUsers(ud.getUsername());
			// Se inserta la metadata para el usuario en wp_usermeta.
			insertWpUserMeta(id, ud.getUsername(), ud.getNombre(), ud.getApellido());
		} catch (ZeniDBExeption e) {
			ZeniContextServer.getInstance().printErrorLog("Error al insertar el usuario Username{" + ud.getUsername()
					+ "} ID{" + id + "}. Se elimina el usuario de la tabla. WP_USERS");
			deleteWPUsers(ud.getUsername(), id);
			throw e;
		}

	}

	/**
	 *
	 * @param username
	 * @param id
	 */
	private void deleteWPUsers(String username, BigDecimal id) {
		try {

			String delete = "DELETE FROM wp_users WHERE user_login =" + stringValue(username);

			if (id != null && id.compareTo(BigDecimal.ZERO) > 1)
				delete += " AND ID= " + id;

			ZeniQueryExcecutorWordPress.excecuteUpdate(delete);

		} catch (Exception e) {
			ZeniContextServer.getInstance().printErrorLog(
					"Error al eliminar el usuario Username{" + username + "} ID{" + id + "} de la tabla. WP_USERS");
		}

	}

	/**
	 *
	 * @param id
	 * @param username
	 * @param nombre
	 * @param apellido
	 */
	private void insertWpUserMeta(BigDecimal id, String username, String nombre, String apellido)
			throws ZeniDBExeption {

		StringBuffer sql = new StringBuffer();

		try {

		// @formatter:off
		sql.append(" INSERT INTO wp_usermeta (user_id, meta_key, meta_value) VALUES ");
		sql.append(" ("+stringValue(""+id)+", 'nickname',   "+stringValue(username) +"), ");
		sql.append(" ("+stringValue(""+id)+", 'first_name', "+stringValue(nombre).toUpperCase()   +"), ");
		sql.append(" ("+stringValue(""+id)+", 'last_name',  "+stringValue(apellido).toUpperCase() +"), ");
		sql.append(" ("+stringValue(""+id)+", 'description', ''), ");
		sql.append(" ("+stringValue(""+id)+", 'rich_editing', 'true'), ");
		sql.append(" ("+stringValue(""+id)+", 'comment_shortcuts', 'false'), ");
		sql.append(" ("+stringValue(""+id)+", 'admin_color', 'fresh'), ");
		sql.append(" ("+stringValue(""+id)+", 'use_ssl', '0'), ");
		sql.append(" ("+stringValue(""+id)+", 'show_admin_bar_front', 'true'), ");
		sql.append(" ("+stringValue(""+id)+", 'wp_capabilities', 'a:1:{s:10:\"subscriber\";b:1;}'), ");
		sql.append(" ("+stringValue(""+id)+", 'wp_user_level', '0'), ");
		sql.append(" ("+stringValue(""+id)+", 'dismissed_wp_pointers', ''); ");
		// @formatter:on

			ZeniQueryExcecutorWordPress.excecuteUpdate(sql.toString());

		} catch (Exception e) {
			throw new ZeniDBExeption(sql.toString(), e);
		}

	}

	/**
	 *
	 * @param username
	 * @param email
	 * @param nombre
	 * @param nombre2
	 */
	private void insertWPUsers(String username, String email, String nombre, String apellido) throws ZeniDBExeption {

		StringBuffer sql = new StringBuffer();
		try {

			String pass = ZeniContextServer.getInstance().getPropertyWP(ConnectionPropertiesWordPress.WP_USERS_PASS);
			String displayName = (nombre == null ? "" : nombre.toUpperCase());
			displayName += displayName.equals("") ? (apellido == null ? "." : " " + apellido.toUpperCase())
					: (apellido == null ? "." : " " + apellido.toUpperCase());

			//// @formatter:off
			sql.append("INSERT INTO wp_users (user_login, user_pass, user_nicename, user_email, ");
			sql.append(" user_url, user_registered, user_activation_key, user_status, display_name) ");
			sql.append("VALUES ( ");
			sql.append(stringValue(username) + ", ");
			sql.append("MD5(" + stringValue(pass) + "), ");
			sql.append(stringValue(username) + ", ");
			sql.append(stringValue(email) + ", ");
			sql.append("'', ");
			sql.append(stringValue(DateUtil.ToString.yyyyMMddHHmmss(new Date()))+ ", ");
			sql.append("'', ");
			sql.append(stringValue("0") + ", ");
			sql.append(stringValue(displayName) + ") ");
			// @formatter:on

			ZeniQueryExcecutorWordPress.excecuteUpdate(sql.toString());

		} catch (Exception e) {
			throw new ZeniDBExeption(sql.toString(), e);
		}

	}

	/**
	 *
	 * @param username
	 * @return
	 */
	private BigDecimal getIdUserWPUsers(String username) throws ZeniDBExeption {

		String query = " SELECT ID FROM wp_users WHERE user_nicename = " + stringValue(username);

		try {
			final List<BigDecimal> data = new ArrayList<BigDecimal>();
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					if (rset.next()) {
						try {
							data.add(rset.getBigDecimal("ID"));
						} catch (java.lang.Exception ex) {
							throw new SQLException("Error Query ID user.", ex);
						}
					}
				}
			};

			ZeniQueryExcecutorWordPress.excecuteSelect(query, resb);

			if (data.size() == 0)
				throw new Exception("No se encontro el usuario (" + username + ") en  wp_users.");
			else
				return data.get(0);

		} catch (Exception e) {
			throw new ZeniDBExeption(query, e);
		}
	}

	/**
	 *
	 * @param ud
	 * @throws ZeniDBExeption
	 */
	public void deleteUSer(UserData ud) throws ZeniDBExeption {

		try {
			ZeniContextServer.getInstance().printInfoLog("Comienza a eliminar usuario en WP_USERS");

			// Se obtiene el id del usuario insertado en wp_users
			BigDecimal id = getIdUserWPUsers(ud.getUsername());

			int result = deleteWPUserMeta(id);
			if (result > 0)
				deleteWPUsers(ud.getUsername(), id);
		} catch (ZeniDBExeption e) {
			ZeniContextServer.getInstance().printErrorLog(
					"Error al eliminar el usuario Username{" + ud.getUsername() + "} de la tabla. WP_USERS");
			//throw e;
		}

	}

	/**
	 *
	 * @param id
	 * @return
	 */
	private int deleteWPUserMeta(BigDecimal id) {
		int result = -1;
		try {
			String delete = "DELETE FROM wp_usermeta where user_id =" + id;
			result = ZeniQueryExcecutorWordPress.excecuteUpdate(delete);

		} catch (Exception e) {
			ZeniContextServer.getInstance()
					.printErrorLog("Error al eliminar el usuario id{" + id + "} de la tabla. WP_USERMETA");
		}
		return result;
	}

	/**
	 *
	 * @param data
	 * @return
	 */
	private String stringValue(String data) {

		if (data == null)
			return "''";
		else
			return "'".concat(data.trim()).concat("'");
	}

	/**
	 *
	 * @param username
	 * @param ud
	 */
	public void updateUSer(String username, UserData ud) throws ZeniDBExeption {

		BigDecimal id = BigDecimal.ZERO;
		try {
			// Se obtiene el id del usuario insertado en wp_users
			id = getIdUserWPUsers(username);

			if (id != null && id.compareTo(BigDecimal.ZERO) > 0) {
				updateWPUsers(ud.getUsername(), ud.getEmail(), ud.getNombre(), ud.getApellido(), id);
				updateWpUserMeta(ud.getUsername(), ud.getNombre(), ud.getApellido(), id);
			} else
				ZeniContextServer.getInstance().printErrorLog("No se encontro el usuario Username{" + username
						+ "} new userName{" + ud.getUsername() + "} id{" + id + "}.");
			// Se inserta la metadata para el usuario en wp_usermeta.
			insertWpUserMeta(id, ud.getUsername(), ud.getNombre(), ud.getApellido());
		} catch (ZeniDBExeption e) {
			ZeniContextServer.getInstance().printErrorLog("Error al actualizar  el usuario Username{" + username
					+ "} new userName{" + ud.getUsername() + "}. " + e.getMessage());
			throw e;
		}

	}

	/**
	 *
	 * @param username
	 * @param nombre
	 * @param apellido
	 * @param id
	 */
	private void updateWpUserMeta(String username, String nombre, String apellido, BigDecimal id)
			throws ZeniDBExeption {
		String sql = new String();

		try {

		// @formatter:off
		sql = " UPDATE wp_usermeta SET meta_value = "+stringValue(username)+" WHERE meta_key = 'nickname' AND user_id =" + id;
		ZeniQueryExcecutorWordPress.excecuteUpdate(sql);
		sql =(" UPDATE wp_usermeta SET meta_value = "+stringValue(nombre).toUpperCase()+ " WHERE meta_key = 'first_name' AND user_id =" + id);
		ZeniQueryExcecutorWordPress.excecuteUpdate(sql);
		sql =(" UPDATE wp_usermeta SET meta_value = "+stringValue(apellido).toUpperCase()+ " WHERE meta_key = 'last_name' AND user_id =" + id);
		ZeniQueryExcecutorWordPress.excecuteUpdate(sql);
//		sql =(" UPDATE wp_usermeta SET meta_value = "+stringValue(username)+" WHERE meta_key = 'nickname' AND user_id =" + id);
		// @formatter:on

			ZeniQueryExcecutorWordPress.excecuteUpdate(sql);

		} catch (Exception e) {
			throw new ZeniDBExeption(sql, e);
		}

	}

	/**
	 *
	 * @param username
	 * @param email
	 * @param nombre
	 * @param apellido
	 * @param id
	 */
	private void updateWPUsers(String username, String email, String nombre, String apellido, BigDecimal id)
			throws ZeniDBExeption {

		StringBuffer sql = new StringBuffer();
		try {
			String displayName = (nombre == null ? "" : nombre.toUpperCase());
			displayName += displayName.equals("") ? (apellido == null ? "." : " " + apellido.toUpperCase())
					: (apellido == null ? "." : " " + apellido.toUpperCase());

		//// @formatter:off
		sql.append("UPDATE  wp_users  SET user_login ="+stringValue(username)+", ");
		sql.append(" user_nicename ="+stringValue(username)+" , user_email = "+stringValue(email)+", ");
		sql.append(" display_name ="+stringValue(displayName));
		sql.append(" WHERE ID = " + id);
		// @formatter:on

			ZeniQueryExcecutorWordPress.excecuteUpdate(sql.toString());

		} catch (Exception e) {
			throw new ZeniDBExeption(sql.toString(), e);
		}

	}

}
