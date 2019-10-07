app.service('myUserService',function($http){

    //搜索
    this.search=function(page,rows,searchEntity){
        return $http.post("../myUser/search.do?page="+page+"&rows="+rows,searchEntity);
    }

})