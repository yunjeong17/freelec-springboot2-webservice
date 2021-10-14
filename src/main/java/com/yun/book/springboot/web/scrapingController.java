package com.yun.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class scrapingController {
    //스크래핑 연습 ㅎㅎ
    @GetMapping("/textScraping")
    public String scraping() throws IOException {
        String naverComicURL = "https://comic.naver.com/webtoon/weekday";
        Document doc = Jsoup.connect(naverComicURL).get();
        Elements elem = doc.select(".title");
        System.out.println("!!!!!!!!!!!!!!!!!!"+elem.text());
        return elem.text();
    }
}
