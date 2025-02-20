package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {

    HashSet<String> urlSet;
    int maxDept = 2;
    Crawler(){
        urlSet = new HashSet<String>();

    }
    public void getPageTextsAndLinks(String url, int dept) throws IOException {

        if(urlSet.contains(url)){
            return;
        }
        if(dept > maxDept){
            return;
        }
        dept++;
        try{
        Document document = Jsoup.connect(url).timeout(5000).get();
        //Indexer work starts here
        System.out.println(document.title());
        Elements availableLinksOnPage = document.select("a[href]");
        for(Element currentLink: availableLinksOnPage){
            getPageTextsAndLinks(currentLink.attr("abs:href"), dept);
        }
    }
        catch (IOException ioException){
            System.out.println("Could not fetch URL");
        }
    }
    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler();
        crawler.getPageTextsAndLinks("https://www.chaturbate.com/", 1);
    }
}