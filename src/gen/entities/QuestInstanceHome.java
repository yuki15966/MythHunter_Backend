
package gen.entities;

// Generated Jul 27, 2018 10:14:50 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class QuestInstance.
 * @see gen.entities.QuestInstance
 * @author Hibernate Tools
 */
public class QuestInstanceHome
{

	private static final Log log = LogFactory.getLog(QuestInstanceHome.class);

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

	public void persist(QuestInstance transientInstance)
	{
		log.debug("persisting QuestInstance instance");
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

	public void attachDirty(QuestInstance instance)
	{
		log.debug("attaching dirty QuestInstance instance");
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

	public void attachClean(QuestInstance instance)
	{
		log.debug("attaching clean QuestInstance instance");
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

	public void delete(QuestInstance persistentInstance)
	{
		log.debug("deleting QuestInstance instance");
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

	public QuestInstance merge(QuestInstance detachedInstance)
	{
		log.debug("merging QuestInstance instance");
		try
		{
			QuestInstance result = (QuestInstance) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		}
		catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public QuestInstance findById(long id)
	{
		log.debug("getting QuestInstance instance with id: " + id);
		try
		{
			QuestInstance instance = (QuestInstance) sessionFactory.getCurrentSession().get(
				"gen.entities.QuestInstance", id);
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

	public List<QuestInstance> findByExample(QuestInstance instance)
	{
		log.debug("finding QuestInstance instance by example");
		try
		{
			List<QuestInstance> results = (List<QuestInstance>) sessionFactory.getCurrentSession()
				.createCriteria("gen.entities.QuestInstance").add(create(instance)).list();
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
