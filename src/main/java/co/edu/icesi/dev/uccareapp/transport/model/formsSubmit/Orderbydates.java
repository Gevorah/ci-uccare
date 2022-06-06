package co.edu.icesi.dev.uccareapp.transport.model.formsSubmit;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Orderbydates {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date2;

    public Orderbydates(){}

    public LocalDate getDate1(){
        return date1;
    }

    public LocalDate getDate2(){
        return date2;
    }

    public void setDate1 (LocalDate date1){
        this.date1=date1;
    }

    public void setDate2 (LocalDate date2){
        this.date2=date2;
    }


}
