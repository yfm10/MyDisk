/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisk;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mydisk.controls.CardPane;
import mydisk.model.Disk;
import mydisk.service.DiskService;

/**
 *
 * @author Administrator
 */
public class MyDiskController implements Initializable {
  
    private MyDisk application;
    private Stage stage;
    private boolean maximized=false;
    private double mouseDragOffsetX = 0;
    private double mouseDragOffsetY = 0;
    private Rectangle2D backupWindowBounds = null;
    private DiskService diskService;
    
    @FXML 
    private ToolBar tbMain;
    @FXML
    private ImageView imgCurDiskType;
    @FXML
    private VBox vbDiskInfo;
    @FXML
    private Label lbUserName;   
    @FXML
    private ProgressBar pbUsage;
    @FXML
    private Button btExit;
    @FXML 
    private Button btMin;
    @FXML
    private Button btMax;
    @FXML 
    private Button btSkin;
    @FXML
    private Region blankRg;
    @FXML 
    private CardPane spContent;//maindiskcontent
    @FXML 
    private GridPane paneWelcome;
    @FXML
    private Region windowResizeButton;
    @FXML
    private MenuItem mnAddDiskBaidu;
    @FXML
    private ListView listDisk;
    @FXML
    private Accordion  adLeft;
     
    @FXML
    private void exit(ActionEvent event) {
          Platform.exit();
    }
    @FXML
    private void min(ActionEvent event) {
         stage.setIconified(true);
    }
    @FXML
    private void toogleMaximized(){
        final Screen screen = Screen.getScreensForRectangle(stage.getX(), stage.getY(), 1, 1).get(0);
        if (maximized) {
            maximized = false;
            if (backupWindowBounds != null) {
                stage.setX(backupWindowBounds.getMinX());
                stage.setY(backupWindowBounds.getMinY());
                stage.setWidth(backupWindowBounds.getWidth());
                stage.setHeight(backupWindowBounds.getHeight());
            }
            if(btMax.getStyleClass().contains("window-max"))
                btMax.getStyleClass().remove("window-max");
            btMax.getStyleClass().add("window-min");
        } else {
            maximized = true;
            backupWindowBounds = new Rectangle2D(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
            stage.setX(screen.getVisualBounds().getMinX());
            stage.setY(screen.getVisualBounds().getMinY());
            stage.setWidth(screen.getVisualBounds().getWidth());
            stage.setHeight(screen.getVisualBounds().getHeight());
            if(btMax.getStyleClass().contains("window-min"))
                btMax.getStyleClass().remove("window-min");
            btMax.getStyleClass().add("window-max");
        }
    }
    // add window dragging
    @FXML
    private void tbMainOnMousePressed(MouseEvent event) {
        mouseDragOffsetX = event.getSceneX();
        mouseDragOffsetY = event.getSceneY();
    }
    @FXML
    private void btMainOnMouseDragged(MouseEvent event) {
        if(!maximized) {
            stage.setX(event.getScreenX()-mouseDragOffsetX);
            stage.setY(event.getScreenY()-mouseDragOffsetY);
        }
    }
    @FXML
    private void btMainOnMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            toogleMaximized();
        }
    }
    
    @FXML
    private void addDisk(ActionEvent event) throws Exception{
       AddDiskController controller=(AddDiskController)application.addPane("AddDisk.fxml", spContent);
       controller.setList(listDisk,paneWelcome,spContent);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         HBox.setHgrow(blankRg, Priority.ALWAYS);
         imgCurDiskType.setVisible(false);
         vbDiskInfo.setVisible(false);
         mnAddDiskBaidu.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("images/baidu-logo.png"))));
         SplitPane.setResizableWithParent(adLeft, false);
         diskService=new DiskService(); 
         diskService.initDiskList(listDisk);
         //initSelcetion
         listDisk.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Disk>() {
                public void changed(ObservableValue<? extends Disk> ov,Disk old_val, Disk new_val) {
                    showDisk(new_val);
                    System.out.println(new_val.getName());
            }
          });   
    }    
    
    private void showDisk(Disk disk){
        try {
            DiskController controller=(DiskController)application.addPane("Disk.fxml", spContent);
        } catch (Exception ex) {
            Logger.getLogger(MyDiskController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public void setApp(MyDisk application,Stage stage){
        this.application = application;
        this.stage=stage;
    }
   
}
