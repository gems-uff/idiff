package idiff.experiments;

/**
 *
 * @author Fernanda Floriano
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XLSFile {

    private String fileName;
    private String dirName;
    private java.io.File file;
    private FileOutputStream fileOut;
    private InputStream s;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet[] = new HSSFSheet[100];
    private HSSFRow row;
    private boolean exist;
    private int currentSheet = 0;
    private int currentLine = -1;
    private int currentColumn = 0;
    private int MAX_ROWS = 65535;

    public XLSFile(String fileName, String dirName) {
        this.fileName = fileName;
        this.dirName = dirName;

        file = new java.io.File(dirName + fileName + ".xls");

        exist = file.exists();

        if (exist) {
            readFile();
        } else {
            createFile();
        }

    }

    private void createFile() {
        try {
            fileOut = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XLSFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        workbook = new HSSFWorkbook();
    }

    public void createSheet(String sheetName) {
        this.currentLine = -1;
        this.currentColumn = 0;

        if ((sheetName != null && sheet[currentSheet] != null) && (sheetExist(sheetName))) {
            workbook.removeSheetAt(workbook.getSheetIndex(sheetName));
        }
        sheet[currentSheet] = workbook.createSheet(sheetName);
    }

    private boolean sheetExist(String sheetName) {
        return (workbook.getSheet(sheetName) != null);
    }

    private void readFile() {
        try {
            s = new FileInputStream(file);
            workbook = new HSSFWorkbook(s);
            fileOut = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XLSFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XLSFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentSheet = workbook.getNumberOfSheets() - 1;
        sheet[currentSheet] = workbook.getSheetAt(currentSheet);
        currentLine = sheet[currentSheet].getLastRowNum();
    }

    public HSSFCell getNewCell() {
        return row.createCell(currentColumn++);
    }

    public void createNextRow() {
        currentColumn = 0;

        if (++currentLine >= MAX_ROWS) {
            currentSheet++;
            currentLine = 0;
            sheet[currentSheet] = workbook.createSheet(this.fileName + "_" + currentSheet);
        }

        row = sheet[currentSheet].createRow(currentLine);
    }

    public void close() {
        try {
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException ex) {
            Logger.getLogger(XLSFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isExist() {
        return exist;
    }

    public void insertCell(XLSFile xls, Object[] contents) {
        xls.createNextRow();
        HSSFCell cell = xls.getNewCell();
        for (int i = 0; i < contents.length; i++) {
            cell.setCellValue(contents[i].toString());
            cell = xls.getNewCell();
        }
    }

    public void createLineTitle(XLSFile xls, String title) {
        xls.createNextRow();
        HSSFCell cell = xls.getNewCell();
        cell.setCellValue(title);
        xls.createNextRow();
    }
}