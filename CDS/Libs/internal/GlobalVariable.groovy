package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object Hostname
     
    /**
     * <p></p>
     */
    public static Object Port
     
    /**
     * <p></p>
     */
    public static Object ExcelFileName
     
    /**
     * <p></p>
     */
    public static Object UploadFileName
     
    /**
     * <p></p>
     */
    public static Object objRepoPOSTInsert
     
    /**
     * <p></p>
     */
    public static Object objRepoPOSTInsertMultiple
     
    /**
     * <p></p>
     */
    public static Object objRepoGETTaxRelief
     
    /**
     * <p></p>
     */
    public static Object objRepoGETTaxReliefSummary
     
    /**
     * <p></p>
     */
    public static Object WebURL
     
    /**
     * <p></p>
     */
    public static Object TotalWorkingClassHeroes
     
    /**
     * <p></p>
     */
    public static Object TotalTaxRelieves
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
            Hostname = selectedVariables['Hostname']
            Port = selectedVariables['Port']
            ExcelFileName = selectedVariables['ExcelFileName']
            UploadFileName = selectedVariables['UploadFileName']
            objRepoPOSTInsert = selectedVariables['objRepoPOSTInsert']
            objRepoPOSTInsertMultiple = selectedVariables['objRepoPOSTInsertMultiple']
            objRepoGETTaxRelief = selectedVariables['objRepoGETTaxRelief']
            objRepoGETTaxReliefSummary = selectedVariables['objRepoGETTaxReliefSummary']
            WebURL = selectedVariables['WebURL']
            TotalWorkingClassHeroes = selectedVariables['TotalWorkingClassHeroes']
            TotalTaxRelieves = selectedVariables['TotalTaxRelieves']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
