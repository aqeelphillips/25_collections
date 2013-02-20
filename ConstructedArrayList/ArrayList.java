public class ArrayList {
	private String[] list;

	public ArrayList(int size) {
		list = new String[size];
	}
	
	public void add(String s) {
		if (size() == getList().length) {
			String[] newList = new String[size()+1];
			for (int i = 0; i < size(); i++) {
				newList[i] = get(i);
			}
			list = newList;
		}
		int i = 0;
		while (getList()[i] != null) { i++; }
		getList()[i] = s;
	}
	
	public void add(String[] coll) {
		for (int i = 0; i < coll.length; i++) {
			add(coll[i]);
		}
	}
	
	public void remove(int index) {
		getList()[index] = null;
		//get the rest of the array
		for (int i = index + 1; i <= size(); i++) {
			set(i-1, get(i));
		}
	}
	
	public void set(int index, String str) {
		getList()[index] = str;
	}
	
	public int size() {
		int counter = 0;
		String [] l = getList();
		for (int i = 0; i < l.length; i++) {
			if (l[i] != null) {
				counter++;
			}
		}
		return counter;
	}
	
	public String get(int index) {
		return getList()[index];
	}
	
	public String[] getList() {
		return list;
	}
	
	public void print() {
		String str = "[";
		for (int i = 0; i < size(); i++) {
			str += (getList()[i]);
			if (i != size() - 1) {
				str += ", ";
			}
		}
		str += "]";
		System.out.println(str);
	}
}