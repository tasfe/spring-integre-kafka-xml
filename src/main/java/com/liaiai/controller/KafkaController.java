package com.liaiai.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wangnan on 16/5/26.
 */
@Controller
@RequestMapping("/test")
public class KafkaController {

    private static final Logger logger = Logger.getLogger(KafkaController.class);

    @Resource
    MessageChannel inputToKafka;

    @Resource
    PollableChannel inputFromKafka;

    @RequestMapping(value = "/test")
    public @ResponseBody Object index(){
        JSONObject json=new JSONObject();
        json.put("kd","aa");

            inputToKafka.send(MessageBuilder.withPayload(json.toJSONString())
                    .setHeader(KafkaHeaders.MESSAGE_KEY,"k1")
                    .setHeader(KafkaHeaders.TOPIC, "test-01")
                    .build());

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Message<?> received = inputFromKafka.receive(10000);
//                    while (received != null) {
//                        System.out.println("======received=====" + JSON.toJSONString(received));
//                        GenericMessage message = (GenericMessage)received;
//                        System.out.println("-----=-=-="+ JSON.toJSONString(message.getPayload()));
//                        received = inputFromKafka.receive(10000);
//                    }
//                }
//            }).start();
        return "finished";
    }
//    @PostConstruct
//    public void receive(){
//        Message<?> received = inputFromKafka.receive(10000);
//        while (received != null) {
//            System.out.println("======received=====" + JSON.toJSONString(received));
//            GenericMessage message = (GenericMessage)received;
//            System.out.println("-----=-=-="+ JSON.toJSONString(message.getPayload()));
//            received = inputFromKafka.receive(10000);
//        }
//    }

}
