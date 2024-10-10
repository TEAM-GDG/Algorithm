const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/DP/Sliver/1904_01 타일/test.txt').toString().trim().split('\n');

let n = parseInt(input[0]);

let dp = new Array(input).fill(0);

dp[1] = 1
dp[2] = 2

for (let i = 3; i <= n; i++) {
    dp[i] = (dp[i - 1] + dp[i - 2]) % 15746
}

console.log(dp[input])