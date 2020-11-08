package com.github.vbychkovskyi.tg.client;

import java.util.HashMap;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

import com.github.vbychkovskyi.tg.client.handle.AuthorizationStateWaitTdlibParametersHandler;
import com.github.vbychkovskyi.tg.client.handle.DefaultNoOperationHandler;
import com.github.vbychkovskyi.tg.client.handle.Handler;

public class Application {

  public static void main(String[] args) {

    System.loadLibrary("tdjni");

    final HashMap<Integer, Client.ResultHandler> hashMap = new HashMap<>();

    final RootHandler resultHandler = new RootHandler(hashMap, new DefaultNoOperationHandler());

    final Client client = Client.create(resultHandler, null, null);

    final TdApi.SendMessage sendMessage = new TdApi.SendMessage(
      -1001211410985L,
      -1,
      null,
      null,
      new TdApi.InputMessageText(new TdApi.FormattedText("Я всьо ближче щоб щитати статку", null), false, false)
    );

    final Handler h1 =
      new AuthorizationStateWaitTdlibParametersHandler(client, resultHandler);

    hashMap.put(h1.getConstructor(), h1);

    client.send(sendMessage, resultHandler);
  }
}
