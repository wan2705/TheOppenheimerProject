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

import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent as HttpTextBodyContent
import groovy.json.JsonOutput as JsonOutput
import groovy.json.JsonSlurper as JsonSlurper
import java.time.*


/* 
 * This test case is to verify that multiple records can be inserted at a time
 */

def birthday = ""
def gender = ""
def name = ""
def natId = ""
def salary = ""
def tax = ""
def dataJson = "["
def commonRowName = "Insert Multiple Records - Record "

boolean isEnded = false
def i = 1
def totalCount = 0
def totalRelieves = 0.00

def objRepoPOSTInsertMultiple = GlobalVariable.objRepoPOSTInsertMultiple


// retrieve the values from excel file
while (!isEnded){
	
	rowName = commonRowName + i
	
	birthday = CustomKeywords.'excel.ReadCellData'(rowName, "birthday")
	
	if (birthday != ""){
		
		gender = CustomKeywords.'excel.ReadCellData'(rowName, "gender")
		name = CustomKeywords.'excel.ReadCellData'(rowName, "name")
		natId = CustomKeywords.'excel.ReadCellData'(rowName, "natid")
		salary = CustomKeywords.'excel.ReadCellData'(rowName, "salary")
		tax = CustomKeywords.'excel.ReadCellData'(rowName, "tax")
		
		dataJson += CustomKeywords.'service.constructJSON'(birthday, gender, name, natId, salary, tax) + ","
		
		BigDecimal BDsalary = new BigDecimal(salary)
		BigDecimal BDtax = new BigDecimal(tax)
		
		def amtAfterTax = BDsalary - BDtax
		
		def taxRelief = CustomKeywords.'service.calculateTaxRelief'(birthday, gender, amtAfterTax)
		
		i++
		totalCount++
		totalRelieves += taxRelief
		
	}else{	
	
		isEnded = true
		dataJson = dataJson.substring(0, dataJson.size() - 1) + "]"
		
	}	
}


// POST new record, and verify the API status code
CustomKeywords.'service.postData'(dataJson, objRepoPOSTInsertMultiple, 202)


// to store the value to global variable for the verification purpose in later test case
GlobalVariable.TotalWorkingClassHeroes += totalCount
GlobalVariable.TotalTaxRelieves += totalRelieves

