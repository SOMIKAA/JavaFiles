import java.util.ArrayList;
import java.util.HashMap;

public class Trie {
	private class Node {
		Character data;
		boolean eow;
		HashMap<Character, Node> children = new HashMap<>();
	}

	private Node root = new Node();
	private int numWords = 0;
	private int numNodes = 1;

	public void addWord(String Word) {
		addWord(root, Word);
	}

	private void addWord(Node parent, String word) {
		if (word.length() == 0) {
			parent.eow = true;
			this.numWords++;
			return;
		}

		Character ch = word.charAt(0);
		String ros = word.substring(1);
		Node child = parent.children.get(ch);
		if (child == null) {
			this.numNodes++;
			child = new Node();
			child.data = ch;
			parent.children.put(ch, child);
			addWord(child, ros);

		} else {
			addWord(child, ros);
		}

	}

	public boolean Search(String Word) {
		return Search(root, Word);
	}

	public boolean Search(Node parent, String Word) {
		if (Word.length() == 0) {
			return parent.eow;
		}
		Character ch = Word.charAt(0);
		String ros = Word.substring(1);
		Node child = parent.children.get(ch);
		if (child == null) {
			return false;
		} else {
			return Search(child, ros);
		}
	}

	public void remove(String Word) {
		remove(root, Word);
	}

	private void remove(Node parent, String Word) {
		if (Word.length() == 0) {
			parent.eow = false;
			this.numWords--;
			return;
		}

	}

	public void display() {
       display(root, "");
	}

	private void display(Node node, String row) {
		if (node.eow == true) {
			System.out.println(row);
		}
		ArrayList<Character> list = new ArrayList<>(node.children.keySet());
		for (Character key : list) {
			Node child = node.children.get(key);
			display(child, row + key);
		}
	}

}
