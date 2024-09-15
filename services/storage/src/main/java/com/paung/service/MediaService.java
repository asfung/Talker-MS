package com.paung.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paung.entity.Media;
import com.paung.repository.MediaRepository;
import com.paung.response.MediaResponse;
import okhttp3.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpInputMessage;
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

  public MediaResponse uploadMedia(String post_id, MultipartFile file) throws IOException {
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

    String generatedValueMedia = post_id + "_" + System.currentTimeMillis() + new Random().nextInt(Integer.parseInt(post_id)) + "." + FilenameUtils.getExtension(file.getOriginalFilename());

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
    media.setId(UUID.randomUUID().toString());
    media.setOrignal_name_file(file.getOriginalFilename());
    media.setMedia_id(responseMedia_id);
    media.setKey(responseKey);
    media.setPost_id(post_id);

    mediaRepository.save(media);

    MediaResponse mediaResponse = new MediaResponse();
    mediaResponse.setMedia(media);
    mediaResponse.setOriginalFileName(file.getOriginalFilename());
    mediaResponse.setGenerated_name(generatedValueMedia);

    return mediaResponse;
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


  public List<Media> findMediaByPostId(String post_id){
    return mediaRepository.findByPost_id(post_id);
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



}