// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Serialize using bfs or dfs (pos/pre) by appending val or # in case of null child
// To deserialize convert the string seperated by , to string arr
// process each elem first, in the same order you serialized the string
// Dont process null values in deserialize , just skip # values

public class SerializeDeserializeTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";

        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr != null) {
                sb.append(curr.val);
                q.add(curr.left);
                q.add(curr.right);
            } else
                sb.append('#');
            sb.append(',');
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data.length() == 0)
            return null;
        String[] arr = data.split(",");
        Deque<TreeNode> q = new ArrayDeque<>();

        int i = 0;
        TreeNode root = new TreeNode(Integer.parseInt(arr[i++]));
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (!arr[i].equals("#")) {
                curr.left = new TreeNode(Integer.parseInt(arr[i]));
                q.add(curr.left);
            }
            i++;

            if (!arr[i].equals("#")) {
                curr.right = new TreeNode(Integer.parseInt(arr[i]));
                q.add(curr.right);
            }
            i++;
        }

        return root;
    }

    public class DFSWay {

        // Encodes a tree to a single string.
        StringBuilder sb;

        public String serialize(TreeNode root) {
            this.sb = new StringBuilder();
            serializeHelper(root);
            return sb.toString();
        }

        private void serializeHelper(TreeNode root) {
            if (root == null) {
                sb.append("#,");
                return;
            }

            sb.append(root.val);
            sb.append(',');
            serializeHelper(root.left);
            serializeHelper(root.right);
        }

        // Decodes your encoded data to tree.
        int i;

        public TreeNode deserialize(String data) {
            if (data.length() == 0)
                return null;
            String[] arr = data.split(",");
            i = 0;
            return deserializeHelper(arr);
        }

        private TreeNode deserializeHelper(String[] arr) {
            if (arr[i].equals("#")) {
                i++;
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(arr[i]));
            i++;
            root.left = deserializeHelper(arr);
            root.right = deserializeHelper(arr);

            return root;
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));