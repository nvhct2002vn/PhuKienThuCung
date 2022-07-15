const app = angular.module("shopping-cart-app", []).controller("shopping-cart-ctrl", function ($scope, $http) {
    $scope.cart = {
        items: [],
        add(id) {
            var item = this.items.find(item => item.id == id)
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/api/product/${id}`).then(result => {
                    result.data.qty = 1;
                    this.items.push(result.data);
                    this.saveToLocalStorage();
                })
            }
        },
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
            console.log(localStorage.getItem("cart"));
        }
    }
})

