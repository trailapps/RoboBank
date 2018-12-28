# RoboBank
Application to load CSV/XML file from file system and generate report based on the validation rules

# Input
Input files CSV & XML are placed inside the code base package com.robobank.resources

# Output
Produces three CSV files as below,
  1. InvalidRecordsInCSVFormat.csv - Has invalid customer statements (transaction reference, description) from CSV feed
  2. InvalidRecordsInXMLFormat.csv - Has invalid customer statements  (transaction reference, description) from XML feed
  3. InvalidRecordsSummary.csv - Has summary of invalid customer statements  (transaction reference, description) from XML &        CSV feed
  
# Assumptions on Output validations
  Some of the validations are assumed as the output validation rule is too broad
  
1.all transaction references should be unique - Transaction repeated more than one times are printed in the report

2.the end balance needs to be validated   - Used below formula to check end balance 
                                            End balance = startBalance + mutation

 

