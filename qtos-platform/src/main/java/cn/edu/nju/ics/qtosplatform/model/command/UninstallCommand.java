package cn.edu.nju.ics.qtosplatform.model.command;

import cn.edu.nju.ics.qtosplatform.domain.valueobject.DeployTaskId;

public record UninstallCommand(DeployTaskId taskId) {
}
