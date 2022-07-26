app.controller("category-ctrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = [];
    $scope.id = null;

    let url = "http://localhost:8080/api/category"
    $scope.loadAll = function () {
        $http.get(url).then((result) => {
            $scope.items = result.data;
            console.log($scope.items);
        }).catch((err) => {
            console.log(err);
        });
    }
    $scope.create = function () {
        var item = angular.copy($scope.form);
        console.log(item);
        $http.post(url, item).then((result) => {
            $scope.items.push(item);
            $scope.loadAll();
            console.log(result.data);
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.edit = function (item) {
        // var uri = `${url}/${id}`;
        // $http.get(uri).then((result) => {
        //     $scope.form = result.data; // lúc này lấy được đối tượng có email tương ứng và gán cho form để chuyển vào input
        //     $scope.id = id;
        // }).catch((err) => {
        //     console.log(err);
        // });

        $scope.form = angular.copy(item);
        $scope.id = item.id;
        console.log(item);
    }

    $scope.update = function () {
        var uri = `${url}/${$scope.id}`;
        var item = angular.copy($scope.form);
        $http.put(uri, item).then((result) => {
            $scope.items[$scope.id] = result.data;
            $scope.loadAll();
        }).catch((err) => {
            console.log(err);
        });
    }

    // sửa trạng thái
    $scope.delete = function (item) {
        var uri = `${url}/${item.id}`;
        var item = angular.copy(item);
        item.status = 0;
        console.log(item);
        $http.put(uri, item).then((result) => {
            $scope.items[$scope.id] = result.data;
            $scope.loadAll();
        }).catch((err) => {
            console.log(err);
        });
    }
    $scope.refesh = function () {
        $scope.form = {};
    }

    $scope.loadAll();


})

app.controller("authority-ctrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = [];

    let url = "http://localhost:8080/api/account"

    $scope.loadAll = function () {
        $http.get(url).then((result) => {
            $scope.items = result.data;
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.loadAll();

})

