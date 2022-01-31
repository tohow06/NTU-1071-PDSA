import edu.princeton.cs.algs4.*;

public class Calculator {

    public Double ans(String e) {
        // please replace the following null to the answer you calculated.
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        String[] temp;
        temp = e.split(" ");

        for (String s : temp) {

            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                Double lastNum = vals.pop();
                if (op.equals("+")) vals.push(lastNum + vals.pop());
                else if (op.equals("*")) vals.push(lastNum * vals.pop());
                else if (op.equals("-")) vals.push(vals.pop() - lastNum);
                else if (op.equals("/")) vals.push(vals.pop() / lastNum);
            } else vals.push(Double.parseDouble(s));
        }

        return vals.pop();
    }

    public static void main(String[] args) {
        Calculator cct = new Calculator();
        String ans = "( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )";
        System.out.println(cct.ans(ans));

    }
}
