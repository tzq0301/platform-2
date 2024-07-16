package cn.edu.nju.ics.qtosplatform.model.command;

import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.MachineId;
import cn.edu.nju.ics.qtosplatform.domain.valueobject.ProjectId;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UploadCommand(String serviceName,
                            ProjectId projectId,
                            MachineId machineId,
                            List<DeployTaskId> dependentTaskIds,
                            MultipartFile file) {
}