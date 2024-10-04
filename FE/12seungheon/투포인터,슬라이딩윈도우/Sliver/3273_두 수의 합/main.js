const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/투포인터,슬라이딩윈도우/Sliver/3273_두 수의 합/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

let n = parseInt(input[0]);
let data = input[1].split(' ').map(Number);
let x = parseInt(input[2]);

let left = 0
let right = n - 1
let cnt = 0
let sum = 0


// 오름차순
data.sort((a,b)=> a-b);

while ( left < right ) {
    sum = data[left] + data[right]
    // sum과 같을때 카운트 후 left right 이동
    if ( sum == x ) {
        cnt++
        left++
        right--
    }
    // x 보다 작을때 left 이동
    else if ( sum < x ) {
        left++
    }
    // x 보다 클때 right 이동
    else {
        right--
    }
}

console.log(cnt)