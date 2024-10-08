package com.paung.controller;


import com.paung.entity.Media;
import com.paung.entity.PhotoProfile;
import com.paung.enums.SupabaseConfig;
import com.paung.repository.MediaRepository;
import com.paung.repository.PhotoProfileRepository;
import com.paung.request.MediaEditRequest;
import com.paung.response.MediaResponse;
import com.paung.response.PhotoProfileResponse;
import com.paung.service.MediaService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1/storage")
public class MediaController {
  @Autowired
  private MediaService mediaService;
  @Autowired
  private MediaRepository mediaRepository;
  @Autowired
  private PhotoProfileRepository photoProfileRepository;


  @PostMapping("/upload")
  public ResponseEntity<MediaResponse> insertMedia(
          @RequestParam(value = "media_id", required = false) String media_id,
          @RequestParam(value = "key", required = false) String key,
          @RequestParam(value = "talk_id", required = false) String talk_id,
          @RequestParam("file") MultipartFile file
  ) throws IOException {
    MediaResponse mediaResponse = mediaService.uploadMedia(talk_id, file);
    return ResponseEntity.ok(mediaResponse);
  }

  @PostMapping("/edit")
  public ResponseEntity<Media> editToBeNewTalk(
          @RequestParam("id") String id,
          @RequestParam("talk_id") String talk_id
  ) {
    Media media = mediaService.editToBeNewTalk(id, talk_id);
    return ResponseEntity.ok(media);
  }

  @PostMapping("/edits")
  public ResponseEntity<List<Media>> editToBeNewTalks(@RequestBody MediaEditRequest mediaEditRequest) {
    List<Media> medias = mediaService.editToBeNewTalks(mediaEditRequest.getIds(), mediaEditRequest.getTalkId());
    return ResponseEntity.ok(medias);
  }


  @PostMapping("/profile/upload")
  public ResponseEntity<PhotoProfileResponse> uploadImagePhotoProfile(
          @RequestParam(value = "media_id", required = false) String media_id,
          @RequestParam(value = "key", required = false) String key,
          @RequestParam("user_id") String user_id,
          @RequestParam("file") MultipartFile file
  ) throws IOException {
    PhotoProfileResponse photoProfileResponse = mediaService.uploadMediaPhotoProfile(user_id, file);
    return ResponseEntity.ok(photoProfileResponse);
//    return ResponseEntity.ok(user_id.getClass().getName());
  }


  @PostMapping("/test/upload")
  public ResponseEntity<List> testMultiFile(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
    List<String> requests = new ArrayList<>();
    requests.add(request.getHeader("Authorization"));

    String message = "";
    try {
      List<String> fileNames = new ArrayList<>();
      List<MultipartFile> fileObject = new ArrayList<>();
      Arrays.asList(files).stream().forEach(file -> {
//        storageService.save(file);
        fileNames.add(file.getOriginalFilename());
        fileObject.add(file);
      });

//      message = "Uploaded the files successfully: " + fileNames;
      return ResponseEntity.status(HttpStatus.OK).body(fileNames);
    } catch (Exception e) {
//      message = "Fail to upload files!";
      List<String> error = new ArrayList<>();
      error.add(e.toString());
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(error);
    }
  }


  @GetMapping("/profile/media/{userId}")
  public HttpEntity<byte[]> getBinaryPhotoProfile(@PathVariable String userId) throws IOException {
    byte[] imageBytes = mediaService.getBinaryByUserId(userId);
    HttpHeaders headers = new HttpHeaders();
    PhotoProfile photoProfile = photoProfileRepository.findFirstByUser_id(userId).orElseThrow();
    String fileExtension = FilenameUtils.getExtension(photoProfile.getOriginal_name_file());
    MediaType mediaType = getMediaType(fileExtension);
    headers.setContentType(mediaType);
    headers.setContentDispositionFormData("inline", userId + "." + fileExtension);
    return new HttpEntity<>(imageBytes, headers);
  }

  @GetMapping("/profile/{userId}")
  public ResponseEntity<PhotoProfile> getPhotoProfile(@PathVariable String userId) throws IOException {
    PhotoProfile photoProfile = photoProfileRepository.findFirstByUser_id(userId).orElseThrow();
    return ResponseEntity.ok(photoProfile);
  }

