package com.github.vbychkovskyi.tg.client;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

public class Handler implements Client.ResultHandler {

  @Override
  public void onResult(final TdApi.Object object) {
    System.out.println(object);

    switch (object.getConstructor()) {
      case TdApi.AuthorizationStateWaitTdlibParameters.CONSTRUCTOR: {
        final TdApi.AuthorizationStateWaitTdlibParameters authorizationState = (TdApi.AuthorizationStateWaitTdlibParameters) object;

        new TdApi.SetTdlibParameters();
      }
      case TdApi.UpdateAuthorizationState.CONSTRUCTOR: {
        final TdApi.UpdateAuthorizationState authorizationState = (TdApi.UpdateAuthorizationState) object;
      }
      default:
        System.out.println("Unknown object:" + object.getConstructor());
    }
  }
}
