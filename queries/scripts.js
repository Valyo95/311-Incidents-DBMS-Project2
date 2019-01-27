var count = 0;
db.getCollection("incidents").find().forEach(function(doc){
	db.getCollection("incidents").update({_id :doc._id },{$set : {"incId":NumberInt(count++)}});
});

//Rename properties
db.incidents.updateMany( {}, {
	$rename: {
		"Creation Date" : "creationDate",
	    "Status" : "status",
	    "Completion Date" : "completionDate",
	    "Service Request Number" : "srn",
	    "Type of Service Request" : "serviceType",
	    "License Plate" : "vehicleLicence",
	    "Model" : "vehicleModel",
	    "Vehicle Color" : "vehicleColor",
	    "Current Activity" : "currentActivity",
	    "Most Recent Action" : "mostRecentAction",
	    "Days Reported" : "daysReported",
	    "Street Address" : "streetAddress",
	    "ZIP Code" : "zipCode",
	    "X Coordinate" : "xCoord",
	    "Y Coordinate" : "yCoord",
	    "Ward" : "ward",
	    "Police District" : "policeDistrict",
	    "Community Area" : "communityArea",
	    "SSA" : "ssa",
	    "Latitude" : "latitude",
	    "Longitude" : "longitude",
	    "Location" : "location"
	}
});

//Small Populate for upvotes
db.incidents.find({
	"creationDate": ISODate("2011-01-01T00:00:00.000+0000")
})
.forEach(function(myDoc) {
    db.incidents.update(myDoc, {
        $set : {"upvotes": [] }}, false, true);  
});

db.incidents.find({
	"creationDate": ISODate("2011-01-01T00:00:00.000+0000")
})
.forEach(function(myDoc) {
    db.incidents.update(myDoc, {
        "$push" : {"upvotes": {
        	"name": "manos",
        	"street": "praxitelous",
        	"phone": "12345"   
        } }}, false, true);  
});

db.incidents.find({
	"creationDate": ISODate("2011-01-01T00:00:00.000+0000")
})
.forEach(function(myDoc) {
    db.incidents.update(myDoc, {
        "$push" : {"upvotes": {
        	"name": "manos",
        	"street": "otinanai",
        	"phone": "12345"   
        } }}, false, true);  
});

db.incidents.find({
	"creationDate": ISODate("2011-01-01T00:00:00.000+0000")
})
.forEach(function(myDoc) {
    db.incidents.update(myDoc, {
        "$push" : {"upvotes": {
        	"name": "valentin",
        	"street": "kapoukapou",
        	"phone": "123456"   
        } }}, false, true);  
});
