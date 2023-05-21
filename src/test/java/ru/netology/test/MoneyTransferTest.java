package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferFirstCardToSecond() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor();
        var dashboardPage = VerificationPage.validCode(verificationCode);
        var firstNumber = DataHelper.getFirstNumber();
        var secondNumber = DataHelper.getSecondNumber();
        int amount = 3000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstNumber) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondNumber) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        dashboardPage = transferPage.Transfer(String.valueOf(amount), firstNumber);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstNumber);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondNumber);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldTransferSecondCardToFirst() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor();
        var dashboardPage = VerificationPage.validCode(verificationCode);
        var firstNumber = DataHelper.getSecondNumber();
        var secondNumber = DataHelper.getFirstNumber();
        int amount = 4000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstNumber) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondNumber) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        dashboardPage = transferPage.Transfer(String.valueOf(amount), firstNumber);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstNumber);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondNumber);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
}