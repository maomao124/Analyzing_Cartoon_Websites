package mao.service;

import mao.constant.URLConstant;
import mao.net.RestfulHTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Project name(项目名称)：解析漫画网站
 * Package(包名): mao.service
 * Class(类名): UpdateServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/10/15
 * Time(创建时间)： 12:51
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class UpdateServiceImpl implements UpdateService
{

    private final RestfulHTTP http;

    public UpdateServiceImpl(RestfulHTTP http)
    {
        this.http = http;
    }

    @Override
    public String getVersion()
    {
        String s = http.GET(URLConstant.updateUrl);
        if (s == null)
        {
            return null;
        }
        Document document = Jsoup.parse(s);
        Element element = document.getElementById("user-content-12345678901234");
        String version = element != null ? element.html() : null;
        return version;
    }

    @Override
    public List<String> getVersionInfo(String version)
    {
        String s = http.GET(URLConstant.updateUrl);
        if (s == null)
        {
            return null;
        }
        Document document = Jsoup.parse(s);
        Element element = document.getElementById("user-content-1234567890" + version);
        if (element == null)
        {
            return null;
        }
        Elements elements = element.getElementsByTag("p");
        List<String> list = new ArrayList<>();
        for (Element p : elements)
        {
            if (p != null)
            {
                list.add(p.html());
            }
        }
        return list;
    }
}
