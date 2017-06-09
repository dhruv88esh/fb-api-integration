package com.inm.fb.graph.api.post.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Class to capture the meta data for the facebook post
 * 
 * @author dkumar
 * 
 */
public class MetaData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String m_postId;
	private Date m_createdTimeStamp;
	private String m_postType;
	private String m_postContents;
	private String m_postUrl;
	private Date m_updatedDate;

	private MetaData(MetaDataBuilder builder) {
		m_postId = builder.postId;
		m_createdTimeStamp = builder.createdTimeStamp;
		m_postType = builder.postType;
		m_postContents = builder.postContents;
		m_postUrl = builder.postUrl;
		m_updatedDate = builder.updatedDate;
	}

	public String getPostId() {
		return m_postId;
	}

	public Date getCreatedTimeStamp() {
		return m_createdTimeStamp;
	}

	public String getPostType() {
		return m_postType;
	}

	public String getPostContents() {
		return m_postContents;
	}

	public String getPostUrl() {
		return m_postUrl;
	}

	public Date getUpdatedDate() {
		return m_updatedDate;
	}
	
	/**
	 * Get the json of the meta data object
	 * 
	 * @return Metadata json string
	 */
	public String toJson(){
		StringBuilder json = new StringBuilder("\"meta_data\":{");
		json.append("\"post_id\":").append("\"").append(m_postId).append("\"")
		.append("\"created_time\":").append("\"").append(m_createdTimeStamp).append("\"")
		.append("\"type\":").append("\"").append(m_postType).append("\"")
		.append("\"contents\":").append("\"").append(m_postContents).append("\"")
		.append("\"url\":").append("\"").append(m_postUrl).append("\"")
		.append("\"updated_date\":").append("\"").append(m_updatedDate).append("\"")
		.append("}");
		return json.toString();
	}

	/**
	 * Builder class for Meta data
	 * 
	 * @author dkumar
	 * 
	 */
	public static class MetaDataBuilder {

		private String postId;
		private Date createdTimeStamp;
		private String postType;
		private String postContents;
		private String postUrl;
		private Date updatedDate;

		public MetaDataBuilder(String postId) {
			this.postId = postId;
		}

		public MetaDataBuilder createdTimeStamp(Date createdTimeStamp) {
			this.createdTimeStamp = createdTimeStamp;
			return this;
		}

		public MetaDataBuilder postType(String postType) {
			this.postType = postType;
			return this;
		}

		public MetaDataBuilder postContents(String postContents) {
			this.postContents = postContents;
			return this;
		}

		public MetaDataBuilder postUrl(String postUrl) {
			this.postUrl = postUrl;
			return this;
		}

		public MetaDataBuilder updatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
			return this;
		}

		public MetaData build() {
			return new MetaData(this);
		}
	}

}
