package cn.edu.nju.ics.qtosplatform.infrastructure.qtosbase;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface QtosBaseClient {
    String upload(@RequestParam("file") MultipartFile archived) throws IOException;

    void install(@RequestBody Map<String, Object> params);
}
