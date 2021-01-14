package com.example.application.views;

import com.example.application.ConnectionFactory.UserDetails;
import com.example.application.Model.User;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "register")
@PageTitle("Register")
@CssImport("./styles/views/helloworld/hello-world-view.css")
public class RegisterView extends VerticalLayout {

    //private TextField id = new TextField("ID");
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last name");
    private TextField email = new TextField("Email address");
    private PasswordField password = new PasswordField("Password");
    private PasswordField repass = new PasswordField("Retype Password");
    private Checkbox checkbox = new Checkbox("Accept Terms and Conditions");

    public RegisterView(){

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setHeightFull();

        Button button = new Button("Continue", event -> registerUser());

        add(firstName,lastName,email,password,repass,checkbox,button);

    }

    private void registerUser() {

        UserDetails usr = new UserDetails();
        if(checkbox.getValue().equals(false))
            Notification.show("You must accept the terms and conditions!",2000, Notification.Position.TOP_CENTER);

        else if(password.getValue().equals(repass.getValue())){
            User aux = new User(usr.getUserNr() + 1,firstName.getValue(),lastName.getValue(),email.getValue(),password.getValue(),"user");
            usr.registerUser(aux);
            UI.getCurrent().navigate("");
            Notification.show("Account Registered!",2000, Notification.Position.TOP_CENTER);
        }
        else {
            Notification.show("Different passwords!",2000, Notification.Position.TOP_CENTER);
        }

    }

}
