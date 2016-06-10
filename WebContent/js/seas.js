/**
 * Created by Aero on 2016/5/28.
 */
var userArticle = angular.module("userArticle", [ 'ngRoute', 'ngAnimate','ngSanitize']);

userArticle.config(function($routeProvider) {
	$routeProvider.when('/edit', {
		templateUrl : 'userArticle/edit.html'
	}).when('/manage', {
		templateUrl : 'userArticle/manage.html',
		controller : 'manageController'
	}).when('/classify', {
		templateUrl : 'userArticle/classify.html',
		controller : 'kindController'
	}).when('/drafts', {
		templateUrl : 'userArticle/drafts.html',
		controller : 'contactController'
	}).when('/detail/:id', {
		templateUrl : 'userArticle/detail.html',
		controller : 'detailController'
	}).otherwise({
		redirectTo : '/manage'
	})
});

userArticle.directive('hello', function() {
    return {
        restrict: 'E',
        transclude: true,
        templateUrl: 'userArticle/detailDirective.html',
        replace: true
    };
});
userArticle.directive('edit', function() {
	return {
		restrict: 'E',
		transclude: true,
		templateUrl: 'userArticle/editDirective.html',
		replace: true,
		scope:true,
		controller : function($scope,$http) {
			$scope.pageClass = 'page-home';
			$scope.title = '';
			$scope.content = '';
			$scope.accessory = '';
			$scope.publish =function(){
						$http({
					method : 'post',
					url : 'articles/insert',
					params:{title:$scope.title,content:$("#content").val(),accessory:$scope.accessory},
				}).success(function(data, status, headers, config) {
				alert(data.status);
				}).error(function(data, status, headers, config) {
					console.log("error...");
				});
				
			}
        },
		link:function(){
			toolbar = [ 'title', 'bold', 'italic', 'underline', 'strikethrough',
						'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|',
						'link', 'image', 'hr', '|', 'indent', 'outdent' ];
				var editor = new Simditor({
					textarea : $('#content'),
					//placeholder : '这里输入内容...',
					toolbar : toolbar, //工具栏
					defaultImage : 'simditor/images/image.png', //编辑器插入图片时使用的默认图片
					upload : {
						url : 'articles/uploadImg', //文件上传的接口地址
						params : null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
						fileKey : 'fileDataFileName', //服务器端获取文件数据的参数名
						connectionCount : 3,
						leaveConfirm : '正在上传文件'
					}
				});
		}
	};
});

// CONTROLLERS ============================================
// home page controller
userArticle.controller('manageController', function($scope, $http) {
	$scope.pageClass = 'page-home';
	$http({
		method : 'GET',
		url : 'articles/list'
	}).success(function(data, status, headers, config) {

		$scope.items = data;
	}).error(function(data, status, headers, config) {
		console.log("error...");
	});

});

userArticle.controller('detailController', function($scope, $http,$routeParams) {
	$scope.pageClass = 'page-home';
	$scope.id = $routeParams.id;
	console.log($scope.id);
	$http({
		method : 'GET',
		url : 'articles/query/'+$scope.id
	}).success(function(data, status, headers, config) {

		$scope.items = data;
	}).error(function(data, status, headers, config) {
		console.log("error...");
	});

});

userArticle.filter('cut', function() {
	return function(value, wordwise, max, tail) {
		if (!value)
			return '';

		max = parseInt(max, 10);
		if (!max)
			return value;
		if (value.length <= max)
			return value;

		value = value.substr(0, max);
		if (wordwise) {
			var lastspace = value.lastIndexOf(' ');
			if (lastspace != -1) {
				value = value.substr(0, lastspace);
			}
		}

		return value + (tail || ' …');
	};
});

// about page controller
userArticle.controller('kindController', function($scope,$http) {
	$scope.pageClass = 'page-about';
	$scope.groupName = '';
	$scope.loadingImg = false;
	$scope.clickInsertGroup =function(){
		$scope.loadingImg = true;
				$http({
			method : 'GET',
			url : 'kind/insert',
			params:{name:$scope.groupName},
		}).success(function(data, status, headers, config) {
		alert(data.status);
		$scope.loadingImg = false;
		}).error(function(data, status, headers, config) {
			console.log("error...");
			$scope.loadingImg = false;
		});
		
	}


});

// contact page controller
userArticle.controller('contactController', function($scope) {
	$scope.pageClass = 'page-contact';
});
