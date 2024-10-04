#include <iostream>
#include<queue>
using namespace std;
//1158
int main() {
  //ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
  
  queue<int> que;
  int N,K;
  cin >> N>> K;

  for(int i=1;i<=N;i++){
    que.push(i);
  }

  cout << "<";
  while(!que.empty()){
    for(int i=1;i<K;i++){
      int temp = que.front();
      que.pop();
      que.push(temp);
    }
    cout << que.front();
    que.pop();
    if(!que.empty()){
      cout << ", ";
    }
  }

  cout << ">";

  return 0;
}