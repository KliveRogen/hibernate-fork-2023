package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Employer;

public class EmployerDao extends GenericDao {

  public EmployerDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * избежать org.hibernate.LazyInitializationException
   * Также в запрос должен передаваться параметр employerId
   * <p>
   * https://vladmihalcea.com/the-best-way-to-handle-the-lazyinitializationexception/
   */
  public Employer getEager(int employerId) {
    return getSession()
        .createQuery("from Employer employer join fetch employer.vacancies " +
            "where employer.id=:employerId", Employer.class)
        .setParameter("employerId", employerId)
        .getSingleResult();
  }

}
