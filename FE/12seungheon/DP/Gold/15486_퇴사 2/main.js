const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/DP/Gold/15486_퇴사 2/test.txt').toString().trim().split('\n');

const n = parseInt(input[0]);  
const data = input.slice(1).map(line => line.split(' ').map(Number));

let dp = new Array(n + 1).fill(0); // 각 날에 얻을 수 있는 최대 수익

for (let i = 0; i < n; i++) {
    const [T, P] = data[i];
    
    // 현재 i일까지의 최대 수익을 i + T 날짜에도 반영
    if (i + T <= n) { 
        dp[i + T] = Math.max(dp[i + T], dp[i] + P);
    }
    
    // 오늘 일을 하지 않는 경우의 최대 수익을 다음 날로 이어줌
    dp[i + 1] = Math.max(dp[i + 1], dp[i]);
}

console.log(dp[n]);
