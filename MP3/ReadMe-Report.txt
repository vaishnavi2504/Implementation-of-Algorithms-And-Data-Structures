ReadMe
1. Driver.java   Starting point for Prim2 and Dijkstra Shortest Path
2. DriverForb.java Starting Point for Prim1
3. MST.java class that contains implementation for Prim1 and Prim2
4. ShortestPath.java Implementation for Dijkstra Shortest Path
5. BinaryHeap1.java Implementation for Binary Heap
6. IndesedHeap12.java Implementation for Indexed Binary 

---------------------------------------------------------------------------------------------


Observations from comparison between Prim1 and Prim2 implementations

Prim1 performs very well for smaller sized inputs.
However, Prim2 beats Prim1(factor of 3 in case of t3) for huge input size.

Stats from the output
Input		Prim2	Prim1
g1			5ms		4ms
g2			12ms	7ms
g3			09ms	11ms
BigInput1	1482ms	2661ms
t2		    154ms	76ms
t3			4580ms	11977 ms
