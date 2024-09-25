package com.paung.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paung.entity.Media;
import com.paung.entity.PhotoProfile;
import com.paung.repository.MediaRepository;
import com.paung.repository.PhotoProfileRepository;
import com.paung.response.MediaResponse;
import com.paung.response.PhotoProfileResponse;
import okhttp3.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class MediaService {

  @Value("${application.config.supabase-storage_bucket-url}")
  String SUPABASE_STORAGE_BUCKET_URL;
  @Value("${application.config.supabase-storage-url}")
  String SUPABASE_STORAGE_URL;
  @Value("${application.config.supabase-auth-token}")
  String SUPABASE_AUTH_TOKEN;

  @Autowired
  private MediaRepository mediaRepository;
  @Autowired
  private PhotoProfileRepository photoProfileRepository;

  public MediaResponse uploadMedia(String talk_id, MultipartFile file) throws IOException {
    String randUUID = UUID.randomUUID().toString();

    Map<String, MediaType> mediaTypes = new HashMap<>();
    mediaTypes.put("png", MediaType.get("image/png"));
    mediaTypes.put("jpg", MediaType.get("image/jpeg"));
    mediaTypes.put("jpeg", MediaType.get("image/jpeg"));
    mediaTypes.put("mp4", MediaType.get("video/mp4"));

    String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
    MediaType mediaType = mediaTypes.get(fileExtension);

    if (mediaType == null) {
      throw new RuntimeException("Unsupported file type: " + fileExtension);
    }

//    String generatedValueMedia = null;
//    if (talk_id != null){
      String generatedValueMedia = new Random().nextInt() +  "." + FilenameUtils.getExtension(file.getOriginalFilename());
//    }else{
//      generatedValueMedia = talk_id + "_" + System.currentTimeMillis() + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
//    }

    OkHttpClient client = new OkHttpClient();
    RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", file.getOriginalFilename(), RequestBody.create(file.getBytes(), mediaType))
            .build();

    Request request = new Request.Builder()
            .url(SUPABASE_STORAGE_BUCKET_URL + generatedValueMedia)
            .header("Authorization", "Bearer " + SUPABASE_AUTH_TOKEN)
            .post(requestBody)
            .build();

    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) {
      throw new RuntimeException("Error uploading file: " + response.code());
    }

    String responseBody = response.body().string();
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.readTree(responseBody);

    String responseMedia_id = jsonNode.get("Id").asText();
    String responseKey = jsonNode.get("Key").asText();

    Media media = new Media();
    media.setId(randUUID);
    media.setOriginal_name_file(file.getOriginalFilename());
    media.setMedia_id(responseMedia_id);
    media.setKey(responseKey);
    media.setTalk_id(talk_id);

    mediaRepository.save(media);

    MediaResponse mediaResponse = new MediaResponse();
    mediaResponse.setMedia(media);
    mediaResponse.setOriginalFileName(file.getOriginalFilename());
    mediaResponse.setGenerated_name(generatedValueMedia);

    return mediaResponse;
  }


  public PhotoProfileResponse uploadMediaPhotoProfile(String user_id, MultipartFile file) throws IOException {
    Map<String, MediaType> mediaTypes = new HashMap<>();
    mediaTypes.put("png", MediaType.get("image/png"));
    mediaTypes.put("jpg", MediaType.get("image/jpeg"));
    mediaTypes.put("jpeg", MediaType.get("image/jpeg"));
    mediaTypes.put("mp4", MediaType.get("video/mp4"));

    String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
    MediaType mediaType = mediaTypes.get(fileExtension);

    if (mediaType == null) {
      throw new RuntimeException("Unsupported file type: " + fileExtension);
    }

    String generatedValueMedia = user_id + "_" + System.currentTimeMillis() + new Random().nextInt(1000) + "." + FilenameUtils.getExtension(file.getOriginalFilename());

    OkHttpClient client = new OkHttpClient();
    RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", file.getOriginalFilename(), RequestBody.create(file.getBytes(), mediaType))
            .build();

    Request request = new Request.Builder()
            .url(SUPABASE_STORAGE_BUCKET_URL + "profile/" + generatedValueMedia)
            .header("Authorization", "Bearer " + SUPABASE_AUTH_TOKEN)
            .post(requestBody)
            .build();

    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) {
      throw new RuntimeException("Error uploading file: " + response.code());
    }

    String responseBody = response.body().string();
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.readTree(responseBody);

    String responseMedia_id = jsonNode.get("Id").asText();
    String responseKey = jsonNode.get("Key").asText();

    PhotoProfile photoProfile = new PhotoProfile();
    photoProfile.setId(UUID.randomUUID().toString());
    photoProfile.setOriginal_name_file(file.getOriginalFilename());
    photoProfile.setMedia_id(responseMedia_id);
    photoProfile.setKey(responseKey);
    photoProfile.setUser_id(user_id);

    photoProfileRepository.save(photoProfile);

    PhotoProfileResponse photoProfileResponse = new PhotoProfileResponse();
    photoProfileResponse.setPhotoProfile(photoProfile);
    photoProfileResponse.setOriginalFileName(file.getOriginalFilename());
    photoProfileResponse.setGenerated_name(generatedValueMedia);

    return photoProfileResponse;
  }

  public ResponseEntity<?> findByMediaId(String mediaId, String type) {
    switch (type){
      case "json":
        return ResponseEntity.ok(getByMediaId(mediaId));
      case "binary":
        byte[] binary = getBinaryByMediaId(mediaId);
        return ResponseEntity.ok(binary);
    }
    return (ResponseEntity<?>) ResponseEntity.notFound();
  }


  public ResponseEntity<?> getById(String id, String type) throws IOException {
    if(type.equals("json")){
      return ResponseEntity.ok(mediaRepository.findById(id).get());
    }
    if(type.equals("binary")){
      return ResponseEntity.ok(getBinaryById(id));
    }
    return (ResponseEntity<?>) ResponseEntity.notFound();
  }


  public List<Media> findMediaByTalk_Id(String talkId){
    return mediaRepository.findByTalk_id(talkId);
  }

  public Media getByMediaId(String mediaId){
    Media media = mediaRepository.findFirstByMedia_id(mediaId);
    return media;
  }

  private byte[] getBinaryByMediaId(String mediaId) {
    Media media = mediaRepository.findFirstByMedia_id(mediaId);
    String url = SUPABASE_STORAGE_URL + media.getKey();
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.execute(url, HttpMethod.GET,
            request -> {
              request.getHeaders().add("Authorization", "Bearer " + SUPABASE_AUTH_TOKEN);
            },
            response -> response.getBody());


    ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
    return response.getBody();
  }

  public byte[] getBinaryById(String id) throws IOException {
    Optional<Media> media = mediaRepository.findById(id);
    OkHttpClient client = new OkHttpClient();
    if (media.isPresent()) {
      String url = SUPABASE_STORAGE_URL + media.get().getKey();
      Request request = new Request.Builder()
              .url(url)
              .header("Authorization", "Bearer " + SUPABASE_AUTH_TOKEN)
              .build();

      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) {
        throw new RuntimeException("Error uploading file: " + response.code());
      }

      return response.body().bytes();
//      return response.getBody();
    } else {
      return null;
    }
  }

  public byte[] getBinaryByUserId(String id) throws IOException {
    Optional<PhotoProfile> photoProfile = photoProfileRepository.findFirstByUser_id(id);
    OkHttpClient client = new OkHttpClient();
    if (photoProfile.isPresent()) {
      String url = SUPABASE_STORAGE_URL + photoProfile.get().getKey();
      Request request = new Request.Builder()
              .url(url)
              .header("Authorization", "Bearer " + SUPABASE_AUTH_TOKEN)
              .build();

      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) {
        throw new RuntimeException("Error uploading file: " + response.code());
      }

      return response.body().bytes();
//      return response.getBody();
    } else {
      return null;
    }
  }


  public Media editToBeNewTalk(String id, String talkId) {
    var media = mediaRepository.findById(id).get();
    media.setTalk_id(talkId);
    mediaRepository.save(media);
    return media;
  }

  public List<Media> editToBeNewTalks(List<String> ids, String talkId) {
    List<Media> medias = mediaRepository.findAllById(ids);
    medias.forEach(media -> media.setTalk_id(talkId));
    mediaRepository.saveAll(medias);
    return medias;
  }


}
