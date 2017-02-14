package testmothed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DT254 on 2017/2/14.
 */
public class FileDto {
    private Long id;
    private String fileName;
    private String filePath;
    private String icon;
    private String type;
    private int depth;
    private List<FileDto> children;

    public FileDto() {
    }

    public FileDto(Long id, String fileName, String filePath, String icon, String type, int depth) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.icon = icon;
        this.type = type;
        this.depth = depth;
    }

    public FileDto(String fileName, String filePath, String icon, String type, int depth) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.icon = icon;
        this.type = type;
        this.depth = depth;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public List<FileDto> getChildren() {
        return children;
    }

    public void setChildren(List<FileDto> children) {
        this.children = children;
    }

    public void addChild(FileDto dto){
        if(children==null){
            children=new ArrayList<FileDto>();
        }
        children.add(dto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
