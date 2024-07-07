package cn.edu.nju.ics.qtosplatform.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class UploadCommand {
    private String serviceName;
    private Long projectId;
    private Long machineId;
    private String[] dependentTaskIds;
    private MultipartFile archived;
}
