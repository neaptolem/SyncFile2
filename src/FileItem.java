import java.util.Date;
import java.util.Objects;

public class FileItem {
    private String data;
    private Date time;

    public FileItem(String data, Date time) {
        this.data = data;
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return this.data + "\n" + this.time.getTime();
    }

    public boolean equals(FileItem fileItem) {
        if (fileItem == null) {
            return false;
        }
        if (Objects.equals(fileItem.getData(),this.data)&&Objects.equals(fileItem.getTime(),this.time))
        return true;
        return false;
    }
}
