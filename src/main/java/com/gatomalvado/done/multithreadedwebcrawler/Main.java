package com.gatomalvado.done.multithreadedwebcrawler;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello Multithreaded Web Crawler!");
        String inputUrl = "https://en.wikipedia.org/wiki/As_It_Was";

        MultithreadedWebCrawler crawler = new MultithreadedWebCrawler(new SingleThreadedHtmlParser(), 10);
        crawler.startCrawl(inputUrl);
        crawler.showParsedUrls();
    }



}
