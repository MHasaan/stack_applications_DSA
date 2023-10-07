public class Main {
    public static void main(String[] args) 
    {
        String expression = "/ -15 - 10 + 3 * 4 5";
        int result = ExpressionEvaluation.evaluatePrefix(expression);
        
        System.out.println();
        System.out.println();

        System.out.println("Result: " + result);

        expression = "4 5 * 3 + 10 - -15 /";
        result = ExpressionEvaluation.evaluatePostfix(expression);
        
        System.out.println();
        System.out.println();

        System.out.println("Result: " + result);
    }
}
