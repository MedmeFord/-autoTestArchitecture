package com.example.project.ui.pages.mainPage;

import com.example.project.ui.pages.BasePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class MainPage extends BasePage {
    @Test
    // # TODO реализовать подтягивание url с .env файла
    void test() {
        open("https://people.sovcombank.ru/");
    }
}
