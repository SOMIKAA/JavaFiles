
public class LinkedList {
	private class Node {
		private int data;
		private Node next;
	}

	private Node head;
	private Node tail;
	private int size;

	public void addLast(int value) {
		Node node = new Node();
		node.data = value;
		node.next = null;
		if (size == 0) {
			head = node;
			tail = node;
		} else {
			this.tail.next = node;
			this.tail = node;

		}
		this.size++;

	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void display() {
		Node node = new Node();
		node = head;

		while (node != null) {
			System.out.print(node.data + ",");
			node = node.next;
		}
	}

	public void addfirst(int val) {
		Node node = new Node();
		node.data = val;
		if (size == 0) {
			addLast(val);
		} else {
			node.next = this.head;
			this.head = node;
			this.size++;
		}

	}

	public void getfirst() throws Exception {
		if (size == 0) {
			throw new Exception("List empty");
		}
		System.out.println(head.data);
	}

	public void getindex(int idx) throws Exception {
		if (size == 0) {
			throw new Exception("List empty");
		}
		if (idx > size) {
			throw new Exception("Out of Bound");
		}
		Node node = new Node();
		node = head;
		for (int i = 0; i < idx; i++) {
			node = node.next;
		}
		System.out.println(node.data);
	}

	public void getlast() throws Exception {
		if (size == 0) {
			throw new Exception("List empty");
		}
		System.out.println(this.tail.data);
	}

	public Node getnode(int idx) {
		Node node = new Node();
		node = head;
		for (int i = 0; i < idx; i++) {
			node = node.next;
		}
		return node;

	}

	public void addindex(int val, int idx) throws Exception

	{
		Node node = new Node();
		node.data = val;
		if (idx < 0 || idx > size) {
			throw new Exception("Out of bound");
		}
		if (idx == 0) {
			addLast(val);
		} else {
			Node m1 = getnode(idx - 1);
			Node m2 = m1.next;
			m1.next = node;
			node.next = m2;

		}
		this.size++;

	}

	public void addend(int val) {
		Node node = new Node();
		node.data = val;
		if (size == 0) {
			addLast(val);
		} else {
			this.tail.next = node;
			node.next = null;
		}
		this.size++;
	}

	public int removefirst() throws Exception {
		Node node = new Node();
		node = head;
		if (size == 0) {
			throw new Exception("Empty List");
		}
		if (size == 1) {
			int temp = head.data;
			this.head = null;
			this.tail = null;
			this.size--;
			return temp;

		}
		head = node.next;
		this.size--;
		return node.data;
	}

	public int removelast() throws Exception {
		Node node = new Node();
		if (size == 0) {
			throw new Exception("List empty");
		}
		if (size == 1) {
			int temp = head.data;
			this.head = null;
			this.tail = null;
			return temp;
		} else {

			node = getnode(size - 2);
			tail = node;
			node.next = null;

		}
		this.size--;

		return node.data;
	}

	public int removeat(int idx) throws Exception {
		if (idx < 0 || idx >= size) {
			throw new Exception("Out of bound");
		}
		if (idx == 0) {
			return removefirst();
		} else if (idx == size - 1) {
			return removelast();
		} else {
			Node m1 = getnode(idx - 1);
			Node m2 = m1.next;
			Node m3 = m2.next;
			m1.next = m3;
			this.size--;
			return m2.data;

		}

	}

	public void reversedataIterative() throws Exception {
		if (size == 0) {
			throw new Exception("List is empty");
		}
		int j = 0;
		int i = 0;
		for (i = 0, j = size - 1; i < size / 2; i++) {
			Node node1 = getnode(i);
			Node node2 = getnode(j);
			int temp1 = node1.data;
			node1.data = node2.data;
			node2.data = temp1;
			j--;
		}

	}

	private class HeapMover {
		Node node;
		int counter;
		int kthfromlast;
	}

	public void reverseddataRecursive_W() {
		HeapMover left = new HeapMover();
		left.node = head;
		reverseddataRecursive_WH(left, head, 0);
	}

	private void reverseddataRecursive_WH(HeapMover left, Node right, int floor) {
		if (right == null) {
			return;
		}
		reverseddataRecursive_WH(left, right.next, floor + 1);
		if (floor >= size / 2) {
			int temp = left.node.data;
			left.node.data = right.data;
			right.data = temp;
			left.node = left.node.next;

		}

	}

	public void q11(int k) {
		LinkedList c = new LinkedList();
		Node temp = new Node();
		temp.data = c.klast(k + 1);
		c.tail.next = head;
		temp.next = head;
		temp = tail;

	}

	public int klast(int k) {

		HeapMover left = new HeapMover();
		left.node = head;
		klasth(left, head, k);
		return left.kthfromlast;

	}

	private void klasth(HeapMover left, Node node, int k) {

		if (node == null) {
			return;
		}
		klasth(left, node.next, k);
		left.counter++;
		if (left.counter == k) {
			left.kthfromlast = node.data;
		}

	}

	public void fold() {
		HeapMover left = new HeapMover();
		left.node = head;
		foldh(left, head, 0);

	}

	private void foldh(HeapMover left, Node right, int floor) {
		if (right == null) {
			return;
		}
		foldh(left, right.next, floor + 1);
		if (floor > (size / 2)) {
			right.next = left.node.next;
			left.node.next = right;
			left.node = right.next;
		} else if (floor == size / 2) {
			tail = right;
			tail.next = null;
		}

	}

	public boolean IsPallindromeH(HeapMover left, Node right) {
		if (right == null) {
			return true;
		}
		boolean res = IsPallindromeH(left, right.next);
		if (res == false) {
			return false;
		}
		if (left.node.data == right.data) {
			left.node = left.node.next;
			return true;
		} else {
			return false;
		}
	}

	public Node mid() {
		Node slow = head;
		Node fast = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	public LinkedList merge(LinkedList other) {
		LinkedList merged = new LinkedList();
		Node tt = this.head;
		Node ot = other.head;
		while (tt != null && ot != null) {
			if (tt.data < ot.data) {
				merged.addLast(tt.data);
				tt = tt.next;
			} else {
				merged.addLast(ot.data);
				ot = ot.next;
			}
		}
		while (tt != null) {
			merged.addLast(tt.data);
			tt = tt.next;
		}
		while (ot != null) {
			merged.addLast(ot.data);
			ot = ot.next;
		}
		return merged;
	}

	public LinkedList Mergesort() {
		if (head == tail) {
			return this;
		}
		Node mid1 = mid();
		Node midn = mid1.next;
		LinkedList fh = new LinkedList();
		LinkedList sh = new LinkedList();
		fh.head = head;
		fh.tail = mid1;
		fh.tail.next = null;
		sh.head = midn;
		sh.tail = tail;
		LinkedList f3 = fh.Mergesort();
		LinkedList f4 = sh.Mergesort();
		LinkedList fl = new LinkedList();
		fl = f3.merge(f4);
		return fl;

	}

	public boolean IsPallindrome() {
		HeapMover left = new HeapMover();
		left.node = head;
		return IsPallindromeH(left, head);
	}

	public void reversepointerIterative() throws Exception {
		if (size == 0) {
			throw new Exception("List is empty");
		}
		Node prev = head;
		Node curr = prev.next;
		while (curr != null) {
			Node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		Node temp = head;
		head = tail;
		tail = temp;
		tail.next = null;
	}

	public void reversePointerRecursive() {
		reversePointerHelper(head);
	}

	private void reversePointerHelper(Node node) {
		if (node.next == null) {
			return;
		}
		reversePointerHelper(node.next);
		node.next.next = node;
	}

	public void reversedisplay() {
		reversedisplayHelper(head);
	}

	private void reversedisplayHelper(Node node) {
		if (node == null) {
			return;
		}

	}

	public void Kreverse(int k) throws Exception {

		LinkedList prev = null;
		LinkedList curr = null;
		while (this.size != 0) {
			curr = new LinkedList();
			for (int i = 0; i < k; i++) {
				curr.addfirst(this.removefirst());

			}
			if (prev == null) {
				prev = curr;
			} else {
				prev.tail.next = curr.head;
				prev.tail = curr.tail;
				prev.size += curr.size;
			}

		}
	}

	public void removeduplicate() throws Exception {
		LinkedList curr = new LinkedList();
		while (this.size != 0) {
			int data = this.removefirst();
			if (curr.isEmpty() || curr.tail.data != data) {
				curr.addLast(data);
			}
		}
		this.head = curr.head;
		this.tail = curr.tail;
		this.size = curr.size;

	}

}
