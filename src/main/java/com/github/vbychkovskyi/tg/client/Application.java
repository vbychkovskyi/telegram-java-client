package com.github.vbychkovskyi.tg.client;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

public class Application {

  public static void main(String[] args) {

    System.loadLibrary("tdjni");

    final Client client = Client.create(new Handler(), null, null);

    client.send(new TdApi.GetChats(), new Handler());
  }
}
