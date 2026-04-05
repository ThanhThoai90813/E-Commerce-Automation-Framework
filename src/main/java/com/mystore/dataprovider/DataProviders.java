package com.mystore.dataprovider;

import com.mystore.utility.NewExcelLibrary;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class DataProviders {

    NewExcelLibrary obj = new NewExcelLibrary();

    @DataProvider(name = "credentials")
    public Object[][] getDataByType(String sheetName, String typeFilter) {

        int rows = obj.getRowCount(sheetName);
        int column = obj.getColumnCount(sheetName);
        int actRows = rows - 1;

        List<Object[]> dataList = new ArrayList<>();

        for (int i = 0; i < actRows; i++) {

            String email = obj.getCellData(sheetName, 0, i + 2);
            String password = obj.getCellData(sheetName, 1, i + 2);
            String type = obj.getCellData(sheetName, 2, i + 2);

            if (type.equalsIgnoreCase(typeFilter)) {
                dataList.add(new Object[]{email, password});
            }
        }

        return dataList.toArray(new Object[0][]);
    }

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return getDataByType("Credentials", "valid");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return getDataByType("Credentials", "invalid");
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

    @DataProvider(name = "signupFailedData")
    public Object[][] getSignupFailedData() {
        int rows = obj.getRowCount("AccountCreationData");

        Object[][] data = new Object[rows - 1][2]; // chỉ 2 cột

        for (int i = 0; i < rows - 1; i++) {
            data[i][0] = obj.getCellData("AccountCreationData", "Name", i + 2);
            data[i][1] = obj.getCellData("AccountCreationData", "Email", i + 2);
        }
        return data;
    }

}
