package cn.edu.nju.ics.qtosbase.service;

import org.springframework.lang.NonNull;

import java.io.IOException;
import java.io.InputStream;

public interface DeployService {
    /**
     * 将压缩包解压到指定目录下
     *
     * @param archived 压缩包
     */
    void transport(@NonNull String taskId, @NonNull InputStream archived) throws IOException;

    void install(@NonNull String taskId) throws IOException, InterruptedException;

    void uninstall(@NonNull String taskId) throws IOException, InterruptedException;
}
