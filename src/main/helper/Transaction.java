
package main.helper;

import java.util.Date;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class Transaction<T>
{
	private SessionFactory sessionFactory;
	private org.hibernate.Transaction transaction;

	public Transaction(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	protected abstract T doInNewTransaction();

	public T commit()
	{
		int failCount = 0;
		while (true)
		{
			try
			{
				beginTransaction();
				T result = doInNewTransaction();
				endTransaction();
				return result;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				rollbackTransaction();
				failCount++;
				if (failCount > 10)
				{
					throw e;
				}
				try
				{
					Thread.sleep(Math.abs((new Random(new Date().getTime()).nextLong() % 200)));
				}
				catch (InterruptedException e1)
				{
				}
			}
		}
	}

	private void beginTransaction()
	{
		Session session = sessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
	}

	private void endTransaction()
	{
		transaction.commit();
	}
	private void rollbackTransaction()
	{
		transaction.rollback();
	}
}
