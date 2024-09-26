/* 초기 세팅 */

// 파일 읽기 기능 사용
const fs = require("fs");

// 실행 환경에 따라 입력 경로 다르게 설정
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";

// 입력을 읽고, 문자열로 변환한 뒤, 공백을 제거하고, 각 문자를 배열로 나눔
const input = fs.readFileSync(filepath).toString().trim().split("");

/* 문제 풀기 */

// 숫자 빈도를 저장하는 배열
let count = Array(10).fill(0);

// 각 숫자의 빈도를 셈
input.forEach((num) => {
  count[parseInt(num)]++;
});

// 6과 9는 서로 교환 가능하므로 합쳐서 처리
let sixnineCount = Math.ceil((count[6] + count[9]) / 2);
count[6] = sixnineCount;
count[9] = sixnineCount;

// 가장 많이 필요한 숫자 세트 개수를 계산
const result = Math.max(...count);

// 결과 출력
console.log(result);
