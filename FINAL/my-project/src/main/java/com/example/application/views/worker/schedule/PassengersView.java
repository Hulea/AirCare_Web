package com.example.application.views.worker.schedule;

import com.example.application.ConnectionFactory.IncomeAndScheduleDetails;
import com.example.application.ConnectionFactory.PlaneTicketDetails;
import com.example.application.Model.IncomeAndSchedule;
import com.example.application.Model.PlaneTicket;
import com.example.application.Model.User;
import com.example.application.views.worker.WorkerMain;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
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
import java.util.List;

@Route(value = "workerPassengerView", layout = WorkerMain.class)
@PageTitle("Passengers")
public class PassengersView extends Div {

    HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
    HttpSession session = request.getSession();

    private Grid<PlaneTicket> passengers;
    private ComboBox<PlaneTicket> combo;
    private User bigUser;
    private PlaneTicketDetails planeticktdtils = new PlaneTicketDetails();
    private TextField email;
    IncomeAndScheduleDetails iasd = new IncomeAndScheduleDetails();
    List<IncomeAndSchedule> sch_aux;

    public PassengersView(){

        try {
            bigUser = (User) session.getAttribute("worker");
            if (!bigUser.getRole().equals("worker")) throw new Exception();

            setSizeFull();
            sch_aux = iasd.getIncomeAndScheduleDetails(bigUser.getEmail());

            if(sch_aux.get(0).getWorkType().equals("pilot")) {

                email = new TextField();
                email.setPlaceholder("Filter by email");

                passengers = new Grid<>();
                passengers.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                        GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
                configureGrid();

                configureFilter();

                combo = new ComboBox<>();
                combo.setAllowCustomValue(false);
                combo.addValueChangeListener(ev ->{
                    fillGrid();
                });
                fillCombo();

                HorizontalLayout lay1 = new HorizontalLayout();
                lay1.add(email,combo);



                add(lay1,passengers);

                fillGrid();


            }
            else{

                VerticalLayout auxx = new VerticalLayout();
                H3 aux = new H3("Only the pilots and attendants can view this");
                auxx.add(aux);
                auxx.setAlignItems(FlexComponent.Alignment.CENTER);
                auxx.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
                add(auxx);
            }


        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void configureGrid() {

        passengers.addClassName("users-grid");
        passengers.setSizeFull();
        passengers.addColumn(PlaneTicket::getTicket_id).setHeader("Nr");
        passengers.addColumn(PlaneTicket::getUser_email).setHeader("Passenger");
        passengers.addColumn(PlaneTicket::getRoute_id).setHeader("Route");
        passengers.addColumn(PlaneTicket::getPlane_model).setHeader("Plane Model");
        passengers.addColumn(PlaneTicket::getTicket_date).setHeader("Date");

    }

    private void configureFilter() {
        email.setPlaceholder("Filter by Start Location");
        email.setClearButtonVisible(true);
        email.setValueChangeMode(ValueChangeMode.LAZY);
        email.addValueChangeListener( ev -> fillGrid());
    }

    private void fillGrid() {

        if(combo.getValue().getTicket_date().equals("") || combo.isEmpty())
            passengers.setItems(planeticktdtils.getTicketsDetailsForPilot(bigUser.getEmail()));
        else
            passengers.setItems(planeticktdtils.getTicketsDetailsForPilot(bigUser.getEmail(),combo.getValue().getTicket_date()));

    }

    private void fillCombo() {

        PlaneTicketDetails aux = new PlaneTicketDetails();
        List<PlaneTicket> planeeTickeets = aux.getTicketsDetailsForPilot(bigUser.getEmail());

        combo.setItemLabelGenerator(PlaneTicket::getTicket_date);
        combo.setItems(planeeTickeets);
        combo.setValue(planeeTickeets.get(0));

    }

}
