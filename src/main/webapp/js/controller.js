/**
 * Created by xiaobin on 16/12/13.
 */
angular.module("LDApp.controllers",[])
    .controller("homeController",function ($scope,$state,$ionicPopup) {
        $scope.messageDetailClick = function () {
            $state.go("messageDetail",{});
        }
        $scope.editClick = function () {
            alert("编辑");
        }
        $scope.shareClick = function () {
            alert("分享一下");
            var alertPopup = $ionicPopup.alert({
                title: 'Don\'t eat that!',
                template: 'It might taste good'
            });
            alertPopup.then(function(res) {
                console.log('Thank you for not eating my delicious ice cream cone');
            });

        }

     })
    .controller("foundController",[function () {

    }])
    .controller("mineController",[function () {

    }])