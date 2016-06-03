package com.yjm.study.list;

/**
 * 单链表
 *
 * Created by yangjinming on 16/6/3.
 */
public class List {

    private Node head;

    public List(){
        head = new Node(-1, null);
    }

    /**
     * 递归计算单链表反转
     * @param node 头结点
     * @return 返回反转之后链表的头节点head
     */
    public Node reverse(Node node) {
        if (node == null || node.getNext() == null) {
            return node;
        } else {
            Node head = reverse(node.next);
            node.next.next = node;
            node.next = null;
            return head;
        }
    }

    /**
     * 非递归计算单链表反转
     * @param node 头节点
     * @return 返回反转之后链表的头节点head
     */
    public Node reverseList(Node node){
        if(node != null){
            Node p = node;
            Node next = node.next;
            node.next = null;
            while(next != null){
                Node temp = next;
                next = next.next;
                temp.next = p;
                p = temp;
            }
            return p;
        }
        return null;
    }

    public static void main(String[] args){
        List list = new List();
        list.bulidList(list.getHead());
        Node node = list.reverseList(list.getHead());
        list.setHead(node);
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    private void bulidList(Node head){
        head.setNext(new Node(0, new Node(1, new Node(2, new Node(3, null)))));
    }

    private class Node{
        private int data;
        private Node next;
        Node(){}
        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
