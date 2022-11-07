package ch.heigvd.api.calc;

import java.util.*;

public class ReversePolishCalculator {

    final static List<String> OPS = Arrays.asList("+", "-", "*", "/");

    interface Operation{
        int calculate(int a, int b);
    }

    enum MathOperation implements Operation{
        ADD('+'){
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        },
        SUB('-'){
            @Override
            public int calculate(int a, int b) {
                return a - b;
            }
        },
        MUL('*'){
            @Override
            public int calculate(int a, int b) {
                return a * b;
            }
        },
        DIV('/'){
            @Override
            public int calculate(int a, int b) {
                return a / b;
            }
        };

        private final char op;
        private MathOperation(char c){
            this.op = c;
        }

        public static MathOperation getByOp(final char op){
            for(final MathOperation math : values()){
                if(math.op == op) return math;
            }
            return null;
        }
    }

    /**
     * Return the result of the specified reverse polish operation line
     * Source : https://gist.github.com/sgtcortez/a521bd9cec7c36dee99e0323977c2f01
     * @param line String with the reverse polish operation to execute
     */
    public int calculatorInvRev(String line){
        final String[] elements = line.split(" ");
        final Stack<Integer> stack = new Stack<>();

        for(int index = 0; index < elements.length; ++index){
            final String current = elements[index];
            if(OPS.contains(current)){
                final int right = stack.pop();
                final int left = stack.pop();
                final MathOperation operator = MathOperation.getByOp(current.charAt(0));
                final int result = operator.calculate(left, right);
                stack.push(result);
            }
            else{
                stack.push(Integer.valueOf(current));
            }
        }
        return stack.pop();
    }
}
