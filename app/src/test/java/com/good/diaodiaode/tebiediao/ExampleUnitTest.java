package com.good.diaodiaode.tebiediao;

import com.good.diaodiaode.tebiediao.treemap.Node;
import com.good.diaodiaode.tebiediao.treemap.TreeMapUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void treemap_text() throws Exception {
        TreeMapUtils.putNode(new Node<Integer, String>(11,"小11"));
        TreeMapUtils.putNode(new Node<Integer, String>(0,"小0"));
        TreeMapUtils.putNode(new Node<Integer, String>(3,"小3"));
        TreeMapUtils.putNode(new Node<Integer, String>(5,"小5"));
        TreeMapUtils.putNode(new Node<Integer, String>(4,"小4"));
        TreeMapUtils.putNode(new Node<Integer, String>(7,"小7"));
        TreeMapUtils.putNode(new Node<Integer, String>(9,"小9"));
    }
}