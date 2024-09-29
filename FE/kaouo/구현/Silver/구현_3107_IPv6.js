/* 초기 세팅 */
const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

/* 문제 풀기 */
// IPv6에서 "::"는 여러 개의 연속된 0 그룹을 생략한 것
// "::"로 나누어 좌측 부분과 우측 부분을 구분함
const parts = input[0].split("::");

// "::" 앞쪽의 주소를 ":"로 나누어 배열로 저장
// 값이 없으면 빈 배열로 처리함
const leftPart = parts[0] ? parts[0].split(":") : [];

// "::" 뒷쪽의 주소를 ":"로 나누어 배열로 저장
// 값이 없으면 빈 배열로 처리함
const rightPart = parts[1] ? parts[1].split(":") : [];

// "::"에 의해 생략된 0 그룹을 채우기 위해
// 전체 8개의 그룹에서 이미 입력된 그룹의 수만큼 뺀
// 나머지를 '0000'으로 채움
const middlePart = new Array(8 - (leftPart.length + rightPart.length)).fill(
  "0000"
);

// 좌측 부분, 생략된 중간 부분, 우측 부분을 하나의 배열로 합침
let fullAddress = [...leftPart, ...middlePart, ...rightPart];

// 각 그룹을 네 자리로 맞추어 출력 형식을 맞춘 후
// ":"로 연결하여 최종 결과 출력
console.log(fullAddress.map((group) => group.padStart(4, "0")).join(":"));
