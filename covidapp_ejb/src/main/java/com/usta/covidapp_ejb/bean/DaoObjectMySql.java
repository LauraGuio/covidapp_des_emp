package com.usta.covidapp_ejb.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.muestra_mysql.model.Login;
import com.muestra_mysql.model.Persona;
import com.usta.covidapp_ejb.interfaces.IObjectQueryMysql;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DaoObjectMySql<T> implements IObjectQueryMysql<T> {
//Definir si se va para MYSQL o PostGres

	@PersistenceContext(unitName = "muestra_mysql") // Se define a que motor de base de datos se va a trabajar
	EntityManager mysqlEntity;// Define los métodos que permiten las transacciones

	@Override
	public void create(T t) throws Exception {
		mysqlEntity.persist(t);
	}

	@Override
	public List<T> findAll(Class<T> t) throws Exception {
		List<T> object = new ArrayList<T>();
		Query querySql = mysqlEntity.createNamedQuery(t.getSimpleName() + ".FIND_ALL"); // Armo la consulta
		object = querySql.getResultList(); // trae datos de consulta
		return object;
	}

	@Override
	public T findById(Integer id, Class<T> object) throws Exception {
		T t = null;
		t = mysqlEntity.find(object, id);
		return t;
	}

	@Override
	public void update(T t) throws Exception {
		mysqlEntity.merge(t);
	}

	@Override
	public void delete(Integer id, Class<T> object) throws Exception {
		T t = findById(id, object);

		if (t != null) {
			mysqlEntity.remove(t);
		}
	}

	@Override
	public T searchUser(String user, String password) throws Exception {
		T t = null;
		Query q = mysqlEntity.createNamedQuery(Login.FIND_USER_CREDENTIALS_MYSQL);
		q.setParameter("us", user);
		q.setParameter("ps", password);

		try {
			t = (T) q.getSingleResult();
		} catch (NoResultException e) {
			t = null;
		}
		return t;
	}

	@Override
	public T requiredPass(String user) throws Exception {
		T t = null;

		Query q = mysqlEntity.createNamedQuery(Login.FIND_USER);
		q.setParameter("user", user);

		try {
			t = (T) q.getSingleResult();
		} catch (NoResultException e) {
			t = null;
		}
		return t;
	}

	@Override
	public T searchPersonByDocument(String documento) throws Exception {
		T t = null;
		Query q = mysqlEntity.createNamedQuery(Persona.FIND_BY_DOCUMENT);
		q.setParameter("doc", documento);
		try {
			t = (T) q.getSingleResult();
		} catch (Exception e) {
			t = null;
		}
		return t;
	}

}
