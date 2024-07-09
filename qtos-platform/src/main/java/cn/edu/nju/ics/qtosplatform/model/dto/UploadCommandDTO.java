package cn.edu.nju.ics.qtosplatform.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class UploadCommandDTO {
    private String serviceName;
    private Long projectId;
    private Long machineId;
    private String[] dependentTaskIds;
    private MultipartFile archived;
}
