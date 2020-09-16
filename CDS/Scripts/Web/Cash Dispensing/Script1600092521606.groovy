import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable


/*
 * This test case is to verify that the page with text 'Cash dispensed' is navigated upon clicking of 'Dispense Now' button
 */




// open browser
WebUI.openBrowser(GlobalVariable.WebURL)

// verify that the page is loaded with the availability of the object
WebUI.verifyElementPresent(findTestObject('Object Repository/Web/Page_Technical Challenge for CDS/btn_DispenseNow'), 1000)

// verify the text on the button is 'Dispense Now'
WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Web/Page_Technical Challenge for CDS/btn_DispenseNow'), "text", "Dispense Now", 1000)

// verify the color of the button is red
def bgcolor = WebUI.getCSSValue(findTestObject('Object Repository/Web/Page_Technical Challenge for CDS/btn_DispenseNow'), 'background-color')

assert bgcolor == "rgba(220, 53, 69, 1)"


// click on the 'Dispense Now' button
WebUI.click(findTestObject('Object Repository/Web/Page_Technical Challenge for CDS/btn_DispenseNow'))

// verify that the page with the text 'Cash dispensed' is navigated
WebUI.verifyElementVisible(findTestObject('Object Repository/Web/Page_Dispense/lbl_CashDispensed'))

def strText = WebUI.getText(findTestObject('Object Repository/Web/Page_Dispense/lbl_CashDispensed'))

assert strText == "Cash dispensed"


// close browser
WebUI.closeBrowser()



