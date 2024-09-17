package com.paung.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class PhotoProfile {
    @Id
    private String id;
    private String orignal_name_file;

    @Indexed(unique = true)
//  @Field("media_id")
    private String media_id;

    private String key;

    //  @Field("post_id")
    private String user_id;
}
