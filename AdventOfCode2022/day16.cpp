//################
//#NOT WORKING
//################


#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <set>
#include <algorithm>
#include <chrono>
#include <map>
#include <boost/algorithm/string.hpp>

using namespace std;

struct Node {
    string id;
    int value;
    bool opened;
    set<string> adj;
};

map<string, Node*> fast_nodes;


void Dijsktra(Node* node unsigned v) {
       dist[source]  := 0                     // Distance from source to source is set to 0
       for each vertex v in Graph:            // Initializations
           if v ≠ source
               dist[v]  := infinity           // Unknown distance function from source to each node set to infinity
           add v to Q                         // All nodes initially in Q

      while Q is not empty:                  // The main loop
          v := vertex in Q with min dist[v]  // In the first run-through, this vertex is the source node
          remove v from Q 

          for each neighbor u of v:           // where neighbor u has not yet been removed from Q.
              alt := dist[v] + length(v, u)
              if alt < dist[u]:               // A shorter path to u has been found
                  dist[u]  := alt            // Update distance of u 

      return dist[]
  end function
}


void read(string inputFileName, int &valve_count) {

    ifstream file(inputFileName);
    string line;

    map<string, int> id_mapping;
    int id = 0;

    valve_count = 0;
    while (getline(file, line)) {
        valve_count++;
        string id = line.substr(6, 2);
        Node *node = fast_nodes[id];
        if (node == nullptr) {
            node = new Node();
            fast_nodes[id] = node;
        }
        node->id = id;
        node->value = atoi(line.substr(23, line.find_first_of(';')).c_str());
        node->opened = node->value == 0 ? true : false;

        vector<string> vlist;
        boost::split(vlist, line.substr(line.find_last_of("valves") + 1), boost::is_any_of(","));
        for (auto v : vlist) {
            boost::trim(v);
            Node *child_node = fast_nodes[v];
            if (child_node == nullptr) {
                child_node = new Node();
                child_node->id = v;
                fast_nodes[child_node->id] = child_node;
            }
            child_node->adj.insert(node->id);
            if (node->adj.count(child_node->id) == 0) {
                node->adj.insert(child_node->id);
            }
        }

    }
}

void solve(string file_name) {
    // unsigned start_index;
    // unsigned end_index;
    // vector<unsigned> adj[rows * cols];
    // vector<char> start_points;
    int valve_count;
    read(file_name, valve_count);

    Node* current_node = fast_nodes["AA"];
    BFS(valve_count);

    // BFS(adj, start_points, start_index, end_index, cols * rows);

}


int main(int argc, char const *argv[])
{
    solve("day16.input");
    return 0;
}
