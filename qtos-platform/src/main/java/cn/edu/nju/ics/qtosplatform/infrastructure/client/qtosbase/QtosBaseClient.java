package cn.edu.nju.ics.qtosplatform.infrastructure.client.qtosbase;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface QtosBaseClient {
    void upload(String taskId, MultipartFile archived) throws IOException;

    void install(Map<String, Object> params);

    void uninstall(Map<String, Object> params);
}
