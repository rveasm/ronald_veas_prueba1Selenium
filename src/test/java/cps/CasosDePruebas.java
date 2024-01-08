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
    public void CP001_inicio_de_sesion() throws InterruptedException {
        By localizadorInicioSesion = By.xpath("//span[contains(text(),'Mi cuenta')]");
        WebElement btnMiCuenta = driver.findElement(localizadorInicioSesion);
        btnMiCuenta.click();
        Thread.sleep(1000);
        driver.findElement(By.id("rutId1_uno")).sendKeys("16847197-1");
        Thread.sleep(1500);
        driver.findElement(By.id("rutId2_uno")).sendKeys("123hjk");
        Thread.sleep(2000);
        driver.findElement(By.id("btn_login_uno")).click();
        WebElement mensaje = driver.findElement(By.xpath("//div[contains(text(),'RUT o contraseña incorrecta.')]"));

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
    }

    @Test
    public void CP003(){

    }

    @Test
    public void CP004(){

    }

    @Test
    public void  CP005(){
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        js.executeScript("window.scrollBy(0,4000)");
        driver.findElement(By.id("next2")).click();
        driver.findElement(By.xpath("//a[contains(text(),'MacBook Pro')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
        //Thread.sleep(2500);
        driver.switchTo().alert().accept();
        //Thread.sleep(1000);
        driver.findElement(By.id("cartur")).click();
        //Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
        //Thread.sleep(1000);
        driver.findElement(By.id("name")).sendKeys("Carlos");
        //Thread.sleep(1000);
        driver.findElement(By.id("country")).sendKeys("Chile");
        //Thread.sleep(1000);
        driver.findElement(By.id("city")).sendKeys("Santiago");
        //Thread.sleep(1000);
        driver.findElement(By.id("card")).sendKeys("5490561184212290");
        //Thread.sleep(1000);
        driver.findElement(By.id("month")).sendKeys("12");
        //Thread.sleep(1000);
        driver.findElement(By.id("year")).sendKeys("2032");
        //Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
        ////h2[contains(text(),'Thank you for your purchase!')]
    }
    /*
driver.get("https://www.pcfactory.cl/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//span[contains(text(),'Mi cuenta')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//a[contains(text(),'¿No tienes cuenta? Regístrate')]")).click();
        driver.findElement(By.id("id_rut_man_cliente")).sendKeys("26810643-k");
        driver.findElement(By.name("id_nombre_man_cliente")).sendKeys("Tomas Sanchez");
        driver.findElement(By.id("phone")).sendKeys("987578291");
        driver.findElement(By.id("id_email_man_cliente")).sendKeys("tsanchez@outlook.com");
        driver.findElement(By.id("autocomplete")).sendKeys("Las Lilas 931");
        driver.findElement(By.id("nombre_direccion")).sendKeys("Casa");
        driver.findElement(By.id("id_rut_man_cliente")).sendKeys("26810643-k");
        driver.findElement(By.id("id_rut_man_cliente")).sendKeys("26810643-k");
        driver.findElement(By.id("id_rut_man_cliente")).sendKeys("26810643-k");

        driver.findElement(By.id("id_rut_man_cliente")).sendKeys("26810643-k");
        driver.findElement(By.id("id_rut_man_cliente")).sendKeys("26810643-k");
        driver.findElement(By.id("id_rut_man_cliente")).sendKeys("26810643-k");
        driver.findElement(By.xpath("//button[contains(text(),'Guardar')]")).click();


        driver.get("https://www.correos.cl/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        js.executeScript("window.scrollBy(0,300)");
        driver.findElement(By.xpath("//body/div[@id='senna_surface1-default']/div[@id='wrapper']/section[@id='content']/div[@id='main-content']/div[1]/div[1]/div[1]/div[3]/section[1]/div[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/label[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("comuna_domicilio")).sendKeys("Santiago");
        driver.findElement(By.id("//body/div[@id='senna_surface1-default']/div[@id='wrapper']/section[@id='content']/div[@id='main-content']/div[1]/div[1]/div[1]/div[3]/section[1]/div[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]")).submit();
    Thread.sleep(5000);
        driver.findElement(By.id("js_btn_cookie")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("calle_domicilio")).sendKeys("Arturo Prat");
        Thread.sleep(1500);
        driver.findElement(By.id("numero_calle_domicilio")).sendKeys("300");
        Thread.sleep(4000);
        driver.findElement(By.id("btnCodigoPostal")).click();
     */
}
