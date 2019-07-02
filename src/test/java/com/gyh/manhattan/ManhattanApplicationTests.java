package com.gyh.manhattan;

import com.alibaba.fastjson.JSON;
import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.model.SysUserRoleModel;
import com.gyh.manhattan.service.SysUserRoleService;
import com.gyh.manhattan.utils.JsoupUtil;
import com.gyh.manhattan.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Test
    public void testSysUserRole() {
        Map<String, Object> params1 = new HashMap<>();
        params1.put("userId", 1L);
        List<SysUserRoleModel> list1 = sysUserRoleService.findUserRoleList(params1);
        System.out.println(JSON.toJSONString(list1));
        List<SysUserRoleModel> list2 = sysUserRoleService.findUserRoleResourceList(params1);
        System.out.println(JSON.toJSONString(list2));
    }

    @Test
    public void testXss() {
        String text = "   <a href=\"http://www.baidu.com/a\" onclick=\"alert(1);\">sss</a><script>alert(0);</script>sss   ";
        System.out.println(JsoupUtil.clean(text));
    }
}
