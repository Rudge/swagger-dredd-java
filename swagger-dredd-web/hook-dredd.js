var hooks = require('hooks');
var lowerCase = require('lower-case');

hooks.beforeEach(function (transaction) {
    hooks.log('before each >> ' + transaction.id);

    if(transaction.expected.headers['Content-Type'] != undefined){
        transaction.expected.headers['Content-Type'] = lowerCase(transaction.expected.headers['Content-Type']);
    }
    //Enable validation errors
    if(transaction.expected.statusCode == 400 || transaction.expected.statusCode == 404){
        transaction.skip = false;
    }
});

hooks.before("user-controller > /api/user/{id} > Return User by Id > 200 > application/json;charset=UTF-8", function (transaction) {
  transaction.fullPath = transaction.fullPath.replace('%7Bid%7D', 1);
  transaction.request.uri = transaction.fullPath;
  hooks.log('before path >> ' + transaction.fullPath);
});

//hooks.before("user-controller > /api/user/{id} > Return User by Id > 400 > application/json;charset=UTF-8", function (transaction) {
//  hooks.log('before getUserId >> ' + transaction.id);
//  transaction.fullPath = transaction.fullPath.replace('getUserId', 1);
//  hooks.log('before path >> ' + transaction.fullPath);
//});

//hooks.before("user-controller > /api/user > Create User > 204", function (transaction) {
//  transaction.fullPath = transaction.fullPath.replace('%7Bid%7D', 1);
//  transaction.request.uri = transaction.fullPath;
//  hooks.log('before path >> ' + transaction.fullPath);
//});
//user-controller > /api/user > Update User > 400
//user-controller > /api/user/{id} > Delete User by id > 400

hooks.before("user-controller > /api/user/{id} > Delete User by id > 204", function (transaction) {
  transaction.fullPath = transaction.fullPath.replace('%7Bid%7D', 1);
  transaction.request.uri = transaction.fullPath;
  hooks.log('before path >> ' + transaction.fullPath);
});