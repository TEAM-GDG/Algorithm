const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/투포인터,슬라이딩윈도우/Sliver/12891_DNA 비밀번호/test.txt').toString().trim().split('\n');

// 입력값 처리
const [S, P] = input[0].split(' ').map(Number);
const dna = input[1];
const requiredCounts = input[2].split(' ').map(Number);

// 최소로 필요한 문자 개수
const required = { A: requiredCounts[0], C: requiredCounts[1], G: requiredCounts[2], T: requiredCounts[3] };

// 슬라이딩 윈도우로 조건을 만족하는 부분 문자열 개수 찾기
let result = 0;
let current = { A: 0, C: 0, G: 0, T: 0 };

// 초기 슬라이딩 윈도우 설정
for (let i = 0; i < P; i++) {
  current[dna[i]]++;
}

// 첫 번째 윈도우가 조건을 만족하는지 확인
if (current['A'] >= required['A'] &&
    current['C'] >= required['C'] &&
    current['G'] >= required['G'] &&
    current['T'] >= required['T']) {
  result++;
}

// 슬라이딩 윈도우 이동 (P부터 S까지)
for (let i = P; i < S; i++) {
  // 새로 들어온 문자 추가
  current[dna[i]]++;
  
  // 윈도우에서 나간 문자 제거
  current[dna[i - P]]--;

  // 조건을 만족하는지 확인
  if (current['A'] >= required['A'] &&
      current['C'] >= required['C'] &&
      current['G'] >= required['G'] &&
      current['T'] >= required['T']) {
    result++;
  }
}

// 결과 출력
console.log(result);