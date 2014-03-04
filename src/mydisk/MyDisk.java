/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisk;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.DepthTest;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import mydisk.controls.CardPane;
import mydisk.controls.MainBorderPane;
import mydisk.controls.WindowResizeButton;


    /**
 *
 * @author Administrator
 */
public class MyDisk extends Application {
    private StackPane modalDimmer;
    private ResourceBundle i18nBundle;
    @Override
    public void start(Stage stage) throws Exception {
         // This line to resolve keys against Bundle.properties
        i18nBundle = ResourceBundle.getBundle("mydisk.i18n.mydisk", new Locale("cn", "ZH"));
        StackPane layerPane = new StackPane();
        
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MyDisk.class.getResourceAsStream("MyDisk.fxml");
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MyDisk.class.getResource("MyDisk.fxml"));
        loader.setResources(i18nBundle);
        MainBorderPane root = (MainBorderPane) loader.load(in);
        ((MyDiskController) loader.getController()).setApp(this,stage);
        
        //remove default button
        stage.initStyle(StageStyle.UNDECORATED);
        //resize button
        WindowResizeButton resizeButton=new WindowResizeButton(stage, 800,600);
        root.setWindowResizeButton(resizeButton);
        resizeButton.setManaged(false);
        root.getChildren().add(resizeButton);
        
        layerPane.setDepthTest(DepthTest.DISABLE);
        layerPane.getChildren().add(root);
         // create scene
        boolean is3dSupported = Platform.isSupported(ConditionalFeature.SCENE3D);
        Scene scene = new Scene(layerPane, 860, 600, is3dSupported);
        scene.getStylesheets().add(MyDisk.class.getResource("mydisk.css").toExternalForm());
        if (is3dSupported) {
            //RT-13234
            scene.setCamera(new PerspectiveCamera());
        }
         // create modal dimmer, to dim screen when showing modal dialogs
        modalDimmer = new StackPane();
        modalDimmer.setId("ModalDimmer");
        modalDimmer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                t.consume();
                hideModalMessage();
            }
        });
        modalDimmer.setVisible(false);
        layerPane.getChildren().add(modalDimmer);
        
        stage.setScene(scene);
        stage.setTitle(i18nBundle.getString("application.title"));
        stage.show();
    }
/**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
     /**
     * Hide any modal message that is shown
     */
    public void hideModalMessage() {
        modalDimmer.setCache(true);
        TimelineBuilder.create().keyFrames(
            new KeyFrame(Duration.seconds(1), 
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        modalDimmer.setCache(false);
                        modalDimmer.setVisible(false);
                        modalDimmer.getChildren().clear();
                    }
                },
                new KeyValue(modalDimmer.opacityProperty(),0, Interpolator.EASE_BOTH)
        )).build().play();
    }
    
    public Initializable addPane(String fxml,CardPane pane) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MyDisk.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MyDisk.class.getResource(fxml));
        loader.setResources(i18nBundle);
        try {
            Pane p=(Pane)loader.load(in);
            pane.getChildren().add(p);
            pane.setActiveChild(p);
        } finally {
            in.close();
        } 
        return (Initializable) loader.getController();
    }
}