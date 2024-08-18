package com.example.demo.data;

import com.example.demo.entity.MerchandiseInfo;
import com.example.demo.repository.MerchandiseInfoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Sam
 * @create 2024-08-14 7:10 PM
 */

@Component
public class SampleDataGenerator implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(SampleDataGenerator.class);
  private final MerchandiseInfoRepository repository;

  public SampleDataGenerator(MerchandiseInfoRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("Loading Sample Data");

//    List<MerchandiseInfo> merchandises = IntStream.rangeClosed(1, 200)
//            .mapToObj(i -> new MerchandiseInfo("product" + i, "商品" + i,
//                    new Random().nextFloat(1000), new Random().nextInt(1000),
//                    LocalDateTime.now(), LocalDateTime.now(),
//                    "user0003"))
//            .collect(Collectors.toList());
//
//    repository.saveAll(merchandises);

  }
}
