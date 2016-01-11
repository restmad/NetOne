package netcracker.app.wf.client.admin.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import netcracker.app.wf.client.admin.ClientFactory;

/**
 * Created by ���������� on 02.12.2015.
 */
public class AppLayout extends Composite {
    private ClientFactory clientFactory;
    interface MainViewUiBinder extends UiBinder<Widget, AppLayout> {
    }

    private static MainViewUiBinder ourUiBinder = GWT.create(MainViewUiBinder.class);

    @UiField
    SimplePanel appContent;
    public AppLayout(ClientFactory clientFactory) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.clientFactory = clientFactory;
    }

    public SimplePanel getAppContentHolder(){
        return this.appContent;
    }
}