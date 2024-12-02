package com.gatomalvado.done.multithreadedwebcrawler;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MultithreadedWebCrawler  {

    private final HtmlParser htmlParser;
    private final Map<String, Boolean> map;
    private final ExecutorService executorService;
    private final int limit;

    public MultithreadedWebCrawler(HtmlParser htmlParser, int limit) {
        this.htmlParser = htmlParser;
        this.map = new ConcurrentHashMap<>();
        this.limit = limit;
        this.executorService = Executors.newFixedThreadPool(12);
    }

    public void startCrawl(String inputUrl) throws ExecutionException, InterruptedException {
        Future<List<String>> extractedUrls = executorService.submit(crawl(inputUrl));
        Deque<Future<List<String>>> queue = new ArrayDeque<>();
        queue.add(extractedUrls);
        while (!queue.isEmpty()) {
            if(map.size() >= limit) {
                break;
            }
            Thread.sleep(3000);

            Future<List<String>> extractedUrlsFuture = queue.removeFirst();
            List<String> parsedUrls = extractedUrlsFuture.get();
            for(String parsedUrl : parsedUrls) {
                if (!map.containsKey(parsedUrl)) {
                    Callable<List<String>> callable = crawl(parsedUrl);
                    queue.add(executorService.submit(callable));
                }
            }
        }
        executorService.shutdown();
        // Wait for existing tasks to complete
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    public void showParsedUrls() {
        for(String key : map.keySet()) {
            System.out.println(key);
        }
    }

    private Callable<List<String>> crawl(String url) {
        return () -> {
            if(!map.containsKey(url)) {
                List<String> parsedUrls = htmlParser.parseHtml(url);
                map.put(url, true);
                return parsedUrls.stream().filter((u) -> !map.containsKey(u)).collect(Collectors.toUnmodifiableList());
            }
            return Collections.emptyList();
        };
    }

}