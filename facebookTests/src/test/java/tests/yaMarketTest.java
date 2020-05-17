package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.byName;

@Epic("QA.GURU home worx")
@Story("yandex tests")
@Tag("yandex")
class yandexTest extends TestBase {

    @Test
    @Description("Positive test searching in ya.ru for yandex market and opening page")
    void yaSuccessfulSearch() {
        Configuration.browser = "opera";
        open("http://ya.ru");
        // entering Яндекс маркет text to search field
        $( byName("text")).val("Яндекс маркет").pressEnter();
        // if contains then 1st part of test is successfull
        $("html").shouldHave(text("market.yandex.ru"));
        //how to select link to yandex market?
        // UL with id="search result"
            // LI class "serp-item" DIV H2 DIV
                // <a class="link link_theme_normal organic__url link_cropped_no i-bem link_js_inited" data-bem="{&quot;link&quot;:{}}" rel="noopener" accesskey="1" data-log-node="gpyi6e" target="_blank" tabindex="0" href="https://market.yandex.ru/?clid=703" data-counter="[&quot;b&quot;]"><div class="favicon favicon_page_0"><div class="favicon__icon" style="background-position:0 -16px;"></div></div><div class="organic__url-text" id="uniq15895697650111250888"><b class="needsclick">Яндекс</b>.<b class="needsclick">Маркет</b> — сервис для сравнения и выбора товаров из проверенных интернет-магазинов</div></a>
        // how to select by a href that contains "market.yandex.ru"?
        $$(By.tagName("a")).findBy(text("market.yandex.ru")).click();
        //<title>Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов</title>
        switchTo().window(1);
        System.out.println("Should be switched to new tab");
        $("title").shouldHave(attribute("text", "Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов"));
    }


    @Test
    @Description("Positive test Yandex market search for Alienware 15 R3")
    void yaMarketSuccessfullSearch() {
        Configuration.browser = "opera";
        open("http://market.yandex.ru");
        // entering Яндекс маркет text to search field
        $("#header-search").val("Alienware 15 R3").pressEnter();
        // if contains then 1st part of test is successfull
        $("h1").shouldHave(attribute("title", "Ноутбуки"));
//      $("h3").$("a").$(withText("Alienware 15 R3"), 0).click();
        // вот это шикарный способ -- проваливаться до последнего потомка
        $(byClassName("n-snippet-card2__title")).lastChild().click();

        System.out.println("Should be switched to new tab");
        switchTo().window(1);
        $("h1").shouldHave(text("Alienware 15 R3"));
    }

}