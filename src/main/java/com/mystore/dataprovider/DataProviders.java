package com.mystore.dataprovider;

import com.mystore.utility.NewExcelLibrary;
import org.testng.annotations.DataProvider;

public class DataProviders {

    NewExcelLibrary obj = new NewExcelLibrary();

    @DataProvider(name = "credentials")
    public Object[][] getCredentials() {
        //Totals rows count
        int rows = obj.getRowCount("Credentials");
        //Totals Columns
        int column = obj.getColumnCount("Credentials");
        int actRows = rows-1;

        Object[][] data = new Object[actRows][column];

        for (int i=0; i<actRows; i++) {
            for (int j=0; j<column; j++) {
                data[i][j] = obj.getCellData("Credentials", j, i+2);
            }
        }
        return data;
    }

    @DataProvider(name = "accountCreationData")
    public Object[][] getAccountCreationData() {
        //Totals rows count
        int rows = obj.getRowCount("AccountCreationData");
        //Totals Columns
        int column = obj.getColumnCount("AccountCreationData");
        int actRows = rows-1;

        Object[][] data = new Object[actRows][column];

        for (int i=0; i<actRows; i++) {
            for (int j=0; j<column; j++) {
                data[i][j] = obj.getCellData("AccountCreationData", j, i+2);
            }
        }
        return data;
    }

}
