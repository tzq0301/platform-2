package cn.edu.nju.ics.qtosbase.interfaces.gui;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class SwingInterface {
    private final String applicationName;

    private final boolean enableUI;

    private final int serverPort;

    private JFrame frame;

    public SwingInterface(@Value("${spring.application.name}") String applicationName,
                          @Value("${qtos.base.ui}") String ui,
                          @Value("${server.port}") int serverPort) {
        this.applicationName = applicationName;
        this.enableUI = "enable".equals(ui);
        this.serverPort = serverPort;
    }

    public void create() throws UnknownHostException {
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame(applicationName);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 300, 140);

        frame.add(new JLabel("hostname: %s".formatted(InetAddress.getLocalHost().getHostName())));
        frame.add(new JLabel("port: %d".formatted(serverPort)));
        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        frame.add(new JLabel("qtos-base server started successfully!"));
    }

    public void show() {
        frame.setVisible(true);
    }

    @PostConstruct
    public void init() throws UnknownHostException {
        if (!enableUI) {
            return;
        }
        create();
        show();
    }
}
