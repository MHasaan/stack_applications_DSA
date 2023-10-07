public class ExpressionEvaluation
{
    public static int evaluatePrefix(String expression)
    {
        Stack stack = new Stack();
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
        Stack stack = new Stack();
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
                    return false; // Invalid token
                }
            }

            //validity based on number of operand and operators
            return operandCount == operatorCount + 1;
        }
    }
}
