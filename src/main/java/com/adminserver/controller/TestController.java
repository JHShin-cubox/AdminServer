package com.adminserver.controller;

import com.adminserver.service.RecordManagementService;
import com.adminserver.service.SchedulerService;
import com.adminserver.service.TestService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@RequestMapping("test")
public class TestController {
    private final TestService testService;
    private final RecordManagementService recordManagementService;
    private final SchedulerService schedulerService;

    @GetMapping("she")
    @ResponseBody
    public void testSchedule(){
        schedulerService.InsertDailyStatistic();
    }

    @GetMapping("swing")
    @ResponseBody
    public void ImageBoundingBox() throws Exception {
        StopWatch stopWatch = new StopWatch(); //메소드 작동 시간
        stopWatch.start(); //메소드 시작 시간
        //Swing 라이브러리 시작
        SwingUtilities.invokeLater(() -> {
            String folderPath = "C:/project/json"; // json데이터 및 원본사진 폴더
            String outputPath = "C:/project/bbox"; // 작업 완료 폴더
            JSONParser parser = new JSONParser();
            Path dirPath = Paths.get(folderPath);
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.png")) {
                for (Path filePath : stream) {
                    String fileName = filePath.getFileName().toString();
                    String jsonFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".json";
                    Path jsonFilePath = dirPath.resolve(jsonFileName);
                    if(recordManagementService.duplicateCheck(fileName)==null){
                        if (Files.exists(jsonFilePath)) {
                            try (FileReader reader = new FileReader(jsonFilePath.toFile())) {
                                Integer topLabelNum = 0;
                                JSONObject json = (JSONObject) parser.parse(reader);
                                JSONArray dataArray = (JSONArray) json.get("data");

                                BufferedImage image = ImageIO.read(filePath.toFile());

                                Graphics2D g2d = image.createGraphics();
                                g2d.setFont(new Font("Arial", Font.PLAIN, 20));

                                for (int i = 0; i < dataArray.size(); i++) {
                                    JSONObject element = (JSONObject) dataArray.get(i);
                                    JSONArray bbox = (JSONArray) element.get("bbox");
                                    Long labelId = (Long) element.get("label_id");
                                    String label = recordManagementService.getLabelName(labelId);
                                    if(label==null){
                                        topLabelNum ++;
                                    }
                                    if(label!=null){

                                        int x1 = Math.toIntExact((long) bbox.get(0));
                                        int y1 = Math.toIntExact((long) bbox.get(1));
                                        int x2 = Math.toIntExact((long) bbox.get(2));
                                        int y2 = Math.toIntExact((long) bbox.get(3));

                                        Random rand = new Random();
                                        int r = rand.nextInt(256);
                                        int g = rand.nextInt(256);
                                        int b = rand.nextInt(256);
                                        Color randomColor = new Color(r, g, b);

                                        g2d.setColor(randomColor);
                                        g2d.setStroke(new BasicStroke(2));
                                        Font font = new Font("NanumGothic", Font.PLAIN, 20);
                                        g2d.setFont(font);
                                        g2d.drawRect(x1, y1, x2 - x1, y2 - y1);

                                        g2d.setColor(randomColor);
                                        g2d.drawString(label, 0, 30 + (i-topLabelNum) * 30);
                                    }
                                }
                                g2d.dispose(); // 그래픽 컨텍스트 해제

                                String outputImagePath = outputPath + "/" + fileName;
                                ImageIO.write(image, "png", new File(outputImagePath));
                                String lId = fileName.substring(0,19);
//                                recordManagementService.createImage(fileName, lId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
