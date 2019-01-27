db.incidents.aggregate([
	{
		"$unwind": "$upvotes" 
	},
	{
		"$match": {
			"upvotes.name": "manos"
		}   
	},
	{
		"$group": {
			"_id": "$ward"   
		}   
	}
])