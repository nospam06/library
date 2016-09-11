var ohmyappLibrary = angular.module("ohmyappLibrary", ['ngRoute', 'library.shared', 'library.services', 'library.controllers']);

ohmyappLibrary.config(function($routeProvider){
$routeProvider
.when( '/checkout', { controller: 'patronController', templateUrl: 'partial/patron.html' } )
.when( '/checkout/media', { controller: 'mediaController', templateUrl: 'partial/media.html' } )
.when( '/checkout/record', { controller: 'checkoutController', templateUrl: 'partial/record.html' } )
.when( '/checkin', { controller: 'mediacopyController', templateUrl: 'partial/mediacopy.html' } )
.when( '/checkin/record', { controller: 'checkinController', templateUrl: 'partial/record.html' } )
.when( '/librarian/patron', { controller: 'patronController', templateUrl: 'partial/librarianpatron.html' } )
.when( '/librarian/savepatron', { controller: 'patronFormController', templateUrl: 'partial/librarianpatronform.html' } )
.when( '/librarian/media', { controller: 'mediaController', templateUrl: 'partial/librarianmedia.html' } )
.when( '/librarian/savemedia', { controller: 'mediaFormController', templateUrl: 'partial/librarianmediaform.html' } )
.when( '/librarian/record', { controller: 'patronController', templateUrl: 'partial/librarianpatronrecord.html' } )
.when( '/librarian/patronrecord', { controller: 'patronRecordController', templateUrl: 'partial/record.html' } )
.otherwise( { redirectTo: '/checkout' } );
});