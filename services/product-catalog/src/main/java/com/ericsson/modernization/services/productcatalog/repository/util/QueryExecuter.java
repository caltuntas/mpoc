package com.ericsson.modernization.services.productcatalog.repository.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryExecuter {

	public static List<Integer> GetBundleRelatedSimpleOfferingIds(int bundleOfferingId) {
		List<Integer> bundleRelatedSimpleOfferingIds = new ArrayList<Integer>(); 
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://77.75.32.147:34251;databaseName=modernization;";
			Connection conn = DriverManager.getConnection(url, "sa", "K852tig684");
			Statement stmt = conn.createStatement();
			ResultSet rs;
			String query = "select clonnedProductOffering_id from ProductOffering (NOLOCK)\r\n"
					+ "where id IN (select relatedProductOfferingId from ProductOfferingRelation (NOLOCK) where productOfferingId = "
					+ bundleOfferingId + ")\r\n" 
					+ "AND productOfferingType_id = 3\r\n" + "AND isDeleted = 0";
			System.out.println(query);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Integer id = rs.getInt("clonnedProductOffering_id");
				System.out.println(id);
				bundleRelatedSimpleOfferingIds.add(id);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return bundleRelatedSimpleOfferingIds;
	}
}