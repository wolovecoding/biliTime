//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            while(true) {
                System.out.println("请输入uid号");
                String uid = scanner.next();
                biliInfo biliInfo = new biliInfo(uid);
               // scanner.close();
                System.out.println("按1选择查看视频总时间     按2选择查看已看时间     按3选择查看还未观看时常");
                System.out.println("按-1重新选择       按0退出");

                for(int i = 0; i != -1; ) {
                    System.out.println("输入要进行的操作");
                    i = scanner.nextInt();
                    if (i == 1) {
                        biliInfo.getAllTime();
                    }

                    int chapter;
                    if (i == 2) {
                        System.out.println("你看到第几章了？");
                        chapter = scanner.nextInt();
                        biliInfo.getWatchedTime(chapter);
                    }

                    if (i == 3) {
                        System.out.println("你看到第几章了？");
                        chapter = scanner.nextInt();
                        biliInfo.getLastTime(chapter);
                    }

                    if (i == -1) {
                        scanner.close();
                        break;
                    }

                    if (i == 0) {
                        scanner.close();
                        System.exit(0);
                    }
                }
            }
        }
    }
}
