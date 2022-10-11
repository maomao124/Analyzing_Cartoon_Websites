package mao.service;

import com.alibaba.fastjson.JSON;
import mao.entity.Cartoon;
import mao.net.HTTP;
import mao.net.RestfulHTTP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project name(项目名称)：解析漫画网站
 * Package(包名): mao.service
 * Class(类名): CartoonServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/10/11
 * Time(创建时间)： 19:41
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class CartoonServiceImpl implements CartoonService
{

    private final RestfulHTTP http;

    public CartoonServiceImpl(RestfulHTTP http)
    {
        this.http = http;
    }


    /**
     * 得到漫画列表json
     *
     * @param urlString url字符串
     * @return {@link List}<{@link Cartoon}>
     */
    public List<Cartoon> getCartoonListByJson(String urlString, Map<String, String> requestHeader)
    {
        String json = http.GET(urlString, requestHeader);
        //System.out.println(json);
        List<Cartoon> cartoonList = JSON.parseArray(json, Cartoon.class);
        return cartoonList;
    }

    public List<Cartoon> getCartoonListByJson(Map<String, String> requestHeader, int pageNumber)
    {
        return this.getCartoonListByJson("http://m.qiman57.com/ajaxf/?page_num=" + pageNumber + "&type=2",
                requestHeader);
    }

    @Override
    public List<Cartoon> getCartoonList(String urlString)
    {
        Map<String, String> map = new HashMap<>();
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.37");
        map.put("Referer", urlString);
        map.put("Host", "m.qiman57.com");
        List<Cartoon> list = new ArrayList<>(100);
        for (int i = 0; i < 6; i++)
        {
            List<Cartoon> cartoonList = getCartoonListByJson(map, i);
            list.addAll(cartoonList);
        }
        return list;
    }
}
