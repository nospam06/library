angular.module('library.services', []).factory('libraryApi',
	function ($http) {
		var libraryApi = {};
		libraryApi.url = 'servlet';
		libraryApi.patron = '';

		libraryApi.getPatron = function (id) {
			libraryApi.result = $http({
				url: libraryApi.url + '/librarian/patron/' +id,
				method: 'get'
			});
			return libraryApi.result;
		};
		libraryApi.findPatron = function (search) {
			libraryApi.result = $http({
				url: libraryApi.url + '/checkout/patron',
				method: 'get',
				params: { search: search }
			});
			return libraryApi.result;
		};
		libraryApi.getMedia = function (id) {
			libraryApi.result = $http({
				url: libraryApi.url + '/librarian/media/' + id,
				method: 'get'
			});
			return libraryApi.result;
		};
		libraryApi.findMedia = function (search) {
			libraryApi.result = $http({
				url: libraryApi.url + '/checkout/media',
				method: 'get',
				params: { search: search }
			});
			return libraryApi.result;
		};
		libraryApi.postCheckout = function (patronId, mediaId) {
			libraryApi.result = $http({
				url: libraryApi.url + '/checkout/record',
				method: 'post',
				params: {
					patronId: patronId,
					mediaId: mediaId
				}
			});
			return libraryApi.result;
		};
		libraryApi.getMediaCopy = function (search) {
			libraryApi.result = $http({
				url: libraryApi.url + '/checkin/mediacopy',
				method: 'get',
				params: { search: search }
			});
			return libraryApi.result;
		};
		libraryApi.putCheckin = function (record) {
			libraryApi.result = $http({
				url: libraryApi.url + '/checkin/record',
				method: 'put',
				params: {
					record: record
				}
			});
			return libraryApi.result;
		};
		libraryApi.postPatron = function (patron) {
			libraryApi.result = $http({
				url: libraryApi.url + '/librarian/savepatron',
				method: 'post',
				params: { patron: patron }
			});
			return libraryApi.result;
		};
		libraryApi.putPatron = function (patron) {
			libraryApi.result = $http({
				url: libraryApi.url + '/librarian/savepatron',
				method: 'put',
				params: { patron: patron }
			});
			return libraryApi.result;
		};
		libraryApi.postMedia = function (media) {
			libraryApi.result = $http({
				url: libraryApi.url + '/librarian/savemedia',
				method: 'post',
				params: { media: media }
			});
			return libraryApi.result;
		};
		libraryApi.putMedia = function (media) {
			libraryApi.result = $http({
				url: libraryApi.url + '/librarian/savemedia',
				method: 'put',
				params: { media: media }
			});
			return libraryApi.result;
		};
		libraryApi.getPatronRecord = function (id) {
			libraryApi.result = $http({
				url: libraryApi.url + '/librarian/record/' +id,
				method: 'get'
			});
			return libraryApi.result;
		};
		return libraryApi;
	});	
	