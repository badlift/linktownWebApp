/**
 * Created by xiaobin on 16/12/13.
 */
angular.module("LDApp",[
    "ionic",
    "LDApp.controllers",
    "LDApp.router"
    ])
    .config(['$ionicConfigProvider', function($ionicConfigProvider) {

        $ionicConfigProvider.tabs.position('bottom'); // other values: top

    }])

    // .run(function($ionicPlatform, $http, messageService, dateService) {
    //
    //     var url = "";
    //     if (ionic.Platform.isAndroid()) {
    //         url = "/android_asset/www/";
    //     }
    //
    //     // if (localStorage.getItem("messageID") === null) {
    //
    //     $http.get(url + "data/json/messages.json").then(function(response) {
    //         // localStorageService.update("messages", response.data.messages);
    //         messageService.init(response.data.messages);
    //
    //     });
    //     $http.get(url + "data/json/friends.json").then(function(response){
    //         console.log(response.data.results);
    //     });
    //     // }
    //     $ionicPlatform.ready(function() {
    //         // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    //         // for form inputs)
    //
    //
    //         if (window.cordova && window.cordova.plugins.Keyboard) {
    //             cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    //         }
    //         if (window.StatusBar) {
    //             StatusBar.styleDefault();
    //         }
    //     });
    // });
