package com.example.application.views.user.Welcome;


import com.example.application.Model.User;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.views.user.UserMain;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import javax.management.Notification;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Route(value = "welcomeUser", layout = UserMain.class)
@PageTitle("Welcome")
public class WelcomeUserView extends Div {

    public WelcomeUserView() {

        HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
        HttpSession session = request.getSession();
        try {
            User bigUser = (User)session.getAttribute("user");
            if(!bigUser.getRole().equals("user")) throw new Exception();
//            add(new Text("Welcome user "+ bigUser.getEmail() +"!"));

            VerticalLayout aux = new VerticalLayout();
            aux.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

            H1 rec = new H1("Most visited locations");

            HorizontalLayout allrecoms = new HorizontalLayout();

            VerticalLayout recoms1 = new VerticalLayout();
            recoms1.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            recoms1.setAlignItems(FlexComponent.Alignment.CENTER);
            recoms1.setAlignSelf(FlexComponent.Alignment.CENTER);
            VerticalLayout recoms2 =  new VerticalLayout();
            recoms2.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            recoms2.setAlignItems(FlexComponent.Alignment.CENTER);
            recoms2.setAlignSelf(FlexComponent.Alignment.CENTER);

            VerticalLayout pic_and_text1 = new VerticalLayout();
            pic_and_text1.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            pic_and_text1.setAlignItems(FlexComponent.Alignment.CENTER);
            pic_and_text1.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img1 = new Image("images/ibiza.jpg","pic1");
            img1.setWidth(640, Unit.PIXELS);
            img1.setHeight(360, Unit.PIXELS);
            pic_and_text1.add(img1);
            pic_and_text1.add(new H3("Ibiza"));

            VerticalLayout pic_and_text2 = new VerticalLayout();
            pic_and_text2.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            pic_and_text2.setAlignItems(FlexComponent.Alignment.CENTER);
            pic_and_text2.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img2 = new Image("images/monaco.jpg","pic2");
            img2.setWidth(640, Unit.PIXELS);
            img2.setHeight(360, Unit.PIXELS);
            pic_and_text2.add(img2);
            pic_and_text2.add(new H3("Monaco"));

            VerticalLayout pic_and_text3 = new VerticalLayout();
            pic_and_text3.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            pic_and_text3.setAlignItems(FlexComponent.Alignment.CENTER);
            pic_and_text3.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img3 = new Image("images/dubai.jpg","pic3");
            img3.setWidth(640, Unit.PIXELS);
            img3.setHeight(360, Unit.PIXELS);
            pic_and_text3.add(img3);
            pic_and_text3.add(new H3("Dubai"));

            VerticalLayout pic_and_text4 = new VerticalLayout();
            pic_and_text4.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            pic_and_text4.setAlignItems(FlexComponent.Alignment.CENTER);
            pic_and_text4.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img4 = new Image("images/croatia.jpg","pic4");
            img4.setWidth(640, Unit.PIXELS);
            img4.setHeight(360, Unit.PIXELS);
            pic_and_text4.add(img4);
            pic_and_text4.add(new H3("Croatia"));

            VerticalLayout pic_and_text5 = new VerticalLayout();
            pic_and_text5.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            pic_and_text5.setAlignItems(FlexComponent.Alignment.CENTER);
            pic_and_text5.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img5 = new Image("images/rome.jpg","pic5");
            img5.setWidth(640, Unit.PIXELS);
            img5.setHeight(360, Unit.PIXELS);
            pic_and_text5.add(img5);
            pic_and_text5.add(new H3("Rome"));

            VerticalLayout pic_and_text6 = new VerticalLayout();
            pic_and_text6.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            pic_and_text6.setAlignItems(FlexComponent.Alignment.CENTER);
            pic_and_text6.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img6 = new Image("images/viena.jpg","pic6");
            img6.setWidth(640, Unit.PIXELS);
            img6.setHeight(360, Unit.PIXELS);
            pic_and_text6.add(img6);
            pic_and_text6.add(new H3("Wien"));

            recoms1.add(pic_and_text1,pic_and_text2,pic_and_text3);
            recoms2.add(pic_and_text4,pic_and_text5,pic_and_text6);
            allrecoms.add(recoms1,recoms2);
            aux.add(rec,allrecoms);
            aux.setAlignItems(FlexComponent.Alignment.CENTER);
            aux.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            add(aux);

        } catch (Exception e) {
            UI.getCurrent().navigate("http://localhost:8080/");
        }

    }

}
