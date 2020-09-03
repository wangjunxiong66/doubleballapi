package com.doubleball.controller;

//import org.apache.commons.beanutils.locale.converters.ByteLocaleConverter;
import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/7/31 下午5:36
 */
@RestController
@RequestMapping
public class OperateBallController {


    @RequestMapping(value = "/openbrowser",method = RequestMethod.GET)
    public void openBrowserWithFirxfoxAndChrome() {

        String geckodriverPath = System.getProperty("user.dir") + "/src/main/resources/geckodriver-v0.24.0-macos";

        //  指定firefox的安装路径
        System.setProperty("webdriver.gecko.driver", geckodriverPath);

        WebDriver driver = new FirefoxDriver();
        // 在火狐浏览器打开map.baidu.com
        driver.get("http://www.baidu.com");
        System.out.println("Go to url: " + driver.getCurrentUrl());
        driver.navigate().to("http://www.cnblogs.com");
        System.out.println("Navigate to url: " + driver.getCurrentUrl());
        driver.navigate().refresh();
        driver.navigate().back();
        System.out.println("Back to url:  " + driver.getCurrentUrl());
        driver.navigate().forward();
        System.out.println("Forward to url: " + driver.getCurrentUrl());
        driver.quit();

    }


//====  以下是cookie的操作  ====  以下是cookie的操作  ========  以下是cookie的操作  ========  以下是cookie的操作  ========  以下是cookie的操作  ====
//        String geckodriverPath = System.getProperty("user.dir")+"/src/main/resources/geckodriver-v0.24.0-macos";
//
//        //  指定firefox的安装路径
//        System.setProperty("webdriver.gecko.driver", geckodriverPath);
//
//        WebDriver driver = new FirefoxDriver();
//        // 在火狐浏览器打开map.baidu.com
//        driver.get("https://m.iqianjin.com/m/login/");
//        WebElement name = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[1]/input"));
//        WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[2]/input"));
//        WebElement submit = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/button"));
//        name.sendKeys("wjx0001");
//        password.sendKeys("123456a");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        submit.click();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
////        driver.close();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        File cookieFile = new File("/Users/finup/Downloads/zhihucookie.txt") ;
//        try{
//            System.out.println("开始写cookie");
//            cookieFile.delete() ;
//            cookieFile.createNewFile() ;
//            FileWriter fileWriter = new FileWriter(cookieFile) ;
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter) ;
//            for ( Cookie cookie : driver.manage().getCookies()){
//                bufferedWriter.write((cookie.getName() + ";" + cookie.getValue( ) + ";"
//                        + cookie.getDomain( ) + ";" + cookie.getPath( ) + ";"
//                        + cookie.getExpiry( ) + ";" + cookie.isSecure()));
//                bufferedWriter.newLine();
//            }
//            bufferedWriter.flush();
//            bufferedWriter.close();
//
//            System.out.println("cookie写完，请退出登录");
//        }catch (Exception ex){
//            ex. printStackTrace();
//        }
//
//        try {
//            System.out.println("开始退出登录");
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println("开始读cookie");
//            FileReader fr = new FileReader(cookieFile) ;
//            BufferedReader bufferedReader = new BufferedReader(fr) ;
//            String line;
//            while ((line = bufferedReader.readLine())!=null) {
//                StringTokenizer stringTokenizer =new StringTokenizer( line, ";");  // 构造一个用来解析 line 的 StringTokenizer 对象，并提供一个指定的分隔符。
//                while ( stringTokenizer.hasMoreTokens()) {         // 返回是否还有分隔符
//                    String name1 = stringTokenizer.nextToken();
//                    String value = stringTokenizer.nextToken();
//                    String domain= stringTokenizer.nextToken();
//                    String path = stringTokenizer.nextToken();
//                    Date expiry = null;
//                    String dt;
//                    if(!(dt = stringTokenizer.nextToken()).equals("null")) {
//                        expiry = new Date(dt) ;
//                    }
//                    boolean isSecure =
//                            new Boolean(stringTokenizer.nextToken()).booleanValue();
//                    Cookie cookie = new Cookie(name1,value,domain,path,expiry,isSecure) ;
//                    driver.manage().addCookie(cookie);
//                }
//            }
//            System.out.println("cookie读完");
//        }catch ( Exception ex){
//            ex.printStackTrace();
//        }
//        try {
//            System.out.println("开始停留5秒");
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("开始刷页面 https://m.iqianjin.com/m/user-center");
//        driver.get("https://m.iqianjin.com/m/user-center");
//        try {
//            System.out.println("开始停留10秒");
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//====  以上是cookie的操作  ====  以上是cookie的操作  ====  以上是cookie的操作  ====  以上是cookie的操作  ====  以上是cookie的操作  ====  以上是cookie的操作

}
