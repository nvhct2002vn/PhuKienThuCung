app.controller("home-ctrl", function ($scope, $http) {
    $scope.items = [];

    $scope.loadAll = function () {
        let url = "/api/product/sortDate";
        $http.get(url).then((result) => {
            $scope.items = result.data;
        }).catch((err) => {
            console.log(err);
        });
    }
    $scope.loadAll();
})

