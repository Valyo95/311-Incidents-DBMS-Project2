db.incidents.aggregate([
   {
      "$match":{
         "creationDate":{
            "$gt":"2011-01-01T00:00:00",
            "$lt":"2018-01-03T00:00:00"
         },
         "serviceType" : "Garbage Cart Black Maintenance/Replacement"
      }
   },
   {
      "$group":{
         "_id": "$creationDate",
         "total":{
            "$sum":1
         }
      }
   },
   {
      "$project":{
         "total":1,
         "_id": 1
      }
   },
   {
      "$sort":{
         "creationDate":-1
      }
   }
])