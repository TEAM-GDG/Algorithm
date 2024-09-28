const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const N = +input[0]; // 사진틀 개수
const K = +input[1]; // 총 추천 횟수
const arr = input[2].split(" ").map(Number); // 추천받은 학생 번호를 배열로 변환
const candidates = new Map(); // 후보자 정보를 저장할 Map 객체

for (let i = 0; i < K; i++) {
  let num = arr[i]; // 현재 추천받은 학생 번호

  // 이미 후보로 등록된 학생이라면 추천 횟수만 증가
  if (candidates.has(num)) {
    candidates.get(num)[0]++; // 추천 횟수 증가
  } else {
    // 사진틀이 꽉 찼다면 후보 중 추천 횟수가 가장 적은 학생을 제거
    if (candidates.size === N) {
      let min = null; // 추천 횟수가 가장 적은 학생을 찾기 위한 변수

      // Map의 모든 후보를 순회
      for ([key, value] of candidates.entries()) {
        if (!min) {
          min = [key, value]; // 첫 번째 학생을 최소값으로 설정
          continue;
        }

        const [minCnt, minTime] = min[1]; // 최소값의 추천 횟수와 등록 시간
        const [nowCnt, nowTime] = value; // 현재 학생의 추천 횟수와 등록 시간

        // 추천 횟수가 더 적거나, 동일한 경우 등록 시간이 더 오래된 학생 선택
        if (nowCnt < minCnt || (nowCnt === minCnt && nowTime < minTime)) {
          min = [key, value]; // 새로운 최소값 갱신
        }
      }

      candidates.delete(min[0]); // 최소값인 학생 제거
    }

    candidates.set(num, [1, i]); // 새로운 학생을 Map에 등록 (추천 횟수 1, 등록 시간 i)
  }
}

let answer = "";
// 후보 학생들을 번호순으로 정렬한 후 출력
[...candidates.keys()]
  .sort((a, b) => a - b) // 번호순 정렬
  .forEach((candidate) => (answer += candidate + " ")); // 결과 문자열에 추가
console.log(answer); // 결과 출력
