package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class JobsFileDto
{

   private @QueryParam("minSalary")Double minSalary ;
    private  @QueryParam("limit")Integer limit;
        private @QueryParam("offset")int offset;

    public Double getMinSalary() {
        return minSalary;
    }


    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

