#!/usr/bin/env node

function test(): string {
    let abc = "123";
    console.log(abc?.length);
    let a = [1];
    (a ??= [2]).push(1);
        console.log(a);
        console.log("xxxxxxx");
    return abc;
}

console.log(test());
