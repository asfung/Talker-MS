package com.paung.client;

import com.paung.dto.MediaDTO;
import com.paung.response.MediaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "storage-service", url = "${application.config.storage-url}")
public interface StorageClient {
  @PostMapping("/upload")
//  @RequestHeader("Authorization")
  MediaResponse uploadFile(@RequestHeader("Authorization") String token,
                           @RequestParam("talk_id") String talk_id,
                           @RequestPart("file") MultipartFile file
                                );

  @GetMapping("/post/media/{talkId}")
  MediaDTO getMediaByTalkId(
          @RequestHeader(value = "Authorization", required = false) String token, // sementara doang
          @PathVariable String talkId
  );

}
