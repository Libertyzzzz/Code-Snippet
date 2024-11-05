package com.xu.code.practice.entity;

public class TreeNode {

    /**
     * @Author liberty
     * @Date 2024/11/4 10:17
     */
    public int val;
    public TreeNode  left;
    public TreeNode right;
    public TreeNode(){

    }
    public TreeNode(int val){

    }
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
