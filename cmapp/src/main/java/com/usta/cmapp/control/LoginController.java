package com.usta.cmapp.control;

import java.io.Serializable;
import java.util.Properties;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.muestra_mysql.model.Login;
import com.muestra_postgres.model.LoginPostgres;
import com.usta.cmapp.constants.EnumDataBase;
import com.usta.coviapp_ejb.service.ServiceDao;

@ManagedBean(name = "login")
@SessionScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String USER_AUTENTICH = "user.app";

	public static final String AUTH_KEY = "app.user.name";

	private Properties properties;

	private String user;

	private String password;

	@EJB
	private ServiceDao<Login> serviceDaoMysql;

	@EJB
	private ServiceDao<com.muestra_postgres.model.LoginPostgres> serviceDaoPostgre;

	/**
	 * method constructor from class
	 */
	public LoginController() {
		super();
		properties = new Properties();
		try {
			properties.load(LoginController.class.getResourceAsStream("messages.properties")); // lee propiedades que
																								// existan en el archivo
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "NO PUEDE LEER ARCHIVOS DE PROPIEDADES"));
		}
	}

	@SuppressWarnings("unused")
	public String accessCheck() {
		String access = null;
		try {
			Login l = serviceDaoMysql.searchUser(user, password, EnumDataBase.MYSQL.getId());

//			if (!l.getIdLogin().equals("0")) {
			if (l.getIdLogin() > 0) {
				access = "/pages/principal";

				// subir
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, user);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(USER_AUTENTICH,
						l.getUsuario().trim());
			} else if (l == null) {
				com.muestra_postgres.model.LoginPostgres lpos = new com.muestra_postgres.model.LoginPostgres();

				lpos = serviceDaoPostgre.searchUser(user, password, EnumDataBase.POSTGRESQL.getId());

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, user);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(USER_AUTENTICH,
						lpos.getUsuario().trim());

				access = "/pages/principal";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						properties.getProperty("errorGeneral"), properties.getProperty("errorExistencia")));
				access = "/login/loginFail";
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					properties.getProperty("errorGeneral"), properties.getProperty("errorAutenticacion")));
			access = "/login/loginFail";
		}
		return access;
	}

	public void forgotPass() {
		try {
			Login login = serviceDaoMysql.searchUser(user, EnumDataBase.MYSQL.getId());

			if (login != null) {
				password = login.getClave().trim();
				setPassword(login.getClave().trim());
				FacesContext.getCurrentInstance()
						.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
								properties.getProperty("passMess"),
								properties.getProperty("passRecupera").concat("TU CONTRASEÑA ES: ").concat(password)));
			} else {
				LoginPostgres loginPostgres = serviceDaoPostgre.searchUser(user, EnumDataBase.POSTGRESQL.getId());
				if (loginPostgres != null) {
					password = loginPostgres.getClave().trim();
					setPassword(loginPostgres.getClave().trim());
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							properties.getProperty("passMess"),
							properties.getProperty("passRecupera").concat("TU CONTRASEÑA ES: ").concat(password)));
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							properties.getProperty("passMess"), properties.getProperty("errorExistencia")));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
