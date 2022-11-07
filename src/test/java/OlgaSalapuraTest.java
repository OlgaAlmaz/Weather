import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.net.UrlChecker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

public class OlgaSalapuraTest {
/*   /TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"
 */

    @Test
    public void testH2Tagtest_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(6000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);


        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropDownMenu = driver.findElement(
                By.xpath("//ul[@class =\"search-dropdown-menu\"]/li/span[text() = 'Paris, FR ']")
        );

        parisFRChoiceInDropDownMenu.click();

        WebElement h2CityCounterHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(5000);
        String actualresult = h2CityCounterHeader.getText();

        Assert.assertEquals(actualresult,expectedResult);

//        Thread.sleep(5000);
        driver.quit();

    }


    /*
    TC_11_01
1.  Открыть базовую ссылку
2.  Нажать на пункт меню Guide
3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void testQuideButtonShoesthePage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver/chromedriver");
        WebDriver driver= new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement quideClick = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")
        );
        quideClick.click();

      Thread.sleep(3000);

       String quideAPI = driver.getCurrentUrl();
       String quideTitle = driver.getTitle();

        Assert.assertEquals(quideAPI,expectedResult1);
        Assert.assertEquals(quideTitle,expectedResult2);

      driver.quit();

    }

    /*

TC_11_02
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph

3.  Подтвердить, что температура для города показана в Фарингейтах
     */


    @Test
    public void testTemperatureInCelsuisAndFahrenhait() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org";
//        String actualResult = "59°F";
    boolean expectedResult = true;

        driver.get(url);
        Thread.sleep(5000);
        WebElement checkTemperatureInFahrenhait = driver.findElement(
                By.xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']")
        );
        checkTemperatureInFahrenhait.click();

        Thread.sleep(5000);
        WebElement checkTheCityTemperatureInFahrenhi = driver.findElement(
                By.xpath("//div[@class='current-temp']//span[@class='heading']")
        );
        boolean actualResult=checkTheCityTemperatureInFahrenhi.getText().contains("F");

        Assert.assertEquals(actualResult,expectedResult);
    driver.quit();
    }

    /*
2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
 We also use non-essential cookies to help us improve our services.
 Any data collected is anonymised. You can allow all cookies or manage them individually.”
3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */


    @Test
    public void testCookieWorning() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String axpectedResultTxt="We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";

        String url = "https://openweathermap.org";
        String all = "Allow all";
        String manage ="Manage cookies";

        driver.get(url);
        Thread.sleep(5000);

        WebElement findLowerPanel = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__container']")
        );

        WebElement findLowerElementWithTxt = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//p[@class='stick-footer-panel__description']")
        );
        String actualResultTxt = findLowerElementWithTxt.getText();

        WebElement findButtonAllowAll = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__container']//button[@type='button']")
        );
        String actualResultAllow = findButtonAllowAll.getText();

        WebElement findButtonManageAll = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__container']//a[@href='/cookies-settings']")
        );

        String actualResultManageAll = findButtonManageAll.getText();



        Assert.assertEquals(actualResultTxt,axpectedResultTxt);
        Assert.assertEquals(actualResultAllow,all);
        Assert.assertEquals(actualResultManageAll,manage);

          driver.quit();
    }
/*

TC_11_04
1.  Открыть базовую ссылку
2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
 */

@Test
public void testButtonFaqHowToStartAskAQuestion() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver/chromedriver");
    WebDriver driver = new ChromeDriver();

    String url = "https://openweathermap.org";
    String faq1 = "FAQ";
    String howToStart = "How to start";
    String askQuestion = "Ask a question";

    driver.manage().window().maximize();

    driver.get(url);
    Thread.sleep(5000);

    WebElement checkSupportButton = driver.findElement(
            By.xpath("//div[@id='desktop-menu']//li[@class='with-dropdown']")
    );
    String actualResult = checkSupportButton.getText();
    checkSupportButton.click();

    WebElement checkDropDownMenu = driver.findElement(
            By.xpath("//div[@id='desktop-menu']//li/ul")
    );
