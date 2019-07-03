package com.gyh.manhattan;

import com.gyh.manhattan.utils.JsoupUtil;
import com.gyh.manhattan.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManhattanApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findAllUsers()  {
        redisUtil.set("key","hello");
    }


    @Test
    public void findAllUsers2()  {
        System.out.println("get key value:"+ redisUtil.get("c19d536b-b9c4-4a5d-8aaa-b103d12d5e82"));
    }

    @Test
    public void testPerThread() {
        int length = 1100;
        int perThread = 200;
        // 5
        int a = length/perThread;
        // 6
        int totalThread = a + (length%perThread != 0 ? 1 : 0);
        int[] indexArray = new int[totalThread];
        List<Integer> indexList = new ArrayList<>();
        for (int i=1; i<=totalThread; i++) {
            // 5
            if (i > a-1) {
                indexArray[i] = length - perThread*(i);
                continue;
            }
            indexList.add(perThread*(i));
        }
        for (int i=0; i<indexArray.length; i++) {
            System.out.println(indexArray[i]);
        }
    }

    @Test
    public void testXss() {
        String text = "   <a href=\"http://www.baidu.com/a\" onclick=\"alert(1);\">sss</a><script>alert(0);</script>sss   ";
        System.out.println(JsoupUtil.clean(text));
        text = "<script>alert('备注');</script>";
        System.out.println(JsoupUtil.clean(text));
    }
}
