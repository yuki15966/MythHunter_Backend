import gen.entities.Card;
import gen.entities.CardHome;
import gen.entities.DbAction;
import gen.entities.DbActionHome;
import gen.entities.DbCardImage;
import gen.entities.DbCardImageHome;
import gen.entities.DbDeck;
import gen.entities.DbDeckHome;
import gen.entities.DbEnemy;
import gen.entities.DbEnemyHome;
import gen.entities.DbHtmlObject;
import gen.entities.DbHtmlObjectHome;
import gen.entities.DbRandomEnemy;
import gen.entities.DbRandomEnemyHome;
import gen.entities.DbUser;
import gen.entities.DbUserHome;
import gen.entities.MapPosition;
import gen.entities.Marker;
import gen.entities.MarkerHome;
import gen.entities.Quest;
import gen.entities.QuestHome;
import gen.entities.QuestInstance;
import gen.entities.QuestInstanceHome;
import gen.entities.QuestRating;
import gen.entities.QuestRatingHome;
import gen.entities.TreePart;
import gen.entities.TreePartHome;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.ejb.Stateless;
import javax.jws.WebService;
import main.entities.Action;
import main.entities.CardImage;
import main.entities.CardType;
import main.entities.Deck;
import main.entities.EditorQuest;
import main.entities.Enemy;
import main.entities.HtmlObject;
import main.entities.LongToIntEntry;
import main.entities.RandomEnemy;
import main.entities.TreePartLazy;
import main.entities.TreePartType;
import main.entities.User;
import main.helper.FileTransferer;
import main.helper.FileTransfererImpl;
import main.helper.Transaction;
import main.integration.IntegratePicture;
import main.integration.IntegrationConfigParams;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Stateless
@WebService(portName = "BackendPort", serviceName = "BackendService", targetNamespace = "http://backend.com/wsdl", endpointInterface = "IBackend")
public class Backend implements IBackend
{

	// private static final SessionFactory sessionFactory = new
	// Configuration().configure().buildSessionFactory();
	private static final SessionFactory sessionFactory;

	protected static final long BEGIN_MONEY = 100;
	private static final String INTEGRATION_PATH = "/home/server/workspace/Integration/";
	private static final String FILE_PATH = "/home/server/workspace/Backend/WebContent/uploads/";

	static
	{
		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");

		try
		{
			sessionFactory = new Configuration().configure().buildSessionFactory();
			// final Context context =
			// EJBContainer.createEJBContainer().getContext();
			//
			// final Timer timer = (Timer)
			// context.lookup("java:global/schedule-methods/Timer");
			// timer.createRandomEnemies();

		}
		catch (Throwable ex)
		{
			System.err.println("Initial SessionFactory creation failed." + ex);

			throw new ExceptionInInitializerError(ex);

		}
	}

	public static final DbUserHome userDao = new DbUserHome();

	public static final DbDeckHome deckDao = new DbDeckHome();

	public static final CardHome cardDao = new CardHome();

	public static final DbCardImageHome cardImageDao = new DbCardImageHome();

	public static final DbActionHome actionDao = new DbActionHome();

	public static final QuestHome questDao = new QuestHome();
	public static final QuestInstanceHome questInstanceDao = new QuestInstanceHome();

	public static final MarkerHome markerDao = new MarkerHome();

	public static final DbHtmlObjectHome htmlDao = new DbHtmlObjectHome();

	public static final TreePartHome treePartDao = new TreePartHome();

	public static final QuestRatingHome questRatingDao = new QuestRatingHome();

	public static final DbEnemyHome dbEnemyDao = new DbEnemyHome();

	public static final DbRandomEnemyHome dbRandomEnemyDao = new DbRandomEnemyHome();

	@Override
	public QuestRating addQuestRating(final QuestRating rating)
	{
		System.out.println("[" + new Date() + "] addQuestRating(rating: " + rating + ")");
		Transaction<QuestRating> transaction = new Transaction<QuestRating>(sessionFactory)
		{

			@Override
			protected QuestRating doInNewTransaction()
			{
				rating.setId(0);
				questRatingDao.persist(rating);
				Quest quest = questDao.findById(rating.getQuestId());

				String hql = "from " + QuestRating.class.getName() + " where questId = :id";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("id", rating.getQuestId());

				List<QuestRating> ratings = query.list();
				float difficultyRating = 0;
				float qualityRating = 0;
				for (QuestRating questRating : ratings)
				{
					difficultyRating += questRating.getDifficultyRating();
					qualityRating = questRating.getQualityRating();
				}
				difficultyRating /= ratings.size();
				qualityRating /= ratings.size();
				quest.setDifficultyRating(difficultyRating);
				quest.setQualityRating(qualityRating);
				questDao.merge(quest);

				return rating;
			}
		};

		return transaction.commit();
	}

	@Override
	public HtmlObject getHtml(final long id)
	{
		System.out.println("[" + new Date() + "] getHtml(id: " + id + ")");
		Transaction<HtmlObject> transaction = new Transaction<HtmlObject>(sessionFactory)
		{

			@Override
			protected HtmlObject doInNewTransaction()
			{
				DbHtmlObject html = htmlDao.findById(id);
				// System.out.println(new Date() +"-----version---:" +
				// html.getVersion());
				HtmlObject result = null;
				if (html != null)
				{
					result = new HtmlObject(html);
				}
				return result;
			}
		};

		HtmlObject result = transaction.commit();
		// System.out.println(new Date() +"getHtml(id: " + id + ") returns:" +
		// result);
		return result;
	}

