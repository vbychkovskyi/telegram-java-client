package com.github.vbychkovskyi.tg.client.handle;

import java.util.Map;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RootHandler implements Client.ResultHandler {

  private final Map<Integer, Client.ResultHandler> handlers;
  private final Client.ResultHandler defaultHandler;

  @Override
  public void onResult(final TdApi.Object object) {
    System.out.println(object);

    final Client.ResultHandler handler = handlers.getOrDefault(object.getConstructor(), defaultHandler);

    handler.onResult(object);
  }
}
