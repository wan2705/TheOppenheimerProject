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
 * This test case is to verify that duplicate of employee record (with different amounts) is allowed
 */

totalCount = 0
totalAmount = 0.00

def rowName = "Insert Duplicate Record"
def objRepoPOSTInsert = GlobalVariable.objRepoPOSTInsert
def objRepoGETTaxRelief = GlobalVariable.objRepoGETTaxRelief


// retrieve the values from excel file
def birthday = CustomKeywords.'excel.ReadCellData'(rowName, "birthday")
def gender = CustomKeywords.'excel.ReadCellData'(rowName, "gender")
def name = CustomKeywords.'excel.ReadCellData'(rowName, "name")
def natId = CustomKeywords.'excel.ReadCellData'(rowName, "natid")
def salary = CustomKeywords.'excel.ReadCellData'(rowName, "salary")
def tax = CustomKeywords.'excel.ReadCellData'(rowName, "tax")

BigDecimal BDsalary = new BigDecimal(salary)
BigDecimal BDtax = new BigDecimal(tax)


// construct the JSON data model
def dataJson = CustomKeywords.'service.constructJSON'(birthday, gender, name, natId, salary, tax)

//POST duplicate record, and verify the API status code
CustomKeywords.'service.postData'(dataJson, objRepoPOSTInsert, 202)
	


// verify the newly added record through GET taxRelief endpoint
def responseGET = CustomKeywords.'service.getData'(objRepoGETTaxRelief, 200)

// assert for natid field
def maskedNatId = CustomKeywords.'service.maskedNatId'(natId)

assert responseGET.last().natid == maskedNatId


// assert for name field
assert responseGET.last().name == name


//assert for relief field
def amtAfterTax = BDsalary - BDtax

def taxRelief = CustomKeywords.'service.calculateTaxRelief'(birthday, gender, amtAfterTax)

assert responseGET.last().relief == String.format("%.2f", taxRelief)
	

// to store the value to global variable for the verification purpose in later test case
GlobalVariable.TotalWorkingClassHeroes += 1
GlobalVariable.TotalTaxRelieves += taxRelief


