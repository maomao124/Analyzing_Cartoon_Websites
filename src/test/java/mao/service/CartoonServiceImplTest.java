package mao.service;

import mao.entity.Cartoon;
import mao.entity.CartoonItem;
import mao.net.HTTP;
import mao.net.RestfulHTTP;
import mao.net.SimpleHTTPImpl;
import mao.net.SimpleRestfulHTTPImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：解析漫画网站
 * Package(包名): mao.service
 * Class(测试类名): CartoonServiceImplTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/10/11
 * Time(创建时间)： 20:01
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

class CartoonServiceImplTest
{

    private static CartoonServiceImpl cartoonService;

    @BeforeAll
    static void beforeAll()
    {
        RestfulHTTP restfulHTTP = new SimpleRestfulHTTPImpl();
        cartoonService = new CartoonServiceImpl(restfulHTTP);
    }

    @Test
    void getCartoonListByJson()
    {
        Map<String, String> map = new HashMap<>();
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.37");
        map.put("Referer", "http://m.qiman57.com/rank/2-1.html");
        map.put("Host", "m.qiman57.com");
        //map.put("Accept", "application/json, text/javascript, */*; q=0.01");
        //map.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        //map.put("Cookie:", "Hm_lvt_9e543e81c8fcea1124d27b659284a99f=1665487230; Hm_lpvt_9e543e81c8fcea1124d27b659284a99f=1665488086");
        List<Cartoon> cartoonList = cartoonService.getCartoonListByJson(
                "http://m.qiman57.com/ajaxf/?page_num=1&type=2", map);
        System.out.println(cartoonList);
    }

    @Test
    void testGetCartoonListByJson()
    {
        Map<String, String> map = new HashMap<>();
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.37");
        map.put("Referer", "http://m.qiman57.com/rank/2-1.html");
        map.put("Host", "m.qiman57.com");
        List<Cartoon> cartoonList = cartoonService.getCartoonListByJson(map, 4);
        System.out.println(cartoonList);
    }

    @Test
    void getCartoonList()
    {
        List<Cartoon> cartoonList = cartoonService.getCartoonList("http://m.qiman57.com/rank/2-1.html");
        System.out.println(cartoonList);
        System.out.println(cartoonList.size());
    }


    @Test
    void getCartoonItemByHtml()
    {
        List<CartoonItem> cartoonItemList = cartoonService.getCartoonItemByHtml(16041);
        System.out.println(cartoonItemList);
    }

    @Test
    void getCartoonItemByJson()
    {
        List<CartoonItem> list = cartoonService.getCartoonItemByJson(16041);
        System.out.println(list);
    }

    @Test
    void getCartoonItem()
    {
//        List<CartoonItem> cartoonItemList = cartoonService.getCartoonItem("http://m.qiman57.com/bookchapter/", 21429);
//        System.out.println(cartoonItemList);
        List<CartoonItem> cartoonItemList = cartoonService.getCartoonItem(16041);
        System.out.println(cartoonItemList);
    }
}