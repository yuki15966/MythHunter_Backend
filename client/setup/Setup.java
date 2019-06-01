
package setup;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import org.junit.Test;
import com.backend.wsdl.Action;
import com.backend.wsdl.ActionType;
import com.backend.wsdl.Card;
import com.backend.wsdl.CardImage;
import com.backend.wsdl.CardType;
import com.backend.wsdl.Deck;
import com.backend.wsdl.DifficultyLevel;
import com.backend.wsdl.EditorQuest;
import com.backend.wsdl.Enemy;
import com.backend.wsdl.HtmlObject;
import com.backend.wsdl.IBackend;
import com.backend.wsdl.IBackendProxy;
import com.backend.wsdl.LongToIntEntry;
import com.backend.wsdl.MapPosition;
import com.backend.wsdl.Marker;
import com.backend.wsdl.MarkerType;
import com.backend.wsdl.Position;
import com.backend.wsdl.StringToBoolEntry;
import com.backend.wsdl.StringToStringEntry;
import com.backend.wsdl.TreePart;
import com.backend.wsdl.TreePartType;
import com.backend.wsdl.User;

public class Setup
{
	/*
	 * @Before public void init() { IBackend backend = new IBackendProxy(); try { backend.clearDB(); } catch
	 * (RemoteException e) { e.printStackTrace(); } }
	 * @Test public void clearDb() throws IOException { IBackend backend = new IBackendProxy(); try { backend.clearDB();
	 * } catch (RemoteException e) { e.printStackTrace(); } }
	 */
	String url = "http://mythhunter.ddns.net:18080/Backend/images/";

	Action taunt;
	Action sleep1;
	Action sleep2;
	Action sleep3;
	Action confuse1;
	Action confuse2;
	Action confuse3;
	Action weaken1;
	Action weaken2;
	Action weaken3;
	Action burn1;
	Action burn2;
	Action burn3;
	Action damage1;
	Action damage2;
	Action damage3;
	Action damage4;
	Action damage5;
	Action damage6;
	Action dot1;
	Action dot2;
	Action dot3;
	Action halveAttack;
	Action reduceAttack1;
	Action reduceAttack2;
	Action draw2cards;
	Action draw4cards;
	Action bannMagic;
	Action heal1;
	Action heal2;
	Action heal3;
	Action heal4;
	Action heal5;
	Action heal6;
	Action heal7;
	Action heal8;
	Action heal9;
	Action heal10;
	Action incAttack1;
	Action doubleAttack;
	Action incAttack2;
	Action incAttack4;

	@Test
	public void setup() throws IOException
	{
		IBackend backend = new IBackendProxy();
		backend.clearDB();
		setupActions(backend);
		final List<LongToIntEntry> cards = setupCards(backend);
		LongToIntEntry[] cardIds = cards.toArray(new LongToIntEntry[cards.size()]);
		createRandomEnemies(backend, cardIds);
	}

