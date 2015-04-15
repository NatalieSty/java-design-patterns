package com.iluwatar;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

public class SpellbookDaoImpl extends DaoBaseImpl<Spellbook> implements SpellbookDao {

	@Override
	public Spellbook findByName(String name) {
		Session session = getSession();
		Transaction tx = null;
		Spellbook result = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(persistentClass);
			criteria.add(Expression.eq("name", name));
			result = (Spellbook) criteria.uniqueResult();
			result.getSpells().size();
			result.getWizards().size();
			tx.commit();
		}
		catch (Exception e) {
			if (tx!=null) tx.rollback();
			throw e;
		}
		finally {
			session.close();
		}		
		return result;
	}

}
