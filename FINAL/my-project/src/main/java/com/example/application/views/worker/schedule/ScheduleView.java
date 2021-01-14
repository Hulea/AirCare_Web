package com.example.application.views.worker.schedule;

import com.example.application.ConnectionFactory.IncomeAndScheduleDetails;
import com.example.application.ConnectionFactory.ScheduleDetails;
import com.example.application.ConnectionFactory.UserDetails;
import com.example.application.Model.Schedule;
import com.example.application.Model.User;
import com.example.application.views.worker.WorkerMain;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Route(value = "scheduleView",layout = WorkerMain.class)
@PageTitle("Schedule")
public class ScheduleView extends Div {

    private Grid<Schedule> grid1;
    private Grid<Schedule> grid2;
    private Grid<Schedule> grid3;
    private Grid<Schedule> grid4;
    private Grid<Schedule> grid5;
    private Grid<Schedule> grid6;
    private Grid<Schedule> grid7;
    ScheduleDetails sch = new ScheduleDetails();
    HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
    HttpSession session = request.getSession();
    User bigUser;


    TextField email = new TextField("Email");

    public ScheduleView(){

        try {
            bigUser = (User) session.getAttribute("worker");
            if (!bigUser.getRole().equals("worker")) throw new Exception();

            addClassName("list-view");
            setSizeFull();

            grid1 = new Grid<>();
            grid2 = new Grid<>();
            grid3 = new Grid<>();
            grid4 = new Grid<>();
            grid5 = new Grid<>();
            grid6 = new Grid<>();
            grid7 = new Grid<>();
            grid1.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
            grid2.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
            grid3.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
            grid4.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                   GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
            grid5.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
            grid6.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
            grid7.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
            configureGrid();


            HorizontalLayout auxx = new HorizontalLayout();
            email.setReadOnly(true);
            email.setValue(bigUser.getEmail());
            auxx.add(
                    email
            );

            VerticalLayout aux2 = new VerticalLayout();
            aux2.add(auxx,grid1,grid2,grid3,grid4,grid5,grid6,grid7);

            add(email,aux2);


        } catch (Exception e) {
            UI.getCurrent().navigate("http://localhost:8080/");
        }
    }

    private void configureGrid() {

//        grid1.addClassName("users-grid");
//        grid1.setSizeFull();
//        grid2.addClassName("users-grid2");
//        grid2.setSizeFull();
        grid1.addColumn(Schedule::getMon).setHeader("Monday hours");
        grid1.addColumn(Schedule::getMon_start).setHeader("Starting at");
        grid2.addColumn(Schedule::getTue).setHeader("Tuesday hours");
        grid2.addColumn(Schedule::getTue_start).setHeader("Starting at");
        grid3.addColumn(Schedule::getWen).setHeader("Wednesday hours");
        grid3.addColumn(Schedule::getWen_start).setHeader("Starting at");
        grid4.addColumn(Schedule::getThu).setHeader("Thursday hours");
        grid4.addColumn(Schedule::getThu_start).setHeader("Starting at");
        grid5.addColumn(Schedule::getFri).setHeader("Friday hours");
        grid5.addColumn(Schedule::getFri_start).setHeader("Starting at");
        grid6.addColumn(Schedule::getSat).setHeader("Saturday hours");
        grid6.addColumn(Schedule::getSat_start).setHeader("Starting at");
        grid7.addColumn(Schedule::getSun).setHeader("Sunday hours");
        grid7.addColumn(Schedule::getSun_start).setHeader("Starting at");

        grid1.setItems(sch.getScheduleDetails(bigUser.getEmail()));
        grid2.setItems(sch.getScheduleDetails(bigUser.getEmail()));
        grid3.setItems(sch.getScheduleDetails(bigUser.getEmail()));
        grid4.setItems(sch.getScheduleDetails(bigUser.getEmail()));
        grid5.setItems(sch.getScheduleDetails(bigUser.getEmail()));
        grid6.setItems(sch.getScheduleDetails(bigUser.getEmail()));
        grid7.setItems(sch.getScheduleDetails(bigUser.getEmail()));


    }
}
