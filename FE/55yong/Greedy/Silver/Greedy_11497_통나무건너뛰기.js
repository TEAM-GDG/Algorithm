const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");
let T = Number(input[0]); // 첫 줄: 테스트 케이스의 개수
let logsList = input.slice(1); // 두 번째 줄부터 각 테스트 케이스별 데이터

let results = [];

for (let i = 0; i < T; i++) {
  let logs = logsList[i * 2 + 1].split(" ").map(Number); // 각 테스트 케이스의 통나무 높이 배열

  // 통나무 높이를 오름차순으로 정렬
  logs.sort((a, b) => a - b);

  let maxDifference = 0;

  // 인접한 통나무 간의 최대 높이 차이를 계산
  for (let j = 2; j < logs.length; j++) {
    maxDifference = Math.max(maxDifference, logs[j] - logs[j - 2]);
  }

  results.push(maxDifference); // 계산된 최대 차이를 결과 배열에 추가
}

console.log(results.join("\n")); // 각 테스트 케이스의 결과 출력
