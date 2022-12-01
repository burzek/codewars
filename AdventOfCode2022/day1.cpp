#include <iostream>
#include <fstream>
#include <string>

using namespace std;


int solve_part_1(string input) {
    ifstream file(input);
    string line; 
    int max_sum = 0;
    int sum = 0;
    while (getline(file, line))
    {
        if (line.length() == 0) {
            max_sum = max(sum, max_sum);
            sum = 0;
        } else {
            sum += atoi(line.c_str());
        }
    }
    return max_sum;
}


void update_min_3(int sum, int *a, int *b, int *c) {
    int *p_min = *a < *b ? (*a < *c ? a : c) : (*b < *c ? b : c);
    if (sum > *p_min) {
        *p_min = sum;
    }
}

int solve_part_2(string input) {
    ifstream file(input);
    string line; 
    
    int sum = 0;
    int max_1 = 0 , max_2 = 0, max_3 = 0;

    while (getline(file, line))
    {
        if (line.length() == 0) {
            update_min_3(sum, &max_1, &max_2, &max_3);
            sum = 0;
        } else {
            sum += atoi(line.c_str());
        }
    }

    return max_1 + max_2 + max_3;
}

int main(int argc, char const *argv[])
{
    //day1.input -> needs empty line at the end of file
    cout << "Part 1: " << solve_part_1("day1.input") << endl;
    cout << "Part 2: " << solve_part_2("day1.input") << endl;
    return 0;
}
