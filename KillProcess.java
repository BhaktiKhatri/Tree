package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * 582. Kill Process
 * https://leetcode.com/problems/kill-process/description/
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 * Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.
 * We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.
 * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.
 * Example 1: Input: pid =  [1, 3, 10, 5]; ppid = [3, 0, 5, 3]; kill = 5; Output: [5,10]
 * Explanation: 
 *           3
 *         /   \
 *        1     5
 *             /
 *            10
 * Kill 5 will also kill 10
 * Note: The given kill id is guaranteed to be one of the given PIDs; n >= 1
 * Explanation and Code from: Approach #3 HashMap + DFS https://leetcode.com/problems/kill-process/solution/
 * Bloomberg
 * Medium
 */

public class KillProcess {

	/*
	 			       3
			         /   \
			        1     5
			             /
			            10
	 */
	public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();			//key-parent i.e ppid; value- list of children i.e. list(pid)
        System.out.println("pid: "+pid+" ppid: "+ppid+" kill: "+kill);
        
        for(int i=0; i<ppid.size(); i++) {
        	System.out.println("ppid.get(i): "+ppid.get(i)+" pid.get(i): "+pid.get(i));
        	
            if(ppid.get(i) > 0) {
                List<Integer> list = map.getOrDefault(ppid.get(i), new ArrayList<Integer>());
                list.add(pid.get(i));
                map.put(ppid.get(i), list);
                System.out.println("list: "+list+" map: "+map);
            }
        }
        
        List<Integer> result = new ArrayList<Integer>();
        result.add(kill);
        getAllChildren(map, result, kill);
        return result;
    }
	
    public static void getAllChildren(HashMap<Integer, List<Integer>> map, List<Integer> l, int kill) {
        if(map.containsKey(kill)) {
        	System.out.println("map: "+map+" kill: "+kill);
            
        	for(int id: map.get(kill)) {
        		System.out.println("id: "+id);
                
        		l.add(id);
        		System.out.println("map: "+map+" l: "+l+" id: "+id);
        		
                getAllChildren(map, l, id);
            }
        }
    }
	
	public static void main(String[] args) {
		List<Integer> pid = new ArrayList<>();
		pid.add(1);
		pid.add(3);
		pid.add(10);
		pid.add(5);
		
		List<Integer> ppid = new ArrayList<>();
		ppid.add(3);
		ppid.add(0);
		ppid.add(5);
		ppid.add(3);
		
		int kill = 5;
		
		System.out.println(killProcess(pid, ppid, kill));
	}

}
