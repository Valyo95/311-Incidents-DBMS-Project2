db.incidents.aggregate([
	{
		"$unwind": "$upvotes" 
	},
	//Count same citizen same phone per group
	{
		"$group":{
			"_id": {
			    "id": "$_id",
			    "name": "$upvotes.name",
			    "phone": "$upvotes.phone"
			},
			"samePhoneCnt": {
			 	"$sum": 1   
			}
      	}
	},
	//Keep those groups with counts > 1
	{
		"$match": {
			"samePhoneCnt": {
				"$gt": 1
			}
		}   
	},
	//Filter per incident (an incident might have more than one samePhoneCnt > 1)
	{
		"$group": {
			"_id": "$_id.id"
		}
	},
	{
		"$project": {
			"_id": 1
		}   
	}
], { allowDiskUse: true } )