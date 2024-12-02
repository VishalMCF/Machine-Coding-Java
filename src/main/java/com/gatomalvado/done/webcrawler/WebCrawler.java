package com.gatomalvado.done.webcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.Getter;

public class WebCrawler {

    private Queue<String> queue;

    @Getter
    private Set<String> discoveredWebsites;

    private int websitesProcessed;

    public WebCrawler() {
        this.queue = new LinkedList<>();
        this.discoveredWebsites = new HashSet<>();
        this.websitesProcessed = 0;
    }

    public void crawl(String seedUrl) throws InterruptedException {
        this.queue.offer(seedUrl);
        Thread.sleep(3000);
        while (!this.queue.isEmpty()) {
            String currentUrl = this.queue.poll();
            String rawHtml = readUrl(currentUrl);
            if("".equals(rawHtml)) {
                continue;
            }

            List<String> urlsParsed = getUrlsFromWebsite(rawHtml);

            for (String websiteUrl : urlsParsed) {
                if(!discoveredWebsites.contains(websiteUrl)) {
                    // System.out.println("Website found with URL: " + websiteUrl);
                    queue.add(websiteUrl);
                }
            }

            this.discoveredWebsites.add(currentUrl);
            this.websitesProcessed++;

            if(this.websitesProcessed == 10000) {
                break;
            }
        }
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
