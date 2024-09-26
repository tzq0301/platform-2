package cn.edu.nju.ics.qtosbase.interfaces.gui;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class SwingInterface {
    private final String applicationName;

    private final boolean enableUI;

    private JFrame frame;

    public SwingInterface(@Value("${spring.application.name}") String applicationName,
                          @Value("${qtos.base.ui}") String ui) {
        this.applicationName = applicationName;
        this.enableUI = "enable".equals(ui);
    }

    public void create() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame(applicationName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 200, 140);

        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);
    }

    public void show() {
        frame.setVisible(true);
    }

    @PostConstruct
    public void init() {
        if (!enableUI) {
            return;
        }
        create();
        show();
    }
}
