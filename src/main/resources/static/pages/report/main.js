'use strict';

appControllers.controller("ReportController", function($scope, $http, $location, $rootScope, $window, notify) {
		
	$scope.endpointPath = '/incident';
	$scope.createPath = '/create';

	$scope.defaultCreateParams = {
		status : "",
		streetAddress : "",
		zipCode : "",
		xCoordinate : "",
		yCoordinate : "",
		ward : "",
		policeDistrict : "",
		communityArea : "",
		latitude : "",
		longitude : "",
		location : ""
	};

	$scope.abandonedVehiclesParams = {
		licensePlate : "",
		model : "",
		color : "",
		currentActivity : "",
		mostRecentAction : "",
		daysAbandoned : "",
		ssa : ""
	};

	$scope.alleyLightsOutParams = {
	};	

	$scope.garbageCartsParams = {
		blackCartsDelivered : "",
		currentActivity : "",
		mostRecentAction : "",
		ssa : ""
	};

	$scope.graffitiRemovalParams = {
		typeOfSurface : "",
		located : "",
		ssa : ""
	};

	$scope.potHolesParams = {
		currentActivity : "",
		mostRecentAction : "",
		potHoles : "",
		ssa : ""
	};

	$scope.rodentBaitingParams = {
		premisesBaited : "",
		premisesWithGarbage : "",
		premisesWithRats : "",
		currentActivity : "",
		mostRecentAction : ""
	};

	$scope.sanitationCodeComplaintsParams = {
		natureOfViolation : ""
	};

	$scope.lightsAllOutParams = {
	};

	$scope.streetLightOneOutParams = {
	};

	$scope.treeDebrisParams = {
		location2 : "",
		currentActivity : "",
		mostRecentAction : ""
	};

	$scope.treeTrimsParams = {
		location2 : ""
	};

	$scope.requestTypes = [
		"ABANDONED VEHICLE",
		"ALLEY LIGHTS OUT",
		"GARBAGE CART",
		"GRAFFITI REMOVAL",
		"POT HOLES",
		"RODENT BAITING",
		"SANITATION CODE",
		"LIGHTS ALL OUT",
		"STREET LIGHT ONE OUT",
		"TREE DEBRIS",
		"TREE TRIMS"
	];

	$scope.selectedRequestType = $scope.requestTypes[0];
	$scope.incidentParams = $.extend({}, $scope.defaultCreateParams, $scope.abandonedVehiclesParams);

	$scope.getIncidentParams = function() {

		switch ($scope.selectedRequestType) {
			case ($scope.requestTypes[0]):
				return $.extend({}, $scope.defaultCreateParams, $scope.abandonedVehiclesParams);
			case ($scope.requestTypes[1]):
				return $.extend({}, $scope.defaultCreateParams, $scope.alleyLightsOutParams);
			case ($scope.requestTypes[2]):
				return $.extend({}, $scope.defaultCreateParams, $scope.garbageCartsParams);
			case ($scope.requestTypes[3]):
				return $.extend({}, $scope.defaultCreateParams, $scope.graffitiRemovalParams);
			case ($scope.requestTypes[4]):
				return $.extend({}, $scope.defaultCreateParams, $scope.potHolesParams);
			case ($scope.requestTypes[5]):
				return $.extend({}, $scope.defaultCreateParams, $scope.rodentBaitingParams);
			case ($scope.requestTypes[6]):
				return $.extend({}, $scope.defaultCreateParams, $scope.sanitationCodeComplaintsParams);
			case ($scope.requestTypes[7]):
				return $.extend({}, $scope.defaultCreateParams, $scope.lightsAllOutParams);
			case ($scope.requestTypes[8]):
				return $.extend({}, $scope.defaultCreateParams, $scope.streetLightOneOutParams);
			case ($scope.requestTypes[9]):
				return $.extend({}, $scope.defaultCreateParams, $scope.treeDebrisParams);
			case ($scope.requestTypes[10]):
				return $.extend({}, $scope.defaultCreateParams, $scope.treeTrimsParams);
		}
	}

	$scope.resetAllParams = function() {
		$scope.defaultCreateParams.status = "";
		$scope.defaultCreateParams.streetAddress = "";
		$scope.defaultCreateParams.zipCode = "";
		$scope.defaultCreateParams.xCoordinate = "";
		$scope.defaultCreateParams.yCoordinate = "";
		$scope.defaultCreateParams.ward = "";
		$scope.defaultCreateParams.policeDistrict = "";
		$scope.defaultCreateParams.communityArea = "";
		$scope.defaultCreateParams.latitude = "";
		$scope.defaultCreateParams.longitude = "";
		$scope.defaultCreateParams.location = "";

		$scope.abandonedVehiclesParams.licensePlate = "";
		$scope.abandonedVehiclesParams.model = "";
		$scope.abandonedVehiclesParams.color = "";
		$scope.abandonedVehiclesParams.currentActivity = "";
		$scope.abandonedVehiclesParams.mostRecentAction = "";
		$scope.abandonedVehiclesParams.daysAbandoned = "";
		$scope.abandonedVehiclesParams.ssa = "";

		$scope.garbageCartsParams.blackCartsDelivered = "";
		$scope.garbageCartsParams.currentActivity = "";
		$scope.garbageCartsParams.mostRecentAction = "";
		$scope.garbageCartsParams.ssa = "";

		$scope.graffitiRemovalParams.typeOfSurface = "";
		$scope.graffitiRemovalParams.located = "";
		$scope.graffitiRemovalParams.ssa = "";

		$scope.potHolesParams.currentActivity = "";
		$scope.potHolesParams.mostRecentAction = "";
		$scope.potHolesParams.potHoles = "";
		$scope.potHolesParams.ssa = "";

		$scope.rodentBaitingParams.premisesBaited = "";
		$scope.rodentBaitingParams.premisesWithGarbage = "";
		$scope.rodentBaitingParams.premisesWithRats = "";
		$scope.rodentBaitingParams.currentActivity = "";
		$scope.rodentBaitingParams.mostRecentAction = "";

		$scope.sanitationCodeComplaintsParams.natureOfViolation = "";

		$scope.treeDebrisParams.location2 = "";
		$scope.treeDebrisParams.currentActivity = "";
		$scope.treeDebrisParams.mostRecentAction = "";

		$scope.treeTrimsParams.location2 = "";	
	}

	//New incident
	$scope.reportIncident = function() {
		//console.log('in createPost');
		$http({
			method : "POST",
			url : $scope.endpointPath + $scope.createPath,
			params : $scope.getIncidentParams(),
			headers : {
			  'Content-Type' : 'application/json'
			}
		})
		.then(
			function(response) {
				$scope.resetAllParams();
				notify({message: "Incident reported!\nService Request Number: " + response.data.srn, duration: 4000});
				console.log(response.data.srn);
				console.log(response.data.id);
			},
			_error)
	}

   function _error(response) {
  		notify({message: response, duration: 5000});
   		console.log(response);
   }

});
		