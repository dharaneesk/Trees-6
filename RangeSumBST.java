// Time Complexity : O(n)
// Space Complexity : O(h)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Use regular inorder dfs traversal to iterate all the values and add to result if it exist in range
// Using morris postorder traversal we maintain two pointers curr and prev to track motion
// if prev is above curr - its downward motion so keep processing all elements below and add to queue
// if curr is above prev - its upward motion so process elements in queue if all childs are done

class Solution {
    int sum;

    public int rangeSumBST(TreeNode root, int low, int high) {
        sum = 0;
        helper(root, low, high);
        return sum;
    }

    private void helper(TreeNode root, int low, int high) {
        if (root == null)
            return;

        if (root.val >= low) {
            helper(root.left, low, high);
        }

        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }

        if (root.val <= high) {
            helper(root.right, low, high);
        }
    }

    public int postOrderMorrisTraversal(TreeNode root, int low, int high) {

        Deque<TreeNode> st = new ArrayDeque();
        st.push(root);
        int result = 0;
        TreeNode prev = null;

        while (!st.isEmpty()) {
            TreeNode curr = st.peek();

            // upward motion
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    st.push(curr.left);
                } else if (curr.right != null) {
                    st.push(curr.right);
                } else {
                    if (curr.val >= low && curr.val <= high)
                        result += curr.val;
                    st.pop();
                }
            } // downward motion -> processed all the left of curr - need to process right
            else if (curr.left == prev) {
                if (curr.right != null) {
                    st.push(curr.right);
                } else {
                    if (curr.val >= low && curr.val <= high)
                        result += curr.val;
                    st.pop();
                }
            }
            // downward motion -> process all the child of curr
            else if (curr.right == prev) {
                if (curr.val >= low && curr.val <= high)
                    result += curr.val;
                st.pop();
            }

            prev = curr;
        }

        return result;
    }
}