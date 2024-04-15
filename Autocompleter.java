import java.util.ArrayList;
public class Autocompleter {

    // A helper class that stores a string and a frequency.
    class Entry {
        String s;
        int freq;

        Entry(String word, int x) {
            s = word;
            freq = x;
        }
    }

    // A helper class that implements a binary search tree node.
    class Node {
        Entry e;
        int height;
        Node left;
        Node right;

        Node() {
            height = 0;
            left = right = null;
        }

        Node(Entry temp) {
            e = temp;
            height = 0;
            left = right = null;
        }
    }

    Node root; // Root of the binary-search-tree-based data structure

      Autocompleter() {
        root = null;
    }

    // A convenience method for getting the height of a subtree.
    // Useful for rebalance().
    int height(Node cur) {
        if (cur == null)
            return -1;
        return cur.height;
    }

    // Adds a string x to the dictionary.
    // If x is already in the dictionary, does nothing.
    //
    // Run in O(log(n)) time if your insert_recurse(e, root) takes O(log n) time.
    void insert(String x, int freq) {
        Entry e = new Entry(x, freq);
        root = insert_recurse(e, root);
    }

    // To fill insert_recurse(e, cur) for inserting an Entry e
    // into an AVL tree rooted at cur.
    //
    // Must run in O(log(n)) time.
    Node insert_recurse(Entry e, Node cur) {
        if (cur == null)
            return new Node(e);

        int compareResult = e.s.compareTo(cur.e.s);
        if (compareResult < 0)
            cur.left = insert_recurse(e, cur.left);
        else if (compareResult > 0)
            cur.right = insert_recurse(e, cur.right);

        return rebalance(cur);
    }

    // To fill rebalance() for rebalancing the AVL tree rooted at cur.
    // Helpful for insert_recurse().
    // Should be called on every node visited during
    // the search in reverse search order.
    //
    // Must run in O(1) time.
    Node rebalance(Node cur) {
        if (cur == null)
            return null;

        updateHeight(cur);

        int balanceFactor = getBalanceFactor(cur);

        // Left heavy
        if (balanceFactor > 1) {
            if (getBalanceFactor(cur.left) < 0)
                cur.left = left_rotate(cur.left);
            return right_rotate(cur);
        }
        // Right heavy
        else if (balanceFactor < -1) {
            if (getBalanceFactor(cur.right) > 0)
                cur.right = right_rotate(cur.right);
            return left_rotate(cur);
        }

        return cur;
    }

    // To fill right_rotate(cur) for the right rotation around the node cur
    // of an AVL tree (helpful for implementing rebalance).
    //
    // Must run in O(1) time.
    Node right_rotate(Node cur) {
        Node newRoot = cur.left;
        cur.left = newRoot.right;
        newRoot.right = cur;

        updateHeight(cur);
        updateHeight(newRoot);

        return newRoot;
    }

    // To fill left_rotate(cur) for the left rotation around the node cur
    // of an AVL tree (helpful for implementing rebalance).
    //
    // Must run in O(1) time.
    Node left_rotate(Node cur) {
        Node newRoot = cur.right;
        cur.right = newRoot.left;
        newRoot.left = cur;

        updateHeight(cur);
        updateHeight(newRoot);

        return newRoot;
    }

    // Returns the number of strings in the dictionary
    //
    //
    // Run in O(n) time if your size_recurse(root) takes O(n) time.
    int size() {
        return size_recurse(root);
    }

    // To fill size_recurse() to calculate the size of
    // the binary tree rooted at cur recursively.
    //
    // Must run in O(n) time.
    int size_recurse(Node cur) {
        if (cur == null)
            return 0;
        return size_recurse(cur.left) + size_recurse(cur.right) + 1;
    }

    // Function completions(x, T) fills the Arraylist T with the three most-frequent completions of x.
    // If x has less than three completions, then
    // T is filled with all completions of x.
    // The completions appear in T from most to least frequent.
    //
    // It runs in O(log(n) + k) time if your completions_recurse(String x, Node cur, ArrayList<Entry> C)
    // takes O(log (n) +k) time,
    // where k is the number of completions of x in the dictionary.
    void completions(String x, ArrayList<String> T) {
        ArrayList<Entry> E = new ArrayList<Entry>();
        completions_recurse(x, root, E);
        T.clear();
        int count = 0;
        for (Entry entry : E) {
            if (count >= 3)
                break;
            T.add(entry.s);
            count++;
        }
    }

    // To fill completions_recurse(String x, Node cur, ArrayList<Entry> C) for
    // Filling C with the completions of x in the BST rooted at cur.
    // Note Entrys in C are in ascending order by their freqs.
    //
    // Must run in O(log(n) + k), where
    // -n is the size of the BST rooted at root.
    // -k is the number of Entrys in the BST rooted at cur
    //  whose strings start with x.
    void completions_recurse(String x, Node cur, ArrayList<Entry> C) {
        if (cur == null)
            return;

        int compareResult = x.compareTo(cur.e.s);

        if (compareResult == 0) {
            inorderTraversal(cur, C);
        } else if (compareResult < 0) {
            completions_recurse(x, cur.left, C);
        } else {
            completions_recurse(x, cur.right, C);
        }
    }

    // Helper method for performing in-order traversal and adding entries to ArrayList
    void inorderTraversal(Node cur, ArrayList<Entry> C) {
        if (cur == null)
            return;

        inorderTraversal(cur.right, C);
        C.add(cur.e);
        inorderTraversal(cur.left, C);
    }

    // Helper method to update the height of a node based on its children
    void updateHeight(Node node) {
        if (node == null)
            return;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    // Helper method to calculate the balance factor of a node
    int getBalanceFactor(Node node) {
        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }
}
