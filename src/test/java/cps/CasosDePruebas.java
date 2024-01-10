package cps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CasosDePruebas {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeEach
    public void preCondiciones(){
        String rutaProyecto = System.getProperty("user.dir");

        String rutaDriver = rutaProyecto+"\\src\\test\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver",rutaDriver);

        driver = new ChromeDriver();

        js = (JavascriptExecutor) driver;

        driver.get("https://www.pcfactory.cl/");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    @AfterEach
    public void postCondicion(){
        driver.close();
    }

    @Test
    public void CP001_inicio_de_sesion_fallido() throws InterruptedException {
        By localizadorInicioSesion = By.xpath("//span[contains(text(),'Mi cuenta')]");
        WebElement btnMiCuenta = driver.findElement(localizadorInicioSesion);
        btnMiCuenta.click();
        Thread.sleep(1000);
        driver.findElement(By.id("rutId1_uno")).sendKeys("16847197-1");
        Thread.sleep(1500);
        driver.findElement(By.id("rutId2_uno")).sendKeys("123hjk");
        Thread.sleep(2000);
        driver.findElement(By.id("btn_login_uno")).click();
        String mensaje = driver.findElement(By.xpath("//div[contains(text(),'RUT o contraseña incorrecta.')]")).getText();

        Assertions.assertEquals("RUT o contraseña incorrecta.",mensaje);
    }

    @Test
    public void CP002_busqueda_wikipedia() throws InterruptedException {
        driver.get("https://www.wikipedia.org/");
        Thread.sleep(1000);
        driver.findElement(By.name("search")).sendKeys("Condorito");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//i[contains( text(),'Search')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.className("mw-file-element")).click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,1400)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[1]/tbody/tr[21]/td/a")).click();

        Assertions.assertNotEquals(driver.getCurrentUrl(),"https://www.condorito.cl");
    }

    @Test
    public void CP003() throws InterruptedException {
        driver.get("https://www.deezer.com/es/offers");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[@id='gdpr-btn-accept-all']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("topbar-login-button")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("login_mail")).sendKeys("tsanchez@outlook.com");
        Thread.sleep(1500);
        driver.findElement(By.id("login_password")).sendKeys("2DKx91w.o");
        Thread.sleep(3800);

        driver.findElement(By.id("login_form_submit")).click();

        WebElement mensaje = driver.findElement(By.id("//h1[contains(text(),'Una experiencia única, solo en Deezer')]"));
        //Select select = new Select(identidad);
        //select.selectByIndex(2)
    }

    @Test
    public void CP004_seguimiento_fallido() throws InterruptedException {
        driver.get("https://www.tricot.cl/");
        WebElement btnSeguimiento = driver.findElement(By.xpath("//div[contains(text(),'Seguimiento')]"));
        btnSeguimiento.click();

        driver.findElement(By.id("invoice")).sendKeys("8768928433");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[contains(text(),'Continuar con el proceso')]")).click();

    }

    @Test
    public void CP005_compra() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);
        driver.findElement(By.id("next2")).click();

        driver.findElement(By.xpath("//a[contains(text(),'MacBook Pro')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
        Thread.sleep(2500);
        driver.switchTo().alert().accept();
        //Thread.sleep(1000);
        driver.findElement(By.id("cartur")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
        //Thread.sleep(1000);
        driver.findElement(By.id("name")).sendKeys("Carlos Mendez");
        Thread.sleep(1000);
        driver.findElement(By.id("country")).sendKeys("Chile");
        Thread.sleep(1000);
        driver.findElement(By.id("city")).sendKeys("Santiago");
        Thread.sleep(1000);
        driver.findElement(By.id("card")).sendKeys("5490561184212290");
        Thread.sleep(1000);
        driver.findElement(By.id("month")).sendKeys("12");
        Thread.sleep(1000);
        driver.findElement(By.id("year")).sendKeys("2032");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
        Thread.sleep(10000);
        String mensajeCompra = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")).getText();
        Assertions.assertEquals("'Thank you for your purchase!",mensajeCompra);

    }
}
