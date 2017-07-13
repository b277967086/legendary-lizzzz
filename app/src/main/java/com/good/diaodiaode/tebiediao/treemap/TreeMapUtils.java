package com.good.diaodiaode.tebiediao.treemap;

/**
 * Created by ex-lizheng102 on 2017-07-06.
 */

public class TreeMapUtils {

    private static Node<Integer,String> rootNode;
    public static void putNode(Node<Integer,String> node){

        Node<Integer,String> fater = rootNode;
        if(fater == null) rootNode =node;
        else {
            while (true) {
                int compareTo = node.key.compareTo(fater.key);
                if(compareTo<0){
                    if(fater.left==null){
                        node.father = fater;
                        fater.left = node;
                        break;
                    }else
                       fater = fater.left;
                }else if(compareTo>0) {
                    if(fater.right==null){
                        node.father = fater;
                        fater.right = node;
                        break;
                    }else
                        fater = fater.right;
                }else {
                   node.left = fater.left;
                    if(node.left!=null) node.left.father = node;
                   node.right = fater.right;
                    if(node.right!=null) node.right.father = node;
                   node.father = fater.father;
                }

            }

        }

    }

}
