
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */



def static "excel.ReadCellData"(
    	Object tcName	
     , 	Object colName	) {
    (new excel()).ReadCellData(
        	tcName
         , 	colName)
}


def static "excel.GetRowCount"(
    	Object fileName	) {
    (new excel()).GetRowCount(
        	fileName)
}


def static "service.constructJSON"(
    	Object strBirthday	
     , 	Object strGender	
     , 	Object strName	
     , 	Object strNatId	
     , 	Object strSalary	
     , 	Object strTax	) {
    (new service()).constructJSON(
        	strBirthday
         , 	strGender
         , 	strName
         , 	strNatId
         , 	strSalary
         , 	strTax)
}


def static "service.postData"(
    	Object jsonData	
     , 	Object objRepo	
     , 	Object statusCode	) {
    (new service()).postData(
        	jsonData
         , 	objRepo
         , 	statusCode)
}


def static "service.getData"(
    	Object objRepo	
     , 	Object statusCode	) {
    (new service()).getData(
        	objRepo
         , 	statusCode)
}


def static "service.maskedNatId"(
    	Object natId	) {
    (new service()).maskedNatId(
        	natId)
}


def static "service.calculateTaxRelief"(
    	Object birthday	
     , 	Object gender	
     , 	Object amtAfterTax	) {
    (new service()).calculateTaxRelief(
        	birthday
         , 	gender
         , 	amtAfterTax)
}
