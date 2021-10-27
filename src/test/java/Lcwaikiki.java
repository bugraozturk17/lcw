import java.util.concurrent.TimeUnit;
import Tests.LcwaikikiConstants;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class Lcwaikiki extends LcwaikikiConstants {

    @Test
    public void setupDriver() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Cayen\\Desktop\\lcw\\gecko\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        driver.get(link);

        //Ana sayfanın acildigi kontrol edilir.
        assertEquals(link, "https://www.lcwaikiki.com/tr-TR/TR");

        //Login sayfasina gidilir.
        driver.findElement(By.xpath(login)).click();



        //Siteye login olunur.
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id(EmailId)).sendKeys(EmailData);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id(PasswordId)).sendKeys(PasswordData);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id(LoginId)).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Login islemi kontrol edilir.
        Assert.assertEquals("LC Waikiki | İlk Alışverişte Kargo Bedava! - LC Waikiki", driver.getTitle());

        //Arama kutucuguna pantolon kelimesi girilir.
        driver.findElement(By.id(SearchInputId)).click();
        driver.findElement(By.id(SearchInputId)).sendKeys(SearchData);
        driver.findElement(By.cssSelector(SearchButtonSelector)).click();

        //scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,15000)", "");


        // daha fazla gör butonuna tıklanır
        driver.findElement(By.cssSelector(SeeMoreSelector)).click();



        //Urun secilir.
        driver.findElement(By.xpath(SelectProductXpath)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // size seçimi
        driver.findElement(By.xpath(SelectSizeXpath)).click();

        //Sepete atmadan onceki miktar.
        String productPrice = driver.findElement(By.cssSelector(ProductPrice)).getText();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        //Sepete urun eklenir.
        driver.findElement(By.id(AddToBasketId)).click();



        //Sepete gidilir.
        driver.findElement(By.xpath(MyBasket)).click();

        //Sepete attiktan sonraki miktar.
        String newPrice= driver.findElement(By.cssSelector(NewProductPrice)).getText();


        //Urun sayfasindaki fiyat ile sepette yer alan urun fiyatinin dogrulugu karsilastirilir.
        assertEquals(productPrice, newPrice);

        //Sepetteki urun sayisi arttirilir.
        driver.findElement(By.xpath(NumberOfProduct)).click();


        //Sepetteki urun silinir.
        WebElement deleteButton = driver.findElement(By.xpath(DeleteProduct));
        deleteButton.click();

        //pop-up sil
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.findElement(By.xpath(PopupDelete)).click();


        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //sepetinizde ürün bulunmamaktadır.
        //String deletecontrol = driver.findElement(By.cssSelector(DeleteControl)).getText();
        //assertEquals(deletecontrol, "Sepetinizde ürün bulunmamaktadır.");
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

       // driver.quit();
    }
}

