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
 * This test case is to verify that record can/cannot be inserted with invalid/empty value of the fields
 */

rowName = ""

def commonRowName = "Insert Record with Invalid Value - "


// Scenario 1 - Insert record with empty value of "birthday"
rowName = commonRowName + "empty birthday"

InsertRecord(500)


// Scenario 2 - Insert record with invalid format of "birthday"
rowName = commonRowName + "invalid format of birthday"

InsertRecord(500)


// Scenario 3 - Insert record with empty value of "gender"
rowName = commonRowName + "empty gender"

InsertRecord(202)


// Scenario 4 - Insert record with empty value of "name"
rowName = commonRowName + "empty name"

InsertRecord(202)


// Scenario 5 - Insert record with empty value of "natid"
rowName = commonRowName + "empty natid"

InsertRecord(202)


// Scenario 6 - Insert record with empty value of "salary"
rowName = commonRowName + "empty salary"

InsertRecord(500)


// Scenario 7 - Insert record with invalid format of "salary"
rowName = commonRowName + "invalid format of salary"

InsertRecord(500)


// Scenario 8 - Insert record with empty value of "tax"
rowName = commonRowName + "empty tax"

InsertRecord(500)


// Scenario 9 - Insert record with invalid format of "tax"
rowName = commonRowName + "invalid format of tax"

InsertRecord(500)



def InsertRecord(def statusCode){
	
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
	CustomKeywords.'service.postData'(dataJson, objRepoPOSTInsert, statusCode)
	
}




