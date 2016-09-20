import java.util.Date;
public class Test extends Thread {
    private String name;
    private FileController fileController;
    private CustomFile customFile = generateCustomFile(1);

    public Test(FileController fileController, String name) {
        this.fileController = fileController;
        this.name = name;
    }

    public void run() {
        fileController.changeFile(customFile);
        runTest("Test1");
        sleep(2000);

        fileController.changeFile(customFile);
        runTest("Test2");
        sleep(2000);

        customFile=fileController.getActive();
        setFileItem(customFile, 1);
        runTest("Test3");
        sleep(2000);


    }

    private void setFileItem(CustomFile customFile, int index) {
        FileItem fileItem = new FileItem("smth", new Date());
        customFile.setData(fileItem, index);
        fileController.changeFileItem(fileItem, index);
    }

    private void runTest(String nameTest) {
        System.out.println(name+"::"+nameTest+"\n" + customFile.equals(fileController.getActive()));
//        System.out.println(customFile.toPrint()+"    "+fileController.getActive().toPrint());
    }

    private void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private CustomFile generateCustomFile(int s) {
        CustomFile customFile = new CustomFile();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                customFile.addData(new FileItem(s + " " + i + " " + j, new Date()));
            }
        }
        return customFile;
    }
}
