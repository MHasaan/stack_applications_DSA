public class Main {
    public static void main(String[] args) 
    {
        String expression = "/ 13 + 7 * 2 - 5 1";
        int result = ExpressionEvaluation.evaluatePrefix(expression);
        
        System.out.println();
        System.out.println();

        System.out.println("Result: " + result);

        expression = ExpressionEvaluation.infixToPostfix("6 * ( 2 - 3 ) * 5 + 5");
        System.out.println(expression);

        result = ExpressionEvaluation.evaluatePostfix(expression);
        System.out.println();
        System.out.println();

        System.out.println("Result: " + result);
    }
}
