package com.bo.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * redis工具类
 */
@Component
public class ZsetRedisBaseUtils<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取Zset集合中权重最大分数对象
     * @param key redis中的键
     * @return
     */
    public T ZsetScoreMax(String key){

        if(StringUtils.isEmpty(key)){
            return null;
        }
        ZSetOperations zset = getZset();
        if(zset == null){
            return null;
        }
        Set<T> set = zset.reverseRange(key,0,-1);
        if(!CollectionUtils.isEmpty(set)){
            return set.iterator().next();
        }
        return null;
    }

    /**
     * 获取Zset集合中最小的权重对象
     * @param key
     * @return
     */
   public T ZsetScoreMin(String key){
        if(StringUtils.isEmpty(key)){
            return null;
        }
       ZSetOperations zset = getZset();
       if(zset == null){
           return null;
       }
       Set<T> range = zset.range(key, 0, -1);
       if(CollectionUtils.isEmpty(range)){
           return range.iterator().next();
       }
       return null;
   }

    /**
     * 假设个需求，把学生平均分配给某一批老师，老师存放在zset中，学生在传入的集合中
     * @param key  redis中存放的zset键
     * @return
     */
   public T distributeByZset(String key){
       if(StringUtils.isEmpty(key)){
           return null;
       }
       ZSetOperations zset = getZset();
       if(zset == null){
           return null;
       }
       Set<T> range = zset.range(key, 0, -1);
       //要分配到的数据为空
       if(CollectionUtils.isEmpty(range)){
           return null;
       }
       //获取最小权重
       Double score = zset.score(key, range.iterator().next());
       //获取最小权重对应的数据
       Set<T> set = zset.rangeByScore(key, score, score);
       //如果多台redis服务器并发确实得考虑影响，这里set判断应该不只一次
       if(CollectionUtils.isEmpty(set)){
           score++;
           set = zset.rangeByScore(key,score,score);
       }
       if(!CollectionUtils.isEmpty(set)){
          List<T> ts = new ArrayList<>(set);
           Collections.shuffle(ts);
           T t = ts.get(0);
           zset.incrementScore(key,t,1);
           return t;
       }

       return null;
   }

    /**
     * 获取全部的Zset键值对
     * @return
     */
   private ZSetOperations getZset(){
        return redisTemplate.opsForZSet();
   }
}
