package pers.david.openfeignexample;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.david.openfeignexample.client.StoresClient;
import pers.david.openfeignexample.dto.Store;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class OpenfeignExampleApplicationTests {

    @Autowired
    StoresClient storesClient;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetStores(){
        System.out.println("result: "+ JSON.toJSONString(storesClient.getStores()));
    }

    @Test
    void testEchoNameAndAge(){
        System.out.println("result: "+ JSON.toJSONString(storesClient.echoNameAndAge("maria",11)));
    }

    @Test
    void testEchoStore(){
        Store store=new Store();
        store.setId("id1");
        store.setName("mike");
        store.setAge(25);

        System.out.println("result: "+ JSON.toJSONString(storesClient.echoStore(store)));
    }

    @Test
    void testUpdate(){
        Store store=new Store();
        store.setId("id2");
        store.setName("jane");
        store.setAge(25);

        System.out.println("result: "+ JSON.toJSONString(storesClient.update("jane",store)));
    }

}
