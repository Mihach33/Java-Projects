package zad1;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiManager extends JFrame{
    private JPanel myApp;
    private JTextField country;
    private JLabel specLabel1;
    private JButton confirmButton;
    private JTextField city;
    private JButton kursPLNButton;
    private JButton kursButton;
    private JTextField currencyTextField;
    private JButton clickToSeeWikiButton;
    private JFXPanel jfxPanel;
    WebView webView;

    public GuiManager(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1000,760);
        this.setContentPane(myApp);


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String a=country.getText();
                String a1= city.getText();
                Service service=new Service(a);
                specLabel1.setText(service.getWeather(a,a1,service.getWeather(a1)));
            }
        });
        kursPLNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String a=country.getText();
                String a2=currencyTextField.getText();
                Service service=new Service(a);
                specLabel1.setText("Kurs "+service.getCurrency(a) +" wobec "+a2+" "+String.valueOf(service.getRateFor(a2)));
            }
        });
        kursButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String a=country.getText();
                Service service=new Service(a);
                specLabel1.setText("Kurs zlotego wobec waluty "+a+" "+service.getNBPRate());
            }
        });

        clickToSeeWikiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jfxPanel=new JFXPanel();
                Platform.runLater(this::createJFX);
                Button jButton=new Button("Click to close");
                jButton.setBounds(0,0,100,100);
                jfxPanel.add(jButton);
                GuiManager.super.setContentPane(jfxPanel);
                GuiManager.super.setVisible(true);
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        GuiManager.super.setContentPane(myApp);
                        GuiManager.super.setVisible(true);
                        Platform.runLater(()->{
                            jfxPanel=new JFXPanel();
                            webView=new WebView();
                            webView.getEngine().load("https://en.wikipedia.org/wiki/"+city.getText());
                            Scene scene = new Scene(webView);
                            jfxPanel.setScene(scene);

                        });
                    }

                });

            }

            private void createJFX() {
                webView = new WebView();
                webView.getEngine().load("https://en.wikipedia.org/wiki/"+city.getText());
                Scene scene = new Scene(webView);
                jfxPanel.setScene(scene);
            }

        });
    }


}
