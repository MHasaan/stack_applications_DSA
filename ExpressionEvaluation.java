public class ExpressionEvaluation
{
    public static int evaluatePrefix(String expression)
    {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expression.trim().split("\\s+");

        if(isValidExpression(tokens))
        {
            String token;

            for (int i = tokens.length - 1; i >= 0; i--)
            {
                token = tokens[i];
                
                if (isOperand(token))
                {
                    stack.push(Integer.parseInt(token));
                } 
                else if (isOperator(token)) 
                {
                    int operand1 = stack.pop();
                    int operand2 = stack.pop();
                    int result = performOperation(token, operand1, operand2);
                    stack.push(result);
                }
            }   
        }
        else
        {
            throw new IllegalArgumentException("the expression is invalid: " + expression);
        }    
        return stack.pop(); 
    }


    public static int evaluatePostfix(String expression)
    {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expression.trim().split("\\s+");

        if(isValidExpression(tokens))
        {
            String token;

            for (int i = 0; i < tokens.length; i++)
            {
                token = tokens[i];
                
                if (isOperand(token))
                {
                    stack.push(Integer.parseInt(token));
                } 
                else if (isOperator(token)) 
                {
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    int result = performOperation(token, operand1, operand2);
                    stack.push(result);
                }
            }   
        }
        else
        {
            throw new IllegalArgumentException("the expression is invalid: " + expression);
        }    
        return stack.pop(); 
    }


    




    private static boolean isOperand(String token) 
    {
        try 
        {
            Integer.parseInt(token);
            return true;
        }
        catch (NumberFormatException e) 
        {
            return false;
        }
    }
    

    private static boolean isOperator(String token) 
    {
        return  token.equals("+") || 
                token.equals("-") || 
                token.equals("*") || 
                token.equals("/");
    }

    
    private static int performOperation(String operator, int operand1, int operand2) 
    {
        // Perform the specified operation
        switch (operator) 
        {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) 
                {
                    return operand1 / operand2;
                }
                else 
                {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static boolean isValidExpression(String[] tokens) 
    {
        if (tokens.length == 0)
        {
            return false;
        }
        else
        {
            int operandCount = 0;
            int operatorCount = 0;

            for (String token : tokens) 
            {
                if (isOperand(token)) 
                {
                    operandCount++;
                } 
                else if (isOperator(token)) 
                {
                    operatorCount++;
                } 
                else 
                {
                    return false;
                }
            }

            //validity based on number of operand and operators
            return operandCount == operatorCount + 1;
        }
    }








    public static String infixToPostfix(String expression) 
    {
        StringBuilder postfixConvertedExpression = new StringBuilder();
        Stack<String> operatorStack = new Stack<>();

        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) 
        {
            if (isOperand(token)) 
            {
                postfixConvertedExpression.append(token).append(" ");
            } 
            else if (isOperator(token)) 
            {
                while (!operatorStack.isEmpty() && precedence(token) <= precedence(operatorStack.top())) 
                {
                    postfixConvertedExpression.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) 
        {
            postfixConvertedExpression.append(operatorStack.pop()).append(" ");
        }

        return postfixConvertedExpression.toString().trim();
}


    private static int precedence(String c) 
    {
        switch (c)
        {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }
}
