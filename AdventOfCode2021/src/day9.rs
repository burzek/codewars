pub fn day9_task1(input: String) {
    let lines: Vec<&str> = input.lines().collect();
    let mut risk_sum: u16 = 0;

    for row in 0..input.lines().count() as i8 {
        let test_line = parse_line(&lines, row);
        let prev_line = parse_line(&lines, row - 1);
        let next_line = parse_line(&lines, row + 1);

        for i in 0..test_line.len() {
            let c = *test_line.get(i).unwrap();
            let tp = *prev_line.get(i).unwrap();
            let tn = *next_line.get(i).unwrap();
            let tl = if i > 0 {*test_line.get(i - 1).unwrap()} else {255};
            let tr = if i < test_line.len() - 1 {*test_line.get(i + 1).unwrap()} else {255};

            risk_sum = risk_sum + if c < tp && c < tn && c < tl && c < tr {1 + c as u16} else {0};
        }
    }

    println!("day 9 task 1: {}", risk_sum);
}

fn parse_line(lines: &Vec<&str>, row: i8) -> Vec<u8> {
    if row < 0 || row > (lines.len() - 1) as i8 {
        return vec![255 as u8; lines.len()];
    }

    lines.get(row as usize).unwrap().trim().chars().collect::<Vec<char>>().iter().map(|&x| x as u8 - '0' as u8).collect::<Vec<u8>>()
}

pub fn day9_task2(input: String) {}




