class Node 
{
    int data;
    Node next;

    public Node(int data) 
    {
        this.data = data;
        this.next = null;
    }
}



class Stack 
{
    private Node top;


    public Stack() 
    {
        this.top = null;
    }


    public void push(int element)
    {
        Node newNode = new Node(element);
        newNode.next = top;
        top = newNode;
    }


    public int pop() 
    {
        if (isEmpty()) 
        {
            System.out.println("Stack is Empty - Cannot Pop");
            return -1; // Return a special value to indicate an empty stack
        }
        int elementToReturn = top.data;
        top = top.next;
        return elementToReturn;
    }


    public boolean isEmpty() 
    {
        return top == null;
    }

    public boolean isOneLeftInStack()
    {
        return top.next==null;
    }


    @Override
    public String toString() 
    {
        StringBuilder result = new StringBuilder();
        Node current = top;
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
