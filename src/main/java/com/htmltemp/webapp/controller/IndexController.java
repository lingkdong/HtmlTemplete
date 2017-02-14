package com.htmltemp.webapp.controller;
import javax.servlet.http.HttpServletRequest;

import com.htmltemp.webapp.dto.Directory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:lk
 * Date: 16-5-20
 * Time: 下午1:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController {
    private static final String DOCUMENT="document";
    private static Log log= LogFactory.getLog(IndexController.class);
    @RequestMapping("/index")
    public String index(HttpServletRequest request){
       String filePath=System.getProperty("htmltemp.root")+ File.separator+DOCUMENT+ File.separator+"index.csv";
        try {
            Reader in=new FileReader(filePath) ;
            Iterable<CSVRecord> csvRecords= CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            List<Directory> records=new ArrayList<Directory>();
            for (CSVRecord record : csvRecords) {
                String name = record.get("name");
                String url = record.get("url");
                String icon = record.get("icon");
                Directory directoyDto=new Directory(name,url,icon);
                records.add(directoyDto);
            }
            request.setAttribute("records",records);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }


}
