package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private final ElementsCollection cards = $$(".list__item");


    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = cards.findBy(text(cardInfo.getCardNumber().substring(12, 16))).getText();
        return extractBalance(text);
    }

    public ru.netology.page.TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(text(cardInfo.getCardNumber().substring(12, 16))).$("button").click();
        return new ru.netology.page.TransferPage();
    }

    public int extractBalance(String text) {
        String balanceStart = "баланс: ";
        var start = text.indexOf(balanceStart);
        String balanceFinish = " р.";
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}