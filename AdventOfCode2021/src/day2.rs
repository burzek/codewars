pub fn day2_task1(input: String) {
    let lines = input.lines();
    let mut depth = 0;
    let mut range = 0;
    lines.map(|s| {
            let cmd = s.split_at(s.find(" ").unwrap());
            match cmd {
                ("forward",v) => range = range + v.trim().parse::<i32>().unwrap(),
                ("down",v) => depth = depth + v.trim().parse::<i32>().unwrap(),
                ("up",v) => depth = depth - v.trim().parse::<i32>().unwrap(),
                 (_,_) => panic!("invalid input")
            }}).count();    //count is for lazy iterator to be consumed
    println!("day2, task1 : {}", range * depth);
}

pub fn day2_task2(input: String) {
    let lines = input.lines();
    let mut depth = 0;
    let mut range = 0;
    let mut aim = 0;

    lines.map(|s| {
        let cmd = s.split_at(s.find(" ").unwrap());
        match cmd {
            ("forward",v) => {
                let v_n = v.trim().parse::<i32>().unwrap();
                range = range + v_n;
                depth = depth + (v_n * aim);
            },
            ("down",v) => aim = aim + v.trim().parse::<i32>().unwrap(),
            ("up",v) => aim = aim - v.trim().parse::<i32>().unwrap(),
            (_,_) => panic!("invalid input")
        }}).count();    //count is for lazy iterator to be consumed
    println!("day2, task2 : {}", range * depth);
}
