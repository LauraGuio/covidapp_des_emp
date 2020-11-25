package com.usta.cmapp.control;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
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

import org.primefaces.event.FlowEvent;

import com.muestra_mysql.model.Ciudade;
import com.muestra_mysql.model.Persona;
import com.muestra_mysql.model.TiposDocumento;
import com.usta.cmapp.constants.EnumDataBase;
import com.usta.cmapp.constants.EnumGender;
import com.usta.cmapp.constants.EnumRh;
import com.usta.coviapp_ejb.service.ServiceDao;

@ManagedBean(name = "principal")
@SessionScoped
public class PrincipalController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Properties properties;
	private String userAccess;
	private String usLoged;
	private final static String PAGE_PRINCIPAL = "../login/login.faces";

	private Persona newPerson;
	private List<TiposDocumento> typesDocuments;
	private List<Ciudade> listCities;
	private List<EnumRh> rhlist;
	private List<EnumGender> genderList;
	private TiposDocumento documentType;
	private Ciudade city;
	private String phone;

	@EJB
	private ServiceDao<Object> servicesDao;

	/**
	 * constructor class
	 */
	public PrincipalController() {
		try {
			properties = new Properties();
			userAccess = null;
			newPerson = new Persona();
			typesDocuments = new ArrayList<TiposDocumento>();
			listCities = new ArrayList<Ciudade>();
			rhlist = new ArrayList<EnumRh>();
			genderList = new ArrayList<EnumGender>();
			city = new Ciudade();
			documentType = new TiposDocumento();
			chargeProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * init variables
	 */
	private void chargeProperties() {
		try {
			properties.load(PrincipalController.class.getResourceAsStream("messages.properties"));
			userAccess = ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(LoginController.USER_AUTENTICH)).toUpperCase();
			usLoged = ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(LoginController.AUTH_KEY)).toUpperCase();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					properties.getProperty("errorGeneral"), properties.getProperty("errorCargaPropiedades")));
		}
	}

	/**
	 * este método se inicializa tanpronto se carga la clase y después que crea el
	 * contructor. NO recibe invocación de ningún cliente, el cliente es el mismo
	 * servidor cuando la aplicación es inicializada
	 */
	@PostConstruct
	public void chargeData() {
		try {
			List<Object> d = servicesDao.findAll(TiposDocumento.class, EnumDataBase.MYSQL.getId());
			for (Object o : d) {
				typesDocuments.add((TiposDocumento) o);
			}
			List<Object> c = servicesDao.findAll(Ciudade.class, EnumDataBase.MYSQL.getId());
			for (Object o : c) {
				listCities.add((Ciudade) o);
			}

			rhlist.add(EnumRh.ABNEG);
			rhlist.add(EnumRh.ABPOS);
			rhlist.add(EnumRh.ANEG);
			rhlist.add(EnumRh.APOS);
			rhlist.add(EnumRh.BNEG);
			rhlist.add(EnumRh.BPOS);
			rhlist.add(EnumRh.ONEG);
			rhlist.add(EnumRh.OPOS);

			genderList.add(EnumGender.FEMALE);
			genderList.add(EnumGender.MALE);
			genderList.add(EnumGender.OTHER);
			
			System.out.println(rhlist);
			System.out.println(genderList);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					properties.getProperty("errorGeneral"), properties.getProperty("errorCargaPropiedades")));
		}
	}

	/**
	 * valida para hacer el paso a la siguiente pestaña
	 * 
	 * @param event
	 * @return
	 */
	public String onFlowProcess(FlowEvent event) {
		String result = null;

		if (validateDataPerson()) {
			result = event.getNewStep();
		} else {
			result = event.getOldStep();
		}

		return result;

	}

	/**
	 * Valida los registros que sean de caracter obligatorio y valida que la fecha
	 * de nacimiento sea menor al día de hoy
	 * 
	 * @return
	 */
	private boolean validateDataPerson() {
		return ((newPerson.getDocumento() != null && !newPerson.getDocumento().equals(""))
				&& (newPerson.getPrimerNombre() != null && !newPerson.getPrimerNombre().equals(""))
				&& (newPerson.getPrimerApellido() != null && !newPerson.getPrimerApellido().equals(""))
				&& (newPerson.getFechaNacimiento() != null && !newPerson.getFechaNacimiento().equals(""))
				&& (newPerson.getTelefono() != null && !newPerson.getTelefono().equals(""))
				&& newPerson.getFechaNacimiento().before(new Date()));
	}

	/**
	 * metodo que crea la persona desde el formulario
	 */
	public void createPerson() {
		try {
			if (validateDataPerson()) {
//				newPerson.setTelefono(BigDecimal.valueOf(phone));
				servicesDao.create(newPerson, EnumDataBase.MYSQL.getId());

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						properties.getProperty("creacionUser"), properties.getProperty("")));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						properties.getProperty("errorCreacionUs"), properties.getProperty("")));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					properties.getProperty("errorCreacionUs"), properties.getProperty("")));
		}
	}

	public void logout() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("principal");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.remove(LoginController.USER_AUTENTICH);
			FacesContext.getCurrentInstance().getExternalContext().redirect(PAGE_PRINCIPAL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Setters y getters */
	public String getUserAccess() {
		return userAccess;
	}

	public void setUserAccess(String userAccess) {
		this.userAccess = userAccess;
	}

	public Persona getNewPerson() {
		return newPerson;
	}

	public void setNewPerson(Persona newPerson) {
		this.newPerson = newPerson;
	}

	public List<TiposDocumento> getTypesDocuments() {
		return typesDocuments;
	}

	public void setTypesDocuments(List<TiposDocumento> typesDocuments) {
		this.typesDocuments = typesDocuments;
	}

	public List<Ciudade> getListCities() {
		return listCities;
	}

	public void setListCities(List<Ciudade> listCities) {
		this.listCities = listCities;
	}

	public List<EnumRh> getRhlist() {
		return rhlist;
	}

	public void setRhlist(List<EnumRh> rhlist) {
		this.rhlist = rhlist;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsLoged() {
		return usLoged;
	}

	public void setUsLoged(String usLoged) {
		this.usLoged = usLoged;
	}

	public Ciudade getCity() {
		return city;
	}

	public void setCity(Ciudade city) {
		this.city = city;
	}

	public TiposDocumento getDocumentType() {
		return documentType;
	}

	public void setDocumentType(TiposDocumento documentType) {
		this.documentType = documentType;
	}

	public List<EnumGender> getGenderList() {
		return genderList;
	}

	public void setGenderList(List<EnumGender> genderList) {
		this.genderList = genderList;
	}

}
