package com.bo;


import com.bo.pojo.Student;
import com.bo.pojo.Teacher;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class TestDemo {

    private String name = "zhangsan";


    @Test
    public void test00(){
        Double aDouble1 = new Double(31.123456789012305);
        Double aDouble2 = new Double(31.123456789012305);
        System.out.println(aDouble1.equals(aDouble2));
    }

    @Test
    public void test0(){
        System.out.println(name);
        String name = "wangwu";
        System.out.println(name);
    }

    /**
     * 判断 101-200 之间有多少个素数，并输出所有素数
     */
    @Test
    public void test01(){
        List<Integer> list = new ArrayList<>();
        for(int i = 101;i<201;i++){
            int num = 0;
            for(int j = 1;j<=i;j++){
                if(i%j == 0){
                    num++;
                }
            }
            if(num == 2){
                list.add(i);
            }
        }
        System.out.print("素数列表："+list);
    }

    /**
     * 打印出所有水仙花数
     */
    @Test
    public void test02(){
        //需要一个范围
        List<Integer> list = new ArrayList<Integer>();
        for(int i=100;i<1000;i++){
            int a = i/100;
            int a1 = i%100;
            int b = a1/10;
            int c = a1%10;

            if(a*a*a+b*b*b+c*c*c == i){
                list.add(i);
            }
        }
        System.out.println("水仙花数："+list);
    }

    /**
     * 将一个正整数分解质因数。例如：输入 90,打印出 90=2*3*3*5
     */
    //这个写复杂了
    @Test
    public void test03(){
        Scanner scanner = new Scanner(System.in);
        int next = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        int num = 0;
        for(int i = 1;i<=next;i++){
            //获取数据集合中的质数
            for(int j = 1;j<=i;j++){
                if(i%j ==0){
                    num++;
                }
            }
            if(num == 2){
                list.add(i);
            }
            num = 0;
        }
        System.out.println(list);
        //统计出对应的质数然后统计计算
        List<Integer> minNums = new ArrayList<>();
        int b = next;
        for(int j = 0;j<list.size();j++){
            if(next > list.get(0)){
                for(int i = 0;i < list.size();i++){
                    Integer a = list.get(i);
                    if(next%a == 0){
                        next = next/a;
                        minNums.add(a);
                        break;
                    }
                }
            }else{
                break;
            }
        }
        if(!CollectionUtils.isEmpty(minNums)){
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<minNums.size();i++){
               sb.append(minNums.get(i)).append("*");
            }
            sb.delete(sb.length()-1,sb.length());
            System.out.println("质因数分解为"+b+"="+sb.toString());
        }
    }

    /**
     * 三目运算符：利用条件运算符的嵌套来完成此题：学习成绩> =90 分的同学用 A 表示，60-89 分之间的用 B 表示，60 分以下的用 C 表示。
     */
    @Test
    public void test04(){

        List<Integer> grades = new ArrayList<>();
        grades.add(10);
        grades.add(30);
        grades.add(50);
        grades.add(70);
        grades.add(80);
        grades.add(90);
        grades.add(100);


    }

    /**
     * 调用方法时参数是否会发生拷贝
     * 总结：除却自定义对象以及集合的方式在传参过程带入原对象外，其余都是发生拷贝
     * 理解方式：基本数据类型以及包装类，String类型被特定的区分开来按值来进行参数传递，
     * 引用数据对象类型则是将地址给传递过去，将值置为空其实是将地址置为空的操作，而原有的对象不发生改变
     * 而修改引用对象的属性，则是在原有地址上对对象进行修改，进而使值发生改变
     */
    @Test
    public void test05(){

        /**
         * 在基本数据类型下参数发生拷贝
         */
        int a = 0;
        subInt(a);
        System.out.println(a);
        /**
         * 在自定义引用关系下采用的是原地址
         */
        Student student = new Student();
        subStudent(student);
        //例外情况，将整个对象置空时原有不变
        System.out.println(student);

        /**
         *在String类型上参数发生了拷贝
         */
        String s = new String("kk");
        subString(s);
        System.out.println(s);

        /**
         * 包装类也会发生拷贝
         */
        Integer i = new Integer(0);
        subInteger(i);
        System.out.println(i.toString());


    }

    private void subInt(int a){
        a = 1;
        System.out.println(a);
    }

    private void subStudent(Student student){
        student = null;
        System.out.println(student);
    }

    private void subString(String s){
        s = "gg";
        System.out.println(s);
    }

    private void subInteger(Integer i){
        i = new Integer("1");
        System.out.println(i.toString());
    }

    /**
     * 确认class文件在方法区中存放
     * @throws ClassNotFoundException
     */
    @Test
    public void test06() throws ClassNotFoundException {
        Class<Teacher> teacherClass = Teacher.class;
        Class<?> teacher = Class.forName("com.bo.pojo.Teacher");
        System.out.println(teacherClass == teacher);

        Integer i1 = 127;
        int i2 = 127;
        System.out.println(i2 == i1);

        Integer i3 = new Integer(-129);
        int i4 = new Integer(-129);
        System.out.println(i4 == i3);

        String s = "11";
        String s1 = new String("11");
        System.out.println(s1 == s);

        Date date = new Date();
        Date date1 = new Date();
        System.out.println(date.equals(date1));

    }

    /**
     *两种方式遍历Map
     */
    @Test
    public void test07(){
        Map<String, String> map = new HashMap<>();
        map.put("key","value");
        map.put("键","值");
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        for(;iterator.hasNext();){
            String next = iterator.next();
            System.out.println("key:"+next+";value:"+map.get(next));
        }

        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterators = entries.iterator();
        for(;iterators.hasNext();){
            Map.Entry<String, String> next = iterators.next();
            System.out.println("key:"+next.getKey()+";value:"+next.getValue());
        }
    }

    /**
     * 按年龄对学生呢个进行排序
     */
    @Test
    public void test08(){
        Student student1 = new Student();
        student1.setAge(18);
        Student student2 = new Student();
        student2.setAge(19);
        Student student3 = new Student();
        student3.setAge(17);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        Collections.sort(students,new Comparator<Student>(){
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });
        for(Student student:students){
            System.out.println(student.getAge());
        }



    }

}
