package com.djh.xpath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author David Hancock
 */
public class Application {

    public class Price {
        String buyRate;
        String sellRate;

        public Price(String buyRate, String sellRate) {
            this.buyRate = buyRate;
            this.sellRate = sellRate;
        }

        @Override
        public String toString() {
            return "Price{" +
                    "buyRate='" + buyRate + '\'' +
                    ", sellRate='" + sellRate + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        Application application = new Application();
        application.scrape();
    }

    private void scrape() {

        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get("https://www4.caixabank.es/apl/divisas/verTodos_es.html");

        List<WebElement> tableElements = webDriver.findElements(By.tagName("table"));

        WebElement under3000EurTable = tableElements.get(0);
        WebElement over3000EurTable = tableElements.get(1);

        Map<String, Price> under3000EurPrices = getPricesFromTableElement(under3000EurTable);
        Map<String, Price> over3000EurPrices = getPricesFromTableElement(over3000EurTable);

        System.out.println(under3000EurPrices);
        System.out.println(over3000EurPrices);
    }

    private Map<String, Price> getPricesFromTableElement(WebElement webElement) {

        Map<String, Price> prices = new HashMap<>();

        List<WebElement> currencyBuySellRows = webElement.findElements(By.xpath("./tbody/tr"));

        for (WebElement rowElement : currencyBuySellRows) {
            List<WebElement> dataElements = rowElement.findElements(By.tagName("td"));

            String currency = dataElements.get(0).getAttribute("textContent").substring(0, 3);
            String buyPrice = dataElements.get(1).getAttribute("textContent");
            String sellPrice = dataElements.get(2).getAttribute("textContent");

            Price price = new Price(buyPrice, sellPrice);
            prices.put(currency, price);
        }

        return prices;
    }

}
