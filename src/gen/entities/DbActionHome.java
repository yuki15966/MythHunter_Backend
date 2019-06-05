
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
 * Home object for domain model class DbAction.
 * @see gen.entities.DbAction
 * @author Hibernate Tools
 */
public class DbActionHome
{

	private static final Log log = LogFactory.getLog(DbActionHome.class);

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

	public void persist(DbAction transientInstance)
	{
		log.debug("persisting DbAction instance");
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

	public void attachDirty(DbAction instance)
	{
		log.debug("attaching dirty DbAction instance");
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

	public void attachClean(DbAction instance)
	{
		log.debug("attaching clean DbAction instance");
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

	public void delete(DbAction persistentInstance)
	{
		log.debug("deleting DbAction instance");
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

	public DbAction merge(DbAction detachedInstance)
	{
		log.debug("merging DbAction instance");
		try
		{
			DbAction result = (DbAction) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		}
		catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public DbAction findById(long id)
	{
		log.debug("getting DbAction instance with id: " + id);
		try
		{
			DbAction instance = (DbAction) sessionFactory.getCurrentSession().get("gen.entities.DbAction", id);
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

	public List<DbAction> findByExample(DbAction instance)
	{
		log.debug("finding DbAction instance by example");
		try
		{
			List<DbAction> results = (List<DbAction>) sessionFactory.getCurrentSession()
				.createCriteria("gen.entities.DbAction").add(create(instance)).list();
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
