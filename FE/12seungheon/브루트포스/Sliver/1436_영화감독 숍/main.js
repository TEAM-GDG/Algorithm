const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/브루트포스/Sliver/1436_영화감독 숍/test.txt').toString().trim().split('\n');
let N = parseInt(input);

let count = 0;
let num = 0; 

while (count < N) {
  num++;
  if (String(num).includes("666")) {
    count++;
  }
}
console.log(num);

