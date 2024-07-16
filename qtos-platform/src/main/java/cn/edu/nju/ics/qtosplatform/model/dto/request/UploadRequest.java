package cn.edu.nju.ics.qtosplatform.model.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UploadRequest(String serviceName,
                            Long projectId,
                            Long machineId,
                            List<String> dependentTaskIds,
                            MultipartFile file) {
}
