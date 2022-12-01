use std::collections::VecDeque;

pub fn day10_task1(input: String) {

    let mut error_score = 0u32;
    input.lines().for_each(|l| {
        let chars = l.chars().collect::<Vec<char>>();
        let mut stack: Vec<char> = Vec::new();
        for i in 0..chars.len() - 1 {
            let c = *chars.get(i).unwrap();
            match c {
                '(' | '{' | '<' | '[' => stack.push(c),
                ')'| '}' | '>' | ']' => {
                    let pop = stack.pop().unwrap();
                    if (pop as i8 - c as i8).abs() > 2 {        //pairs distance are 1 or 2 in ascii table
                        error_score += match c {
                            ')' => 3,
                            ']' => 57,
                            '}' => 1197,
                            '>' => 25137,
                            _ => 0
                        };
                        break;
                    }
                }
                _ => {}
            }
        }
    });

    println!("day 10 task 1: {}", error_score);
}

pub fn day10_task2(input: String) {
    let mut finish_score : Vec<u128> = Vec::new();
    input.lines().for_each(|l| {
        let chars = l.chars().collect::<Vec<char>>();
        let mut stack: Vec<char> = Vec::new();
        for i in 0..chars.len() {
            let c = *chars.get(i).unwrap();
            match c {
                '(' | '{' | '<' | '[' => stack.push(c),
                ')'| '}' | '>' | ']' => {
                    let pop = stack.pop().unwrap();
                    if (pop as i8 - c as i8).abs() > 2 {
                        stack.clear();
                        break;  //invalid line
                    }
                }
                _ => {}
            }
        }


        if !stack.is_empty() {
            //count finish score
            let mut fs = 0u128;
            while let Some(c) = stack.pop() {
                fs =  match c {
                    '(' => fs * 5 + 1,
                    '[' => fs * 5 + 2,
                    '{' => fs * 5 + 3,
                    '<' => fs * 5 + 4,
                    _ => fs
                };
            }
            finish_score.push(fs);
        }
    });

    finish_score.sort();
    println!("day 10 task 2: {}", finish_score.get(finish_score.len() /2 ).unwrap());
}




