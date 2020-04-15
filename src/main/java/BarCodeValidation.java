
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class BarCodeValidation {
    WebDriver driver;
    @Test
    public void TestBarcode() throws IOException, NotFoundException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/mali4/Documents/gitRepo/CMP-Automation/cmp-legacy/src/test/resources/webdrivers/chromedriver/MacChromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.scandit.com/barcode-generator/");

        //Select code type
        driver.findElement(By.xpath("//label[text()='Code 128']")).click();
        //enter text
        driver.findElement(By.name("value")).clear();
        Thread.sleep(2000);
        driver.findElement(By.name("value")).sendKeys("Temp Here");
        Thread.sleep(5000);
        // refresh image
        driver.findElement((By.id("create_btn"))).click();
        //wait for some time
        Thread.sleep(5000);

        //get barcode source url
        String barCodeURL = driver.findElement(By.xpath("//div[@id = 'result']/img")).getAttribute("src");
       
        System.out.println(barCodeURL);

        //convert url to image
        URL url = new URL(barCodeURL);
        BufferedImage bufferedImage = ImageIO.read(url);

        // zxing class to conver image to text
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

        //decoding image
        Result result = new MultiFormatReader().decode(binaryBitmap);
        System.out.println(result.getText());
        Assert.assertEquals();
    }
    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
