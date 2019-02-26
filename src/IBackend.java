import gen.entities.Card;
import gen.entities.MapPosition;
import gen.entities.Marker;
import gen.entities.Quest;
import gen.entities.QuestInstance;
import gen.entities.QuestRating;
import gen.entities.TreePart;
import java.util.List;
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
import main.entities.User;
import main.integration.IntegrationConfigParams;

@WebService(targetNamespace = "http://backend.com/wsdl")
abstract interface IBackend
{
	HtmlObject addHtml(HtmlObject htmlObject);
	HtmlObject updateHtml(final HtmlObject htmlObject);
	HtmlObject getHtml(long id);

	Marker getMarker(long id);
	List<Marker> getMarkers(List<Long> ids);
	void deleteMarker(long id);

	TreePart getTreePart(long id);
	void deleteTreePart(long id);

	TreePartLazy addTreePartLazy(TreePartLazy part);

	void setTreePartActive(long id, boolean active);
	void setTreePartFinished(long id, boolean finished);
	void setTreePartOpened(long id, boolean opened);
	void setQuestInstanceOfTreePart(long markerTreepartId, long QuestInstanceId);

	User login(String unsername, String password);
	void register(String unsername, String password);
	User addUser(User user);
	User updateUser(User user);
	void deleteUser(long id);

	Quest addQuest(Quest quest);
	Quest updateQuest(Quest quest);
	Quest getQuest(long id);
	List<Quest> getQuests(List<Long> ids);
	void deleteQuest(long id);
	List<Quest> getQuestsInRange(MapPosition position, float range, List<Long> hideQuests);

	QuestInstance addQuestInstance(QuestInstance questInstance);
	QuestInstance updateQuestInstance(QuestInstance questInstance);
	QuestInstance getQuestInstance(long id);
	List<QuestInstance> getQuestInstances(List<Long> ids);
	void deleteQuestInstance(long id);
	// void clearDB(); // TODO remove. only for testing

	Deck getDeck(long id);
	Deck updateDeck(Deck deck);
	Deck addDeck(Deck deck);
	List<Deck> getDecks(List<Long> ids);
	void deleteDeck(long id);

	List<Action> getActions(List<Long> ids);
	Action getAction(long id);
	Action addAction(Action action);
	Action updateAction(Action action);
	void deleteAction(long id);
	List<Action> getAllActions();
	List<Action> getAllActionsOfCardType(CardType type);

	Card addCard(Card card);
	void deleteCard(long id);
	Card getCard(long id);
	Card updateCard(Card card);
	List<Card> getCards(List<Long> ids);

	CardImage addCardImage(CardImage image);
	CardImage updateCardImage(CardImage image);
	CardImage getCardImage(long id);
	void deleteCardImage(long id);

	TreePart addOrUpdateTreePart(TreePart part);
	Marker addOrUpdateMarker(Marker marker);
	List<EditorQuest> getEditorQuests(List<Long> ids);
	EditorQuest getEditorQuest(long id);
	EditorQuest updateEditorQuest(EditorQuest quest);
	EditorQuest addEditorQuest(EditorQuest quest);

	void setUserTaskCount(long id, long count);
	void setUserAnsweredQuestionsCount(long id, long count);
	void setUserFoundLocationsCount(long id, long count);
	void setUserWonFightsCount(long id, long count);
	void setUserStartedFightsCount(long id, long count);
	void setUserKmWalked(long id, double kmWalked);
	QuestRating addQuestRating(QuestRating rating);
	RandomEnemy getRandomEnemy(long id);
	void setUserMoney(long id, long money);
	void setQuestInstanceFailCount(long id, long count);
	void setTreePartExecutedAt(TreePartLazy part);
	Enemy addEnemy(Enemy enemy);
	Enemy getEnemy(long id);
	Enemy updateEnemy(Enemy enemy);

	Deck getStandartDeck(long user);

	String uploadFile(String fileName, String imageData);
	String downloadFile(String fileName);
	void deleteFile(String fileName);
	IntegrationConfigParams convertPicture(IntegrationConfigParams parameter);

	void setQuestInstanceVisibleOnMap(long id, boolean visibleOnMap);
	void setQuestInstanceHighlighted(long id, boolean highlighted);
	void setTreePartHighlightedInvisibleMarker(long id, boolean highlightedInvisibleMarker);
	void updateUserDeckIds(long id, List<Long> deckIds);
	List<Card> getBooster(long userId);
	void addCardToUser(long userId, long cardId);
	void setQuestInstanceFightSkipped(long id, boolean skipped);
	void createRandomEnemies();
	List<RandomEnemy> getRandomEnemiesInRange(MapPosition position, float range, List<Long> hideEnemies);
	void addUserIdToCompletedListOf(long randomEnemyId, long userId);
	void setUserTutorialPlayed(long id, boolean tutorialPlayed);
	void updateUserCardIds(long id, List<LongToIntEntry> cardIds);
	void addDeckToUser(long userId, long deckId);
	void removeDeckFromUser(long userId, long deckId);
	void addCreatedCardToUser(long userId, long cardId);
	void removeCreatedCardFromUser(long userId, long cardId);
	void removeCreatedQuestFromUser(long userId, long questId);
	void addCreatedQuestToUser(long userId, long questId);
	void setRotateMapForUser(long userId, boolean rotateMap);
	void addQuestInstanceToUser(long userId, long questInstanceId);
	void addSolvedQuestToUser(long userId, long questId);
	void removeQuestInstanceFromUser(long userId, long questInstanceId);

}
