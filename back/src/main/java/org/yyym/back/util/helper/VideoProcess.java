package org.yyym.back.util.helper;

import org.apache.poi.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideoProcess {
    private final static String[] toTs =
            {"ffmpeg -y -i \"", "", "\" -vcodec copy -acodec copy -bsf:v h264_mp4toannexb \"", "", "\""};
    private final static String[] toM3U8 =
            {"ffmpeg -i \"", "", "\" -c copy -map 0 -f segment -segment_list \"", "", "\" -segment_time 10 \"", "", "\""};
    private final static String saveBasePath = "E:/sources/cartoon_online/video/";
    private final static String temBasePath = "E:/sources/cartoon_online/tem/";

    public static void changeToTsFile(String oriFileName, String savePath) {
        String[] tem = toTs;
        tem[1] = oriFileName; tem[3] = savePath;
        String commend = StringUtil.join(tem);
        executeCommand(commend);
    }

    public static void changeToM3U8(String oriTsFile, String finalFile) {
        String[] tem = toM3U8;
        tem[1] = oriTsFile; tem[3] = finalFile + ".m3u8"; tem[5] = finalFile + "_%3d.ts";
        String commend = StringUtil.join(tem);
        executeCommand(commend);
    }

    public static void process(String cartoonId, Integer num, String oriName, String newName) {
        File savePath = new File(saveBasePath + cartoonId + "/" + num + "/");
        File oriFile = new File(temBasePath + oriName);
        if(!savePath.exists())
            savePath.mkdir();
        File tsFile = new File(savePath.getAbsolutePath() + "/" + newName + ".ts");
        changeToTsFile(oriFile.getAbsolutePath(), tsFile.getAbsolutePath());
        changeToM3U8(tsFile.getAbsolutePath(),
                savePath.getAbsolutePath() + "/" + newName);
        tsFile.delete();
    }

    private static void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        process("0bdaac4", 13 , "0f46f33ed65641cf878a9cf8a14aae1f.mp4", "---sss");
    }
}
