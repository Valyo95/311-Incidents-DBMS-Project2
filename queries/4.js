db.incidents.aggregate([
   {
      "$match": {
         "serviceType" : "Garbage Cart Black Maintenance/Replacement",
         "ward": {
             "$ne": null
         }
      }
   },
   {
      "$group":{
         "_id": "$ward",
         "total": {
            "$sum":1
         }
      }
   },
   {
      "$sort":{
         "total":1
      }
   },
   {
     "$limit": 3  
   },
   {
      "$project": {
         "total":1,
         "_id": 1
      }
   }
])