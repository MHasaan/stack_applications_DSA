import java.util.EmptyStackException;			//main at end
                                                //only works considering that given expression is well formated eg.(space between all operators and operands)
class Node<T> 
{
    T data;
    Node<T> next;

    public Node(T data) 
    {
        this.data = data;
        this.next = null;
    }
}

class Stack<T> 
{
    private Node<T> top;   // Head of stack
    private int N;        // Maximum length of the stack
    private int n;        // Total nodes in the stack

    public Stack() 
    {
        this.top = null;
        this.N = 10;
    }

    public Stack(int N) 
    {
        this.top = null;
        this.N = N;
    }

    public void push(T element) 
    {
        if (!isFull()) 
        {
            Node<T> newNode = new Node<>(element);
            newNode.next = top;
            top = newNode;
            this.n++;
        }
        else 
        {
            throw new StackOverflowError("Stack is full");
        }
    }

    public T pop()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }

        T valueToReturn = top.data;
        top = top.next;
        this.n--;

        return valueToReturn;
    }

    public T top()      //named it top instead of like peek
    {
        if (!isEmpty())
        {
            return top.data;
        }
        else
        {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public boolean isFull()
    {
        return n == N;
    }

    public boolean isOneLeftInStack()
    {
        return n == 1;
    }
    

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        Node<T> current = top;
        while (current != null)
        {
            result.append(current.data);
            if (current.next != null)
            {
                result.append(" ");
            }
            current = current.next;
        }
        return result.toString();
    }
}




//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////




class ExpressionEvaluation
{
    public static float evaluatePrefix(String expression)
    {
        Stack<Float> stack = new Stack<>();
        String[] tokens = expression.trim().split("\\s+");

        if(isValidExpression(tokens))
        {
            String token;

            for (int i = tokens.length - 1; i >= 0; i--)
            {
                token = tokens[i];
                
                if (isOperand(token))
                {
                    stack.push(Float.parseFloat(token));
                } 
                else if (isOperator(token)) 
                {
                    float operand1 = stack.pop();
                    float operand2 = stack.pop();
                    float result = performOperation(token, operand1, operand2);
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
    public static float evaluatePostfix(String expression)
    {
        Stack<Float> stack = new Stack<>();
        String[] tokens = expression.trim().split("\\s+");

        if(isValidExpression(tokens))
        {
            String token;

            for (int i = 0; i < tokens.length; i++)
            {
                token = tokens[i];
                
                if (isOperand(token))
                {
                    stack.push(Float.parseFloat(token));
                } 
                else if (isOperator(token)) 
                {
                    float operand2 = stack.pop();
                    float operand1 = stack.pop();
                    float result = performOperation(token, operand1, operand2);
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


///////////////////////////////////////////////////////////////////
    private static boolean isOperand(String token) 
    {
        try 
        {
            Float.parseFloat(token);
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

    
    private static float performOperation(String operator, float operand1, float operand2)
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
}




//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////



//only works considering that given expression is well formated eg.(space between all operators and operands)
public class Main 
{
    public static void main(String[] args) 
    {
        String expression = "/ 13 + 7 * 2 - 5 1";
        float result = ExpressionEvaluation.evaluatePrefix(expression);
        
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
