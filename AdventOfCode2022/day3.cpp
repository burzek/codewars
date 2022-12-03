#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>  

using namespace std;


int solve_part_1(string input) {
    ifstream file(input);
    string line; 
    int sum = 0;
    unsigned char comp[52];
    
    while (getline(file, line)) {
         fill_n(comp, 52, 0);
        int mid = line.length() / 2;
        for (int pos = 0; pos < line.length(); pos++) {
             unsigned char c = line[pos];
             int cVal = (c <= 'Z' ? c - 'A' + 26 : c - 'a');
             comp[cVal] |= (1 << (pos < mid ? 0 : 1));
        }
        
        //sum priorities
        for (int i = 0; i < 52; i++) {
            sum += (comp[i] ==  3 ? i + 1 : 0);
        }
    }
    return sum;
}


int solve_part_2(string input) {
    ifstream file(input);
    string line;
    unsigned char comp[52];
    int sum = 0;
    do {
        fill_n(comp, 52, 0);
        for (int elf = 0; elf < 3; elf++) {
            getline(file, line);
            for (int pos = 0; pos < line.length(); pos++) {
                unsigned char c = line[pos];
                int cVal = (c <= 'Z' ? c - 'A' + 26 : c - 'a');
                comp[cVal] |= (1 << elf);
            }
        }
       
        //sum priorities
        for (int i = 0; i < 52; i++) {
            sum += (comp[i] == 7 ? i + 1 : 0);
        }

    } while (!file.eof());
    return sum;
}

int main(int argc, char const *argv[])
{
    cout << "Part 1: " << solve_part_1("day3.input") << endl;
    cout << "Part 2: " << solve_part_2("day3.input") << endl;
    return 0;
}
