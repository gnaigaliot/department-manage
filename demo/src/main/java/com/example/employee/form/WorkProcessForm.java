package com.example.employee.form;

import java.util.Date;

public class WorkProcessForm {
    private int id_department;
    private int id_positions;
    private int id_employee;
    private Date start_date;
    private Date end_date;

    public WorkProcessForm(int id_department, int id_positions, int id_employee, Date start_date, Date end_date) {
        this.id_department = id_department;
        this.id_positions = id_positions;
        this.id_employee = id_employee;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public int getId_positions() {
        return id_positions;
    }

    public void setId_positions(int id_positions) {
        this.id_positions = id_positions;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
