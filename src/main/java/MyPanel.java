import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.UserInterface;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;

import javax.swing.*;
import java.awt.*;

public class MyPanel
{
    private final JPanel panel;
    private final HttpRequestEditor httpRequestEditor;
    private final HttpResponseEditor httpResponseEditor;

    public MyPanel(UserInterface userInterface)
    {
        LayoutManager layoutManager = new GridLayout(0,2);

        panel = new JPanel(layoutManager);

        httpRequestEditor = userInterface.createHttpRequestEditor();
        httpResponseEditor = userInterface.createHttpResponseEditor();

        panel.add(httpRequestEditor.uiComponent());
        panel.add(httpResponseEditor.uiComponent());
    }

    public void setRequestResponse(HttpRequestResponse requestResponse)
    {
        httpRequestEditor.setRequest(requestResponse.request());
        httpResponseEditor.setResponse(requestResponse.hasResponse() ? requestResponse.response() : null);
    }

    public Component getPanel()
    {
        return panel;
    }
}
