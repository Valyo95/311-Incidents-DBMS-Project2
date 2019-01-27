db.incidents.aggregate([
	{
		"$unwind": "$upvotes" 
	},
	{
		"$group":{
			"_id": "$upvotes.name",
			"userUpvotes":{
            	"$sum":1
         	}
      	}
	},
	{
		"$sort" : {
			"userUpvotes": -1  
		}
	},
	{
		"$limit": 50   
	},
	{
		"$project": {
			"_id": 1,
//			"userUpvotes": 1
		}
	}
])