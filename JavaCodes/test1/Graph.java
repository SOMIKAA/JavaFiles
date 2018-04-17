import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

	public class Vertex {
		HashMap<String, Integer> nbs = new HashMap<>();
	}

	HashMap<String, Vertex> vrtx = new HashMap<>();

	public int numVertices() {
		return vrtx.size();
	}

	public boolean containsVertex(String vname) {
		return vrtx.containsKey(vname);
	}

	public void addVertex(String Vname) {
		if (vrtx.containsKey(Vname)) {
			return;
		}
		Vertex ad = new Vertex();
		vrtx.put(Vname, ad);
	}

	public int numedges() {
		int rv = 0;
		ArrayList<String> keys = new ArrayList<>(vrtx.keySet());
		for (String str : keys) {
			Vertex r = vrtx.get(str);
			rv += r.nbs.size();
		}
		return rv / 2;
	}

	public boolean containsEdge(String v1name, String v2name) {
		Vertex vtx1 = vrtx.get(v1name);
		Vertex vtx2 = vrtx.get(v2name);

		if (vtx1 == null || vtx2 == null) {
			return false;
		}
		return vtx1.nbs.containsKey(v2name);

	}

	public void addEdge(String v1name, String v2name, int weight) {
		Vertex vtx1 = vrtx.get(v1name);
		Vertex vtx2 = vrtx.get(v2name);

		if (vtx1 == null || vtx2 == null) {
			return;
		}

		vtx1.nbs.put(v2name, weight);
		vtx2.nbs.put(v1name, weight);

	}

	public void removeEdge(String v1name, String v2name) {
		Vertex vtx1 = vrtx.get(v1name);
		Vertex vtx2 = vrtx.get(v2name);

		if (vtx1 == null || vtx2 == null) {
			return;
		}

		vtx1.nbs.remove(v2name);
		vtx2.nbs.remove(v1name);

	}

	public void display() {
		System.out.println("-----------------------------");
		ArrayList<String> keys = new ArrayList<>(vrtx.keySet());
		for (String str : keys) {
			System.out.println(str + "=>" + vrtx.get(str).nbs);
		}
		System.out.println("----------------------------");
	}

	public void removeVertex(String vname) {
		if (this.vrtx.containsKey(vname) == false) {
			return;
		}
		Vertex vx1 = vrtx.get(vname);
		ArrayList<String> n = new ArrayList<>(vx1.nbs.keySet());
		for (String str : n) {
			Vertex vx2 = vrtx.get(str);
			vx2.nbs.remove(vname);
		}
		vrtx.remove(vname);

	}

	public boolean haspath(String v1name, String v2name) {
		return haspathhelp(v1name, v2name, new HashMap<>());
	}

	private boolean haspathhelp(String v1name, String v2name, HashMap<String, Boolean> visited) {
		if (visited.containsKey(v1name) == true) {
			return false;
		} else {
			visited.put(v1name, true);
		}
		if (containsEdge(v1name, v2name) == true) {
			return true;
		}
		Vertex vx1 = vrtx.get(v1name);
		ArrayList<String> n = new ArrayList<>(vx1.nbs.keySet());
		for (String str : n) {
			if (haspathhelp(str, v2name, visited) == true) {
				return true;
			}
		}
		return false;

	}

	public boolean bfs(String v1name, String v2name) {
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		Pair rootpair = new Pair(v1name, v1name);

		queue.addLast(rootpair);

		while (queue.size() != 0)

		{
			Pair out = queue.removeFirst();

			if (processed.containsKey(out.vname)) {
				continue;
			} else {
				processed.put(out.vname, true);
			}

			System.out.println(out.vname + "via" + out.psf);
			if (out.vname.equals(v2name)) {
				return true;
			}

		}

		return false;

	}

	private class Pair {
		String vname;
		String psf;

		public Pair(String vname, String psf) {
			this.vname = vname;
			this.psf = psf;
		}
	}

	public boolean isConnected() {
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> k = new ArrayList<>(vrtx.keySet());
		String v1name = k.get(0);

		Pair rootpair = new Pair(v1name, v1name);

		queue.addLast(rootpair);

		while (queue.size() != 0)

		{
			Pair out = queue.removeFirst();

			if (processed.containsKey(out.vname)) {
				continue;
			} else {
				processed.put(out.vname, true);
			}

			Vertex vrt1 = vrtx.get(out.vname);
			ArrayList<String> final1 = new ArrayList<>(vrt1.nbs.keySet());
			for (String str : final1) {
				if (!processed.containsKey(str)) {
					Pair p = new Pair(str, out.psf + str);
					queue.add(p);
				}
			}

		}

		return vrtx.size() == processed.size();

	}

	public void bft() {
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> k = new ArrayList<>(vrtx.keySet());

		for (String v1name : k) {

			if (processed.containsKey(v1name)) {
				continue;
			}

			Pair rootpair = new Pair(v1name, v1name);

			queue.addLast(rootpair);

			while (queue.size() != 0)

			{
				Pair out = queue.removeFirst();

				if (processed.containsKey(out.vname)) {
					continue;
				} else {
					processed.put(out.vname, true);
				}
				System.out.println(out.vname + "via" + out.psf);

				Vertex vrt1 = vrtx.get(out.vname);
				ArrayList<String> final1 = new ArrayList<>(vrt1.nbs.keySet());
				for (String str : final1) {
					if (!processed.containsKey(str)) {
						Pair p = new Pair(str, out.psf + str);
						queue.add(p);
					}
				}

			}
		}

	}

	public boolean Iscyclic() {
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> k = new ArrayList<>(vrtx.keySet());

		for (String v1name : k) {

			if (processed.containsKey(v1name)) {
				continue;
			}

			Pair rootpair = new Pair(v1name, v1name);

			queue.addLast(rootpair);

			while (queue.size() != 0)

			{
				Pair out = queue.removeFirst();
				System.out.println(out.vname + "via" + out.psf);

				if (processed.containsKey(out.vname)) {

					return true;
				} else {
					processed.put(out.vname, true);
				}

				Vertex vrt1 = vrtx.get(out.vname);
				ArrayList<String> final1 = new ArrayList<>(vrt1.nbs.keySet());
				for (String str : final1) {
					if (!processed.containsKey(str)) {
						Pair p = new Pair(str, out.psf + str);
						queue.add(p);
					}
				}

			}

		}
		return false;

	}

	public boolean Istree() {
		return this.isConnected() == true && this.Iscyclic() == false;
	}

	public ArrayList<String> gcc() {
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> k = new ArrayList<>(vrtx.keySet());
		ArrayList<String> l = new ArrayList<>();
		String str1 = "";

		for (String v1name : k) {

			if (processed.containsKey(v1name)) {
				continue;
			}

			Pair rootpair = new Pair(v1name, v1name);
			queue.addLast(rootpair);

			while (queue.size() != 0)

			{
				Pair out = queue.removeFirst();

				if (processed.containsKey(out.vname)) {
					continue;
				} else {
					processed.put(out.vname, true);

				}
				str1 += out.vname;
				Vertex vrt1 = vrtx.get(out.vname);
				ArrayList<String> final1 = new ArrayList<>(vrt1.nbs.keySet());
				for (String str : final1) {
					if (!processed.containsKey(str)) {
						Pair p = new Pair(str, out.psf + str);
						queue.add(p);
					}
				}

			}
			l.add(str1);
			str1 = "";

		}
		return l;
	}

	public boolean dfs(String v1name, String v2name) {
		LinkedList<Pair> Stack = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		Pair rootpair = new Pair(v1name, v1name);

		Stack.addFirst(rootpair);

		while (Stack.size() != 0)

		{
			Pair out = Stack.removeFirst();

			if (processed.containsKey(out.vname)) {
				continue;
			} else {
				processed.put(out.vname, true);
			}

			System.out.println(out.vname + "via" + out.psf);
			if (out.vname.equals(v2name)) {
				return true;
			}

			Vertex vrt1 = vrtx.get(out.vname);
			ArrayList<String> final1 = new ArrayList<>(vrt1.nbs.keySet());
			for (String str : final1) {
				if (!processed.containsKey(str)) {
					Pair p = new Pair(str, out.psf + str);
					Stack.addFirst(p);
				}
			}

		}

		return false;

	}

	public void dft() {
		LinkedList<Pair> Stack = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> k = new ArrayList<>(vrtx.keySet());

		for (String v1name : k) {

			if (processed.containsKey(v1name)) {
				continue;
			}

			Pair rootpair = new Pair(v1name, v1name);

			Stack.addFirst(rootpair);

			while (Stack.size() != 0)

			{
				Pair out = Stack.removeFirst();

				if (processed.containsKey(out.vname)) {
					continue;
				} else {
					processed.put(out.vname, true);
				}
				System.out.println(out.vname + "via" + out.psf);

				Vertex vrt1 = vrtx.get(out.vname);
				ArrayList<String> final1 = new ArrayList<>(vrt1.nbs.keySet());
				for (String str : final1) {
					if (!processed.containsKey(str)) {
						Pair p = new Pair(str, out.psf + str);
						Stack.addFirst(p);
					}
				}

			}
		}

	}
	
	

}
