import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList <T> implements Iterable <T>
{
    Node head;
    Node tail;
	Node currentNode;
    int size;

    private class ListIterator implements Iterator<T>
    {
        Node current = head;

        public boolean hasNext()    {return current != null;}

        public T next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            
            T data = current.data;
            current = current.next;

            return data;
        }

    }

	public class Node
	{
		T data;
		Node next;
		Node prev;

		Node(T data)
		{
			this.data = data;
			next = null;
			prev = null;
		}

		Node (T data, Node next)
		{
			this(data);
			this.next = next;
		}
	}

    DoublyLinkedList()
    {
        head = null;
        tail = null;
		currentNode = head;
        size = 0;
    }

    public ListIterator iterator()  {return new ListIterator();}

	public T next()
	{
        if (head==null || currentNode==null)
            return null;

		if (currentNode.next!=null)
			currentNode = currentNode.next;
				
		return currentNode.data;
	}
	
	public T prev()
	{
        
        if (head==null || currentNode==null)
        return null;

		if (currentNode.prev!=null)
			currentNode = currentNode.prev;		
		
		return currentNode.data;
	}

    public T get(int index)
    {
        Node current = head;

        for (int i=0; i<index; i++)
        {
            current = current.next;

            if(current==null)
                throw new IndexOutOfBoundsException();
        }
        return current.data;    
    }

    public void insertSorted (T data)
    {

        if (head==null)
        {
            head = new Node(data);
            tail = head;
			//currentNode = head;
            return;
        }

        LocalDate taskDate = LocalDate.parse(data.toString());

        Node current = head;

        while (current!=null && taskDate.isAfter(LocalDate.parse(current.data.toString())))
            current = current.next;

        if (current==head)
        {
            addToFront(data);
            return;
        }


        if (current==null)
        {
            addToBack(data);
            return;
        }

        Node newNode = new Node(data);
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
    }

    public void addToFront(T data)
    {
        Node newNode = new Node(data);

        if (head==null)
        {
            head = newNode;
            tail = newNode;
			currentNode = head;
        }    

        else
        {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
			currentNode = head;
        }    

        ++size;
    }

    public T getFrontItem() 
    {
        return head.data;
    }

    public Node getFront()
    {
        return head;
    }

    public void removeFrontItem()
    {
        if (head==null) return;
        
        head = head.next;
        head.prev = null;
		currentNode = head;
    }

    public void addToBack(T data)
    {
        Node newNode = new Node(data);
        if (head==null)
        {
            head = newNode;
            tail = newNode;
			currentNode = head;
        }

        else
        {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
			currentNode = head;
        }

        ++size;
    }

    public T getBackItem()
    {
        return tail.data;
    }

    public void removeBackItem()
    {
        if (head==null)
            return;

        if (head.next==null)
        {
            head = null;
            tail = null;
			currentNode = null;
            return;
        }    

        tail = tail.prev;
        tail.next = null;
    }

    public boolean find(T key)
	{
		Node current = head;

		while(current!=null)
		{
			if (current.data==key)
				return true;

			current = current.next;	
		}

		return false;
	}

    public void remove(T key)
    {
        if (head==null)
            return;
        else if (tail.data==key)
        {
            removeBackItem();
            return;
        }
        else if (head.data==key)
        {
            removeFrontItem();
            return;
        }    
        
        Node current = head.next;

        while(current.next!=null)
        {
            if (current.data==key)
            {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                return;
            }

            current = current.next;
        }
    }

    
    public boolean isListEmpty() {return (head==null);}
    
    public void addKeyBeforeNode(T key, T nodeData)
    {
        if (head==null)
        return;
        
        if (head.data==nodeData)
        {
            addToFront(key);
            return;
        }
        
        Node current = head.next;
        
        while (current!=null)
        {
            if (current.data==nodeData)
            {
                Node newNode = new Node(key);
                current.prev.next = newNode;
                newNode.prev = current.prev;
                current.prev = newNode;
                newNode.next = current;
                return;
            }
            
            current = current.next;
        }

        ++size;
    }

    public void addKeyAfterNode(T key, T nodeData)
    {
        if (head==null)
            return;

        if (tail.data==nodeData)
        {
            addToBack(key);
            return;
        }    

        Node current = head;
        
        while (current.next!=null)
        {
            if (current.data==nodeData)
            {
                Node newNode = new Node(key);
                current.next.prev = newNode;
                newNode.next = current.next;
                current.next = newNode;
                newNode.prev = current;
                return;
            }
            
            current = current.next;
        }

        ++size;
    }

    public boolean contains(T data)
    {
        Node current = head;

        while (current!=null)
        {
            if (current.data.equals(data))
                return true;
            
            current = current.next;
        }

        return false;
    }

    public int size()
    {
        Node current = head;
        int s = 0;

        while (current!=null)
        {
            current = current.next;
            ++s;
        }
        
        return s;
    }

    public void printAll()
	{
		Node current = head;

		System.out.print("[");
		while(current!=null)
		{
			System.out.print(current.data+",");
			current = current.next;

		}
		System.out.println("]");	
	}



}
