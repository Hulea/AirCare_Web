package com.example.application.views.user.Routes;

import com.example.application.ConnectionFactory.RoutesDetails;
import com.example.application.Model.User;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.user.UserMain;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Route(value = "routes", layout = UserMain.class)
@PageTitle("Routes")
public class RoutesView extends Div {

    HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
    HttpSession session = request.getSession();

    private Grid<com.example.application.Model.Route> grid;
    RoutesDetails rd = new RoutesDetails();


    TextField start = new TextField("Start");
    TextField destination = new TextField("Destination");


    public RoutesView() {
        try {
            User bigUser = (User)session.getAttribute("user");
            if(!bigUser.getRole().equals("user")) throw new Exception();

            addClassName("list-view");
            setSizeFull();

            grid = new Grid<>();
            grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);


            configureGrid();
            configureFilter1();
            configureFilter2();

            HorizontalLayout filters = new HorizontalLayout();
            filters.add(start,destination);

            VerticalLayout aux = new VerticalLayout();
            aux.add(filters);
            aux.setAlignItems(FlexComponent.Alignment.CENTER);
            aux.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            aux.setAlignSelf(FlexComponent.Alignment.CENTER);

            add(aux,grid);
            updateGrid();

        } catch (Exception e) {
            UI.getCurrent().navigate("http://localhost:8080/");
        }
    }

    private void configureFilter1() {
        start.setPlaceholder("Filter by Start Location");
        start.setClearButtonVisible(true);
        start.setValueChangeMode(ValueChangeMode.LAZY);
        start.addValueChangeListener( ev -> updateGrid());
    }

    private void configureFilter2() {
        destination.setPlaceholder("Filter by Destination");
        destination.setClearButtonVisible(true);
        destination.setValueChangeMode(ValueChangeMode.LAZY);
        destination.addValueChangeListener( ev -> updateGrid());
    }

    private void updateGrid() {
        if(start.getValue() == null || start.isEmpty())
            if(destination.getValue() == null || destination.isEmpty())
                grid.setItems(rd.getRoutesDetails());
            else grid.setItems(rd.getRoutesDetails2(destination.getValue()));
        else grid.setItems(rd.getRoutesDetails(start.getValue()));
    }

    private void configureGrid(){

        grid.addClassName("routes-grid");
        grid.setSizeFull();
        grid.addColumn(com.example.application.Model.Route::getStart).setHeader("Start Location");
        grid.addColumn(com.example.application.Model.Route::getDestination).setHeader("Destination");
        grid.addColumn(com.example.application.Model.Route::getHours).setHeader("Hours");
        grid.addColumn(com.example.application.Model.Route::getMinutes).setHeader("Minutes");
        grid.addColumn(com.example.application.Model.Route::getRoute_date).setHeader("Date");
        grid.addColumn(com.example.application.Model.Route::getPrice).setHeader("Price");

    }

}
