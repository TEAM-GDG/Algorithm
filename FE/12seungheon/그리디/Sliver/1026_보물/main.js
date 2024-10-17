const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/그리디/Sliver/1026_보물/test.txt').toString().trim().split('\n');

let N = input[0].split(' ').map(Number);
let A = input[1].split(' ').map(Number);
let B = input[2].split(' ').map(Number);

A.sort((a,b)=>(a-b));
B.sort((a,b)=>(b-a));

let cnt = 0

for(let i = 0; i < N; i++) {
    cnt += A[i] * B[i]
}

console.log(cnt)
