import java.util.ArrayList;

public class HW6_1 {

    private static Node root = null;


    //inorder   4 2 5 1 6 7 3 8
    //postorder 4 5 2 6 7 8 3 1

    public Node BTrebuild(String inString, String postString) {
        // Build a binary tree based on in-order and post-order traversal and return the root of binary tree constructed.
        // The judging system will traverse your binary tree from this root, therefore DO NOT return only the key or value of the root.
        int indexRootIn;
        String leftIn;
        String rightIn;
        String leftPost;
        String rightPost;
        String in = aToString(inString.split(" "));
        String post = aToString(postString.split(" "));



        Node r = new Node(Character.getNumericValue(post.charAt(post.length() - 1)), null);
        if (inString.equals(postString)){
            root=r;
            return root;
        }

        //generate new strings
        indexRootIn = in.indexOf(post.charAt(post.length() - 1));
        leftIn = in.substring(0, indexRootIn);
        rightIn = in.substring(indexRootIn + 1);
        leftPost = post.substring(0,leftIn.length());
        rightPost = post.substring(leftIn.length(),post.length()-1);


        r.setLeft(BTrebuild(leftIn,leftPost));
        r.setRight(BTrebuild(rightIn,rightPost));


        root = r;
        return root;
    }


    private static String aToString(String[] a) {
        String s = new String();
        for (String n : a) {
            s += n;
        }
        return s;
    }
}
