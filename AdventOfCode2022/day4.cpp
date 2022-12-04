#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include <vector>
#include <boost/algorithm/string.hpp>

using namespace std;


void solve(string input) {
    ifstream file(input);
    string line; 
    
    int count_1 = 0;
    int count_2 = 0;
    while (getline(file, line)) {
       
        vector<string> elf;
        boost::split(elf, line, boost::is_any_of(","));
        
        vector<string> section;
        boost::split(section, elf[0], boost::is_any_of("-"));
        int r1f = atoi(section[0].c_str());
        int r1t = atoi(section[1].c_str());
        boost::split(section, elf[1], boost::is_any_of("-"));
        int r2f = atoi(section[0].c_str());
        int r2t = atoi(section[1].c_str());

        count_1 += ((r1f <= r2f && r1t >= r2t) || (r2f <= r1f && r2t >= r1t)) ? 1 : 0;
        count_2 += ((r1f <= r2f && r1t >= r2f) || (r2f <= r1f && r2t >= r1f)) ? 1 : 0;
    };

    cout << "Part 1: " << count_1 << endl << "Part 2: " << count_2 << endl;


}


int main(int argc, char const *argv[])
{
    solve("day4.input");
    return 0;
}
