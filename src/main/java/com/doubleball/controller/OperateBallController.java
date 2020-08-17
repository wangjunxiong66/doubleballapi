package com.doubleball.controller;

//import org.apache.commons.beanutils.locale.converters.ByteLocaleConverter;
//import org.joda.time.DateTime;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/7/31 下午5:36
 */
@Controller
@RequestMapping("/api/doubleball")
public class OperateBallController {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/initball")
    public void initBall() {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());

    }

//    @RequestMapping(value = "/openbrowser",method = RequestMethod.GET)
//    public void openBrowserWithFirxfoxAndChrome(){
//
//        String geckodriverPath = System.getProperty("user.dir")+"/src/main/resources/geckodriver-v0.24.0-macos";
//
//        //  指定firefox的安装路径
//        System.setProperty("webdriver.gecko.driver", geckodriverPath);
//
//        WebDriver driver = new FirefoxDriver();
//        // 在火狐浏览器打开map.baidu.com
//        driver.get("https://www.baidu.com");
//        driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
//        String getWindowHandle = driver.getWindowHandle();
//        System.out.println("getWindowHandle  is   "+getWindowHandle);
//        System.out.println("getPagesource  is   "+driver.getPageSource());
////        driver.navigate().to("http://www.hao123.com");
////        url = driver.getCurrentUrl();
////        System.out.println(url);
////        driver.navigate().to("http://www.baidu.com");
////        url = driver.getCurrentUrl();
////        System.out.println(url);
//
////        WebElement searchBox = driver.findElement(By.id("kw"));
////        searchBox.sendKeys(Keys.chord(Keys.SHIFT+"abcde"));
////        try {
////            Thread.sleep(5000);
////            System.out.println("等待5秒，准备提交=======");
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        WebElement sbumitBox = driver.findElement(By.id("su"));
////        System.out.println("Value of the button is:"+sbumitBox.getAttribute("value"));
////        System.out.println("text of the button is:"+sbumitBox.getText());
////        System.out.println("tag name of the button is:"+sbumitBox.getTagName());
////        System.out.println("CSS height of the button is:"+sbumitBox.getCssValue("height"));
////        System.out.println("CSS of the button is:"+sbumitBox.getClass());
////        System.out.println("location of the button is:"+sbumitBox.getLocation());
////        System.out.println("size of the button is:"+sbumitBox.getSize());
////
////        sbumitBox.submit();
////        try {
////            Thread.sleep(5000);
////            System.out.println("点击完毕=======");
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        driver.quit();
//        driver.close();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    @RequestMapping("/index")
    public String index(){
        return "template/index";
    }


}