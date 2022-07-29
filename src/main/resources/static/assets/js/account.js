app.controller("account-ctrl", function ($scope, $http) {
    $scope.auth = {};
    $scope.items = [];
    $scope.form = {};

    let url = "http://localhost:8080/rest/account"

    $scope.loadAll = function () {
        $http.get(url).then((result) => {
            $scope.items = result.data;
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.authority_changed = function (item, roleGet) {
        var account = angular.copy(item)
        account.role = roleGet;
        console.log("account copy: ", account);
        $http.put("/rest/account", account).then((result) => {
            $scope.items[item] = result.data;
            Swal.fire({
                icon: 'success',
                title: 'Phân quyền tại khoản thành công!',
                showConfirmButton: false,
                timer: 1200
            })
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.register = function () {
        var usernameGet = $scope.form.username;
        var username = $scope.items.find((username) => username.username == usernameGet);

        var emailGet = $scope.form.email;
        var email = $scope.items.find((email) => email.email == emailGet);

        console.log(username);
        if (email != undefined) {
            Swal.fire({
                icon: 'error',
                title: 'Email đã tồn tại!',
                showConfirmButton: false,
                timer: 1200
            })
        } else if (username != undefined) {
            Swal.fire({
                icon: 'error',
                title: 'Tên tài khoản đã tồn tại!',
                showConfirmButton: false,
                timer: 1200
            })
        } else if ($scope.form.password != $scope.form.password2) {
            Swal.fire({
                icon: 'error',
                title: 'Mật khẩu không khớp!',
                showConfirmButton: false,
                timer: 1200
            })
        } else {
            var account = angular.copy($scope.form);
            if (account.photo == null) {
                account.photo = "hiennv.png";
            }
            console.log(account);
            $http.post("/rest/account", account).then((result) => {
                Swal.fire({
                    icon: 'success',
                    title: 'Tạo tài khoản thành công!',
                    text: 'Đang chuyển hướng đến trang đăng nhập!',
                    showConfirmButton: false,
                    timer: 1200
                })
            }).catch((err) => {
                console.log(err);
            });
        }
    }
    $scope.loadAll();

})

