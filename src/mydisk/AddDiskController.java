/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisk;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import mydisk.model.Disk;
import mydisk.service.DiskService;
import mydisk.util.DiskUtil;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class AddDiskController implements Initializable {
   private ListView list;
   private GridPane welcome;
   private StackPane spContent;
   private boolean isLogin=false;
   private String type="baidu";
   private ResourceBundle i18nBundle;
   
   @FXML
   private Label lbDiskInfo;
   @FXML
   private ImageView imgDiskInfo;
   
   @FXML
   private TextField userName;
   
   @FXML 
   private PasswordField password;
   
   @FXML
   private Hyperlink link;
   
   @FXML
   private Label errMsg;
   
   @FXML
   private Label Hyperlink;
   
   @FXML
   private CheckBox autologin;
   @FXML
   private GridPane gpAddDisk;
   @FXML
   private Button btYes;
   
   @FXML
   public void addDisk(ActionEvent event){
         if(DiskUtil.isBlank(userName.getText())){
            errMsg.setText("user name can not be null");
            return;
         }
         if(autologin.isSelected()){
             if(DiskUtil.isBlank(password.getText())){
                 errMsg.setText("password can not be null");
                 return;
             }
         }
         ObservableList<Disk> data=list.getItems();
         for(Disk d:data){
            if(d.getName().equals(userName.getText()) && d.getType().equals(type)){
                 errMsg.setText("the same account is in the list");
                 return;
            }
         }
         Disk disk=new Disk(userName.getText(),"baidu");
         data.add(disk);
         errMsg.setText(null);
         DiskService.writeDiskAcount(list);
   }
   
   @FXML
   public void canel(ActionEvent event){
       this.gpAddDisk.setVisible(false);
       this.welcome.setVisible(true);     
   }
   
   @FXML
   public void linkToReg(ActionEvent event){
   
   }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        i18nBundle=rb;       
    }    

    
    void setList(ListView listDisk, GridPane welcome, StackPane spContent) {
       this.list=listDisk;
       this.welcome=welcome;
       this.spContent=spContent;
    }
    
    public void initContent(boolean isLogin,String type){
        if(isLogin){
             btYes.setText(i18nBundle.getString("button.text.login"));
        }
        
    }
}
