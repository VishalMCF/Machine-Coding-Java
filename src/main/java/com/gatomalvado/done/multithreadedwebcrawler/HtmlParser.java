package com.gatomalvado.done.multithreadedwebcrawler;

import java.util.List;

public interface HtmlParser {

    List<String> parseHtml(String inputUrl);

}
