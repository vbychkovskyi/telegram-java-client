package com.github.vbychkovskyi.tg.client.handle;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

public interface Handler<T extends TdApi.Object> extends Client.ResultHandler {

  int getConstructor();

  void onTypedResult(T object);
}
