package com.rsupport.bucketlist.admin.push;

import com.rsupport.bucketlist.core.domain.PushMessage;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class FcmSender {

  private final String fcmUrl;

  private final HttpHeaders headers = new HttpHeaders();

  //48시간
  private final int ttl = 172800;

  @Autowired
  public FcmSender(Environment env) {
    fcmUrl = env.getProperty("fcm.url");
    String fcmKey = env.getProperty("fcm.key");
    headers.add("Content-Type", "application/json");
    headers.add("Authorization", String.format("key=%s", fcmKey));
  }

  public void sendMessage(String topic, PushMessage pushMessage, long timeStamp) {
    JSONObject msg = new JSONObject();
    msg.put("title", pushMessage.getTitle());
    msg.put("content", pushMessage.getContent());
    msg.put("startDate", pushMessage.getStartDate());

    JSONObject data = new JSONObject();
    data.put("payload", 7000);
    data.put("msg", msg);
    data.put("sendTimestamp", timeStamp);

    JSONObject jsonMessage = new JSONObject();
    /*jsonMessage.put("to", String.format("/topics/%s", topic));*/
    jsonMessage.put("to", topic);
    jsonMessage.put("data", data);
    jsonMessage.put("time_to_live", ttl);

    String message = jsonMessage.toJSONString();

    HttpEntity<String> request = new HttpEntity<>(message, headers);
    trySendingRequest(request);
  }

  private void trySendingRequest(HttpEntity<String> request) {
    int tryCount = 0;

    trySendingRequestUntilMaxCount(request, tryCount);
  }

  private ResponseEntity<String> trySendingRequestUntilMaxCount(HttpEntity<String> request, int tryCount) {
    final int maxCount = 10;
    final ResponseEntity<String> responseEntity = new RestTemplate().exchange(this.fcmUrl, HttpMethod.POST, request, String.class);

    try {
      if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
        final JSONObject responseJsonBody = (JSONObject)new JSONParser().parse(responseEntity.getBody());
        log.info(responseJsonBody.toJSONString());
        final JSONArray results = (JSONArray) responseJsonBody.get("results");
        final JSONObject result = (JSONObject) results.get(0);
        final String messageId = (String) result.get("message_id");
        final String error = (String)responseJsonBody.get("error");

        if (messageId == null && !StringUtils.isEmpty(error)) {
          tryCount++;
          if (tryCount < maxCount) {
            log.warn(String.format("푸쉬 요청 메세지: %s, 재시도 횟수: %d", request.getBody(), tryCount));

            sleepThreadForSendingRequest();
            return trySendingRequestUntilMaxCount(request, tryCount);
          } else {
            log.warn(String.format("푸쉬 전송 실패: %s, 재시도 횟수: %d", request.getBody(), tryCount));
          }
        } else {
          log.info(String.format("푸쉬 전송 성공: %s, 메세지아이디: %s, 재시도 횟수: %d", request.getBody(), messageId, tryCount));
        }
      } else {
        log.warn(String.format("푸쉬 전송 실패 응답코드: %s", responseEntity.getStatusCode()));
      }
    } catch (ParseException e) {
      log.error(e.getMessage());
    }

    return responseEntity;
  }

  private void sleepThreadForSendingRequest() {
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
  }
}
