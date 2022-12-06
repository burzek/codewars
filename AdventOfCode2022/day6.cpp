#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include <boost/algorithm/string.hpp>

using namespace std;

string read_input_file(string inputFileName) {
    ifstream file(inputFileName);
    string line; 

    //read into array
    stringstream buffer;
    buffer << file.rdbuf();
    string str = buffer.str();
    return str;
}

void solve(string inputFileName, int required_len) {
    
    string str = read_input_file(inputFileName);

    int start {0}, current_pos {0};
    int arr[26];
    fill_n(arr, 26, -1);

    while (current_pos - start < required_len) {
        int charIdx = str.at(current_pos) - 'a';
        if (arr[charIdx] != -1 && arr[charIdx] >= start) {
            start = arr[charIdx] + 1;
        }
        arr[charIdx] = current_pos;
        current_pos++;
    }

    //process moves  
    cout << "required len: " << required_len << ", position:" << current_pos << endl;

}



int main(int argc, char const *argv[])
{
    solve("day6.input", 4);
    solve("day6.input", 14);
    return 0;
}