//    checkDropDownMenu.click();

     WebElement checkFaqLink = driver.findElement(
          By.xpath("//*[@id=\"support-dropdown-menu\"]/li/a[text()='FAQ']")
     );
    String actualResult1 = checkFaqLink.getText();

    Thread.sleep(5000);

     WebElement checkHowToStart = driver.findElement(
          By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/appid']")
  );
     String actualResult2= checkHowToStart.getText();


  WebElement checkAskQuastion = driver.findElement(
          By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']")
  );
  String actualResult3 = checkAskQuastion.getText();


  Assert.assertEquals(actualResult1, faq1);
  Assert.assertEquals(actualResult2,howToStart);
  Assert.assertEquals(actualResult3,askQuestion);

    driver.quit();
}
/*

TC_11_05
1. Открыть базовую ссылку
2. Нажать пункт меню Support → Ask a question
3. Заполнить поля Email, Subject, Message
4. Не подтвердив CAPTCHA, нажать кнопку Submit
5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

 */

    @Test
    public void testCAPTCHA() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org";
        String email = "test1@test@gmail.com";
        String massage = "Help";
        String captcha = "reCAPTCHA verification failed, please try again.";


        driver.get(url);
        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement checkSupportButton = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        checkSupportButton.click();

        Thread.sleep(5000);
        WebElement checkAskQuastion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']")
        );
        checkAskQuastion.click();
        Thread.sleep(2000);

        for (String winHandle:
        driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebElement emailField = driver.findElement(
                By.id("question_form_email")
        );
        emailField.click();
        emailField.sendKeys(email);

        Thread.sleep(2000);

        WebElement subjectField = driver.findElement(
                By.id("question_form_subject")
        );
        subjectField.click();
        Thread.sleep(2000);

        WebElement chooseOptionFromDropDownMenu =driver.findElement(
                By.xpath("//option[@value='Sales']")
         );
        chooseOptionFromDropDownMenu.click();
        Thread.sleep(5000);

        WebElement messajeFiled = driver.findElement(
                By.id("question_form_message")
        );
        messajeFiled.click();
        messajeFiled.sendKeys(massage);

        Thread.sleep(5000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");

        WebElement submittButton = driver.findElement(
                By.xpath("//input[@class ='btn btn-default']")
        );
        submittButton.click();
        Thread.sleep(3000);

        WebElement captchatxt= driver.findElement(
                By.xpath("//div[@class='help-block']")
        );
        String actualResultCaptcha = captchatxt.getText();
        Assert.assertEquals(actualResultCaptcha,captcha);

        driver.quit();



    }


 /*
 TC_11_06
1.  Открыть базовую ссылку
2.  Нажать пункт меню Support → Ask a question
3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
4. Оставить пустым поле Email
5. Заполнить поля  Subject, Message
6. Подтвердить CAPTCHA
7. Нажать кнопку Submit
8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
  */
    @Ignore
    @Test
    public void testEmptyEmail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org";
        String massage = "blabla";
        String email = "can't be blank";


        driver.get(url);
        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement checkSupportButton = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        checkSupportButton.click();
        Thread.sleep(200);

        WebElement checkAskQuastion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']")
        );
        checkAskQuastion.click();
        Thread.sleep(2000);

        for (String winHandle:
                driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebElement subjectField = driver.findElement(
                By.id("question_form_subject")
        );
        subjectField.click();
        Thread.sleep(2000);

        WebElement chooseOptionFromDropDownMenu = driver.findElement(
                By.xpath("//option[@value='Sales']")
        );
        chooseOptionFromDropDownMenu.click();
        Thread.sleep(2000);

        WebElement messajeFiled = driver.findElement(
                By.id("question_form_message")
        );
        messajeFiled.click();
        messajeFiled.sendKeys(massage);

        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");


        WebElement findCaptcha = driver.findElement(
                By.xpath("//script[@src='https://www.google.com/recaptcha/api.js']")
        );
//        findCaptcha.click();
        Thread.sleep(3000);

//        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
//
//        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark")));



        WebElement chackCaptcha = driver.findElement(
                By.xpath("//span[@id='recaptcha-anchor']")
        );
        chackCaptcha.click();
        Thread.sleep(3000);

        WebElement submittButton = driver.findElement(
                By.xpath("//input[@class='btn btn-default']")
        );
        submittButton.click();
        Thread.sleep(3000);


        WebElement errorMessege = driver.findElement(
                By.cssSelector("can't be blank")
        );
//        String actualResult = errorMessege.getText();
//        Assert.assertEquals(actualResult,email);

        driver.quit();

    }

    /*
    TC_11_07
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph

3.  Нажать на единицы измерения Metric: °C, m/s
4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С

     */

    @Test
    public void testMetriImperial() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org";
        String imperial = "°F, mph";
        String metric = "°C, m/s";


        driver.get(url);
        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement metricSwitch = driver.findElement(
                By.xpath("//div[text()='Metric: °C, m/s']")
        );
        metricSwitch.click();
        String actualResultMetric = metricSwitch.getText();

        Thread.sleep(2000);
        WebElement imperialSwitch = driver.findElement(
                By.xpath("//div[text()='Imperial: °F, mph']")
        );
        imperialSwitch.click();
        String actualResulImperial = imperialSwitch.getText();
        Thread.sleep(2000);


        driver.quit();

    }
/*
TC_11_08
1.  Открыть базовую ссылку
2.  Нажать на лого компании

3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась

 */
  @Test
     public void testLogoOfTheCompany() throws InterruptedException {
      System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver/chromedriver");
      WebDriver driver = new ChromeDriver();

      String url = "https://openweathermap.org";


      driver.get(url);
      Thread.sleep(5000);
      driver.manage().window().maximize();

      WebElement fingLogo = driver.findElement(
              By.xpath("//li[@class='logo']/a[@href='/']")
      );
      fingLogo.click();
      Thread.sleep(3000);
      driver.getCurrentUrl();




       driver.quit();

  }
}








