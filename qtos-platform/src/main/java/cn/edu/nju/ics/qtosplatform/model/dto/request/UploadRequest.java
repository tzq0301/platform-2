package cn.edu.nju.ics.qtosplatform.model.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadRequest {
    private String serviceName;
    private Long projectId;
    private Long machineId;
    private String[] dependentTaskIds;
    private MultipartFile file;
}
