let n = Int(readLine()!)!                // 후보 사진틀의 개수를 입력받음
_ = readLine()                           // 불필요한 입력 무시
let array = readLine()!.split(separator: " ").map { Int($0)! } // 학생들의 추천 번호 배열을 입력받아 정수 배열로 변환
var photos: [(Int, Int)] = []            // 사진틀에 들어가는 학생 번호와 추천 수를 담는 튜플 배열

for arr in array {                       // 학생들의 추천 번호를 하나씩 처리
    if photos.contains(where: { $0.0 == arr }) {    // 사진틀에 이미 해당 학생이 있으면
        let findIndex = photos.firstIndex { $0.0 == arr }! // 그 학생의 인덱스를 찾고
        photos[findIndex].1 += 1         // 해당 학생의 추천 수를 1 증가시킴
    } else {
        if photos.count == n {           // 사진틀이 꽉 찼다면
            let removed = photos.filter { $0.1 == photos.map { $0.1 }.min()! }.first!       // 추천 수가 가장 적은 학생을 찾아서 제거할 학생으로 선정
            photos.remove(at: photos.firstIndex { $0 == removed }!)  // 가장 적은 추천 수를 가진 학생을 사진틀에서 제거
        }
        photos.append((arr, 1))          // 해당 학생이 사진틀에 없으면 새로 추천받은 학생을 사진틀에 추가하고, 추천 수는 1로 초기화
    }
}

photos.map { $0.0 }.sorted(by: <).forEach { print($0, terminator: " ") }        // 사진틀에 있는 학생 번호들을 오름차순으로 정렬하여 출력
