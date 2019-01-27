'use strict';

var app = angular.module('app', [
	'ngRoute',
    'ngCookies',
    // 'app.services',
    'app.controllers',
    'app.services',
    'cgNotify',
    '720kb.datepicker',
    'checklist-model',
    'infinite-scroll'
])
.config(['$routeProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $locationProvider, $httpProvider) {
	  $routeProvider.when('/', {templateUrl: 'pages/incidents/main.html', controller: 'IncidentsController'});
      $routeProvider.when('/report', {templateUrl: 'pages/report/main.html', controller: 'ReportController'});
      $routeProvider.when('/queries', {templateUrl: 'pages/queries/main.html', controller: 'QueriesController'});
	  
	  $routeProvider.otherwise({redirectTo: '/'});
      
      $locationProvider.html5Mode(true);

        
    }])
    .run(function ($rootScope, $location, $cookieStore, $http) {

    	//FUNCTIONS
    	
	    /* Reset error when a new view is loaded */
	    $rootScope.$on('$viewContentLoaded', function () {
	        delete $rootScope.error;
	    });
	});
        
var appControllers = angular.module("app.controllers", [])
	.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

/**
 * infiniteScroll - Directive for ui-select when drop-down has lots of items
 * usage: "infinite-scroll" on ui-select-choices tag
 */
function infiniteScroll($rootScope, $window, $timeout) {
    return {
        link: function(scope, elem, attrs) {
            var checkWhenEnabled, handler, scrollDistance, scrollEnabled;
            $window = angular.element($window);
            elem.css('overflow-y', 'auto');
            elem.css('overflow-x', 'hidden');
            elem.css('height', 'inherit');
            scrollDistance = 0;
            if (attrs.infiniteScrollDistance != null) {
                scope.$watch(attrs.infiniteScrollDistance, function(value) {
                    return (scrollDistance = parseInt(value, 10));
                });
            }
            scrollEnabled = true;
            checkWhenEnabled = false;
            if (attrs.infiniteScrollDisabled != null) {
                scope.$watch(attrs.infiniteScrollDisabled, function(value) {
                    scrollEnabled = !value;
                    if (scrollEnabled && checkWhenEnabled) {
                        checkWhenEnabled = false;
                        return handler();
                    }
                });
            }
            $rootScope.$on('refreshStart', function(){
                elem.animate({ scrollTop: "0" });
            });
            handler = function() {
                var container, elementBottom, remaining, shouldScroll, containerBottom;
                container = $(elem.children()[0]);
                elementBottom = elem.offset().top + elem.height();
                containerBottom = container.offset().top + container.height();
                remaining = containerBottom - elementBottom ;
                shouldScroll = remaining <= elem.height() * scrollDistance;
                if (shouldScroll && scrollEnabled) {
                    if ($rootScope.$$phase) {
                        return scope.$eval(attrs.infiniteScroll);
                    } else {
                        return scope.$apply(attrs.infiniteScroll);
                    }
                } else if (shouldScroll) {
                    return (checkWhenEnabled = true);
                }
            };
            elem.on('scroll', handler);
            scope.$on('$destroy', function() {
                return $window.off('scroll', handler);
            });
            return $timeout((function() {
                if (attrs.infiniteScrollImmediateCheck) {
                    if (scope.$eval(attrs.infiniteScrollImmediateCheck)) {
                        return handler();
                    }
                } else {
                    return handler();
                }
            }), 0);
        }
    }
};

var services = angular.module('app.services', ['ngResource']);

services.factory('UserService', function ($resource) {

    return $resource('rest/user/:action', {},
        {
            authenticate: {
                method: 'POST',
                params: {'action': 'authenticate'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }
    );
});