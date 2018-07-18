
package gen.entities;

// Generated Jul 4, 2017 3:25:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DbUser.
 * @see gen.entities.DbUser
 * @author Hibernate Tools
 */
public class DbUserHome
{

	private static final Log log = LogFactory.getLog(DbUserHome.class);

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

	public void persist(DbUser transientInstance)
	{
		log.debug("persisting DbUser instance");
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

	public void attachDirty(DbUser instance)
	{
		log.debug("attaching dirty DbUser instance");
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

	public void attachClean(DbUser instance)
	{
		log.debug("attaching clean DbUser instance");
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

	public void delete(DbUser persistentInstance)
	{
		log.debug("deleting DbUser instance");
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

	public DbUser merge(DbUser detachedInstance)
	{
		log.debug("merging DbUser instance");
		try
		{
			DbUser result = (DbUser) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		}
		catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public DbUser findById(long id)
	{
		log.debug("getting DbUser instance with id: " + id);
		try
		{
			DbUser instance = (DbUser) sessionFactory.getCurrentSession().get("gen.entities.DbUser", id);
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

	public List<DbUser> findByExample(DbUser instance)
	{
		log.debug("finding DbUser instance by example");
		try
		{
			List<DbUser> results = (List<DbUser>) sessionFactory.getCurrentSession()
				.createCriteria("gen.entities.DbUser").add(create(instance)).list();
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
