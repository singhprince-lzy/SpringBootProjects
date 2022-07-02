package com.stackroute.newz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.newz.model.News;

/*
 * This class is implementing the NewsDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */
@EnableTransactionManagement
@Transactional
@Repository

public class NewsDAOImpl implements NewsDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */

	SessionFactory sessionfactory;

	@Autowired
	public NewsDAOImpl(SessionFactory sessionFactory) {
		this.sessionfactory = sessionFactory;
	}

	/*
	 * Save the news in the database(news) table.
	 */
	public boolean addNews(News news) {
		sessionfactory.getCurrentSession().save(news);
		return true;
	}

	/*
	 * retrieve all existing news sorted by created Date in descending order(showing
	 * latest news first)
	 */
	public List<News> getAllNews() {
		List<News> list = sessionfactory.getCurrentSession().createQuery("from News").list();

		return list;
	}

	/*
	 * retrieve specific news from the database(news) table
	 */
	public News getNewsById(int newsId) {
		News obj = (News) sessionfactory.getCurrentSession().createQuery("from News where newsId=" + newsId)
				.uniqueResult();
		return obj;
	}

	public boolean updateNews(News news) {
		sessionfactory.getCurrentSession().update(news);
		return true;
	}

	/*
	 * Remove the news from the database(news) table.
	 */
	public boolean deleteNews(int newsId) {
		News res = this.getNewsById(newsId);
		if (res != null) {
			sessionfactory.getCurrentSession().delete(res);
			return true;
		}
		return false;
	}
}