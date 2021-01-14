package com.example.application.views.user.history;

import com.example.application.ConnectionFactory.HistoryDetails;
import com.example.application.Model.History;
import com.example.application.Model.User;
import com.example.application.views.user.UserMain;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Route(value = "userHistoryView",layout = UserMain.class)
@PageTitle("History")
public class HistoryView extends Div {

    HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
    HttpSession session = request.getSession();

    HistoryDetails hst = new HistoryDetails();
    User bigUser;
    private Grid<History> grid;

    public HistoryView(){

        try {
            bigUser = (User) session.getAttribute("user");
            if (!bigUser.getRole().equals("user")) throw new Exception();

            setSizeFull();

            grid = new Grid<>();
            grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                    GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);

            configureGrid();

            VerticalLayout vert = new VerticalLayout();
            vert.add(new H3("Your plane ticket transaction history"));


            add(vert,grid);
            updateGrid();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateGrid() {
        grid.setItems(hst.getHistoryDetails(bigUser.getEmail()));
    }

    private void configureGrid() {
        grid.addClassName("routes-grid");
        grid.setSizeFull();
        grid.addColumn(History::getStart).setHeader("Start");
        grid.addColumn(History::getDestination).setHeader("Destination");
        grid.addColumn(History::getDate).setHeader("Date");
        grid.addColumn(History::getPrice).setHeader("Price");
    }
}
