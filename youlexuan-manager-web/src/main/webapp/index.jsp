<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="angular.min.js"></script>
    <script>
        //创建自定义的模块,模块的名称myApp
        var app = angular.module('myApp',[]);
        //在app模块下，创建一个controller，名称myController,在该controler下可以定义多个方法，多个变量
        //$scope
        app.controller('myController',function ($scope,$http) {
            //在myController中定义一个add方法
            $scope.findAll = function () {
                //调用后台controller
                $http.get("/brand/findAll.do").success(function(resp){
                    $scope.list = resp;
                });
            }
        });

    </script>
</head>
<body ng-app="myApp" ng-controller="myController" ng-init="findAll()">
<table>
    <tr>
        <td>id</td>
        <td>品牌名称</td>
        <td>品牌缩写</td>
    </tr>
    <tr ng-repeat="entity in list">
        <td>{{entity.id}}</td>
        <td>{{entity.name}}</td>
        <td>{{entity.firstChar}}</td>
    </tr>
</table>

</body>
</html>