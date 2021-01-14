package com.example.application.views;


import com.example.application.ConnectionFactory.UserDetails;
import com.example.application.Model.User;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Route(value = "")
@PageTitle("Login")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "")
//@Theme(value = Lumo.class, variant = Lumo.DARK)
public class LoginView extends VerticalLayout {

    public HttpSession session;
    public HttpServletRequest request;
    private TextField email = new TextField("Email");
    private PasswordField password = new PasswordField("Password");
    private boolean ok = false;
    public User bigUser = null;
    public boolean darkTheme;

    public LoginView() {

        request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
        session = request.getSession();
        session.removeAttribute("user");

        Button toggle = new Button("Dark Theme",click ->{
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();

                    if(themeList.contains(Lumo.DARK))
                        themeList.remove(Lumo.DARK);
                    else themeList.add(Lumo.DARK);
        });

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setHeightFull();
        Button button = new Button("Login", event -> checkLogin());
        Button register = new Button("Register?", event -> UI.getCurrent().navigate("register"));

        button.addClickShortcut(Key.ENTER);

        HorizontalLayout aux = new HorizontalLayout();
        aux.add(button,register);

        add(new Image("images/flight_movement6.png", "My Project logo"),new H1("Login"),email, password, aux,toggle);
    }

    private void checkLogin() {

        UserDetails users = new UserDetails();
        List<User> listUsers = users.getUsersDetails();
        User aux = new User(email.getValue(), password.getValue(), null);


        for (User i : listUsers) {

            if ((i.getEmail().equals(aux.getEmail())) & (i.getPassword().equals(aux.getPassword()))) {

                if (i.getRole().equals("user")) {
                    this.bigUser = i;
                    ok = true;
                    request = ((VaadinServletRequest)VaadinService.getCurrentRequest()).getHttpServletRequest();
                    session = request.getSession();
                    session.setAttribute("user",this.bigUser);
                    UI.getCurrent().navigate("welcomeUser");

                }
                else if (i.getRole().equals("admin")) {
                    this.bigUser = i;
                    ok = true;
                    request = ((VaadinServletRequest)VaadinService.getCurrentRequest()).getHttpServletRequest();
                    session = request.getSession();
                    session.setAttribute("admin",this.bigUser);
                    UI.getCurrent().navigate("adminAccountManagement");
                }
                else if (i.getRole().equals("worker")) {
                    this.bigUser = i;
                    ok = true;
                    request = ((VaadinServletRequest)VaadinService.getCurrentRequest()).getHttpServletRequest();
                    session = request.getSession();
                    session.setAttribute("worker",this.bigUser);
                    UI.getCurrent().navigate("scheduleView");
                }
            }
        }

        if (ok == false)
            Notification.show("Wrong usermane or password!",2000, Notification.Position.TOP_CENTER);

    }

}
