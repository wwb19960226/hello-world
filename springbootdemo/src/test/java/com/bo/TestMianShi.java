package com.bo;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.util.ArrayList;
import java.util.List;

public class TestMianShi {

    private static String fileName = "E:\\practice\\files";

    /**
     * 校验list集合中存储的是变量指向地址还是别的东西
     *
     * 今天在通过IText来画pdf文档表格的时候，发现PdfPTable表中的table对象一直在add一个公有的Cell变量
     * 即table.addCell(cell)，其实这里虽然只用一个cell变量来重复插入到list集合，但在addCell方法内，
     * 是以拷贝的方式又创建了一个新对象，所以在集合中插入的值是不一致的。
     *
     * 但是还有一个问题，list在重复插入一个变量后，内部仍然是两个元素，虽然这俩元素都指向一个地址吧，原因何在
     * 原因吗，list底层是一个数组，可以理解为他内部存储了多个变量来指向对应地址，而一个元素在list集合中被多次add，
     * 只能说这多个元素变量都指向了那同一个地址，所以虽然在List集合中存放了多个元素，但内部指向的都是一个元素。
     */
    @Test
    public void test01(){
        List<String> strings = new ArrayList<>();
        String s = new String("一号");
        strings.add(s);
        s = new String("二号");
        strings.add(s);
        s = "kk";
        for(String ss: strings){
            System.out.println(s.hashCode());
        }
    }

    /**
     * IO流联系并对字符串进行处理
     */
    //可以试着去写一下数组扩容
    @Test
    public void test02() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[1024];
        if(bufferedReader.read(chars,0,-1) != -1){
            


        }


    }
}
