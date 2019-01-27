db.incidents.aggregate([
	{
		"$unwind": "$upvotes" 
	},
	//Find citizen-ward votes
	{
		"$group":{
			"_id": {
			    "name": "$upvotes.name",
			    "ward": "$ward"
			}
      	}
	},
	//Count them by grouping for each citizen
	//Second group created in order to assert ward uniqueness
	{
		"$group": {
			"_id": "$_id.name",
			"wardCount": {
				"$sum": 1
			}
		}   
	},
	{
		"$sort" : {
			"wardCount": -1
		}
	},
	{
		"$limit": 50   
	},
	{
		"$project": {
			"_id": 1,
//			"wardCount": 1
		}
	}
])