package com.xu.code.practice.entity;

public class TreeNode {

    /**
     * @Author liberty
     * @Date 2024/11/4 10:17
     */
    int val;
    TreeNode  left;
    TreeNode right;
    TreeNode(){

    }
    TreeNode(int val){

    }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
