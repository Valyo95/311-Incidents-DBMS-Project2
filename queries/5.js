db.incidents.aggregate([
   {
      "$match":{
         "creationDate":{
            "$gt": ISODate("2011-01-01T00:00:00"),
            "$lt": ISODate("2011-01-03T00:00:00")
         }
      }
   },
   {
      "$group": {
         "_id": "$serviceType",
         "averageCompletion":{
            "$avg": {
            	"$subtract":["$completionDate","$creationDate"]  
            }
      	 }
   	  }
   },
   {   
    	"$project": {
    		"_id": 1,
    		"averageCompletion": 1,
    		"averageInDays": {
    		    "$divide": ["$averageCompletion", 8640]
    		}
    	}   
   }
 ])