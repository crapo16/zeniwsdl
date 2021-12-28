package ar.com.zeni.admin.app;

import ar.com.zeni.security.UsuarioAuthenticationUtil.UserData;

public class UserDataImplementation implements UserData {
	String username;
	String passdb;
	String saltdb;
	String clienteid;
	String apellido;
	String nombre;
	String email;
	public UserDataImplementation(String clienteid, String username, String passdb, String saltdb, String nombre, String apellido, String email) {
		this.username = username;
		this.passdb=passdb;
		this.saltdb=saltdb;
		this.clienteid = clienteid;
		this.apellido=apellido;
		this.nombre=nombre;
		this.email=email;
	}
	public void setDBPassword(String sPasswordToStore) {
		this.passdb=sPasswordToStore;
	}
	/**
	 * Encuentra el salt para el usuario y password
	 * @param usuario
	 * @return
	 */
	public String getDBSalt() {
		return this.saltdb;
	}
	/**
	 * Encuentra el password para el usuario
	 * @param usuario
	 * @return
	 */
	public String getDBPassword() {
		return this.passdb;
	}
	/**
	 * Encuentra el username para el usuario
	 * @param usuario
	 * @return
	 */
	public String getUsername() {
		return this.username;
	}
	public String getClienteid() {
		return clienteid;
	}
	public String getApellido() {
		return apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public void setUsername(String sPasswordToStore) {
		this.username=sPasswordToStore;
	}
	@Override
	public void setClienteid(String sPasswordToStore) {
		this.clienteid=sPasswordToStore;
	}
}
