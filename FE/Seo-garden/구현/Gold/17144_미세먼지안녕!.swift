let input = readLine()!.split(separator: " ").map{Int(String($0))!}     // 입력을 받아 공백을 기준으로 자른 후, 각 요소를 Int로 변환한 배열을 input에 저장
let row = input[0], col = input[1], T = input[2]        // row는 행의 개수, col은 열의 개수, T는 시뮬레이션 시간
var array = Array(repeating: Array(repeating: 0, count: col), count: row)       // 2차원 배열 array를 초기화, 모든 요소를 0으로 채움
var aircleaner = [(Int, Int)]()     // 공기청정기의 위치를 저장할 배열
let direction = [(1, 0), (-1, 0), (0, 1), (0, -1)]      // 방향을 나타내는 배열: 상(위), 하(아래), 좌(왼쪽), 우(오른쪽)

// 배열 입력 받기
for i in 0..<row {
    let input2 = readLine()!.split(separator: " ").map{Int(String($0))!}
    for j in 0..<input2.count {
        
        array[i][j] = input2[j]     // 입력받은 값으로 array 배열을 채움
        
        if input2[j] == -1 {        // 공기청정기 위치를 저장 (-1인 값이 공기청정기임)
            aircleaner.append((i, j))
        }
    }
}

// T초 동안 시뮬레이션 실행
for _ in 0..<T {
    diffuse() // 미세먼지 확산
    doAircleaner() // 공기청정기 작동
}

// 결과 출력
print(getAnswer())

func diffuse() {                // 미세먼지를 확산시키는 함수
    var temp = Array(repeating: Array(repeating: 0, count: col), count: row)        // 확산된 미세먼지를 임시로 저장할 배열 temp
    for i in 0..<row {      // 배열을 순회하면서 미세먼지 확산 처리
        for j in 0..<col {
            if array[i][j] != 0 && array[i][j] != -1 {      // 미세먼지가 있는 경우 (0이 아니고, 공기청정기가 아닌 경우)
                var diffuseSuccess = 0 // 확산이 성공한 방향의 개수를 카운트
                let diffuse = array[i][j] / 5 // 확산되는 미세먼지의 양
                
                // 상, 하, 좌, 우로 확산 시도
                for k in 0..<4 {
                    let next = (i + direction[k].0, j + direction[k].1)
                    if next.0 >= row || next.1 >= col || next.0 < 0 || next.1 < 0 { continue }    // 배열 범위를 벗어나거나, 공기청정기 위치이면 확산되지 않음
                    if array[next.0][next.1] != -1 {        //공기청정기가 아니라면
                        temp[next.0][next.1] += diffuse
                        diffuseSuccess += 1
                    }
                }
                array[i][j] -= (diffuse * diffuseSuccess)       // 확산된 만큼 현재 위치의 미세먼지 양 감소
            }
        }
    }
    sum(temp)       // 확산된 결과를 원래 배열에 반영
}

// 확산된 미세먼지 값을 원래 배열에 더하는 함수
func sum(_ temp: [[Int]]) {
    for i in 0..<row {
        for j in 0..<col {
            // 공기청정기 위치는 그대로 두고, 나머지 위치는 값을 더함
            if array[i][j] != -1 {
                array[i][j] += temp[i][j]
            }
        }
    }
}

// 공기청정기를 작동시키는 함수
func doAircleaner() {
    // 공기청정기 윗부분 작동 (반시계 방향 순환)
    // 왼쪽 열 아래로 순환
    for i in (0..<aircleaner[0].0).reversed() {
        if i + 1 == aircleaner[0].0 {
            array[i][0] = 0
        } else {
            array[i + 1][0] = array[i][0]
        }
    }
    
    // 맨 위 행 오른쪽으로 순환
    for i in (1..<col) {
        array[0][i - 1] = array[0][i]
    }
    
    // 오른쪽 열 위로 순환
    for i in (1...aircleaner[0].0) {
        array[i - 1][col - 1] = array[i][col - 1]
    }
    
    // 공기청정기까지 왼쪽으로 순환
    for i in (1..<col-1).reversed() {
        array[aircleaner[0].0][i + 1] = array[aircleaner[0].0][i]
        if i == 1 {
            array[aircleaner[0].0][i] = 0
        }
    }
    
    // 공기청정기 아랫부분 작동 (시계 방향 순환)
    // 왼쪽 열 위로 순환
    for i in (aircleaner[1].0 + 1..<row) {
        if i - 1 == aircleaner[1].0 {
            array[i][0] = 0
        } else {
            array[i - 1][0] = array[i][0]
        }
    }
    
    // 맨 아래 행 오른쪽으로 순환
    for i in (1..<col) {
        array[row - 1][i - 1] = array[row - 1][i]
    }
    
    // 오른쪽 열 아래로 순환
    for i in (aircleaner[1].0..<row-1).reversed() {
        array[i + 1][col - 1] = array[i][col - 1]
    }
    
    // 공기청정기까지 왼쪽으로 순환
    for i in (1..<col-1).reversed() {
        array[aircleaner[1].0][i + 1] = array[aircleaner[1].0][i]
        if i == 1 {
            array[aircleaner[1].0][i] = 0
        }
    }
}

// 결과값을 계산하여 반환하는 함수
func getAnswer() -> Int {
    var answer = 0
    // 공기청정기 위치를 제외한 모든 위치의 미세먼지 양을 더함
    for i in 0..<row {
        for j in 0..<col {
            if array[i][j] != -1 {
                answer += array[i][j]
            }
        }
    }
    return answer
}
