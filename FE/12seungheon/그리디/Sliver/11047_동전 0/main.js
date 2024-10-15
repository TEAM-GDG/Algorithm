const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/그리디/Sliver/11047_동전 0/test.txt').toString().trim().split('\n');

let [n, k] = input[0].split(' ').map(Number);
let data = input.slice(1).map(Number);

data.sort((a,b) => (b-a))

let cnt = 0
let nam = k

for (let i = 0; i < data.length; i++) {
    if(data[i] <= nam) {
        cnt += Math.floor(nam / data[i])
        nam %= data[i]
    }
}

console.log(cnt)