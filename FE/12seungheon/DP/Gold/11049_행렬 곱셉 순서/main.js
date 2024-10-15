const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/DP/Gold/11049_행렬 곱셉 순서/test.txt').toString().trim().split('\n');

const n = parseInt(input[0]);  // 행렬의 개수
const data = input.slice(1).map(line => line.split(' ').map(Number));

// DP 테이블을 초기화합니다.
const dp = Array.from({ length: n }, () => Array(n).fill(0));

// 비용 테이블 초기화
for (let len = 1; len < n; len++) {  // len은 연속된 행렬의 길이
    for (let i = 0; i < n - len; i++) {
        const j = i + len;
        dp[i][j] = Infinity;
        for (let k = i; k < j; k++) {
            const cost = dp[i][k] + dp[k + 1][j] + data[i][0] * data[k][1] * data[j][1];
            dp[i][j] = Math.min(dp[i][j], cost);
        }
    }
}

console.log(dp[0][n - 1]);  // 최소 곱셈 연산 횟수를 출력
