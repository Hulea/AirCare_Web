package com.example.application.views.admin.vechicleManagement;


import com.example.application.ConnectionFactory.VehiclesDetails;
import com.example.application.Model.User;
import com.example.application.Model.Vehicle;
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
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Route(value = "adminVehiclesManagement", layout = AdminMain.class)
@PageTitle("Vehicle Management")
public class VehiclesManagement extends Div {

    private Grid<Vehicle> grid;
    TextField filterText = new TextField();
    VehiclesDetails vhc = new VehiclesDetails();

    TextField id = new TextField("ID");
    TextField name = new TextField("Name");
    TextField fuelConsumption = new TextField("Fuel Consumption");
    TextField damage = new TextField("Damage");
    TextField capacity = new TextField("Capacity");

    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button edit = new Button("Edit");

    HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
    HttpSession session = request.getSession();

    public VehiclesManagement() {
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

            HorizontalLayout auxx = new HorizontalLayout();
            auxx.add(
                    id,
                    name,
                    fuelConsumption,
                    damage,
                    capacity
            );

            VerticalLayout aux2 = new VerticalLayout();
            aux2.add(auxx, createButtonLayout());

            add(filterText, aux2, grid);
            updateGrid();
        } catch (Exception e){
            UI.getCurrent().navigate("http://localhost:8080/");
        }

}

    private Component createButtonLayout() {

        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);


        add.addClickListener( ev ->{
           vhc.addVehicle(new Vehicle(name.getValue(),Integer.parseInt(fuelConsumption.getValue()),damage.getValue(),Integer.parseInt(capacity.getValue())));
            updateGrid();
        });

        delete.addClickListener( ev ->{
                vhc.deleteVehicle(Integer.parseInt(id.getValue()),name.getValue());
            updateGrid();
        });

        edit.addClickListener(ev ->{
           vhc.editVehicle(new Vehicle(Integer.parseInt(id.getValue()),name.getValue(),Integer.parseInt(fuelConsumption.getValue()),damage.getValue(),Integer.parseInt(capacity.getValue())));
            updateGrid();
        });

        return new HorizontalLayout(add,delete,edit);
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener( e -> updateGrid());
    }

    private void updateGrid() {
        if(filterText.getValue() == null || filterText.isEmpty())
            grid.setItems(vhc.getVehicles());
        else grid.setItems(vhc.getVehicles(filterText.getValue()));
    }

    private void configureGrid() {

        grid.addClassName("vehicles-grid");
        grid.setSizeFull();
        grid.addColumn(Vehicle::getId).setHeader("ID");
        grid.addColumn(Vehicle::getName).setHeader("Name");
        grid.addColumn(Vehicle::getFuelConsumption).setHeader("Fuel consumption");
        grid.addColumn(Vehicle::getDamage).setHeader("Damage");
        grid.addColumn(Vehicle::getCapacity).setHeader("Capacity");
    }



}
