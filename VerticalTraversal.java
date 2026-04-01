// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// By doing level order traversal and keeping an extra log of the right and left level, we add the element to a map <level, elem>
// we note the min and max values of levels 
// iterate through min to max in the map and add it to the result array

public class VerticalTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        int min = 0;
        int max = 0;

        nodes.add(root);
        levels.add(0);

        while (!nodes.isEmpty()) {
            TreeNode curr = nodes.poll();
            int col = levels.poll();

            if (!map.containsKey(col))
                map.put(col, new ArrayList<>());
            map.get(col).add(curr.val);

            if (curr.left != null) {
                nodes.add(curr.left);
                levels.add(col - 1);
                min = Math.min(min, col - 1);
            }

            if (curr.right != null) {
                nodes.add(curr.right);
                levels.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }

        for (int i = min; i <= max; i++)
            result.add(map.get(i));

        return result;

    }

}
