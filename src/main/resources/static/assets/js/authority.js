app.controller("authority-ctrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = [];

    let url = "http://localhost:8080/rest/account"

    $scope.loadAll = function () {
        $http.get(url).then((result) => {
            $scope.items = result.data;
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.loadAll();

})

