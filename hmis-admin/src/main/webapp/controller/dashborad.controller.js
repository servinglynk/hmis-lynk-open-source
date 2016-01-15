
app.controller('dashboradCtrl', function($scope,$location,$routeSegment,$http, $timeout,$sessionStorage) {
	$scope.authToken = ($location.search()).code;
	//$scope.sessionToken= $window.localStorage.getItem('sessionToken');
	//$window.localStorage.setItem('authToken',$scope.authToken);
	$sessionStorage.authToken = $scope.authToken;
	if($sessionStorage.isLoggedIn){
		$("#userDetails").html($sessionStorage.account.emailAddress);	
	}
	var sessionToken = $sessionStorage.sessionToken;
	if(!sessionToken) {
		Service.getToken($http,$scope,  //success
			    function(data){ 
					$sessionStorage.sessionToken =data.oAuthAuthorization.accessToken;
					$sessionStorage.expiresIn =data.oAuthAuthorization.expiresIn;
					$sessionStorage.tokenType = data.oAuthAuthorization.tokenType;
					$sessionStorage.isLoggedIn = true;
					$scope.sessionToken=data.oAuthAuthorization.accessToken;
					$scope.expiresIn = data.oAuthAuthorization.expiresIn;
					$scope.tokenType = data.oAuthAuthorization.tokenType;
					
					Service.GetUserInfo($http,$scope,function(data){ 
						//$window.localStorage.setItem('account',data.account);
						$sessionStorage.account = data.account;
						$("#userDetails").html(data.account.emailAddress);
						},
					function() {})
			},
				//error
				function(){})
}
	
    Service.CheckServiceAvailableBulkUpload($http,
    //success
    function(data){$("#divBulkUpload .button-success").css("display", "inline");},
	//error
	function(){$("#divBulkUpload .button-error").css("display", "inline");})
	
	
	
	Service.CheckServiceAvailableUploadFile($http,
    //success
    function(data){$("#divUploadFile .button-success").css("display", "inline");},
	//error
	function(){$("#divUploadFile .button-success").css("display", "inline");})
	  
	  
	 Service.CheckServiceAvailableAuthenticate($http,
    //success
    function(data){$("#divAuthenticate .button-success").css("display", "inline");},
	//error
	function(){$("#divAuthenticate .button-success").css("display", "inline");})
	
	
	
	
	Service.LoadStatistics($http,
    //success
    function(filesCollection){
 	 $scope.managefiles = filesCollection;

    })
  
   
});
