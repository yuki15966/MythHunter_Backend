
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
 * Home object for domain model class DbEnemy.
 * @see gen.entities.DbEnemy
 * @author Hibernate Tools
 */
public class DbEnemyHome
{

	private static final Log log = LogFactory.getLog(DbEnemyHome.class);

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

	public void persist(DbEnemy transientInstance)
	{
		log.debug("persisting DbEnemy instance");
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

	public void attachDirty(DbEnemy instance)
	{
		log.debug("attaching dirty DbEnemy instance");
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

	public void attachClean(DbEnemy instance)
	{
		log.debug("attaching clean DbEnemy instance");
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

	public void delete(DbEnemy persistentInstance)
	{
		log.debug("deleting DbEnemy instance");
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

	public DbEnemy merge(DbEnemy detachedInstance)
	{
		log.debug("merging DbEnemy instance");
		try
		{
			DbEnemy result = (DbEnemy) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		}
		catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public DbEnemy findById(long id)
	{
		log.debug("getting DbEnemy instance with id: " + id);
		try
		{
			DbEnemy instance = (DbEnemy) sessionFactory.getCurrentSession().get("gen.entities.DbEnemy", id);
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

	public List<DbEnemy> findByExample(DbEnemy instance)
	{
		log.debug("finding DbEnemy instance by example");
		try
		{
			List<DbEnemy> results = (List<DbEnemy>) sessionFactory.getCurrentSession()
				.createCriteria("gen.entities.DbEnemy").add(create(instance)).list();
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
