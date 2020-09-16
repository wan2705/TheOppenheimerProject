import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonBuilder as JsonBuilder
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent as HttpTextBodyContent

import internal.GlobalVariable
import java.time.*

public class service {

	@Keyword
	def constructJSON(def strBirthday, def strGender, def strName, def strNatId, def strSalary, def strTax){

		def builder = new JsonBuilder()

		def root = builder{

			birthday strBirthday
			gender strGender
			name strName
			natid strNatId
			salary strSalary
			tax strTax
		}

		return builder.toPrettyString()
	}


	@Keyword
	def postData(def jsonData, def objRepo, def statusCode){

		def dataPOSTObj = findTestObject('Object Repository/' + objRepo) as RequestObject
		dataPOSTObj.setBodyContent(new HttpTextBodyContent(jsonData))

		def ResponsePOSTData = WS.sendRequestAndVerify(dataPOSTObj)

		assert ResponsePOSTData.statusCode == statusCode

	}


	@Keyword
	def getData(def objRepo, def statusCode){

		def dataGETObj = findTestObject('Object Repository/' + objRepo) as RequestObject
		def ResponseGETData = WS.sendRequestAndVerify(dataGETObj)

		assert ResponseGETData.statusCode == statusCode

		ResponseGETData = new JsonSlurper().parseText(ResponseGETData.responseText)

		return ResponseGETData
	}


	@Keyword
	def maskedNatId(def natId){

		def length = natId.size()
		def maskedValue = (natId.substring(0, 4)).padRight(length,'$')

		return maskedValue
	}


	@Keyword
	def calculateTaxRelief(def birthday, def gender, def amtAfterTax){

		def numDecimal = 0

		//to obtain the variable by age
		def variable = calcVariable(birthday)

		//to obtain the genderBonus by gender
		def genderBonus = (gender == "M") ? 0 : 500

		//calculate tax relief amount
		def taxReliefAmt = (amtAfterTax * variable) + genderBonus

		//convert the data format into BigDecimal format
		BigDecimal BDtaxReliefAmt = new BigDecimal(taxReliefAmt)

		//truncate the amount at second decimal, if any
		BDtaxReliefAmt = BDtaxReliefAmt.setScale(2, BigDecimal.ROUND_DOWN)

		//round up/down the value and remove decimal places, if any
		BDtaxReliefAmt = BDtaxReliefAmt.setScale(0, BigDecimal.ROUND_HALF_UP)

		//if the rounded value is less than 50, set to 50.
		BDtaxReliefAmt = (BDtaxReliefAmt < 50) ? 50 : BDtaxReliefAmt

		return BDtaxReliefAmt

	}


	def calcVariable(def birthday){

		def variable = 0.0

		def day = birthday.substring(0,2).toInteger()
		def month = birthday.substring(2,4).toInteger()
		def year = birthday.substring(4).toInteger()

		LocalDate today = LocalDate.now()
		LocalDate dob = LocalDate.of(year,month,day)
		Period period = Period.between(dob, today)

		def age = period.years

		switch(age){

			case 0..18:
				variable = 1
				break

			case 19..35:
				variable = 0.8
				break

			case 36..50:
				variable = 0.5
				break

			case 51..75:
				variable = 0.367
				break

			default:
				variable = 0.05
				break
		}

		return variable
	}
}
