/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisk.controls;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Administrator
 */
public class CardPane extends StackPane {
    
    public void setActiveChild(int index){
        Node active=this.getActiveChild();
        active.setVisible(false);
        this.getChildren().get(index).setVisible(true);
    }
    
    public void setActiveChild(Node node){
        Node active=this.getActiveChild();
        if(active.getId().equals(node.getId())){
            return;
        }
        active.setVisible(false);
        node.setVisible(true);
    }
    
    public int getActiveChildIndex(){
       ObservableList<Node> nodes=this.getChildren();
       if(null==nodes && nodes.size()==0){
           return -1;
       }
       for(int i=0;i<nodes.size();i++){
         Node n=nodes.get(i);
         if(n.isVisible()){
            return i;
         }
       }
       return -1;
    }
    
    public Node getActiveChild(){
       int index=this.getActiveChildIndex();
       if(-1!=index){
          return this.getChildren().get(index);
       }
       return null;
    }
    
}
