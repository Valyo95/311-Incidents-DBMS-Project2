db.incidents.aggregate([
	{
		"$match": {
		"creationDate" : ISODate("2011-01-03T00:00:00Z")
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