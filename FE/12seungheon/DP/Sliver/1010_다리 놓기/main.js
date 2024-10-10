const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/DP/Sliver/1010_다리 놓기/test.txt').toString().trim().split('\n');

const T = parseInt(input[0]);  // 테스트 케이스 수

// 각 테스트 케이스에 대해 파스칼의 삼각형으로 조합 계산
for (let i = 1; i <= T; i++) {
    const [k, n] = input[i].split(' ').map(Number);  // k: 서쪽 다리, n: 동쪽 다리

    // 파스칼의 삼각형을 저장할 배열 초기화
    const triangle = Array.from(Array(n + 1), () => Array(n + 1).fill(0));

    // 파스칼의 삼각형 생성
    for (let a = 0; a <= n; a++) {
        for (let b = 0; b <= a; b++) {
            if (b === 0 || b === a) {
                triangle[a][b] = 1; // 양 끝은 항상 1
            } else {
                triangle[a][b] = triangle[a - 1][b - 1] + triangle[a - 1][b];
            }
        }
    }

    // nCk 값 출력 (서쪽에서 동쪽으로 다리를 놓는 경우의 수)
    console.log(triangle[n][k]);
}
