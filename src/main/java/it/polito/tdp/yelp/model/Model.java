 package it.polito.tdp.yelp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.yelp.db.YelpDAO;

public class Model {

	List<Business> businesses ;
	Map<String,Business> businessIdMap ;
	
	public Model() {
		YelpDAO dao = new YelpDAO() ;
		
		this.businessIdMap = new HashMap<>() ;
		this.businesses = dao.readBusinesses(businessIdMap);
		
		
		/* se il DAO non crea la IdMap, la posso creare nel model
		for(Business b: this.businesses)
			businessIdMap.put(b.getBusinessId(), b) ;
			*/
	}
	
}
