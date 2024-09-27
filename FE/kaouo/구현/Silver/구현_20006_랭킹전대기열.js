/* 초기 세팅 */
const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

/* 문제 풀기 */
const [[, roomLimitInfo], ...players] = input.map((line) => line.split(" "));

// 방 정원을 숫자로 변환
const roomLimit = Number(roomLimitInfo);

// 방 정보를 저장할 배열 선언
// 이 배열에는 각각의 방이 들어감
const rooms = [];

// 각 플레이어를 순서대로 처리 -> 적절한 방에 배정
players.forEach(([levelString, id]) => {
  const level = Number(levelString);

  // 현재 플레이어가 들어갈 수 있는 방 찾기
  // 조건 : 방의 정원이 다 차지 않았고 첫 번째 플레이어의 레벨 차이가 ±10 이내인 방
  const availableRoom = rooms.find(
    (room) =>
      room.players.length < roomLimit &&
      level >= room.level - 10 &&
      level <= room.level + 10
  );

  // 입장 가능한 방이 없다면 새로운 방을 생성하고 플레이어를 추가함
  if (!availableRoom) rooms.push({ players: [[level, id]], level });
  // 입장 가능한 방이 있으면 해당 방에 플레이어를 추가함
  else availableRoom.players.push([level, id]);
});

// 결과를 출력하기 위한 배열 선언
const sol = [];

// 각 방의 상태를 출력
rooms.forEach((room) => {
  sol.push(room.players.length === roomLimit ? "Started!" : "Waiting!");
  sol.push(
    room.players
      .sort((one, another) => (one[1] < another[1] ? -1 : 1))
      .map((player) => player.join(" "))
      .join("\n")
  );
});

// 결과 출력
console.log(sol.join("\n"));
