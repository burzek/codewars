#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include <set>
#include <iomanip>
#include <boost/algorithm/string.hpp>

using namespace std;

class Folder {
    public:
        Folder(string name, Folder* parent) {
            this->name = name;
            this->parent = parent;
        }
    string name;
    Folder* parent {};
    set<Folder*> subdirs {};
    long size;
};


Folder* add_dir(string dirname, Folder* currentDir) {
    Folder* newFolder = new Folder(dirname, currentDir);
    currentDir->subdirs.insert(newFolder);
    return currentDir;
}

void process_file(string fileRecord, Folder* currentDir) {
    long size = atol(fileRecord.substr(0, fileRecord.find(' ')).c_str());
    currentDir->size += size;
    Folder* tmp = currentDir->parent;
    while (tmp != nullptr) {
        tmp->size += size;
        tmp = tmp->parent;
    }
}

Folder* change_dir(string folderName, Folder* currentDir) {
    if (folderName.at(0) == '.') {
        currentDir = currentDir->parent;
    } else {
        for (auto f : currentDir->subdirs) {
            if (f->name == folderName) {
                currentDir = f;
            }
        }
    }
    return currentDir;
}

Folder* build_structure(ifstream& file) {
    Folder* root = new Folder("/", nullptr);
    Folder* currentDir = root;
    
    string line;
    do {
        getline(file, line);
        if (line.at(2) == 'c') {            //cd
            currentDir = change_dir(line.substr(5), currentDir);
        } else if (line.at(0) == 'd') {     //dir
            currentDir = add_dir(line.substr(4), currentDir);
        } else {                            //len-file
            process_file(line, currentDir);
        }
    } while (!file.eof());
    return root;
}


long sum(Folder* folder, long total) {
    long ret = 0;
    if (folder->size < 100000) {
        ret = folder->size;
    }

    for (auto sd : folder->subdirs) {
        ret += sum(sd, total);
    }
    return ret;
}


long find_to_delete(Folder* folder, long required, long min_size) {
    if (folder->size > required && folder->size < min_size) {
        min_size = folder->size;
    }

    for (auto sd : folder->subdirs) {
        min_size = find_to_delete(sd, required, min_size);
    }

    return min_size;
    
    
}


void solve(string inputFileName) {
    ifstream file(inputFileName);
    string line; 
    Folder* root = build_structure(file);
    cout << "part 1: " << sum(root, 0) << endl;
    cout << "part 2: " << find_to_delete(root, (30000000 - (70000000 - root->size)), LONG_MAX) << endl;
    

}



int main(int argc, char const *argv[])
{
    solve("day7.input");
    return 0;
}
