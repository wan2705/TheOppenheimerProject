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
import com.kms.katalon.core.configuration.RunConfiguration


/*
 * This test case is to verify that the file can be uploaded
 */


def filePath = RunConfiguration.getProjectDir() + GlobalVariable.UploadFileName

println(filePath)

// to retrieve number of records in the data file
def noOfRecord = CustomKeywords.'excel.GetRowCount'(filePath)

// to store the count into the global variable for the verification purpose in later test case
GlobalVariable.TotalWorkingClassHeroes += noOfRecord

// open browser
WebUI.openBrowser(GlobalVariable.WebURL)

// verify that the page is loaded with the availability of the object
WebUI.verifyElementPresent(findTestObject('Object Repository/Web/Page_Technical Challenge for CDS/file_Upload'), 1000)

// upload file from the specific path
WebUI.uploadFile(findTestObject('Object Repository/Web/Page_Technical Challenge for CDS/file_Upload'), filePath)

WebUI.delay(1)

// close browser
WebUI.closeBrowser()







