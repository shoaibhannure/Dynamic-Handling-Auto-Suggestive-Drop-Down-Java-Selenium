package com.shoaib.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleDynamicSearch {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(); // launch chrome
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.google.com/"); //launch URL

		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("Shoaib"); //locate auto suggestive search box
		
		Thread.sleep(3000);

		//Here we use find elements to find all the dynamic search results in the Auto Suggestive drop down 
		List<WebElement> list = driver
				.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbtc']"));
		System.out.println("Total No of Suggestion in the Search Box are===>  " + list.size());

		//descendant is used to find child & Grand Childs of li 
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());
			if (list.get(i).getText().equalsIgnoreCase("shoaib akhtar age")) {
				list.get(i).click();
				break; //break if the searching result is found; instead of iterating it for 'n' no of times
			}
		}

		System.out.println("Auto Suggestive Search performed successfully");
		
		String tileAftersearch = driver.getTitle();
		System.out.println("Title of the Page after Search is  " +tileAftersearch);
	}

}
