package org.example.dto;

public class EmpSDDto {

   private String deptCode ;
   private int idSeq;
   private int hirYear;

    public EmpSDDto() {
    }

    public EmpSDDto(String deptCode, int idSeq, int hirYear) {
        this.deptCode = deptCode;
        this.idSeq = idSeq;
        this.hirYear = hirYear;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public int getIdSeq() {
        return idSeq;
    }

    public void setIdSeq(int idSeq) {
        this.idSeq = idSeq;
    }

    public int getHirYear() {
        return hirYear;
    }

    public void setHirYear(int hirYear) {
        this.hirYear = hirYear;
    }

    @Override
    public String toString() {
        return   deptCode  + idSeq +
                hirYear ;
    }
}
