import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
@Lock(LockType.READ)
public class Timer
{
	@EJB
	private IBackend backend;

	@Schedule(minute = "0", hour = "0")
	public void createRandomEnemies()
	{
		backend.createRandomEnemies();
	}
}
