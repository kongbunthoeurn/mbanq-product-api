package com.mbanq.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.mbanq.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	Date date = new Date();
	long time = date.getTime();
	Timestamp myDate = new Timestamp(time);

	@Autowired
	private SessionFactory sessionFactory;

	@Override

	public List<Product> listProduct() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> theQuery = currentSession.createQuery("from Product p where p.created_by=:create_by",
				Product.class);

		theQuery.setParameter("create_by", this.getLoginName());
		List<Product> products = theQuery.getResultList();
		return products;

	}

	@Override
	public void createProduct(Product product) {
		product.setCreated_date(myDate);
		product.setMod_date(myDate);
		product.setMod_by(this.getLoginName());
		product.setCreated_by(this.getLoginName());
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(product);

	}

	@Override
	public void updateProduct(Product product) {
		product.setCreated_date(myDate);
		product.setMod_date(myDate);
		product.setMod_by(this.getLoginName());
		product.setCreated_by(this.getLoginName());
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.update(product);
	}

	@Override
	public boolean checkExistName(String name) {
		boolean result = false;
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> theQuery = currentSession.createQuery("from Product p where p.name=:pname", Product.class);
		theQuery.setParameter("pname", name);
		if (theQuery.uniqueResult() == null)
			result = true;
		else
			result = false;
		return result;
	}

	@Override
	public boolean checkExistNameUpdate(Product product) {
		boolean result = false;
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> theQuery = currentSession.createQuery("from Product p where p.name=:pname and p.id!=:pid",
				Product.class);
		theQuery.setParameter("pname", product.getName());
		theQuery.setParameter("pid", product.getId());
		if (theQuery.uniqueResult() == null)
			result = true;
		else
			result = false;
		return result;
	}

	@Override
	public void deleteProduct(Product product) {
		product.setCreated_by(this.getLoginName());
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(product);

	}

	@Override
	public boolean checkExistUpdateId(Product product) {
		boolean result = true;
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> theQuery = currentSession
				.createQuery("from Product p where p.created_by=:created_by and p.id=:pid", Product.class);
		theQuery.setParameter("created_by", this.getLoginName());
		theQuery.setParameter("pid", product.getId());
		if (theQuery.uniqueResult() == null)
			result = false;

		return result;
	}

	@Override
	public boolean checkExistUpdateIdFullUser(Product product) {
		boolean result = true;
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> theQuery = currentSession.createQuery("from Product p where p.id=:pid", Product.class);
		theQuery.setParameter("pid", product.getId());
		if (theQuery.uniqueResult() == null)
			result = false;

		return result;
	}

	@Override
	public boolean checkExistDeleteIdFullUser(Product product) {
		boolean result = true;
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> theQuery = currentSession.createQuery("from Product p where p.id=:pid", Product.class);
		theQuery.setParameter("pid", product.getId());
		if (theQuery.uniqueResult() == null)
			result = false;

		return result;
	}

	@Override
	public boolean checkExistDeleteId(Product product) {
		boolean result = true;
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> theQuery = currentSession
				.createQuery("from Product p where p.created_by=:created_by and p.id=:pid", Product.class);
		theQuery.setParameter("created_by", this.getLoginName());
		theQuery.setParameter("pid", product.getId());
		if (theQuery.uniqueResult() == null)
			result = false;
		return result;
	}

	public String getLoginName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
