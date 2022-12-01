pub fn day1_task1(input: String) {
    let lines = input.lines();
    let mut increased = 0;
    lines
        .map(|s| { s.parse::<i32>().unwrap() })
        .reduce(|prev, current| {
            if prev < current { increased = increased + 1 };
            current
        });

    println!("day1, task1 : {}", increased);
}

pub fn day1_task2(input: String) {
    let lines = input.lines();
    let mut increased = 0;
    let values: Vec<i32> = lines
        .map(|s| { s.parse::<i32>().unwrap() })
        .collect();
    for i in 3..values.len() {
        if values[i - 1] + values[i - 2] + values[i - 3] < values[i] + values[i - 1] + values[i - 2] {
            increased = increased + 1;
        };
    }

    println!("day1, task2 : {}", increased);
}
