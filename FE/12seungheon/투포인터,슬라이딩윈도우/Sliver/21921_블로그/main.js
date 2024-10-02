const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/투포인터,슬라이딩윈도우/Sliver/21921_블로그/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

let [N, X] = input[0].split(' ').map(Number);
let data = input[1].split(' ').map(Number);

let start = 0
let end = X - 1
let cnt = 0
let max = 0

let sum = 0


// sum 값 첫 구간의 합으로 정함
for (i = start; i <= end; i++) {
    sum += data[i]
}

// end가 data.length 까지 연산을 함
while (end < data.length) {
    // 구간의 합이 최대 방문자 수 보다 크면
    if ( sum > max ) {
        // 현재 구간의 합으로 변경
        max = sum
        // 기간 1 설정
        cnt = 1
    }
    // 구간의 합이 최대 방문자 수랑 같으면 카운트해줌
     else if ( sum === max) {
        cnt++
    }
    // data[start]에 위치한 값 빼고 오른쪽으로 한칸씩 이동 
    sum -= data[start]
    start++
    end++
    // 후 data[end]에 위치한 값 더해줌
    sum += data[end]
}


// 출력
if (max == 0) {
    console.log('SAD')
} else {
    console.log(max)
    console.log(cnt)
}