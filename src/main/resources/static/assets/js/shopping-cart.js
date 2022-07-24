const app = angular.module("app", []).controller("shopping-cart-ctrl", function ($scope, $http, $rootScope) {
    $scope.quantity,

        $scope.cart = {
            items: [],
            add(id) {
                var item = this.items.find(item => item.id == id)
                if (item) {
                    item.qty += $scope.quantity;
                    this.saveToLocalStorage();
                } else {
                    $http.get(`/api/product/${id}`).then(result => {
                        result.data.qty = $scope.quantity;
                        this.items.push(result.data);
                        this.saveToLocalStorage();
                    })
                }
            },
            saveToLocalStorage() {
                var json = JSON.stringify(angular.copy(this.items));
                localStorage.setItem("cart", json);
                console.log(localStorage.getItem("cart"));
            },
            loadFromLocalStorage() {
                var json = localStorage.getItem("cart");
                this.items = json ? JSON.parse(json) : [];
            },
            //get số lượng sp trong local
            get count() {
                return this.items.map(item => item.qty).reduce((total, qty) => total += qty, 0);
            },
            //get tổng tiền trong local        
            get amount() {
                return this.items.map(item => item.qty * item.price).reduce((total, qty) => total += qty, 0);
            },
            remove(id) {
                var index = this.items.findIndex(item => item.id == id);
                this.items.splice(index, 1);
                this.saveToLocalStorage();
            }
            , clear() {
                localStorage.clear();
                $scope.cart.loadFromLocalStorage();
            }
        }
    $scope.cart.loadFromLocalStorage();

    // lấy username để get user theo username để lấy ra id
    $scope.getusername = {
        accountget: { username: $("#username").text() },
    }

    $scope.order = {
        createDate: new Date,
        phoneNumber: "",
        address: "",
        status: 1,
        account: { id: null }, //đặt mặc định 1 user, đang tìm cách fix

        //lấy toàn bộ mặt hàng trong rỏ hàng
        get orderDetail() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        purchase() {
            // lấy username
            var username = $scope.getusername.accountget.username;
            console.log("Lấy đc user thành công: ", username);
            $http.get(`/rest/account/${username}`).then((result) => {
                // gán id lấy đc vào acc.id đc khai báo bên trên
                $scope.order.account.id = result.data.id;
                console.log("Lấy được id:" + $scope.order.account.id);
                var order = angular.copy(this);
                console.log("coppy order thành công: ", order);
                $http.post("/rest/orders", order).then((result) => {
                    alert("Đặt hàng thành công");
                    $scope.cart.clear();
                    location.href = "/order/detail/" + result.data.id;
                }).catch((err) => {
                    alert("Lỗi đặt hàng");
                    console.log(err);
                });
            }).catch((err) => {
                console.log(err);
            });
        }
    }


})

