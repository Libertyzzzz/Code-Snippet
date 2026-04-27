package com.xu.code.practice.entity;

public class ListNode {

    /**
     * @Author liberty
     * @Date 2024/10/23 10:37
     */

      public  int val;
      public ListNode next;
      public ListNode() {}
      public ListNode(int val) { this.val = val; }
      public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

      public static ListNode createLinkedList(int[] data){
          int n = data.length;
          if(n == 0)
              return null;
          int i = 0;
          ListNode dummy = new ListNode();
          ListNode p = dummy;
          while(i < n){
              int val = data[i];
              ListNode curr = new ListNode(val);
              p.next = curr;
              p = p.next;
              i++;
          }
          return dummy.next;
      }
}
