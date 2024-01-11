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

        driver.findElement(By.id("rutId2_uno")).sendKeys("o#ku87hg9D");
        Thread.sleep(2000);
        driver.findElement(By.id("btn_login_uno")).click();
        String mensaje = driver.findElement(By.xpath("//*[@id='id_nota_forgot_password']/div[1]")).getText();

        Assertions.assertNotEquals("RUT o contraseña incorrecta.",mensaje);
    }

    @Test
    public void CP002_seguimiento_fallido() throws InterruptedException {
        WebElement btnSeguimiento = driver.findElement(By.xpath("//a[contains(text(),'Seguir mi pedido')]"));
        btnSeguimiento.click();

        driver.findElement(By.id("id_rut_despacho")).sendKeys("17581848-0");
        driver.findElement(By.id("id_boleta_despacho")).sendKeys("8768928433");
        Thread.sleep(3000);

        driver.findElement(By.id("id_buscar_documento")).click();
        String mensajeErrorDesp = driver.findElement(By.id("id_msg_error_despacho")).getText();

        Assertions.assertEquals("RUT o Numero de boleta incorrecto",mensajeErrorDesp);

    }

    @Test
    public void CP003_inicio_de_sesion() throws InterruptedException {
        By localizadorInicioSesion = By.xpath("//span[contains(text(),'Mi cuenta')]");
        WebElement btnMiCuenta = driver.findElement(localizadorInicioSesion);
        btnMiCuenta.click();
        Thread.sleep(1000);
        driver.findElement(By.id("rutId1_uno")).sendKeys("24499688-4");
        Thread.sleep(1500);
        driver.findElement(By.id("rutId2_uno")).sendKeys("o$ku87hg9D");
        Thread.sleep(2000);
        driver.findElement(By.id("btn_login_uno")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Hola Cesar Pililla!')]")).click();
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//a[contains(text(),'Edita tu perfil')]")).click();
        //driver.findElement(By.xpath("//a[@href='/misdatos']")).click();
        driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[5]/div[3]/div[1]/div[1]/div[3]/div[1]/a[1]")).click();

        Assertions.assertEquals("https://www.pcfactory.cl/misdatos",driver.getCurrentUrl());
    }

    @Test
    public void CP004_busqueda_wikipedia() throws InterruptedException {
        driver.get("https://www.wikipedia.org/");
        Thread.sleep(1000);
        By localizadorBusqueda = By.name("search");
        WebElement txtBuscar = driver.findElement(localizadorBusqueda);
        txtBuscar.sendKeys("Condorito");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//i[contains( text(),'Search')]")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,1400)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[1]/tbody/tr[21]/td/a")).click();

        Assertions.assertNotEquals(driver.getCurrentUrl(),"https://www.condorito.cl");
    }

    @Test
    public void CP005_compra_web() throws InterruptedException {
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
        String mensajeCompra = driver.findElement(By.xpath("/html/body/div[10]/h2")).getText();
        Assertions.assertEquals("Thank you for your purchase!",mensajeCompra);

    }
}