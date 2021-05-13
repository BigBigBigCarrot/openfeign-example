package pers.david.openfeignexample.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pers.david.openfeignexample.dto.Store;

import java.util.List;

//如果不使用spring cloud(服务注册发现和负载均衡)，那么name可以随便起（根据注解中的url属性执行服务地址），
// 如果使用spring cloud则name应该为对应服务的名称，一个clinet对应一个远端的服务
@FeignClient(name="stores")
public interface StoresClient {
    @RequestMapping(method = RequestMethod.GET, value = "/getStores")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.GET, value = "/echoNameAndAge")
    Store echoNameAndAge(@RequestParam("name") String name, @RequestParam("age")Integer age);

    /**
     * Spring官网中的Open Feign关于这段代码用的是GET方法，当入参中有自定义Java对象时会被认为是requestBody，然后被自动改成Post请求
     * @param store
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/echoStore")
    Store echoStore(Store store);

    @RequestMapping(method = RequestMethod.POST, value = "/update/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") String storeId, Store store);
//    @RequestMapping(method = RequestMethod.POST, value = "/update/{storeId}")
//    Store update(@PathVariable("storeId") String storeId, Store store);
}
