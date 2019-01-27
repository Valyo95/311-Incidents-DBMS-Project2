package com.incidents.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jongo.marshall.jackson.oid.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="incidents")
//@Document(collection="test")
public class Incident implements Serializable {
	private static final long serialVersionUID = 1L;

	@ObjectId @Id
	private String id;
	
	@Field("incId")
	private long incId;

	@Field("srn")
	private String srn;

	@Field("status")
	private String status;
	
	@Field("serviceType")
	private String serviceType;

	@Field("streetAddress")
	private String streetAddress;

	@Field( "zipCode")
	private String zipCode;
	
	@Field( "ssa")
	private String ssa;

	@Field( "xCoord")
	private Double xCoordinate;

	@Field("yCoord")
	private Double yCoordinate;

	@Field("ward")
	private Integer ward;

	@Field("policeDistrict")
	private Integer policeDistrict;

	@Field("communityArea")
	private Integer communityArea;

	@Field( "latitude")
	private Double latitude;

	@Field( "longitude")
	private Double longitude;

	@Field("location")
	private String location;
	
	@Field("location2")
	private String location2;

	@Field("creationDate")
	private String creationDate;

	@Field("completionDate")
	private String completionDate;

	
	
	
	@Field("vehicleLicence")
	private String licensePlate;

	@Field("vehicleModel")
	private String model;

	@Field("vehicleColor")
	private String color;

	@Field("currentActivity")
	private String currentActivity;

	@Field("mostRecentAction")
	private String mostRecentAction;

	@Field("daysReported")
	private Double daysAbandoned;
	
	@Field("BLACK_CARTS_DELIEVRED")
	private Long blackCartsDelivered;
	
	
	
	
	@Field("TYPE_OF_SURFACE")
	private String typeOfSurface;

	@Field("LOCATED")
	private String located;
	
	
	
	
	@Field("POT_HOLS")
	private Double potHoles;
	
	
	
	
	
	@Field("PREMISES_BAITED")
	private Double premisesBaited;

	@Field("PREMISES_WITH_GARBAGE")
	private Double premisesWithGarbage;

	@Field("PREMISES_WITH_RATS")
	private Double premisesWithRats;

	
	
	
	
	@Field("NATURE_OF_VIOLATION")
	private String natureOfViolation;



	@Field("upvotes")
	private List<Citizen> upvotes;




	public Incident(String id, String srn, String status, String type, String streetAddress, String zipCode,
			Double xCoordinate, Double yCoordinate, Integer ward, Integer policeDistrict, Integer communityArea,
			Double latitude, Double longitude, String location, String createdAt, String completionDate,
			String licensePlate, String model, String color, String currentActivity, String mostRecentAction,
			Double daysAbandoned, Long blackCartsDelivered, String typeOfSurface, String located, Double potHoles,
			Double premisesBaited, Double premisesWithGarbage, Double premisesWithRats, String natureOfViolation, String ssa, String location2) {
		super();
		this.id = id;
		this.srn = srn;
		this.status = status;
		this.serviceType = type;
		this.streetAddress = streetAddress;
		this.zipCode = zipCode;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.ward = ward;
		this.policeDistrict = policeDistrict;
		this.communityArea = communityArea;
		this.latitude = latitude;
		this.longitude = longitude;
		this.location = location;
		this.creationDate  = createdAt;
		this.completionDate = completionDate;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
		this.currentActivity = currentActivity;
		this.mostRecentAction = mostRecentAction;
		this.daysAbandoned = daysAbandoned;
		this.blackCartsDelivered = blackCartsDelivered;
		this.typeOfSurface = typeOfSurface;
		this.located = located;
		this.potHoles = potHoles;
		this.premisesBaited = premisesBaited;
		this.premisesWithGarbage = premisesWithGarbage;
		this.premisesWithRats = premisesWithRats;
		this.natureOfViolation = natureOfViolation;
		this.upvotes = new ArrayList<Citizen>();
		this.ssa = ssa;
		this.location2 = location2;
	}

	public List<Citizen> getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(List<Citizen> upvotes) {
		this.upvotes = upvotes;
	}
	
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getLocation2() {
		return location2;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public String getSsa() {
		return ssa;
	}

	public void setSsa(String ssa) {
		this.ssa = ssa;
	}

	public Incident() {
		super();
		// TODO Auto-generated constructor stub
	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getSrn() {
		return srn;
	}





	public void setSrn(String srn) {
		this.srn = srn;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	public String getStreetAddress() {
		return streetAddress;
	}





	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}





	public String getZipCode() {
		return zipCode;
	}





	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}





	public Double getxCoordinate() {
		return xCoordinate;
	}





	public void setxCoordinate(Double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}





	public Double getyCoordinate() {
		return yCoordinate;
	}





	public void setyCoordinate(Double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}





	public Integer getWard() {
		return ward;
	}





	public void setWard(Integer ward) {
		this.ward = ward;
	}





	public Integer getPoliceDistrict() {
		return policeDistrict;
	}





	public void setPoliceDistrict(Integer policeDistrict) {
		this.policeDistrict = policeDistrict;
	}





	public Integer getCommunityArea() {
		return communityArea;
	}





	public void setCommunityArea(Integer communityArea) {
		this.communityArea = communityArea;
	}





	public Double getLatitude() {
		return latitude;
	}





	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}





