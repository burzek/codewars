#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>  

using namespace std;


unordered_map<string, int> pointsTable = {
    {"AX", 4}, {"AY", 8}, {"AZ", 3},
    {"BX", 1}, {"BY", 5}, {"BZ", 9},
    {"CX", 7}, {"CY", 2}, {"CZ", 6}
};

unordered_map<string, int> shiftTable = {
    {"AX", 3}, {"BX", 1}, {"CX", 2},
    {"AY", 4}, {"BY", 5}, {"CY", 6},
    {"AZ", 8}, {"BZ", 9}, {"CZ", 7}
};

int solve_part_1(string input) {
    ifstream file(input);
    string line; 
    int sum = 0;
    while (getline(file, line))
    {
        sum += pointsTable[(string(1, line[0]) + line[2])];
    }
    return sum;
}


int solve_part_2(string input) {
    ifstream file(input);
    string line; 
    int sum = 0;
    while (getline(file, line))
    {
        sum += shiftTable[(string(1, line[0]) + line[2])];

    }
    return sum;
}

int main(int argc, char const *argv[])
{
    cout << "Part 1: " << solve_part_1("day2.input") << endl;
    cout << "Part 2: " << solve_part_2("day2.input") << endl;
    return 0;
}
