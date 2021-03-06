
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
 * Home object for domain model class DbHtmlObject.
 * @see gen.entities.DbHtmlObject
 * @author Hibernate Tools
 */
public class DbHtmlObjectHome
{

	private static final Log log = LogFactory.getLog(DbHtmlObjectHome.class);

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

	public void persist(DbHtmlObject transientInstance)
	{
		log.debug("persisting DbHtmlObject instance");
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

	public void attachDirty(DbHtmlObject instance)
	{
		log.debug("attaching dirty DbHtmlObject instance");
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

	public void attachClean(DbHtmlObject instance)
	{
		log.debug("attaching clean DbHtmlObject instance");
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

	public void delete(DbHtmlObject persistentInstance)
	{
		log.debug("deleting DbHtmlObject instance");
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

	public DbHtmlObject merge(DbHtmlObject detachedInstance)
	{
		log.debug("merging DbHtmlObject instance");
		try
		{
			DbHtmlObject result = (DbHtmlObject) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		}
		catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public DbHtmlObject findById(long id)
	{
		log.debug("getting DbHtmlObject instance with id: " + id);
		try
		{
			DbHtmlObject instance = (DbHtmlObject) sessionFactory.getCurrentSession().get("gen.entities.DbHtmlObject",
				id);
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

	public List<DbHtmlObject> findByExample(DbHtmlObject instance)
	{
		log.debug("finding DbHtmlObject instance by example");
		try
		{
			List<DbHtmlObject> results = (List<DbHtmlObject>) sessionFactory.getCurrentSession()
				.createCriteria("gen.entities.DbHtmlObject").add(create(instance)).list();
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
