package org.example;

public class TGPCT {
    public TGPCT() {

    }

    public static void main(String[] args) {
        LC ballController = new LC("ohhh yeahh", new TGPCT());
        new Thread(ballController.view).start();
    }
}
