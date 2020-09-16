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
 * This test case is to verify that the tax relief amount is calculated correctly with the conditions of Age and gender
 */

birthday = ""
gender = ""
name = ""
natId = ""
salary = ""
tax = ""

commonRowName = "Verify Tax Relief Amount - "


// Scenario 1 - verify the record with age of 17 - gender F
rowName = commonRowName + "Age under 19 - gender F"

InsertRecord()


// Scenario 2 - verify the record with age of 18 - gender M
rowName = commonRowName + "Age under 19 - gender M"

InsertRecord()


// Scenario 3 - verify the record with age of 19 - gender F
rowName = commonRowName + "Age beween 19 and 35 - gender F"

InsertRecord()


// Scenario 4 - verify the record with age of 35 - gender M
rowName = commonRowName + "Age beween 19 and 35 - gender M"

InsertRecord()


// Scenario 5 - verify the record with age of 36 - gender F
rowName = commonRowName + "Age beween 36 and 50 - gender F"

InsertRecord()


// Scenario 6 - verify the record with age of 50 - gender M
rowName = commonRowName + "Age beween 36 and 50 - gender M"

InsertRecord()


// Scenario 7 - verify the record with age of 51 - gender F
rowName = commonRowName + "Age beween 51 and 75 - gender F"

InsertRecord()


// Scenario 8 - verify the record with age of 75 - gender M
rowName = commonRowName + "Age beween 51 and 75 - gender M"

InsertRecord()


// Scenario 9 - verify the record with age of 76 - gender F
rowName = commonRowName + "Age at least 76 - gender F"

InsertRecord()


// Scenario 10 - verify the record with age of 77 - gender M
rowName = commonRowName + "Age at least 76 - gender M"

InsertRecord()


def InsertRecord(){
	
	POSTrecord()
	RetrieveAndVerify()
	
}


def POSTrecord(){
	
	def objRepoPOSTInsert = GlobalVariable.objRepoPOSTInsert
	
	// retrieve the values from excel file
	birthday = CustomKeywords.'excel.ReadCellData'(rowName, "birthday")
	gender = CustomKeywords.'excel.ReadCellData'(rowName, "gender")
	name = CustomKeywords.'excel.ReadCellData'(rowName, "name")
	natId = CustomKeywords.'excel.ReadCellData'(rowName, "natid")
	salary = CustomKeywords.'excel.ReadCellData'(rowName, "salary")
	tax = CustomKeywords.'excel.ReadCellData'(rowName, "tax")
	
	
	// construct the JSON data model
	def dataJson = CustomKeywords.'service.constructJSON'(birthday, gender, name, natId, salary, tax)
	
	// POST new record, and verify the API status code
	CustomKeywords.'service.postData'(dataJson, objRepoPOSTInsert, 202)
	
}


def RetrieveAndVerify(){
	
	def objRepoGETTaxRelief = GlobalVariable.objRepoGETTaxRelief
	
	BigDecimal BDsalary = new BigDecimal(salary)
	BigDecimal BDtax = new BigDecimal(tax)
	
	// verify the newly added record through GET taxRelief endpoint
	def responseGET = CustomKeywords.'service.getData'(objRepoGETTaxRelief, 200)
	
	// assert for natid field
	def maskedNatId = CustomKeywords.'service.maskedNatId'(natId)
	
	assert responseGET.last().natid == maskedNatId
	
	
	// assert for name field
	assert responseGET.last().name == name
	
	
	// assert for relief field
	def amtAfterTax = BDsalary - BDtax
	
	def taxRelief = CustomKeywords.'service.calculateTaxRelief'(birthday, gender, amtAfterTax)
	
	assert responseGET.last().relief == String.format("%.2f", taxRelief)
	
}




