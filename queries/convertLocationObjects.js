db.incidents.find({}).forEach(function(doc){
    var obj = {};
//    print(doc.location);
	  if (!doc.location || !doc.location.match) return;
	  
	  locParts = doc.location.match("{''latitude'': ''(.*)'', ''longitude'': ''(.*)'', ''needs_recoding'': (.*)}");
	  if (locParts == null) return;
//    print(locParts[1]);
//    print(locParts[2]);
//    print("'" + locParts[3] + "'");
   	
    locCoord = [parseFloat(locParts[1]), parseFloat(locParts[2])];

    obj["type"] = "Point";
    obj["coordinates"] = locCoord;
    obj["needs_recoding"] = locParts[3] != 'False';
    doc.location = obj;
    db.incidents.save(doc);    
});