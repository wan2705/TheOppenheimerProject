
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonOutput as JsonOutput
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.reader.ExcelFactory
import internal.GlobalVariable
import java.io.File

public class excel {


	/* Read from excel data file */
	/* Params : Test Case Name, Column Name */
	@Keyword
	private String ReadCellData(def tcName, def colName){


		def data = ExcelFactory.getExcelDataWithDefaultSheet(RunConfiguration.getProjectDir() + GlobalVariable.ExcelFileName, "testdata",true)

		println(RunConfiguration.getProjectDir() + GlobalVariable.ExcelFileName)

		String testcaseName = ''
		String cellData = ''

		for (def index : (1..data.getRowNumbers())) {

			testcaseName = data.getValue('Test Case Name', index)

			if (testcaseName == tcName){

				cellData = data.getValue(colName, index)
				break
			}
		}

		return cellData
	}

	@Keyword
	def GetRowCount(def fileName){

		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		String input
		int count = 0
		while((input = bufferedReader.readLine()) != null){
			count++;
		}

		return count-1
	}
}
