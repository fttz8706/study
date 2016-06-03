package com.yjm.study.binarytree;

/**
 * 二叉树相关算法
 *
 * Created by yangjinming on 16/6/2.
 */
public class BinaryTree {

    private Node root;

    public BinaryTree(){
        root = new Node(0, null, null);
    }

    /**
     * 递归法获取二叉树的镜像
     * @param node
     */
    public void getMirror(Node node){
        if(node != null && (node.getLeft() != null || node.getRight() != null)){
            Node left = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(left);
            getMirror(node.getLeft());
            getMirror(node.getRight());
        }
    }

    /**
     * 前序遍历
     * @param node
     */
    public void beforeTraverse(Node node){
       if(node != null){
           System.out.print(node.getData() + ", ");
           beforeTraverse(node.getLeft());
           beforeTraverse(node.getRight());
       }
    }

    /**
     * 中序遍历
     * @param node
     */
    public void middleTraverse(Node node){
        if(node != null){
            middleTraverse(node.getLeft());
            System.out.print(node.getData() + ", ");
            middleTraverse(node.getRight());
        }
    }

    /**
     * 后序遍历
     * @param node
     */
    public void afterTraverse(Node node){
        if(node != null){
            afterTraverse(node.getLeft());
            afterTraverse(node.getRight());
            System.out.print(node.getData() + ", ");
        }
    }

    public static void main(String[] args){
        BinaryTree btree = new BinaryTree();
        btree.buildBinaryTree(btree.getRoot());
        // btree.getMirror(btree.getRoot());
        /*btree.beforeTraverse(btree.getRoot());
        btree.middleTraverse(btree.getRoot());
        btree.afterTraverse(btree.getRoot());*/
        System.out.println("finished...");
    }

    private void buildBinaryTree(Node root){
        Node n_1_1 = new Node(1, null, null);
        Node n_1_2 = new Node(2, null, null);
        Node n_2_1 = new Node(3, null, null);
        n_1_1.setLeft(n_2_1);
        root.setLeft(n_1_1);
        root.setRight(n_1_2);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static class Node{
        private int data;
        private Node left;
        private Node right;
        Node(){}
        Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}