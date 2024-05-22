import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@SuppressWarnings("unused")
public class Extension implements BurpExtension
{
    private static final String EXTENSION_NAME = "Editable response editor";
    @Override
    public void initialize(MontoyaApi montoyaApi)
    {
        montoyaApi.extension().setName(EXTENSION_NAME);

        MyPanel panel = new MyPanel(montoyaApi.userInterface());

        montoyaApi.userInterface().registerContextMenuItemsProvider(new ContextMenuItemsProvider()
        {
            @Override
            public List<Component> provideMenuItems(ContextMenuEvent event)
            {
                JMenuItem menuItem = new JMenuItem("Send to editable response editor");
                menuItem.addActionListener(l -> {
                    HttpRequestResponse requestResponse = event.messageEditorRequestResponse().isPresent()
                            ? event.messageEditorRequestResponse().get().requestResponse()
                            : event.selectedRequestResponses().get(0);

                    panel.setRequestResponse(requestResponse);
                });

                return List.of(menuItem);
            }
        });

        montoyaApi.userInterface().registerSuiteTab(EXTENSION_NAME, panel.getPanel());
    }
}
