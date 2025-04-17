#!/usr/bin/env node
function test() {
    var abc = "123";
    console.log(abc === null || abc === void 0 ? void 0 : abc.length);
    var a = [1];
    (a !== null && a !== void 0 ? a : (a = [2])).push(1);
    console.log(a);
    console.log("xxxxxxx");
    return abc;
}
console.log(test());
