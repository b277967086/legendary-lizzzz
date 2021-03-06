package com.good.diaodiaode.tebiediao;

import com.good.diaodiaode.tebiediao.model.xPoints;
import com.good.diaodiaode.tebiediao.treemap.Node;
import com.good.diaodiaode.tebiediao.treemap.TreeMapUtils;
import com.good.diaodiaode.tebiediao.utils.AlgorithmUtils;
import com.good.diaodiaode.tebiediao.utils.SignUtils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

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
        TreeMapUtils.putNode(new Node<Integer, String>(11, "小11"));
        TreeMapUtils.putNode(new Node<Integer, String>(0, "小0"));
        TreeMapUtils.putNode(new Node<Integer, String>(3, "小3"));
        TreeMapUtils.putNode(new Node<Integer, String>(5, "小5"));
        TreeMapUtils.putNode(new Node<Integer, String>(4, "小4"));
        TreeMapUtils.putNode(new Node<Integer, String>(7, "小7"));
        TreeMapUtils.putNode(new Node<Integer, String>(9, "小9"));
    }

    @Test
    public void getSubString_text() throws Exception {
        assertEquals(AlgorithmUtils.getSubString("asdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhg", 3), "ecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebeb");
    }

    @Test
    public void getSubStrings_text() throws Exception {
        assertEquals(AlgorithmUtils.getSubStrings("asdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhgasdfghljwerognvdfkuhgoierhfvoidsfgajksdbfoiqweufjsbadvoigwerogbvnlkdsfbgiieieywnxmecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebebasdgfdsfhfwerdtjervniousrhdgvlndslfuvhowrnileqoebcebebbcbecccebcaeroiuhkjlvbniuewrhg", 3), "ecebebbecebebceeececeececbebcbecccbebcbeccebeebcbecbeccbbbecbecbeeeecebebcebebebebbebeb");
    }

    @Test
    public void getApiSign_text() throws Exception {
        HashMap<String, String> map1 = new HashMap();
        map1.put("asdasd", "sdfsd电饭锅");
        map1.put("asdasdasd", "sdfasdsd电饭sdogjf锅");
        map1.put("asdaetposd", "sdfsd电sdfg饭锅");
        map1.put("assfdgdasd", "sdfsd电饭锅");
        map1.put("asdfghasd", "sdfsd电fghjf饭锅");
        HashMap map2 = new HashMap();
        map2.put("asdasd", "sdfsd电饭锅");
        map2.put("asdasdasd", "sdfasdsd电饭sdogjf锅");
        map2.put("asdaetposd", "sdfsd电sdfg饭锅");
        map2.put("assfdgdasd", "sdfsd电饭锅");
        map2.put("asdfghasd", "sdfsd电fghjf饭锅");
        assertEquals(SignUtils.getApiSign(map1, map2), SignUtils.getApiSign2(map1, map2));

    }

    @Test
    public void getFeiI_text() throws Exception {
        assertEquals(1, AlgorithmUtils.getfeiI(9));
    }

    @Test
    public void fourSumCount_text() throws Exception {
        assertEquals(new String[]{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"}, AlgorithmUtils.letterCombinations("23"));

    }

    @Test
    public void isas_text() throws Exception {
        assertEquals(new String[]{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"}, AlgorithmUtils.letterCombinations("23"));

    }

    @Test
    public void removeNthFromEnd_text() throws Exception {
        AlgorithmUtils.ListNode head = new AlgorithmUtils.ListNode(1);
        AlgorithmUtils.ListNode pre = head;
        for (int i = 1; i < 5; i++) {
            AlgorithmUtils.ListNode next = new AlgorithmUtils.ListNode(1+i);
            pre.next = next;
            pre = next;
        }

        assertEquals(1, AlgorithmUtils.removeNthFromEnd(head,2));
    }

    @Test
    public void mergeTwoLists_text() throws Exception {
        AlgorithmUtils.ListNode l1 = new AlgorithmUtils.ListNode(1);
        AlgorithmUtils.ListNode l12 = new AlgorithmUtils.ListNode(2);
        AlgorithmUtils.ListNode l13 = new AlgorithmUtils.ListNode(4);
        l1.next = l12;
        l12.next = l13;
        AlgorithmUtils.ListNode l2 = new AlgorithmUtils.ListNode(1);
        AlgorithmUtils.ListNode l22 = new AlgorithmUtils.ListNode(3);
        AlgorithmUtils.ListNode l23 = new AlgorithmUtils.ListNode(4);
        l2.next = l22;
        l22.next = l23;
        assertEquals(1, AlgorithmUtils.mergeTwoLists(l1,l2));

    }

    @Test
    public void lianxu_text() throws Exception {
        ArrayList list = new ArrayList();
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();
        Object o5 = new Object();

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        HashMap<Object, Object> objectObjectHashMap2 = new HashMap<>();
        HashMap<Object, Object> objectObjectHashMap3 = new HashMap<>();

        xPoints xPoints = new xPoints();

        Object o6 = new Object();
        Object o7 = new Object();
        Object o8 = new Object();
        Object o9 = new Object();
        Object o10 = new Object();


//        Log.e("ExampleUnitTest", o1.toString());
//        Log.e("ExampleUnitTest", o2.toString());
//        Log.e("ExampleUnitTest", o3.toString());
//        Log.e("ExampleUnitTest", o4.toString());
//        Log.e("ExampleUnitTest", o5.toString());
//        Log.e("ExampleUnitTest", o6.toString());
//        Log.e("ExampleUnitTest", o7.toString());
//        Log.e("ExampleUnitTest", o8.toString());
//        Log.e("ExampleUnitTest", o9.toString());
//        Log.e("ExampleUnitTest", o10.toString());
//        Log.e("ExampleUnitTest_HashMap", objectObjectHashMap2.toString());

        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        list.add(o6);
        list.add(o8);
        list.add(o10);

//        for (Object o:list) {
//            Log.e("ExampleUnitTest_forea", o.toString());
//        }
    }

    @Test
    public void threadTest_text() throws Exception {
        assertTrue(AlgorithmUtils.threadTest());
    }

    @Test
    public void asdas_text() throws Exception {
        assertEquals("1231",AlgorithmUtils.countAndSay(5));
    }
}