package com.myProject.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myProject.connection.MyProjectConnection;
import com.myProject.connection.Queries;
import com.opencsv.CSVWriter;

/**
 * Servlet implementation class ReportServlet
 */
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date beginDate = null , endDate=null;
		String msg = "Report generation Successful";
		String tableName = request.getParameter("tableCategory");
		String bDate = request.getParameter("startDate");
		String eDate = request.getParameter("endDate");
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yy"); 
		try {
			beginDate = formatter.parse(bDate);
			endDate = formatter.parse(eDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		System.out.println("Begin date is: " + beginDate);
		
		System.out.println("End Date is: " + endDate);
		ResultSet rs = null;
		TimeLog timeLog = new TimeLog();

		try {
			Connection conn = MyProjectConnection.connectionMethod();
			PreparedStatement pstmt = conn.prepareStatement(Queries.getQuery(tableName));
			if(tableName.equalsIgnoreCase("all")){
				System.out.println("All report selected.");
				pstmt.setString(1, beginDate.toString());
				pstmt.setString(2, endDate.toString());
			}
			
			pstmt.setQueryTimeout(7200);
			 	
				rs= pstmt.executeQuery();
				System.out.println("ExecuteQuery Execution took in seconds: " + (timeLog.getElapsedTime()));
				ResultSetMetaData metadata = rs.getMetaData();
				int numberOfColumns = metadata.getColumnCount();
				
				 try {   
					 CSVWriter writer = new CSVWriter(new BufferedWriter(new FileWriter("C:\\myProject_report\\" + tableName + ".csv")), ';');
					 writer.writeAll(rs,true);
					 writer.close();
	          }catch (Exception e){
	        	  e.printStackTrace();
	          }
				 System.out.println("Write Execution took in seconds: " + (timeLog.getElapsedTime()));
				 
				
		/*		System.out.println("Write opr starttime:" + System.currentTimeMillis());
				//FileWriter writer = new FileWriter("C:\\myProject_report\\" + tableName + ".txt");
				BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\myProject_report\\" + tableName + ".txt"));
				 //BufferedWriter bufferedWriter = new BufferedWriter(writer, 8192);
				int count = 1;
				while (rs.next() && count<=10000) {
					int i = 1;
					while (i <= numberOfColumns) {
						writer.append(rs.getString(i));
						writer.append(',');
						i++;
					}
					writer.append('\n');
					count++;
					}
				System.out.println("Write Execution took in seconds: " + (timeLog.getElapsedTime()));
				writer.flush();
				writer.close();
		*/
			response.setContentType("text/plain");
			PrintWriter out=response.getWriter();
			out.print(msg);
			out.flush();
			out.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
