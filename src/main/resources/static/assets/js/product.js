app.controller("product-ctrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = [];
    $scope.cates = [];
    $scope.id = null;

    let url = "http://localhost:8080/api/product"

    $scope.loadAll = function () {
        $http.get(url).then((result) => {
            $scope.items = result.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            });
        }).catch((err) => {
            console.log(err);
        });
        $http.get("/api/category").then((result) => {
            $scope.cates = result.data;
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(url, item).then((result) => {
            $scope.items.push(item);
            $scope.loadAll();
            Swal.fire({
                icon: 'success',
                title: 'Tạo thành công!',
                showConfirmButton: false,
                timer: 1200
            })
            $scope.refesh();
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $scope.id = item.id;
    }

    $scope.update = function () {
        var uri = `${url}/${$scope.id}`;
        var item = angular.copy($scope.form);
        $http.put(uri, item).then((result) => {
            var index = $scope.items.findIndex(item => item.id == $scope.id);
            $scope.items[index] = result.data;
            Swal.fire({
                icon: 'success',
                title: 'Sửa thành công!',
                showConfirmButton: false,
                timer: 1200
            })
        }).catch((err) => {
            console.log(err);
        });
    }

    // sửa trạng thái
    $scope.delete = function (item) {
        var uri = `${url}/${item.id}`;
        var itemCp = angular.copy(item);
        itemCp.status = 0;
        $http.put(uri, itemCp).then((result) => {
            var index = $scope.items.findIndex(itemCp => itemCp.id == item.id)
            $scope.items[index] = result.data;
            Swal.fire({
                icon: 'success',
                title: 'Xoá thành công!',
                showConfirmButton: false,
                timer: 1200
            })
        }).catch((err) => {
            console.log(err);
        });
    }

    $scope.refesh = function () {
        $scope.form = {};
    }

    $scope.imageChanged = function (files) {
        var data = new FormData(); //tạo ra đối tượng formdata
        data.append('file', files[0]); // lấy file ng dụng chọn bỏ vào form data đó
        $http.post('/rest/upload/images', data, { // post lên server với địa chỉ là /rest/upload/images. trong đó images là thư mục
            transformRequest: angular.identity, headers: { 'Content-Type': undefined }
        }).then((result) => {
            $scope.form.image = result.data.name; // upload thành công trả về result , chúng tả chỉ lấy name để hiển thị ảnh
        }).catch((err) => {
            alert("upload image error")
            console.log(err);
        });
    }

    // phân trang
    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        firt() {
            this.page = 0;
        }, prev() {
            this.page--;
            if (this.page < 0) {
                this.page = 0;
            }
        }, next() {
            this.page++;
            if (this.page > this.count - 1) {
                this.page = this.count - 1;
            }
        }, last() {
            this.page = this.count - 1;
        }
    }

    $scope.loadAll();


})

