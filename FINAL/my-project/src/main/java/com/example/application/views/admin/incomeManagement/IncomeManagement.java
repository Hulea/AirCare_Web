package com.example.application.views.admin.incomeManagement;


import com.example.application.ConnectionFactory.IncomeAndScheduleDetails;
import com.example.application.ConnectionFactory.UserDetails;
import com.example.application.Model.IncomeAndSchedule;
import com.example.application.Model.User;
import com.example.application.views.admin.AdminMain;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Route(value = "adminIncomeManagement", layout = AdminMain.class)
@PageTitle("Income Management")
public class IncomeManagement extends Div {

    private Grid<IncomeAndSchedule> grid;
    TextField filterText = new TextField();
    IncomeAndScheduleDetails iasd = new IncomeAndScheduleDetails();

    UserDetails usr = new UserDetails();

    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("Email");
    TextField workType = new TextField("Job");
    TextField monthly = new TextField("Salary");
    TextField mondayHours = new TextField("Monday Hours");
    TextField tuesdayHours = new TextField("Tuesday Hours");
    TextField wednesdayHours = new TextField("Wednesday Hours");
    TextField thursdayHours = new TextField("Thursday Hours");
    TextField fridayHours = new TextField("Friday Hours");
    TextField saturdayHours = new TextField("Saturday Hours");
    TextField sundayHoursHours = new TextField("Sunday Hours");
    TextField freeDaysLeft = new TextField("Free Days Left");

    Button add = new Button("Add");
    Button editIncome = new Button("Edit Income");
    Button editWorkType = new Button("Edit Job");
    Button editSchedule = new Button("Edit Schedule");
    Button editFreeDays = new Button("Edit Free Days");
    Button removeWorker = new Button("Remove worker");
    HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
    HttpSession session = request.getSession();


    public IncomeManagement(){

        try {
            User bigUser = (User) session.getAttribute("admin");
            if (!bigUser.getRole().equals("admin")) throw new Exception();

            addClassName("list-view");
            setSizeFull();

            grid = new Grid<>();
            grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
            configureGrid();
            configureFilter();

            HorizontalLayout auxx1 = new HorizontalLayout();
            auxx1.add(firstName, lastName, email, workType, monthly);

            HorizontalLayout auxx2 = new HorizontalLayout();
            auxx2.add(mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours);

            HorizontalLayout auxx3 = new HorizontalLayout();
            auxx3.add(saturdayHours, sundayHoursHours, freeDaysLeft);

            VerticalLayout auxx = new VerticalLayout();
            auxx.add(auxx1, auxx2, auxx3);

            VerticalLayout aux2 = new VerticalLayout();
            aux2.add(auxx, createButtonsLayout());

            add(filterText, aux2, grid);
            updateGrid();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Component createButtonsLayout() {

        add.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        editIncome.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        editWorkType.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        editSchedule.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        editFreeDays.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        removeWorker.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        add.addClickListener( ev -> {
           iasd.addWorker(
                   usr.getUserNr(),
                   firstName.getValue(),
                   lastName.getValue(),
                   "randomEditablePass",
                   email.getValue(),
                   workType.getValue(),
                   Integer.parseInt(monthly.getValue()),
                   Integer.parseInt(mondayHours.getValue()),
                   Integer.parseInt(tuesdayHours.getValue()),
                   Integer.parseInt(wednesdayHours.getValue()),
                   Integer.parseInt(thursdayHours.getValue()),
                   Integer.parseInt(fridayHours.getValue()),
                   Integer.parseInt(saturdayHours.getValue()),
                   Integer.parseInt(sundayHoursHours.getValue()),
                   Integer.parseInt(freeDaysLeft.getValue())

           );
           updateGrid();
        });

        editIncome.addClickListener( ev -> {
            iasd.editIncome(email.getValue(),Integer.parseInt(monthly.getValue()));
            updateGrid();
                }
        );

        editWorkType.addClickListener( ev -> {
           iasd.editWorkType(email.getValue(),workType.getValue());
           updateGrid();
        });

        editSchedule.addClickListener( ev -> {
            iasd.editSchedule(
                    email.getValue(),
                    Integer.parseInt(mondayHours.getValue()),
                    Integer.parseInt(tuesdayHours.getValue()),
                    Integer.parseInt(wednesdayHours.getValue()),
                    Integer.parseInt(thursdayHours.getValue()),
                    Integer.parseInt(fridayHours.getValue()),
                    Integer.parseInt(saturdayHours.getValue()),
                    Integer.parseInt(sundayHoursHours.getValue()));
            updateGrid();
        });

        editFreeDays.addClickListener( ev -> {

            iasd.editFreeDays(email.getValue(),freeDaysLeft.getValue());
           updateGrid();
        });

        removeWorker.addClickListener( ev ->{
            iasd.removeWorker(email.getValue());

            updateGrid();
        });


        return new HorizontalLayout(add,editIncome,editWorkType,editSchedule,editFreeDays,removeWorker);


    }

    private void configureFilter() {

        filterText.setPlaceholder("Filter by email");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener( ev -> updateGrid());

    }

    private void updateGrid() {
        if(filterText.getValue() == null || filterText.isEmpty())
            grid.setItems(iasd.getIncomeAndScheduleDetails());
        else grid.setItems(iasd.getIncomeAndScheduleDetails(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassName("workers-grid");
        grid.setSizeFull();
        grid.addColumn(IncomeAndSchedule::getFirstName).setHeader("First Name");
        grid.addColumn(IncomeAndSchedule::getLastName).setHeader("Last Name");
        grid.addColumn(IncomeAndSchedule::getEmail).setHeader("Email");
        grid.addColumn(IncomeAndSchedule::getWorkType).setHeader("Job");
        grid.addColumn(IncomeAndSchedule::getMonthly).setHeader("Salary");
        grid.addColumn(IncomeAndSchedule::getMondayHours).setHeader("Monday");
        grid.addColumn(IncomeAndSchedule::getTuesdayHours).setHeader("Tuesday");
        grid.addColumn(IncomeAndSchedule::getWednesdayHours).setHeader("Wednesday");
        grid.addColumn(IncomeAndSchedule::getThursdayHours).setHeader("Thursday");
        grid.addColumn(IncomeAndSchedule::getFridayHours).setHeader("Friday");
        grid.addColumn(IncomeAndSchedule::getSaturdayHours).setHeader("Saturday");
        grid.addColumn(IncomeAndSchedule::getSundayHours).setHeader("Sunday");
        grid.addColumn(IncomeAndSchedule::getFreeDaysLeft).setHeader("Free Days Left");

    }
}
