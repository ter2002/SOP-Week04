package com.example.week04;

import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class Cashier {
    private Change change = new Change();
    @RequestMapping(value="/getChange/{money}", method = RequestMethod.GET)
    public Change getChange(@PathVariable("money") int money){

        change.setB1000(money/1000);
        change.setB500((money-(change.getB1000()*1000))/500);
        change.setB100((money-(change.getB1000()*1000+change.getB500()*500))/100);
        change.setB20((money-(change.getB1000()*1000+change.getB500()*500+change.getB100()*100))/20);
        change.setB10((money-(change.getB1000()*1000+change.getB500()*500+change.getB100()*100+change.getB20()*20))/10);
        change.setB5((money-(change.getB1000()*1000+change.getB500()*500+change.getB100()*100+change.getB20()*20+change.getB10()*10))/5);
        change.setB1((money-(change.getB1000()*1000+change.getB500()*500+change.getB100()*100+change.getB20()*20+change.getB10()*10+change.getB5()*5))/1);
        return change;

    }

}
