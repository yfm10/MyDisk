/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisk.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import mydisk.model.Disk;
import mydisk.util.DiskUtil;

/**
 *
 * @author Administrator
 */
public class DiskService {
    
     /**
     * init Disk ListView
     */
    public  void initDiskList(final ListView list) {             
        list.setCellFactory(new Callback<ListView<Disk>, ListCell<Disk>>() {
            @Override
            public ListCell<Disk> call(ListView<Disk> list) {
                return new DiskListCell();
            }
        });
        Task task=new Task<Void>() {
            @Override 
            public Void call() {
                ObservableList<Disk> data =readDiskCount();
                list.setItems(data);
                return null;
            }
        };
        new Thread(task).start();
    }
    
    /**
     * get user dick count
     * @return 
     */
    private  ObservableList<Disk> readDiskCount(){
        ObservableList<Disk> data = FXCollections.observableArrayList();
        try {           
            JsonFactory factory = new JsonFactory();   
            JsonParser  parser=factory.createParser(ClassLoader.getSystemResourceAsStream("disk.json"));
            parser.nextToken(); 
            ObjectMapper mapper = new ObjectMapper();
            while(parser.nextToken() == JsonToken.START_OBJECT){
               Disk d=mapper.readValue(parser, Disk.class);
               data.add(d);
            }
            parser.close();
        } catch (Exception ex) {
            Logger.getLogger(DiskUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public  static void writeDiskAcount(final ListView list){
       Task task=new Task<Void>() {
            @Override 
            public Void call() {
                ObservableList<Disk> data = list.getItems();
                ObjectMapper mapper = new ObjectMapper();
                try {
                    URL url=ClassLoader.getSystemResource("disk.json");
                    System.out.printf(url.getFile());
                    mapper.writeValue(new File(url.getFile()), data);
                } catch (Exception ex) {
                    Logger.getLogger(DiskService.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    return null;
                }
        };
        new Thread(task).start();
    }
}
