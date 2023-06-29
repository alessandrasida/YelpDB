package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.yelp.model.Business;

public class YelpDAO {
	//non fa parte dello stato del DAO, ma del modello

	public List<Business> readBusinesses(Map<String, Business> businessIdMap) {
		
		Connection conn;
		try {
			conn = DBConnect.getConnection();
			
			String sql = "SELECT * FROM business ";
			List<Business> result = new ArrayList<>();
			
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while( res.next()) {
				String id = res.getString("business_id");
				if(businessIdMap.containsKey(id)){
					result.add(businessIdMap.get(id));
				}
				else {
					Boolean active = res.getString("active").equals("true");
					Business b= new Business( id, 
							res.getString("full_address"),
							active, res.getString("categories"), 
							res.getString("city"),
							res.getInt("review_count"), 
							res.getString("business_name"), 
							res.getString("neighborhoods"), 
							res.getDouble("latitude"), 
							res.getDouble("longitude"), 
							res.getString("state"), 
							res.getDouble("stars"));
					result.add(b);
					businessIdMap.put(b.getBusinessId(), b);
				}
			}
			
			st.close();
			res.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	public Double avarageStars(Business b) {
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "SELECT AVG(stars) AS stelle "
					+ "FROM reviews "
					+ "WHERE business_id = ? ";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, b.getBusinessId());
			ResultSet res = st.executeQuery();
			// queary da solo una riga non serve fare il while
			
			res.first();
			Double stelle = res.getDouble("stelle");
			
			
			conn.close();
			return stelle;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
}