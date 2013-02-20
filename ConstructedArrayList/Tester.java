public class Tester {
	public static void main(String[] args) {
		ArrayList a = new ArrayList(10);
		
		//Add a single string, and print the size of the list and the list itself
		a.add("Hello");
		System.out.println(a.size());
		a.print();
		
		//Add an entire array, print the size and list
		System.out.println("Add an entire array:");
		String[] arr = new String[3];
		arr[0] = "What's";
		arr[1] = "up";
		arr[2] = "dawg?";
		a.add(arr);
		System.out.println(a.size());
		a.print();
		
		//Change a specific node
		System.out.println("Change a specific node:");
		a.set(2, "down");
		a.print();
		
		//Remove the 3rd node, print size
		System.out.println("Remove the 3rd node:");
		a.remove(2);
		System.out.println(a.size());
		a.print();
	}
}