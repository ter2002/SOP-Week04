package com.example.week04;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;


@Route(value = "index2")
public class CashierView extends VerticalLayout {
    private TextField money,b1000,b500,b100,b20,b10,b5,b1;
    private Span text;
    private int number;
    private Button result;


    public CashierView(){
        Div dollarPrefix = new Div();
        Div n1 = new Div();Div n2 = new Div();Div n3 = new Div();Div n4 = new Div();Div n5 = new Div();Div n6 = new Div();Div n7 = new Div();

        dollarPrefix.setText("$");
        n1.setText("$1000:");n2.setText("$500:");n3.setText("$100:");n4.setText("$20:");n5.setText("$10:");n6.setText("$5:");n7.setText("$1:");
        result = new Button("คำนวณเงินทอน");
        money = new TextField();
        b1000 = new TextField();;
        b500 = new TextField();b100 = new TextField();b20 = new TextField();b10 = new TextField();b5 = new TextField();b1 = new TextField();
        money.setPrefixComponent(dollarPrefix);
        money.setLabel("เงินทอน");
        b1000.setPrefixComponent(n1);b500.setPrefixComponent(n2);b100.setPrefixComponent(n3);b20.setPrefixComponent(n4);b10.setPrefixComponent(n5);b5.setPrefixComponent(n6);b1.setPrefixComponent(n7);
        b1000.setReadOnly(true);b500.setReadOnly(true);b100.setReadOnly(true);b20.setReadOnly(true);b10.setReadOnly(true);b5.setReadOnly(true);b1.setReadOnly(true);
        add(money,result,b1000,b500,b100,b20,b10,b5,b1);

        result.addClickListener(event->{
                    int num = Integer.parseInt(money.getValue());

                    Change out = WebClient.create()
                            .get()
                            .uri("http://localhost:8080/getChange/"+num)
                            .retrieve()
                            .bodyToMono(Change.class)
                            .block();
                b1000.setValue(out.getB1000()+"");
                b500.setValue(out.getB500()+"");
                b100.setValue(out.getB100()+"");
                b20.setValue(out.getB20()+"");
                b10.setValue(out.getB10()+"");
                b5.setValue(out.getB5()+"");
                b1.setValue(out.getB1()+"");
                }

        );
    }


}
