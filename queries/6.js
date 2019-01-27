db.incidents.aggregate([
   {
      "$project":{
         "coordinates": ["$latitude", "$longitude"],
         "creationDate": 1,
         "serviceType": 1
      }
   },
   {
       "$match" : {
         "creationDate": ISODate("2011-01-01T00:00:00.000Z"),
         "coordinates": {
          "$geoWithin": {
            "$box": [
               	[
                    40.99825021043669,
                    -80.543586730957031
                ],
                [
                    60.139285461134996,
                    -100.996772766113281
                ]
             ]
          }
      	}
      }
   },
   {
      "$group": {
         "_id": "$serviceType",
         "totalRequestsPerType": {
          	"$sum": 1   
         }
      }
   },
   {
      "$sort": {
      	"totalRequestsPerType": -1   
      }
   },
   {
      "$limit": 1   
   },
   {
      "$project": {
        "_id": 1/*,
        "totalRequestsPerType": 0*/
      }   
   }
])