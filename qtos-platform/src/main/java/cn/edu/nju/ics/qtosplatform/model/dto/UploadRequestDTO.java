package cn.edu.nju.ics.qtosplatform.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadRequestDTO {
    private String serviceName;
    private Long projectId;
    private Long machineId;
    private String[] dependentTaskIds;
    private MultipartFile file;
}
