package com.example.application.views.user.about;

import com.example.application.Model.User;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.views.user.UserMain;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Route(value = "about", layout = UserMain.class)
@PageTitle("About")
public class AboutView extends Div {

    public AboutView() {
        HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
        HttpSession session = request.getSession();
        try {
            User bigUser = (User)session.getAttribute("user");
            if(!bigUser.getRole().equals("user")) throw new Exception();



        }
        catch (Exception e) {
            UI.getCurrent().navigate("http://localhost:8080/");
        }
    }

}
