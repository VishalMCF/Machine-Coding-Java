package com.gatomalvado.done.webcrawler;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello Simple Webcrawler!");
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.crawl("https://en.wikipedia.org/wiki/As_It_Was");
        System.out.println(webCrawler.getDiscoveredWebsites());
    }

}
