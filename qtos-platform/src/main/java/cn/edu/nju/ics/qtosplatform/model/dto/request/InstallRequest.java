package cn.edu.nju.ics.qtosplatform.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record InstallRequest(@NotBlank String taskId) {
}
