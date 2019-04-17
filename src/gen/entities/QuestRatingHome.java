
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
 * Home object for domain model class QuestRating.
 * @see gen.entities.QuestRating
 * @author Hibernate Tools
 */
public class QuestRatingHome
{

	private static final Log log = LogFactory.getLog(QuestRatingHome.class);

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

	public void persist(QuestRating transientInstance)
	{
		log.debug("persisting QuestRating instance");
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

	public void attachDirty(QuestRating instance)
	{
		log.debug("attaching dirty QuestRating instance");
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

	public void attachClean(QuestRating instance)
	{
		log.debug("attaching clean QuestRating instance");
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

	public void delete(QuestRating persistentInstance)
	{
		log.debug("deleting QuestRating instance");
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

	public QuestRating merge(QuestRating detachedInstance)
	{
		log.debug("merging QuestRating instance");
		try
		{
			QuestRating result = (QuestRating) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		}
		catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public QuestRating findById(long id)
	{
		log.debug("getting QuestRating instance with id: " + id);
		try
		{
			QuestRating instance = (QuestRating) sessionFactory.getCurrentSession().get("gen.entities.QuestRating", id);
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

	public List<QuestRating> findByExample(QuestRating instance)
	{
		log.debug("finding QuestRating instance by example");
		try
		{
			List<QuestRating> results = (List<QuestRating>) sessionFactory.getCurrentSession()
				.createCriteria("gen.entities.QuestRating").add(create(instance)).list();
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
