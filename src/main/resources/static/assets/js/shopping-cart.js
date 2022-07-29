const app = angular
  .module("app", [])
  .controller("shopping-cart-ctrl", function ($scope, $http, $rootScope) {
    $scope.quantity,
      ($scope.cart = {
        items: [],
        add(id) {
          var item = this.items.find((item) => item.id == id);
          if (item) {
            item.qty += $scope.quantity;
            this.saveToLocalStorage();
            Swal.fire({
              icon: 'success',
              title: 'Thêm vào rỏ hàng thành công!',
              showConfirmButton: false,
              timer: 1200
            })
          } else {
            $http.get(`/api/product/${id}`).then((result) => {
              result.data.qty = $scope.quantity;
              this.items.push(result.data);
              this.saveToLocalStorage();
            });
          }
        },
        saveToLocalStorage() {
          var json = JSON.stringify(angular.copy(this.items));
          localStorage.setItem("cart", json);
        },
        loadFromLocalStorage() {
          var json = localStorage.getItem("cart");
          this.items = json ? JSON.parse(json) : [];
        },
        //get số lượng sp trong local
        get count() {
          return this.items
            .map((item) => item.qty)
            .reduce((total, qty) => (total += qty), 0);
        },
        //get tổng tiền trong local
        get amount() {
          return this.items
            .map((item) => item.qty * item.price)
            .reduce((total, qty) => (total += qty), 0);
        },
        remove(id) {
          var index = this.items.findIndex((item) => item.id == id);
          this.items.splice(index, 1);
          Swal.fire({
            icon: 'success',
            title: 'Xoá sản phẩm thành công!',
            showConfirmButton: false,
            timer: 1200
          })
          this.saveToLocalStorage();
        },
        clear() {
          localStorage.clear();
          Swal.fire({
            icon: 'success',
            title: 'Xoá sạch rỏ hàng thành công!',
            showConfirmButton: false,
            timer: 1200
          })
          $scope.cart.loadFromLocalStorage();
        },
      });
    $scope.cart.loadFromLocalStorage();

    // lấy username để get user theo username để lấy ra id
    $scope.getusername = {
      accountget: { username: $("#username").text().trim() },
    };

    $scope.order = {
      totalMoney: $scope.cart.amount,
      createDate: new Date(),
      phoneNumber: "",
      address: "",
      status: 1,
      account: { id: null }, //đặt mặc định 1 user, đang tìm cách fix

      //lấy toàn bộ mặt hàng trong rỏ hàng
      get orderDetail() {
        return $scope.cart.items.map((item) => {
          return {
            product: { id: item.id },
            price: item.price,
            quantity: item.qty,
          };
        });
      },
      purchase() {
        // lấy username
        var username = $scope.getusername.accountget.username;
        $http
          .get(`/rest/account/${username}`)
          .then((result) => {
            // gán id lấy đc vào acc.id đc khai báo bên trên
            $scope.order.account.id = result.data.id;
            var order = angular.copy(this);
            $http
              .post("/rest/orders", order)
              .then((result) => {
                $scope.cart.clear();
                alert("Đặt hàng thành công!")
                // Swal.fire({
                //   icon: 'success',
                //   title: 'Đặt hàng thành công!',
                //   // text: 'Đang chuyển đến hoá đơn chi tiết',
                //   showConfirmButton: false,
                //   timer: 1200
                // })
                location.href = "/order/detail/" + result.data.id;
              })
              .catch((err) => {
                Swal.fire({
                  icon: 'error',
                  title: 'Đặt hàng không thành công!',
                  showConfirmButton: false,
                  timer: 1200
                })
              });
          })
          .catch((err) => {
            console.log(err);
          });
      },
    };
  });
