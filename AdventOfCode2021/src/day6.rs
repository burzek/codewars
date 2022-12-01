use std::cmp::max;
use std::collections::HashMap;

fn parse_input(input: String) -> Vec<(i8)> {
    input.lines().next().unwrap().split(",").map(|val| { val.trim().parse::<i8>().unwrap() }).collect::<Vec<(i8)>>()
}


pub fn day6_task1(input: String) {
    let mut population = parse_input(input);

    for day in 1..80 + 1 {
        // println!("Population in day {}:{:?}", day, population);

        let mut new_pop = 0;
        population = population
            .iter()
            .map(|&l| {
                let ic = l - 1;
                if ic < 0 {new_pop = new_pop + 1};
                if ic < 0 {6} else {ic}
            })
            .collect();


        //add new
        for i in 0..new_pop {
            population.push(8);
        }
    }

    println!("day 6 task 1: {}", population.len());
}


fn lantern_fish_growth(days: usize, input : String) -> usize {
    let lantern_fish: Vec<usize> = input.split(",").map(|val| { val.parse().unwrap() }).collect();

    //length[i] is number of fish with size i

    let mut length = [0; 9];

    for x in &lantern_fish {
        length[*x] += 1;
    }

    for _ in 0..days {
        length.rotate_left(1);  //fish HP = HP - 1
        //at length[8] fish with HP=0 now have HP=8, every such fish is spawned with HP=6 and therefore added to length[6]
        length[6] += length[8];
    }

    length.iter().sum()
}

pub fn day6_task2(input: String) {
    //task1 solution is too naive and slow
    //solution is taken from https://github.com/jeffomatic/adventofcode/blob/main/2021-rust/day06b/src/main.rs
    //for study purposes
    println!("day 6 task 2: {}", lantern_fish_growth(256, input));

}

