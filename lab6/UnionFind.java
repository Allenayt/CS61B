public class UnionFind {
    /******* Version of Path Compression *********/
    // TODO - Add instance variables?
    int[] root;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex >= root.length) {
            throw new IllegalArgumentException("illegal vertex index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        return -root[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return root[v1];

    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (find(v1) == find(v2)) {

            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (connected(v1, v2)) {
            return;
        }
        int vertex1 = find(v1);
        int vertex2 = find(v2);
        if (sizeOf(vertex1) > sizeOf(vertex2)) {
            root[vertex1] = root[vertex2] + root[vertex1];
            root[vertex2] = vertex1;
        } else {
            root[vertex2] = root[vertex1] + root[vertex2];
            root[vertex1] = vertex2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        if (parent(vertex) < 0) {
            return vertex;
        }
        root[vertex] = find(parent(vertex));
        return root[vertex];
    }

}
