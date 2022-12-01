use std::collections::HashSet;

pub fn day3_task1(mut input: String) {
    input.retain(|c| c != '\n');

    const PACKET_LEN: usize = 12;
    let packets = input.len() / PACKET_LEN;
    let mut gamma = String::new();

    for i in 0..PACKET_LEN {
        let mut zero_count = 0;
        for j in 0..packets {
            match input.as_str().bytes().nth(i + j * PACKET_LEN).unwrap() {
                48 => zero_count = zero_count + 1,  //bytes ~100x faster than chars()
                49 => zero_count = zero_count - 1,
                _ => panic!("invalid input")
            }
        }
        gamma.push(if zero_count > 0 { '0' } else { '1' });
    }

    let gamma_i = u32::from_str_radix(gamma.as_str(), 2).unwrap();
    println!("day 3 task 1: {}", gamma_i * (((1 << PACKET_LEN) - 1) - gamma_i));
}

pub fn day3_task2(mut input: String) {
    let mut set = HashSet::new();
    input.lines().for_each(|l| { set.insert(l); });

    let mut iter = 0;
    while (set.len() != 1) {
        let zero_count = set.iter().fold(0, |acc, &x| { acc + if x.as_bytes()[iter] == 48 {1} else {-1}  });
        set.retain(|&e| { e.bytes().nth(iter).unwrap() == if zero_count > 0 { 48 } else { 49 } });
        iter = iter + 1;
    }
    let co2 = u32::from_str_radix(set.iter().next().unwrap(), 2).unwrap();

    set.clear();
    input.lines().for_each(|l| { set.insert(l); });
    let mut iter = 0;
    while (set.len() != 1) {
        let zero_count = set.iter().fold(0, |acc, &x| { acc + if x.as_bytes()[iter] == 48 {1} else {-1}  });
        set.retain(|&e| { e.bytes().nth(iter).unwrap() != if zero_count > 0 { 48 } else { 49 } });
        iter = iter + 1;
    }
    let co2_scrub = u32::from_str_radix(set.iter().next().unwrap(), 2).unwrap();

    println!("day 3 task 2: {}", co2 * co2_scrub);

}
