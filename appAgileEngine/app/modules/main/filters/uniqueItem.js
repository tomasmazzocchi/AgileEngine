'use strict';
angular.module('main').filter('uniqueItem', function() {

  return function (arr, field) {
    var o = {}, i, l = arr !== undefined ? arr.length : 0 , r = [];
    for(i=0; i<l;i+=1) {
      o[arr[i][field]] = arr[i];
    }
    for(i in o) {
      r.push(o[i]);
    }
    return r;
  };
});