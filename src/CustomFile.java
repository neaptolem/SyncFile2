import java.util.ArrayList;
import java.util.Date;

public class CustomFile {
    public ArrayList<FileItem> data = new ArrayList<FileItem>();

    public void addData(FileItem fileItem) {
        data.add(fileItem);
    }
    public ArrayList<FileItem> getData(){
        return this.data;
    }
    public CustomFile(CustomFile customFile){
        ArrayList<FileItem> fileItems=new ArrayList<FileItem>();
        ArrayList<FileItem> customFileData=customFile.getData();
        for (int i=0;i<customFileData.size();i++){
            fileItems.add(new FileItem(customFileData.get(i).getData(),customFileData.get(i).getTime()));
        }
        this.data=fileItems;
    }
    public CustomFile(){};

    @Override
    public String toString() {
        String s = new String();
        for (FileItem item : data) {
            s += item.toString() + "\n";
        }
        return s;
    }
    public void setData(FileItem fileItem, int index){
        data.set(index,fileItem);
    }
    public String toPrint() {
        String s = new String();
        for (FileItem item : data) {
            s += item.getData() + "\n" + item.getTime() + "\n";
        }
        return s;
    }
    public boolean equals(CustomFile customFile){
        ArrayList<FileItem> customFileData = customFile.getData();
        int size = Math.max(customFileData.size(), this.data.size());
        for (int i = 0; i < size; i++) {
            if (!(this.data.get(i)).equals(customFileData.get(i))) {
                return false;
            }
        }
        return true;
    }
}
