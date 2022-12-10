#include <fstream>
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

void solve1(string inputFileName) {
    ifstream file(inputFileName);
    string line; 

    int power = {20};
    int regX = {1};
    int regXTick = {0};
    int clock = {0};
    while (getline(file, line)) {
        int param = {0};
        int ticks = {0};
        ticks += (line.at(0) == 'n' ? 1 : 2);
        param = (line.at(0) == 'n' ? 0 : atoi(line.substr(5).c_str()));   
        
        if (clock + ticks >= power) {
            regXTick += (regX * power);
            power += 40;
        }
        clock += ticks;
        regX += param;
        

    }
    
    cout << "part 1: " << regXTick << endl;

}

void solve2(string inputFileName) {
    ifstream file(inputFileName);
    string line; 

    int regX = {1};
    int clock = {0};
    
    while (getline(file, line)) {
        int param = {0};
        int ticks = {0};
        ticks += (line.at(0) == 'n' ? 1 : 2);
        param = (line.at(0) == 'n' ? 0 : atoi(line.substr(5).c_str()));   

        for (int rep = 0; rep < ticks; rep++) {
            cout << ((clock >= (regX - 1) && clock <= (regX + 1)) ? "#" : ".");
            if (++clock % 40 == 0) {
                cout << endl;
                clock %= 40;
            }
        }

        regX += param;

    }
    
}



int main(int argc, char const *argv[])
{
    solve1("day10.input");
    solve2("day10.input");
    return 0;
}
