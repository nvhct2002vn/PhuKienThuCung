app.controller("OrderDetails-ctrl", function ($scope, $http) {
    $scope.item = {};

    let url = "/rest/orders";

    $scope.xacNhanDonHang = function (id) {
        var uri = `${url}/${id}`;
        $http.get(uri).then((result) => {
            $scope.item = result.data;

            $scope.item.status = 2;
            orderItem = angular.copy($scope.item);
            $http.put(uri, orderItem).then((result) => {
                alert("Xác nhận đơn hàng thành công");
                window.location = "/admin/order";
            }).catch((err) => {
                console.log(err);
            });
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.huyDonHang = function (id) {
        var uri = `${url}/${id}`;
        $http.get(uri).then((result) => {
            $scope.item = result.data;

            $scope.item.status = 0;
            orderItem = angular.copy($scope.item);
            $http.put(uri, orderItem).then((result) => {
                alert("Huỷ đơn hàng thành công");
                window.location = "/order/list";
            }).catch((err) => {
                console.log(err);
            });
        }).catch((err) => {
            console.log(err);
        });

    }
    $scope.datLaiDonHang = function (id) {
        var uri = `${url}/${id}`;
        $http.get(uri).then((result) => {
            $scope.item = result.data;

            $scope.item.status = 1;
            orderItem = angular.copy($scope.item);
            $http.put(uri, orderItem).then((result) => {
                alert("Đặt lại đơn hàng thành công");
                window.location = "/order/list";
            }).catch((err) => {
                console.log(err);
            });
        }).catch((err) => {
            console.log(err);
        });

    }
})

