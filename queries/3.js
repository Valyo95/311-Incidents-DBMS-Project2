db.incidents.aggregate([
   {
      "$match": {
         "creationDate" : ISODate("2011-04-01T00:00:00")
         //For tests maybe try a range
         /*{
            "$gt":"2011-01-01T00:00:00",
            "$lt":"2018-01-03T00:00:00"
         }*/
      }
   },
   {
      "$group":{
         "_id": {
             "zipCode": "$zipCode",
             "serviceType": "$serviceType"
         },
         "totalRequestsPerType": {
          	"$sum": 1   
         }
      }
   },
   {
      "$sort": {
      	"_id.zipCode": 1,
      	"totalRequestsPerType": -1   
      }  
   },
   {   
      "$group": {
      	"_id": "$_id.zipCode",
      	"zipRequestTypes": {
      		"$push" : {
      		    //Include total for tests to make sure it looks ok
      			"serviceType": "$_id.serviceType"/*,
      			"total": "$totalRequestsPerType"*/
      		}   
      	}
      }   
   },
   {
      "$project": {
      	"_id": 1,
      	"topThreeTypes": {
      	  "$slice": ["$zipRequestTypes", 3]   
      	}   
      }    
   }
])
