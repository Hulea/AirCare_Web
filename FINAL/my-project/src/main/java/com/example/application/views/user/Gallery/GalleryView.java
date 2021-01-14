package com.example.application.views.user.Gallery;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Route(value = "gallery", layout = UserMain.class)
@PageTitle("Gallery")
public class GalleryView extends Div {

    public GalleryView() {
        HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
        HttpSession session = request.getSession();
        try {
            User bigUser = (User) session.getAttribute("user");
            if (!bigUser.getRole().equals("user")) throw new Exception();


            VerticalLayout aux = new VerticalLayout();
            aux.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            aux.setAlignItems(FlexComponent.Alignment.CENTER);
            aux.setAlignSelf(FlexComponent.Alignment.CENTER);

            HorizontalLayout all = new HorizontalLayout();



            VerticalLayout pics1 = new VerticalLayout();
            VerticalLayout pics2 = new VerticalLayout();
            pics1.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            pics1.setAlignItems(FlexComponent.Alignment.CENTER);
            pics1.setAlignSelf(FlexComponent.Alignment.CENTER);
            pics2.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            pics2.setAlignItems(FlexComponent.Alignment.CENTER);
            pics2.setAlignSelf(FlexComponent.Alignment.CENTER);

            VerticalLayout plane1 = new VerticalLayout();
            plane1.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            plane1.setAlignItems(FlexComponent.Alignment.CENTER);
            plane1.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img1 = new Image("images/a380.jpg", "pic1");
            img1.setWidth(640, Unit.PIXELS);
            img1.setHeight(360, Unit.PIXELS);
            H3 text1 = new H3("Boeing a380");
            plane1.add(img1,text1);

            VerticalLayout plane2 = new VerticalLayout();
            plane2.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            plane2.setAlignItems(FlexComponent.Alignment.CENTER);
            plane2.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img2 = new Image("images/787.jpg", "pic2");
            img2.setWidth(640, Unit.PIXELS);
            img2.setHeight(360, Unit.PIXELS);
            H3 text2 = new H3("Boeing 787");
            plane2.add(img2,text2);


            VerticalLayout plane3 = new VerticalLayout();
            plane3.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            plane3.setAlignItems(FlexComponent.Alignment.CENTER);
            plane3.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img3 = new Image("images/777.jpg", "pic3");
            img3.setWidth(640, Unit.PIXELS);
            img3.setHeight(360, Unit.PIXELS);
            H3 text3 = new H3("Boeing 777");
            plane3.add(img3,text3);


            VerticalLayout plane4 = new VerticalLayout();
            plane4.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            plane4.setAlignItems(FlexComponent.Alignment.CENTER);
            plane4.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img4 = new Image("images/767.jpg", "pic4");
            img4.setWidth(640, Unit.PIXELS);
            img4.setHeight(360, Unit.PIXELS);
            H3 text4 = new H3("Boeing 767");
            plane4.add(img4,text4);

            VerticalLayout plane5 = new VerticalLayout();
            plane5.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            plane5.setAlignItems(FlexComponent.Alignment.CENTER);
            plane5.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img5 = new Image("images/757.jpg", "pic5");
            img5.setWidth(640, Unit.PIXELS);
            img5.setHeight(360, Unit.PIXELS);
            H3 text5 = new H3("Boeing 757");
            plane5.add(img5,text5);


            VerticalLayout plane6 = new VerticalLayout();
            plane6.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            plane6.setAlignItems(FlexComponent.Alignment.CENTER);
            plane6.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img6 = new Image("images/747.jpg", "pic6");
            img6.setWidth(640, Unit.PIXELS);
            img6.setHeight(360, Unit.PIXELS);
            H3 text6 = new H3("Boeing 747");
            plane6.add(img6,text6);

            VerticalLayout plane7 = new VerticalLayout();
            plane7.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            plane7.setAlignItems(FlexComponent.Alignment.CENTER);
            plane7.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img7 = new Image("images/737.jpg", "pic7");
            img7.setWidth(640, Unit.PIXELS);
            img7.setHeight(360, Unit.PIXELS);
            H3 text7 = new H3("Boeing 737");
            plane7.add(img7,text7);

            VerticalLayout plane8 = new VerticalLayout();
            plane8.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            plane8.setAlignItems(FlexComponent.Alignment.CENTER);
            plane8.setAlignSelf(FlexComponent.Alignment.CENTER);
            Image img8 = new Image("images/717.jpg", "pic8");img7.setWidth(640, Unit.PIXELS);
            img8.setWidth(640, Unit.PIXELS);
            img8.setHeight(360, Unit.PIXELS);
            H3 text8 = new H3("Boeing 717");
            plane8.add(img8,text8);


            pics1.add(plane1,plane2,plane3,plane4);
            pics2.add(plane5,plane6,plane7,plane8);
            all.add(pics1,pics2);

            aux.add(new H1("Our plane models"),all);
            add(aux);

        } catch (Exception e) {
            UI.getCurrent().navigate("http://localhost:8080/");
        }
    }
}
