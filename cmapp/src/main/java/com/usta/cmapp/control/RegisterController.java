package com.usta.cmapp.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.muestra_mysql.model.Empresa;
import com.muestra_mysql.model.Enfermedade;
import com.muestra_mysql.model.Persona;
import com.muestra_mysql.model.Registro;
import com.usta.cmapp.constants.EnumDataBase;
import com.usta.coviapp_ejb.service.ServiceDao;
import com.usta.cmapp.control.PrincipalController;

@ManagedBean(name = "regis")
@SessionScoped
public class RegisterController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Properties properties;
	private Registro registerPerson;
	private String userAccess;
	private String usLoged;
	private List<Enfermedade> listDisease;
	private List<Empresa> listCompany;
	private String document;
	private boolean visiblePanel;
	private Persona personRegis;
	private Float imc;
	private String resultaImc;
	private String documento;

	@EJB
	private ServiceDao<Object> servicesDao;

	public RegisterController() {
		try {
			properties = new Properties();
			userAccess = null;
			registerPerson = new Registro();
			documento = null;
			listDisease = new ArrayList<Enfermedade>();
			listCompany = new ArrayList<Empresa>();
			document = null;
			visiblePanel = false;
			imc = 0.0F;
			resultaImc = null;
			personRegis = new Persona();
			chargeProperties();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * init variables
	 */
	private void chargeProperties() {
		try {
			properties.load(RegisterController.class.getResourceAsStream("messages.properties"));
			userAccess = ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(LoginController.USER_AUTENTICH)).toUpperCase();
			usLoged = ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(LoginController.AUTH_KEY)).toUpperCase();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					properties.getProperty("errorGeneral"), properties.getProperty("errorCargaPropiedades")));
		}
	}

	@PostConstruct
	public void chargeData() {
		try {
			List<Object> diseaseTemp = servicesDao.findAll(Enfermedade.class, EnumDataBase.MYSQL.getId());
			for (Object o : diseaseTemp) {
				listDisease.add((Enfermedade) o);
			}

			List<Object> companyTemp = servicesDao.findAll(Empresa.class, EnumDataBase.MYSQL.getId());
			for (Object com : companyTemp) {
				listCompany.add((Empresa) com);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void validatePerson() {
		try {
			if ((documento != null && documento.equals("")) 
					&& (registerPerson.getIdEnfermedad() > 0)
					&& (registerPerson.getIdEmpresa() > 0)) {
				personRegis = (Persona) servicesDao.searchPersonByDocument(documento, EnumDataBase.MYSQL.getId());
				if (personRegis != null && !personRegis.equals("")) {
					registerPerson.setIdPersona(personRegis.getIdPersona());
					visiblePanel = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							properties.getProperty(""), properties.getProperty("")));
				}

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					properties.getProperty(""), properties.getProperty("")));
		}
	}

	public void regisInto() {
		try {
			if (personRegis.getPeso() > 0 && registerPerson.getTemperatura() > 0) {
				if (registerPerson.getTemperatura() < 38 && registerPerson.getTemperatura() > 35) {
					registerPerson.setIngreso(Byte.valueOf("0"));
					registerPerson.setFechaIngreso(new Date());
					registerPerson.setSintomas(Byte.valueOf("0"));
					servicesDao.create(registerPerson, EnumDataBase.MYSQL.getId());
					imc = Float.valueOf(personRegis.getPeso())
							/ Float.valueOf((float) Math.pow(Double.valueOf(personRegis.getEstatura()), 2));
					resultaImc = personRegis.getGenero().equals("male")
							? (imc > 25 ? "SOBREPESO".concat(imc.toString()) : "NORMAL".concat(imc.toString()))
							: (imc > 24 ? "SOBREPESO".concat(imc.toString()) : "NORMAL".concat(imc.toString()));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Getter y setters
	public Registro getRegisterPerson() {
		return registerPerson;
	}

	public void setRegisterPerson(Registro registerPerson) {
		this.registerPerson = registerPerson;
	}

	public String getUserAccess() {
		return userAccess;
	}

	public void setUserAccess(String userAccess) {
		this.userAccess = userAccess;
	}

	public String getUsLoged() {
		return usLoged;
	}

	public void setUsLoged(String usLoged) {
		this.usLoged = usLoged;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public boolean isVisiblePanel() {
		return visiblePanel;
	}

	public void setVisiblePanel(boolean visiblePanel) {
		this.visiblePanel = visiblePanel;
	}

	public Persona getPersonRegis() {
		return personRegis;
	}

	public void setPersonRegis(Persona personRegis) {
		this.personRegis = personRegis;
	}

	public List<Enfermedade> getListDisease() {
		return listDisease;
	}

	public void setListDisease(List<Enfermedade> listDisease) {
		this.listDisease = listDisease;
	}

	public List<Empresa> getListCompany() {
		return listCompany;
	}

	public void setListCompany(List<Empresa> listCompany) {
		this.listCompany = listCompany;
	}

	public Float getImc() {
		return imc;
	}

	public void setImc(Float imc) {
		this.imc = imc;
	}

	public String getResultaImc() {
		return resultaImc;
	}

	public void setResultaImc(String resultaImc) {
		this.resultaImc = resultaImc;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
