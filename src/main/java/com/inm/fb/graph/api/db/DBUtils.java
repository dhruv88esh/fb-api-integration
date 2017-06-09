/**
 * 
 */
package com.inm.fb.graph.api.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.inm.fb.graph.api.post.model.MetaData;
import com.inm.fb.graph.api.post.model.MetaData.MetaDataBuilder;
import com.inm.fb.graph.api.post.model.PerformanceMetrics;
import com.inm.fb.graph.api.post.model.PerformanceMetrics.PerformanceMetricsBuilder;

/**
 * Utils class for insert and update data in database
 * 
 * @author dkumar
 * 
 */
public class DBUtils {

	private static final Logger LOGGER = Logger.getLogger(DBUtils.class);

	private static final String SQL_INSERT_METADATA = "INSERT INTO meta_data (post_id, created_date, post_type, content, link, db_created_date, updated_date) VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_INSERT_PERF_METRICS = "INSERT INTO perf_metrics (post_id, likes, shares, comments, reaches, impressions, engagements, engagers, created_date, updated_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SQL_UPDATE_PERF_METRICS = "UPDATE perf_metrics SET likes=?, shares=?, comments=?, reaches=?, impressions=?, engagements=?, engagers=?, updated_date=? WHERE post_id = ?;";

	private static final String SQL_RETRIEVE_METADATA = "SELECT * FROM meta_data where post_id = ? ORDER BY created_date DESC LIMIT 1;";
	private static final String SQL_RETRIEVE_PERF_METRICS = "SELECT * FROM perf_metrics WHERE post_id = ? ORDER BY created_date DESC LIMIT 1;";

	/**
	 * Method to persist the meta data in the db
	 * 
	 * @param metadata
	 *            Meta data to persist
	 */
	public static void persistMetaData(MetaData metadata) {
		LOGGER.info("Persisting meta data for post : " + metadata.getPostId());
		Connection dbConnection = DataBaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

		try {
			preparedStatement = dbConnection
					.prepareStatement(SQL_INSERT_METADATA);

			preparedStatement.setString(1, metadata.getPostId());
			preparedStatement.setDate(2, new Date(metadata
					.getCreatedTimeStamp().getTime()));
			preparedStatement.setString(3, metadata.getPostType());
			preparedStatement.setString(4, metadata.getPostContents());
			preparedStatement.setDate(5, currentDate);
			preparedStatement.setDate(6, currentDate);

			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error(String.format(
					"Unable to persist meta data for postId: %s %s",
					metadata.getPostId(), e));
		} finally {
			DataBaseConnection.closeConnection(dbConnection);
		}
	}

	/**
	 * Method to persist performance metrics into database
	 * 
	 * @param metrics
	 *            Metrics to persist
	 */
	public static void persistPerformanceMetrics(PerformanceMetrics metrics) {
		LOGGER.info("Persisting performance metrics data for post : "
				+ metrics.getPostId());
		Connection dbConnection = DataBaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

		try {
			preparedStatement = dbConnection
					.prepareStatement(SQL_INSERT_PERF_METRICS);

			preparedStatement.setString(1, metrics.getPostId());
			preparedStatement.setLong(2, metrics.getLikes());
			preparedStatement.setLong(3, metrics.getShares());
			preparedStatement.setLong(4, metrics.getComments());
			preparedStatement.setLong(5, metrics.getReachs());
			preparedStatement.setLong(6, metrics.getImpressions());
			preparedStatement.setLong(7, metrics.getEngagements());
			preparedStatement.setLong(8, metrics.getEngagers());
			preparedStatement.setDate(9, currentDate);
			preparedStatement.setDate(10, currentDate);

			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error(String.format(
					"Unable to persist perf metrics for postId: %s %s",
					metrics.getPostId(), e));
		} finally {
			DataBaseConnection.closeConnection(dbConnection);
		}

	}

	/**
	 * Method to update the performance metrics for a post into db
	 * 
	 * @param metrics
	 *            Metrics to update
	 * @param postId
	 *            Post id
	 */
	public static void updatePerformanceMetrics(PerformanceMetrics metrics,
			String postId) {
		LOGGER.info("Updating performance metrics for post Id: " + postId);
		Connection dbConnection = DataBaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

		try {
			preparedStatement = dbConnection
					.prepareStatement(SQL_UPDATE_PERF_METRICS);

			preparedStatement.setLong(1, metrics.getLikes());
			preparedStatement.setLong(2, metrics.getShares());
			preparedStatement.setLong(3, metrics.getComments());
			preparedStatement.setLong(4, metrics.getReachs());
			preparedStatement.setLong(5, metrics.getImpressions());
			preparedStatement.setLong(6, metrics.getEngagements());
			preparedStatement.setLong(7, metrics.getEngagers());
			preparedStatement.setDate(8, currentDate);
			preparedStatement.setString(9, postId);

			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error(String.format(
					"Unable to update perf metrics for postId: %s %s", postId,
					e));
		} finally {
			DataBaseConnection.closeConnection(dbConnection);
		}
	}

	/**
	 * Retrieve metadata for a post from db
	 * 
	 * @param postId
	 *            Post id
	 * @return Metadata
	 */
	public static MetaData retrieveMetaData(String postId) {
		Connection dbConnection = DataBaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			preparedStatement = dbConnection
					.prepareStatement(SQL_RETRIEVE_METADATA);
			preparedStatement.setString(1, postId);
			rs = preparedStatement.executeQuery();
			MetaDataBuilder builder = new MetaDataBuilder(
					rs.getString("post_id"))
					.createdTimeStamp(rs.getDate("created_date"))
					.postType(rs.getString("post_type"))
					.postContents(rs.getString("content"))
					.postUrl(rs.getString("link"))
					.updatedDate(rs.getDate("updated_date"));

			preparedStatement.close();

			return builder.build();
		} catch (SQLException e) {
			LOGGER.error(String
					.format("Unable to retrieve meta data for postId: %s %s",
							postId, e));
		} finally {
			DataBaseConnection.closeConnection(dbConnection);
		}
		return null;
	}

	/**
	 * Retrieve performance metrics for a post from db
	 * 
	 * @param postId
	 *            Post id
	 * @return Performance metrics
	 */
	public static PerformanceMetrics retrievePerformanceMetrics(String postId) {
		Connection dbConnection = DataBaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			preparedStatement = dbConnection
					.prepareStatement(SQL_RETRIEVE_PERF_METRICS);
			preparedStatement.setString(1, postId);
			rs = preparedStatement.executeQuery();
			PerformanceMetricsBuilder builder = new PerformanceMetricsBuilder(
					rs.getString("post_id")).likes(rs.getLong("likes"))
					.shares(rs.getLong("shares"))
					.comments(rs.getLong("comments"))
					.reachs(rs.getLong("reaches"))
					.impressions(rs.getLong("impressions"))
					.engagements(rs.getLong("engagements"))
					.engagers(rs.getLong("engagers"))
					.updatedDate(rs.getDate("updated_date"));

			preparedStatement.close();

			return builder.build();
		} catch (SQLException e) {
			LOGGER.error(String.format(
					"Unable to retrieve perf metrics for postId: %s %s",
					postId, e));
		} finally {
			DataBaseConnection.closeConnection(dbConnection);
		}
		return null;
	}
}
