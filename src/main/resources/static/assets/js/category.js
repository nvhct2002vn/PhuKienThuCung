app.controller("category-ctrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = {};
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
            console.log(result.data);
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.edit = function (id) {
        var uri = `${url}/${id}`;
        $http.get(uri).then((result) => {
            $scope.form = result.data; // lúc này lấy được đối tượng có email tương ứng và gán cho form để chuyển vào input
            $scope.id = id;
        }).catch((err) => {
            console.log(err);
        });
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

    $scope.loadAll();


})

