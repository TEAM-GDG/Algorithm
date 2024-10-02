const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/투포인터,슬라이딩윈도우/Sliver/1940_주몽/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

let n = parseInt(input[0]);
let m = parseInt(input[1]);
let data = input[2].split(' ').map(Number);

let cnt = 0;
let end = n-1;
let start = 0;
let result = 0;

data.sort((a,b)=> a-b);

while (start < end) {
    result = data[start] + data[end];
    
    if (result === m) {
        start++;
        end--;
        cnt++;
    } else if (result < m) {
        start++;
    } else if (result > m) {
        end--;
    }
}

console.log(cnt);