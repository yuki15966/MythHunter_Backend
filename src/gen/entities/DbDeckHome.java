
package gen.entities;

// Generated Jul 27, 2018 5:45:55 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DbDeck.
 * @see gen.entities.DbDeck
 * @author Hibernate Tools
 */
public class DbDeckHome
{

	private static final Log log = LogFactory.getLog(DbDeckHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory()
	{
		try
		{
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		}
		catch (Exception e)
		{
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(DbDeck transientInstance)
	{
		log.debug("persisting DbDeck instance");
		try
		{
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		}
		catch (RuntimeException re)
		{
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DbDeck instance)
	{
		log.debug("attaching dirty DbDeck instance");
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		}
		catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DbDeck instance)
	{
		log.debug("attaching clean DbDeck instance");
		try
		{
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		}
		catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DbDeck persistentInstance)
	{
		log.debug("deleting DbDeck instance");
		try
		{
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		}
		catch (RuntimeException re)
		{
			log.error("delete failed", re);
			throw re;
		}
	}

	public DbDeck merge(DbDeck detachedInstance)
	{
		log.debug("merging DbDeck instance");
		try
		{
			DbDeck result = (DbDeck) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		}
		catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public DbDeck findById(long id)
	{
		log.debug("getting DbDeck instance with id: " + id);
		try
		{
			DbDeck instance = (DbDeck) sessionFactory.getCurrentSession().get("gen.entities.DbDeck", id);
			if (instance == null)
			{
				log.debug("get successful, no instance found");
			}
			else
			{
				log.debug("get successful, instance found");
			}
			return instance;
		}
		catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DbDeck> findByExample(DbDeck instance)
	{
		log.debug("finding DbDeck instance by example");
		try
		{
			List<DbDeck> results = (List<DbDeck>) sessionFactory.getCurrentSession()
				.createCriteria("gen.entities.DbDeck").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		}
		catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			throw re;
		}
	}
}
