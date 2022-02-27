package com.emiteai.willian.utils;

import com.emiteai.willian.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class ProductExelExport {
    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private List<Product> productList;

    public ProductExelExport(List<Product> productList){
        this.productList = productList;
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("Products");

    }

    private void writeHeaderRows() {
        Row row = this.sheet.createRow(0);

        Cell cell= row.createCell(0);
        cell.setCellValue("Product Id");

        cell = row.createCell(1);
        cell.setCellValue("Product Name");

        cell = row.createCell(2);
        cell.setCellValue("Product price");


    }
    private void writeDateRows(){
        int countRow = 1;

        for (Product product: this.productList) {
            Row row = this.sheet.createRow(countRow);

            Cell cell= row.createCell(0);
            cell.setCellValue(product.getId());

            cell = row.createCell(1);
            cell.setCellValue(product.getName());

            cell = row.createCell(2);
            cell.setCellValue(product.getPrice());

            countRow += 1;
        }

    }

    public void export(HttpServletResponse response) throws IOException {
        this.writeHeaderRows();
        this.writeDateRows();

        ServletOutputStream outputStream=  response.getOutputStream();

        this.workbook.write(outputStream);
        this.workbook.close();
        outputStream.close();
    }
}
