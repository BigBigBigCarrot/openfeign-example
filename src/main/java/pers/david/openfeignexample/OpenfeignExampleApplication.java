package pers.david.openfeignexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import pers.david.openfeignexample.client.StoresClient;
import pers.david.openfeignexample.dto.Store;

import java.util.ArrayList;
import java.util.List;

//使用注解开始OpenFeign
@EnableFeignClients
//使用注解开始Nacos服务注册发现
@EnableDiscoveryClient
@SpringBootApplication
public class OpenfeignExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignExampleApplication.class, args);
    }

    @RestController
    class StoresController{
        @GetMapping("getStores")
        List<Store> getStores(){
            return getStores(10);
        }

        @GetMapping("echoNameAndAge")
        Store echoStore(@RequestParam("name") String name,@RequestParam("age")Integer age){
            Store store=new Store();
            store.setName(name);
            store.setAge(age);
            return store;
        }

        @PostMapping("echoStore")
        Store echoStore(@RequestBody Store store){
            return store;
        }

        @PostMapping("update/{storeId}")
        Store update(@PathVariable("storeId") String storeId,@RequestBody Store store)
        {
            store.setId(storeId);
            return store;
        }

        private List<Store> getStores(int size){
            List<Store> stores=new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Store store=new Store();
                store.setId(i+"");
                store.setName("jack"+i);
                store.setAge(i);
                stores.add(store);
            }
            return stores;
        }
    }
}
