const app = angular.module("app", []).controller("shopping-cart-ctrl", function ($scope, $http) {
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
})

