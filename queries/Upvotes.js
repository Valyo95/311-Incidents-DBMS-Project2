var count = 0;
db.getCollection("test").find().forEach(function(doc){
	db.getCollection("test").update({_id :doc._id },{$set : {"upvotes": []}});
})