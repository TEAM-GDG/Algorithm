const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/그리디/Gold/11000_강의실 배정/test.txt').toString().trim().split('\n');

let N = Number(input[0]);
const data = input.slice(1).map(line => line.split(' ').map(Number));

// 시작 시간 기준으로 수업을 정렬
data.sort((a, b) => a[0] - b[0]);

// 종료 시간을 저장할 배열
let endTimes = [];

// 첫 번째 수업의 종료 시간을 배열에 추가
endTimes.push(data[0][1]);

// 각 수업을 배정하는 루프
for (let i = 1; i < N; i++) {
    const [start, end] = data[i];
    
    // 현재 수업의 시작 시간보다 종료 시간이 작거나 같은 경우, 기존 방을 재사용 가능
    if (endTimes[0] <= start) {
        endTimes.shift(); // 기존 종료 시간을 제거
    }
    
    // 새로운 종료 시간을 추가하고 오름차순으로 정렬
    endTimes.push(end);
    endTimes.sort((a, b) => a - b);
}

// 필요한 강의실 개수 출력
console.log(endTimes.length);
