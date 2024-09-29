const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const [[, roomLimitInfo], ...players] = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" "));

const roomLimit = Number(roomLimitInfo);
const rooms = [];

players.forEach(([levelString, name]) => {
  // players 배열을 순회하며 각 플레이어의 level, name 가져오기
  const level = Number(levelString);

  // 해당 플레이어가 들어갈 수 있는 방 탐색
  // 방이 꽉 차지 않았어야 하고
  // 방에 있는 플레이어의 레벨 범위가 +- 10 이내여야 함
  const availableRoom = rooms.find(
    (room) =>
      room.players.length < roomLimit &&
      level >= room.level - 10 &&
      level <= room.level + 10
  );

  // 조건을 만족하는 방이 없으면 새로운 방을 만들어 그 방에 플레이어를 추가
  if (!availableRoom) rooms.push({ players: [[level, name]], level });
  // 조건을 만족하는 방이 있으면 그 방에 플레이어를 추가
  else availableRoom.players.push([level, name]);
});

const sol = [];

// rooms 배열을 순회하며 각 방의 상태 확인
rooms.forEach((room) => {
  // 방의 플레이어 수가 roomLimit와 같으면 "Started!" 추가, 그렇지 않으면 "Waiting!" 추가
  sol.push(room.players.length === roomLimit ? "Started!" : "Waiting!");
  // 각 방의 플레이어를 아이디 순으로 정렬하고 출력 형식에 맞게 변환하여 배열에 추가
  sol.push(
    room.players
      .sort((one, another) => (one[1] < another[1] ? -1 : 1))
      .map((player) => player.join(" "))
      .join("\n")
  );
});

console.log(sol.join("\n"));
