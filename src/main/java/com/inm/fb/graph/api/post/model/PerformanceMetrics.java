/**
 * 
 */
package com.inm.fb.graph.api.post.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Class to contain the performance metrics for the facebook post
 * 
 * @author dkumar
 * 
 */
public class PerformanceMetrics implements Serializable {

	private static final long serialVersionUID = 1L;

	private String m_postId;
	private Long m_likes;
	private Long m_shares;
	private Long m_comments;
	private Long m_impressions;
	private Long m_reachs;
	private Long m_engagements;
	private Long m_engagers;
	private Date m_updatedDate;

	private PerformanceMetrics(PerformanceMetricsBuilder builder) {
		m_postId = builder.postId;
		m_likes = builder.likes;
		m_shares = builder.shares;
		m_comments = builder.comments;
		m_impressions = builder.impressions;
		m_reachs = builder.reachs;
		m_engagements = builder.engagements;
		m_engagers = builder.engagers;
		m_updatedDate = builder.updatedDate;
	}

	public String getPostId() {
		return m_postId;
	}

	public Long getLikes() {
		return m_likes;
	}

	public Long getShares() {
		return m_shares;
	}

	public Long getComments() {
		return m_comments;
	}

	public Long getImpressions() {
		return m_impressions;
	}

	public Long getReachs() {
		return m_reachs;
	}

	public Long getEngagements() {
		return m_engagements;
	}

	public Long getEngagers() {
		return m_engagers;
	}

	public Date getUpdatedDate() {
		return m_updatedDate;
	}

	/**
	 * Get the json of the performance metrics object
	 * 
	 * @return Performance metrics json string
	 */
	public String toJson(){
		StringBuilder json = new StringBuilder("\"perf_metrics\":{");
		json.append("\"post_id\":").append("\"").append(m_postId).append("\"")
		.append("\"likes\":").append("\"").append(m_likes).append("\"")
		.append("\"shares\":").append("\"").append(m_shares).append("\"")
		.append("\"comments\":").append("\"").append(m_comments).append("\"")
		.append("\"impressions\":").append("\"").append(m_impressions).append("\"")
		.append("\"reaches\":").append("\"").append(m_reachs).append("\"")
		.append("\"engagements\":").append("\"").append(m_engagements).append("\"")
		.append("\"engagers\":").append("\"").append(m_engagers).append("\"")
		.append("\"updated_date\":").append("\"").append(m_updatedDate).append("\"")
		.append("}");
		return json.toString();
	}
	/**
	 * Builder class for Performance metrics
	 * 
	 * @author dkumar
	 * 
	 */
	public static class PerformanceMetricsBuilder {

		private String postId;
		private Long likes;
		private Long shares;
		private Long comments;
		private Long impressions;
		private Long reachs;
		private Long engagements;
		private Long engagers;
		private Date updatedDate;

		public PerformanceMetricsBuilder(String postId) {
			this.postId = postId;
		}

		public PerformanceMetricsBuilder likes(Long likes) {
			this.likes = likes;
			return this;
		}

		public PerformanceMetricsBuilder shares(Long shares) {
			this.shares = shares;
			return this;
		}

		public PerformanceMetricsBuilder comments(Long comments) {
			this.comments = comments;
			return this;
		}

		public PerformanceMetricsBuilder impressions(Long impressions) {
			this.impressions = impressions;
			return this;
		}

		public PerformanceMetricsBuilder reachs(Long reachs) {
			this.reachs = reachs;
			return this;
		}

		public PerformanceMetricsBuilder engagements(Long engagements) {
			this.engagements = engagements;
			return this;
		}

		public PerformanceMetricsBuilder engagers(Long engagers) {
			this.engagers = engagers;
			return this;
		}

		public PerformanceMetricsBuilder updatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
			return this;
		}

		public PerformanceMetrics build() {
			return new PerformanceMetrics(this);
		}
	}

}
