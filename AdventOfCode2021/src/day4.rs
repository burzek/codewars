pub fn day4_task1_task2(input: String) {
    let mut lines = input.lines();
    let numbers = lines.next().unwrap().split(",").map(|s| s.parse().unwrap()).collect::<Vec<i32>>();

    let mut boards:Vec<i32> = lines
        .flat_map(
            |l| l.split_ascii_whitespace().map(|x| x.parse().unwrap()).collect::<Vec<i32>>()
        )
        .collect();

    let mut iter = numbers.iter();
    while let Some(drawn) = iter.next()  {
        // println!("drawn:{}", drawn);
        boards = boards.iter().map(|n| if  n == drawn {- *n} else {*n}).collect::<Vec<i32>>();
        let winning_board = is_bingo(&boards);
        if winning_board >= 0 {
                let sum_unmarked: i32 = boards[(winning_board as i32 * 25) as usize..(winning_board as i32 * 25 + 25) as usize].iter().filter(|v| **v > 0).sum();
                println!("day 4 task 1: {}", sum_unmarked * drawn);
                break;
            }
    }

}

fn is_bingo(boards : &Vec<i32>) -> i32 {
    let board_count = boards.len() / 25;
    for b in 0..board_count {
        let board = &boards[b * 25 .. b * 25 + 25];

        for i in 0..5 {
            let row_win = (board[i * 5] < 0) &&
                (board[i * 5 + 1] < 0) &&
                (board[i * 5 + 2] < 0) &&
                (board[i * 5 + 3] < 0) &&
                (board[i * 5 + 4] < 0);
            let col_win = (board[i] < 0) &&
                (board[i + 5] < 0) &&
                (board[i + 10] < 0) &&
                (board[i + 15] < 0) &&
                (board[i + 20] < 0);
            if row_win || col_win {
                return b as i32;
            }
        }
    }
    return -1;
}

