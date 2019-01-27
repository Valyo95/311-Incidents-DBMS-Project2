'use strict';

appControllers.controller("QueriesController", function($scope, $http, $location, $rootScope, $window, notify) {
		
	$scope.endpointPath = '/query/';

	$scope.startDate = "";
	$scope.endDate = "";
	$scope.type = "";
	$scope.day = "";
	$scope.name = "";

	$scope.lat1 = "";
	$scope.lon1 = "";
	$scope.lat2 = "";
	$scope.lon2 = "";
	
	$scope.queryNumbers = [
		"1",
		"2",
		"3",
		"4",
		"5",
		"6",
		"7",
		"8",
		"9",
		"10",
		"11"
	];
	$scope.queryNumber = $scope.queryNumbers[0];

	$scope.runQuery = function() {
		var url = $scope.endpointPath + $scope.queryNumber;

		switch ($scope.queryNumber) {
			case ("1"):
				url = url + "?start=" + $scope.startDate + "&end=" + $scope.endDate;
				break;
			case ("2"):
				url = url + "?start=" + $scope.startDate + "&end=" + $scope.endDate
					  + "&type=" + $scope.type;
				break;
			case ("3"):
				url = url + "?day=" + $scope.day;
				break;
			case ("4"):
				url = url + "?type=" + $scope.type;
				break;
			case ("5"):
				url = url + "?start=" + $scope.startDate + "&end=" + $scope.endDate;
				break;
			case ("6"):
				url = url + "?x1=" + $scope.lat1 + "&y1=" + $scope.lon1
					  + "&x2=" + $scope.lat2 + "&y2=" + $scope.lon2
					  + "&day=" + $scope.day;
				break;
			case ("7"):
				url = url + "?day=" + $scope.day;
				break;
			case ("11"):
				url = url + "?name=" + $scope.name;
				break;
			default:
				break;
		}

		$window.open(url, '_blank');
	}

});
		