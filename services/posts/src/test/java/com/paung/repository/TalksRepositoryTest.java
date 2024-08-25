package com.paung.repository;

import cn.hutool.core.lang.Snowflake;
import com.paung.entity.Talks;
import com.paung.utils.SNFID;
import de.huxhorn.sulky.ulid.ULID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class TalksRepositoryTest {

  @Autowired
  private TalksRepository talksRepository;

  @Test
  public void saveTalks(){
    UUID uuid = UUID.randomUUID();
//    ULID ulid = new ULID();
//    System.out.println("uuid = " + uuid);
//    Talks talk = new Talks();
//    talk.setTalk_id(ulid.nextULID());
//    talk.setUser_id(uuid);
//    talk.setContent("Hello World #test");
//
//    System.out.println("talk = " + talk);
//    System.out.println("ulid = " + ulid);
//
//    talksRepository.save(talk);
    Talks talks = new Talks();
    talks.setContent("happy morning #pagi");
//    talks.setContent("haaaaa");
    talks.setUser_id(uuid);
    talksRepository.save(talks);
  }

  @Test
  void testUlid(){
    String ulid =  new ULID().nextULID();
    System.out.println("ulid = " + ulid);
  }

  @Test
  void createReplyTalk(){
    Snowflake snowflake = new Snowflake();
    UUID uuid = UUID.randomUUID();

    String content =
                     """
                      halo duniaaaaaaa
                     """;
    Talks parentTalk = new Talks();
//    parentTalk.setTalk_id(snowflake.nextIdStr());
//    parentTalk.setTalk_id("1827356862700937216");
    parentTalk.setTalk_id("1827358091933999104");

    Talks talks = new Talks();
//    talks.setContent("halo duniaaaaaaaaaaa ");
    talks.setContent(content);
    talks.setUser_id(uuid);
    talks.setParentTalk(parentTalk);
    talksRepository.save(talks);
  }

//  @Test
//  void insertReplyTalk(){
//    String parentTalkId = "01J629PHP3R5ZAE56W4715E47R";
//    String ulid = new ULID().nextULID();
//    UUID uuid = UUID.randomUUID();
//    String content = """
//            pagi yang cerahhhhh
//            """;
//    Talks talks = new Talks(new ULID());
//    talks.setContent(content);
////    talks.setParentTalk(parentTalkId);
//    // pake uuid ajalah anjeng, ulid kurang minat banyak
//    talksRepository.insertReplyTalk(ulid, uuid, content, "01J629PHP3R5ZAE56W4715E47R");
//  }

}

