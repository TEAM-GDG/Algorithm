const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/구현/Silver/1713_후보 추천하기/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const N = parseInt(input[0]); // 사진틀의 개수
const totalVotes = parseInt(input[1]); // 추천 횟수
const votes = input[2].split(' ').map(Number); // 추천 받은 후보들

const frames = []; // 사진틀에 들어간 후보들

// 후보가 있는지 없는지 확인
const findCandidateIndex = (candidate) => {
    return frames.findIndex(f => f.number === candidate);
}

for (let i = 0; i < totalVotes; i++) {
    const candidate = votes[i];
    const candidateIndex = findCandidateIndex(candidate);

    // 이미 사진틀에 있으면 추천 수만 증가
    if (candidateIndex !== -1) {
        frames[candidateIndex].recommendations++;
    } else {
        // 사진틀이 꽉 찼을 때, 가장 추천 수가 적은 후보 제거
        if (frames.length >= N) {
            frames.sort((a, b) => {
                if (a.recommendations === b.recommendations) {
                    return a.time - b.time; // 추천 수가 같으면 오래된 후보 먼저
                }
                return a.recommendations - b.recommendations; // 추천 수가 적은 후보 먼저
            });
            frames.shift(); // 첫 번째 요소 제거 (추천 수가 적거나 오래된 후보)
        }
        // 새로운 후보 추가
        frames.push({
            number: candidate,
            recommendations: 1,
            time: i // 후보가 추가된 시간 (순서)
        });
    }
}

// 최종 후보들을 오름차순으로 정렬
frames.sort((a, b) => a.number - b.number);
console.log(frames.map(f => f.number).join(' '));