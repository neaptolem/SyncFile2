import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class FileController extends Thread {
    private ChangeHandler listener;
    private String fileName = "sync.txt";
    private CustomFile archive = new CustomFile();
    private CustomFile active = new CustomFile();
    private final Lock lock = new ReentrantLock();

    public void run() {
        System.out.println("FileController run");
        lock.lock();
        active = readFromFile();
        changeViewFile(active);
        archive = active;
        lock.unlock();
        for (; ; ) {
            lock.lock();
            active = readFromFile();
            if (! active.equals(archive)) {
               // System.out.println("changed");
                backup(active);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();

        }
    }
    public CustomFile getActive(){
        return this.active;
    }

    private void backup(CustomFile customFile) {
        lock.lock();
        writeToFile(customFile);
        archive = customFile;
        changeViewFile(customFile);
        lock.unlock();
    }

    private void writeToFile(CustomFile file) {
        lock.lock();

        try {

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
                writer.write(file.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    private CustomFile readFromFile() {
        String line, line1;
        CustomFile file = new CustomFile();
        try {
            lock.lock();
            File readFile=new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            if (line != null) {
                line1 = br.readLine();
                file.addData(new FileItem(line, new Date(Long.parseLong(line1))));

            } else {
                file.addData(new FileItem("0", new Date()));
                return file;
            }
            while ((line = br.readLine()) != null) {
                line1 = br.readLine();
                file.addData(new FileItem(line, new Date(Long.parseLong(line1))));
            }
            lock.unlock();

            return file;

        } catch (IOException e) {
            return archive;
        }
    }

    public void changeFile(CustomFile customFile) {
        lock.lock();
        if (! active.equals(customFile)) {
//            System.out.println("File changed");
            active = customFile;
            backup(customFile);
        }
        lock.unlock();
    }

    public void changeFileItem(FileItem fileItem, int index){
        lock.lock();
        CustomFile customFile=new CustomFile(active);
        customFile.setData(fileItem, index);
        changeFile(customFile);
        lock.unlock();
    }

    public void addListener(ChangeHandler toAdd) {
        listener = toAdd;
    }

    private void changeViewFile(CustomFile customFile) {
        listener.fileChange(customFile);
    }

}

