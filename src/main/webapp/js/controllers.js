angular.module('library.controllers', [])
	/* patron controller */
	.controller('patronController', ['$scope', 'libraryShared', 'libraryApi',
		function ($scope, libraryShared, libraryApi) {
			$scope.heading = libraryShared.patronHeading;
			$scope.search = function () {
				//$scope.searchString = libraryShared.searchString;
				if ($scope.searchString == null || $scope.searchString == '') {
					return;
				}
				libraryApi.findPatron($scope.searchString).success(
					function (response) {
						$scope.rows = response.data;
					}).error(function (a, b, c) {
						console.log(a);
					});
			};
			$scope.setPatron = function (patron) {
				if (patron == null) {
					patron = {};
					patron.id = null;
					patron.name = '';
				}
				libraryShared.patron = patron;
			}
		}])
	/* media controller */
	.controller('mediaController', ['$scope', 'libraryShared', 'libraryApi',
		function ($scope, libraryShared, libraryApi) {
			$scope.heading = libraryShared.mediaHeading;
			$scope.patron = libraryShared.patron;
			$scope.search = function () {
				if ($scope.searchString == null) {
					return;
				}
				libraryApi.findMedia($scope.searchString).success(
					function (response) {
						$scope.rows = response.data;
					}).error(function (a, b, c) {
						console.log(a);
					});
			}
			$scope.setPatronMedia = function (patron, media) {
				libraryShared.patron = patron;
				libraryShared.media = media;
			}
			$scope.setMedia = function (media) {
				libraryShared.media = media;
			};
		}])
	/* media copy controller */
	.controller('mediacopyController', ['$scope', 'libraryShared', 'libraryApi',
		function ($scope, libraryShared, libraryApi) {
			$scope.heading = libraryShared.recordHeading;
			$scope.search = function () {
				if ($scope.searchString == null) {
					return;
				}
				libraryApi.getMediaCopy($scope.searchString).success(
					function (response) {
						$scope.rows = response.data;
					}).error(function (a, b, c) {
						console.log(a);
					});
			}
			$scope.setRecord = function (record) {
				libraryShared.record = record;
			}
		}])
	/* checkout controller */
	.controller('checkoutController', ['$scope', 'libraryShared', 'libraryApi',
		function ($scope, libraryShared, libraryApi) {
			$scope.heading = libraryShared.recordHeading;
			$scope.media = libraryShared.media;
			$scope.patron = libraryShared.patron;
			if ($scope.patron.id == null || $scope.media.id == null) {
				return;
			}
			libraryApi.postCheckout($scope.patron.id, $scope.media.id).success(
				function (response) {
					$scope.rows = response.data;
				}).error(function (a, b, c) {
					console.log(a);
				});
		}])
	/* checkin controller */
	.controller('checkinController', ['$scope', 'libraryShared', 'libraryApi',
		function ($scope, libraryShared, libraryApi) {
			$scope.heading = libraryShared.recordHeading;
			libraryApi.putCheckin(libraryShared.record).success(
				function (response) {
					$scope.rows = response.data;
				}).error(function (a, b, c) {
					console.log(a);
				});
		}])
	/* patron form controller */
	.controller('patronFormController', ['$scope', 'libraryShared', 'libraryApi',
		function ($scope, libraryShared, libraryApi) {
			$scope.heading = libraryShared.patronHeading;
			$scope.getPatron = function () {
				var id = null;
				if (libraryShared.patron != null) {
					id = libraryShared.patron.id;
				}
				libraryApi.getPatron(id).success(
					function (response) {
						$scope.patron = response.data[0];
					}).error(function (a, b, c) {
						console.log(a);
					});
			};
			$scope.save = function () {
				if ($scope.patron == null) {
					return;
				}
				libraryShared.searchString = $scope.patron.name;
				if ($scope.patron.id == null || $scope.patron.id == "") {
					libraryApi.postPatron($scope.patron).success(
						function (response) {
							$scope.rows = response.data;
						}).error(function (a, b, c) {
							console.log(a);
						});
				} else {
					libraryApi.putPatron($scope.patron).success(
						function (response) {
							$scope.rows = response.data;
						}).error(function (a, b, c) {
							console.log(a);
						});
				}
			};
			$scope.reset = function () {
				$scope.patron.id = null;
			}
		}])
	/* librarian media form controller */
	.controller('mediaFormController', ['$scope', 'libraryShared', 'libraryApi',
		function ($scope, libraryShared, libraryApi) {
			$scope.heading = libraryShared.mediaHeading;
			$scope.getMedia = function () {
				var id = null;
				if (libraryShared.media != null) {
					id = libraryShared.media.id;
				}
				libraryApi.getMedia(id).success(
					function (response) {
						$scope.media = response.data[0];
					}).error(function (a, b, c) {
						console.log(a);
					});
			}
			$scope.save = function () {
				if ($scope.media == null) {
					return;
				}
				if ($scope.media.id == null || $scope.media.id == "") {
					libraryApi.postMedia($scope.media).success(
						function (response) {
							$scope.rows = response.data;
						}).error(function (a, b, c) {
							console.log(a);
						});
				} else {
					libraryApi.putMedia($scope.media).success(
						function (response) {
							$scope.rows = response.data;
						}).error(function (a, b, c) {
							console.log(a);
						});
				}
			};
			$scope.reset = function () {
				$scope.media.id = null;
			}
		}])
	/* patron record controller */
	.controller('patronRecordController', ['$scope', 'libraryShared', 'libraryApi',
		function ($scope, libraryShared, libraryApi) {
			$scope.heading = libraryShared.recordHeading;
			libraryApi.getPatronRecord(libraryShared.patron.id).success(
				function (response) {
					$scope.rows = response.data;
				}).error(function (a, b, c) {
					console.log(a);
				});
		}]);
