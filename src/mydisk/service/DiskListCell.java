/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisk.service;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import mydisk.model.Disk;
import mydisk.util.DiskUtil;

/**
 *
 * @author Administrator
 */
public class DiskListCell extends ListCell<Disk>{
      @Override
       public void updateItem(Disk item, boolean empty) {
            super.updateItem(item, empty);
             if (null != item) {
                HBox hbox = new HBox(8); // spacing = 8
                hbox.setPrefSize(100, 20);
                Label lb=new Label(item.getName());
                lb.setPrefHeight(20);
                lb.setContentDisplay(ContentDisplay.CENTER);
                lb.setAlignment(Pos.CENTER_LEFT);
                hbox.getChildren().addAll(DiskUtil.getImageView(item.getType()),lb);
                setGraphic(hbox);
            }
        }
}
