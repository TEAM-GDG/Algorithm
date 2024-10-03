const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/투포인터,슬라이딩윈도우/Sliver/15565_귀여운 라이언/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

let [N, K] = input[0].split(' ').map(Number);
let data = input[1].split(' ').map(Number);

let left = 0; // 왼쪽 포인터
let right = 0; // 오른쪽 포인터
let lionCount = 0; // 현재 라이언(1)의 개수
let minLength = Infinity; // 최소 길이 초기화

// 오른쪽 포인터가 배열의 끝에 도달할 때까지 반복
while (right < N) {
    // 오른쪽 포인터가 가리키는 값이 라이언이면 개수 증가
    if (data[right] === 1) {
        lionCount++;
    }

    // 라이언 개수가 K개 이상인 경우
    while (lionCount >= K) {
        minLength = Math.min(minLength, right - left + 1); // 최소 길이 갱신
        // 왼쪽 포인터를 이동하여 구간을 축소
        if (data[left] === 1) {
            lionCount--; // 왼쪽 포인터가 가리키는 값이 라이언이면 개수 감소
        }
        left++; // 왼쪽 포인터 이동
    }

    right++; // 오른쪽 포인터 이동
}

// 결과 출력
console.log(minLength === Infinity ? -1 : minLength);