	@Test
	public void setupAll() throws IOException
	{
		final IBackend backend = new IBackendProxy();
		// backend.clearDB();
		setupActions(backend);
		List<LongToIntEntry> cards = setupCards(backend);
		final LongToIntEntry[] cardIds = cards.toArray(new LongToIntEntry[cards.size()]);
		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					createRandomEnemies(backend, cardIds);
				}
				catch (RemoteException e)
				{
					e.printStackTrace();
				}
			}
		});
		thread.start();
		Long[] questIds = setupQuests(backend, cardIds);
		Long[] deckIds = setupDecks(backend, cardIds);
		setupUser(backend, cardIds, questIds, deckIds);
	}

	private User setupUser(IBackend backend, LongToIntEntry[] cardIds, Long[] questIds, Long[] deckIds)
		throws IOException
	{
		System.out.println("setupUser");
		User user = new User(null, 0, cardIds, null, questIds, "", deckIds, 0, 0, 0, 1500000, "test",
			"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b", true, null, 0, 0, false, 0);
		user = backend.addUser(user);

		Card[] booster = backend.getBooster(user.getId());
		return user;
	}
	private Long[] setupDecks(IBackend backend, LongToIntEntry[] cardIds) throws IOException
	{
		System.out.println("setupDecks");
		Deck deck = backend.addDeck(new Deck(cardIds, 0, "Base Deck"));
		Deck deck2 = backend.addDeck(new Deck(cardIds, 0, "Second Deck"));
		Long[] deckIds = { deck.getId(), deck2.getId() };
		return deckIds;
	}

	private List<LongToIntEntry> setupCards(IBackend backend) throws RemoteException
	{
		System.out.println("setupCards");

		List<LongToIntEntry> cards = createTutorialCards(backend);

		{
			Action action = draw2cards;
			String name = "Poseidons Gunst";
			String description = "In seiner Güte gewährt dir Poseidon 2 zusätzliche Karten.";
			Long[] actionIds = { action.getId() };
			BufferedImage bufferedImage = getImage("Poseidon.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, null, description, 0, image.getId(), null, name,
				(int) action.getStarCosts(), CardType.SPELL, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action action = incAttack2;
			String name = "Thors Stärke";
			String description = "Der Donnergott gewährt einen Teil seiner Stärke.";
			Long[] actionIds = { action.getId() };
			BufferedImage bufferedImage = getImage("Thor.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, null, description, 0, image.getId(), null, name,
				(int) action.getStarCosts(), CardType.SPELL, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = {};
			String name = "Steirischer Panther";
			String description = "Der mächtige Panther verbreitet Furcht unter seinen Gegnern.";
			int attack = 7;
			int life = 9;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("panther_card.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = { burn2 };
			String name = "Baby Drache";
			String description = "Begehe nicht den Fehler diesen Drachen zu unterschätzen nur weil er noch klein ist.";
			int attack = 2;
			int life = 4;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("Drake.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = {};
			String name = "Odin Allvater";
			String description = "Der Vater aller nordischen Götter ist ein gefürchteter Krieger.";
			int attack = 10;
			int life = 10;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("Odin.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = { confuse1 };
			String name = "Wolpertinger";
			String description = "Dieses scheue bayrische Wesen verwirrt Gegner durch sein unnatürliches Aussehen.";
			int attack = 3;
			int life = 3;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("Wolpertinger.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = { weaken1 };
			String name = "Incubus";
			String description = "Der Incubus-Dämon schwächt Gegner indem er Alpträume verursacht.";
			int attack = 3;
			int life = 5;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("Incubus.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action action = heal4;
			String name = "Heilende Hände";
			String description = "Eine Berührung der magischen Hände reicht aus um alle körperlichen Gebrechen zu heilen.";
			Long[] actionIds = { action.getId() };
			BufferedImage bufferedImage = getImage("Hands.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, null, description, 0, image.getId(), null, name,
				(int) action.getStarCosts(), CardType.SPELL, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action action = dot2;
			String name = "Pest";
			String description = "Die Pest ist eine Krankheit, die vor allem im Mittelalter weit verbreitet war.";
			Long[] actionIds = { action.getId() };
			BufferedImage bufferedImage = getImage("Pest.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, null, description, 0, image.getId(), null, name,
				(int) action.getStarCosts(), CardType.SPELL, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action action = halveAttack;
			String name = "Schrumpfstrahler";
			String description = "Der Schrumpfstrahler verkleiner jedes Ziel auf die Hälfte der Größe.";
			Long[] actionIds = { action.getId() };
			BufferedImage bufferedImage = getImage("Schrumpfstrahler.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");

			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, null, description, 0, image.getId(), null, name,
				(int) action.getStarCosts(), CardType.SPELL, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = { taunt };
			String name = "Rübezahl";
			String description = "Der Berggeist Rübezahl lauert seinen Feinden im tschechischen Riesengebirge auf.";
			int attack = 6;
			int life = 6;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("Rübezahl.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));

		}

		return cards;
	}

	private List<LongToIntEntry> createTutorialCards(IBackend backend) throws RemoteException
	{
		System.out.println("createTutorialCards");
		List<LongToIntEntry> cards = new ArrayList<LongToIntEntry>();
		{
			Action action = bannMagic;
			String name = "Anti-Magie-Spray";
			String description = "Gegen kleine und große magische Anhängsel";
			Long[] actionIds = { action.getId() };
			BufferedImage bufferedImage = getImage("Spray.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, null, description, 0, image.getId(), null, name,
				(int) action.getStarCosts(), CardType.SPELL, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action action = damage5;
			String name = "Feuerball";
			String description = "Ein mächtiger Feuerball der alles auf seinem Weg verwüstet";
			Long[] actionIds = { action.getId() };
			BufferedImage bufferedImage = getImage("fireball.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, null, description, 0, image.getId(), null, name,
				(int) action.getStarCosts(), CardType.SPELL, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = {};
			String name = "Angriffslustiger Pudel";
			String description = "Ein \"Wadl-Beisser\" wie er im Buche steht";
			int attack = 3;
			int life = 1;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("poodle.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = { taunt };
			String name = "Robuster Kobold";
			String description = "Ein besonders großes Exemplar der Kobolde aus den fernen Mooren.";
			int life = 3;
			int attack = 3;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("cobold.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = { taunt };
			String name = "Baumriese";
			String description = "Eine uralte, riesige Eiche aus den Alpen-Wäldern. Gerüchteweise ein Verwandter von Baumbart";
			int attack = 2;
			int life = 10;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("tree.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = { sleep1 };
			String name = "Listige Fee";
			String description = "Die Stimme dieser Fee macht selbst aus wütenden Orks zahme Schäfchen.";
			int attack = 1;
			int life = 1;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("fairy.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = { taunt };
			String name = "Gestiefelter Kater";
			String description = "Stiefel, Säbel und Sampt-Pfötchn: So kennen wir den gestiefelten Kater.";
			int attack = 2;
			int life = 2;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("cat.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = {};
			String name = "Furchtlose Eule";
			String description = "Diese Eule fürchtet sich vor nichts, weder vor dem Jäger, noch dem Tageslicht. Vor garnichts!";
			int attack = 6;
			int life = 4;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("owl_out.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		{
			Action[] actions = {};
			String name = "Gartenzwerg";
			String description = "Ein allezeit freundlicher und gut gelaunter Gärtner-Zwerg der nur manchmal mit seiner Harke um sich schlägt.";
			int attack = 2;
			int life = 2;
			int stars = (int) (life * 0.5 + attack * 0.5);
			List<Long> actionIdsList = new ArrayList<Long>();
			for (Action action : actions)
			{
				stars += action.getStarCosts();
				actionIdsList.add(action.getId());
			}
			Long[] actionIds = actionIdsList.toArray(new Long[actionIdsList.size()]);
			BufferedImage bufferedImage = getImage("dwarf.png");
			byte[] imageAsByteArray = ToByteArray(bufferedImage, "png");
			CardImage image = new CardImage(bufferedImage.getHeight(), 0l,
				DatatypeConverter.printBase64Binary(imageAsByteArray), 0.0f, 0.0f, "", "image/png",
				bufferedImage.getWidth());
			image = backend.addCardImage(image);
			Card newCard = new Card(actionIds, attack, description, 0, image.getId(), life, name, (int) stars,
				CardType.MONSTER, null);
			newCard = backend.addCard(newCard);
			cards.add(new LongToIntEntry(newCard.getId(), 1));
		}
		return cards;
	}

	private BufferedImage getImage(String imageName)
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File(new File(".").getCanonicalPath() + "/WebContent/images/" + imageName));
			return img;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private byte[] ToByteArray(BufferedImage image, String format)
	{
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, format, baos);

			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Test
	public void getHTMLS() throws IOException
	{
		IBackend backend = new IBackendProxy();
		HtmlObject htmlObject = backend.getHtml(11);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(39);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(17);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(33);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(31);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(10);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(2);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(47);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(15);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(5);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(19);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(24);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(8);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(28);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(4);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(1);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(22);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());

		htmlObject = backend.getHtml(43);
		System.out.println(htmlObject.getId() + " : " + htmlObject.getHtml());
	}

	public void setupActions(IBackend backend) throws IOException
	{
		System.out.println("setupActions");
		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "taunt");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Spott");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"The Player and other Monsters without taunt can't be attacked while this Monster is present.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Der Spieler und andere Monster ohne Spott können nicht angegriffen werden solange dieses Monster am Feld ist.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			taunt = backend.addAction(new Action(0, descriptionEntries, 0, CardType.MONSTER, false, 0, 0, 0, false,
				namesEntries, 2, ActionType.TAUNT));
		}

		// {
		// StringToStringEntry enNameEntry = new StringToStringEntry("en", "Base-Attack");
		// StringToStringEntry deNameEntry = new StringToStringEntry("de", "Basis-Angriff");
		// StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
		// StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
		// "The Monster physically damages an enemy");
		// StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
		// "Das Monster F�gt einem Gegner physischen Schaden zu");
		// StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
		// backend.addAction(new Action(0, descriptionEntries, 0, CardType.MONSTER, false, 0, 1, 0, false, namesEntries,
		// 2,
		// ActionType.DAMAGE));
		// }

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Sleep 1");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Einschläfern 1");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Puts an enemy monster to sleep for one round. 1 time useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Schläfert ein gegnerisches Monster für eine Runde ein. 1 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			sleep1 = backend.addAction(new Action(0, descriptionEntries, 1, CardType.MONSTER, true, 0, 1, 1, false,
				namesEntries, 2, ActionType.SLEEP));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Sleep 2");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Einschläfern 2");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Puts an enemy monster to sleep for one round. 2 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Schläfert ein gegnerisches Monster für eine Runde ein. 2 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			sleep2 = backend.addAction(new Action(0, descriptionEntries, 1, CardType.MONSTER, true, 0, 1, 2, false,
				namesEntries, 3, ActionType.SLEEP));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Sleep 3");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Einschläfern 3");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Puts an enemy monster to sleep for one round. 3 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Schläfert ein gegnerisches Monster für eine Runde ein. 3 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			sleep3 = backend.addAction(new Action(0, descriptionEntries, 1, CardType.MONSTER, true, 0, 1, 3, false,
				namesEntries, 4, ActionType.SLEEP));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Confuse 1");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Verwirren 1");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Confuses an enemy monster for two rounds. 1 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Verwirrt ein gegnerisches Monster für zwei Runden. 1 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			confuse1 = backend.addAction(new Action(0, descriptionEntries, 2, CardType.MONSTER, true, 0, 1, 1, false,
				namesEntries, 2, ActionType.CONFUSE));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Confuse 2");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Verwirren 2");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Confuses an enemy monster for two rounds. 2 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Verwirrt ein gegnerisches Monster für zwei Runden. 2 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			confuse2 = backend.addAction(new Action(0, descriptionEntries, 2, CardType.MONSTER, true, 0, 1, 2, false,
				namesEntries, 3, ActionType.CONFUSE));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Confuse 3");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Verwirren 3");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Confuses an enemy monster for two rounds. 3 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Verwirrt ein gegnerisches Monster für zwei Runden. 3 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			confuse3 = backend.addAction(new Action(0, descriptionEntries, 2, CardType.MONSTER, true, 0, 1, 3, false,
				namesEntries, 4, ActionType.CONFUSE));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Weaken 1");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schwächen 1");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Weakens (1/2) an enemy monster for two rounds. 1 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Schwächt ein gegnerisches Monster (1/2) für zwei Runden. 1 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			weaken1 = backend.addAction(new Action(0.5f, descriptionEntries, 0, CardType.MONSTER, true, 0, 1, 1, true,
				namesEntries, 2, ActionType.DECREASE_ATTACK));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Weaken 2");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schwächen 2");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Weakens (1/2) an enemy monster for two rounds. 2 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Schwächt ein gegnerisches Monster (1/2) für zwei Runden. 2 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			weaken2 = backend.addAction(new Action(0.5f, descriptionEntries, 0, CardType.MONSTER, true, 0, 1, 2, true,
				namesEntries, 3, ActionType.DECREASE_ATTACK));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Weaken 3");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schwächen 3");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Weakens (1/2) an enemy monster for two rounds. 3 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Schwächt ein gegnerisches Monster (1/2) für zwei Runden. 3 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			weaken3 = backend.addAction(new Action(0.5f, descriptionEntries, 0, CardType.MONSTER, true, 0, 1, 3, true,
				namesEntries, 4, ActionType.DECREASE_ATTACK));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Burn 1");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Verbrennen 1");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Burns an enemy monster for two rounds, 3 Damage each round. 1 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Verbrennt ein gegnerisches Monster für zwei Runden, 3 Schaden pro Runde. 1 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			burn1 = backend.addAction(new Action(-2, descriptionEntries, 3, CardType.MONSTER, true, 0, 1, 1, false,
				namesEntries, 2, ActionType.DOT));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Burn 2");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Verbrennen 2");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Burns an enemy monster for two rounds, 3 Damage each round. 2 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Verbrennt ein gegnerisches Monster für zwei Runden, 3 Schaden pro Runde. 2 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			burn2 = backend.addAction(new Action(-2, descriptionEntries, 3, CardType.MONSTER, true, 0, 1, 2, false,
				namesEntries, 3, ActionType.DOT));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Burn 3");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Verbrennen 3");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Burns an enemy monster for two rounds, 3 Damage each round. 3 times useable");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Verbrennt ein gegnerisches Monster für zwei Runden, 3 Schaden pro Runde. 3 mal einsetzbar.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			burn3 = backend.addAction(new Action(-2, descriptionEntries, 3, CardType.MONSTER, true, 0, 1, 3, false,
				namesEntries, 4, ActionType.DOT));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Damage 1");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schaden 1");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Inflicts 1 point damage to an enemy.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Fügt einem Gegner 1 Punkt Schaden zu.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			damage1 = backend.addAction(new Action(-1, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 2, ActionType.DAMAGE));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Damage 2");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schaden 2");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Inflicts 2 point damage to an enemy.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Fügt einem Gegner 2 Punkt Schaden zu.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			damage2 = backend.addAction(new Action(-2, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 4, ActionType.DAMAGE));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Damage 3");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schaden 3");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Inflicts 3 point damage to an enemy.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Fügt einem Gegner 3 Punkt Schaden zu.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			damage3 = backend.addAction(new Action(-3, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 6, ActionType.DAMAGE));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Damage 4");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schaden 4");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Inflicts 4 point damage to an enemy.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Fügt einem Gegner 4 Punkt Schaden zu.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			damage4 = backend.addAction(new Action(-4, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 8, ActionType.DAMAGE));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Damage 5");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schaden 5");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Inflicts 5 point damage to an enemy.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Fügt einem Gegner 5 Punkt Schaden zu.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			damage5 = backend.addAction(new Action(-5, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 9, ActionType.DAMAGE));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Damage 6");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schaden 6");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Inflicts 6 point damage to an enemy.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Fügt einem Gegner 6 Punkt Schaden zu.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			damage6 = backend.addAction(new Action(-6, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 10, ActionType.DAMAGE));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Damage Over Time 1");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schaden Über Zeit 1");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Damages an enemy for 3 rounds, 2 Damage each round.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Fügt einem Gegner 3 Runden lang, 2 Schaden pro Runde zu.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			dot1 = backend.addAction(new Action(-2, descriptionEntries, 3, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 4, ActionType.DOT));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Damage Over Time 2");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schaden Über Zeit 2");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Damages an enemy for 2 rounds, 3 Damage each round.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Fügt einem Gegner 2 Runden lang, 3 Schaden pro Runde zu.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			dot2 = backend.addAction(new Action(-3, descriptionEntries, 2, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 5, ActionType.DOT));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Damage Over Time 3");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Schaden Über Zeit 3");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Damages an enemy for 3 rounds, 3 Damage each round.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Fügt einem Gegner 3 Runden lang, 3 Schaden pro Runde zu.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			dot3 = backend.addAction(new Action(-3, descriptionEntries, 3, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 6, ActionType.DOT));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "halve attack");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Angriff Halbieren");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Halve an enemies monsters attackpoints");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Halbiert die Angriffspunkte eines gegnerischen Monsters");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			halveAttack = backend.addAction(new Action(0.5f, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0,
				true, namesEntries, 5, ActionType.DECREASE_ATTACK));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "reduce attack 2");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Angriff verringern 2");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Reduces an enemies monsters attackpoints by 2");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Verringert die Angriffspunkte eines gegnerischen Monsters um 2");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			reduceAttack1 = backend.addAction(new Action(-2, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0,
				false, namesEntries, 4, ActionType.DECREASE_ATTACK));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "reduce attack 4");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Angriff verringern 4");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Reduces an enemies monsters attackpoints by 4");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Verringert die Angriffspunkte eines gegnerischen Monsters um 4");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			reduceAttack2 = backend.addAction(new Action(-4, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0,
				false, namesEntries, 8, ActionType.DECREASE_ATTACK));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "draw 2 cards");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Ziehe 2 Karten");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en", "Draw 2 cards from your deck.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de", "Ziehe 2 Karten vom Deck.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			draw2cards = backend.addAction(new Action(2, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 3, ActionType.DRAW_CARDS));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "draw 4 cards");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Ziehe 4 Karten");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en", "Draw 4 cards from your deck.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de", "Ziehe 4 Karten vom Deck.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			draw4cards = backend.addAction(new Action(4, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 8, ActionType.DRAW_CARDS));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "bann magic");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Magie entfernen");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Banns all magical effects from a monster.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Entfernt alle magischen Effekte eines Monsters.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			bannMagic = backend.addAction(new Action(0, descriptionEntries, null, CardType.SPELL, false, 0, 0, 0,
				false, namesEntries, 3, ActionType.BANN_MAGIC));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 1");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 1");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 1 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 1 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal1 = backend.addAction(new Action(1, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 1, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 2");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 2");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 2 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 2 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal2 = backend.addAction(new Action(2, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 2, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 3");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 3");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 3 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 3 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal3 = backend.addAction(new Action(3, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 3, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 4");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 4");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 4 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 4 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal4 = backend.addAction(new Action(4, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 4, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 5");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 5");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 5 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 5 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal5 = backend.addAction(new Action(5, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 5, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 6");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 6");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 6 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 6 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal6 = backend.addAction(new Action(6, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 6, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 7");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 7");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 7 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 7 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal7 = backend.addAction(new Action(7, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 7, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 8");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 8");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 8 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 8 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal8 = backend.addAction(new Action(8, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 8, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 9");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 9");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 9 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 9 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal9 = backend.addAction(new Action(9, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 9, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Heal 10");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Heilen 10");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Heals a player or monster for 10 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Heilt einen Spieler oder ein Monster um 10 Punkt.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			heal10 = backend.addAction(new Action(10, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 10, ActionType.HEAL));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Increase Attack 1:1,5");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Angriff erhöhen 1:1,5");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Increases attack of a monster by 1:1,5 Point.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Erhöht die Angriffspunkte eines Monsters um 1:1,5.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			incAttack1 = backend.addAction(new Action(1.5f, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0,
				true, namesEntries, 5, ActionType.INCREASE_ATTACK));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Double Attack");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Angriff verdoppeln");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en", "Doubles the attack of a monster.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Verdoppelt die Angriffspunkte eines Monsters.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			doubleAttack = backend.addAction(new Action(2f, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0,
				true, namesEntries, 10, ActionType.INCREASE_ATTACK));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Increase Attack 2");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Angriff erhöhen 2");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Increases the attack of a monster by 2 points.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Erhöht die Angriffspunkte eines Monsters um 2.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			incAttack2 = backend.addAction(new Action(2f, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 4, ActionType.INCREASE_ATTACK));

		}

		{
			StringToStringEntry enNameEntry = new StringToStringEntry("en", "Increase Attack 4");
			StringToStringEntry deNameEntry = new StringToStringEntry("de", "Angriff erhöhen 4");
			StringToStringEntry[] namesEntries = { enNameEntry, deNameEntry };
			StringToStringEntry enDecriptionEntry = new StringToStringEntry("en",
				"Increases the attack of a monster by 4 points.");
			StringToStringEntry deDecriptionEntry = new StringToStringEntry("de",
				"Erhöht die Angriffspunkte eines Monsters um 4.");
			StringToStringEntry[] descriptionEntries = { enDecriptionEntry, deDecriptionEntry };
			incAttack4 = backend.addAction(new Action(4f, descriptionEntries, 0, CardType.SPELL, false, 0, 0, 0, false,
				namesEntries, 8, ActionType.INCREASE_ATTACK));

		}
	}

	public Long[] setupQuests(final IBackend backend, final LongToIntEntry[] cardIds) throws IOException
	{
		System.out.println("setupQuests");

		HtmlObject htmlObject = addTestHtml(backend);

		Long complexQuestId = createComplexQuest(backend, htmlObject, cardIds);
		Long simpleQuestId = createSimpleQuest(backend, htmlObject, cardIds);

		Long[] ids = { complexQuestId, simpleQuestId };

		return ids;
	}
	private void createRandomEnemies(IBackend backend, LongToIntEntry[] cardIds) throws RemoteException
	{
		System.out.println("createRandomEnemies");

		for (int i = 0; i < 5; i++)
		{
			Deck deck = backend.addDeck(new Deck(cardIds, 0, "Base Deck"));
			backend.addEnemy(new Enemy(deck.getId(), "RandomEnemy" + i + " desc", 0, null, "RandomEnemy" + i, true));
		}

		backend.createRandomEnemies();
	}

	private HtmlObject addTestHtml(IBackend backend) throws IOException
	{
		File htmlFile = new File("client/test.html");
		System.out.println(htmlFile.getAbsolutePath());
		BufferedReader reader = new BufferedReader(new FileReader(htmlFile));
		String html = "";
		String line;
		while ((line = reader.readLine()) != null)
		{
			html += line + "\n";
		}

		System.out.println(html);

		StringToBoolEntry e1 = new StringToBoolEntry("radio1", false);
		StringToBoolEntry e2 = new StringToBoolEntry("radio2", true);
		StringToBoolEntry e3 = new StringToBoolEntry("radio3", false);

		StringToBoolEntry e4 = new StringToBoolEntry("checkbox1", false);
		StringToBoolEntry e5 = new StringToBoolEntry("checkbox2", true);
		StringToBoolEntry e6 = new StringToBoolEntry("checkbox3", true);

		StringToBoolEntry[] answers = { e1, e2, e3, e4, e5, e6 };

		HtmlObject htmlObject = new HtmlObject(answers, html, 0);
		htmlObject = backend.addHtml(htmlObject);

		return htmlObject;
	}

	private Long createSimpleQuest(IBackend backend, HtmlObject htmlObject, LongToIntEntry[] cardIds)
		throws RemoteException
	{
		System.out.println("createSimpleQuest");
		// InfoMarker startMarker = new InfoMarker(htmlObject.getId(), 0, "startMarker", new MapPosition(47.068313f,
		// 15.459121f), null);
		// startMarker = backend.addInfoMarker(startMarker);

		Marker startMarker = new Marker(0, 0, htmlObject.getId(), 0, "startMarker", new MapPosition(47.068313f,
			15.459121f), null, MarkerType.INFO, null);
		startMarker = backend.addOrUpdateMarker(startMarker);
		TreePart startPart = new TreePart(true, null, false, false, 0, startMarker, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);

		Deck deck = backend.addDeck(new Deck(cardIds, 0, "Base Deck"));
		Enemy enemy = backend.addEnemy(new Enemy(deck.getId(), "testEnemy desc", 0, url + "monster1Head.jpg",
			"testEnemy", false));

		Marker fightMarker = new Marker(enemy.getId(), htmlObject.getId(), htmlObject.getId(), 0, "fightMarker",
			new MapPosition(47.068896f, 15.458740f), null, MarkerType.FIGHT, null);
		fightMarker = backend.addOrUpdateMarker(fightMarker);
		TreePart fightPart = new TreePart(false, null, false, false, 0, fightMarker, false, new Position(0, 0), 0,
			null, TreePartType.Marker, null);

		// Marker findMarker = new Marker(enemy.getId(), htmlObject.getId(), htmlObject.getId(), 0, "findMarker",
		// new MapPosition(47.068896f, 15.458740f), new MapPosition(47.070255f, 15.462404f), MarkerType.INVISIBLE,
		// null);
		// findMarker = backend.addOrUpdateMarker(findMarker);
		// TreePart fightPart = new TreePart(false, null, false, false, 0, findMarker, false, 0, null,
		// TreePartType.Marker, null);

		Marker endMarker = new Marker(0, 0, htmlObject.getId(), 0, "endMarker", new MapPosition(47.068927f, 15.4597f),
			null, MarkerType.INFO, null);
		endMarker = backend.addOrUpdateMarker(endMarker);
		TreePart endPart = new TreePart(false, null, false, false, 0, endMarker, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		endPart = backend.addOrUpdateTreePart(endPart);

		TreePart[] startSuccessors = { fightPart };
		startPart.setSuccessors(startSuccessors);

		TreePart[] fightSuccessors = { endPart };
		fightPart.setSuccessors(fightSuccessors);
		fightPart = backend.addOrUpdateTreePart(fightPart);
		startPart = backend.addOrUpdateTreePart(startPart);

		Long[] specialCards = { cardIds[0].getKey(), cardIds[1].getKey() };
		EditorQuest quest = new EditorQuest(true, 0, com.backend.wsdl.DifficultyLevel.MEDIUM, 0, htmlObject.getId(), 0,
			"SimpleQuest", 0, "shortDescription", specialCards, startMarker.getId(), true, startPart.getId(), null);
		quest = backend.addEditorQuest(quest);
		return quest.getId();

	}
	private Long createComplexQuest(IBackend backend, HtmlObject htmlObject, LongToIntEntry[] cardIds)
		throws RemoteException
	{
		System.out.println("createComplexQuest");
		// InfoMarker m1 = new InfoMarker(htmlObject.getId(), 0, "M1", new MapPosition(47.068806f, 15.459872f), null);
		// asm1 = backend.addInfoMarker(m1);

		Marker m1 = new Marker(0, 0, htmlObject.getId(), 0, "M1", new MapPosition(47.068806f, 15.459872f), null,
			MarkerType.INFO, null);
		m1 = backend.addOrUpdateMarker(m1);

		// MarkerTreePart part1 = new MarkerTreePart();
		// part1.setMarker(m1);
		// part1 = backend.addMarkerTreePart(part1);

		TreePart part1 = new TreePart(true, null, false, false, 0, m1, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part1 = backend.addOrUpdateTreePart(part1);

		// InfoMarker m2 = new InfoMarker(htmlObject.getId(), 0, "M2", new MapPosition(47.069672f, 15.454314f), null);
		// m2 = backend.addInfoMarker(m2);

		Marker m2 = new Marker(0, 0, htmlObject.getId(), 0, "M2", new MapPosition(47.069672f, 15.454314f), null,
			MarkerType.INFO, null);
		m2 = backend.addOrUpdateMarker(m2);

		// MarkerTreePart part2 = new MarkerTreePart();
		// part2.setMarker(m2);
		// part2 = backend.addMarkerTreePart(part2);

		TreePart part2 = new TreePart(false, null, false, false, 0, m2, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part2 = backend.addOrUpdateTreePart(part2);

		// QuizMarker m3 = new QuizMarker(htmlObject.getId(), 0, "m3", new MapPosition(47.068459f, 15.454786f), null);
		// m3 = backend.addQuizMarker(m3);

		Marker m3 = new Marker(0, htmlObject.getId(), htmlObject.getId(), 0, "M3", new MapPosition(47.068459f,
			15.454786f), null, MarkerType.QUIZ, null);
		m3 = backend.addOrUpdateMarker(m3);

		// MarkerTreePart part3 = new MarkerTreePart();
		// part3.setMarker(m3);
		// part3 = backend.addMarkerTreePart(part3);

		TreePart part3 = new TreePart(false, null, false, false, 0, m3, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part3 = backend.addOrUpdateTreePart(part3);

		// InfoMarker m4 = new InfoMarker(htmlObject.getId(), 0, "m4", new MapPosition(47.067509f, 15.45498f), null);
		// m4 = backend.addInfoMarker(m4);

		Marker m4 = new Marker(0, 0L, htmlObject.getId(), 0L, "M4", new MapPosition(47.067509f, 15.45498f), null,
			MarkerType.INFO, null);
		m4 = backend.addOrUpdateMarker(m4);

		// MarkerTreePart part4 = new MarkerTreePart();
		// part4.setMarker(m4);
		// part4 = backend.addMarkerTreePart(part4);

		TreePart part4 = new TreePart(false, null, false, false, 0, m4, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part4 = backend.addOrUpdateTreePart(part4);

		// InfoMarker m5 = new InfoMarker(htmlObject.getId(), 0, "m5", new MapPosition(47.066252f, 15.455602f), null);
		// m5 = backend.addInfoMarker(m5);

		Marker m5 = new Marker(0, 0, htmlObject.getId(), 0, "M5", new MapPosition(47.066252f, 15.455602f), null,
			MarkerType.INFO, null);
		m5 = backend.addOrUpdateMarker(m5);

		// MarkerTreePart part5 = new MarkerTreePart();
		// part5.setMarker(m5);
		// part5 = backend.addMarkerTreePart(part5);

		TreePart part5 = new TreePart(false, null, false, false, 0, m5, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part5 = backend.addOrUpdateTreePart(part5);

		// InfoMarker m6 = new InfoMarker(htmlObject.getId(), 0, "m6", new MapPosition(47.065886f, 15.458155f), null);
		// m6 = backend.addInfoMarker(m6);

		Marker m6 = new Marker(0, 0, htmlObject.getId(), 0, "M6", new MapPosition(47.065886f, 15.458155f), null,
			MarkerType.INFO, null);
		m6 = backend.addOrUpdateMarker(m6);

		// MarkerTreePart part6 = new MarkerTreePart();
		// part6.setMarker(m6);
		// part6 = backend.addMarkerTreePart(part6);

		TreePart part6 = new TreePart(false, null, false, false, 0, m6, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part6 = backend.addOrUpdateTreePart(part6);

		// InfoMarker m7 = new InfoMarker(htmlObject.getId(), 0, "m7", new MapPosition(47.068313f, 15.459078f), null);
		// m7 = backend.addInfoMarker(m7);

		Marker m7 = new Marker(0, 0, htmlObject.getId(), 0, "M7", new MapPosition(47.068313f, 15.459078f), null,
			MarkerType.INFO, null);
		m7 = backend.addOrUpdateMarker(m7);

		// MarkerTreePart part7 = new MarkerTreePart();
		// part7.setMarker(m7);
		// part7 = backend.addMarkerTreePart(part7);

		TreePart part7 = new TreePart(false, null, false, false, 0, m7, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part7 = backend.addOrUpdateTreePart(part7);

		// InfoMarker m8 = new InfoMarker(htmlObject.getId(), 0, "m8", new MapPosition(47.068298f, 15.463348f), null);
		// m8 = backend.addInfoMarker(m8);

		Marker m8 = new Marker(0, 0, htmlObject.getId(), 0, "M8", new MapPosition(47.068298f, 15.463348f), null,
			MarkerType.INFO, null);
		m8 = backend.addOrUpdateMarker(m8);

		TreePart part8 = new TreePart(false, null, false, false, 0, m8, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part8 = backend.addOrUpdateTreePart(part8);

		Marker m9 = new Marker(0, 0, htmlObject.getId(), 0, "M9", new MapPosition(47.076641f, 15.442253f), null,
			MarkerType.INFO, null);
		m9 = backend.addOrUpdateMarker(m9);

		TreePart part9 = new TreePart(false, null, false, false, 0, m9, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part9 = backend.addOrUpdateTreePart(part9);

		Marker m10 = new Marker(0, 0, htmlObject.getId(), 0, "M10", new MapPosition(47.076174f, 15.443786f), null,
			MarkerType.INFO, null);
		m10 = backend.addOrUpdateMarker(m10);

		TreePart part10 = new TreePart(false, null, false, false, 0, m10, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part10 = backend.addOrUpdateTreePart(part10);

		Marker m11 = new Marker(0, 0, htmlObject.getId(), 0, "M11", new MapPosition(47.074859f, 15.444418f), null,
			MarkerType.INFO, null);
		m11 = backend.addOrUpdateMarker(m11);

		TreePart part11 = new TreePart(false, null, false, false, 0, m11, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part11 = backend.addOrUpdateTreePart(part11);

		Marker m12 = new Marker(0, 0, htmlObject.getId(), 0, "M12", new MapPosition(47.069094f, 15.446542f), null,
			MarkerType.INFO, null);
		m12 = backend.addOrUpdateMarker(m12);

		TreePart part12 = new TreePart(false, null, false, false, 0, m12, false, new Position(0, 0), 0, null,
			TreePartType.Marker, null);
		part12 = backend.addOrUpdateTreePart(part12);

		// AndTreePart andPart1 = new AndTreePart();
		// andPart1 = backend.addAndTreePart(andPart1);

		TreePart andPart1 = new TreePart(false, null, false, false, 0, null, false, new Position(0, 0), 0, null,
			TreePartType.And, null);
		andPart1 = backend.addOrUpdateTreePart(andPart1);

		// AndTreePart andPart2 = new AndTreePart();
		// andPart2 = backend.addAndTreePart(andPart2);

		TreePart andPart2 = new TreePart(false, null, false, false, 0, null, false, new Position(0, 0), 0, null,
			TreePartType.And, null);
		andPart2 = backend.addOrUpdateTreePart(andPart2);

		TreePart orPart3 = new TreePart(false, null, false, false, 0, null, false, new Position(0, 0), 0, null,
			TreePartType.Or, null);
		orPart3 = backend.addOrUpdateTreePart(orPart3);

		// OrTreePart orTreePart1 = new OrTreePart();
		// orTreePart1 = backend.addOrTreePart(orTreePart1);

		TreePart orTreePart1 = new TreePart(false, null, false, false, 0, null, false, new Position(0, 0), 0, null,
			TreePartType.Or, null);
		orTreePart1 = backend.addOrUpdateTreePart(orTreePart1);

		// OrTreePart orTreePart2 = new OrTreePart();
		// orTreePart2 = backend.addOrTreePart(orTreePart2);

		TreePart orTreePart2 = new TreePart(false, null, false, false, 0, null, false, new Position(0, 0), 0, null,
			TreePartType.Or, null);
		orTreePart2 = backend.addOrUpdateTreePart(orTreePart2);

		TreePart[] partsForPart1 = { part2, part3, part4, part5, part6 };
		part1.setSuccessors(partsForPart1);

		TreePart[] partsForPart2 = { andPart1 };
		part2.setSuccessors(partsForPart2);

		TreePart[] partsForPart3 = { andPart1 };
		part3.setSuccessors(partsForPart3);

		TreePart[] partsForPart4 = { orTreePart2 };
		part4.setSuccessors(partsForPart4);

		TreePart[] partsForPart5 = { orTreePart1 };
		part5.setSuccessors(partsForPart5);

		TreePart[] partsForPart6 = { orTreePart1, part9 };
		part6.setSuccessors(partsForPart6);

		TreePart[] partsForPart7 = { andPart2 };
		part7.setSuccessors(partsForPart7);

		TreePart[] partsForAndPart1 = { orTreePart2 };
		andPart1.setSuccessors(partsForAndPart1);

		TreePart[] partsForAndPart2 = { part8 };
		andPart2.setSuccessors(partsForAndPart2);

		TreePart[] partsForOrPart1 = { andPart2 };
		orTreePart1.setSuccessors(partsForOrPart1);

		TreePart[] partsForPart9 = { part10, part11 };
		part9.setSuccessors(partsForPart9);

		TreePart[] partsForPart10 = { orPart3 };
		part10.setSuccessors(partsForPart10);

		TreePart[] partsForPart11 = { orPart3 };
		part11.setSuccessors(partsForPart11);

		TreePart[] partsForOrPart3 = { part12 };
		orPart3.setSuccessors(partsForOrPart3);

		TreePart[] partsForOrPart2 = { part7 };
		orTreePart2.setSuccessors(partsForOrPart2);

		part1 = backend.addOrUpdateTreePart(part1);

		EditorQuest quest = new EditorQuest(true, 0, DifficultyLevel.HIGH, 0, htmlObject.getId(), 0, "ComplexQuest", 0,
			"shortDescription", null, m1.getId(), true, part1.getId(), null);
		quest = backend.addEditorQuest(quest);
		return quest.getId();
	}
}
