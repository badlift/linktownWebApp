/**
 * Created by xiaobin on 16/12/13.
 */
angular.module("LDApp.router",[])

    .config(function($stateProvider, $urlRouterProvider){
        $stateProvider
            .state("tab", {
                url: "/tab",
                abstract: true,
                templateUrl: "views/tabs.html",
            })
            .state("tab.home",{
                url: "/home",
                views:{
                   "home":{
                       templateUrl: "views/home.html",
                       controller: "homeController"
                   }

                }

            })
            .state("tab.found",{
            url: "/found",
            views:{
                "found":{
                    templateUrl: "views/found.html",
                    controller: "foundController"
                }

            }
            .state("tab.mine",{
                    url: "/mine",
                    views:{
                        "mine":{
                            templateUrl: "views/mine.html",
                            controller: "mineController"
                        }

                    }

                })

        })

    $urlRouterProvider.otherwise("/tab/home");
   });
