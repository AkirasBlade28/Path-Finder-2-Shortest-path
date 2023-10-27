import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Finder {
	
	public static int pathFinder(String maze) {
		if(maze.length() == 1) return 0;
		else return bfs(maze);
	}
	//breadth first search
	private static int bfs(String maze) {
	    int mazeLength = maze.length();
	    int mazeWidth = maze.indexOf('\n');
	    Queue<Integer> que = new ArrayDeque<>(mazeLength);
	    Set<Integer> visitedNode = new HashSet<>();
	    Map<Integer, Integer> parentMap = new HashMap<>(); // added parentMap

	    que.add(0);
	    visitedNode.add(0);
	    parentMap.put(0, -1); // set the parent of the starting node to -1

	    while(!que.isEmpty()) {
	        int currentNode = que.poll();
	        int[] moves = {
	            currentNode - mazeWidth - 1,
	            currentNode + mazeWidth + 1,
	            currentNode - 1,
	            currentNode + 1
	        };
	        for (var move : moves) {
	            if (move >= 0 && move < mazeLength && maze.charAt(move) == '.' && !visitedNode.contains(move)) {
	                que.add(move);
	                visitedNode.add(move);
	                parentMap.put(move, currentNode); // add the parent of the current node to the parentMap
	            }
	        }
	        if(que.contains(mazeLength-1)) {
	            // if the finish node is found, backtrack to find the path
	            List<Integer> path = new LinkedList<>();
	            int node = mazeLength - 1;
	            path.add(node);
	            while(parentMap.get(node) != -1) {
	                node = parentMap.get(node);
	                path.add(node);
	            }
	            return path.size()-1;
	        }       
	    }
	    return -1;
	}
}
