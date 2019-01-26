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
      "$group":{
         "_id": "$serviceType",
         "total":{
            "$sum":1
         }
      }
   },
   {
      "$project":{
         "total": 1,
         "_id": 1
      }
   },
   {
      "$sort":{
         "total":-1
      }
   }
])