  @GetMapping("/media")
  public ResponseEntity<?> getMediaByMediaId(@RequestParam("media_id") String mediaId, @RequestParam(value = "type", required = false, defaultValue = "json") String type) {
    ResponseEntity<?> response = mediaService.findByMediaId(mediaId, type);
    if (response != null) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable String id, @RequestParam(value = "type", required = false, defaultValue = "json") String type) throws IOException {
    ResponseEntity<?> response = mediaService.getById(id, type);
    if (response != null) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/media/{id}")
  public HttpEntity<byte[]> getById(@PathVariable String id) throws IOException {
    byte[] imageBytes = mediaService.getBinaryById(id);
    HttpHeaders headers = new HttpHeaders();
    Media media = mediaRepository.findById(id).orElseThrow();
    String fileExtension = FilenameUtils.getExtension(media.getOriginal_name_file());
    MediaType mediaType = getMediaType(fileExtension);
    headers.setContentType(mediaType);
    headers.setContentDispositionFormData("inline", id + "." + fileExtension);
    return new HttpEntity<>(imageBytes, headers);
  }


  @GetMapping("/media-id/{mediaId}")
  public Media getMediaByMediaId(@PathVariable String mediaId) {
    return mediaService.getByMediaId(mediaId);
  }

  private MediaType getMediaType(String fileExtension) {
    switch (fileExtension.toLowerCase()) {
      case "jpg":
      case "jpeg":
        return MediaType.IMAGE_JPEG;
      case "png":
        return MediaType.IMAGE_PNG;
      case "gif":
        return MediaType.IMAGE_GIF;
      default:
        return MediaType.APPLICATION_OCTET_STREAM;
    }
  }




  @GetMapping("/post/media/{talkId}")
  public List<Media> getMediaByPostId(@PathVariable String talkId){
    return mediaService.findMediaByTalk_Id(talkId);
  }




//  @PostMapping("/post")
//  public ResponseEntity<MediaResponse> insertMedia(
//          @RequestParam("media_id") String media_id,
//          @RequestParam("key") String key,
//          @RequestParam("post_id") String post_id,
//          @RequestParam("file") MultipartFile file
//  ) throws IOException {
//    Map<String, MediaType> mediaTypes = new HashMap<>();
//    mediaTypes.put("png", MediaType.get("image/png"));
//    mediaTypes.put("jpg", MediaType.get("image/jpeg"));
//    mediaTypes.put("jpeg", MediaType.get("image/jpeg"));
//    mediaTypes.put("mp4", MediaType.get("video/mp4"));
//
//    String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
//    MediaType mediaType = mediaTypes.get(fileExtension);
//
//    if (mediaType == null) {
//      throw new RuntimeException("Unsupported file type: " + fileExtension);
//    }
//
//
//    Media media = new Media();
//    MediaResponse mediaResponse = new MediaResponse();
//    String generatedValueMedia = post_id + "_" + System.currentTimeMillis() + new Random().nextInt(Integer.parseInt(post_id)) + "." + FilenameUtils.getExtension(file.getOriginalFilename());
//
//    OkHttpClient client = new OkHttpClient();
//    RequestBody requestBody = new MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//                    .addFormDataPart("file", file.getOriginalFilename(), RequestBody.create(file.getBytes(), mediaType))
//                            .build();
//
//    Request request = new Request.Builder()
////            .url("https://zchajthzhlhzctddfjaz.supabase.co/storage/v1/object/storage/" + generatedValueMedia)
//            .url(SUPABASE_URL + generatedValueMedia)
//            .header("Authorization", "Bearer " + TOKEN)
//            .post(requestBody)
//            .build();
//
//    Response response = client.newCall(request).execute();
//    if (!response.isSuccessful()) {
//      throw new RuntimeException("Error uploading file: " + response.code());
//    }
//
//    String responseBody = response.body().string();
//    ObjectMapper mapper = new ObjectMapper();
//    JsonNode jsonNode = mapper.readTree(responseBody);
//
//    String responseMedia_id = jsonNode.get("Id").asText();
//    String responseKey = jsonNode.get("Key").asText();
//
//
//    media.setId(UUID.randomUUID().toString());
//    media.setOrignal_name_file(file.getOriginalFilename());
//    media.setMedia_id(responseMedia_id);
//    media.setKey(responseKey); // natikey nya "https://zchajthzhlhzctddfjaz.supabase.co/storage/v1/object/storage/{GENERATED_VALUE_NAME}.jpg/png/mp4"
//    media.setPost_id(post_id);
//
//    mediaResponse.setMedia(media);
//    mediaResponse.setOriginalFileName(file.getOriginalFilename());
//    mediaResponse.setGenerated_name(generatedValueMedia);
//
//
//    return ResponseEntity.ok(mediaResponse);
//  }

  @GetMapping("/test")
  List<String> applicationConfig(){
    List<String> configs = new ArrayList<>();
    configs.add(String.valueOf(SupabaseConfig.SUPABASE_STORAGE_URL));
    configs.add(String.valueOf(SupabaseConfig.SUPABASE_AUTH_TOKEN));
    return configs;
  }


}
