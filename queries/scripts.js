var count = 0;
db.getCollection("incidents").find().forEach(function(doc){
	db.getCollection("incidents").update({_id :doc._id },{$set : {"incId":NumberInt(count++)}});
})