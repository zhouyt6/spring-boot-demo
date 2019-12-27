package com.zyt;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Author: zhouyt
 * @Date: 2019/12/25
 * @Description:
 */

public class CollectionTest {

    @Test
    public void concurrenTest() {

        final ConcurrentHashMap<String, Long> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.merge("key", 2L, Long::sum);
        concurrentHashMap.merge("key1", 1L, Long::sum);
        // 原子操作，设置值put*，compute*，merge
//        concurrentHashMap.putIfAbsent("key",new LongAdder()).increment();//  原子操作
//        concurrentHashMap.compute("key",(k,v) -> v==null?1:v+1);  java8
        // 搜索
        final String search = concurrentHashMap.search(100, (k, v) -> v > 1 ? k : null);
        System.out.println(search);
        // 遍历->返回null值自动过滤
        concurrentHashMap.forEach(10,(k,v) -> v>1?
                k+"-> "+v:null,System.out::println);
        concurrentHashMap.forEach((k,v) -> System.out.println(k+" -> "+v));
        // 规约
        // 统计大于1的数目
        Long count = concurrentHashMap.reduceValues(100,
                v -> v > 1 ? 1L : null,
                Long::sum);

        // 特殊的后缀ToInt，ToLong和ToDouble
//        Long sum = concurrentHashMap.reduceValuesToLong(100,
//                v -> v > 1 ? 1L : null,
//                0L, // 默认值必须是累加器的零元素
//                Long::sum);

        /**
         * 并发集试图
         */
        Set<String> wordSet = ConcurrentHashMap.<String>newKeySet();
        /**
         * 并行数组算法
         * Arrays.parallelSort()：可以对一个基本类型值或对象的数组排序
         */
        String[] words = {"a","addd","bbb"};
        Arrays.parallelSort(words, Comparator.comparing(String::length));
        for (String word : words) {
            System.out.println("parallelSort ="+word);
        }
        Arrays.parallelPrefix(words,(x,y) -> x+y);
        for (String word : words) {
            System.out.println("parallelPrefix = "+ word);
        }


        /**
         * 如果指定的键尚未与值相关联，尝试使用给定的映射函数计算其值
         *
         * computeIfPresent():已经有原值是情况下计算新值
         * computeIfAbsent(): 没有原值的时候计算新值
         */
//        concurrentHashMap.computeIfAbsent("key",k -> new LongAdder()).increment();
        /**
        concurrentHashMap.merge("key",1L,(existingValue,newValue) -> existingValue+newValue);
        简写：map.merge("key",1L,Long::sum);

         注释：如果传入compute或merge的函数返回null，将从映射中删除现有的条目
              使用compute或merge，不要做太多工作。
        */


        /**
         * ConcurrentSkipListMap是线程安全的有序的哈希表，适用于高并发的场景。
         * ConcurrentSkipListMap和TreeMap，它们虽然都是有序的哈希表。但是，第一，它们的线程安全机制不同，TreeMap是非线程安全的，而ConcurrentSkipListMap是线程安全的。第二，ConcurrentSkipListMap是通过跳表实现的，而TreeMap是通过红黑树实现的。
         *
         * 在4线程1.6万数据的条件下，ConcurrentHashMap 存取速度是ConcurrentSkipListMap 的4倍左右。
         * 但ConcurrentSkipListMap有几个ConcurrentHashMap 不能比拟的优点：
         * 1、ConcurrentSkipListMap 的key是有序的。
         * 2、ConcurrentSkipListMap 支持更高的并发。ConcurrentSkipListMap 的存取时间是log（N），和线程数几乎无关。也就是说在数据量一定的情况下，并发的线程越多，ConcurrentSkipListMap越能体现出他的优势。
         * 在非多线程的情况下，应当尽量使用TreeMap。此外对于并发性相对较低的并行程序可以使用 Collections.synchronizedSortedMap将TreeMap进行包装，也可以提供较好的效率。对于高并发程序，应当使用ConcurrentSkipListMap，能够提供更高的并发度。
         * 所以在多线程程序中，如果需要对Map的键值进行排序时，请尽量使用ConcurrentSkipListMap，可能得到更好的并发度。
         * 注意，调用ConcurrentSkipListMap的size时，由于多个线程可以同时对映射表进行操作，所以映射表需要遍历整个链表才能返回元素个数，这个操作是个O(log(n))的操作。
         */
        final ConcurrentSkipListMap<Object, Object> concurrentSkipListMap = new ConcurrentSkipListMap<>();

        final ConcurrentSkipListSet<Long> concurrentSkipListSet = new ConcurrentSkipListSet<>();
        /**
         * 构造一个可以被多线程安全访问的无边界非阻塞的队列。
         */
        final ConcurrentLinkedQueue<Object> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    }
}
