package com.bo;

import org.junit.Test;

import java.util.*;

/**
 * 数据结构数组扩容，线性数组一系列的测试类
 * ArrayList的扩容较为复杂，虽然是从10开始扩容的，但不会像现在一样扩容的位置上有值并且都为null，需要去考虑一下
 */
public class TestArrayData<T> {

    /**
     * 测试普通的数组扩容
     */
    @Test
    public void test01() {
        Integer[] arr = new Integer[10];
        System.out.println(arr.length);//10  初始化的长度为10，这10个元素均为null
        System.out.println(arr[0]); //null  内存已经被分配了，当前内存存放数据为null
        System.out.println(arr.hashCode());//739498517   当前数组被分配的hash为739498517

        Integer[] arr1 = {1, 2, 3};
        System.out.println(arr1.length);
        System.out.println(arr1.hashCode());

        Integer[] arr2 = new Integer[]{};
        arr1 = Arrays.copyOf(arr1, 5);
        System.out.println(arr1.length);
        System.out.println(arr1.hashCode());//hash值发生变化，确认是新建的复制数组

        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]);
        }
    }

    /**
     * 两个排序递增数组，只插入不同的数据
     */
    @Test
    public void test02() {
        Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5};
        Integer[] arr2 = {3, 4, 5, 6, 7};

        List<Integer> list1 = Arrays.asList(arr1);

        Set<Integer> set1 = new HashSet<>(list1);

        for (int i = 0; i < arr2.length; i++) {
            //去重的情况下采用set，不去重的情况下采用List
            /*if(!list1.contains(arr2[i])){
                arr1 = Arrays.copyOf(arr1,arr1.length+1);
                arr1[arr1.length-1] = arr2[i];
            }*/
            if (!set1.contains(arr2[i])) {
                arr1 = Arrays.copyOf(arr1, arr1.length + 1);
                arr1[arr1.length - 1] = arr2[i];
            }
        }

        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]);
        }
    }


    /**
     * 两个不同的递增线性数组，将他们的不同元素插入，并按照顺序来排序，并按照线性排列 三种方法
     * 嵌套for循环，时间复杂度为i*j
     * i和j同时进入一个循环，并相互比较值，时间复杂度为i+j
     * 如test02方法，先将数据在后方插入，然后在通过排序排序，时间复杂度也不低
     */
    @Test
    public void test03() {
        Integer[] arr1 = new Integer[]{1, 2, 4, 5, 6};
        Integer[] arr2 = {3, 4, 5, 6, 7};
        //这里先行写死，但数组是可以扩容的，要明白这一点
        Integer length = 10;
        Integer[] arr3 = new Integer[10];
        int i = 0;
        int j = 0;
        int l = 0;


        while (arr1.length > i && arr2.length > j) {
            if (arr1[i] < arr2[j]) {
                arr3[l] = arr1[i];
                i++;
            } else if (arr1[i] == arr2[j]) {
                arr3[l] = arr1[i];
                ;
                i++;
                j++;
            } else {
                arr3[l] = arr2[j];
                j++;
            }
            l++;
        }
        while (i < arr1.length) {
            arr3[l] = arr1[i];
            i++;
            l++;
        }
        while (j < arr2.length) {
            arr3[l] = arr2[j];
            j++;
            l++;
        }

        for (int k = 0; k < arr3.length; k++) {
            System.out.println(arr3[k]);
        }
    }

    /**
     * 在数组中新增一个元素，该元素位置值为原先的数组位置，并且之后的元素位置需要去加1
     */
    @Test
    public void test04(){
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8};
        int site = 6;
        int data = 12;
        arr = insertArr(arr,site,data);

        for(int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    /**
     * 在数组中某个元素的位置添加数据
     * @param arr 要添加字段的数组
     * @param site 在数组中添加字段的位置
     * @param data 在数组位置上添加字段的数据
     */
    private Integer[] insertArr(Integer[] arr, int site, int data) {
    //  将其转化为9位数组
        arr = Arrays.copyOf(arr,arr.length+1);
    //
        for(int i = arr.length-1;i>site-1;i--){
            arr[i] = arr[i-1];
        }
        arr[site-1] = data;
        return arr;
    }

    /**
     * 线性数组删除数据
     */
    @Test
    public void test05(){
        Integer[] arr = new Integer[]{1,2,3,4,5,6};
        Integer site = 4;
        Integer data = 12;
        arr = deleteArrData(arr,site);

        for(int i = 0;i<arr.length;i++)
            System.out.print(arr[i]);

    }

    /**
     * 删除数组中指定位置的元素并将数组容量减小
     * @param arr
     * @param site
     * @return
     */
    private Integer[] deleteArrData(Integer[] arr, Integer site) {
        arr[site-1] = null;
        for(int i = site-1;i<arr.length-1;i++){
            arr[i] = arr[i+1];
        }
        arr = Arrays.copyOf(arr,arr.length-1);
        return arr;
    }


    /**
     * 扩容数组，当插入数据长度大于0.75倍时，将数据长度扩容到原有的两倍
     * @return
     */
    private Integer dilatationArray(Object[] array){
        //怎么确认插入的数据是长度的0.75倍呢，需要加一个记录插入该字段的长度size，但源码中我没有看到初始长度为10的操作
        if(array.length > 0){

        }

        return null;
    }
}
