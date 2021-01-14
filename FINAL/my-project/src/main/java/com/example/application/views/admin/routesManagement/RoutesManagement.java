package com.example.application.views.admin.routesManagement;

import com.example.application.ConnectionFactory.RoutesDetails;
import com.example.application.Model.Route;
import com.example.application.Model.User;
import com.example.application.views.admin.AdminMain;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
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
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@com.vaadin.flow.router.Route(value = "adminRoutesManagement", layout = AdminMain.class)
@PageTitle("Routes Management")
public class RoutesManagement extends Div {

    private Grid<Route> grid;
    RoutesDetails rd = new RoutesDetails();
    TextField filterText1 = new TextField();
    TextField filterText2 = new TextField();

    TextField id = new TextField("ID");
    TextField start = new TextField("Start");
    TextField destination = new TextField("Destination");
    TextField duration_hr = new TextField("Hours");
    TextField duration_min = new TextField("Minutes");
    TextField price = new TextField("Price");
    TextField route_date = new TextField("Route Date");

    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button edit = new Button("Edit");

    Button editStart = new Button("Edit Start Location");
    Button editDestination = new Button("Edit Destination");
    Button editPrice = new Button("Edit Price");

    HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
    HttpSession session = request.getSession();

    public RoutesManagement(){
        try {
            User bigUser = (User) session.getAttribute("admin");
            if (!bigUser.getRole().equals("admin")) throw new Exception();

            addClassName("list-view");
            setSizeFull();

            grid = new Grid<>();
            grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);

            configureGrid();
            configureFilter1();
            configureFilter2();

            HorizontalLayout aux1 = new HorizontalLayout();
            aux1.add(filterText1,filterText2);

            HorizontalLayout aux2 = new HorizontalLayout();
            aux2.add(id,start,destination,duration_hr,duration_min,route_date,price);

            VerticalLayout aux4 = new VerticalLayout();
            aux4.add(aux1,aux2,configureButtons());

            add(aux4,grid);
            updateGrid();


        } catch (Exception e) {
            UI.getCurrent().navigate("http://localhost:8080/");
        }
    }

    private Component configureButtons(){


        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);


        add.addClickListener( ev -> {
            rd.addRoute(
                    new Route
                    (start.getValue(),
                    destination.getValue(),
                    Integer.parseInt(duration_hr.getValue()),
                    Integer.parseInt(duration_min.getValue()),
                    route_date.getValue(),
                    Integer.parseInt(price.getValue())));
            updateGrid();
        });

        delete.addClickListener( ev -> {
           rd.deleteRoute(Integer.parseInt(id.getValue()));
            updateGrid();
        });

        edit.addClickListener( ev -> {
            rd.editRoute(
                    Integer.parseInt(id.getValue()),
                    start.getValue(),
                    destination.getValue(),
                    Integer.parseInt(duration_hr.getValue()),
                    Integer.parseInt(duration_min.getValue()),
                    route_date.getValue(),
                    Integer.parseInt(price.getValue()));
            updateGrid();
        });

        editStart.addClickListener( ev -> {
            rd.editRouteStart(start.getValue(),Integer.parseInt(id.getValue()));
            updateGrid();
        });

        editDestination.addClickListener(ev -> {
            rd.editRouteDestination(destination.getValue(),Integer.parseInt(id.getValue()));
            updateGrid();
        });

        editPrice.addClickListener(ev -> {
            rd.editRoutePrice(Integer.parseInt(price.getValue()),Integer.parseInt(id.getValue()));
            updateGrid();
        });

        return new HorizontalLayout(add,edit,delete,editStart,editDestination,editPrice);
    }

    private void configureFilter1() {
        filterText1.setPlaceholder("Filter by Start Location");
        filterText1.setClearButtonVisible(true);
        filterText1.setValueChangeMode(ValueChangeMode.LAZY);
        filterText1.addValueChangeListener( ev -> updateGrid());
    }

    private void configureFilter2() {
        filterText2.setPlaceholder("Filter by Destination");
        filterText2.setClearButtonVisible(true);
        filterText2.setValueChangeMode(ValueChangeMode.LAZY);
        filterText2.addValueChangeListener( ev -> updateGrid());
    }

    private void updateGrid() {
        if(filterText1.getValue() == null || filterText1.isEmpty())
            if(filterText2.getValue() == null || filterText2.isEmpty())
                grid.setItems(rd.getRoutesDetails());
            else grid.setItems(rd.getRoutesDetails2(filterText2.getValue()));
        else grid.setItems(rd.getRoutesDetails(filterText1.getValue()));
    }

    private void configureGrid() {

        grid.addClassName("routes-grid");
        grid.setSizeFull();
        grid.addColumn(Route::getId).setHeader("ID");
        grid.addColumn(Route::getStart).setHeader("Start Location");
        grid.addColumn(Route::getDestination).setHeader("Destination");
        grid.addColumn(Route::getHours).setHeader("Hours");
        grid.addColumn(Route::getMinutes).setHeader("Minutes");
        grid.addColumn(Route::getRoute_date).setHeader("Date");
        grid.addColumn(Route::getPrice).setHeader("Price");



    }

}
