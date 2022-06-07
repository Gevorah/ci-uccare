package co.edu.icesi.dev.uccareapp.transport.model.formsSubmit;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class DateRange {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    public DateRange() {
    }

    public LocalDate getStart(){
        return start;
    }

    public LocalDate getEnd(){
        return end;
    }

    public void setStart (LocalDate start){
        this.start=start;
    }

    public void setEnd (LocalDate end){
        this.end=end;
    }
}