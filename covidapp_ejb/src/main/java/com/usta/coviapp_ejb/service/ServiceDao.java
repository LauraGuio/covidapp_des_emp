package com.usta.coviapp_ejb.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.muestra_mysql.model.Login;
import com.usta.covidapp_ejb.bean.DaoObjectMySql;
import com.usta.covidapp_ejb.bean.DaoObjectPostgresql;
import com.usta.covidapp_ejb.interfaces.IObjectQuerPostgres;
import com.usta.covidapp_ejb.interfaces.IObjectQueryMysql;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ServiceDao<T> {

	@EJB
	IObjectQueryMysql<T> mysqlDao;

	@EJB
	IObjectQuerPostgres<T> postgresDao;

	/**
	 * método que se encarga de crear el objeto a persistir
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void create(T t, int db) throws Exception {
		switch (db) {
		case 1: // persiste MySQL
			mysqlDao.create(t);
			break;
		case 2: // persiste PostGreSQL
			postgresDao.create(t);
			break;
		}

	}

	/**
	 * Método encargado de buscar y listar todos los objetos de la consulta deseada
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> findAll(T t, int db) throws Exception {

		List<T> object = new ArrayList<T>();
		switch (db) {
		case 1: // persiste MySQL
			object = mysqlDao.findAll((Class<T>) t);
			break;
		case 2: // persiste PostGreSQL
			object = postgresDao.findAll((Class<T>) t);
			break;
		}
		return object;
	}

	/**
	 * Método de encontrar por el id el objeto buscado
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */

	public T findById(Integer id, Class<T> object, int db) throws Exception { // El db es para saber en que motor se

		T o = null;
		switch (db) {
		case 1: // persiste MySQL
			o = mysqlDao.findById(id, object);
			break;
		case 2: // persiste PostGreSQL
			o = postgresDao.findById(id, object);
			break;
		}
		return o;
	}

	/**
	 * Método para realizar cualquier cambio o modificación sobre un dato de un
	 * objeto
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void update(T t, int db) throws Exception {
		switch (db) {
		case 1: // persiste MySQL
			mysqlDao.update(t);
			break;
		case 2: // persiste PostGreSQL
			postgresDao.update(t);
			break;
		}
	}

	/**
	 * Elimina un objeto dependiendo el id que se solicité borrar
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(Integer id, Class<T> object, int db) throws Exception {
		switch (db) {
		case 1: // persiste MySQL
			mysqlDao.delete(id, object);
			break;
		case 2: // persiste PostGreSQL
			postgresDao.delete(id, object);
			break;
		}
	}

	/**
	 * Busca un usuario según credenciales
	 * 
	 * @param user
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public T searchUser(String user, String password, int bd) throws Exception {
		T t = null;
		switch (bd) {
		case 1:
			t = mysqlDao.searchUser(user, password);
			break;
		case 2:
			t = postgresDao.searchUser(user, password);
			break;
		}
		return t;
	}

	/**
	 * metodo que se encarga de buscar el usuario para traer su contrasenia
	 */
	public T searchUser(String user, int bd) throws Exception {
		T t = null;
		switch (bd) {
		case 1:
			t = mysqlDao.requiredPass(user);
			break;
		case 2:
			t = postgresDao.requiredPass(user);
			break;
		}
		return t;
	}

	public T searchPersonByDocument(String document, int bd) throws Exception {
		T t = null;
		switch (bd) {
		case 1:
			t = mysqlDao.searchPersonByDocument(document);
			break;
		case 2:
			t = postgresDao.searchPersonByDocument(document);
			break;
		}
		return t;
	}
}
