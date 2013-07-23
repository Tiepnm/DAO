package com.qsoft;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 7/18/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Node
{
    private Node next;
    private int value;

    public Node(int value)
    {
        this.value = value;
    }

    public Node getNext()
    {
        return next;
    }

    public void setNext(Node temp)
    {
        next = temp;
    }

    public int getValue()
    {
        return value;
    }
}
