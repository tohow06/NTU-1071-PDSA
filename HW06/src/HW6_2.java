public class HW6_2 {


    public void BTprint(String inString, String preString) {
        //print the postorder string inside this function
        String post = BTpost(inString, preString);
        post = new StringBuilder(post).reverse().toString();
        System.out.println(post);
    }

    private String BTpost(String inString, String preString) {
        String post = new String();

        int indexRootIn;
        inString = aToString(inString.split(" "));
        preString = aToString(preString.split(" "));
        String leftIn;
        String leftPre;
        String rightIn;
        String rightPre;

        if (inString.length() == 0) {
            return "";
        }

        post += preString.charAt(0);
        if (inString.length() <= 1) {
            if (inString.equals(preString)) {
                return post;
            }
        }

        indexRootIn = inString.indexOf(preString.charAt(0));
        leftIn = inString.substring(0, indexRootIn);
        leftPre = preString.substring(1, indexRootIn + 1);
        rightIn = inString.substring(indexRootIn + 1);
        rightPre = preString.substring(indexRootIn + 1);

        post += BTpost(rightIn, rightPre);
        post += BTpost(leftIn, leftPre);

        return post;
    }


    private static String aToString(String[] a) {
        String s = new String();
        for (String n : a) {
            s += n;
        }
        return s;
    }

//    public static void main(String[] args) {
//        HW6_2 a = new HW6_2();
//        a.BTprint("6371402859", "0136742589");
//    }
}
