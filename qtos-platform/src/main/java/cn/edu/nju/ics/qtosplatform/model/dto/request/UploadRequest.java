package cn.edu.nju.ics.qtosplatform.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UploadRequest(@NotBlank String serviceName,
                            @NotNull Long projectId,
                            @NotNull Long machineId,
                            List<String> dependentTaskIds,
                            @NotNull MultipartFile file) {
}