	public Double getLongitude() {
		return longitude;
	}


	



	public long getIncId() {
		return incId;
	}

	public void setIncId(long incId) {
		this.incId = incId;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}





	public String getLocation() {
		return location;
	}





	public void setLocation(String location) {
		this.location = location;
	}





	public String getCreationDate() {
		return creationDate ;
	}





	public void setCreationDate(String createdAt) {
		this.creationDate  = createdAt;
	}





	public String getCompletionDate() {
		return completionDate;
	}





	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}





	public String getLicensePlate() {
		return licensePlate;
	}





	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}





	public String getModel() {
		return model;
	}





	public void setModel(String model) {
		this.model = model;
	}





	public String getColor() {
		return color;
	}





	public void setColor(String color) {
		this.color = color;
	}





	public String getCurrentActivity() {
		return currentActivity;
	}





	public void setCurrentActivity(String currentActivity) {
		this.currentActivity = currentActivity;
	}





	public String getMostRecentAction() {
		return mostRecentAction;
	}





	public void setMostRecentAction(String mostRecentAction) {
		this.mostRecentAction = mostRecentAction;
	}





	public Double getDaysAbandoned() {
		return daysAbandoned;
	}





	public void setDaysAbandoned(Double daysAbandoned) {
		this.daysAbandoned = daysAbandoned;
	}





	public Long getBlackCartsDelivered() {
		return blackCartsDelivered;
	}





	public void setBlackCartsDelivered(Long blackCartsDelivered) {
		this.blackCartsDelivered = blackCartsDelivered;
	}





	public String getTypeOfSurface() {
		return typeOfSurface;
	}





	public void setTypeOfSurface(String typeOfSurface) {
		this.typeOfSurface = typeOfSurface;
	}





	public String getLocated() {
		return located;
	}





	public void setLocated(String located) {
		this.located = located;
	}





	public Double getPotHoles() {
		return potHoles;
	}





	public void setPotHoles(Double potHoles) {
		this.potHoles = potHoles;
	}





	public Double getPremisesBaited() {
		return premisesBaited;
	}





	public void setPremisesBaited(Double premisesBaited) {
		this.premisesBaited = premisesBaited;
	}





	public Double getPremisesWithGarbage() {
		return premisesWithGarbage;
	}





	public void setPremisesWithGarbage(Double premisesWithGarbage) {
		this.premisesWithGarbage = premisesWithGarbage;
	}





	public Double getPremisesWithRats() {
		return premisesWithRats;
	}





	public void setPremisesWithRats(Double premisesWithRats) {
		this.premisesWithRats = premisesWithRats;
	}





	public String getNatureOfViolation() {
		return natureOfViolation;
	}





	public void setNatureOfViolation(String natureOfViolation) {
		this.natureOfViolation = natureOfViolation;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	@Override
	public String toString() {
		return "Incident [id=" + id + ", srn=" + srn + ", status=" + status + ", type=" + serviceType + ", streetAddress="
				+ streetAddress + ", zipCode=" + zipCode + ", xCoordinate=" + xCoordinate + ", yCoordinate="
				+ yCoordinate + ", ward=" + ward + ", policeDistrict=" + policeDistrict + ", communityArea="
				+ communityArea + ", latitude=" + latitude + ", longitude=" + longitude + ", location=" + location
				+ ", createdAt=" + creationDate  + ", completionDate=" + completionDate + ", licensePlate=" + licensePlate
				+ ", model=" + model + ", color=" + color + ", currentActivity=" + currentActivity
				+ ", mostRecentAction=" + mostRecentAction + ", daysAbandoned=" + daysAbandoned
				+ ", blackCartsDelivered=" + blackCartsDelivered + ", typeOfSurface=" + typeOfSurface + ", located="
				+ located + ", potHoles=" + potHoles + ", premisesBaited=" + premisesBaited + ", premisesWithGarbage="
				+ premisesWithGarbage + ", premisesWithRats=" + premisesWithRats + ", natureOfViolation="
				+ natureOfViolation + "]";
	}

	
	
	
	



}