	@Override
	public HtmlObject addHtml(final HtmlObject htmlObject)
	{
		System.out.println("[" + new Date() + "] addHtml(htmlObject: " + htmlObject + ")");
		Transaction<HtmlObject> transaction = new Transaction<HtmlObject>(sessionFactory)
		{

			@Override
			protected HtmlObject doInNewTransaction()
			{
				try
				{
					DbHtmlObject html = htmlObject.asDbHtmlObject();
					html.setId(0);
					htmlDao.persist(html);
					htmlObject.setId(html.getId());
					return htmlObject;

				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		};

		return transaction.commit();
	}

	@Override
	public HtmlObject updateHtml(final HtmlObject htmlObject)
	{
		System.out.println("[" + new Date() + "] updateHtml(htmlObject: " + htmlObject + ")");
		Transaction<HtmlObject> transaction = new Transaction<HtmlObject>(sessionFactory)
		{

			@Override
			protected HtmlObject doInNewTransaction()
			{
				try
				{
					DbHtmlObject html = htmlDao.findById(htmlObject.getId());
					DbHtmlObject htmlTemp = htmlObject.asDbHtmlObject();
					html.setAnswers(htmlTemp.getAnswers());
					html.setHtml(htmlTemp.getHtml());
					return htmlObject;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		};

		return transaction.commit();
	}

	private List<TreePart> getTreeParts(List<Long> ids)
	{
		System.out.println("[" + new Date() + "] getTreeParts(ids: " + ids + ")");
		List<TreePart> treeParts = new ArrayList<TreePart>();
		if (ids != null)
		{
			for (Long treePartId : ids)
			{
				treeParts.add(treePartDao.findById(treePartId));
			}
		}
		return treeParts;
	}

	@Override
	public TreePartLazy addTreePartLazy(final TreePartLazy part)
	{
		System.out.println("[" + new Date() + "] addTreePartLazy(part: " + part + ")");

		Transaction<TreePart> transaction = new Transaction<TreePart>(sessionFactory)
		{

			@Override
			protected TreePart doInNewTransaction()
			{
				part.setId(0);
				List<TreePart> successors = getTreeParts(part.getSuccessorIds());
				Marker marker = null;
				if (part.getType() == TreePartType.Marker)
				{
					marker = markerDao.findById(part.getMarkerId());
					if (marker == null)
					{
						throw new RuntimeException("MarkerTreePart without marker == null is not allowed!");
					}
				}
				TreePart treePart = new TreePart(part.getPosition(), part.isFinished(), part.isActive(),
					part.isOpened(), part.getExecutedAt(), successors, marker, part.getQuestInstanceId(),
					part.getType(), part.isHighlightedInvisibeMarker());

				treePartDao.persist(treePart);
				return treePart;
			}
		};

		TreePart treePart = transaction.commit();
		return new TreePartLazy(treePart);
	}

	@Override
	public void deleteTreePart(final long id)
	{
		System.out.println("[" + new Date() + "] deleteTreePart(id: " + id + ")");
		Transaction<TreePart> transaction = new Transaction<TreePart>(sessionFactory)
		{

			@Override
			protected TreePart doInNewTransaction()
			{

				TreePart part = treePartDao.findById(id);
				if (part != null)
				{
					treePartDao.delete(part);
				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void deleteMarker(final long id)
	{
		System.out.println("[" + new Date() + "] deleteMarker(id: " + id + ")");
		Transaction<Marker> transaction = new Transaction<Marker>(sessionFactory)
		{

			@Override
			protected Marker doInNewTransaction()
			{
				Marker marker = markerDao.findById(id);
				if (marker != null)
				{
					Long markerHtmlId = marker.getHtmlId();
					try
					{
						markerDao.delete(marker);
						DbHtmlObject markerHtml = htmlDao.findById(markerHtmlId);
						htmlDao.delete(markerHtml);
					}
					catch (RuntimeException re)
					{
						throw re;
					}
				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void clearDB() // TODO remove. only for testing
	{
		System.out.println("[" + new Date() + "] clearDB");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{
			@Override
			protected Object doInNewTransaction()
			{
				String sql = "SELECT count(*) FROM truncate_tables('DB_ADMIN');";
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				query.list();
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public User login(final String unserName, final String password)
	{
		System.out.println("[" + new Date() + "] login(unserName: " + unserName + " ,password: " + password + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				String hql = "from " + DbUser.class.getName() + " where name = :userName and password = :password";

				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setString("userName", unserName);
				query.setString("password", password);

				return (DbUser) query.uniqueResult();
			}
		};

		DbUser dbUser = transaction.commit();
		if (dbUser == null)
		{
			return null;
		}
		else
		{
			return new User((DbUser) transaction.commit());
		}
	}

	@Override
	public void setUserKmWalked(final long id, final double kmWalked)
	{
		System.out.println("[" + new Date() + "] setUserKmWalked(id: " + id + " ,kmWalked: " + kmWalked + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);

				user.setKmWalked(kmWalked);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setUserTutorialPlayed(final long id, final boolean tutorialPlayed)
	{
		System.out
			.println(new Date() + "setUserTutorialPlayed(id: " + id + " ,tutorialPlayed: " + tutorialPlayed + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);

				user.setTutorialPlayed(tutorialPlayed);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setUserWonFightsCount(final long id, final long count)
	{
		System.out.println("[" + new Date() + "] setUserWonFightsCount(id: " + id + " ,count: " + count + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);

				user.setWonFightsCount(count);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void updateUserDeckIds(final long id, final List<Long> deckIds)
	{
		System.out.println("[" + new Date() + "] updateUserDeckIds(id: " + id + " ,deckIds: " + deckIds + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);
				user.setDeckIds(deckIds);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void addDeckToUser(final long userId, final long deckId)
	{
		System.out.println("[" + new Date() + "] addDeckToUser(userId: " + userId + " ,deckId: " + deckId + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);
				user.getDeckIds().add(deckId);
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void removeDeckFromUser(final long userId, final long deckId)
	{
		System.out.println("[" + new Date() + "] removeDeckFromUser(userId: " + userId + " ,deckId: " + deckId + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);
				user.getDeckIds().remove(deckId);
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void updateUserCardIds(final long id, final List<LongToIntEntry> cardIds)
	{
		System.out.println("[" + new Date() + "] updateUserCardIds(id: " + id + " ,cardIds: " + cardIds + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);

				Map<Long, Integer> ids = new HashMap<>();
				for (LongToIntEntry longToIntEntry : cardIds)
				{
					ids.put(longToIntEntry.getKey(), longToIntEntry.getValue());
				}
				user.setCardIds(ids);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void addCreatedCardToUser(final long userId, final long cardId)
	{
		System.out.println("[" + new Date() + "] addCreatedCardToUser(userId: " + userId + " ,cardId: " + cardId + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);
				user.getCreatedCardIds().add(cardId);
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void removeCreatedCardFromUser(final long userId, final long cardId)
	{
		System.out.println("[" + new Date() + "] removeCreatedCardFromUser(userId: " + userId + " ,cardId: " + cardId
			+ ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);
				user.getCreatedCardIds().remove(cardId);
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setUserStartedFightsCount(final long id, final long count)
	{
		System.out.println("[" + new Date() + "] setUserStartedFightsCount(id: " + id + " ,count: " + count + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);
				user.setStartedFightsCount(count);
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setUserFoundLocationsCount(final long id, final long count)
	{
		System.out.println("[" + new Date() + "] setUserFoundLocationsCount(id: " + id + " ,count: " + count + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);

				user.setFoundLocationsCount(count);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setUserMoney(final long id, final long money)
	{
		System.out.println("[" + new Date() + "] setUserMoney(id: " + id + " ,money: " + money + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);

				user.setMoney(money);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setQuestInstanceFailCount(final long id, final long count)
	{
		System.out.println("[" + new Date() + "] setQuestInstanceFailCount(id: " + id + " ,count: " + count + ")");
		Transaction<QuestInstance> transaction = new Transaction<QuestInstance>(sessionFactory)
		{

			@Override
			protected QuestInstance doInNewTransaction()
			{
				QuestInstance questInstance = questInstanceDao.findById(id);

				questInstance.setFailCount(count);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setQuestInstanceFightSkipped(final long id, final boolean skipped)
	{
		System.out.println("[" + new Date() + "] setQuestInstanceFightSkipped(id: " + id + " ,skipped: " + skipped
			+ ")");
		Transaction<QuestInstance> transaction = new Transaction<QuestInstance>(sessionFactory)
		{

			@Override
			protected QuestInstance doInNewTransaction()
			{
				QuestInstance questInstance = questInstanceDao.findById(id);

				questInstance.setFightSkipped(skipped);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setQuestInstanceHighlighted(final long id, final boolean highlighted)
	{
		System.out
			.println(new Date() + "setQuestInstanceHighlighted(id: " + id + " ,highlighted: " + highlighted + ")");
		Transaction<QuestInstance> transaction = new Transaction<QuestInstance>(sessionFactory)
		{

			@Override
			protected QuestInstance doInNewTransaction()
			{
				QuestInstance questInstance = questInstanceDao.findById(id);

				questInstance.setHighlighted(highlighted);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setQuestInstanceVisibleOnMap(final long id, final boolean visibleOnMap)
	{
		System.out.println("[" + new Date() + "] setQuestInstanceVisibleOnMap(id: " + id + " ,visibleOnMap: "
			+ visibleOnMap + ")");
		Transaction<QuestInstance> transaction = new Transaction<QuestInstance>(sessionFactory)
		{

			@Override
			protected QuestInstance doInNewTransaction()
			{
				QuestInstance questInstance = questInstanceDao.findById(id);

				questInstance.setVisibleOnMap(visibleOnMap);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setUserAnsweredQuestionsCount(final long id, final long count)
	{
		System.out.println("[" + new Date() + "] setUserAnsweredQuestionsCount(id: " + id + " ,count: " + count + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);

				user.setAnsweredQuestionsCount(count);
				userDao.merge(user);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setUserTaskCount(final long id, final long count)
	{
		System.out.println("[" + new Date() + "] setUserTaskCount(id: " + id + " ,count: " + count + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);

				user.setTaskCount(count);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void register(final String username, final String password)
	{
		System.out.println("[" + new Date() + "] register(username: " + username + " ,password: " + password + ")");

		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				checkUserDoesNotexist(username, true);

				DbUser user_ = new DbUser(new ArrayList<Long>(), new ArrayList<Long>(), username, password,
					BEGIN_MONEY, true, new ArrayList<Long>(), new ArrayList<Long>(), 0, 0, 0, 0, 0, 0, false, "",
					new HashMap<Long, Integer>(), new ArrayList<Long>());
				userDao.persist(user_);

				Deck deck = doCreateStandartDeck();
				DbDeck dbDeck = deck.asDbDeck();
				deckDao.persist(dbDeck);
				deck.setId(dbDeck.getId());
				user_.getDeckIds().add(deck.getId());
				for (LongToIntEntry entry : deck.getCardIds())
				{
					user_.getCardIds().put(entry.getKey(), entry.getValue());
				}
				return null;
			}

		};

		transaction.commit();
	}

	@Override
	public void setRotateMapForUser(final long userId, final boolean rotateMap)
	{
		System.out.println("[" + new Date() + "] setRotateMapForUser(userId: " + userId + " ,rotateMap: " + rotateMap
			+ ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				userDao.findById(userId).setRotateMap(rotateMap);
				return null;
			}

		};

		transaction.commit();
	}

	private boolean checkUserDoesNotexist(String userName, boolean throwException)
	{
		String hql = "from " + DbUser.class.getName() + " where name = :userName";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("userName", userName);

		if (query.uniqueResult() != null)
		{
			if (throwException)
			{
				throw new RuntimeException("User with name " + userName + " already exists!");
			}
			else
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public User addUser(final User user)
	{
		System.out.println("[" + new Date() + "] addUser(user: " + user + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user_ = null;

				if (checkUserDoesNotexist(user.getName(), false))
				{
					user.setMoney(BEGIN_MONEY);
					user_ = user.asDbUser();
					user_.setId(0);
					userDao.persist(user_);
				}

				return user_;
			}
		};
		DbUser dbUser = transaction.commit();
		if (dbUser == null)
		{
			return null;
		}
		else
		{
			return new User(dbUser);
		}
	}

	@Override
	public User updateUser(final User user)
	{
		System.out.println("[" + new Date() + "] updateUser(user: " + user + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				int version = userDao.findById(user.getId()).getVersion();
				DbUser user_ = user.asDbUser();
				user_.setVersion(version);
				userDao.merge(user_);
				return user_;
			}
		};

		return new User(transaction.commit());
	}

	@Override
	public void deleteUser(final long id)
	{
		System.out.println("[" + new Date() + "] deleteUser(id: " + id + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(id);
				if (user != null)
				{
					userDao.delete(user);
				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public Quest addQuest(final Quest quest)
	{
		System.out.println("[" + new Date() + "] addQuest(quest: " + quest + ")");
		Transaction<Quest> transaction = new Transaction<Quest>(sessionFactory)
		{

			@Override
			protected Quest doInNewTransaction()
			{
				quest.setId(0);
				Quest quest_ = quest;
				if (quest.isSubmitted())
				{
					validateQuest(quest);
				}
				questDao.persist(quest_);
				return quest_;
			}
		};

		return transaction.commit();
	}

	@Override
	public Quest updateQuest(final Quest quest)
	{
		System.out.println("[" + new Date() + "] updateQuest(quest: " + quest + ")");

		Transaction<Quest> transaction = new Transaction<Quest>(sessionFactory)
		{

			@Override
			protected Quest doInNewTransaction()
			{
				int version = questDao.findById(quest.getId()).getVersion();
				Quest quest_ = quest;
				quest_.setVersion(version);
				questDao.merge(quest_);
				return quest_;
			}
		};

		return transaction.commit();
	}

	@Override
	public Quest getQuest(final long id)
	{
		System.out.println("[" + new Date() + "] getQuest(id: " + id + ")");
		Transaction<Quest> transaction = new Transaction<Quest>(sessionFactory)
		{

			@Override
			protected Quest doInNewTransaction()
			{
				return questDao.findById(id);
			}
		};

		return (Quest) transaction.commit();
	}

	@Override
	public List<Quest> getQuests(final List<Long> ids)
	{
		System.out.println("[" + new Date() + "] getQuests(ids: " + ids + ")");
		Transaction<List<Quest>> transaction = new Transaction<List<Quest>>(sessionFactory)
		{

			@Override
			protected List<Quest> doInNewTransaction()
			{
				String hql = "from " + Quest.class.getName() + " where id in :ids";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameterList("ids", ids);

				return query.list();
			}
		};

		return (List<Quest>) transaction.commit();
	}

	public void deletePart(TreePart part)
	{

		List<TreePart> successors = part.getSuccessors();
		treePartDao.delete(part);

		for (TreePart successor : successors)
		{
			deletePart(successor);
		}
	}

	@Override
	public void deleteQuest(final long id)
	{
		System.out.println("[" + new Date() + "] deleteQuest(id: " + id + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				Quest quest = questDao.findById(id);

				if (quest != null)
				{
					Marker startMarker = quest.getStartMarker();
					Long htmlId = quest.getHtmlId();
					Long treeRootId = quest.getTreeRootId();

					try
					{
						questDao.delete(quest);

						try
						{
							TreePart rootPart = treePartDao.findById(treeRootId);
							deletePart(rootPart);
						}
						catch (RuntimeException re)
						{
							throw re;
						}

						Long startMarkerHtmlId = startMarker.getHtmlId();
						try
						{
							markerDao.delete(startMarker);
							DbHtmlObject startMarkerHtml = htmlDao.findById(startMarkerHtmlId);
							htmlDao.delete(startMarkerHtml);
						}
						catch (RuntimeException re)
						{
							throw re;
						}

						DbHtmlObject html = htmlDao.findById(htmlId);

						if (html != null)
						{
							htmlDao.delete(html);
						}
					}
					catch (RuntimeException re)
					{
						throw re;
					}

				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public List<Quest> getQuestsInRange(final MapPosition position, final float range, final List<Long> hideQuests)
	{
		System.out.println("[" + new Date() + "] getQuestsInRange(position: " + position + " ,range: " + range
			+ " ,hideQuests: " + hideQuests + ")");
		Transaction<List<Quest>> transaction = new Transaction<List<Quest>>(sessionFactory)
		{

			@Override
			protected List<Quest> doInNewTransaction()
			{
				float longMin = position.getLongitude() - range;
				float longMax = position.getLongitude() + range;
				float latMin = position.getLatitude() - range;
				float latMax = position.getLatitude() + range;

				String hql = "from " + Quest.class.getName() + " qu where "
					+ "qu.startMarker.position.longitude > :longMin and "
					+ "qu.startMarker.position.longitude < :longMax and "
					+ "qu.startMarker.position.latitude > :latMin and " + "qu.startMarker.position.latitude < :latMax"
					+ " and qu.submitted = true and qu.approved = true";
				if (hideQuests != null && !hideQuests.isEmpty())
				{
					hql += " and qu.id not in :ids";
				}

				Query query = sessionFactory.getCurrentSession().createQuery(hql);

				if (hideQuests != null && !hideQuests.isEmpty())
				{
					query.setParameterList("ids", hideQuests);
				}

				query.setFloat("longMin", longMin);
				query.setFloat("longMax", longMax);
				query.setFloat("latMin", latMin);
				query.setFloat("latMax", latMax);
				query.setMaxResults(100);

				String q = query.getQueryString();

				return query.list();
			}
		};

		return transaction.commit();
	}

	@Override
	public QuestInstance addQuestInstance(final QuestInstance questInstance)
	{
		System.out.println("[" + new Date() + "] addQuestInstance(questInstance: " + questInstance + ")");
		Transaction<QuestInstance> transaction = new Transaction<QuestInstance>(sessionFactory)
		{

			@Override
			protected QuestInstance doInNewTransaction()
			{
				questInstance.setId(0);
				QuestInstance instance = questInstance;
				questInstanceDao.persist(instance);
				return instance;
			}
		};
		return transaction.commit();
	}

	@Override
	public QuestInstance updateQuestInstance(final QuestInstance questInstance)
	{
		System.out.println("[" + new Date() + "] updateQuestInstance(questInstance: " + questInstance + ")");
		Transaction<QuestInstance> transaction = new Transaction<QuestInstance>(sessionFactory)
		{
			@Override
			protected QuestInstance doInNewTransaction()
			{
				int version = questInstanceDao.findById(questInstance.getId()).getVersion();
				QuestInstance instance = questInstance;
				instance.setVersion(version);
				questInstanceDao.merge(instance);
				return instance;
			}
		};
		return transaction.commit();
	}

	@Override
	public QuestInstance getQuestInstance(final long id)
	{
		System.out.println("[" + new Date() + "] getQuestInstance(id: " + id + ")");
		Transaction<QuestInstance> transaction = new Transaction<QuestInstance>(sessionFactory)
		{
			@Override
			protected QuestInstance doInNewTransaction()
			{
				return questInstanceDao.findById(id);
			}
		};

		return transaction.commit();
	}

	@Override
	public List<QuestInstance> getQuestInstances(final List<Long> ids)
	{
		System.out.println("[" + new Date() + "] getQuestInstances(ids: " + ids + ")");
		Transaction<List<QuestInstance>> transaction = new Transaction<List<QuestInstance>>(sessionFactory)
		{
			@Override
			protected List<QuestInstance> doInNewTransaction()
			{
				String hql = "from " + QuestInstance.class.getName() + " where id in :ids";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameterList("ids", ids);

				return query.list();
			}
		};

		return transaction.commit();
	}

	private void deleteSuccessors(TreePart treePart)
	{
		for (TreePart successor : treePart.getSuccessors())
		{
			deleteSuccessors(successor);
		}
		treePartDao.delete(treePart);
	}

	@Override
	public void deleteQuestInstance(final long id)
	{
		System.out.println("[" + new Date() + "] deleteQuestInstance(id: " + id + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{
			@Override
			protected Object doInNewTransaction()
			{
				QuestInstance questInstance = questInstanceDao.findById(id);

				TreePart part = treePartDao.findById(questInstance.getTreeInstanceRootId());
				deleteSuccessors(part);

				if (questInstance != null)
				{
					questInstanceDao.delete(questInstance);
				}
				return null;
			}
		};

		transaction.commit();
	}

	@Override
	public void setTreePartActive(final long id, final boolean active)
	{
		System.out.println("[" + new Date() + "] setTreePartActive(id: " + id + " ,active: " + active + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				TreePart treePart = treePartDao.findById(id);
				if (treePart != null)
				{
					treePart.setActive(active);
					treePartDao.merge(treePart);
				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void setTreePartFinished(final long id, final boolean finished)
	{
		System.out.println("[" + new Date() + "] setTreePartFinished(id: " + id + " ,finished: " + finished + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				TreePart treePart = treePartDao.findById(id);
				if (treePart != null)
				{
					treePart.setFinished(finished);
					treePartDao.merge(treePart);
				}
				return null;
			}
		};

		transaction.commit();
	}

	@Override
	public void setTreePartHighlightedInvisibleMarker(final long id, final boolean highlightedInvisibleMarker)
	{
		System.out.println("[" + new Date() + "] setTreePartHighlightedInvisibleMarker(id: " + id
			+ " ,highlightedInvisibleMarker: " + highlightedInvisibleMarker + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				TreePart treePart = treePartDao.findById(id);
				if (treePart != null)
				{
					treePart.setHighlightedInvisibeMarker(highlightedInvisibleMarker);
					treePartDao.merge(treePart);
				}
				return null;
			}
		};

		transaction.commit();
	}

	@Override
	public void setTreePartOpened(final long id, final boolean opened)
	{
		System.out.println("[" + new Date() + "] setTreePartOpened(id: " + id + " ,opened: " + opened + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				TreePart treePart = treePartDao.findById(id);
				if (treePart != null)
				{
					treePart.setOpened(opened);
					treePartDao.merge(treePart);
				}
				return null;
			}
		};

		transaction.commit();
	}

	@Override
	public void setTreePartExecutedAt(final TreePartLazy part)
	{
		System.out.println("[" + new Date() + "] setTreePartExecuted(id: " + part.getId() + " ,executedAt: "
			+ part.getExecutedAt() + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				TreePart treePart = treePartDao.findById(part.getId());
				if (treePart != null)
				{
					treePart.setExecutedAt(part.getExecutedAt());
					treePartDao.merge(treePart);
				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public List<Marker> getMarkers(final List<Long> ids)
	{
		System.out.println("[" + new Date() + "] getMarkers(ids: " + ids + ")");
		Transaction<List<Marker>> transaction = new Transaction<List<Marker>>(sessionFactory)
		{

			@Override
			protected List<Marker> doInNewTransaction()
			{
				String hql = "from " + Marker.class.getName() + " where id in :ids";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameterList("ids", ids);

				return query.list();
			}
		};
		return transaction.commit();
	}

	@Override
	public void setQuestInstanceOfTreePart(final long markerTreepartId, final long questInstanceId)
	{
		System.out.println("[" + new Date() + "] setQuestInstanceOfMarkerTreePart(markerTreepartId: "
			+ markerTreepartId + " ,questInstanceId: " + questInstanceId + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				TreePart markerTreePart = treePartDao.findById(markerTreepartId);
				QuestInstance instance = questInstanceDao.findById(questInstanceId);

				markerTreePart.setQuestInstanceId(instance.getId());

				treePartDao.merge(markerTreePart);
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public Deck addDeck(final Deck deck)
	{
		System.out.println("[" + new Date() + "] addDeck(deck: " + deck + ")");
		Transaction<DbDeck> transaction = new Transaction<DbDeck>(sessionFactory)
		{

			@Override
			protected DbDeck doInNewTransaction()
			{
				deck.setId(0);
				DbDeck deck_ = deck.asDbDeck();
				deckDao.persist(deck_);
				return deck_;
			}
		};

		return new Deck(transaction.commit());
	}

	@Override
	public Enemy addEnemy(final Enemy enemy)
	{
		System.out.println("[" + new Date() + "] addEnemy(deck: " + enemy + ")");
		Transaction<DbEnemy> transaction = new Transaction<DbEnemy>(sessionFactory)
		{

			@Override
			protected DbEnemy doInNewTransaction()
			{
				enemy.setId(0);
				DbEnemy dbEnemy = enemy.asDbEnemy();
				dbEnemyDao.persist(dbEnemy);
				return dbEnemy;
			}
		};

		return new Enemy(transaction.commit());
	}

	@Override
	public Deck updateDeck(final Deck deck)
	{
		System.out.println("[" + new Date() + "] updateDeck(deck: " + deck + ")");
		Transaction<DbDeck> transaction = new Transaction<DbDeck>(sessionFactory)
		{
			@Override
			protected DbDeck doInNewTransaction()
			{
				int version = deckDao.findById(deck.getId()).getVersion();
				DbDeck instance = deck.asDbDeck();
				instance.setVersion(version);
				deckDao.merge(instance);
				return instance;
			}
		};
		return new Deck(transaction.commit());
	}

	@Override
	public Enemy updateEnemy(final Enemy enemy)
	{
		System.out.println("[" + new Date() + "] updateEnemy(enemy: " + enemy + ")");
		Transaction<DbEnemy> transaction = new Transaction<DbEnemy>(sessionFactory)
		{
			@Override
			protected DbEnemy doInNewTransaction()
			{
				int version = dbEnemyDao.findById(enemy.getId()).getVersion();
				DbEnemy instance = enemy.asDbEnemy();
				instance.setVersion(version);
				dbEnemyDao.merge(instance);
				return instance;
			}
		};
		return new Enemy(transaction.commit());
	}

	@Override
	public Deck getDeck(final long id)
	{
		System.out.println("[" + new Date() + "] getDeck(id: " + id + ")");
		Transaction<DbDeck> transaction = new Transaction<DbDeck>(sessionFactory)
		{
			@Override
			protected DbDeck doInNewTransaction()
			{
				return deckDao.findById(id);
			}
		};
		DbDeck dbDeck = transaction.commit();
		if (dbDeck == null)
		{
			return null;
		}
		else
		{
			return new Deck(dbDeck);
		}
	}

	@Override
	public List<Deck> getDecks(final List<Long> ids)
	{
		System.out.println("[" + new Date() + "] getDecks(ids: " + ids + ")");
		Transaction<List<DbDeck>> transaction = new Transaction<List<DbDeck>>(sessionFactory)
		{
			@Override
			protected List<DbDeck> doInNewTransaction()
			{
				String hql = "from " + DbDeck.class.getName() + " where id in :ids";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameterList("ids", ids);

				return query.list();
			}
		};

		List<Deck> decks = new ArrayList<Deck>();
		for (DbDeck deck : transaction.commit())
		{
			decks.add(new Deck(deck));
		}
		return decks;
	}

	@Override
	public void deleteDeck(final long id)
	{
		System.out.println("[" + new Date() + "] deleteDeck(id: " + id + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				DbDeck deck = deckDao.findById(id);
				if (deck != null)
				{
					deckDao.delete(deck);
				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public List<Action> getAllActionsOfCardType(final CardType type)
	{
		System.out.println("[" + new Date() + "] getAllMonsterActions()");
		Transaction<List<Action>> transaction = new Transaction<List<Action>>(sessionFactory)
		{
			@Override
			protected List<Action> doInNewTransaction()
			{
				String hql = "from " + DbAction.class.getName() + " where forCardType = :type";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setInteger("type", type.ordinal());

				List<DbAction> dbActions = query.list();

				List<Action> actions = new ArrayList<Action>();
				for (DbAction action : dbActions)
				{
					actions.add(new Action(action));
				}

				return actions;
			}
		};

		return transaction.commit();
	}

	@Override
	public void deleteAction(final long id)
	{
		System.out.println("[" + new Date() + "] deleteMonsterAction(id: " + id + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				DbAction action = actionDao.findById(id);
				if (action != null)
				{
					actionDao.delete(action);
				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public List<Action> getAllActions()
	{
		System.out.println("[" + new Date() + "] getAllSpellActions()");
		Transaction<List<Action>> transaction = new Transaction<List<Action>>(sessionFactory)
		{
			@Override
			protected List<Action> doInNewTransaction()
			{
				String hql = "from " + Action.class.getName();
				Query query = sessionFactory.getCurrentSession().createQuery(hql);

				List<DbAction> dbActions = query.list();

				List<Action> actions = new ArrayList<Action>();
				for (DbAction action : dbActions)
				{
					actions.add(new Action(action));
				}

				return actions;
			}
		};

		return transaction.commit();
	}

	@Override
	public List<Action> getActions(final List<Long> ids)
	{
		System.out.println("[" + new Date() + "] getActions(ids: " + ids + ")");
		Transaction<List<Action>> transaction = new Transaction<List<Action>>(sessionFactory)
		{
			@Override
			protected List<Action> doInNewTransaction()
			{
				String hql = "from " + DbAction.class.getName() + " where id in :ids";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameterList("ids", ids);

				List<DbAction> dbActions = query.list();

				List<Action> actions = new ArrayList<Action>();
				for (DbAction action : dbActions)
				{
					actions.add(new Action(action));
				}

				return actions;
			}
		};

		return transaction.commit();
	}

	@Override
	public Action addAction(final Action action)
	{
		System.out.println("[" + new Date() + "] addAction(action: " + action + ")");
		Transaction<Action> transaction = new Transaction<Action>(sessionFactory)
		{

			@Override
			protected Action doInNewTransaction()
			{
				action.setId(0);
				DbAction action_ = action.asDbAction();
				actionDao.persist(action_);
				return new Action(action_);
			}
		};

		return transaction.commit();
	}

	@Override
	public Action updateAction(final Action action)
	{
		System.out.println("[" + new Date() + "] updateAction(action: " + action + ")");
		Transaction<Action> transaction = new Transaction<Action>(sessionFactory)
		{
			@Override
			protected Action doInNewTransaction()
			{

				DbAction dbAction = action.asDbAction();

				actionDao.merge(dbAction);
				return new Action(dbAction);
			}
		};
		return transaction.commit();
	}

	@Override
	public Action getAction(final long id)
	{
		System.out.println("[" + new Date() + "] getAction(id: " + id + ")");
		Transaction<Action> transaction = new Transaction<Action>(sessionFactory)
		{
			@Override
			protected Action doInNewTransaction()
			{
				return new Action(actionDao.findById(id));
			}
		};

		return transaction.commit();
	}

	@Override
	public Card addCard(final Card card)
	{
		System.out.println("[" + new Date() + "] addCard(card: " + card + ")");
		Transaction<Card> transaction = new Transaction<Card>(sessionFactory)
		{

			@Override
			protected Card doInNewTransaction()
			{
				card.setId(0);
				// DbCard card_ = card.asDbCard();
				cardDao.persist(card);
				return card;// new Card(card_, cardImageDao);
			}
		};

		return transaction.commit();
	}

	@Override
	public Card updateCard(final Card card)
	{
		System.out.println("[" + new Date() + "] updateCard(action: " + card + ")");
		Transaction<Card> transaction = new Transaction<Card>(sessionFactory)
		{
			@Override
			protected Card doInNewTransaction()
			{
				int version = cardDao.findById(card.getId()).getVersion();
				Card instance = card;
				// DbCard instance = card.asDbCard();
				instance.setVersion(version);
				cardDao.merge(instance);
				return card;// new Card(instance, cardImageDao);
			}
		};
		return transaction.commit();
	}

	@Override
	public Card getCard(final long id)
	{
		System.out.println("[" + new Date() + "] getCard(id: " + id + ")");
		Transaction<Card> transaction = new Transaction<Card>(sessionFactory)
		{
			@Override
			protected Card doInNewTransaction()
			{
				return cardDao.findById(id);// new Card(cardDao.findById(id),
											// cardImageDao);
			}
		};

		return transaction.commit();
	}

	@Override
	public List<Card> getCards(final List<Long> ids)
	{
		System.out.println("[" + new Date() + "] getCards(ids: " + ids + ")");
		Transaction<List<Card>> transaction = new Transaction<List<Card>>(sessionFactory)
		{
			@Override
			protected List<Card> doInNewTransaction()
			{
				String hql = "from " + Card.class.getName() + " where id in :ids";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameterList("ids", ids);

				List<Card> dbCards = query.list();
				// List<Card> cards = new ArrayList<Card>();
				// for (DbCard card : dbCards)
				// {
				// cards.add(new Card(card, cardImageDao));
				// }
				return dbCards;
			}
		};

		return transaction.commit();

	}

	@Override
	public void deleteCard(final long id)
	{
		System.out.println("[" + new Date() + "] deleteCard(id: " + id + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				Card card = cardDao.findById(id);
				if (card != null)
				{
					cardDao.delete(card);
				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public CardImage addCardImage(final CardImage image)
	{
		System.out.println("[" + new Date() + "] addCardImage(image: " + image + ")");
		Transaction<CardImage> transaction = new Transaction<CardImage>(sessionFactory)
		{

			@Override
			protected CardImage doInNewTransaction()
			{
				image.setId(0);
				DbCardImage image_ = image.asDbCardImage();
				cardImageDao.persist(image_);
				return new CardImage(image_);
			}
		};

		return transaction.commit();
	}

	@Override
	public CardImage updateCardImage(final CardImage image)
	{
		System.out.println("[" + new Date() + "] updateCardImage(image: " + image + ")");
		Transaction<CardImage> transaction = new Transaction<CardImage>(sessionFactory)
		{
			@Override
			protected CardImage doInNewTransaction()
			{
				// int version =
				DbCardImage cardImage = cardImageDao.findById(image.getId());
				DbCardImage instance = image.asDbCardImage();
				cardImage.setHeight(instance.getHeight());
				cardImage.setImage(instance.getImage());
				cardImage.setOffsetLeft(instance.getOffsetLeft());
				cardImage.setOffsetTop(instance.getOffsetTop());
				cardImage.setOriginalImageSrc(instance.getOriginalImageSrc());
				cardImage.setType(instance.getType());
				cardImage.setWidth(instance.getWidth());

				return new CardImage(cardImage);
			}
		};
		return transaction.commit();
	}

	@Override
	public CardImage getCardImage(final long id)
	{
		System.out.println("[" + new Date() + "] getCardImage(id: " + id + ")");
		Date start = new Date();
		Transaction<CardImage> transaction = new Transaction<CardImage>(sessionFactory)
		{
			@Override
			protected CardImage doInNewTransaction()
			{
				return new CardImage(cardImageDao.findById(id));
			}
		};

		CardImage result = transaction.commit();
		System.out.println("[" + new Date() + "] getCardImage(id: " + id + ") returns after "
			+ ((new Date().getTime()) - start.getTime()));
		return result;
	}

	@Override
	public void deleteCardImage(final long id)
	{
		System.out.println("[" + new Date() + "] deleteCardImage(id: " + id + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				DbCardImage image = cardImageDao.findById(id);
				if (image != null)
				{
					cardImageDao.delete(image);
				}
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public TreePart getTreePart(final long id)
	{
		System.out.println("[" + new Date() + "] getTreePart(id: " + id + ")");
		Transaction<TreePart> transaction = new Transaction<TreePart>(sessionFactory)
		{

			@Override
			protected TreePart doInNewTransaction()
			{

				return treePartDao.findById(id);
			}
		};
		return transaction.commit();
	}

	@Override
	public Marker getMarker(final long id)
	{
		System.out.println("[" + new Date() + "] getMarker(id: " + id + ")");
		Transaction<Marker> transaction = new Transaction<Marker>(sessionFactory)
		{

			@Override
			protected Marker doInNewTransaction()
			{
				return markerDao.findById(id);
			}
		};

		return transaction.commit();
	}

	@Override
	public TreePart addOrUpdateTreePart(final TreePart part)
	{
		System.out.println("[" + new Date() + "] addOrUpdateTreePart(part: " + part + ")");
		if (part.getType() == TreePartType.Marker && part.getMarker() == null)
		{
			throw new RuntimeException("MarkerTreePart without marker == null is not allowed!");
		}
		Transaction<TreePart> transaction = new Transaction<TreePart>(sessionFactory)
		{

			@Override
			protected TreePart doInNewTransaction()
			{
				if (part.getId() > 0 && treePartDao.findById(part.getId()) == null)
				{
					part.setId(0); // is a retry
				}
				return createOrUpdateTreePart(part);
			}
		};

		return transaction.commit();
	}

	@Override
	public Marker addOrUpdateMarker(final Marker marker)
	{
		System.out.println("[" + new Date() + "] addOrUpdateMarker(marker: " + marker + ")");
		Transaction<Marker> transaction = new Transaction<Marker>(sessionFactory)
		{

			@Override
			protected Marker doInNewTransaction()
			{
				if (marker.getId() > 0 && markerDao.findById(marker.getId()) == null)
				{
					marker.setId(0); // is a retry
				}
				return createOrUpdateMarker(marker);
			}
		};

		return transaction.commit();
	}

	private TreePart createOrUpdateTreePart(TreePart treepart)
	{
		List<TreePart> successors = new ArrayList<TreePart>();
		for (TreePart part : treepart.getSuccessors())
		{
			successors.add(createOrUpdateTreePart(part));
		}
		treepart.setSuccessors(successors);

		if (treepart.getId() < 1)
		{
			TreePart part = treepart;
			treePartDao.persist(part);
			return part;
		}
		else
		{
			int version = treePartDao.findById(treepart.getId()).getVersion();
			TreePart instance = treepart;
			instance.setVersion(version);
			treePartDao.merge(instance);
			return instance;
		}
	}

	private Marker createOrUpdateMarker(Marker marker)
	{
		if (marker.getId() < 1)
		{
			Marker mark = marker;
			markerDao.persist(mark);
			return mark;
		}
		else
		{
			int version = markerDao.findById(marker.getId()).getVersion();
			Marker instance = marker;
			instance.setVersion(version);
			markerDao.merge(instance);
			return instance;
		}
	}

	@Override
	public EditorQuest addEditorQuest(final EditorQuest editorQuest)
	{
		System.out.println("[" + new Date() + "] addEditorQuest(quest: " + editorQuest + ")");
		Transaction<EditorQuest> transaction = new Transaction<EditorQuest>(sessionFactory)
		{

			@Override
			protected EditorQuest doInNewTransaction()
			{
				editorQuest.setId(0);
				Quest quest = editorQuest.asQuest(markerDao);
				quest.setId(0);
				if (quest.isSubmitted())
				{
					validateQuest(quest);
				}
				questDao.persist(quest);
				return new EditorQuest(quest);
			}

		};

		return transaction.commit();
	}

	private void validateQuest(Quest quest)
	{

		if (quest.getName() == null || quest.getName().isEmpty())
		{
			throw new RuntimeException("Quest (id=" + quest.getId() + ") has no name!");
		}
		DbHtmlObject html = htmlDao.findById(quest.getHtmlId());
		if (html == null)
		{
			throw new RuntimeException("Quest (id=" + quest.getId() + ") has no html!");
		}
		if (quest.getStartMarker() == null)
		{
			throw new RuntimeException("Quest (id=" + quest.getId() + ") has no startMarker!");
		}
		TreePart treePart = treePartDao.findById(quest.getTreeRootId());
		if (treePart == null)
		{
			throw new RuntimeException("Quest (id=" + quest.getId() + ") has no treeRoot!");
		}
		validateTreePart(treePart);
	}

	private void validateTreePart(TreePart treePart)
	{

		if (treePart.getType() == TreePartType.Marker)
		{

			if (treePart.getMarker() == null)
			{

				throw new RuntimeException("Treepart (id=" + treePart.getId() + ") of type "
					+ treePart.getType().name() + "has no Marker!");
			}

			validateMarker(treePart.getMarker());

		}

		if (treePart.getSuccessors() != null)
		{

			for (TreePart part : treePart.getSuccessors())
			{
				validateTreePart(part);
			}
		}
	}

	private void validateMarker(Marker marker)
	{
		if (marker.getName() == null || marker.getName().isEmpty())
		{
			throw new RuntimeException("Marker (id=" + marker.getId() + ") has no name!");
		}
		DbHtmlObject html = htmlDao.findById(marker.getHtmlId());
		if (html == null)
		{
			throw new RuntimeException("Marker (id=" + marker.getId() + ") has no html!");
		}

		if (marker.getPosition() == null || marker.getPosition().getLatitude() == 0
			|| marker.getPosition().getLongitude() == 0)
		{
			throw new RuntimeException("Marker (id=" + marker.getId() + ") has no or invalid position!");
		}

		DbHtmlObject finishedHtml = htmlDao.findById(marker.getFinishedHtmlId());
		switch (marker.getType())
		{
			case FIGHT:
			{
				if (finishedHtml == null)
				{
					throw new RuntimeException("Marker (id=" + marker.getId() + ") of type " + marker.getType().name()
						+ "has no finishedHtml!");
				}
				DbEnemy enemy = dbEnemyDao.findById(marker.getEnemyId());
				if (enemy == null)
				{
					throw new RuntimeException("Marker (id=" + marker.getId() + ") of type " + marker.getType().name()
						+ "has no enemy!");
				}
				validateEnemy(enemy);

				break;
			}
			case INVISIBLE:
			{
				if (finishedHtml == null)
				{
					throw new RuntimeException("Marker (id=" + marker.getId() + ") of type " + marker.getType().name()
						+ "has no finishedHtml!");
				}
				if (marker.getTargetPosition() == null || marker.getTargetPosition().getLatitude() == 0
					|| marker.getTargetPosition().getLongitude() == 0)
				{
					throw new RuntimeException("Marker (id=" + marker.getId() + ") of type " + marker.getType().name()
						+ "has no or invalid targetPosition!");
				}
				break;
			}
			case QUIZ:
			{
				if (finishedHtml == null)
				{
					throw new RuntimeException("Marker (id=" + marker.getId() + ") of type " + marker.getType().name()
						+ "has no finishedHtml!");
				}
				break;
			}

			default:
				break;
		}
	}

	private void validateEnemy(DbEnemy enemy)
	{
		if (enemy.getName() == null || enemy.getName().isEmpty())
		{
			throw new RuntimeException("Enemy (id=" + enemy.getId() + ") has no name!");
		}
		if (enemy.getDeckId() == 0)
		{
			throw new RuntimeException("Enemy (id=" + enemy.getId() + ") has no deck!");
		}
	}

	@Override
	public EditorQuest updateEditorQuest(final EditorQuest editorQuest)
	{
		System.out.println("[" + new Date() + "] updateEditorQuest(quest: " + editorQuest + ")");

		Transaction<EditorQuest> transaction = new Transaction<EditorQuest>(sessionFactory)
		{

			@Override
			protected EditorQuest doInNewTransaction()
			{
				int version = questDao.findById(editorQuest.getId()).getVersion();

				Quest quest = editorQuest.asQuest(markerDao);
				quest.setVersion(version);

				questDao.merge(quest);
				return new EditorQuest(quest);
			}
		};

		return transaction.commit();
	}

	@Override
	public EditorQuest getEditorQuest(final long id)
	{
		System.out.println("[" + new Date() + "] getEditorQuest(id: " + id + ")");
		Transaction<EditorQuest> transaction = new Transaction<EditorQuest>(sessionFactory)
		{

			@Override
			protected EditorQuest doInNewTransaction()
			{
				return new EditorQuest(questDao.findById(id));
			}
		};

		return transaction.commit();
	}

	@Override
	public List<EditorQuest> getEditorQuests(final List<Long> ids)
	{
		System.out.println("[" + new Date() + "] getQuests(ids: " + ids + ")");
		Transaction<List<EditorQuest>> transaction = new Transaction<List<EditorQuest>>(sessionFactory)
		{

			@Override
			protected List<EditorQuest> doInNewTransaction()
			{
				String hql = "from " + Quest.class.getName() + " where id in :ids";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameterList("ids", ids);

				List<Quest> quests = query.list();
				List<EditorQuest> editorQuests = new ArrayList<EditorQuest>();
				for (Quest quest : quests)
				{
					editorQuests.add(new EditorQuest(quest));
				}
				return editorQuests;
			}
		};

		return transaction.commit();
	}

	@Override
	public Enemy getEnemy(final long id)
	{
		System.out.println("[" + new Date() + "] getEnemy(id: " + id + ")");
		Transaction<DbEnemy> transaction = new Transaction<DbEnemy>(sessionFactory)
		{
			@Override
			protected DbEnemy doInNewTransaction()
			{
				return dbEnemyDao.findById(id);
			}
		};

		DbEnemy dbEnemy = transaction.commit();
		if (dbEnemy == null)
		{
			return null;
		}
		else
		{
			return new Enemy(dbEnemy);
		}
	}

	@Override
	public RandomEnemy getRandomEnemy(final long id)
	{
		System.out.println("[" + new Date() + "] getRandomEnemy(id: " + id + ")");
		Transaction<DbRandomEnemy> transaction = new Transaction<DbRandomEnemy>(sessionFactory)
		{
			@Override
			protected DbRandomEnemy doInNewTransaction()
			{
				return dbRandomEnemyDao.findById(id);
			}
		};

		DbRandomEnemy dbEnemy = transaction.commit();
		if (dbEnemy == null)
		{
			return null;
		}
		else
		{
			return new RandomEnemy(dbEnemy);
		}
	}

	@Override
	public String uploadFile(String fileName, String imageData)
	{
		FileTransferer service = new FileTransfererImpl();
		return service.upload(FILE_PATH + fileName, fileName, imageData);
	}

	@Override
	public String downloadFile(String fileName)
	{
		FileTransferer service = new FileTransfererImpl();
		return service.download(FILE_PATH + fileName);
	}

	@Override
	public void deleteFile(String fileName)
	{
		FileTransferer service = new FileTransfererImpl();
		service.delete(FILE_PATH + fileName);
	}

	@Override
	public IntegrationConfigParams convertPicture(IntegrationConfigParams inParams)
	{
		IntegratePicture converter = new IntegratePicture(true, INTEGRATION_PATH + "config", "integration",
			INTEGRATION_PATH + "Integration");
		// IntegrationConfigParams params = new
		// IntegrationConfigParams("/home/backend/Downloads/katze.jpg", 110, 10,
		// 450, 362);
		inParams.setInName(FILE_PATH + inParams.getInName());
		IntegrationConfigParams outParams = converter.ConvertPicture(inParams);

		String outName = outParams.getOutName();
		outParams.setOutName(outName.substring(outName.lastIndexOf("/") + 1));
		return outParams;
	}

	@Override
	public Deck getStandartDeck(final long userId)
	{

		System.out.println("[" + new Date() + "] getStandartDeck(user: " + userId + ")");

		return addDeck(createStandardDeck());
	}

	private Deck createStandardDeck()
	{
		Transaction<Deck> transaction = new Transaction<Deck>(sessionFactory)
		{

			@Override
			protected Deck doInNewTransaction()
			{

				Deck deck = doCreateStandartDeck();

				return deck;
			}
		};

		return transaction.commit();
	}

	private Deck doCreateStandartDeck()
	{
		Deck deck = new Deck();
		deck.setName("Standard Deck");
		List<LongToIntEntry> cardIds = new ArrayList<>();

		String hql = "select card from " + Card.class.getName() + " card order by card.id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setMaxResults(20);
		List<Card> cards = query.list();
		for (Card card : cards)
		{
			cardIds.add(new LongToIntEntry(1, card.getId()));
		}
		deck.setCardIds(cardIds);
		return deck;
	}

	@Override
	public List<Card> getBooster(final long userId)
	{
		final int NUM_CARD_PER_BOOSTER = 3;

		System.out.println("[" + new Date() + "] getBooster(id: " + userId + ")");
		Transaction<List<Card>> transaction = new Transaction<List<Card>>(sessionFactory)
		{
			@Override
			protected List<Card> doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);

				String hql = "select max(card.id) from " + Card.class.getName() + " card";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);

				long cardCount = (long) query.uniqueResult();
				List<Card> result = new ArrayList<>(NUM_CARD_PER_BOOSTER);
				for (int i = 0; i < NUM_CARD_PER_BOOSTER; i++)
				{
					long randomNum = ThreadLocalRandom.current().nextLong(0, cardCount + 1);
					Card card = cardDao.findById(randomNum);
					if (card == null)
					{
						i--;
						continue;
					}
					result.add(card);
					addCardToUser(user, card);
				}
				userDao.merge(user);
				return result;
			}
		};
		return transaction.commit();
	}

	@Override
	public void addCardToUser(final long userId, final long cardId)
	{
		System.out.println("[" + new Date() + "] addCardToUser(userId: " + userId + ", cardId: " + cardId + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);
				Card card = cardDao.findById(cardId);
				addCardToUser(user, card);
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void addQuestInstanceToUser(final long userId, final long questInstanceId)
	{
		System.out.println("[" + new Date() + "] addQuestInstanceToUser(userId: " + userId + ", questInstanceId: "
			+ questInstanceId + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);
				user.getActiveQuestIds().add(questInstanceId);
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void removeQuestInstanceFromUser(final long userId, final long questInstanceId)
	{
		System.out.println("[" + new Date() + "] removeQuestInstanceFromUser(userId: " + userId + ", questInstanceId: "
			+ questInstanceId + ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);
				user.getActiveQuestIds().remove(questInstanceId);
				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void addSolvedQuestToUser(final long userId, final long questId)
	{
		System.out.println("[" + new Date() + "] addSolvedQuestToUser(userId: " + userId + ", questId: " + questId
			+ ")");
		Transaction<DbUser> transaction = new Transaction<DbUser>(sessionFactory)
		{

			@Override
			protected DbUser doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);
				user.getSolvedQuestIds().add(questId);
				return null;
			}
		};
		transaction.commit();
	}

	private void addCardToUser(DbUser user, Card card)
	{
		Map<Long, Integer> userCards = user.getCardIds();
		int value = 1;
		if (userCards.containsKey(card.getId()))
		{
			value = userCards.get(card.getId()) + 1;
		}
		userCards.put(card.getId(), value);
	}

	@Override
	public void createRandomEnemies()
	{
		System.out.println("[" + new Date() + "] createRandomEnemies()");
		final float LONG_MIN = 13.427517f;
		final float LONG_MAX = 16.056518f;
		final float LAT_MIN = 46.652770f;
		final float LAT_MAX = 47.723016f;
		final int latSteps = 280;
		final int longSteps = 720;

		Transaction<List<DbRandomEnemy>> transaction = new Transaction<List<DbRandomEnemy>>(sessionFactory)
		{

			@Override
			protected List<DbRandomEnemy> doInNewTransaction()
			{
				String hql = "delete from " + DbRandomEnemy.class.getName();
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.executeUpdate();
				return null;
			}

		};
		transaction.commit();

		float longDiff = LONG_MAX - LONG_MIN;
		float latDiff = LAT_MAX - LAT_MIN;
		final float longStep = longDiff / longSteps;
		final float latStep = latDiff / latSteps;
		final Random rand = new Random(System.currentTimeMillis());
		float currentLat = LAT_MIN;
		int enemyCount = 0;
		for (; currentLat < LAT_MAX; currentLat += latStep)
		{
			final float finalCurrentLat = currentLat;
			final int finalEnemyCount = enemyCount;
			final float finalLongStep = longStep;
			Transaction<Integer> createTransaction = new Transaction<Integer>(sessionFactory)
			{
				@Override
				protected Integer doInNewTransaction()
				{
					int thisEnemyCOunt = finalEnemyCount;
					String hql = "from " + DbEnemy.class.getName() + " where randomEnemy = :randomEnemy";
					Query query = sessionFactory.getCurrentSession().createQuery(hql);
					query.setBoolean("randomEnemy", true);

					List<DbEnemy> enemies = query.list();
					float currentLong = LONG_MIN + rand.nextFloat() * finalLongStep;

					for (; currentLong < LONG_MAX; currentLong += finalLongStep)
					{
						DbEnemy enemy = enemies.get(thisEnemyCOunt % (enemies.size() - 1));
						MapPosition position = new MapPosition(currentLong + rand.nextFloat() * latStep,
							finalCurrentLat + rand.nextFloat() * longStep);
						DbRandomEnemy randomEnemy = new DbRandomEnemy(position, enemy, new ArrayList<Long>());
						dbRandomEnemyDao.persist(randomEnemy);
						if (thisEnemyCOunt % 10000 == 0)
						{
							System.out.println("[" + new Date() + "] creating enemy #" + thisEnemyCOunt + "  at "
								+ position.toString());
						}
						thisEnemyCOunt++;
					}

					return thisEnemyCOunt;
				}
			};

			enemyCount = createTransaction.commit();
		}
	}

	@Override
	public List<RandomEnemy> getRandomEnemiesInRange(final MapPosition position, final float range,
		final List<Long> hideEnemies)
	{
		System.out.println("[" + new Date() + "] getRandomEnemiesInRange(position: " + position + " ,range: " + range
			+ " ,hideEnemies: " + hideEnemies + ")");
		Transaction<List<RandomEnemy>> transaction = new Transaction<List<RandomEnemy>>(sessionFactory)
		{

			@Override
			protected List<RandomEnemy> doInNewTransaction()
			{
				float longMin = position.getLongitude() - range;
				float longMax = position.getLongitude() + range;
				float latMin = position.getLatitude() - range;
				float latMax = position.getLatitude() + range;

				String hql = "from " + DbRandomEnemy.class.getName() + " en where "
					+ "en.position.longitude > :longMin and " + "en.position.longitude < :longMax and "
					+ "en.position.latitude > :latMin and " + "en.position.latitude < :latMax";
				if (hideEnemies != null && !hideEnemies.isEmpty())
				{
					hql += " and en.id not in :ids";
				}

				Query query = sessionFactory.getCurrentSession().createQuery(hql);

				if (hideEnemies != null && !hideEnemies.isEmpty())
				{
					query.setParameterList("ids", hideEnemies);
				}

				query.setFloat("longMin", longMin);
				query.setFloat("longMax", longMax);
				query.setFloat("latMin", latMin);
				query.setFloat("latMax", latMax);
				query.setMaxResults(100);
				List<DbRandomEnemy> dbEnemies = query.list();
				List<RandomEnemy> enemies = new ArrayList<>();
				for (DbRandomEnemy dbRandomEnemy : dbEnemies)
				{
					enemies.add(new RandomEnemy(dbRandomEnemy));
				}

				return enemies;
			}
		};

		return transaction.commit();
	}

	@Override
	public void addUserIdToCompletedListOf(final long randomEnemyId, final long userId)
	{
		System.out.println("[" + new Date() + "] addUserIdToCompletedListOf(randomEnemyId: " + randomEnemyId
			+ " ,userId: " + userId + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				DbRandomEnemy enemy = dbRandomEnemyDao.findById(randomEnemyId);

				enemy.getCompletedByUserIds().add(userId);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void addCreatedQuestToUser(final long userId, final long questId)
	{
		System.out.println("[" + new Date() + "] addCreatedQuestToUser(userId: " + userId + " ,questId: " + questId
			+ ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);

				user.getCreatedQuestIds().add(questId);

				return null;
			}
		};
		transaction.commit();
	}

	@Override
	public void removeCreatedQuestFromUser(final long userId, final long questId)
	{
		System.out.println("[" + new Date() + "] removeCreatedQuestFromUser(userId: " + userId + " ,questId: "
			+ questId + ")");
		Transaction<?> transaction = new Transaction<Object>(sessionFactory)
		{

			@Override
			protected Object doInNewTransaction()
			{
				DbUser user = userDao.findById(userId);

				user.getCreatedQuestIds().remove(questId);

				return null;
			}
		};
		transaction.commit();
	}
}
