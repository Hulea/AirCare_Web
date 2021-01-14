package com.example.application.views.user.buy;

import com.example.application.ConnectionFactory.PlaneTicketDetails;
import com.example.application.ConnectionFactory.RoutesDetails;
import com.example.application.Model.User;
import com.example.application.views.user.UserMain;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;

@Route(value = "userBuyView",layout = UserMain.class)
@PageTitle("Buy")
public class BuyView extends Div {

    HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
    HttpSession session = request.getSession();


    PlaneTicketDetails ptd = new PlaneTicketDetails();
    RoutesDetails rd = new RoutesDetails();
    private TextField start = new TextField("Departure Location");
    private TextField destination = new TextField("Destination");

    private DatePicker datePicker = new DatePicker();

    private Dialog dialog = new Dialog();

    private Button openDialog = new Button("Confirm");

    private Text diagtext;

    public BuyView(){

        try {
            User bigUser = (User) session.getAttribute("user");
            if (!bigUser.getRole().equals("user")) throw new Exception();

            VerticalLayout vert1 = new VerticalLayout();
            vert1.setAlignSelf(FlexComponent.Alignment.CENTER);
            vert1.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            vert1.setAlignItems(FlexComponent.Alignment.CENTER);

            ComboBox<String> combo = new ComboBox<>("Plane model");
            combo.setItems("a380","787","777","767","757","747","737","717");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

            Button buyButton = new Button("Buy", ev ->{
                com.vaadin.flow.component.notification.Notification.show("Successful transaction!",2000, Notification.Position.TOP_CENTER);
                ptd.buyTicket(
                        bigUser.getEmail(),
                        "worker@worker.com",
                        ptd.returnRouteId(start.getValue(),destination.getValue(),datePicker.getValue().format(formatter)),
                        combo.getValue(),
                        datePicker.getValue().toString()
                );
                dialog.close();
            });
            Button cancelButton = new Button("Cancel", ev -> {

                dialog.close();
            });



            HorizontalLayout diagbuttons = new HorizontalLayout();
            diagbuttons.add(buyButton,cancelButton);
            VerticalLayout alldiag = new VerticalLayout();
            diagtext = new Text("The price will be "+ rd.price(start.getValue(),destination.getValue()) +"Accept?");
            alldiag.add(diagtext,diagbuttons);
            dialog.add(alldiag);

            openDialog.addClickListener(event -> {
                dialog.open();
                diagtext.setText("The price will be "+ rd.price(start.getValue(),destination.getValue()) +". Accept?");

            });

            vert1.add(start,destination,datePicker,combo,openDialog);

            add(vert1);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
