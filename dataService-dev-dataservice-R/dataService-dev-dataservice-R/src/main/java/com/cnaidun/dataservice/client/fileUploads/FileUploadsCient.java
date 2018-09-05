package com.cnaidun.dataservice.client.fileUploads;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("file-uploads-api")
public interface FileUploadsCient {
    /**
     * 文件转base64下载
     */
    @RequestMapping(value = "/fileUploads/downloadBase64")
    public Map fileDownloadBase64(@RequestParam("fileId") String fileId) throws Exception;
}
