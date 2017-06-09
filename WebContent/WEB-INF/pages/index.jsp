<%@page import="com.inm.fb.graph.api.connection.FBConnection"%>
<%@page import="com.inm.fb.graph.api.post.model.MetaData"%>
<%@page import="com.inm.fb.graph.api.post.model.PerformanceMetrics"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	FBConnection fbConnection = new FBConnection();
	MetaData metaData = fbConnection.getLatestPostMetaData();
	PerformanceMetrics perfMetrics = fbConnection.getPerformanceMetrics();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="scripts/refresh.js"></script>
<title>Facebook Graph API Integration</title>
</head>

<body style="text-align: center; margin: 0 auto;">
	<h3>Facebook Post Metadata</h3>
	<input id="refresh_data_button" value="Refresh" onclick="refresh_data();"/>
	<br>
	<div>
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Created Date</th>
					<th>Type</th>
					<th>Contents</th>
					<th>Links</th>
					<th>Updated Date</th>
				</tr>
			</thead>
			<tbody id="meta_data_table">
				<tr>
					<td><% metaData.getPostId(); %></td>
					<td><% metaData.getCreatedTimeStamp(); %></td>
					<td><% metaData.getPostType(); %></td>
					<td><% metaData.getPostContents(); %></td>
					<td><% metaData.getPostUrl(); %></td>
					<td><% metaData.getUpdatedDate(); %></td>
				</tr>
			</tbody>
		</table>

	</div>

	<br>
	<br>

	<h3>Facebook Post Performance Metrics</h3>
	<div>
		<table>
			<thead>
				<tr>
					<th>Post Id</th>
					<th>Likes</th>
					<th>Shares</th>
					<th>Comments</th>
					<th>Reaches</th>
					<th>Impressions</th>
					<th>Engagements</th>
					<th>Engagers</th>
					<th>Updated Date</th>
				</tr>
			</thead>
			<tbody id="perf_metrics_table">
				<tr>
					<td><% perfMetrics.getPostId(); %></td>
					<td><% perfMetrics.getLikes(); %></td>
					<td><% perfMetrics.getShares(); %></td>
					<td><% perfMetrics.getComments(); %></td>
					<td><% perfMetrics.getImpressions(); %></td>
					<td><% perfMetrics.getReachs(); %></td>
					<td><% perfMetrics.getEngagements(); %></td>
					<td><% perfMetrics.getEngagers(); %></td>
					<td><% perfMetrics.getUpdatedDate(); %></td>
				</tr>
			</tbody>
		</table>

	</div>
</body>

</html>