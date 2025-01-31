package framework.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.appInit.Class_initEcomPrac;
import framework.pages.LandingPage_PartyLite;
import framework.utils.ExcelDataReader;

public class ConsultantSignIn_PartyLite extends Class_initEcomPrac{
	
	public ConsultantSignIn_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest(groups= {"Common"})
	public void setData() {
		tcName = "ConsultantSignIn";
		tcDescription = "Signing in of a Consultant";
		category = "Smokes";
		authors = "Anand";
		testNodes = "Consultant signing in";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Login_ConsultantUser_PL";
		
	}
	
	@Test(groups= {"PEX"}, dataProvider="ConsultantLoginDetails")
	public void consultantSignin(String consultant_uname, String consultant_password) {
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			
			//.clickToAllowCookie() //.closeDialogSignUpNewsLetter() 
			.clickToAllowCookie()
			.clickSignin()
			.enterEmailName(consultant_uname)
			.enterPassword(consultant_password)
			.ConClickSignin()
			//.closeSocialNewAcctDialog()
			.clickLogoutConsultant();
			} catch(WebDriverException e) {
				e.getMessage();
				reportStep("The test encountered an exception.","warning");
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}	
		
	}
	
	@DataProvider(name="ConsultantLoginDetails")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	

}
