package applicaton.android.com.sehonmin.Model.service;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import applicaton.android.com.sehonmin.Model.dao.StorageDAO;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by ken13 on 2017-12-12.
 */

public class ExcelHelper {

    String filePath= Environment.getExternalStoragePublicDirectory("Music")+"/sample.xls";
    File file;
    WritableWorkbook wb=null;
    WritableSheet sh=null;
    private String days;
    private String formName;
    private String comment;
    private String groupID;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    private WritableCellFormat textFormat;
    private int row=0;
    private int col=0;
    public void createSheet(){
        file=new File(filePath);
        try {
            wb= Workbook.createWorkbook(file);
            sh=wb.createSheet("hello",1);
            textFormat = new WritableCellFormat();
            textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }

    public void sendStorage(){
        try {
            wb.write();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        StorageDAO.getInstance().sendData(filePath,formName);
    }
    public void addCell(String[] array){
        try {
            Log.i("sesese",String.valueOf(array.length)+": "+array[0]+array[1]+array[2]);
            for (int i = 0; i < array.length; i++) {
                Label addLabel = new jxl.write.Label(i, row, array[i], textFormat);
                sh.addCell(addLabel);
            }
            row++;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init(String days, String formName,String groupID, String comment){
        this.days=days;
        this.formName=formName;
        this.groupID=groupID;
        this.comment=comment;
        String[] formarray={"조사 이름","기간","그룹 ID","comment"};
        String[] formarray2={" "," "," "," "};
        String[] array={formName,days,groupID,comment};
        String[] field={"name","참여 여부","학번"};
        try {
            addCell(formarray);
            addCell(formarray2);
            addCell(array);
            addCell(field);
        }catch(Exception e){
            e.printStackTrace();

        }
    }
}
