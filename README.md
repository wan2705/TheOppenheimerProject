# TheOppenheimerProject

Automation Tool - Katalon Studio (https://www.katalon.com/download/)


### Steps to execute Test Suite

1.    Launch the Katalon Studio, select File > Open Project menu, and select the project folder
2.    Open the test suite named 'TheOppenheimerProject' under the 'Test Suites' folder
3.    Click on the arrow down next to the 'Run' button at the toolbar, and select 'Chrome (headless)' option to run the test suite
4.    All the test cases will be executing in sequential order
5.    Execution log can be viewed under 'Log Viewer' or the executed test steps log under 'Console' tab after the execution is done


### Below are the test cases included in the Test Suite

##### 1.    GET Tax Relief Summary
      - To verify that both "totalWorkingClassHeroes" and "totalTaxReliefAmount" are shown as 0 before inserting any record.
      
##### 2.    Insert Single Record
      - Single record can be added via POST /calculator/insert API endpoint. 
      - Verify the record details via GET /calculator/taxRelief API endpoint after the insertion
      
##### 3.    Insert Duplicate Record
      - Duplicate record (same natid, name, gender, and birthday) can be added with no error returned. 
      - Verify the record details via GET /calculator/taxRelief API endpoint after the insertion.
      
##### 4.    Insert Multiple Records
      - Multiple records can be added via POST /calculator/insertMultiple API endpoint. 

##### 5.    GET Tax Relief Summary
      - To verify that both "totalWorkingClassHeroes" and "totalTaxReliefAmount" are shown correctly after the insertions.
      
##### 6.    Upload Tax Relief File
      - To verify that the data file can be uploaded to the web portal
      
##### 7.    Verify Total Number of Working Class Heroes After File Upload
      - As the test case name, to verify the total number of working class heroes after the file upload
      
##### 8.    Verify Tax Relief Amount - with conditions of Age and Gender
      - To verify that the Tax Relief Amount is calculated as per the formula, with the conditions of age and gender

##### 9.    Verify Tax Relief Amount - Tax Relief Amount Min 50
      - To verify that the calculated Tax Relief Amount is minimum 50
      
##### 10.   Cash Dispensing
      - To verify that the 'Dispense Now' button is red color, and with the text 'Dispense Now'
      - To verify that the page with 'Cash dispensed' text is navigated upon clicking the 'Dispense Now' button
      
##### 11.  Insert Record with Invalid Value
      - To verify that the record can/cannot be inserted with invalid/empty value of the fields

