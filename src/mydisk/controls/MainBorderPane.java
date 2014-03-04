/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisk.controls;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author Administrator
 */
public class MainBorderPane extends BorderPane {

    private WindowResizeButton windowResizeButton;

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        if (null != windowResizeButton) {
            windowResizeButton.autosize();
            windowResizeButton.setLayoutX(getWidth() - windowResizeButton.getLayoutBounds().getWidth());
            windowResizeButton.setLayoutY(getHeight() - windowResizeButton.getLayoutBounds().getHeight());
        }
    }

    public void setWindowResizeButton(WindowResizeButton button) {
        windowResizeButton = button;
    }
}
