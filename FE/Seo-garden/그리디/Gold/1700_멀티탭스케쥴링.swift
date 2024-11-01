let inp = readLine()!.split(separator: " ").map { Int($0)! }
let N = inp[0], K = inp[1]
let list = readLine()!.split(separator: " ").map { Int($0)! }

var keep = [Int]()
var pull = 0

for i in 0..<K {
    // 만약 현재 작업이 이미 플러그에 꽂혀 있다면 넘어간다.
    if keep.contains(list[i]) {
        continue
    }
    // 플러그에 여유가 있다면 현재 작업을 추가한다.
    else if keep.count < N {
        keep.append(list[i])
    }
    // 플러그가 꽉 차 있는 경우, 교체가 필요하다.
    else {
        // 현재 플러그에 꽂힌 작업을 세트로 저장하여 추적할 임시 세트를 만든다.
        var temp = Set(keep)
        
        // 다음 작업들을 확인하며 가장 늦게 사용되는 작업을 찾는다.
        for j in i + 1..<K {
            // 교체할 작업이 하나로 결정되면 중단
            if temp.count == 1 { break }
            
            // 만약 다음 작업에 현재 꽂힌 작업이 있다면 임시 세트에서 제거
            if temp.contains(list[j]) {
                temp.remove(list[j])
            }
        }
        
        // temp에 남아 있는 첫 번째 작업을 플러그에서 제거
        keep.remove(at: keep.firstIndex(of: temp.first!)!)
        
        // 새 작업을 플러그에 추가하고 pull 횟수 증가
        keep.append(list[i])
        pull += 1
    }
}

print(pull)


//N = 2, K = 7
//list = [2, 3, 2, 3, 1, 2, 7]
//keep = []
//pull = 0

//첫 번째 반복 (i = 0):
//list[0] = 2
//keep에 2가 없고 keep.count < N이므로 keep.append(2)
//현재 상태:
//keep = [2]
//pull = 0

//두 번째 반복 (i = 1)
//list[1] = 3
//keep에 3이 없고 keep.count < N이므로 keep.append(3)
//현재 상태:
//keep = [2, 3]
//pull = 0

//세 번째 반복 (i = 2)
//list[2] = 2
//keep에 2가 있으므로 continue
//현재 상태:
//keep = [2, 3]
//pull = 0

//네 번째 반복 (i = 3):
//list[3] = 3
//keep에 3이 있으므로 continue
//현재 상태:
//keep = [2, 3]
//pull = 0

//다섯 번째 반복 (i = 4):
//list[4] = 1
//keep에 1이 없고 keep.count == N이므로, keep에서 하나를 빼고 1을 추가해야 합니다.
//temp = Set(keep) = {2, 3}
//j = 5, list[5] = 2이므로 temp.remove(2), temp = {3}
//temp.first! = 3이므로 keep.remove(at: keep.firstIndex(of: 3)!), keep.append(1), pull += 1
//현재 상태:
//keep = [2, 1]
//pull = 1

//여섯 번째 반복 (i = 5):
//list[5] = 2
//keep에 2가 있으므로 continue
//현재 상태:
//keep = [2, 1]
//pull = 1

//일곱 번째 반복 (i = 6):
//list[6] = 7
//keep에 7이 없고 keep.count == N이므로, keep에서 하나를 빼고 7을 추가해야 합니다.
//temp = Set(keep) = {2, 1}
//j는 i + 1부터 시작하지만 K를 초과하므로 temp에 아무 변화 없음
//temp.first! = 2이므로 keep.remove(at: keep.firstIndex(of: 2)!), keep.append(7), pull += 1
//최종 상태:
//keep = [1, 7]
//pull = 2
