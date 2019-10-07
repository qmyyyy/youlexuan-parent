app.controller('myUserController' ,function($scope,$controller,myUserService) {

    $controller('baseController', {$scope: $scope});//继承

    $scope.searchEntity={};//定义搜索对象
    $scope.search = function (page,rows) {
        myUserService.search(page,rows,$scope.searchEntity).success(function (resp) {
            $scope.paginationConf.totalItems=resp.total;//总记录数
            $scope.list=resp.rows;//给列表变量赋值
        })
    }

})