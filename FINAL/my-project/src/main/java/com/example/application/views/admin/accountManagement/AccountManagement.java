package com.example.application.views.admin.accountManagement;

import com.example.application.ConnectionFactory.UserDetails;
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
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Route(value = "adminAccountManagement", layout = AdminMain.class)
@PageTitle("Account Management")
public class AccountManagement extends Div {

    private Grid<User> grid;
    TextField filterText = new TextField();
    UserDetails usr = new UserDetails();

    TextField id = new TextField("ID");
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("Email");
    TextField password = new TextField("Password");
    TextField role = new TextField("Role");

    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button edit = new Button("Edit");

    HttpServletRequest request = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
    HttpSession session = request.getSession();

    public AccountManagement() {

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
                firstName,
                lastName,
                email,
                password,
                role
        );

        VerticalLayout aux2 = new VerticalLayout();
        aux2.add(auxx,createButtonsLayout());

        add(filterText,aux2,grid);
        updateGrid();

        } catch (Exception e) {
            UI.getCurrent().navigate("http://localhost:8080/");
        }


    }

    private Component createButtonsLayout() {

        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        add.addClickListener( ev -> {
                if(id.getValue()==null || id.getValue().equals(""))
                    usr.addUser(new User(usr.getUserNr(),firstName.getValue(),lastName.getValue(),email.getValue(),password.getValue(),role.getValue()));
                else
                    usr.addUser(new User(Integer.parseInt(id.getValue()),firstName.getValue(),lastName.getValue(),email.getValue(),password.getValue(),role.getValue()));
            updateGrid();
        });

        delete.addClickListener( ev ->{
           usr.deleteUser(email.getValue());
            updateGrid();
        });

        edit.addClickListener( ev ->{
           usr.editUser(email.getValue(),firstName.getValue(),lastName.getValue(),password.getValue(),role.getValue());
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
            grid.setItems(usr.getUsersDetails());
        else grid.setItems(usr.getUsersDetails(filterText.getValue()));
    }


    private void configureGrid() {
        grid.addClassName("users-grid");
        grid.setSizeFull();
        grid.addColumn(User::getId).setHeader("ID");
        grid.addColumn(User::getFirstName).setHeader("First Name");
        grid.addColumn(User::getLastName).setHeader("Last Name");
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.addColumn(User::getPassword).setHeader("Password");
        grid.addColumn(User::getRole).setHeader("Role");

    }

    public String getGridRow(){
        SingleSelect<Grid<User>,User> sel = grid.asSingleSelect();
        return sel.getValue().getEmail();
    }

}
