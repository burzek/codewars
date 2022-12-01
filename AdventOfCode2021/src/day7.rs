pub fn day7_task1(input: String) {
    let mut data: Vec<i32> = input.lines().next().unwrap().split(",").map(|x| { x.parse().unwrap() }).collect();
    data.sort();

    //middle element
    let n = data.len();
    let mut middle = 0;
    if n % 2 == 0 {
        middle = data[n / 2] as i32;
    } else {
        middle = (data[n / 2] as i32 + data[(n - 2) / 2] as i32) / 2;
    };

    let mut s = 0;
    for i in 0..n {
        s += i32::abs(data[i] - middle);
    }

    println!("day 6 task 1: {}", s);
}

pub fn day7_task2(input: String) {

    let mut data: Vec<i32> = input.lines().next().unwrap().split(",").map(|x| { x.parse().unwrap() }).collect();
    // let max = *data.iter().max().unwrap();
    //
    // let mut fuel_burn = 0;
    // let mut step = 1;
    // while fuel_burn < max {
    //     println!("s:{} step:{} fb:{}", s, step, fuel_burn);
    //     println!("{:?}", data);
    //
    //     data.sort();
    //
    //     //middle element
    //     let n = data.len();
    //     let mut middle = 0;
    //     if n % 2 == 0 {
    //         middle = data[n / 2] as i32;
    //     } else {
    //         middle = (data[n / 2] as i32 + data[(n - 2) / 2] as i32) / 2;
    //     };
    //
    //     let mut s = 0;
    //     for i in 0..n {
    //         s += i32::abs(data[i] - middle);
    //     }
    //     fuel_burn += step;
    //     step += 1;
    //     data = data.iter().map(|&x| if x < middle {x + step} else if x > middle {x - step} else {x}).collect();
    // }
    //
    // println!("day 6 task 2: {}", 0);
}




