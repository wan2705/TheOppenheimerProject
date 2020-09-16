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
 * This test case is to verify that the final tax relief amount is minimum 50.00
 */

birthday = ""
gender = ""
name = ""
natId = ""
salary = ""
tax = ""

commonRowName = "Verify Tax Relief Amount - Tax Relief Amount Min 50 - "


// Scenario 1 - verify the record with Tax relief amount less than 50
rowName = commonRowName + "less than 50"

InsertRecord()


// Scenario 2 - verify the record with Tax relief amount equals to 50
rowName = commonRowName + "equals 50"

InsertRecord()


// Scenario 3 - verify the record with Tax relief amount more than 50
rowName = commonRowName + "more than 50"

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




