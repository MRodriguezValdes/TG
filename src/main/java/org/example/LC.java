package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;

public class LC extends JFrame implements ActionListener, EventListener {

    TGPCT generalController;

    LV view;

    LM model;

    JToggleButton jbPlay;

    public LC(String title, TGPCT generalController) throws HeadlessException {
        super(title);
        this.generalController = generalController;
        this.jbPlay = new JToggleButton("Play");
        this.jbPlay.setSelected(true);
        this.model = new LM(this);
        this.view = new LV(this);
        this.configuringUI();
    }

    public void collideManagement(ArrayList<VO> objectsCollide){
        TGR.takeDecision(objectsCollide,this);
    }
    public void addBall(int x ,int y){
        this.model.addBall(x,y);
    }

    private void configuringUI() {
        this.setLayout(new GridBagLayout());
        this.setSize(500, 500);

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(10, 10, 10, 10);

        this.add(jbPlay, c);
        c.gridy++;
        c.weighty++;
        this.add(this.view, c);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
