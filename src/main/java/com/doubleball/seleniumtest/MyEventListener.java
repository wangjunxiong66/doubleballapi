package com.doubleball.seleniumtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/9/28 下午4:08
 */
public class MyEventListener extends AbstractWebDriverEventListener {

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println( "After Navigate To" +url);
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        System.out.println("After Navigate BockTo" + driver.getCurrentUrl());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("After Click On" + element.getText());
    }



}
