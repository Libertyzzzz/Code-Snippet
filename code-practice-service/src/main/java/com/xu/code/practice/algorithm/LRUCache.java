package com.xu.code.practice.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    /**
     * @Author liberty
     * @Date 2024/11/30 20:56
     */
    private class Node{
        int key;
        int value;
        Node pre, next;
        public Node(){}
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    Node head, tail;
    Map<Integer, Node> cache;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node != null){
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if(node != null){
            node.value = value;
            moveToHead(node);
        }else{
            if(cache.size() == capacity){
                Node toRemoveNode = tail.pre;
                remove(toRemoveNode);
                cache.remove(toRemoveNode.key);
            }
            Node curr = new Node(key, value);
            add(curr);
            cache.put(key, curr);
        }
    }

    public void remove(Node node){
        node.pre.next = tail;
        tail.pre = node.pre;
    }

    public void add(Node node){
        node.next = head.next;
        node.pre = head.next.pre;
        head.next.pre = node;
        head.next = node;
    }

    public void moveToHead(Node node){
        remove(node);
        add(node);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
    }

}
