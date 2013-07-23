package com.qsoft;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 7/18/13
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingleLinkedList
{
    private Integer size;
    private Node first;

    public int getSize()
    {
        return size;
    }

    public SingleLinkedList()
    {
        first = new Node(0);
        size = 0;
    }

    public void add(Node node)
    {

        Node temp = new Node(node.getValue());
        Node current = this.first;

        while (current.getNext() != null)
        {
            current = current.getNext();
        }

        current.setNext(temp);
        size++;
    }

    public Node getNodeFirst()
    {
        return get(1);
    }

    public Node getNodeLast()
    {
        return get(size);
    }

    public Node getNodeBefore(Node node)
    {
        Node current = first;
        int i = 1;
        while (null != current.getNext())
        {
            if (current.getNext().getValue() == node.getValue())
            {
                return get(i - 1);
            }
            i++;
            current = current.getNext();
        }
        return null;
    }

    public Node getNodeAfter(Node node)
    {
        Node current = first;
        int i = 1;
        while (null != current.getNext())
        {
            if (current.getNext().getValue() == node.getValue())
            {
                return get(i + 1);
            }
            i++;
            current = current.getNext();
        }
        return null;
    }

    public Node get(int index)
    {
        if (index <= 0 || index > size)
        {
            return null;
        }

        int i = 1;
        Node current = first;
        while (null != current.getNext() && i < index + 1)
        {
            current = current.getNext();
            i++;
        }
        return current;
    }

    public void createListedListFromArray(Node[] nodes)
    {
        for (Node node : nodes)
        {
            add(node);
        }
    }

    public void insertAfterNode(Node node, Node newNode)
    {
        Node current = first;

        while (current.getNext() != null)
        {

            if (current.getNext().getValue() == node.getValue())
            {

                newNode.setNext(node.getNext());
                node.setNext(newNode);
                size++;
            }
            current = current.getNext();
        }

    }

    public void appendNode(Node node)
    {
        Node current = first;

        while (current.getNext() != null)
        {
            current = current.getNext();
        }
        size++;
        current.setNext(node);
    }

    public void removeNode(Node node)
    {
        Node current = first;

        while (current.getNext() != null)
        {
            if (current.getNext().getValue() == node.getValue())
            {
                size--;
                break;
            }
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());

    }


    public Node findNode(Node node)
    {
        Node current = first;
        while (null != current.getNext())
        {
            if (current.getNext().getValue() == node.getValue())
            {
                return node;
            }

            current = current.getNext();

        }
        return null;
    }


}
