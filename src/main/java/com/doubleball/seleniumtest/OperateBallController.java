package com.doubleball.seleniumtest;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDate.now;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.junit.Assert.assertEquals;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/7/31 下午5:36
 */
@RestController
@RequestMapping
public class OperateBallController {

    @RequestMapping(value = "/openbrowser",method = RequestMethod.GET)
    public void openBrowserWithFirxfoxAndChrome() throws Exception {

        String geckodriverPath = System.getProperty("user.dir") + "/src/main/resources/geckodriver-v0.24.0-macos";
        String pngPath = System.getProperty("user.dir") + "/src";

        //  指定firefox的安装路径
//        System.setProperty("webdriver.gecko.driver", geckodriverPath);

//        WebDriver driver = new FirefoxDriver();
//        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new RemoteWebDriver(new URL("http://www.baidu.com"),DesiredCapabilities.firefox());

        System.out.println("准备打开RemoteWebDriver");

        WebDriver driver;
//        URL url = new URL("http://localhost:4444/wd/hub");
//        FirefoxProfile fp = new FirefoxProfile();
//        DesiredCapabilities dc = DesiredCapabilities.firefox();
//        DesiredCapabilities dc = DesiredCapabilities.chrome();
//        dc.setCapability("marionette", true);
//        dc.setCapability(CapabilityType.PLATFORM_NAME,Platform.MAC);
//        dc.setCapability(FirefoxDriver.PROFILE, fp);
//        driver = new RemoteWebDriver(url, dc);

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),DesiredCapabilities.chrome());
        System.out.println("打开了RemoteWebDriver");
        try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        driver.get("http://www.baidu.com") ;
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(pngPath+"/remotewebdriver_screenshot.png"));
        driver.close();
        driver.quit();

//        driver.get("https://www.w3school.com.cn/i/movie.mp4") ;
//        WebElement video = driver.findElement(By.tagName("video"));
//        System.out.println("video 是 ： "+video);
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        String source = (String)jse.executeScript("return arguments[0].currentSrc;",video);
//        assertEquals("https://www.w3school.com.cn/i/movie.mp4",source);
//        System.out.println("source 是  ： "+source);
//        System.out.println("jse 是： "+jse);
//        for (int i=1;i<7;i++){
//            System.out.println("第 "+i+" 次");
//            System.out.println("开始播放");
//            jse.executeScript("arguments[0].play();", video);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("开始暂停");
//            jse.executeScript("arguments[0].pause();", video);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }



//        try {
//            System.out.println("进入");
////            Zip.zip(new File("/Users/finup/Documents/E/source_directory1/source_directory33"));
//            Zip.unzip(new FileInputStream(new File("/Users/finup/Documents/E/source_directory/source_directory33.zip")),new File("/Users/finup/Documents/E/source_directory1/"));
//            System.out.println("压缩完");
//        } catch ( IOException e){
//            e. printStackTrace( );
//        }

//        File tempDirectory = TemporaryFilesystem.getDefaultTmpFS().createTempDir("prefix", "suffix");
//        System.out.println( tempDirectory.getAbsolutePath());
//        System.out.println("Free Space of Temporary Directory is:" +tempDirectory.getFreeSpace());

//        if(!FileHandler.canExecute(new File("/directory/file1.sh"))){
//            try{
//                FileHandler.makeExecutable(new Fle("/directory/file1.sh"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            FileHandler.makeWritable(new File("/Users/finup/Documents/E/source_directory/file2.txt"));
//        } catch( IOException e){
//            e.printStackTrace();
//        }

    }

    private boolean isElementPresent(By by){
        try{
            WebDriver driver = new FirefoxDriver();
            driver.findElement(by);
            return true ;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    @RequestMapping(value = "/browser",method = RequestMethod.GET)
    public void testWebDriverEvent(){

        String geckodriverPath = System.getProperty("user.dir") + "/src/main/resources/geckodriver-v0.24.0-macos";
        //  指定firefox的安装路径
        System.setProperty("webdriver.gecko.driver", geckodriverPath);

        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 300) ;
        EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(driver);
        MyEventListener myEventlistener = new MyEventListener();
        eventFiringDriver.register( myEventlistener);
        eventFiringDriver.get("http://www.baidu.com");
        eventFiringDriver.get("http://www.hao123.com");
        eventFiringDriver.navigate().back();
        WebElement webElement = eventFiringDriver.findElement(By.id("kw"));
        webElement.sendKeys("selenium");
        eventFiringDriver.findElement( By.id("su")).click();
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
