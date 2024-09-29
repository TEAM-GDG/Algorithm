const INPUT_FILE = process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/구현/Silver/20006_랭킹전 대기열/test.txt';
const [[, roomLimitInfo], ...players] = require('fs')
  .readFileSync(INPUT_FILE)
  .toString()
  .trim()
  .split('\n')
  .map((line) => line.split(' '));

// 방 정보 초기화
const roomLimit = Number(roomLimitInfo);
const rooms = [];

// 플레이어 배정 
players.forEach(([levelString, id]) => {
  const level = Number(levelString);

// rooom.fine로 방 찾기, 그 방 레벨 범위 +- 10 내에 플레이어 포함, 없으면 새로운 방 만들어 플레이어 추가
  const availableRoom = rooms.find(
    (room) =>
      room.players.length < roomLimit && level >= room.level - 10 && level <= room.level + 10,
  );

  if (!availableRoom) rooms.push({ players: [[level, id]], level });
  else availableRoom.players.push([level, id]);
});

// 방 상태 출력 방에 다 차면 start 아니면 waiting 출력
const sol = [];
rooms.forEach((room) => {
  sol.push(room.players.length === roomLimit ? 'Started!' : 'Waiting!');
  sol.push(
    room.players
    // one[1] another[1] 을 기준으로 오름차순으로 정렬하는 역할
      .sort((one, another) => (one[1] < another[1] ? -1 : 1))
      // 하나의 문자열로 만듬 
      .map((player) => player.join(' '))
      .join('\n'),
  );
});

console.log(sol.join('\n'));