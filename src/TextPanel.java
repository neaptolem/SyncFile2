import javafx.scene.control.TextArea;


public class TextPanel extends TextArea implements ChangeHandler  {
    @Override
    public void fileChange(CustomFile customFile) {
        this.setText(customFile.toPrint());
    }
}
