const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/DP/Gold/11066_파일 합치기/test.txt').toString().trim().split('\n');

const t = parseInt(input[0]); // 테스트 케이스 수

let index = 1;
for (let test = 0; test < t; test++) {
    const n = parseInt(input[index]); // 파일 개수
    const files = input[index + 1].split(' ').map(Number); // 파일 크기 배열
    index += 2; // 다음 테스트 케이스로 이동하기 위해 인덱스를 2씩 증가

    // 누적합 배열 생성
    const sum = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        sum[i] = sum[i - 1] + files[i - 1];
    }

    // DP 테이블 초기화
    const dp = Array.from({ length: n }, () => Array(n).fill(0));

    // 최소 비용 계산
    for (let len = 1; len < n; len++) { // len은 파일의 길이
        for (let i = 0; i < n - len; i++) {
            const j = i + len;
            dp[i][j] = Infinity;
            for (let k = i; k < j; k++) {
                const cost = dp[i][k] + dp[k + 1][j] + (sum[j + 1] - sum[i]);
                dp[i][j] = Math.min(dp[i][j], cost);
            }
        }
    }

    console.log(dp[0][n - 1]); // 각 테스트 케이스에 대한 최소 비용 출력
}