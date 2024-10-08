const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/DP/Sliver/11051_이항 계수2/test.txt').toString().trim().split(' ');

const n = parseInt(input[0]);
const k = parseInt(input[1]);

// 파스칼의 삼각형을 저장할 배열 초기화
const triangle = Array.from(Array(n + 1), () => Array(n + 1).fill(0));

// 파스칼의 삼각형 생성
for (let i = 0; i <= n; i++) {
    for (let j = 0; j <= i; j++) {
        if (j === 0 || j === i) {
            triangle[i][j] = 1; // 양 끝은 항상 1
        } else {
            triangle[i][j] = (triangle[i - 1][j - 1] + triangle[i - 1][j]) % 10007; // 위의 두 값의 합을 10007로 나눈 나머지
        }
    }
}

// n번째 줄의 k번째 값 출력 (결과를 10007로 나눈 나머지)
console.log(triangle[n][k] % 10007);
