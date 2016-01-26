package netcracker.app.wf.client.admin.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.client.admin.ClientFactory;
import netcracker.app.wf.client.admin.UserService;
import netcracker.app.wf.client.admin.mvp.view.widgets.UsersView;
import netcracker.app.wf.back.model.User;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���������� on 04.12.2015.
 */
public class UsersActivity extends AbstractActivity implements UsersView.UsersPresenter {
    private ClientFactory clientFactory;
    private UsersView view;
    private UserService service;

    public UsersActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        service = GWT.create(UserService.class);
    }


    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view = clientFactory.getUsersView();
        view.setPresenter(this);
        panel.setWidget(view.asWidget());

        service.getAllUsers(new MethodCallback<List<User>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
            }

            @Override
            public void onSuccess(Method method, List<User> users) {
                view.updateTable(users);
            }
        });
    }

    @Override
    public void updateTableByName(String token) {
        if(!token.equals("")) {
            service.getUserByName(token, new MethodCallback<List<User>>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                }

                @Override
                public void onSuccess(Method method, List<User> users) {
                    view.updateTable(users);
                }
            });
        }
    }

    @Override
    public void updateTableById(String token) {
        if(!token.equals("")) {
            service.getUserById(Integer.parseInt(token), new MethodCallback<User>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                }

                @Override
                public void onSuccess(Method method, User user) {
                    List<User> users = new ArrayList<User>();
                    users.add(user);
                    view.updateTable(users);
                }
            });
        }
    }

    @Override
    public void showAll() {
        service.getAllUsers(new MethodCallback<List<User>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
            }

            @Override
            public void onSuccess(Method method, List<User> users) {
                view.updateTable(users);
            }
        });
    }
}