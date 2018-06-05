package com.good.diaodiaode.tebiediao.treemap;

/**
 * Created by ex-lizheng102 on 2017-07-06.
 */

public class Node<K,V>{
    K key;
    V value;
    Node father;
    Node left;
    Node right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

}
