package com.github.vbychkovskyi.tg.client.handle;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OnReadyHandler implements Handler {

  private final Client client;
  private final Client.ResultHandler resultHandler;

  @Override
  public int getConstructor() {
    return TdApi.AuthorizationStateReady.CONSTRUCTOR;
  }

  @Override
  public void onTypedResult(final TdApi.Object object) {

  }

  @Override
  public void onResult(final TdApi.Object object) {


  }
}
