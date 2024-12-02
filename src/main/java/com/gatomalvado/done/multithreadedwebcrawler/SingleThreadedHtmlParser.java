package com.gatomalvado.done.multithreadedwebcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SingleThreadedHtmlParser implements HtmlParser {

    @Override
    public List<String> parseHtml(String inputUrl) {
        String rawHtml = readUrl(inputUrl);
        return getUrlsFromWebsite(rawHtml);
    }

    private List<String> getUrlsFromWebsite(String rawHtml) {
        List<String> urls = new LinkedList<>();
        Document doc = Jsoup.parse(rawHtml);
        Elements elements = doc.select("a[href]");

        for(Element element : elements) {
            String link = element.attr("abs:href");
            if(!link.isEmpty()) {
                urls.add(link);
            }
        }

        return urls;
    }

    private String readUrl(String webLink) {
        String rawHtml = "";
        try {
            URL url = new URL(webLink);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                rawHtml += inputLine;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading url: " + webLink);
        }

        return rawHtml;
    }
}
