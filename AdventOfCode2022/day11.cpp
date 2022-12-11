#include <fstream>
#include <iostream>
#include <string>
#include <boost/algorithm/string.hpp>
#include <sstream>
#include <map>
#include <boost/multiprecision/cpp_int.hpp>


using namespace std;

struct Monkey {
        int monkey_id;
        vector<int> items{};
        int test;
        char op;
        int op_val;
        int t_monkey_id;
        int f_monkey_id;
        int test_count {0};
};


vector<Monkey> monkeys;


void monkey_index(ifstream& file, Monkey& monkey) {
    string line;
    getline(file, line);
    monkey.monkey_id = atoi(line.substr(7, line.length() - 1).c_str());
}

void starting_items(ifstream& file, Monkey& monkey) {
    string line;
    getline(file, line);

    vector<string> split;
    boost::split(split, line.substr(18), boost::is_any_of(","));
    for (auto item : split) {
        boost::trim(item);
        monkey.items.push_back(atoi(item.c_str()));
    }
}

void operation(ifstream& file, Monkey& monkey) {
    string line;
    getline(file, line);
    monkey.op = line.at(23);
    monkey.op_val = line.substr(24).at(0) == 'o'? 0 : atoi(line.substr(25).c_str());
}

void test(ifstream& file, Monkey& monkey) {
    string test, t,f;
    getline(file, test);
    getline(file, t);
    getline(file, f);
    monkey.test = atoi(test.substr(21).c_str());
    monkey.t_monkey_id = atoi(t.substr(29).c_str());
    monkey.f_monkey_id = atoi(f.substr(30).c_str());
}

vector<Monkey> read_monkeys(string inputFileName) {
    ifstream file(inputFileName);
    string line;
    do {
        Monkey m {};
        monkey_index(file, m);
        starting_items(file, m);
        operation(file, m);
        test(file, m);
        monkeys.push_back(m);
    } while (!getline(file, line).eof());

    return monkeys;
}

int calculate(int old_item_value, char op, int r_operand) {
    int r = r_operand == 0 ? old_item_value : r_operand;
    switch(op) {
        case '*':
            return old_item_value * r;
        case '+':
            return old_item_value + r;
        case '-':
            return old_item_value - r;
        case '/':
            return old_item_value / r;
        default: 
            return old_item_value;
    };
}

int solve(string inputFileName, int worry_divider, int iterations) {
    monkeys.clear();
    monkeys = read_monkeys(inputFileName);
    
    for (int i = 0; i < iterations; i++) {
        for (auto &m : monkeys) {
            for (auto it = m.items.begin(); it < m.items.end();) {
                int new_val = calculate(*it, m.op, m.op_val) / worry_divider;
               if (new_val % m.test == 0) {
                    monkeys.at(m.t_monkey_id).items.push_back(new_val);      
                } else {
                    monkeys.at(m.f_monkey_id).items.push_back(new_val);
                }
                m.test_count++;
                it = m.items.erase(it);
            }
        }

    }

    int max_1 {0};
    int max_2 {0};
    for (auto m : monkeys) {
        if (m.test_count > max_1) {
            max_2 = max_1;
            max_1 = m.test_count;
        } else if (m.test_count > max_2) {
            max_2 = m.test_count;
        }
    }


    return max_1 * max_2;

}



int main(int argc, char const *argv[])
{
    cout << "Part 1:" << solve("day11.input", 3, 20) << endl;
    return 0;
}
