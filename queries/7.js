db.incidents.aggregate([
	{
		"$match": {
		"creationDate" : "Tue Jul 12 03:00:00 EEST 2011"
		}
	},
	{
		"$project": {
			"_id": 1,
			"upvotesCount": {
			    "$size": "$upvotes"
			},
			"upvotes": 1
		}
	},
	{
		"$sort" : {
			"upvotesCount": -1  
		}
	},
	{
		"$limit": 50   
	},
	{
		"$project": {
			"_id": 1
		}   
	}
])