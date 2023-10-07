public class Main {
    public static void main(String[] args) {
        String expression = "+ 3 * 4 5a";
        int result = ExpressionEvaluation.evaluatePrefix(expression);
        
        System.out.println();
        System.out.println();

        System.out.println("Result: " + result);
    }
}
