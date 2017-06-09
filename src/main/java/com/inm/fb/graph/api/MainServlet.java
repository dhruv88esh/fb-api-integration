package com.inm.fb.graph.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inm.fb.graph.api.connection.FBConnection;
import com.inm.fb.graph.api.db.DBUtils;
import com.inm.fb.graph.api.post.model.MetaData;
import com.inm.fb.graph.api.post.model.PerformanceMetrics;

/**
 * Main servlet for the application
 * 
 * @author dkumar
 *
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		FBConnection fbConnection = new FBConnection();
		MetaData metadata = fbConnection.getLatestPostMetaData();
		PerformanceMetrics pMetrics = fbConnection.getPerformanceMetrics();

		DBUtils.persistMetaData(metadata);
		DBUtils.persistPerformanceMetrics(pMetrics);

		StringBuilder sb = new StringBuilder("{");
		sb.append(metadata.toJson()).append(",").append(pMetrics.toJson())
				.append("}");
		response.getWriter().write(sb.toString());
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
