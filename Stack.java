import java.util.EmptyStackException;

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
