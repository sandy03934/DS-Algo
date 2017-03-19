require.config({
  paths : {
    'angular' : '../bower_components/angular/angular',
    'jquery' : '../bower_components/jquery/dist/jquery',
    'app' : 'app',
    'loginmodule' : 'loginmodule'
  },
  shim :{
    'angular' : {
      deps : ['jquery']
    },
    'app' : {
      deps : ['angular', 'loginmodule']
    },
    'loginmodule' : {
      deps : ['angular']
    }
  }
})


require(['app'], function(){
  angular.bootstrap(document, ['app']);
})
