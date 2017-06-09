package com.inm.fb.graph.api.connection;

import java.util.List;

import org.apache.log4j.Logger;

import com.inm.fb.graph.api.ContextListener;
import com.inm.fb.graph.api.config.AppProperties;
import com.inm.fb.graph.api.post.model.MetaData;
import com.inm.fb.graph.api.post.model.MetaData.MetaDataBuilder;
import com.inm.fb.graph.api.post.model.PerformanceMetrics;
import com.inm.fb.graph.api.post.model.PerformanceMetrics.PerformanceMetricsBuilder;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Post;
import com.restfb.types.User;

/**
 * Facebook connection class to make connection with facebook and get the data
 * 
 * @author dkumar
 * 
 */
public class FBConnection {

	private static final Logger LOGGER = Logger.getLogger(FBConnection.class);

	private FacebookClient m_facebookClient;
	private String m_pageId;

	public FBConnection() {
		m_facebookClient = new DefaultFacebookClient(AppProperties
				.getProperties().getProperty("FB_ACCESS_TOKEN"), AppProperties
				.getProperties().getProperty("FB_APP_SECRET"),
				Version.VERSION_2_8);
		m_pageId = AppProperties.getProperties().getProperty("FB_PAGE_ID");
	}

	/**
	 * Get the latest post's meta data
	 * 
	 * @return MetaData
	 */
	public MetaData getLatestPostMetaData() {
		
		LOGGER.info("Getting the meta data for the latest post from facebook page...");
		User user = m_facebookClient.fetchObject("me", User.class);

		Connection<Post> pageFeed = m_facebookClient.fetchConnection(m_pageId
				+ "/feed", Post.class, Parameter.with("limit", 10));
		List<Post> postlist = pageFeed.getData();
		Post post1 = postlist.get(0);

		MetaDataBuilder builder = new MetaDataBuilder(post1.getId())
				.createdTimeStamp(post1.getCreatedTime())
				.postType(post1.getType()).postContents(post1.getMessage())
				.postUrl(post1.getLink());
		return builder.build();

	}

	/**
	 * Return the performance metrics for the post
	 * 
	 * @return PerformanceMetrics
	 */
	public PerformanceMetrics getPerformanceMetrics() {

		LOGGER.info("Getting the performance metrics for the post..");
		Connection<Post> myFeed = m_facebookClient.fetchConnection(m_pageId
				+ "/feed", Post.class, Parameter.with("limit", 10));
		List<Post> postlist = myFeed.getData();
		System.out.println(postlist.size());
		Post post1 = postlist.get(0);

		PerformanceMetricsBuilder builder = new PerformanceMetricsBuilder(
				post1.getId()).likes(post1.getLikesCount())
				.shares(post1.getSharesCount())
				.comments(post1.getCommentsCount())
				.reachs(post1.getReactionsCount())
				.impressions(new Long(10)).engagements(new Long(5))
				.engagers(new Long(5));
		//FIXME Need to explore restfb api on how to get impressions, engagements and engagers from restfb api

		return builder.build();

	}
}
