package com.qsoft;


import junit.framework.Assert;
import org.junit.Test;

import javax.accessibility.AccessibleStateSet;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 7/18/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestLinkedList
{
    private void createDataForLinkedList(SingleLinkedList singleLinkedList)
    {

        Node[] nodes = new Node[4];
        nodes[0] = new Node(1);
        nodes[1] = new Node(2);
        nodes[2] = new Node(3);
        nodes[3] = new Node(4);

        singleLinkedList.createListedListFromArray(nodes);
    }

    @Test
    public void testEmptyLinkedList()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        Assert.assertEquals(0, singleLinkedList.getSize());
        Assert.assertEquals(0, singleLinkedList.getFirst().getValue());
    }


    @Test
    public void testCreateListedListFromArray()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        createDataForLinkedList(singleLinkedList);

        Assert.assertEquals(4, singleLinkedList.getSize());
    }

    @Test
    public void testGetFirstNode()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        createDataForLinkedList(singleLinkedList);

        Node data = singleLinkedList.getNodeFirst();
        Assert.assertEquals(1, data.getValue());
    }

    @Test
    public void testGetLastNode()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        createDataForLinkedList(singleLinkedList);

        Node data = singleLinkedList.getNodeLast();
        Assert.assertEquals(4, data.getValue());
    }

    @Test
    public void testGetBeforeNode()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        createDataForLinkedList(singleLinkedList);

        //get node before node 3
        Node data = singleLinkedList.getNodeBefore(singleLinkedList.getNode(3));
        Assert.assertEquals(2, data.getValue());
    }

    @Test
    public void testGetAfterNode()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        createDataForLinkedList(singleLinkedList);
        //get node after node 3
        Node data = singleLinkedList.getNodeAfter(singleLinkedList.getNode(3));
        Assert.assertEquals(4, data.getValue());
    }

    @Test
    public void testFindNode()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        createDataForLinkedList(singleLinkedList);
        //find node 3
        Node data = singleLinkedList.findNode(singleLinkedList.getNode(3));
        Assert.assertEquals(3, data.getValue());

    }

    @Test
    public void testInsertAfter()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        createDataForLinkedList(singleLinkedList);
        //insert node 4 after node 1
        Node node = new Node(4);
        singleLinkedList.insertAfterNode(singleLinkedList.getNode(1), node);

        Node nodeResult = singleLinkedList.getNode(2);
        Assert.assertEquals(4, nodeResult.getValue());
    }

    @Test
    public void testAppendNode()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        createDataForLinkedList(singleLinkedList);
        //append node 5
        Node node = new Node(5);
        singleLinkedList.appendNode(node);

        Assert.assertEquals(5, singleLinkedList.getNodeLast().getValue());
    }

    @Test
    public void testRemoveNode()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        createDataForLinkedList(singleLinkedList);
        //remove node 3
        singleLinkedList.removeNode(singleLinkedList.getNode(3));

        Assert.assertEquals(3, singleLinkedList.getSize());
        Assert.assertTrue(4 == singleLinkedList.getNode(3).getValue());
    }


}
