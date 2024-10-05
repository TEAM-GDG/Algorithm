const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/투포인터,슬라이딩윈도우/Gold/1806_부분합/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const [N, S] = input[0].split(" ").map(Number);
const numArr = input[1].split(" ").map(Number);

let start = 0;
let end = 0;
let min = Number.MAX_VALUE;

let sum = numArr[0];
while (start < N && end < N) { // 배열 범위 내에서 반복
    if (sum >= S) { // 부분합이 S 이상일 때
        // 최소 길이를 갱신 (현재 구간의 길이가 더 짧다면 갱신)
        if (min > end - start + 1) {
            min = end - start + 1;
        }
    }
    
    // 부분합이 S보다 작으면 끝점을 증가시켜 범위를 확장
    if (sum <= S) {
        end++; // 끝점 이동
        sum += numArr[end]; // 새로운 끝점의 값을 부분합에 더해줌
    } else { // 부분합이 S 이상이면 시작점을 증가시켜 범위를 줄임
        sum -= numArr[start]; // 현재 시작점의 값을 부분합에서 빼줌
        start++; // 시작점 이동
    }
}

// 최소 길이가 갱신되지 않았다면 불가능한 경우이므로 0을 출력, 아니면 최소 길이 출력
min === Number.MAX_VALUE ? console.log(0) : console.log(min);