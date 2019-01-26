db.getCollection("incidents").find().forEach(function(doc) { 
    doc.creationDate=new ISODate(doc.creationDate);
    doc.completionDate=new ISODate(doc.completionDate);
    db.incidents.save(doc); 
})
