app.controller("account-ctrl", function ($scope, $http) {
    $scope.auth = {};
    $scope.items = [];
    $scope.form = {};
    $scope.changePassword = {};
    $scope.account = {};

    let url = "http://localhost:8080/rest/account"

    // lấy username để get user theo username để lấy ra id
    $scope.getusername = {
        accountget: { username: $("#username").text().trim() },
    };

    $scope.getAccountByUsername = function () {
        var username = $scope.getusername.accountget.username;
        console.log("Username: ", username);
        $http
            .get(`/rest/account/${username}`)
            .then((result) => {
                $scope.account = result.data;
                console.log("Account nef: ", $scope.account);
            })
            .catch((err) => {
                console.log(err);
            });
    }

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

    $scope.changePassword = function () {

        var account = angular.copy($scope.account);
        console.log("account get: ", account);
        if (!(account.password == $scope.changePassword.password1)) {
            Swal.fire({
                icon: 'error',
                title: 'Mật khẩu hiện tại không đúng!',
                showConfirmButton: false,
                timer: 1200
            })
            return;
        } else if (!($scope.changePassword.password2 == $scope.changePassword.password3)) {
            Swal.fire({
                icon: 'error',
                title: 'Mật khẩu mới không khớp!',
                showConfirmButton: false,
                timer: 1200
            })
            return;
        } else {
            account.password = $scope.changePassword.password3;
            $http.put(url, account).then((result) => {
                Swal.fire({
                    icon: 'success',
                    title: 'Đổi mật khẩu thành công!',
                    showConfirmButton: false,
                    timer: 1200
                })
            }).catch((err) => {
                console.log(err);
            });
        }
    };

    $scope.loadAll();
    $scope.getAccountByUsername();

})

