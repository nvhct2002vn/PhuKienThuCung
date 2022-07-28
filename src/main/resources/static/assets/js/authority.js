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

    $scope.a123 = function () {
        console.log($(`input[type=radio][name=auth1]:checked`).val());
    };

    $scope.authority_changed = function (item) {
        var account = angular.copy(item)
        account.role = 1;
        console.log("account copy: ", account);
        $http.put("/rest/account", account).then((result) => {
            $scope.items[item] = result.data;
            console.log("Phân quyền thành công: ", $scope.items[item].role);
        }).catch((err) => {
            console.log(err);
        });
    }
    $scope.loadAll();

})

