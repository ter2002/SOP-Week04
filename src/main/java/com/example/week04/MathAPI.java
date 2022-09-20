package com.example.week04;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@Route(value = "index")
public class MathAPI extends FormLayout {
    private TextField n1, n2, result;


    private double  one,two;
    private Button btnPlus, btnMinus, btnMultiply, btnDivide, btnMod, btnMax;
    private Span operator;

    public MathAPI(double n, double t){
        this.one = n;
        this.two = t;
    }
    public double getOne() {
        return one;
    }

    public void setOne(double one) {
        this.one = one;
    }

    public double getTwo() {
        return two;
    }

    public void setTwo(double two) {
        this.two = two;
    }
    public MathAPI(){
        this.setResponsiveSteps(new ResponsiveStep("0",7));
        n1 = new TextField("Number 1");
        n2 = new TextField("Number 2");
        result = new TextField("Answer");
        result.setReadOnly(true);
        operator = new Span("Operator");


        btnPlus = new Button("+");
        btnMinus = new Button("-");
        btnMultiply = new Button("x");
        btnDivide = new Button("/");
        btnMod = new Button("Mod");
        btnMax = new Button("Max");


        add(n1,n2,operator,btnPlus,btnMinus,btnMultiply,btnDivide,btnMod,btnMax,result);
        this.setColspan(n1,7);
        this.setColspan(n2,7);
        this.setColspan(operator,7);
        this.setColspan(result,7);


        btnPlus.addClickListener(event->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/plus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
                    result.setValue(out);
                }

        );
        btnMinus.addClickListener(event->{
                    double num1 = Double.parseDouble(n1.getValue());
                    double num2 = Double.parseDouble(n2.getValue());

                    String out = WebClient.create()
                            .get()
                            .uri("http://localhost:8080/minus/"+num1+"/"+num2)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                    result.setValue(out);
                }

        );
        btnDivide.addClickListener(event->{
                    double num1 = Double.parseDouble(n1.getValue());
                    double num2 = Double.parseDouble(n2.getValue());

                    String out = WebClient.create()
                            .get()
                            .uri("http://localhost:8080/divide/"+num1+"/"+num2)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                    result.setValue(out);
                }

        );
        btnMultiply.addClickListener(event->{
                    double num1 = Double.parseDouble(n1.getValue());
                    double num2 = Double.parseDouble(n2.getValue());

                    String out = WebClient.create()
                            .get()
                            .uri("http://localhost:8080/multi/"+num1+"/"+num2)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                    result.setValue(out);
                }

        );
        btnMod.addClickListener(event->{
                    double num1 = Double.parseDouble(n1.getValue());
                    double num2 = Double.parseDouble(n2.getValue());

                    String out = WebClient.create()
                            .get()
                            .uri("http://localhost:8080/mod/"+num1+"/"+num2)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                    result.setValue(out);
                }

        );
        btnMax.addClickListener(event->{

                    double num1 = Double.parseDouble(n1.getValue());
                    double num2 = Double.parseDouble(n2.getValue());

                    String out = WebClient.create()
                            .post()
                            .uri("http://localhost:8080/max")
                            .body(Mono.just(new MyNumber(num1, num2)), MyNumber.class)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                    result.setValue(out);
                }

        );




    }
    @RequestMapping(value="/plus/{n1}/{n2}", method = RequestMethod.GET)
    public String myPlus(@PathVariable("n1") double n1, @PathVariable("n2") double n2){
        return n1+n2+"";
    }
    @RequestMapping(value="/minus/{n1}/{n2}", method = RequestMethod.GET)
    public String myMinus(@PathVariable("n1") double n1, @PathVariable("n2") double n2){
        return n1-n2+"";
    }
    @RequestMapping(value="/divide/{n1}/{n2}", method = RequestMethod.GET)
    public String myDivide(@PathVariable("n1") double n1, @PathVariable("n2") double n2){
        return n1/n2+"";
    }
    @RequestMapping(value="/multi/{n1}/{n2}", method = RequestMethod.GET)
    public String myMulti(@PathVariable("n1") double n1, @PathVariable("n2") double n2){
        return n1*n2+"";
    }
    @RequestMapping(value="/mod/{n1}/{n2}", method = RequestMethod.GET)
    public String myMod(@PathVariable("n1") double n1, @PathVariable("n2") double n2){
        return n1%n2+"";
    }
    @RequestMapping(value = "/max", method = RequestMethod.POST)
    public double myMax(@RequestBody MyNumber n){
        return Math.max(n.getN1(), n.getN2());
    }
}
