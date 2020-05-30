package com.selenium.app;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        RabbitMQSender sender = new RabbitMQSender();
        while(true) {
            try {
                driver.get("https://techcrunch.com");
                WebElement firstResult = driver.findElement(By.className("river"));
                List<WebElement> articleElements = firstResult.findElement(By.tagName("div")).findElements(By.tagName("article"));
                List<Article> articles = new ArrayList<>();
                articleElements.forEach(article -> articles.add(Article.builder()
                        .title(article.findElement(By.tagName("header"))
                                .findElement(By.tagName("h2"))
                                .findElement(By.tagName("a")).getText())
                        .author(article.findElement(By.tagName("header"))
                                .findElement(By.className("post-block__meta"))
                                .findElement(By.tagName("div"))
                                .findElement(By.tagName("span"))
                                .findElement(By.tagName("span"))
                                .findElement(By.tagName("a")).getText())
                        .description(article.findElement(By.className("post-block__content"))
                                .findElement(By.tagName("p")).getText())
                        .link(article.findElement(By.tagName("header"))
                                .findElement(By.tagName("h2"))
                                .findElement(By.tagName("a")).getAttribute("href"))
                        .date(article.findElement(By.tagName("header"))
                                .findElement(By.className("post-block__meta"))
                                .findElement(By.tagName("div"))
                                .findElement(By.tagName("div"))
                                .findElement(By.tagName("time"))
                                .getAttribute("datetime"))
                        .build()));
                articles.forEach(article -> sender.addMessageToQueue(article.toString()));
                articles.forEach(System.out::println);
            } finally {
                driver.quit();
                Thread.sleep(3600000); // 1 hour
            }
        }
    }
}
