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

///////////////////////////////////////////////////////////////////
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

///////////////////////////////////////////////////////////////////
    public static String infixToPostfix(String expression) 
    {
        String postfixConvertedExpression = "";
        Stack<String> operatorStack = new Stack<>();

        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) 
        {
            if (isOperand(token)) 
            {
                postfixConvertedExpression += token + " ";
            }

            else if (isOperator(token)) 
            {
                while (!operatorStack.isEmpty() && isOperator(operatorStack.top()) && precedence(token) <= precedence(operatorStack.top())) 
                {
                    postfixConvertedExpression += operatorStack.pop() + " ";
                }
                operatorStack.push(token);  //Stack 1+_ 2+_ 1*_ 1/
            }                               //string n n 1+ n n 1* 2+
            else if(token == "(")
            {
                operatorStack.push(token);
            }
            else if(token == ")")
            {
                while( !operatorStack.isEmpty() && operatorStack.top() == ")")
		{
                    postfixConvertedExpression += operatorStack.pop() + " ";
		}
                if( !operatorStack.isEmpty() && operatorStack.top() == "(")
                {
                    operatorStack.pop();
                }

            }
        }

        while (!operatorStack.isEmpty()) 
        {
            postfixConvertedExpression += operatorStack.pop() + " ";
        }

        return postfixConvertedExpression.trim();
    }

///////////////////////////////////////////////////////////////////
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
            int paranthesisCount = 0;

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
                else if (token == "(")
                {
                    paranthesisCount++;
                }
                else if (token == ")")
                {
                    paranthesisCount--;
                }
                else
                {
                    return false;
                }
            }

            if( paranthesisCount != 0 )
            {
                return false;
            }
            //validity based on number of operand and operators
            return operandCount == operatorCount + 1;
        }
    }
}
