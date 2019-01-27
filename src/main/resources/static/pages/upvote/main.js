'use strict';

appControllers.controller("UpvoteController", function($scope, $http, $location, $rootScope, $window, notify) {
		
	$scope.endpointPath = '/incident/upvote/';

	$scope.incidentId = "";
	$scope.name = "";
	$scope.phone = "";
	$scope.address = "";

	$scope.upvote = function() {
		$scope.hasSearched = true;

		$http({
			method : "GET",
			url : $scope.endpointPath + $scope.incidentId,
			params: {
				name: $scope.name,
				phone: $scope.phone,
				address: $scope.address
			},
			headers : {
			  'Content-Type' : 'application/json'
			}
		})
		.then(
			function(response) {
				notify({message: "Successfully upvoted incident with ID " + $scope.incidentId, duration: 5000});
			},
			_error)
	}

   function _error(response) {
  		notify({message: response.data.message, duration: 5000});
   		console.log(response);
   }



});
		