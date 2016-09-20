import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application{
    private TextPanel textArea= new TextPanel();
    private VBox vbox=new VBox(textArea);
    private Button btn=new Button();
    private Scene scene = new Scene(vbox, 300, 250);
    private FileController fileController=new FileController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("fileChanger");
        btn.setText("Save");
        vbox.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();

        fileController.start();
        fileController.addListener(textArea);
        for (int i=0; i<2; i++){
            Test test=new Test(fileController, "TestThread"+i);
            test.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                fileController.viewChange(textArea.getText());
            }
        });
    }
}
