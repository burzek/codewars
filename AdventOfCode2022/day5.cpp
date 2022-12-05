#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <deque>
#include <boost/algorithm/string.hpp>

using namespace std;

vector<deque<char>*> read_map(ifstream& file) {
    string line; 

    //prepare map
    vector<deque<char>*> cargo;
    for (int i = 0; i < 10; i++) {
        cargo.push_back(new deque<char>());
    }
    
    //read map    
    getline(file, line);
    while (line[1] != '1') {
        for (int i = 1; i < line.length(); i += 4) {
            if (line[i] != 32) {
                cargo.at(i / 4)->push_back(line[i]);
            }
        }
        getline(file, line);
    }

    return cargo;
}

void solve_1(string inputFileName) {
    
    ifstream file(inputFileName);
    string line; 

    vector<deque<char>*> cargo = read_map(file);
 
    //process moves  
    while (getline(file, line)) {
        if (line[0] == 'm') {
            vector<string> moveSplit;
            boost::split(moveSplit, line, boost::is_any_of(" "));
            int count = atoi(moveSplit[1].c_str());
            int from = atoi(moveSplit[3].c_str());
            int to = atoi(moveSplit[5].c_str());
        
            for (int i = 0; i < count; i++) {
                cargo.at(to - 1)->push_front(cargo.at(from - 1)->front());
                cargo.at(from - 1)->pop_front();
            }
        }
    }
    
    cout << "Part 1: ";
    //get
    for (auto c : cargo) {
        cout << c->front();
    }
    cout << endl;

}


void solve_2(string inputFileName) {
    
    ifstream file(inputFileName);
    string line; 

    vector<deque<char>*> cargo = read_map(file);
 
    //process moves  
    while (getline(file, line)) {
        if (line[0] == 'm') {
            vector<string> moveSplit;
            boost::split(moveSplit, line, boost::is_any_of(" "));
            int count = atoi(moveSplit[1].c_str());
            int from = atoi(moveSplit[3].c_str());
            int to = atoi(moveSplit[5].c_str());
        
            deque<char> tmp;
            for (int i = 0; i < count; i++) {
                tmp.push_back(cargo.at(from - 1)->front());
                cargo.at(from - 1)->pop_front();
            }
            for (auto iter = tmp.rbegin(); iter != tmp.rend(); ++iter) {
                cargo.at(to - 1)->push_front(*iter);
            }

        }
    }
    
    cout << "Part 2: ";
    //get
    for (auto c : cargo) {
        cout << c->front();
    }
    cout << endl;

}



int main(int argc, char const *argv[])
{
    solve_1("day5.input");
    solve_2("day5.input");
    return 0;
}
