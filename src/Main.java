import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

	static HashMap<String, List<Task>> categories = new HashMap<>();
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fn = args[0];
		
		Scanner sc = new Scanner(new File(fn));

		int n = sc.nextInt();

		for (int i=0;i<n;i++) {
			Task task = new Task(sc.next(), sc.next(), sc.nextInt());
			if (categories.containsKey(task.catigory)) 
				categories.get(task.catigory).add(task);
			else {
				List<Task> list = new ArrayList<>();
				list.add(task);
				categories.put(task.catigory,list);
			}
		}
		
		for (String cat:categories.keySet()) {
			List<Task> list = categories.get(cat);
			list = mergeSort(list);
			categories.put(cat, list);
		}
		
		for (String cat:categories.keySet()) {
			int totalTime=0;
			List<Task> list = categories.get(cat);
			System.out.print(cat+" ");
			for (Task task:list) {
				System.out.print(task.name+" ");
				totalTime+=task.time;
			}
			System.out.println(totalTime);
		}
		
		
	}
	
	
	private static List<Task> mergeSort(List<Task> al) {
		// TODO Auto-generated method stub
		if (al.size()<=1) return al; 
		List<Task> listLeft = getLeftList(al);
		listLeft = mergeSort(listLeft);
		List<Task> listRight = getRightList(al);
		listRight = mergeSort(listRight);
		return mergeList(listLeft,listRight);
	}
	
	
	

	private static List<Task> mergeList(List<Task> listLeft, List<Task> listRight) {
		// TODO Auto-generated method stub
		List<Task> ret = new ArrayList<>();
		int i=0;
		int j=0;
		while (true) {
			if (i<listLeft.size()) {
				if (j<listRight.size()) {
					if (listLeft.get(i).time>listRight.get(j).time) {
						ret.add(listLeft.get(i));
						i++;
					} else {
						ret.add(listRight.get(j));
						j++;
					}
				} else { // j is out of boundary.
					for (;i<listLeft.size();i++) {
						ret.add(listLeft.get(i));
					}
					return ret;
				}
			} else {// i is out of boundary
				for (;j<listRight.size();j++) {
					ret.add(listRight.get(j));
				}
				return ret;
			}
		}
	}

	private static List<Task> getRightList(List<Task> al) {
		// TODO Auto-generated method stub
		List<Task> ret = new ArrayList<>();
		if (al.size() <= 1) return al;
		for (int i=al.size()/2;i<al.size();i++) {
			ret.add(al.get(i));
		}
		return ret;
	}

	private static List<Task> getLeftList(List<Task> al) {
		// TODO Auto-generated method stub
		List<Task> ret = new ArrayList<>();
		if (al.size() <= 1) return al;
		for (int i=0;i<al.size()/2;i++) {
			ret.add(al.get(i));
		}
		return ret;
	}

}

class Task {
	String name;
	String catigory;
	int time;
	
	public Task(String name, String cat, int tm) {
		this.name=name;
		this.catigory=cat;
		this.time = tm;
	}
}
